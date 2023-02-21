
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="database.DatabaseListings"%>
<%@ page import="database.DatabaseConnector"%>
<%@ page import="database.ContentForSearch"%>
<%@ page import="servlet.GetBlobIlu"%>
<%@ page import="database.YearHandler"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map"%>
<%@ page import=" java.util.Calendar"%>

<!DOCTYPE html>
<html>
<head>
<title>Search</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
<style>

	body{
		background-image: url("paper.gif");
	
	}

</style>
</head>
<body>

	<header>
		<%@include file="header.jsp"%>
	</header>
	<section class="mainBody">
	
		<H2>LATEST CONTENT</H2>
		<br>

		<%
		DatabaseConnector dc = new DatabaseConnector();
			
			if (session.getAttribute("idUser") != null && session.getAttribute("idUser") != "") {
				String userid = (String) session.getAttribute("idUser");
				if (dc.getBlockedStateUser(userid)!= true ){
					
				
				String idUser = (String) session.getAttribute("idUser");

				DatabaseListings dl = new DatabaseListings();
				ContentForSearch cs = new ContentForSearch();
				//dicionario organizado RecursoId,Media ClassificacaoVal
				HashMap<Integer, Integer> dic = dl.getDicionarioRecursoIdClassificacao();
				YearHandler yh = new YearHandler();
				Iterator hmIterator = dic.entrySet().iterator();
			%>
			
			
		<%
			while (hmIterator.hasNext()) {
				Map.Entry mapElement = (Map.Entry) hmIterator.next();
				
				//buscar o id do recurso
				int i = (int) mapElement.getKey();
				
				String recursoId = String.valueOf(i);
				
				
				
				
				//GetBlobIlu blob= new GetBlobIlu();
				//blob.setIdRecurso(recId);
			 
				
				
				String ageTipo = cs.getAgeTipoRecurso(i);
				java.sql.Date dbSqlDate = yh.getDaysUser(idUser);
				String userDays = String.valueOf(dbSqlDate);
				long days = yh.yearsToDays(userDays);
				

				if (yh.canAccesContent(days, ageTipo)) {

				String titulo = cs.getTitulosByRecursoId(i);
				ArrayList<String> arr = dl.getRecursoAttributes(recursoId);
				
			%>

		
        <div class="col col-md-2">
        	  
		<div class="card">
			<img class="card-img-top" src="./BlobIlu?recursoId=<%=recursoId %>" Width="350px" Height="280px" alt="Card image cap">
			<div class="card-body" style="width: 18rem " style="height:18rem" >
			
				<h3 class="card-title"><%=titulo%></h3>
				<p class="card-text"></p>

			</div>
			<ul class="list-group list-group-flush">
				<%
					if (arr != null) {
						if (arr.get(0).equals("flm")) {
					%>
				<li class="list-group-item"><span
					style="color: blue; font-weight: bold">Type: </span>Movie</li>
				<%
					}

					if (arr.get(0).equals("msc")) {
					%>
				<li class="list-group-item"><span
					style="color: blue; font-weight: bold">Type: </span>Music</li>
				<%
					}

					if (arr.get(0).equals("ftg")) {
					%>
				<li class="list-group-item"><span
					style="color: blue; font-weight: bold">Type: </span>Photograph</li>
				<%
					}

					if (arr.get(0).equals("pma")) {
					%>
				<li class="list-group-item"><span
					style="color: blue; font-weight: bold">Type: </span>Poem</li>
				<%
					}
					%>
				<li class="list-group-item"><span
					style="color: blue; font-weight: bold">Published in: </span><%=arr.get(1)%></li>
				<%
					}
					
					%>

			</ul>

		</div>
		<form action="${pageContext.request.contextPath}/Manage_search_button"
			method="get">
			<input name="consultar" type="submit" value="Consultar" /> <input
				type="hidden" name="userInput" value="<%=i%>" />

		</form>
		<br>
	
		</div>
		</div>


		<%
			}
		%>
		
		<%
		}
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