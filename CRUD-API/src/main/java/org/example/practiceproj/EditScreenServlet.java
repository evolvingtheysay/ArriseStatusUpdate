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

@WebServlet("/edit")
public class EditScreenServlet extends HttpServlet {

    public static final String SELECT_QUERY = "SELECT * FROM newbook WHERE id = ?";
    public static final String UPDATE_QUERY = "UPDATE newbook SET name=?, edition=?, author=?, price=? WHERE id=?";

    // GET - Show edit form with current book data
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));

        try (Connection con = DBConnection.getDBConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_QUERY)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            pw.println("<form action='/edit?id=" + id + "' method='post'>");
            pw.println("<table>");
            pw.println("<tr><td>Book Name</td>");
            pw.println("<td><input type='text' name='name' value='" + rs.getString(2) + "'></td></tr>");
            pw.println("<tr><td>Book Edition</td>");
            pw.println("<td><input type='text' name='edition' value='" + rs.getString(3) + "'></td></tr>");
            pw.println("<tr><td>Book Author</td>");
            pw.println("<td><input type='text' name='author' value='" + rs.getString(4) + "'></td></tr>");
            pw.println("<tr><td>Book Price</td>");
            pw.println("<td><input type='text' name='price' value='" + rs.getString(5) + "'></td></tr>");
            pw.println("<tr>");
            pw.println("<td><input type='submit' value='Edit'></td>");
            pw.println("<td><input type='reset' value='Reset'></td>");
            pw.println("</tr>");
            pw.println("</table>");
            pw.println("</form>");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // POST - Update book (used by HTML form)
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String edition = req.getParameter("edition");
        String author = req.getParameter("author");
        float price = Float.parseFloat(req.getParameter("price"));

        try (Connection con = DBConnection.getDBConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_QUERY)) {

            ps.setString(1, name);
            ps.setString(2, edition);
            ps.setString(3, author);
            ps.setFloat(4, price);
            ps.setInt(5, id);

            int count = ps.executeUpdate();

            if (count > 0) {
                pw.println("Book updated successfully!");
                pw.println("<a href='booklist'>Back to Book List</a>");
            } else {
                pw.println("Failed to update book.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // PUT - Update book (used by Postman PUT)
    public void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doPost(req, res);
    }

    // PATCH - Partial update (used by Postman PATCH)
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws javax.servlet.ServletException, IOException {
        if ("PATCH".equalsIgnoreCase(req.getMethod())) {
            doPost(req, res);
        } else {
            super.service(req, res);
        }
    }
}