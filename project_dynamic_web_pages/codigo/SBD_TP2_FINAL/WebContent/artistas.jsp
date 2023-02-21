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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artists</title>
</head>
<body>
	<header>
		<%@ include file="header.jsp"%>
	</header>
	<section class="mainBody">

		<%
		if (session.getAttribute("idUser") != null && session.getAttribute("idUser") != "") {
			if (session.getAttribute("LogedInUserType").equals("3")) {
		%>
		<h1>Artists</h1>
		<%
		DatabaseListings dl = new DatabaseListings();
		DatabaseConnector db = new DatabaseConnector();
		ArrayList<Integer> arrIds = dl.getArtistasIdList();

		for (int j = 0; j < arrIds.size(); j++) {

			//GET NOME DO ARTISTA
			String idArtista = String.valueOf(arrIds.get(j));
			String artistaName = db.getTituloArtista(idArtista);

			//GET RECURSOS DO ARTISTA

			ResourcesByPerson rb = new ResourcesByPerson();

			//getRecursosRealizador
			ArrayList<Integer> idsFilmeRealizador = rb.getRecursosByArtista("filme", "idRealizador", idArtista, "idRecurso");

			String tituloFilmeRealizador = "";
			String tituloFilme = "";
			String tituloPoema = "";
			String tituloFotografia = "";
			String tituloMusica = "";
			String musicaAutor = "";
			
			%>
			<h1><%=artistaName%></h1>
				<%
			if (idsFilmeRealizador != null) {
				for (int i = 0; i < idsFilmeRealizador.size(); i++) {
						int idFilme = idsFilmeRealizador.get(i);

						tituloFilmeRealizador = db.getTituloRecurso(idFilme);
						
						%>
						<li class="list-group-item"><span style="color:blue;font-weight:bold">Director in: </span><%=tituloFilmeRealizador%></li>
						<% 

				}

			}

			//getRecursosAtores
			ArrayList<Integer> idsFilmeAtores = rb.getRecursosByArtista("ator_atua_filme", "idAtor", idArtista, "idFilme");

			if (idsFilmeAtores != null) {
				for (int i = 0; i < idsFilmeAtores.size(); i++) {
					int idFilme = idsFilmeAtores.get(i);
						tituloFilme = db.getTituloRecurso(idFilme);
						
						
						%>
						<li class="list-group-item"><span style="color:blue;font-weight:bold">Actor in: </span><%=tituloFilme%></li>
						<% 

						
							

				}
			}
			//getRecursos poema
			ArrayList<Integer> idspoeta_atua_poesia = rb.getRecursosByArtista("poeta_atua_poesia", "idPoeta", idArtista,
			"idPoema");

			if (idspoeta_atua_poesia != null) {
				for (int i = 0; i < idspoeta_atua_poesia.size(); i++) {
						int idFilme = idspoeta_atua_poesia.get(i);
							tituloPoema = db.getTituloRecurso(idFilme);
							
							%>
							<li class="list-group-item"><span style="color:blue;font-weight:bold">Poet in: </span><%=tituloPoema%></li>
							<% 


				}

			}
			//FOTOGRAFIA

			ArrayList<Integer> idsfotografia = rb.getRecursosByArtista("fotografia", "idFotografo", idArtista, "idRecurso");

			if (idsfotografia != null) {
				for (int i = 0; i < idsfotografia.size(); i++) {
						int idFilme = idsfotografia.get(i);
							tituloFotografia = db.getTituloRecurso(idFilme);
							
							%>
							<li class="list-group-item"><span style="color:blue;font-weight:bold">Photographer in: </span><%=tituloFotografia%></li>
							<% 


				}

			}
			//musica

			ArrayList<Integer> idsmusica = rb.getRecursosByArtista("musica", "idAutor", idArtista, "idRecurso");

			if (idsmusica != null) {
				for (int i = 0; i < idsmusica.size(); i++) {
							int idFilme = idsmusica.get(i);
							tituloMusica = db.getTituloRecurso(idFilme);
							
							%>
							<li class="list-group-item"><span style="color:blue;font-weight:bold">Musician in: </span><%=tituloMusica%></li>
							<% 


				}

			}
			ArrayList<Integer> idsmusicaLetra = rb.getRecursosByArtista("musica", "idAutorLetra", idArtista, "idRecurso");

			if (idsmusicaLetra != null) {
				for (int i = 0; i < idsmusicaLetra.size(); i++) {
					int idFilme = idsmusicaLetra.get(i);
						musicaAutor = db.getTituloRecurso(idFilme);
						
						%>
						<li class="list-group-item"><span style="color:blue;font-weight:bold">Lyrics author in: </span><%=musicaAutor%></li>
						<% 

				}

			}

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