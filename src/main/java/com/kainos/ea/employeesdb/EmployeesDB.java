package com.kainos.ea.employeesdb;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.kainos.ea.Employee_stuff.Employee;
import com.kainos.ea.Employee_stuff.Project;
import com.kainos.ea.Employee_stuff.SalesEmployee;

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
                                Salary
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

    public static void insertEmployees(List<Employee> emps) {
        if (c == null) {
            c = getConnection();
        }

        try {
            Statement s = c.createStatement();
            emps.forEach(emp -> {
                try {
                    s.executeUpdate(
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
                    s.executeUpdate(
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

    public static void insertSalesEmployees(List<SalesEmployee> emps) {
        if (c == null) {
            c = getConnection();
        }

        try {
            Statement s = c.createStatement();
            emps.forEach(emp -> {
                try {
                    s.executeUpdate(
                            String.format("INSERT INTO Sales(EmployeeID, NumberOfSales, CommissionRate, TotalSalesBiAnnually)" +
                                            "VALUES(%d, %d, %2f, %2f)", emp.getId(), emp.getNumOfSales(),
                                    emp.getCommissionRate(), emp.getTotalSales()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertSalesEmployee(SalesEmployee emp) {
        if (c == null) {
            c = getConnection();
        }

        try {
            Statement s = c.createStatement();
            try {
                s.executeUpdate(
                        String.format("INSERT INTO Sales(EmployeeID, NumberOfSales, CommisionRate, TotalSalesBiAnnually)" +
                                        "VALUES(%d, %d, %2f, %2f)", emp.getId(), emp.getNumOfSales(),
                                emp.getCommissionRate(), emp.getTotalSales()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getReport() {
        if (c == null) {
            c = getConnection();
        }
        try {
            Statement s = c.createStatement();
            ResultSet rows = s.executeQuery("SELECT EmployeeID, Name, Department, FROM Employee ORDER BY Department");
            while (rows.next()) {
                System.out.println("ID: " + rows.getShort("EmployeeID"));
                System.out.println("Name: " + rows.getString("Name"));
                System.out.println("Department: " + rows.getShort("Department"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Project> getProjectsWithoutEmployees() {
        if (c == null) {
            c = getConnection();
        }
        List<Project> projects = new ArrayList<>();
        try {
            Statement s = c.createStatement();
            try {
                ResultSet rows =  s.executeQuery("SELECT ProjectName, ProjectID, Status, ProjectType, count(Name) as Devs " +
                        "FROM Employee e inner join Project_Employee pe Using(EmployeeID) " +
                        "right outer join Project p Using(ProjectID) group by ProjectName having Devs = 0;");
                while (rows.next()) {
                    projects.add(new Project(
                            rows.getShort("ProjectID"),
                            rows.getString("ProjectName"),
                            rows.getString("Status"),
                            rows.getString("ProjectType")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects;
    }
}

