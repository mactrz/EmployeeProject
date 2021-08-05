package com.kainos.ea.ui;

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

        try {
            selectedOption = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Bad input!");
            return;
        }

        if (selectedOption == 1) {
            setLoggedIn(false);
            loginProcess();
        } else {
            System.out.println("No option for number " + selectedOption + ", try again!");
            return;
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
