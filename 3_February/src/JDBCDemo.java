//// Step 1 Import required package
//import java.sql.*;
//// It is not a good practice to import all utilities
//
//public class JDBCDemo{
//    public static void main(String[] args){
//        //Step 2 Load drivers
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            //Step 3 Url Connection
//            String url="jdbc:mysql://localhost:3306/employee_db.employee";
//            String userName ="example_user";
//            String password ="StrongPassword@123";
//            Connection con = DriverManager.getConnection(url,userName,password);
//            //Creating Statement
//            Statement stm=con.createStatement();
//            //Execute Query
//            String query = "INSERT INTO employee_db.employee " + "(Emp_No, Emp_Name, Emp_Add, Emp_Phone, Dept_No, Dept_Name, Salary) " + "VALUES (1, 'Abhinav', 'Jabalpur', '9451050170', '3445', 'Tech', '30000')";
//            stm.executeUpdate(query);
//            // Close Connection
//            con.close();
//            System.out.println("Data Inserted");
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
//}


import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/employee_db";
            String userName = "example_user";
            String password = "StrongPassword@123";

            Connection con = DriverManager.getConnection(url, userName, password);
            Statement stm = con.createStatement();

            String query =
                    "INSERT INTO new" + "workers " + "(emp_no, emp_name, emp_address, emp_phone, dept_no, dept_name, salary) " + "VALUES (1, 'Abhinav', 'Jabalpur', '9451050170', '3445', 'Tech', 30000)";

            stm.executeUpdate(query);
            con.close();

            System.out.println("Data Inserted Successfully");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
