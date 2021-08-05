package com.kainos.ea;

import com.kainos.ea.employeesdb.EmployeesDB;

import java.sql.*;

public class Check {
    // academy2020.cpc8rvmbbd9k.eu-west-2.rds.amazonaws.com
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        try {
//            Connection c =
//                    DriverManager.getConnection("jdbc:mysql://academy2020.cpc8rvmbbd9k.eu-west-2.rds.amazonaws.com/world_Maciej",
//                            "Maciej", "tempPwd!");
//            Statement st = c.createStatement();
//            ResultSet rs = st.executeQuery(
//                    "SELECT name, district FROM city ORDER BY RAND() LIMIT 5");
//            while (rs.next()) {
//                String out = String.format("%s is in %s.",
//                        rs.getString("name"), rs.getString("district"));
//                System.out.println(out);
//            }
//        } catch(Exception ex) {
//            throw ex;
//        }


        EmployeesDB db = new EmployeesDB();
//        db.getEmployees();

    }
}
