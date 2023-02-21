<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
</head>
<body>

	<header>
		<%@ include file="header.jsp"%>
	</header>
<section class="mainBody">

	<!--        CHECK IF USER JA TA LOGED IN OU NAO <-->
	 
	<%
	
	if (session.getAttribute("idUser")!=null && session.getAttribute("idUser")!=""){
		%>
			<p> You are already logged in </p>
		<%
		
	}

	else {
		%>

	<div id="loginForm">

		<h2>LOGIN</h2>
		<form action="${pageContext.request.contextPath}/Manage_login"
			method="get">
			<label for="fname">First name:</label><br> <input type="text"
				id="fname" name="username"><br>
			<!--  value="jon" -->
			<label for="Password">Password:</label><br> <input
				type="password" id="lname" name="password"><br> <br>
			<input type="submit" value="LOGIN">
		</form>
	</div>

	<div id="create account">
		<h3>
			Dont have an account ?
			<h3 />
			<a href="create_account.jsp">Create one now</a>
	</div>
	
	</br>
<%
}
	  %>

	<%
			
		
		%>

	<!--        CHECK IF PASSWORD OU USERNAME ERRADOS E TRY AGAIN -->
	<%
		if (session.getAttribute("tryAgain") != null &&  session.getAttribute("tryAgain")!="") {
			String tryAgain = (String) session.getAttribute("tryAgain");
		%>
	<p>${tryAgain}</p>
	<%
		session.removeAttribute("tryAgain");
		}
		%>




</section>
	<footer>
		<%@ include file="footer.jsp"%>
	</footer>

</body>
</html>