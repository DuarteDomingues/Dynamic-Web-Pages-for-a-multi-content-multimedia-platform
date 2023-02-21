<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="database.DatabaseConnector"%>
<%@ page import="database.ContentForSearch"%>
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


				<%
				DatabaseConnector dc = new DatabaseConnector();
	
				if (session.getAttribute("idUser") != null && session.getAttribute("idUser") != "") {
					String userid = (String) session.getAttribute("idUser");
					if (dc.getBlockedStateUser(userid)!= true ){
						
					%>
						<div class="input-group">
				 		 <div class="form-outline">

						<h2>SEARCH</h2>
						<form  action="${pageContext.request.contextPath}/Smart_search"  method="get">

							<input  placeholder="Search"
								list="CountriesList" name="nameInput" id="country">
							<datalist id="CountriesList">

						<% 
				
				ContentForSearch cs = new ContentForSearch();
				String nome = cs.getNames();
				String titulo = cs.getTitulos();
				String resumo = cs.getResumo();
				
				
				String finals = nome+titulo+resumo;
				finals=finals.replace(".","");
				finals=finals.replace(" ",",");
				
				String[] finalsArray = finals.split(","); 
				
				Set<String> mySet = new HashSet<>(Arrays.asList(finalsArray));
				
				
				
				List<String> finalsArrayList = new ArrayList<String>(mySet);
			
				for(int i=0;i<finalsArrayList.size();i++){
					
					if(finalsArrayList.get(i).length()<4){
						//finalsArrayList.remove(i);
					}
				}  
		           	
				for(int i=0;i<finalsArrayList.size();i++){
					%>
				<option value="<%=finalsArrayList.get(i)%>"></option>
				<% 
				}
				
				%>
				
				</datalist>
			 <button  type="submit">Search </button>
			<!-- <input type="submit" value="SEARCH">  -->	

			</form>
		</div>
	</div>

	<% 
	
	
				}
					else {
						%>
						<h1>You are blocked.</h1>
						<%
										
					}
				
				}
				
				
				

%>


</section>

	<footer>
		<%@ include file="footer.jsp"%>
	</footer>

</body>
</html>