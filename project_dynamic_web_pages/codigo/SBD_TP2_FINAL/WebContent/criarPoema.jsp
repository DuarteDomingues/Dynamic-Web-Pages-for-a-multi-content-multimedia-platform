<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="database.CreateTables"%>
<%@ page import="database.DatabaseListings"%>
<%@ page import="java.sql.Blob"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page import="java.util.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>



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

<meta charset="ISO-8859-1">
<title>Criar Poema</title>
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
       accept="image/png, image/jpeg, image/jpg, image/jfif, image/*" required>
       <br>

       
       <h3>Poema a inserir:</h3>
		<textarea name="poema" rows="20" cols="40" required></textarea>
		<br>
		<br>
		<br>
		<div id="list1" class="dropdown-check-list" tabindex="100">
			<span class="anchor">Choose poetas:</span>
			<ul class="items">
			<%
			 LinkedHashMap<String,String> dicPoetas=dbL.getPoetasNames();
			
			  for (Map.Entry<String, String> mapElement : dicPoetas.entrySet()) { 
		    		 String keyA = mapElement.getKey(); 
		    		
		    		 String value = mapElement.getValue(); 
		    		 
		    		 %><li><input type="checkbox" id=<%=keyA %> name=<%=keyA %> value=<%=keyA %>/><%=value %></li><%
		    		 
			  }
			
			%>
			</ul>
		</div>
		 <script>
       var checkList = document.getElementById('list1');
       checkList.getElementsByClassName('anchor')[0].onclick = function(evt) {
         if (checkList.classList.contains('visible'))
           checkList.classList.remove('visible');
         else
           checkList.classList.add('visible');
       }
       
       </script>
       
       <br>
       <br>
       <br>
       
       <input name="adicionar" type="submit" value="Adicionar Recurso"/>
       
       <%
       String adicionar = request.getParameter("adicionar");
       
       if(adicionar!=null){
    	   
    	   String ilustracao1 = request.getParameter("ilustracao");
    	   String ilustracao2 = "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/"+ilustracao1;
    	   
    	   String conteudo1 = request.getParameter("poema");

    	   Date date = new Date();  
    	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
    	   String strDate= formatter.format(date); 
    	   
    	   
 			ArrayList<Integer> poetas= new ArrayList<Integer>();
    	   
    	   for (Map.Entry<String, String> mapElement : dicPoetas.entrySet()) { 
				 String keyA = mapElement.getKey(); 
				 String value = mapElement.getValue(); 
				 
				 String id = request.getParameter(keyA); 
				 
				 if(id!=null){
					 id=id.replace("/","");
					 poetas.add(Integer.parseInt(id));
				 }
			 }
    	   System.out.println("poetas: "+poetas);
    	   
    	   
    	   CreateTables ct = new CreateTables();
    	   ct.createRecurso2(tipo, titulo, ilustracao2, conteudo1, strDate, resumo, idUser, idade,false);
    	   
    	   int recurso = dbL.getLastRecurso();
    	   ct.createPoema(recurso);
    	   ct.poeta_atua_poesia(poetas, recurso);
    	   
    	   
    	   
       }
       
       %>
       
	 
	</form>
	<footer>
		<%@ include file="footer.jsp"%>
	</footer>

</body>
</html>