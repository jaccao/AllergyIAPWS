package com.allergyiap.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.allergy.service.AllergyService;
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
			response.sendRedirect("ProductCatalog");
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
			response.sendRedirect("ProductCatalog");
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
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("User");

		int id = 0;
		String name = request.getParameter("name");
		
		long allergy = new Long(request.getParameter("allergy"));
		// long customer = new Long(request.getParameter("customer"));
		String description = request.getParameter("description");

		if (request.getParameter("id") != null) { // Edit Product
			id = Integer.parseInt(request.getParameter("id"));

			ProductCatalog p = ProductCatalogService.get(id);
			p.setProductName(name);
			p.setProductDescription(description);
			p.setAllergyId(allergy);
			ProductCatalogService.update(p);
			
		} else { // New Product

			ProductCatalog p = new ProductCatalog(allergy, customer.getId(), name, description);
			ProductCatalogService.insert(p);
			 
			//TODO Implement Upload Images
			//saveImage(request, id);
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
		request.setAttribute("alergies", AllergyService.getAll());

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/edit-catalog.jsp");
		rd.forward(request, response);

	}

	private void newProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("alergies", AllergyService.getAll());
		request.getRequestDispatcher("WEB-INF/pages/new-catalog.jsp").forward(request, response);

	}

	private void saveImage(HttpServletRequest request, int id) {

		System.out.println("saveImage");
		
		InputStream inputStream = null;
		FileOutputStream outputStream = null;

		try {
			for (Part part : request.getParts()) {
				inputStream = request.getPart(part.getName()).getInputStream();
				int i = inputStream.available();
				byte[] b = new byte[i];
				inputStream.read(b);

				System.out.println("Length : " + b.length);

				// Finding the fileName //
				String fileName = "";
				String partHeader = part.getHeader("content-disposition");

				System.out.println("Part Header = " + partHeader);
				System.out.println("part.getHeader(content-disposition) = " + part.getHeader("content-disposition"));

				for (String temp : part.getHeader("content-disposition").split(";")) {
					if (temp.trim().startsWith("filename")) {
						fileName = temp.substring(temp.indexOf('=') + 1).trim().replace("\"", "");
					}
				}

				String uploadDir = System.getProperty("jboss.server.base.dir") + "/upload";
				System.out.println("File will be Uploaded at: " + uploadDir + "/" + fileName);
				outputStream = new FileOutputStream(uploadDir + "/" + fileName);
				outputStream.write(b);
				inputStream.close();

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
