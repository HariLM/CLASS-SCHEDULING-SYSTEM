package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteinstructor
 */
@WebServlet("/deleteinstructor")
public class deleteinstructor extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        
        String id = request.getParameter("id");

        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tts", "root", "password");
            //out.print("You are successfully registered...");

            PreparedStatement ps = con.prepareStatement("delete from instructor where INS_ID='"+id+"'");
            int i = ps.executeUpdate();

            
            
            if(i>0) {
            	request.setAttribute("errorMessage", "Data deleted");   
            	request.setAttribute("colour", "#32CD32");
            }
 
        } catch (Exception e2) {
        	request.setAttribute("errorMessage", e2);
        	request.setAttribute("colour", "#FF0000");
        }
        request.getRequestDispatcher("/instructors.jsp").forward(request, response);

	}

}
