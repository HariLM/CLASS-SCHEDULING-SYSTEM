package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class signin
 */
//@WebServlet("/signin")
public class signin extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname=request.getParameter("username");
		String pass=request.getParameter("password");
		
		if(uname.equals("admin") && pass.equals("admin")) {
			HttpSession session=request.getSession();
			session.setAttribute("username",uname);
			response.sendRedirect("add-courses.jsp");
		}
		else
		{
			response.sendRedirect("forgot-password.html");
		}
	}

}
