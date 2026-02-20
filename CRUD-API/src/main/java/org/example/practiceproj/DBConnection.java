package org.example.practiceproj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url="jdbc:mysql://localhost:3306/bookcenter";
    private static final String username="root123";
    private static final String password="password123";

    public static Connection getDBConnection(){


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(url,username,password);
            return con;

        }catch (ClassNotFoundException e){
            System.out.println("Found error1");
            e.printStackTrace();
        }catch (SQLException e){
            System.out.println("FOund error 2");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            DBConnection.getDBConnection();
            System.out.println("Connection established");
        } catch (Exception e){
            System.out.println("Error"+ e.toString());
        }
    }
}
