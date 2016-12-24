package com.allergyiap.servlet;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.allergy.comm.SystemHttp;
import com.allergy.comm.SystemSql;

/**
 * Servlet implementation class XarxaImportServlet
 */
@WebServlet("/XarxaImportServlet")
public class XarxaImportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XarxaImportServlet() {
		super();
	}

	private void updateData() {
		try {
			InputSource is = new InputSource(
					new StringReader(SystemHttp.getHTML("http://lap.uab.cat/api/v0/forecast/catalunya/en/xml")));
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(is);

			NodeList nList = doc.getElementsByTagName("report");

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);

				Element eReport = (Element) nNode;

				Element eStation = (Element) eReport.getElementsByTagName("station").item(0);
				Element eStationName = (Element) eStation.getElementsByTagName("name").item(0);
				Element elatitudeStation = (Element) eStation.getElementsByTagName("latitude").item(0);
				Element elongitudeStation = (Element) eStation.getElementsByTagName("longitude").item(0);

				Element eDate = (Element) eReport.getElementsByTagName("date").item(0);
				Element eDateStart = (Element) eDate.getElementsByTagName("start").item(0);
				Element eDateEnd = (Element) eDate.getElementsByTagName("end").item(0);

				Element eCurrent = (Element) eReport.getElementsByTagName("current").item(0);
				Element eCurrentPollens = (Element) eCurrent.getElementsByTagName("pollens").item(0);

				Element eForecast = (Element) eReport.getElementsByTagName("forecast").item(0);
				Element eForecastPollens = (Element) eForecast.getElementsByTagName("pollens").item(0);

				String sStationName = eStationName.getTextContent();
				String slatitudeStation = elatitudeStation.getTextContent();
				String slongitudeStation = elongitudeStation.getTextContent();

				String qStationInsert = "INSERT INTO station(name_station, latitude, longitude) VALUES ('"
						+ sStationName + "'," + slatitudeStation + "," + slongitudeStation + ");";
				String qStationUpdate = "UPDATE station SET latitude = " + slatitudeStation + ", longitude = "
						+ slongitudeStation + " WHERE name_station = '" + sStationName + "';";
				List<HashMap<String, Object>> stationExists = SystemSql
						.executeQuery("SELECT * FROM station WHERE name_station = '" + sStationName + "';");

				if (stationExists.isEmpty()) {
					SystemSql.execute(qStationInsert);
				} else {
					SystemSql.execute(qStationUpdate);
				}

				String sDateStart = eDateStart.getTextContent();
				String sDateEnd = eDateEnd.getTextContent();

				List<HashMap<String, Object>> lmAllergy = SystemSql.executeQuery("SELECT * FROM allergy");
				for (HashMap<String, Object> oAllergy : lmAllergy) {
					String aAllergyCode = oAllergy.get("allergy_code").toString();
					String aAllergyId = oAllergy.get("idallergy").toString();
					NodeList nCurrentPollens = eCurrentPollens.getElementsByTagName(aAllergyCode);
					NodeList nForecastPollens = eForecastPollens.getElementsByTagName(aAllergyCode);
					if (nCurrentPollens.getLength() > 0 && nForecastPollens.getLength() > 0) {
						String sCurrentPollens = nCurrentPollens.item(0).getTextContent();
						String sForecastPollens = nForecastPollens.item(0).getTextContent();
						String qAllergyLevel = String.format(
								"INSERT INTO public.allergy_level(allergy_idallergy, current_level, station, date_start, date_end, forecast_level) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
								aAllergyId, sCurrentPollens, sStationName, sDateStart, sDateEnd, sForecastPollens);
						SystemSql.execute(qAllergyLevel);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.getWriter().append("[");
			List<HashMap<String, Object>> lmAllergyLevel = SystemSql.executeQuery(
					"SELECT * FROM allergy_level WHERE date_start <= CURRENT_DATE+2 AND CURRENT_DATE+2 <= date_end");
			if (lmAllergyLevel.isEmpty()) {
				this.updateData();
				lmAllergyLevel = SystemSql.executeQuery(
						"SELECT * FROM allergy_level WHERE date_start <= CURRENT_DATE+2 AND CURRENT_DATE+2 <= date_end");
			}
			boolean isFirst = true;
			for (HashMap<String, Object> oAllergyLevel : lmAllergyLevel) {
				if (!isFirst) {
					response.getWriter().append(",");
				}
				isFirst = false;
				response.getWriter().append("{");
				response.getWriter().append("\"date_start\":").append("\"")
						.append(oAllergyLevel.get("date_start").toString()).append("\",");
				response.getWriter().append("\"date_end\":").append("\"")
						.append(oAllergyLevel.get("date_end").toString()).append("\",");

				response.getWriter().append("\"current_level\":").append("\"")
						.append(oAllergyLevel.get("current_level").toString()).append("\",");
				response.getWriter().append("\"forecast_level\":").append("\"")
						.append(oAllergyLevel.get("forecast_level").toString()).append("\",");
				response.getWriter().append("\"allergy_idallergy\":").append("\"")
						.append(oAllergyLevel.get("allergy_idallergy").toString()).append("\",");
				response.getWriter().append("\"idallergy_level\":").append("\"")
						.append(oAllergyLevel.get("idallergy_level").toString()).append("\",");

				String nameStation = oAllergyLevel.get("station").toString();
				response.getWriter().append("\"station\":").append("\"").append(nameStation).append("\",");
				List<HashMap<String, Object>> lmStation = SystemSql
						.executeQuery("SELECT * FROM station WHERE name_station = '" + nameStation + "';");
				response.getWriter().append("\"latitude\":").append("\"")
						.append(lmStation.get(0).get("latitude").toString()).append("\",");
				response.getWriter().append("\"longitude\":").append("\"")
						.append(lmStation.get(0).get("longitude").toString());
				response.getWriter().append("\"}");
			}
			response.getWriter().append("]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
