package com.kainos.ea.Employee_stuff;

public class Developer extends Employee {

    private String CV;
    private String pic;

    public String getCV() {
        return CV;
    }

    public void setCV(String CV) {
        this.CV = CV;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Developer(short id, String name, String address, String nationalInsurance, String bankDetails, float salary, Department department, boolean isDeptManager, String CV, String pic) {
        super(id, name, address, nationalInsurance, bankDetails, salary, department, isDeptManager);
        this.CV = CV;
        this.pic = pic;
    }
}
