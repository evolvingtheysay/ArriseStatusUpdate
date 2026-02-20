package org.example.practiceproj;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/booklist")
public class BookListServlet extends HttpServlet {

    private static final String QUERY = "SELECT * FROM newbook";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        try (
                Connection con = DBConnection.getDBConnection();
                PreparedStatement ps = con.prepareStatement(QUERY)
        ) {

            ResultSet rs = ps.executeQuery();

            pw.println("<h2 align='center'>Book List</h2>");
            pw.println("<table border='1' align='center'>");

            pw.println("<tr>");
            pw.println("<th>ID</th>");
            pw.println("<th>Book Name</th>");
            pw.println("<th>Book Edition</th>");
            pw.println("<th>Book Author</th>");
            pw.println("<th>Book Price</th>");
            pw.println("<th>Edit</th>");
            pw.println("<th>Delete</th>");
            pw.println("</tr>");

            while (rs.next()) {
                int id = rs.getInt(1);

                pw.println("<tr>");
                pw.println("<td>" + id + "</td>");
                pw.println("<td>" + rs.getString(2) + "</td>");
                pw.println("<td>" + rs.getString(3) + "</td>");
                pw.println("<td>" + rs.getString(4) + "</td>");
                pw.println("<td>" + rs.getFloat(5) + "</td>");

                pw.println("<td><a href='edit?id=" + id + "'>Edit</a></td>");

                pw.println("<td><a href='delete?id=" + id + "'>Delete</a></td>");

                pw.println("</tr>");
            }

            pw.println("</table>");

        } catch (SQLException e) {
            e.printStackTrace();
            pw.println("<h3>Error fetching books</h3>");
        }
    }
}
