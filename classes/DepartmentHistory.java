package main.java.classes;

import java.util.Date;

public class DepartmentHistory{
    
    //attributes
    private int busEntID;
    private int deptID;
    private Date endDate;

    //member functions
    public void setBusEntID(int value){
        this.busEntID = value;
    }

    public void setDeptID(int value){
        this.deptID = value;
    }

    public void setEndDate(Date value){
        this.endDate = value;
    }

    public int getBusEntID(){
        return busEntID;
    }

    public int getDeptID(){
        return deptID;
    }

    public Date getEndDate(){
        return endDate;
    }
}