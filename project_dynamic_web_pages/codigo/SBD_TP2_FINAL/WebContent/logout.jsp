<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<!DOCTYPE html>
<html>
<head>
<title>Logout </title>
</head>
<body>
<header>
<%@ include file="header.jsp" %>
</header>
<section class="mainBody">
<div id="logoutForm">

		<h2>LOGOUT</h2>
		<form action="${pageContext.request.contextPath}/Manage_logout"
			method="get">
		
			<input type="submit" value="LOGOUT">
		</form>
	</div>
</section>
<footer>
<%@ include file="footer.jsp" %>
</footer>

</body>
</html>