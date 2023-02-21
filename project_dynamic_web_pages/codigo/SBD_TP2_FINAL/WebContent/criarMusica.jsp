<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="database.CreateTables"%>
<%@ page import="java.sql.Blob"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page import="java.util.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page import="database.DatabaseListings"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Criar Música</title>
</head>
<body>

	<header>
		<%@ include file="header.jsp"%>
	</header>
	
	<%
	int idUser = Integer.parseInt((String) session.getAttribute("idUser"));
	String tipo = (String) request.getSession().getAttribute("tipo");
	String titulo = (String) request.getSession().getAttribute("titulo");
	String resumo = (String) request.getSession().getAttribute("resumo");
	String idade = (String) request.getSession().getAttribute("idade");
	
	DatabaseListings dbL = new DatabaseListings();
	
	
	 %>
	 <form method="get">
	 <h3>Ilustração:</h3>
	 
	 <input type="file"
       id="ilustracao" name="ilustracao"
       accept="image/png, image/jpeg, image/jpg, image/jfif, image/*" >
       <br>
       
      <h3>Música a inserir</h3>
      <input type="file"
       id="musica" name="musica"
       accept="audio/*" >
       
       <br>
       <br>
       <br>
       
     <label for="item">Escolha o autor da música:</label> 
		<select id="item" name="item" size="3">
			<%
       
       LinkedHashMap<String,String> dicMusica=dbL.getArtistasMusicasNames();
			
			 
  	 for (Map.Entry<String, String> mapElement : dicMusica.entrySet()) { 
  		 String keyR = (String) mapElement.getKey(); 
  		 String value = (String) mapElement.getValue(); 
  		
  		%>
			<option value=<%=keyR %>><%=value %></option>

			<% 
  	 }
       
       %>
       </select>
       
       <br>
       <br>
       <br>
       
        <label for="letra">Escolha o autor da letra:</label> 
		<select id="letra" name="letra" size="3">
			<%
       
       LinkedHashMap<String,String> dicLetra=dbL.getArtistasLetrasMusicasNames();
			
			 
  	 for (Map.Entry<String, String> mapElement : dicLetra.entrySet()) { 
  		 String keyR = mapElement.getKey(); 
  		 String value = mapElement.getValue(); 
  		
  		%>
			<option value=<%=keyR %>><%=value %></option>

			<% 
  	 }
       
       %>
       </select>
       
       <br>
       <br>
       <br>
       <input name="adicionar" type="submit" value="Adicionar Recurso"/>
       
       <%
       String adicionar = request.getParameter("adicionar");
       String musica = request.getParameter("item");
       String letra = request.getParameter("letra");
       
       if(adicionar!=null){
    	   
    	   String ilustracao1 = request.getParameter("ilustracao");
    	   String ilustracao2 = "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/"+ilustracao1;
    	   
    	   String conteudo1 = request.getParameter("musica");
    	   String conteudo2 = "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/cont/"+conteudo1;
    	   
    	   Date date = new Date();  
    	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
    	   String strDate= formatter.format(date); 
    	     
    	   
    	  
           System.out.println("musica: "+musica);
           System.out.println("letra: "+letra);
    	   
    	   
 
    	   
    	   CreateTables ct = new CreateTables();
    	   ct.createRecurso2(tipo, titulo, ilustracao2, conteudo2, strDate, resumo, idUser, idade,false);
    	   int recurso = dbL.getLastRecurso();
    	   
    	   ct.createMusica(Integer.parseInt(musica),Integer.parseInt(letra), recurso);
    	   
    	   
    	   
       }
       
       %>
       
	 
	</form>
	<footer>
		<%@ include file="footer.jsp"%>
	</footer>

</body>
</html>