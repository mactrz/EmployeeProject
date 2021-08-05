package com.kainos.ea.Employee_stuff;

public class Project {
    private short ProjectID;
    private String ProjectName;
    private String ProjectType;
    private String Status;
    public Project(short ProjectID, String ProjectName, String Status, String ProjectType) {
        this.ProjectID = ProjectID;
        this.ProjectName = ProjectName;
        this.Status = Status;
        this.ProjectType = ProjectType;
    }
}
