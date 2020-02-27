package main.java.dam;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import main.java.classes.*;

public class DamDepartment{
    private Database db;

	public DamDepartment(Database db) {
        this.db = db;
    }

    /**
     * Returns the department name of a given DepartmentID.
     * @param value DepartmentID
     * @return Department Name
     */
    public Department selectDepartmentNameByID(int value){
        String sql = "SELECT * "
                        +" FROM "
                            + "HumanResources.Department"
                        + " WHERE "
                            + "DepartmentID="
                            + value;
        try(Connection connection = db.connection(); 
        Statement st = db.connection().createStatement();
        ){
            //execute my sql
            ResultSet rs = st.executeQuery(sql);

            //create department
            Department d = new Department();

            //get the name of the department
            while(rs.next()){
                d.setDeptName(rs.getString("Name"));
            }

            return d;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

/**
 * Returns the DepartmentID of the current department that
 * the employee is working in.
 * 
 * @param value BusinessEntityID of Employee
 * @return DepartmentID of current department
 */
    public int selectCurrentDepartmentByBusID(int value){
        Date date;
        String sql = "SELECT * "
                        +" FROM "
                            + "HumanResources.EmployeeDepartmentHistory"
                        + " WHERE "
                            + "BusinessEntityID="
                            + value;
        try(Connection connection = db.connection(); 
        Statement st = db.connection().createStatement();
        ){
            //execute my sql
            ResultSet rs = st.executeQuery(sql);

            //create department
            DepartmentHistory dh = new DepartmentHistory();

            //iterate through the ResultSet
            while(rs.next()){
                //if the value of EndDate is null, setdeptID()
                //to current row's value of DepartmentID
                date = rs.getDate("EndDate");
                if(date == null){
                    dh.setDeptID(rs.getInt("DepartmentID"));;
                }
            }
            //return current value of deptID
            int num = dh.getDeptID();
            return num;
        }
        catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Uses the output of selectCurrentDepartmentByBusID() as the
     * input for selectDepartmentNameByID() and returns the name 
     * of the current department that the employee belongs to.
     * 
     * @param value BusinessEntityID of Employee
     * @return Current Department
     */
    public Department selectCurrentDepartmentName(int value){
        int i = value;
        return selectDepartmentNameByID(selectCurrentDepartmentByBusID(i));
    }
    
}