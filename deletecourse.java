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
 * Servlet implementation class deletecourse
 */
@WebServlet("/deletecourse")
public class deletecourse extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String id = request.getParameter("id");

        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tts", "root", "password");
            //out.print("You are successfully registered...");

            PreparedStatement ps = con.prepareStatement("delete from course where C_ID='"+id+"'");
            int i = ps.executeUpdate();

            
            
            if(i>0) {
            	request.setAttribute("errorMessage", "Data deleted");   
            	request.setAttribute("colour", "#32CD32");
            }
 
        } catch (Exception e2) {
        	request.setAttribute("errorMessage", e2);
        	request.setAttribute("colour", "#FF0000");
        }
        request.getRequestDispatcher("/courses.jsp").forward(request, response);

 
        out.close();
    }
}
