package com.allergyiap.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
				saveProduct(request, response);
			} else if (action.equals("edit")) {
				saveProduct(request, response);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));

		ProductCatalogService.delete(id);

		response.sendRedirect("ProductCatalog");
	}

	private void saveProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = 0;
		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		}

		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("User");

		String name = request.getParameter("name");
		long allergy = new Long(request.getParameter("allergy"));
		// long customer = new Long(request.getParameter("customer"));
		String description = request.getParameter("description");

		ProductCatalog p = new ProductCatalog(id, allergy, customer.getId(), name, description);

		if (id != 0) {
			ProductCatalogService.update(p);
		} else {
			ProductCatalogService.insert(p);
		}

		response.sendRedirect("ProductCatalog");
	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("User");
		long id = customer.getId();

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

		request.setAttribute("p", product);

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/edit-catalog.jsp");
		rd.forward(request, response);

	}

	private void newProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/pages/new-catalog.jsp").forward(request, response);

	}

}
