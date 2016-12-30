package com.allergyiap.servlet;

import java.io.IOException;

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
import com.allergyiap.beans.Customer;
import com.allergyiap.beans.ProductCatalog;

import java.util.List;

/**
 * Servlet implementation class ProductCatalog
 */
@WebServlet("/ProductCatalog")
public class ProductCatalogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductCatalogServlet() {
		super();
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
				listProducts(request, response);
			} else if (action.equals("new")) {
				newProduct(request, response);
			} else if (action.equals("edit")) {
				editProduct(request, response);
			} else if (action.equals("delete")) {
				deleteProduct(request, response);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("ProductCatalog");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = (String) request.getParameter("action");

			if (action.equals("new")) {
				saveProduct(request, response);
			} else if (action.equals("edit")) {
				saveProduct(request, response);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("ProductCatalog");
		}

	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		ProductCatalogService.delete(id);

		response.sendRedirect("ProductCatalog");
	}

	private void saveProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		response.sendRedirect("ProductCatalog");
	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("User");
		long id = customer.getIdcustomer();

		List<ProductCatalog> products = null;
		if (customer.isAdmin())
			products = ProductCatalogService.getAll();
		else
			products = ProductCatalogService.getAllByCustomer(id);

		request.setAttribute("products", products);
		try {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/show-catalog.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void editProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		ProductCatalog product = ProductCatalogService.get(id);

		request.setAttribute("customers", CustomerService.getAll());
		request.setAttribute("p", product);
		request.setAttribute("alergies", AllergyService.getAll());

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/edit-catalog.jsp");
		rd.forward(request, response);

	}

	private void newProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("customers", CustomerService.getAll());
		request.setAttribute("alergies", AllergyService.getAll());
		request.getRequestDispatcher("WEB-INF/pages/new-catalog.jsp").forward(request, response);

	}
}
