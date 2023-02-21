<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="servlet.CriarRecurso"%>
<%@ page import="database.DatabaseConnector"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adicionar Recurso</title>
</head>
<body>
	<header>
		<%@ include file="header.jsp"%>
	</header>
	<section class="mainBody">
	
	
	
	<%
	DatabaseConnector dc = new DatabaseConnector();
		
	String seguir = request.getParameter("seguir");
	


	if (session.getAttribute("idUser") != null && session.getAttribute("idUser") != "") {
		String userid = (String) session.getAttribute("idUser");
		if (dc.getBlockedStateUser(userid)!= true ){
			%>
			
			
			<form method="get">
			
			
			<label for="tipo">Selecione o tipo de recurso:</label>
			<select id="tipo" name="tipo">
		  	<option value="flm">Filme</option>
		  	<option value="msc">Música</option>
		  	<option value="ftg">Fotografia</option>
		  	<option value="pma">Poema</option>
			</select>
			
			
			<h4>Selecione o titulo do recurso:</h4>
			<input type="text" name="titulo" required><br>
			
			<h4>Escreva o resumo do recurso:</h4>
			<textarea name="resumo" rows="20" cols="40"></textarea>
			<br>
			<label for="idade">Selecione o escalão etário:</label>
			<select id="idade" name="idade">
		  	<option value="G">G</option>
		  	<option value="PG13">PG-13</option>
		  	<option value="R">R</option>
		  	<option value="NC17">NC-17</option>
			</select>
			<br>
			
			<h4>Insira a data de lancamento do recurso:</h4>
			<input type="date" name="data" required>
			
			<br>
			<input name="seguir" type="submit" value="Seguir"/>
			
			<% 
			
	if(seguir!=null){
		
		String tipo = request.getParameter("tipo");
		String titulo = request.getParameter("titulo");
		String resumo = request.getParameter("resumo");
		String idade = request.getParameter("idade");
		String data = request.getParameter("data");
		
		request.getSession().setAttribute("tipo", tipo);
		request.getSession().setAttribute("titulo", titulo);
		request.getSession().setAttribute("resumo", resumo);
		request.getSession().setAttribute("idade", idade);
		request.getSession().setAttribute("data", data);
		
		
	
		System.out.println("Carreguei: "+tipo);
		
		
	
	  if (tipo.equals("flm")) {
			response.sendRedirect("criarFilme.jsp");	
			
		}
	  if (tipo.equals("msc")) {
			response.sendRedirect("criarMusica.jsp");	
			
		}
	  if (tipo.equals("ftg")) {
			response.sendRedirect("criarFotografia.jsp");	
			
		}
	  if (tipo.equals("pma")) {
			response.sendRedirect("criarPoema.jsp");
			
		}
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