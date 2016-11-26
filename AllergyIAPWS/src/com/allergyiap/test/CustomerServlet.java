package com.allergyiap.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.CustomerTable;
import database.ProductCatalogTable;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet(description = "Manage the customer segment", urlPatterns = { "/Customers" })
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = (String) request.getParameter("action");

		try {

			if (action == null || action.isEmpty()) {
				listCustomers(request, response);
			} else if (action.equals("new")) {
				newCustomer(request, response);
			} else if (action.equals("edit")) {
				editCustomer(request, response);
			} else if (action.equals("delete")) {
				deleteCustomer(request, response);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String action = (String) request.getParameter("action");

			if (action.equals("new")) {
				saveCustomer(request, response);
			} else if (action.equals("edit")) {
				saveCustomer(request, response);
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void listCustomers(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		int id = 1;

		List<CustomerTable> customers = CustomerTable.getAllCustomers();

		request.setAttribute("customers", customers);
		try {
			RequestDispatcher rd = request.getRequestDispatcher("customersList.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void saveCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = 0;
		if (request.getParameter("id") != null){
			id = Integer.parseInt(request.getParameter("id"));
		}
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPassword");
		String companyName = request.getParameter("companyName");

		CustomerTable c = null;

		if (id != 0) {
			c = new CustomerTable(id, userName, userPass, companyName);
			c.updateCustomer();
		} else {
			c = new CustomerTable(userName, userPass, companyName);
			c.insertNewCustomer();
		}

		response.sendRedirect("Customers");
	}
	
	private void editCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		CustomerTable customer = CustomerTable.getCustomerById(id);

		request.setAttribute("c", customer);

		RequestDispatcher rd = request.getRequestDispatcher("edit-customer.jsp");
		rd.forward(request, response);

	}

	private void newCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("new-customer.jsp").forward(request, response);

	}
	
	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));

		CustomerTable customer = CustomerTable.getCustomerById(id);

		customer.deleteCustomer();

		response.sendRedirect("Customers");
	}

}
