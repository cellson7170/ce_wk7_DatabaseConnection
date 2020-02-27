package main.java.classes;

public class Person{
    //attributes (members)
    private String firstName;
    private String lastName;

    //operations(member functions)
    public void setFirstName(String value){
        this.firstName = value;
    }

    public void setLastName(String value){
        this.lastName = value;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }
}