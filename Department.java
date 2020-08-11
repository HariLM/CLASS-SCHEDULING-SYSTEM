package com.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class Department extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        
        String n = request.getParameter("deptname");
        String id = request.getParameter("deptid");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tts", "root", "password");
            //out.print("You are successfully registered...");

            PreparedStatement ps = con.prepareStatement("insert into DEPARTMENT values(?,?)");
 
            ps.setString(1, id);
            ps.setString(2, n);
            
            int i = ps.executeUpdate();
            if(i>0) {
            	request.setAttribute("errorMessage", "Your data has been successfully recorded");
            	request.setAttribute("colour", "#32CD32");
            }
 
        } catch (Exception e2) {
        	request.setAttribute("errorMessage", "Error entering data");
        	request.setAttribute("colour", "#FF0000");
        }
        request.getRequestDispatcher("/add-department.jsp").forward(request, response);
 
    }
}
