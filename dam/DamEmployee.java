package main.java.dam;

//import sql libraries and package classes
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import main.java.classes.*;

public class DamEmployee{
    private Database db;

    public DamEmployee(Database db){
        this.db = db;
    }

    /**
     * Returns the first and last name of an employee based
     * on their BusinessEntityID.
     * 
     * @param value BusinessEntityID
     * @return FirstName and LastName
     */
    public Person selectNameByID(int value){
        String sql = "SELECT * "
                        +" FROM "
                            + "Person.Person"
                        + " WHERE "
                            + "BusinessEntityID="
                            + value;
        try(Connection connection = db.connection(); 
        Statement st = db.connection().createStatement();
        ){
            //execute my sql
            ResultSet rs = st.executeQuery(sql);

            //create Person
            Person p = new Person();

            while(rs.next()){
                p.setFirstName(rs.getString("firstName"));
                p.setLastName(rs.getString("lastName"));
            }

            return p;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns the job title and hire date of an employee
     * based on their BusinessEntityID.
     * 
     * @param value BusinessEntityID
     * @return JobTitle and HireDate
     */
    public Employee selectJobInfoByID(int value){
        String sql = "SELECT * "
                        +" FROM "
                            + "HumanResources.Employee"
                        + " WHERE "
                            + "BusinessEntityID="
                            + value;
        try(Connection connection = db.connection(); 
        Statement st = db.connection().createStatement();
        ){
            //execute my sql
            ResultSet rs = st.executeQuery(sql);

            //create Employee
            Employee e = new Employee();

            while(rs.next()){
                e.setJobTitle(rs.getString("JobTitle"));
                e.setHireDate(rs.getDate("HireDate"));
            }

            return e;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}