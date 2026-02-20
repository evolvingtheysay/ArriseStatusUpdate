package org.example.practiceproj;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    public static final String DELETE_QUERY = "DELETE FROM newbook WHERE id = ?";

    // GET - Delete via browser link click
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));

        try (Connection con = DBConnection.getDBConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_QUERY)) {

            ps.setInt(1, id);
            int count = ps.executeUpdate();

            if (count > 0) {
                pw.println("Book deleted successfully!");
                pw.println("<a href='booklist'>Back to Book List</a>");
            } else {
                pw.println("Book not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE - Delete via Postman DELETE method
    public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req, res);
    }
}