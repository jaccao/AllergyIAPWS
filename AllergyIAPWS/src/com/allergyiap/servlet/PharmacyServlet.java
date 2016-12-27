package com.allergyiap.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.allergy.service.CustomerService;
import com.allergy.service.PharmacyService;
import com.allergy.service.RelationPharmaciesCustomersService;
import com.allergyiap.beans.Customer;
import com.allergyiap.beans.Pharmacy;
import com.allergyiap.beans.RelationPharmaciesCustomers;

/**
 * Servlet implementation class PharmacyServlet
 */
@WebServlet("/Pharmacies")
public class PharmacyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PharmacyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = (String) request.getParameter("action");

		try {

			if (action == null || action.isEmpty()) {
				listPharmacies(request, response);
			} else if (action.equals("new")) {
				newPharmacy(request, response);
			} else if (action.equals("edit")) {
				editPharmacy(request, response);
			} else if (action.equals("delete")) {
				deletePharmacy(request, response);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String action = (String) request.getParameter("action");

			if (action.equals("new")) {
				savePharmacy(request, response);
			} else if (action.equals("edit")) {
				savePharmacy(request, response);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void listPharmacies(HttpServletRequest request, HttpServletResponse response) {

		List<Pharmacy> pharmacies = PharmacyService.getAll();
		Map<Pharmacy, List<Integer>> relations = new HashMap<Pharmacy, List<Integer>>();
		for(Pharmacy p: pharmacies){
			List<Integer> customers = RelationPharmaciesCustomersService.getCustomersByPharmacy(p.getId_pharmacy());
			relations.put(p, customers);
		}
		request.setAttribute("pharmacies", relations);
		try {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/pharmaciesList.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void savePharmacy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = 0;
		if (request.getParameter("id_pharmacy") != null) {
			id = Integer.parseInt(request.getParameter("id_pharmacy"));
		}
		String name_pharmacy = request.getParameter("nPharmacyName");
		String latitude = request.getParameter("nLatitude");
		String longitude = request.getParameter("nLongitude");

		Pharmacy p = new Pharmacy(id, name_pharmacy, latitude, longitude);

		if (id != 0) {
			PharmacyService.update(p);
		} else {
			PharmacyService.insert(p);
		}
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("User");
		if(customer.isAdmin()){
			response.sendRedirect("Pharmacies");
		}else{
			response.sendRedirect("Relations?idcustomer="+customer.getIdcustomer());
		}
		
	}

	private void editPharmacy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id_pharmacy"));

		Pharmacy pharmacy = PharmacyService.get(id);
		request.setAttribute("p", pharmacy);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/edit-pharmacy.jsp");
		rd.forward(request, response);

	}

	private void newPharmacy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/pages/new-pharmacy.jsp").forward(request, response);

	}

	private void deletePharmacy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id_pharmacy"));
		PharmacyService.delete(id);
		response.sendRedirect("Pharmacies");
	}
}
