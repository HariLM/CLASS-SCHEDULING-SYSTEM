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
 
public class MeetingTime extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        //String d = request.getParameter("day");
        //String s = request.getParameter("slot");
        String slots[] = request.getParameterValues("slots");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tts", "root", "password");
            //out.print("You are successfully registered...");

            PreparedStatement ps = con.prepareStatement("insert into SLOTS (TIME) values(?)");
            int v=0;
            for (String st : slots) 
            { 
                //ps.setString(1,st);
                ps.setString(1,st);
                v=ps.executeUpdate();
            }

            //int i = ps.executeUpdate();
            if(v>0) {
            	request.setAttribute("errorMessage", "Your data has been successfully recorded");
            	request.setAttribute("colour", "#32CD32");
            }
 
        } catch (Exception e2) {
        	request.setAttribute("errorMessage", "Error entering data");
        	request.setAttribute("colour", "#FF0000");
        }
        request.getRequestDispatcher("/add-meetingtime.jsp").forward(request, response);
 
        out.close();
    }
}
