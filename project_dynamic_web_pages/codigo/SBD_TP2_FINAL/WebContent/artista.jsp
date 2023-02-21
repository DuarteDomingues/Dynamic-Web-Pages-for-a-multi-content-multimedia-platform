<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="database.DatabaseConnector"%>


<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page import="database.DatabaseListings"%>
<%@ page import="database.ResourcesByPerson"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import=" java.util.Calendar"%>
<%@ page import="java.util.Arrays"%>



<!DOCTYPE html>
<html>
<head>
<title>ARTISTA</title>
</head>
<body>
	<header>
		<%@ include file="header.jsp"%>
	</header>
	<section class="mainBody">
	<br>
	<%
	String idArtista = (String) request.getAttribute("idArtista");
	request.removeAttribute("idArtista");

	String idUser = "";
	if (session.getAttribute("idUser") != null && session.getAttribute("idUser") != "") {
		idUser = (String) session.getAttribute("idUser");

		DatabaseConnector db = new DatabaseConnector();
		
		//GET NOME DO ARTISTA
		String artistaName = db.getTituloArtista(idArtista);
		
		
		//GET RECURSOS DO ARTISTA
		
		ResourcesByPerson rb = new ResourcesByPerson();
		
		//getRecursosRealizador
		ArrayList<Integer> idsFilmeRealizador = rb.getRecursosByArtista("filme", "idRealizador", idArtista,"idRecurso");
		
		%>
		
		
		

		<H2>ARTIST</H2>
		<h3><%=artistaName%></h3>


		<%
		
		if (idsFilmeRealizador!= null){
			for ( int i =0;i<idsFilmeRealizador.size();i++){
					int idFilme = idsFilmeRealizador.get(i);
					String tituloFilme = db.getTituloRecurso(idFilme);
				%>
						<h3><span style="color:blue;font-weight:bold">Director in movie: </span> <%=tituloFilme%></h3>
						
					<%
					
			}
			
		}
		
		//getRecursosAtores
		ArrayList<Integer> idsFilmeAtores = rb.getRecursosByArtista("ator_atua_filme", "idAtor", idArtista,"idFilme");
		
		if (idsFilmeAtores!= null){
			for ( int i =0;i<idsFilmeAtores.size();i++){
					int idFilme = idsFilmeAtores.get(i);
					String tituloFilme = db.getTituloRecurso(idFilme);
				%>
						<h3><span style="color:blue;font-weight:bold">Actor in movie: </span> <%=tituloFilme%></h3>
		
					<%
					
			}
			
		}
		
		
		//getRecursos poema
		ArrayList<Integer> idspoeta_atua_poesia = rb.getRecursosByArtista("poeta_atua_poesia", "idPoeta", idArtista,"idPoema");
		
		if (idspoeta_atua_poesia!= null){
			for ( int i =0;i<idspoeta_atua_poesia.size();i++){
					int idFilme = idspoeta_atua_poesia.get(i);
					String tituloFilme = db.getTituloRecurso(idFilme);
				%>
				<h3><span style="color:blue;font-weight:bold">Poet in poem: </span> <%=tituloFilme%></h3>
	
						
					<%
					
			}
			
		}
		//FOTOGRAFIA
		
		ArrayList<Integer> idsfotografia = rb.getRecursosByArtista("fotografia", "idFotografo", idArtista,"idRecurso");
		
		if (idsfotografia!= null){
			for ( int i =0;i<idsfotografia.size();i++){
					int idFilme = idsfotografia.get(i);
					String tituloFilme = db.getTituloRecurso(idFilme);
				%>
						<h3><span style="color:blue;font-weight:bold">Photographer in photo: </span> <%=tituloFilme%></h3>
			
					<%
					
			}
			
		}
		
	//musica
		
		ArrayList<Integer> idsmusica = rb.getRecursosByArtista("musica", "idAutor", idArtista,"idRecurso");
		
		if (idsmusica!= null){
			for ( int i =0;i<idsmusica.size();i++){
					int idFilme = idsmusica.get(i);
					String tituloFilme = db.getTituloRecurso(idFilme);
				%>
				
				<h3><span style="color:blue;font-weight:bold">Music autor in: </span> <%=tituloFilme%></h3>
				
					<%
					
			}
			
		}

		ArrayList<Integer> idsmusicaLetra = rb.getRecursosByArtista("musica", "idAutorLetra", idArtista,"idRecurso");
		
		if (idsmusicaLetra!= null){
			for ( int i =0;i<idsmusicaLetra.size();i++){
					int idFilme = idsmusicaLetra.get(i);
					String tituloFilme = db.getTituloRecurso(idFilme);
				%>
						<h3><span style="color:blue;font-weight:bold">Music lyrics author  in: </span> <%=tituloFilme%></h3>
						
					<%
					
			}
			
		}
		
	

	}
	%>



</section>

	<footer>
		<%@ include file="footer.jsp"%>
	</footer>

</body>
</html>