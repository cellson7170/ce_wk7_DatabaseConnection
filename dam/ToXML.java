package main.java.dam;

import java.io.File;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ToXML {
    private  Database db;

    public ToXML(Database db){
        this.db = db;
    }
    public  void toXML() throws Exception {

	
        //Create the DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //Create the DocumentBuilder
        DocumentBuilder builder = factory.newDocumentBuilder();
        //Create the Document
        Document doc = builder.newDocument();

        //Create element 'results' and assign it the value 'persons'
        Element results = doc.createElement("Persons");
        //Append document with 'results'
        doc.appendChild(results);

        //Establish a connection with the database
        Connection con = db.connection();

        ResultSet rs = con.createStatement().executeQuery(
            "SELECT TOP 1000"
                +" P.BusinessEntityID, P.FirstName, P.LastName" 
                +", E.JobTitle, E.HireDate" 
                +", DH.DepartmentID, DH.StartDate, DH.EndDate" 
                +", D.Name, D.GroupName" 
            +" FROM" 
                +" AdventureWorks.Person.Person AS P"
        
            +" JOIN AdventureWorks.HumanResources.Employee AS E"
            +" ON P.BusinessEntityID = E.BusinessEntityID"
            
            +" JOIN AdventureWorks.HumanResources.EmployeeDepartmentHistory AS DH"
            +" ON P.BusinessEntityID = DH.BusinessEntityID"
            
            +" JOIN AdventureWorks.HumanResources.Department AS D"
            +" ON DH.DepartmentID = D.DepartmentID");
        
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();

        //Check for data by moving the cursor to the first record (if there is one)
        while (rs.next()) {
            //For each row of data
            //Create a new element called "row"
            Element row = doc.createElement("Person");

            results.appendChild(row);
                for (int i = 1; i <= colCount; i++) {
                    //For each column index, determine the column name
                    String columnName = rsmd.getColumnName(i);
                    //Get the column value
                    Object value = rs.getObject(i);
                    //Determine if the last column accessed was null
                    if (rs.wasNull()){
                        value = "null";
                    }
                    //Create a new element with the same name as the column
                    Element node = doc.createElement(columnName);
                    //Add the data to the new element
                    node.appendChild(doc.createTextNode(value.toString()));
                    //Add the new element to the row
                    row.appendChild(node);
                }
            }
        //Create new DOMSource
        DOMSource domSource = new DOMSource(doc);
        //Create the TransformerFactory
        TransformerFactory tf = TransformerFactory.newInstance();
        //Create the Transformer
        Transformer transformer = tf.newTransformer();
        //Omit XML Declaration
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        //Set output method to XML
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        //Set encoding
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        //Set indent to 'yes'
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
       // StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(new File("person.xml"));
        //Transform Source to a Result
        transformer.transform(domSource, sr);

        con.close();
        rs.close();
    }  
}
