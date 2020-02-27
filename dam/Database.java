package main.java.dam;

//import sql libraries
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database{
    //attributes (members)
    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String connectURL = "jdbc:sqlserver://";
    private String host;
    private String instance = "MSSQLSERVER";
    private int port;
    private Boolean integrated;
    private String dbName;
    private String dbUser;
    private String dbPassword;

    /**
     * Constructor for the Database class
     * 
     * @param host Host of Database
     * @param instance Database instance name
     * @param port Port Number
     * @param integrated Integrated Security = true or false
     * @param dbName Database name
     * @param dbUser Username
     * @param dbPassword Password
     */
    public Database(
        String host,
        String instance,
        int port,
        Boolean integrated,
        String dbName,
        String dbUser,
        String dbPassword
    ){
        // set the required attributes
        this.host = host;
        this.instance = instance;
        this.port = port;
        this.integrated = integrated;
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;

        //generate the connection url for this database connection
        this.connectURL = generateConnectURL();
    }

    //operations (member functions)
    public Connection connection(){
        try{
            Class.forName(this.driver);
            return DriverManager.getConnection(this.connectURL, this.dbUser, this.dbPassword);
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }


    public String getConnectionURL(){
        return connectURL;
    }

    private String generateConnectURL(){
        //init string builder
        StringBuilder sb = new StringBuilder();
        sb.append(this.connectURL);

        //append connection properties to connection URL
        //jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
        sb.append(host);
        
        //if we're not using a default instance, append it
        if(!this.instance.equals("MSSQLSERVER")){
            sb.append("\\");
            sb.append(this.instance);
        }

        //if we're not using the default port, append it
        if(port != 1433){
            sb.append(":");
            sb.append(this.port);
        }

        sb.append(";databaseName=");
        sb.append(this.dbName);

        sb.append(";integreatedSecurity=");
        sb.append(this.integrated);

        return sb.toString();
    }
}