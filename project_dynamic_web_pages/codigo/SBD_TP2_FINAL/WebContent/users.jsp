

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="database.DatabaseConnector"%>
<%@ page import="database.DatabaseListings"%>
<%@ page import="database.UpdateTables"%>
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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<header>
		<%@ include file="header.jsp"%>
	</header>

	<br>

	<section class="mainBody">
		<%
		if (session.getAttribute("idUser") != null && session.getAttribute("idUser") != "") {
			String userCurrent = (String) session.getAttribute("idUser");
			if (session.getAttribute("LogedInUserType").equals("3")) {
		%>

		<h1>Users</h1>
		<%
		}

		DatabaseListings dl = new DatabaseListings();
		HashMap<Integer, Integer> dic = dl.getUserReputacaoDic();
		Iterator hmIterator = dic.entrySet().iterator();
		UpdateTables ut = new UpdateTables();
		while (hmIterator.hasNext()) {
		Map.Entry mapElement = (Map.Entry) hmIterator.next();
		int i = (int) mapElement.getKey();

		int reputacao = (int) mapElement.getValue();
		ArrayList<String> userAttributes = dl.getUserAttributes(i);

		String is = String.valueOf(i);
		int count = ut.CountResourcesUser(is);

		if (userAttributes != null) {
		%>
		
		<div class="card" style="width: 60rem;">
			<img class="card-img-top" src="..." alt="Card image cap">
			<div class="card-body">
				<h5 class="card-title"><%=userAttributes.get(0)%></h5>
				<p class="card-text"></p>
			</div>
			<ul class="list-group list-group-flush">
				<li class="list-group-item"><span
					style="color: blue; font-weight: bold">from: </span><%=userAttributes.get(1)%></li>
				<li class="list-group-item"><span
					style="color: blue; font-weight: bold">Birthdate: </span><%=userAttributes.get(2)%></li>
				<li class="list-group-item"><span
					style="color: blue; font-weight: bold">User Type: </span><%=userAttributes.get(3)%></li>
				<li class="list-group-item"><span
					style="color: blue; font-weight: bold">blocked: </span><%=userAttributes.get(4)%></li>
				<li class="list-group-item"><span
					style="color: blue; font-weight: bold">Reputation: </span><%=reputacao%></li>
				<li class="list-group-item"><span
					style="color: blue; font-weight: bold">Number of published
						resources: </span><%=count%></li>
			</ul>
			<br>
			<p>
		</div>

		</div>

		<%
		if (userAttributes.get(4) == "false") {
		%>

		<form action="${pageContext.request.contextPath}/Manage_block"
			method="get">
			<input name="consultar" type="submit" value="Block" /> <input
				type="hidden" name="val" value="1" />
			<input	type="hidden" name="userId" value="<%=i%>" />
		</form>

		<%
		}
		
		if (userAttributes.get(4) == "true") {
			%>

			<form action="${pageContext.request.contextPath}/Manage_block"
				method="get">
				<input name="consultar" type="submit" value="Unlock" /> <input
					type="hidden" name="val" value="0" />
				<input	type="hidden" name="userId" value="<%=i%>" />
			</form>

			<%
			}
		
		
		
		%>
		
		<form action="${pageContext.request.contextPath}/Manage_see_user"
				method="get">
				<input name="consultar" type="submit" value="See website as user" /> 
				<input	type="hidden" name="userIdcorrente" value="<%=i%>" />
				
				
				
				<input	type="hidden" name="userId" value="<%=i%>" />
			</form>
		<br>

		<%
		}

		}
		}
		%>
		</div>
	</section>
	<footer>
		<%@ include file="footer.jsp"%>
	</footer>
</body>
</html>