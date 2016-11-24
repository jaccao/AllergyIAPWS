package com.allergyiap.test;


import java.io.IOException;
import java.io.PrintWriter;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	try{
    		PrintWriter out = response.getWriter(); 
    		out.write("HELLO CATALOG\n");
    		List<ProductCatalogTable> rs = ProductCatalogTable.getAllProductCatalogs();
    		out.write("<b>" + rs.get(0).getProductName()+"</b>");
    		out.write("<b>" + rs.get(1).getProductName()+"</b>");
    		out.write("<b>" + rs.get(2).getProductName()+"</b>");

    		List<ProductCatalogTable> p = ProductCatalogTable.getProductCatalogById(1);
    		out.write("<b>" + p.get(0).getProductDescription()+"</b>");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
