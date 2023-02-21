<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">




<title> h</title>
</head>

<body>
	<header id="h1">



		<li>
			<%
			boolean loged = false;

			if (session.getAttribute("idUser") != null && session.getAttribute("idUser") != "") {
				if (session.getAttribute("LogedInUserType").equals("1")) {
					
					%>
					
					<nav class="navbar navbar-expand-lg navbar-light bg-light">
					  <a class="navbar-brand" href="recursos.jsp">HOME</a>
					  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
					    <span class="navbar-toggler-icon"></span>
					  </button>
					  <div class="collapse navbar-collapse" id="navbarNav">
					    <ul class="navbar-nav">
					      <li class="nav-item active">
					        <a class="nav-link" href="perfil.jsp">PROFILE <span class="sr-only">(current)</span></a>
					      </li>
					      <li class="nav-item">
					        <a class="nav-link" href="search2.jsp">SEARCH</a>
					      </li>
					      <li class="nav-item">
					        <a class="nav-link" href="logout.jsp">LOGOUT</a>
					      </li>
					    </ul>
					  </div>
					</nav>
			<% 
 }

				if (session.getAttribute("LogedInUserType").equals("2")) {
 %>
		
				<nav class="navbar navbar-expand-lg navbar-light bg-light">
					  <a class="navbar-brand" href="recursos.jsp">HOME</a>
					  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
					    <span class="navbar-toggler-icon"></span>
					  </button>
					  <div class="collapse navbar-collapse" id="navbarNav">
					    <ul class="navbar-nav">
					     <li class="nav-item active">
					        <a class="nav-link" href="adicionarRecurso.jsp">ADD RESOURCES <span class="sr-only">(current)</span></a>
					      </li>
					         <li class="nav-item active">
					        <a class="nav-link" href="removerRecurso.jsp">REMOVE RESOURCES <span class="sr-only">(current)</span></a>
					       <li class="nav-item active">
					        <a class="nav-link" href="perfil.jsp">PROFILE <span class="sr-only">(current)</span></a>
					      </li>
					      <li class="nav-item">
					        <a class="nav-link" href="search2.jsp">SEARCH</a>
					      </li>
					      <li class="nav-item">
					        <a class="nav-link" href="logout.jsp">LOGOUT</a>
					      </li>
					    </ul>
					  </div>
					</nav>
			
			 <%
 }
				if (session.getAttribute("LogedInUserType").equals("3")) {
					
					%>
					<nav class="navbar navbar-expand-lg navbar-light bg-light">
					  <a class="navbar-brand" href="recursos.jsp">HOME</a>
					  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
					    <span class="navbar-toggler-icon"></span>
					  </button>
					  <div class="collapse navbar-collapse" id="navbarNav">
					    <ul class="navbar-nav">
					     <li class="nav-item active">
					        <a class="nav-link" href="adicionarRecurso.jsp">ADD RESOURCES <span class="sr-only">(current)</span></a>
					      </li>
					         <li class="nav-item active">
					        <a class="nav-link" href="removerRecurso.jsp">REMOVE RESOURCES <span class="sr-only">(current)</span></a>
					       <li class="nav-item active">
					        <a class="nav-link" href="perfil.jsp">PROFILE <span class="sr-only">(current)</span></a>
					      </li>
					      <li class="nav-item active">
					        <a class="nav-link" href="users.jsp">USERS <span class="sr-only">(current)</span></a>
					      </li>
					       <li class="nav-item active">
					        <a class="nav-link" href="artistas.jsp">ARTISTS <span class="sr-only">(current)</span></a>
					      </li>
					      <li class="nav-item">
					        <a class="nav-link" href="search2.jsp">SEARCH</a>
					      </li>
					      <li class="nav-item">
					        <a class="nav-link" href="recursosEstado.jsp">PENDING UNLOCK</a>
					      </li>
					      <li class="nav-item">
					        <a class="nav-link" href="logout.jsp">LOGOUT</a>
					      </li>
					    </ul>
					  </div>
					</nav>
					<%
				}
 }

 else {
 %>
 
 	<nav class="navbar navbar-expand-lg navbar-light bg-light">
					  <a class="navbar-brand" href="recursos.jsp">HOME</a>
					  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
					    <span class="navbar-toggler-icon"></span>
					  </button>
					  <div class="collapse navbar-collapse" id="navbarNav">
					    <ul class="navbar-nav">
					      <li class="nav-item active">
					        <a class="nav-link" href="login.jsp">LOGIN <span class="sr-only">(current)</span></a>
					      </li>
					    </ul>
					  </div>
					</nav>
		 <%
 }
 %>



		</li>


	</header>
</body>
</html>