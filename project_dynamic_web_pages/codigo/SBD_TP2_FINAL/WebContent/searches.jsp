
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="database.ContentForSearch"%>
<%@ page import="database.YearHandler"%>
<%@ page import="database.DatabaseConnector"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Map"%>
<%@ page import=" java.util.Calendar"%>
<%@ page import="database.DatabaseListings"%>

<!DOCTYPE html>
<html>
<head>
<title>Search</title>
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
			if (session.getAttribute("idUser") != null && session.getAttribute("idUser") != "") {
				String userid = (String) session.getAttribute("idUser");
				
				
				if (dc.getBlockedStateUser(userid)!= true ){
					
				idUser = (String) session.getAttribute("idUser");

				String userInput = (String) request.getAttribute("userInput");
				request.removeAttribute("userInput");
				ContentForSearch cs = new ContentForSearch();
				String filmeStr = cs.makeStringFilme(userInput);
				String poemaStr = cs.makeStringPoema(userInput);
				String musicaStr = cs.makeStringMusica(userInput);
				String atorStr = cs.makeStringAtorAtuaFilme(userInput);
				String photoStr = cs.makeStringFoto(userInput);

				HashMap<Integer, String> xdfilme = cs.getRecursoTituloIds(filmeStr);
				HashMap<Integer, String> xdpoema = cs.getRecursoTituloIds(poemaStr);
				HashMap<Integer, String> xdmusica = cs.getRecursoTituloIds(musicaStr);
				HashMap<Integer, String> xdator = cs.getRecursoTituloIds(atorStr);
				HashMap<Integer, String> xddphoto = cs.getRecursoTituloIds(photoStr);

				xdfilme.putAll(xdpoema);
				xdfilme.putAll(xdmusica);
				xdfilme.putAll(xdator);
				xdfilme.putAll(xddphoto);

				HashMap<Integer, String> dic = cs.getTitulosWithKey(userInput);
				dic.putAll(xdfilme);
				YearHandler yh = new YearHandler();
				Iterator hmIterator = dic.entrySet().iterator();
				
				DatabaseListings dl = new DatabaseListings();

				
				while (hmIterator.hasNext()) {
					Map.Entry mapElement = (Map.Entry) hmIterator.next();
					int i = (int) mapElement.getKey();
					
					String recursoId = String.valueOf(i);

					String ageTipo = cs.getAgeTipoRecurso(i);
					java.sql.Date dbSqlDate = yh.getDaysUser(idUser);
					String userDays = String.valueOf(dbSqlDate);
					long days = yh.yearsToDays(userDays);

					if (yh.canAccesContent(days, ageTipo)) {
				String recId = (String) mapElement.getValue();
				
				
				ArrayList<String> arr = dl.getRecursoAttributes(recursoId);
			%>

		
	
		<div class="card" style="width: 60rem;">
			<img class="card-img-top" src="..." alt="Card image cap" >
			<div class="card-body">
					<h5 class="card-title"><%=recId%></h5>
					<p class="card-text"></p>

				</div>
				<ul class="list-group list-group-flush">
				<% 
					if (arr !=null){
						if (arr.get(0).equals("flm")){
							%>
							<li class="list-group-item"><span style="color:blue;font-weight:bold">Type: </span>Movie</li>
							<% 
							
						}
						
						if (arr.get(0).equals("msc")){
							%>
							<li class="list-group-item"><span style="color:blue;font-weight:bold">Type: </span>Music</li>
							<% 
							
						}
						
						if (arr.get(0).equals("ftg")){
							%>
							<li class="list-group-item"><span style="color:blue;font-weight:bold">Type: </span>Photograph</li>
							<% 
							
						}
						
						if (arr.get(0).equals("pma")){
							%>
							<li class="list-group-item"><span style="color:blue;font-weight:bold">Type: </span>Poem</li>
							<% 
							
						}
					
						%>
		   				 <li class="list-group-item"><span style="color:blue;font-weight:bold">Published in: </span><%=arr.get(1)%></li>
						<% 
					}
   				
   				 %>
   				 
 			 </ul>
			</div>
			<form
				action="${pageContext.request.contextPath}/Manage_search_button"
				method="get">
				<input name="consultar" type="submit" value="Consultar" /> <input
					type="hidden" name="userInput" value="<%=i%>" />

			</form>
			<br>
	
		
			<%
			
			//request.getSession().setAttribute("idSearch",xd);
			}

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
			%>

		
	


	</section>

	<footer>
		<%@ include file="footer.jsp"%>
	</footer>

</body>
</html>