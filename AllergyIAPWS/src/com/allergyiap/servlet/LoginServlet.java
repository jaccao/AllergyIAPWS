package com.allergyiap.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.allergy.service.CustomerService;
import com.allergyiap.beans.Customer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/LoginPage.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		try {

			String user = request.getParameter("user_name");
			String pass = request.getParameter("user_password");

			Customer customer = null;

			if (!user.isEmpty() && !pass.isEmpty()) {

				if (user.equals("admin") && pass.equals("admin")) {
					customer = new Customer(user, pass, "Admin");
				} else {
					customer = CustomerService.get(user, pass);
				}
			}

			HttpSession session = request.getSession(true);

			if (customer != null) {

				session.setAttribute("User", customer);

				// setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30 * 60);

				Cookie userName = new Cookie("username", customer.getUser_name());
				// Cookie password = new Cookie("password",
				// customer.getPassword());
				userName.setMaxAge(30 * 60);
				// password.setMaxAge(30 * 60);
				response.addCookie(userName);
				// response.addCookie(password);

				response.sendRedirect("index.jsp");
			} else {
				// response.sendRedirect("WEB-INF/pages/InvalidLogin.jsp"); //
				// error page
				session.setAttribute("error", "Crendentials incorrect");
				response.sendRedirect("LoginServlet"); // logged-in page
			}

		}

		catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
