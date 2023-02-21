<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
	//String id = request.getParameter("userid");
	String driver = "com.mysql.cj.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://localhost:3306/";
	String database = "sbd1";
	String userid = "root";
	String password = "dimaria";
	try {
		Class.forName(driver);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/sbd1?useTimezone=true&serverTimezone=UTC", userid, password);
			statement = connection.createStatement();
			String sql = "select * from user";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
	%>
	
	<h4><%=resultSet.getString("name")%></h4>
	
	
	<%
		}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>



</body>
</html>