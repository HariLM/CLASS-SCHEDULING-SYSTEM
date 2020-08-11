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
 
public class Course extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String cid = request.getParameter("cid");
        String cn = request.getParameter("cname");
        String in = request.getParameter("ins");
        String de = request.getParameter("dept");
        String m = request.getParameter("max");
        String n = request.getParameter("nofc");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tts", "root", "password");
            //out.print("You are successfully registered...");

            PreparedStatement ps = con.prepareStatement("call proced1(?,?,?,?,?)");
            PreparedStatement ps1 = con.prepareStatement("insert into handles values(?,?)");
 
            ps.setString(1, cid);
            ps.setString(2, cn);
            ps.setInt(3, Integer.parseInt(m));
            ps.setInt(4, Integer.parseInt(n));
            ps.setString(5, de);
            
            ps1.setString(1,cid);
            ps1.setString(2,in);
            int i = ps.executeUpdate();
            int j = ps1.executeUpdate();
            
            
            if(i>0 && j>0) {
            	request.setAttribute("errorMessage", "Your data has been successfully recorded");   
            	request.setAttribute("colour", "#32CD32");
            }
 
        } catch (Exception e2) {
        	request.setAttribute("errorMessage", e2);
        	request.setAttribute("colour", "#FF0000");
        }
        request.getRequestDispatcher("/add-courses.jsp").forward(request, response);

 
        out.close();
    }
}
