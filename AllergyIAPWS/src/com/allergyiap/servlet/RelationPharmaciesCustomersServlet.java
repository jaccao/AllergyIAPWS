package com.allergyiap.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.allergy.service.CustomerService;
import com.allergy.service.PharmacyService;
import com.allergy.service.ProductCatalogService;
import com.allergy.service.RelationPharmaciesCustomersService;
import com.allergyiap.beans.Customer;
import com.allergyiap.beans.Pharmacy;
import com.allergyiap.beans.ProductCatalog;
import com.allergyiap.beans.RelationPharmaciesCustomers;

@WebServlet("/Relations")
public class RelationPharmaciesCustomersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RelationPharmaciesCustomersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = (String) request.getParameter("action");

		try {

			if (action == null || action.isEmpty()) {
				listRelations(request, response);
			} else if (action.equals("new")) {
				newRelation(request, response);
			} else if (action.equals("delete")) {
				deleteRelation(request, response);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			String referer = request.getHeader("Referer");
			response.sendRedirect(referer);
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
			saveRelation(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
			String referer = request.getHeader("Referer");
			response.sendRedirect(referer);
		}

	}

	private void deleteRelation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int id_customer = Integer.parseInt(request.getParameter("idcustomer"));
		int id_pharmacy = Integer.parseInt(request.getParameter("idpharmacy"));
		try{
			RelationPharmaciesCustomersService.delete(id_customer,id_pharmacy);
		}catch(Exception ex){
			ex.printStackTrace();
			String referer = request.getHeader("Referer");
			response.sendRedirect(referer);
		}
		response.sendRedirect("Customers");
	}

	private void saveRelation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idcustomer = Integer.parseInt(request.getParameter("idcustomer"));
		int idpharmacy = Integer.parseInt(request.getParameter("pharmacy"));
		try{
			RelationPharmaciesCustomersService.insert(new RelationPharmaciesCustomers(idpharmacy, idcustomer));
		}catch(Exception ex){
			ex.printStackTrace();
			String referer = request.getHeader("Referer");
			response.sendRedirect(referer);
		}
		response.sendRedirect("Customers");
	}

	private void listRelations(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("idcustomer"));
		request.setAttribute("idcustomer",id);
		try {
			List<Pharmacy> pharmacies = RelationPharmaciesCustomersService.getPharmaciesByCustomer(id);
			request.setAttribute("pharmacies", pharmacies);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/managePharmacies.jsp");
			rd.forward(request, response);
		}catch(Exception ex){
			ex.printStackTrace();
			String referer = request.getHeader("Referer");
			response.sendRedirect(referer);
		}
	}

	private void newRelation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("idcustomer"));
		request.setAttribute("idcustomer",id);
		List<Pharmacy> p = PharmacyService.getNewPharmacies(id);
		request.setAttribute("pharmacies", p);
		try{
			request.getRequestDispatcher("WEB-INF/pages/new-relation.jsp").forward(request, response);
		}catch(Exception ex){
			ex.printStackTrace();
			String referer = request.getHeader("Referer");
			response.sendRedirect(referer);
		}

	}
}
