<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="database.DatabaseConnector"%>


<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<!DOCTYPE html>
<html>
<head>
<title>Recurso</title>
</head>
<body>
	<header>
		<%@ include file="header.jsp"%>
	</header>
	<section class="mainBody">
	<%

String titulo = (String) request.getAttribute("titulo");
//request.removeAttribute("titulo");

String idUser = "";
	if (session.getAttribute("idUser")!=null && session.getAttribute("idUser")!=""){
		idUser =(String) session.getAttribute("idUser");
		String query = "SELECT idRecurso from sbd1.recurso_multimedia where titulo='"+titulo+"'";
			DatabaseConnector db = new DatabaseConnector();
			ResultSet rs = db.makeStatement(query);
			String recursoTipo = null;
			String tituloRecurso = null;
			java.sql.Date dbSqlDate = null;
			String resumo = null;
			
			if (rs!=null) {
				try {
				while (rs.next()) {
					
					recursoTipo = rs.getString("recursoTipo");
					tituloRecurso = rs.getString("titulo");
					dbSqlDate = rs.getDate("dataCarregamento");
					resumo =rs.getString("resumo");
					
					
				}
				}
					catch (Exception e) {
						e.printStackTrace();
					}
			}
				
			

%>
	<h1>Recurso Tipo:</h1>
	<h1><%=recursoTipo%></h1>

	<h1>titulo:</h1>
	<h2><%=titulo%></h2>

	<h1>dbSqlDate</h1>
	<h2><%=dbSqlDate%></h2>

	<h1>resumo:</h1>
	<h2><%=resumo%></h2>

<% 
	}
%>
	<h1>RECURSO</h1>
</section>
	<footer>
		<%@ include file="footer.jsp"%>
	</footer>

</body>
</html>