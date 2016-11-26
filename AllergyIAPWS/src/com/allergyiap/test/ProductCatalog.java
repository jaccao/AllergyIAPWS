package com.allergyiap.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.ProductCatalogTable;
import java.util.List;

/**
 * Servlet implementation class ProductCatalog
 */
@WebServlet("/ProductCatalog")
public class ProductCatalog extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductCatalog() {
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

		ProductCatalogTable product = ProductCatalogTable.getProductCatalog(id);

		product.deleteProduct();

		response.sendRedirect("ProductCatalog");
	}

	private void saveProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = 0;
		if (request.getParameter("id") != null){
			id = Integer.parseInt(request.getParameter("id"));
		}
		String name = request.getParameter("name");
		long allergy = new Long(request.getParameter("allergy"));
		long customer = new Long(request.getParameter("customer"));
		String description = request.getParameter("description");

		ProductCatalogTable p = null;

		if (id != 0) {
			p = new ProductCatalogTable(id, allergy, customer, name, description);
			p.updateProduct();
		} else {
			p = new ProductCatalogTable(allergy, customer, name, description);
			p.insertNewProduct();
		}

		response.sendRedirect("ProductCatalog");
	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		int id = 1;

		List<ProductCatalogTable> products = ProductCatalogTable.getProductCatalogByCustomer(id);

		request.setAttribute("products", products);
		try {
			RequestDispatcher rd = request.getRequestDispatcher("show-catalog.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void editProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		ProductCatalogTable product = ProductCatalogTable.getProductCatalog(id);

		request.setAttribute("p", product);

		RequestDispatcher rd = request.getRequestDispatcher("edit-catalog.jsp");
		rd.forward(request, response);

	}

	private void newProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("new-catalog.jsp").forward(request, response);

	}

}
