package com.kainos.ea.Employee_stuff;

import com.kainos.ea.Employee_stuff.Department;

public class Employee {
    private short id;
    private String name;
    private String address;
    private String nationalInsurance;
    private String bankDetails;
    private float salary;
    private Department department;
    private boolean isDeptManager = false ;

    public Employee(short id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee(short id, String name, String address, String nationalInsurance, String bankDetails, float salary, Department department) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.nationalInsurance = nationalInsurance;
        this.bankDetails = bankDetails;
        this.salary = salary;
        this.department = department;
    }

    public Employee(short id, String name, String address, String nationalInsurance, String bankDetails, float salary, Department department, boolean isDeptManager) {
        this(id, name, address, nationalInsurance, bankDetails, salary, department);
        this.isDeptManager = isDeptManager;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationalInsurance() {
        return nationalInsurance;
    }

    public void setNationalInsurance(String nationalInsurance) {
        this.nationalInsurance = nationalInsurance;
    }

    public String getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(String bankDetails) {
        this.bankDetails = bankDetails;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean isDeptManager() {
        return isDeptManager;
    }

    public void setDeptManager(boolean deptManager) {
        isDeptManager = deptManager;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nationalInsurance='" + nationalInsurance + '\'' +
                ", bankDetails='" + bankDetails + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                ", isDeptManager=" + isDeptManager +
                '}';
    }
}
