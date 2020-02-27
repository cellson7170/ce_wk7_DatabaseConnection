package main.java.classes;

import java.sql.Date;

public class Employee {
    //attributes (members)
    private int busEntID;
    private String jobTitle;
    private Date hireDate;

    //operations (member functions)
    public void setBusEntID(int value){
        busEntID = value;
    }

    public void setJobTitle(String value){
        jobTitle = value;
    }

    public void setHireDate(Date date){
        hireDate = date;
    }

    public int getBusEntID(){
        return busEntID;
    }

    public String getJobTitle(){
        return jobTitle;
    }

    public Date getHireDate(){
        return hireDate;
    }

	public String getJobInfo() {
		return getJobTitle() + "\nHire Date: " + getHireDate();
	}


}