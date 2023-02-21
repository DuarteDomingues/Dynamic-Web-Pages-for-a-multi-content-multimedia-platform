<%@ page import="database.DatabaseConnector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page import="database.DatabaseConnector"%>

<!DOCTYPE html>
<html>
<head>
<title>Home Page</title>
</head>
<body>
	<header>
		<%@ include file="header.jsp"%>
	</header>
<section class="mainBody">
		<br>
		<% 
		DatabaseConnector dc = new DatabaseConnector();
		
		String idUser = "";
		if (session.getAttribute("idUser")!=null && session.getAttribute("idUser")!=""){
			String userid = (String) session.getAttribute("idUser");
			if (dc.getBlockedStateUser(userid)!= true ){
				
			
			idUser =(String) session.getAttribute("idUser");
			String query = "SELECT name,nacionalidade,dataNascimento,reputacao from sbd1.user where idUser = '"+idUser+"'";
			DatabaseConnector db = new DatabaseConnector();
			ResultSet rs = db.makeStatement(query);
			String name=null;
			String nacionalidade=null;
			java.sql.Date dbSqlDate = null;
			int reputacao= 0;
			
			if (rs!=null) {
				try {
				while (rs.next()) {
					
					name = rs.getString("name");
					nacionalidade = rs.getString("nacionalidade");
					dbSqlDate = rs.getDate("dataNascimento");
					reputacao =rs.getInt("reputacao");
					
					
				}
				}
					catch (Exception e) {
						e.printStackTrace();
					}
				
			}
			%>
			<div class="cardCenterId">
			<div class="card"  style="width: 40rem;">
			<img class="card-img-top" src="..." alt="Card image cap">
			<div class="card-body">
				<h1 class="card-title"><%=name%></h1>
				<p class="card-text"></p>
			</div>
			<ul class="list-group list-group-flush">
				<li class="list-group-item"><span style="color:blue;font-weight:bold">from: </span><%=nacionalidade%></li>
				<li class="list-group-item"><span style="color:blue;font-weight:bold">Birthdate: </span><%=dbSqlDate%></li>
				<li class="list-group-item"><span style="color:blue;font-weight:bold">Reputation: </span><%=reputacao%></li>
			</ul>
			<br>
			<p>
			</div>
			
		</div>
		

<br>
		<% 
			}
		else {
			%>
			<h1>You are blocked.</h1>
			<%
						
	}
		
				
	}
	
		else {
			%>
			<h1>Please login to see content</h1>
			<%
			}
			%>
	


</section>

	<footer>
		<%@ include file="footer.jsp"%>
	</footer>

</body>
</html>