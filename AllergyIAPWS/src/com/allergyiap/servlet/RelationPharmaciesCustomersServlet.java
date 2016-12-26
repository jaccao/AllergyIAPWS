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

import com.allergy.service.AllergyService;
import com.allergy.service.CustomerService;
import com.allergy.service.ProductCatalogService;
import com.allergy.service.RelationPharmaciesCustomersService;
import com.allergyiap.beans.Customer;
import com.allergyiap.beans.Pharmacy;
import com.allergyiap.beans.ProductCatalog;

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
			response.sendRedirect("Relations");
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
			response.sendRedirect("Relations");
		}

	}

	private void deleteRelation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int id_customer = Integer.parseInt(request.getParameter("idcustomer"));
		int id_pharmacy = Integer.parseInt(request.getParameter("idpharmacy"));
		RelationPharmaciesCustomersService.delete(id_customer,id_pharmacy);
		response.sendRedirect("Relations");
	}

	private void saveRelation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("User");

		int id = 0;
		String name = request.getParameter("name");
		long allergy = new Long(request.getParameter("allergy"));
		String description = request.getParameter("description");
		String url = request.getParameter("url");
		
		if(customer.getUser_mail().equals("admin")){
			customer = (Customer) CustomerService.get(new Long(request.getParameter("customer")));
		}

		if (request.getParameter("id") != null) { // Edit Product
			id = Integer.parseInt(request.getParameter("id"));
			ProductCatalog c = new ProductCatalog(id, allergy, customer.getIdcustomer(), name, description, url);
			ProductCatalogService.update(c);
			
		} else { // New Product
			ProductCatalog p = new ProductCatalog(allergy, customer.getIdcustomer(), name, description, url);
			ProductCatalogService.insert(p);
		}

		response.sendRedirect("Relations");
	}

	private void listRelations(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("LLISTEM FARMACIES");
		int id = Integer.parseInt(request.getParameter("idcustomer"));
		List<Pharmacy> pharmacies = RelationPharmaciesCustomersService.getPharmaciesByCustomer(id);
		request.setAttribute("pharmacies", pharmacies);
		try {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/managePharmacies.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void newRelation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("NOVA RELACIÓ");
		request.getRequestDispatcher("WEB-INF/pages/new-relation.jsp").forward(request, response);

	}
}
