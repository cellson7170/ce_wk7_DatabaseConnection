// HEADER
// Program Name: Database Connection Application Control 
// Author: Chris Ellson 
// Class: CS262 Winter 19/20
// Date: 2/26/20
// Description: Connect to a company database and retrieve specific information about employees.            

package main.java;

import main.java.dam.*;
import main.java.classes.*;

class Main {

    public static void main(final String[] args) throws Exception {
        
        final Database db = new Database(
            "server.berzirkhosting.com", 
            "CGCC", 
            1444, 
            false, 
            "AdventureWorks", 
            "jdoe",
            "Password1234!");

        System.out.println(db.getConnectionURL());
        db.connection();

        //Assign the BusinessEntityID number of your
        //desired employee to the variable below.
        int businessEntityID = 4;

        final Person p = new DamEmployee(db).selectNameByID(businessEntityID);
        final Employee e = new DamEmployee(db).selectJobInfoByID(businessEntityID);
        final Department d = new DamDepartment(db).selectCurrentDepartmentName(businessEntityID);

        //Print information about the employee to the terminal
        System.out.print("\n\nFull Name: ");
        System.out.println(p.getFullName());
        System.out.print("Job Title: ");
        System.out.println(e.getJobInfo());
        System.out.print("Current Department: ");
        System.out.println(d.getDeptName() + "\n\n");


        //The following lines are to create the XML file,
        //which does not apply to this assignment.

        // ToXML xml = new ToXML(db);
        // xml.toXML();
    }
}

/* FOOTER


Full Name: Rob Walters
Job Title: Senior Tool Designer
Hire Date: 2007-12-05
Current Department: Tool Design

*/