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
import com.allergy.service.ProductCatalogService;
import com.allergy.service.StationService;
import com.allergy.service.UserService;
import com.allergyiap.beans.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/Users")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = (String) request.getParameter("action");

		try {

			if (action == null || action.isEmpty()) {
				listUsers(request, response);
			} else if (action.equals("new")) {
				newUser(request, response);
			} else if (action.equals("edit")) {
				editUser(request, response);
			} else if (action.equals("delete")) {
				deleteUser(request, response);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("Users");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = (String) request.getParameter("action");

			if (action.equals("new")) {
				saveUser(request, response);
			} else if (action.equals("edit")) {
				saveUser(request, response);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("Users");
		}
	}
	
	private void saveUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		
		String name = request.getParameter("name");
		String secondName = request.getParameter("secondName");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		String location = request.getParameter("location");
		String weekdays = request.getParameter("weekdays");
		String time = request.getParameter("time");

		if (request.getParameter("id") != null) { // Edit user
			id = Integer.parseInt(request.getParameter("id"));

			User u = UserService.get(id);
			u.setUser_name(name);
			u.setUser_second_name(secondName);
			u.setUser_mail(mail);
			u.setUser_password(password);
			u.setUser_station_default(location);
			u.setAlarm_weekdays(weekdays);
			u.setAlarm_time(time);
			UserService.update(u);
			
		} else { // New Product

			User u = new User(name, secondName, mail, password, location, weekdays, time);
			UserService.insert(u);
		}

		response.sendRedirect("Users");
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		List<User> users = UserService.getAll();
		request.setAttribute("users", users);
		try {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/show-users.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void editUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		User user = UserService.get(id);
		
		request.setAttribute("u", user);
		request.setAttribute("users", UserService.getAll());
		request.setAttribute("locations", StationService.getAll());

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/edit-user.jsp");
		rd.forward(request, response);

	}
	
	private void newUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("users", UserService.getAll());
		request.setAttribute("locations", StationService.getAll());
		request.getRequestDispatcher("WEB-INF/pages/new-user.jsp").forward(request, response);

	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		UserService.delete(id);
		response.sendRedirect("Users");
	}
}
