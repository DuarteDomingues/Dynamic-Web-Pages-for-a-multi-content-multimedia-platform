import java.sql.*;

public class run {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
	
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Connection myConnection =DriverManager.getConnection("jdbc:mysql://localhost/sbd1?useTimezone=true&serverTimezone=UTC","root","passwordPedro");
			Statement stm=myConnection.createStatement();
			ResultSet myRes=stm.executeQuery("select * from user");
		
			while(myRes.next()) {
				System.out.println(myRes.getString("name"));
			}
			
	}
	
}
