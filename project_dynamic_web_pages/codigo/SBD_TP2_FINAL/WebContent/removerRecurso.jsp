<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="database.DatabaseListings"%>
<%@page import="database.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.LinkedHashMap"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<header>
		<%@ include file="header.jsp"%>
	</header>
	
	<section class="mainBody">



<%
	DatabaseConnector dc = new DatabaseConnector();
	

	if (session.getAttribute("idUser") != null && session.getAttribute("idUser") != "") {
		String userid = (String) session.getAttribute("idUser");
		if (dc.getBlockedStateUser(userid)!= true ){
			%>
				<h3>Resouces added </h3>
	<form method="get">
		<%
			String idUser = (String) session.getAttribute("idUser");

			DatabaseListings dbL = new DatabaseListings();
			LinkedHashMap<String, String> dic = dbL.getRecursoUserId(idUser);

			for (Map.Entry<String, String> mapElement : dic.entrySet()) {
				String key = mapElement.getKey();
				String value = mapElement.getValue();
		%>
		<h4><%=value%></h4>
		<label for=<%=key%>> Delete</label> <input type="checkbox" id=<%=key%>
			name=<%=key%> value=<%=key%>> <br>


		<%
			}
		%>

		<input name="apagar" type="submit" value="Apagar" />

		<%
			String apagar = request.getParameter("apagar");
			if (apagar != null) {

				ArrayList<Integer> recursosApagar = new ArrayList<Integer>();

				for (Map.Entry<String, String> mapElement : dic.entrySet()) {
					String key = mapElement.getKey();
					String value = mapElement.getValue();

					String id = request.getParameter(key);

					if (id != null) {
						recursosApagar.add(Integer.parseInt(id));
					}
				}
				System.out.println(recursosApagar);
				CreateTables cr = new CreateTables();
				

				ArrayList<Integer> recursosFilmes = new ArrayList<Integer>();
				ArrayList<Integer> recursosMusicas = new ArrayList<Integer>();
				ArrayList<Integer> recursosFotos = new ArrayList<Integer>();
				ArrayList<Integer> recursosPoemas = new ArrayList<Integer>();

				for (int r : recursosApagar) {

					if (dbL.getTypeR(r).equals("flm")) {
						recursosFilmes.add(r);
					}
					if (dbL.getTypeR(r).equals("msc")) {
						recursosMusicas.add(r);
					}
					if (dbL.getTypeR(r).equals("ftg")) {
						recursosFotos.add(r);
					}
					if (dbL.getTypeR(r).equals("pma")) {
						recursosPoemas.add(r);
					}

				}

				if (recursosFilmes.size() != 0) {
					cr.removerAtor_atua_filme(recursosFilmes);
					cr.removerFilmes(recursosFilmes);
					
				}
				if (recursosMusicas.size() != 0) {
					cr.removerMusicas(recursosMusicas);
				}
				if (recursosFotos.size() != 0) {
					cr.removerFotografias(recursosFotos);
				}
				if (recursosPoemas.size() != 0) {
					cr.removerPoeta_atua_poesia(recursosPoemas);
					cr.removerPoemas(recursosPoemas);
					
				}
				cr.removerRecursos(recursosApagar);

				
				response.sendRedirect("removerRecurso.jsp");

			}
			
		%>
	</form>
	
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