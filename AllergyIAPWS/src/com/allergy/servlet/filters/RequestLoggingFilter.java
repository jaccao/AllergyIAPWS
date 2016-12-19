package com.allergy.servlet.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RequestLoggingFilter implements Filter {

	private ServletContext context;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);
		if (session != null || uri.toLowerCase().contains("rest") || uri.toLowerCase().endsWith(".css")
				|| uri.toLowerCase().endsWith(".js") || uri.toLowerCase().endsWith("loginservlet")
				|| uri.toLowerCase().endsWith("xarxaimportservlet")) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect("LoginServlet");
		}

		/*
		 * if ((session == null && !(uri.endsWith("jsp") ||
		 * uri.endsWith("LoginServlet"))) || (!customer &&
		 * !uri.endsWith("LoginServlet"))) {
		 * this.context.log("Unauthorized access request");
		 * ((HttpServletResponse) response).sendRedirect("LoginServlet"); } else
		 * { // pass the request along the filter chain chain.doFilter(request,
		 * response); }
		 */
	}

	@Override
	public void init(FilterConfig filter) throws ServletException {
		this.context = filter.getServletContext();
		this.context.log("RequestLoggingFilter initialized");
	}

}
