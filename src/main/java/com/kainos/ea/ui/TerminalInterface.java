package com.kainos.ea.ui;

import java.util.Scanner;

public class TerminalInterface {

    private boolean loggedIn;
    private int employeeID;
    private String employeePassword;

    public static void main(String[] args) {
        TerminalInterface ui = new TerminalInterface();
        ui.loginProcess();



        System.out.println(ui.getEmployeeID());
        System.out.println(ui.getEmployeePassword());

    }

    public void loginProcess() {
        while (!isLoggedIn()) {
            try {
                requestLogin();
                setLoggedIn(true);
            } catch (NumberFormatException e) {
                System.out.println("EmployeedID MUST BE AN INTEGER.");
            }
        }
    }

    public void requestLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your EmpployeeID: ");
        setEmployeeID(Integer.parseInt(scanner.nextLine()));
        System.out.println("Enter your Password: ");
        setEmployeePassword(scanner.nextLine());
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
