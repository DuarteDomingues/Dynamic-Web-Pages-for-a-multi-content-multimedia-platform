

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="database.DatabaseConnector"%>
<%@ page import="database.DatabaseListings"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map"%>
<%@ page import=" java.util.Calendar"%>
<%@ page import="servlet.GetBlob"%>


<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>




<!DOCTYPE html>
<html>
<head>

<style>
.dropdown-check-list {
	display: inline-block;
}

.dropdown-check-list .anchor {
	position: relative;
	cursor: pointer;
	display: inline-block;
	padding: 5px 50px 5px 10px;
	border: 1px solid #ccc;
}

.dropdown-check-list .anchor:after {
	position: absolute;
	content: "";
	border-left: 2px solid black;
	border-top: 2px solid black;
	padding: 5px;
	right: 10px;
	top: 20%;
	-moz-transform: rotate(-135deg);
	-ms-transform: rotate(-135deg);
	-o-transform: rotate(-135deg);
	-webkit-transform: rotate(-135deg);
	transform: rotate(-135deg);
}

.dropdown-check-list .anchor:active:after {
	right: 8px;
	top: 21%;
}

.dropdown-check-list ul.items {
	padding: 2px;
	display: none;
	margin: 0;
	border: 1px solid #ccc;
	border-top: none;
}

.dropdown-check-list ul.items li {
	list-style: none;
}

.dropdown-check-list.visible .anchor {
	color: #0094ff;
}

.dropdown-check-list.visible .items {
	display: block;
}
</style>

<style>
.checked {
	color: orange;
}
</style>
<!-- Add icon library -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">




<title>photograph</title>
</head>
<body>
	<header>
		<%@ include file="header.jsp"%>
	</header>
<section class="mainBody">
	<%
	
	String recId = (String) request.getAttribute("idRecurso");
	request.removeAttribute("idRecurso");
	GetBlob blob= new GetBlob();
	blob.setIdRecurso(recId);
 
	String idUser = "";
	if (session.getAttribute("idUser") != null && session.getAttribute("idUser") != "") {
		idUser = (String) session.getAttribute("idUser");
		
		String query = "SELECT * from sbd1.recurso_multimedia inner join sbd1.fotografia  on fotografia.idRecurso = recurso_multimedia.idRecurso inner join sbd1.artista on artista.idArtista = fotografia.idFotografo inner join sbd1.user on user.idUser = recurso_multimedia.userCriador where sbd1.recurso_multimedia.idRecurso = "+recId+"";
	
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(query);
		String tituloFoto = null;
		java.sql.Date dbSqlDate = null;
		String resumo ="";
		String ageRating = "";
		String artistName ="";
		String userPublisher = "";
		String photoType = "";
		int idRecurso =0;
		int artistaId =0;
		if (rs != null) {
			try {
		//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
		while (rs.next()) {
			tituloFoto = rs.getString("titulo");
			dbSqlDate = rs.getDate("dataCarregamento");
			resumo = rs.getString("resumo");
			ageRating = rs.getString("idadeTipo");
			artistName = rs.getString("artista_name");
			artistaId = rs.getInt("idArtista");
			userPublisher = rs.getString("name");
			idRecurso = rs.getInt("idRecurso");
			photoType = rs.getString("RecursoTipo");
			
			
			
		}
			} catch (Exception e) {
		e.printStackTrace();
			}
		}
	%>
	<h1> <%=tituloFoto%></h1>
	
	<%
		DatabaseListings dl = new DatabaseListings();
		
		if(dl.getEstadoRecurso(idRecurso)){
			
		 %>
		 
	<img src="./Blob" Width="400px" Height="400px"/>
	<%
		if (session.getAttribute("LogedInUserType").equals("3")) {
			%>
			<br>
			<form action="${pageContext.request.contextPath}/Manage_blockContent"
					method="get">
					<input name="consultar" type="submit" value="Block Content" /> 
					<input type="hidden" name="estadoRecurso" value="false" />
					<input type="hidden" name="idRecurso" value=<%=recId%> />
					<input type="hidden" name="recursoTipo" value=<%=photoType%> />
					<input type="hidden" name="idUser" value=<%=userPublisher%> />
					<input type="hidden" name="reputacao" value="-10" />
				</form>
			<%
		}
	
	}
		else{
			%>
			<img
				src="https://www.ispreview.co.uk/wp-content/gallery/article-illustrations/thumbs/thumbs_blocked_internet_content_website_uk_isp.jpg"
				width="400" height="400"> <br> <br>
				
					<%
			if (session.getAttribute("LogedInUserType").equals("3")) {
			
			%>
			<form action="${pageContext.request.contextPath}/Manage_blockContent"
				method="get">
				<input name="consultar" type="submit" value="Unlock Content" /> 
				<input type="hidden" name="estadoRecurso" value="true" />
				<input type="hidden" name="idRecurso" value=<%=recId%> />
				<input type="hidden" name="recursoTipo" value=<%=photoType%> />
				<input type="hidden" name="idUser" value=<%=userPublisher%> />
				<input type="hidden" name="reputacao" value="10" />
			</form>
			<%
			}
			}
		
			%>


	<h4><span style="color:blue;font-weight:bold">Rated: </span><%=ageRating%></h4>
		<h2>About:</h2>
		<p><%=resumo%></p>
	

	<h2>Photographer</h2>
	<p><%=artistName%></p>
	
	<form action="${pageContext.request.contextPath}/Manage_Artista"
					method="get">
		<input name="consultar" type="submit" value="Consultar" />
		<input	type="hidden" name="idArtista" value=<%=artistaId%> /> 
		<input	type="hidden" name="artistaTag" value="RLZ" /> 

	</form>
	<br>


	<h4><span style="color:blue;font-weight:bold">Published by: </span><%=userPublisher%></h4>

	<h4><span style="color:blue;font-weight:bold">Published in: </span><%=dbSqlDate%></h4>
	
	<h1> Comments</h1>
	<% 
		
 
///////COMMENTS
	
	
		ArrayList<String> comments = dl.getComentariotexts(recId);
		ArrayList<Integer> commentsIds = dl.getComentarioIds(recId);
		ArrayList<String> commentDates = dl.getComentariodates(recId);
		if (commentsIds!=null){
			
		for ( int i =0; i<comments.size();i++){
			
			String comentario = comments.get(i);
			Integer comentarioId = commentsIds.get(i);
			String userComentador = dl.getNameUser(comentarioId);
			String dateCom = commentDates.get(i);

			

			%>
				<h3><span style="color:blue;font-weight:bold">Commented by: </span><%=userComentador%>  <span style="color:blue;font-weight:normal">  <%=dateCom%></span></h3>
				<p><%=comentario%> </p>
			
			<% 	
		}
		}
			
		java.sql.Date currentDates = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		%>

		<form action="${pageContext.request.contextPath}/Manage_comment"
				method="get">
				<textarea id="w3review" name="commentText" rows="4" cols="50"
					placeholder="Comment...">
	  </textarea>
			<input type="hidden" name="userComentador" value=<%=idUser%> /> <input
				type="hidden" name="currentData" value="<%=currentDates%>" /> <input
				type="hidden" name="recursoId" value="<%=recId%>" /> <input
				type="hidden" name="recursoTipo" value="<%=photoType%>" /> <input
				type="hidden" name="recursoTitulo" value="<%=photoType%>" /> <br>
			<br> <input type="submit" value="Comment">
		</form>

	<!--      CLASSIFICACOES -->

		<h1> Ratings</h1>
		<% 
		HashMap<Integer, Integer> dicClass = dl.getClassificacaoDic(recId);
		boolean userClassified = false;
		if (dicClass!=null){
			 Iterator hmIterator = dicClass.entrySet().iterator();
			
				while (hmIterator.hasNext()) {
					Map.Entry mapElement = (Map.Entry) hmIterator.next();
					int i = (int) mapElement.getKey();
					int rating = (int) mapElement.getValue();
					String userComentador = dl.getNameUser(i);
					String userS = String.valueOf(i);
					if (userS.equals(idUser)){
						userClassified = true;
					}
					
					
					if (rating==(4)) {
					%>
					<span class="fa fa-star checked"></span> <span
						class="fa fa-star checked"></span> <span class="fa fa-star checked"></span>
					<span class="fa fa-star checked"></span> <span class="fa fa-star"></span>

					<%
					}

					if (rating==(5)) {
					%>
					<span class="fa fa-star checked"></span> <span
						class="fa fa-star checked"></span> <span class="fa fa-star checked"></span>
					<span class="fa fa-star checked"></span> <span
						class="fa fa-star checked"></span>

					<%
					}
					if (rating==(3)) {
					%>
					<span class="fa fa-star checked"></span> <span
						class="fa fa-star checked"></span> <span class="fa fa-star checked"></span>
					<span class="fa fa-star"></span> <span class="fa fa-star"></span>
						
					<%
					}

					if  (rating==(2)) {
					%>
					
					<span class="fa fa-star checked"></span> <span
						class="fa fa-star checked"></span> <span class="fa fa-star"></span>
					<span class="fa fa-star"></span> <span class="fa fa-star"></span>
							
					<%
					}

					if (rating==(1)) {
					%>
					
					<span class="fa fa-star checked"></span> <span class="fa fa-star"></span>
					<span class="fa fa-star"></span> <span class="fa fa-star"></span> <span
						class="fa fa-star"></span>
							
					<%
					}

					if (rating==(0)) {
					%>
				
					<span class="fa fa-star"></span> <span class="fa fa-star"></span> <span
						class="fa fa-star"></span> <span class="fa fa-star"></span> <span
						class="fa fa-star"></span>
							
					<%
					
					}

					%>
						<h3><span style="color:blue;font-weight:bold">Rated by: </span><%=userComentador%> </h3>
					
					<%
				}
				
			}
	

		if (userClassified == false){
		%>
		<form action="${pageContext.request.contextPath}/Manage_comment"
		method="get">
		<div>
			<input id="class" type="number" name="classification" min="0"
				max="5"> <input type="hidden" name="userComentador"
				value=<%=idUser%> /> <input type="hidden" name="currentData"
				value="<%=currentDates%>" /> <input type="hidden" name="recursoId"
				value="<%=recId%>" /> <input type="hidden"
				name="recursoTitulo" value="<%=photoType%>" /> <input
				type="hidden" name="recursoTipo" value="<%=photoType%>" />

		</div>
		<br>
		<div>
			<input type="submit" value="rate this photo">
		</div>
	</form>
	<%
		}
	
	%>
		
	
		
		<h1>Associations</h1>
		<br>
		<% 
		if (session.getAttribute("LogedInUserType").equals("3")) {

		HashMap<Integer, String> dic = dl.getRecursoNameDic(tituloFoto, recId);

		Iterator hmIterator = dic.entrySet().iterator();
		%>
		<form action="${pageContext.request.contextPath}/Manage_association"
			method="get">
			<div id="list1" class="dropdown-check-list" tabindex="100">
				<span class="anchor">Add a resource to associate</span>
				<ul class="items">
					<input type="hidden" name="titleRecurso" value=<%=tituloFoto%> />
					<input type="hidden" name="idRecurso" value=<%=recId%> />
					<input type="hidden" name="tipoRecurso" value=<%=photoType%> />
					<%
					while (hmIterator.hasNext()) {
						Map.Entry mapElement = (Map.Entry) hmIterator.next();
						int ind = (int) mapElement.getKey();

						String titulo = (String) mapElement.getValue();
					%>

					<li><input type="checkbox" name=<%=ind%> value=<%=ind%> /> <%=titulo%></li>
					<%
					}
					%>
				</ul>
			</div>
			<br> <input name="adicionar" type="submit"
				value="Add Associations" />
		</form>
		<%
		}
		%>
	<br>
		
		<%
		HashMap<String, String> dicAssociacoes = dl.getRecursoTituloAssociacoes(recId);
		Iterator hmAssociacoesIterator = dicAssociacoes.entrySet().iterator();

		while (hmAssociacoesIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) hmAssociacoesIterator.next();
			String indRecurso = (String) mapElement.getKey();

			String titulo = (String) mapElement.getValue();
		%>

		<h3>


			<%=titulo%> </h3>


	<form
			action="${pageContext.request.contextPath}/Manage_search_button"
			method="get">
			<input name="consultar" type="submit" value="Consult" /> <input
				type="hidden" name="userInput" value="<%=indRecurso%>" />

			</form>
		
		<%
		
				}
		}
	%>
	
		
	



<br>


</section>
	<footer>
		<%@ include file="footer.jsp"%>
	</footer>
	
<script>
		var checkList = document.getElementById('list1');
		checkList.getElementsByClassName('anchor')[0].onclick = function(evt) {
			if (checkList.classList.contains('visible'))
				checkList.classList.remove('visible');
			else
				checkList.classList.add('visible');
		}
	</script>
	
</body>
</html>