package database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class ConnectionConfigurate {
	
	private static String database = "sbd1";
	private static String usr = "root";
	private static String pwd = "dimaria";
	private static String serverName = "localhost";
	private static String drv = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://"+serverName+"/"+database+"?useLegacyDatetimeCode=false&serverTimezone=Europe/Lisbon";

	
	 public static Connection getConnection() {
	   Connection connection = null;
	

       try {
    	   Class.forName(drv);
           connection = DriverManager.getConnection(url, usr, pwd);
        
       } catch (Exception e) {
           e.printStackTrace();
       }

       return connection;
   }

		
	
}
