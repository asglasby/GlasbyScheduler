/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasbyscheduler.utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author aglasby
 */
public class DbConnection {
    
    private static final String CONNECTION_USERNAME = "U04sat";
    private static final String CONNECTION_PASSWORD = "53688333041";
    private static final String URL = "jdbc:mysql://52.206.157.109/U04sat";
   // private static final String JDBC_Driver = "com.mysql.jdbc.Driver";
    private static final String JDBC_Driver = "com.mysql.jdbc.Driver";
    private static Connection connection;
    
    	public static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
                    try {
                        Class.forName( "com.mysql.jdbc.Driver");
                        // Class.forName( "com.mysql.cj.jdbc.Driver");
                    } catch (ClassNotFoundException e) {
                        System.out.println("Could not register driver!");
                        e.printStackTrace();
                    }
                    connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			System.out.println("Opening new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}
    
}
