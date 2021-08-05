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
        try (var f = new FileInputStream("src/main/java/com/kainos/ea/employeesdb/properties")) {
            Properties props = new Properties();
            props.load(f);
            user = props.getProperty("user");
            password = props.getProperty("password");
            host = props.getProperty("host");
            if (user == null || password == null || host == null)
                throw new IllegalArgumentException(
                        "Properties file must exist and must contain user, " +
                                "password, and host properties.");
            c = DriverManager.getConnection("jdbc:mysql://" + host +
                    "/employees", user, password);
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
                            SELECT emp_no / 10e3 AS `number`,
                                CONCAT_WS(' ', first_name, last_name) AS `name`,
                                salary * 100 AS `salary`
                            FROM employees JOIN salaries USING(emp_no)
                            WHERE to_date > NOW()
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

    public static void insertEmployees(List<Employee> emps) {
        if (c == null) {
            c = getConnection();
        }

        try {
            Statement s = c.createStatement();
            emps.forEach(emp -> {
                try {
                    s.executeQuery(
                            String.format("INSERT INTO Employee(EmployeeID, Name, Address, NIN, Salary, Department, IsDepartmentManager)" +
                                    "VALUES(%d, '%s', '%s', '%s', %2f, '%s', %b)", emp.getId(), emp.getName(),
                                    emp.getAddress(), emp.getNationalInsurance(), emp.getSalary(), emp.getDepartment(), emp.isDeptManager()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertEmployee(Employee emp) {
        if (c == null) {
            c = getConnection();
        }

        try {
            Statement s = c.createStatement();
                try {
                    s.executeQuery(
                            String.format("INSERT INTO Employee(EmployeeID, Name, Address, NIN, Salary, Department, IsDepartmentManager)" +
                                    "VALUES(%d, '%s', '%s', '%s', %2f, '%s', %b)", emp.getId(), emp.getName(),
                                    emp.getAddress(), emp.getNationalInsurance(), emp.getSalary(), emp.getDepartment(), emp.isDeptManager()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

