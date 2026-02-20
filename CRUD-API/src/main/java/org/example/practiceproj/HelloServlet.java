package org.example.practiceproj;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/addBook")
public class HelloServlet extends HttpServlet {
    private String message;


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter pw=response.getWriter();
        response.setContentType("text/html");
        String name= request.getParameter("name");
        String edition=request.getParameter("edition");
        String author=request.getParameter("author");
        Float price=Float.parseFloat(request.getParameter("price"));
        System.out.println(name+" "+edition+" "+author+" "+price);
        String sql = "INSERT INTO newbook (name, edition, author, price) VALUES (?, ?, ?, ?)";

        try (
            Connection con=DBConnection.getDBConnection();
            PreparedStatement ps= con.prepareStatement(sql);)
        {
            ps.setString(1, name);
            ps.setString(2, edition);
            ps.setString(3,author);
            ps.setFloat(4, price);


            int count = ps.executeUpdate();

            if (count > 0) {
                pw.println("BOOK ADDED SUCCESSFULLY");
                pw.println("<a href='index.html'>home</a>");
                pw.println("<a href='booklist'>booklist</a>");
            } else {
                pw.println("FAILED TO ADD THE BOOK");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}