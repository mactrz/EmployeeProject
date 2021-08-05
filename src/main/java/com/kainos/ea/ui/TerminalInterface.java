package com.kainos.ea.ui;

import com.kainos.ea.Employee_stuff.Department;
import com.kainos.ea.Employee_stuff.Employee;
import com.kainos.ea.Employee_stuff.SalesEmployee;
import com.kainos.ea.employeesdb.EmployeesDB;

import java.util.Scanner;

public class TerminalInterface {

    private boolean loggedIn;
    private int employeeID;
    private String employeePassword;

    public static void main(String[] args) {
        TerminalInterface ui = new TerminalInterface();
        ui.loginProcess();
    }

    public void loginProcess() {
        try {
            requestLogin();
        } catch (NumberFormatException e) {
            System.out.println("EmployeeID MUST BE AN INTEGER.");
            return;
        }

        if (!validateLogin()) {
            System.out.println("Invalid EmployeeID or Password");
            return;
        } else {
            setLoggedIn(true);
            loggedInProcess();
        }
    }

    private void requestLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your EmpployeeID: ");
        setEmployeeID(Integer.parseInt(scanner.nextLine()));
        System.out.println("Enter your Password: ");
        setEmployeePassword(scanner.nextLine());
    }

    //Stub method
    private boolean validateLogin() {
        return true;
    }

    private void loggedInProcess() {
        if (!loggedIn) {
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int selectedOption = 0;

        System.out.println();
        System.out.println("What would you like to do Employee_" + getEmployeeID() +" ?");
        System.out.println("1.Logout");
        System.out.println("2.Get Employees");
        System.out.println("3.Insert Employee");
        System.out.println("4.Insert Sales Employee");

        try {
            selectedOption = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Bad input!");
            loggedInProcess();
        }

        if (selectedOption == 1) {
            setLoggedIn(false);
            loginProcess();
        } else if (selectedOption == 2) {
            System.out.println("Executing Query");
            System.out.println(EmployeesDB.getEmployees());
            loggedInProcess();
        } else if (selectedOption == 3) {
            System.out.println("Enter employee ID: ");
            short id;
            try {
                id = Short.parseShort(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Bad ID");
                loggedInProcess();
                return;
            }

            System.out.println("Enter employee Name: ");
            String name = scanner.nextLine();

            System.out.println("Enter employee Address: ");
            String address = scanner.nextLine();

            System.out.println("Enter employee NINO: ");
            String nino = scanner.nextLine();

            System.out.println("Enter employee Bank Details: ");
            String bank = scanner.nextLine();

            System.out.println("Enter employee Salary: ");
            float salary;
            try {
                salary = Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Bad Salary");
                loggedInProcess();
                return;
            }

            System.out.println("Enter employee Department: ");
            String depart = scanner.nextLine();
            Department department = Department.HR;
            boolean correctDept = false;
            if(depart.equalsIgnoreCase("hr")) {
                department = Department.HR;
                correctDept = true;
            } else if (depart.equalsIgnoreCase("finance")) {
                department = Department.Finance;
                correctDept = true;
            } else if (depart.equalsIgnoreCase("sales")) {
                department = Department.Sales;
                correctDept = true;
            } else if (depart.equalsIgnoreCase("talent")) {
                department = Department.Talent;
                correctDept = true;
            } else if (depart.equalsIgnoreCase("development")) {
                department = Department.Development;
                correctDept = true;
            } else {
                System.out.println("No department: " + depart);
            }

            if (correctDept) {
                System.out.println("Executing Query");
                EmployeesDB.insertEmployee(new Employee(id, name, address, nino, bank, salary, department));
            }

            loggedInProcess();
        } else if (selectedOption == 4) {
            System.out.println("Enter sales employee ID: ");
            short id;
            try {
                id = Short.parseShort(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Bad ID");
                loggedInProcess();
                return;
            }

            System.out.println("Enter sales employee Name: ");
            String name = scanner.nextLine();

            System.out.println("Enter sales employee Address: ");
            String address = scanner.nextLine();

            System.out.println("Enter sales employee NINO: ");
            String nino = scanner.nextLine();

            System.out.println("Enter sales employee Bank Details: ");
            String bank = scanner.nextLine();

            System.out.println("Is manager (y/n): ");
            String ans = scanner.nextLine();
            Boolean isManager = false;
            if (ans.equalsIgnoreCase("y")) {
                isManager = true;
            } else if (ans.equalsIgnoreCase("n")) {
                isManager = false;
            } else {
                System.out.println("Bad Input");
                loggedInProcess();
                return;
            }

            System.out.println("Enter sales employee Salary: ");
            float salary;
            try {
                salary = Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Bad Salary");
                loggedInProcess();
                return;
            }

            System.out.println("Enter sales employee number of sales: ");
            int numOfSales;
            try {
                numOfSales = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Bad Number of Sales");
                loggedInProcess();
                return;
            }

            System.out.println("Enter sales employee number of commission rate: ");
            float commissionRate;
            try {
                commissionRate = Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Bad Commission Rate");
                loggedInProcess();
                return;
            }

            System.out.println("Enter sales employee total sales: ");
            float totalSales;
            try {
                totalSales = Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Bad Total Sales");
                loggedInProcess();
                return;
            }


            Department department = Department.Sales;

            System.out.println("Executing Query");
            EmployeesDB.insertSalesEmployee(new SalesEmployee(id, name, address, nino, bank, salary, department, isManager, numOfSales, commissionRate,totalSales));

            loggedInProcess();
        } else {
            System.out.println("No option for number " + selectedOption + " !");
            loggedInProcess();
        }
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
