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
 
public class Instructor extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String id = request.getParameter("id");
        String name= request.getParameter("name");
        String gender= request.getParameter("gender");
        String qua= request.getParameter("qua");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tts", "root", "password");
            //out.print("You are successfully registered...");

            PreparedStatement ps = con.prepareStatement("insert into instructor values(?,?,?,?)");
            
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, gender);
            ps.setString(4, qua);
            
            int i = ps.executeUpdate();
            if(i>0) {
            	request.setAttribute("errorMessage", "Your data has been successfully recorded");
            	request.setAttribute("colour", "#32CD32");
            }
 
        } catch (Exception e2) {
        	request.setAttribute("errorMessage", "Error entering data");
        	request.setAttribute("colour", "#FF0000");
        }
        request.getRequestDispatcher("/add-instructor.jsp").forward(request, response);
 
        out.close();
    }
}
