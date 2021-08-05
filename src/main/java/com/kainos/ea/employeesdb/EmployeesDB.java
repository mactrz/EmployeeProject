package com.kainos.ea.employeesdb;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.kainos.ea.Employee_stuff.Employee;

public class EmployeesDB {
    private static Connection c;
    public static Connection getConnection() {
        String user;
        String password;
        String host;
        Connection c;
        try (var f = new FileInputStream("src/main/java/com/kainos/ea/employeesdb/properties")){
            Properties props = new Properties();
            props.load(f);
            user      = props.getProperty("user");
            password   = props.getProperty("password");
            host      = props.getProperty("host");
            if (user == null || password == null || host == null)
                throw new IllegalArgumentException(
                        "Properties file must exist and must contain user, " +
                                "password, and host properties.");
            c = DriverManager.getConnection("jdbc:mysql://" + host +
                    "/employee_CodePoltergeist", user, password);
            System.out.println("Connected!");
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Employee> getEmployees() {
        if (c == null) {
            c = getConnection();
        }
        List<Employee> emps = new ArrayList<>();
        try {
            Statement s = c.createStatement();
            ResultSet rows = s.executeQuery(
                    """
                        SELECT EmployeeID,
                            Name,
                            Salary * 100 AS `salary`
                        FROM Employee
                        """);
            while (rows.next()) {
                emps.add(new Employee(
                        rows.getShort("EmployeeID"),
                        rows.getString("Name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emps;
    }
}
