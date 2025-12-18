package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class EmployeeSalaryViewer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Employee ID:");
        int empId = sc.nextInt();

        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/company",
                "root",
                "root"
            );

            // SELECT Query
            String query = "SELECT name, salary FROM employees WHERE emp_id = ?";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, empId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("Employee Name  : " + rs.getString("name"));
                System.out.println("Employee Salary: " + rs.getDouble("salary"));
            } else {
                System.out.println("Employee record not found");
            }

            rs.close();
            pst.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

