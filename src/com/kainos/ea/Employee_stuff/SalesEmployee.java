package com.kainos.ea.Employee_stuff;

import com.kainos.ea.Employee_stuff.Department;
import com.kainos.ea.Employee_stuff.Employee;

public class SalesEmployee extends Employee {

    private int numOfSales;
    private float commissionRate;
    private float totalSales;

    public SalesEmployee(short id, String name, String address, String nationalInsurance, String bankDetails, float salary, Department department, boolean isDeptManager, int numOfSales, float commissionRate, float totalSales) {
        super(id, name, address, nationalInsurance, bankDetails, salary, department);
        this.numOfSales = numOfSales;
        this.commissionRate = commissionRate;
        this.totalSales = totalSales;
    }


    public int getNumOfSales() {
        return numOfSales;
    }

    public void setNumOfSales(int numOfSales) {
        this.numOfSales = numOfSales;
    }

    public float getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(float commissionRate) {
        this.commissionRate = commissionRate;
    }

    public float getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(float totalSales) {
        this.totalSales = totalSales;
    }
}
