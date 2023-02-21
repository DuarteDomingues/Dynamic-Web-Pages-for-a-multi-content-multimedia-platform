package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import parameters.Parameters;

import java.util.Set;


public class DatabaseListings {

	//RETORNA UMA LISTA COM OS IDS DOS COMENTARIOS DO RECURSO ID DE PARAMETRO

public ArrayList<String> getTypeName() {
		
		String id="1";
		
		String queryCommentType = "select RecursoTipo from sbd1.recurso_multimedia where idRecurso = "+id; 
		String queryCommentContent = "select conteudo from sbd1.recurso_multimedia where idRecurso = "+id; 
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs1 = db.makeStatement(queryCommentType);
		ResultSet rs2 = db.makeStatement(queryCommentContent);
		
		
		String userTipo="";
		String texto="";
		
		try {
			
			if (rs1!=null) {
				while (rs1.next()) {
					userTipo = rs1.getString("RecursoTipo");
					System.out.println("User tipo 1: "+userTipo);
					
			
				}

			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(userTipo.equals("pma")) {
			try {
				if (rs2.next()) {
					
					texto=rs2.getString("conteudo");
					
					} 
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		ArrayList<String> array = new ArrayList<String>();
		array.add(userTipo);
		array.add(id);
		array.add(texto);
		
		return array;
		
	}
	
	public  LinkedHashMap<String,String> getRecursoUserId(String userCriador){

		

		String queryComment = "select titulo from sbd1.recurso_multimedia where userCriador = "+userCriador;
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);
		
		LinkedHashMap<String,String> titulos = new LinkedHashMap<String,String>();
		String titulo="";
		String idRecurso="";
		
		ArrayList<String> aux1= new ArrayList<String>();
		ArrayList<String> aux2= new ArrayList<String>();
		
		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					
					titulo=rs.getString("titulo");
					aux1.add(titulo);
					
				


				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String queryRecurso = "select idRecurso from sbd1.recurso_multimedia where userCriador = "+userCriador;
		//DatabaseConnector db2 = new DatabaseConnector();
		ResultSet rs2 = db.makeStatement(queryRecurso);
		if (rs2 != null) {
			try {
				
				while (rs2.next()) {
					
					idRecurso=rs2.getString("idRecurso");
					aux2.add(idRecurso);
					
				

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		for(int i=0;i<aux1.size();i++) {
			titulos.put(aux2.get(i),aux1.get(i) );
		}
		
		
		
		
		return titulos;

	}

	
	
	public LinkedHashMap<String,String> getRealizadoresNames() {
		
	
		
		String query = "select artista_name from sbd1.artista inner join sbd1.realizador on realizador.idRealizador = artista.idArtista where artista.idArtista = realizador.idRealizador"; 
		
	
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs1 = db.makeStatement(query);
		
		LinkedHashMap<String,String> titulos = new LinkedHashMap<String,String>();
		ArrayList<String> aux1= new ArrayList<String>();
		ArrayList<String> aux2= new ArrayList<String>();
		
		
		
		String userTipo="";
		
		
		try {
			
			if (rs1!=null) {
				while (rs1.next()) {
					userTipo = rs1.getString("artista_name");
					aux1.add(userTipo);
					
			
				}

			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String query2 = "select idRealizador from sbd1.realizador";
		ResultSet rs2 = db.makeStatement(query2);
		userTipo="";
		try {
			
			if (rs2!=null) {
				while (rs2.next()) {
					userTipo = rs2.getString("idRealizador");
					aux2.add(userTipo);
					
			
				}

			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<aux1.size();i++) {
			titulos.put(aux2.get(i),aux1.get(i) );
		}
	return titulos;
	}
	
public int getLastRecurso() {
		

		
		String queryCommentType = "select idRecurso from sbd1.recurso_multimedia"; 
		
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs1 = db.makeStatement(queryCommentType);
		ArrayList<Integer> arrayId = new ArrayList<Integer>();
		
		
		
		int userTipo=0;
		
		
		try {
			
			if (rs1!=null) {
				while (rs1.next()) {
					userTipo = rs1.getInt("idRecurso");
					arrayId.add(userTipo);
					System.out.println("User tipo 1: "+userTipo);
					
			
				}

			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int max=(Collections.max(arrayId));
		
		return max;
		
	}

public LinkedHashMap<String,String> getActoresNames() {
	
	String queryCommentType = "select artista_name from sbd1.artista inner join sbd1.ator on ator.idAtor = artista.idArtista where artista.idArtista = ator.idAtor"; 
	
	DatabaseConnector db = new DatabaseConnector();
	ResultSet rs1 = db.makeStatement(queryCommentType);
	
	LinkedHashMap<String,String> atores = new LinkedHashMap<String,String>();
	ArrayList<String> aux1= new ArrayList<String>();
	ArrayList<String> aux2= new ArrayList<String>();
	
	
	String userTipo="";
	
	try {
		if (rs1!=null) {
			while (rs1.next()) {
				userTipo = rs1.getString("artista_name");
				aux1.add(userTipo);
				
					
			}

		}

	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String query2 = "select idAtor from sbd1.ator";
	ResultSet rs2 = db.makeStatement(query2);
	userTipo="";
	try {
		
		if (rs2!=null) {
			while (rs2.next()) {
				userTipo = rs2.getString("idAtor");
				aux2.add(userTipo);
				
		
			}

		}

	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	for(int i=0;i<aux1.size();i++) {
		atores.put(aux2.get(i),aux1.get(i) );
	}
	
	
	return atores;
}

public LinkedHashMap<String,String> getArtistasMusicasNames() {
	
	String queryCommentType = "select artista_name from sbd1.artista inner join sbd1.artistaMusica on artistaMusica.idArtistaMusica = artista.idArtista where artista.idArtista = artista.idArtista"; 
	
	DatabaseConnector db = new DatabaseConnector();
	ResultSet rs1 = db.makeStatement(queryCommentType);
	
	LinkedHashMap<String,String> atores = new LinkedHashMap<String,String>();
	ArrayList<String> aux1= new ArrayList<String>();
	ArrayList<String> aux2= new ArrayList<String>();
	
	
	String userTipo="";
	
	try {
		if (rs1!=null) {
			while (rs1.next()) {
				userTipo = rs1.getString("artista_name");
				aux1.add(userTipo);
				
					
			}

		}

	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String query2 = "select idArtistaMusica from sbd1.artistaMusica";
	ResultSet rs2 = db.makeStatement(query2);
	userTipo="";
	try {
		
		if (rs2!=null) {
			while (rs2.next()) {
				userTipo = rs2.getString("idArtistaMusica");
				aux2.add(userTipo);
				
		
			}

		}

	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	for(int i=0;i<aux1.size();i++) {
		atores.put(aux2.get(i),aux1.get(i) );
	}
	
	
	return atores;
}

public LinkedHashMap<String,String> getArtistasLetrasMusicasNames() {
	
	String queryCommentType = "select artista_name from sbd1.artista inner join sbd1.artistaLetra on artistaLetra.idArtistaLetra = artista.idArtista where artista.idArtista = artista.idArtista"; 
	
	DatabaseConnector db = new DatabaseConnector();
	ResultSet rs1 = db.makeStatement(queryCommentType);
	
	LinkedHashMap<String,String> atores = new LinkedHashMap<String,String>();
	ArrayList<String> aux1= new ArrayList<String>();
	ArrayList<String> aux2= new ArrayList<String>();
	
	
	String userTipo="";
	
	try {
		if (rs1!=null) {
			while (rs1.next()) {
				userTipo = rs1.getString("artista_name");
				aux1.add(userTipo);
				
					
			}

		}

	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String query2 = "select idArtistaLetra from sbd1.artistaLetra";
	ResultSet rs2 = db.makeStatement(query2);
	userTipo="";
	try {
		
		if (rs2!=null) {
			while (rs2.next()) {
				userTipo = rs2.getString("idArtistaLetra");
				aux2.add(userTipo);
				
		
			}

		}

	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	for(int i=0;i<aux1.size();i++) {
		atores.put(aux2.get(i),aux1.get(i) );
	}
	
	
	return atores;
}

public LinkedHashMap<String,String> getFotografosNames() {
	
	String queryCommentType = "select artista_name from sbd1.artista inner join sbd1.fotografo on fotografo.idFotografo = artista.idArtista where artista.idArtista = artista.idArtista"; 
	
	DatabaseConnector db = new DatabaseConnector();
	ResultSet rs1 = db.makeStatement(queryCommentType);
	
	LinkedHashMap<String,String> atores = new LinkedHashMap<String,String>();
	ArrayList<String> aux1= new ArrayList<String>();
	ArrayList<String> aux2= new ArrayList<String>();
	
	
	String userTipo="";
	
	try {
		if (rs1!=null) {
			while (rs1.next()) {
				userTipo = rs1.getString("artista_name");
				aux1.add(userTipo);
				
					
			}

		}

	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String query2 = "select idFotografo from sbd1.fotografo";
	ResultSet rs2 = db.makeStatement(query2);
	userTipo="";
	try {
		
		if (rs2!=null) {
			while (rs2.next()) {
				userTipo = rs2.getString("idFotografo");
				aux2.add(userTipo);
				
		
			}

		}

	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	for(int i=0;i<aux1.size();i++) {
		atores.put(aux2.get(i),aux1.get(i) );
	}
	
	
	return atores;
}

public LinkedHashMap<String,String> getPoetasNames() {
	
	String queryCommentType = "select artista_name from sbd1.artista inner join sbd1.poeta on poeta.idPoeta = artista.idArtista where artista.idArtista = artista.idArtista"; 
	
	DatabaseConnector db = new DatabaseConnector();
	ResultSet rs1 = db.makeStatement(queryCommentType);
	
	LinkedHashMap<String,String> atores = new LinkedHashMap<String,String>();
	ArrayList<String> aux1= new ArrayList<String>();
	ArrayList<String> aux2= new ArrayList<String>();
	
	
	String userTipo="";
	
	try {
		if (rs1!=null) {
			while (rs1.next()) {
				userTipo = rs1.getString("artista_name");
				aux1.add(userTipo);
				
					
			}

		}

	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String query2 = "select idPoeta from sbd1.poeta";
	ResultSet rs2 = db.makeStatement(query2);
	userTipo="";
	try {
		
		if (rs2!=null) {
			while (rs2.next()) {
				userTipo = rs2.getString("idPoeta");
				aux2.add(userTipo);
				
		
			}

		}

	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	for(int i=0;i<aux1.size();i++) {
		atores.put(aux2.get(i),aux1.get(i) );
	}
	
	
	return atores;
}

public String getTypeR(int idRecurso) {
	

	

	String queryCommentType = "select RecursoTipo from sbd1.recurso_multimedia where idRecurso = "+idRecurso; 
	
	DatabaseConnector db = new DatabaseConnector();
	ResultSet rs1 = db.makeStatement(queryCommentType);
	
	
	String userTipo="";
	
	
	try {
		
		if (rs1!=null) {
			while (rs1.next()) {
				userTipo = rs1.getString("RecursoTipo");
				
		
			}

		}

	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return userTipo;
	
}

	
	
	
	
	
	

	
	public ArrayList<String> recursosTitles(){

		ArrayList<String> recursosTitulos = new ArrayList<String>();


		String queryComment = "select titulo from sbd1.recurso_multimedia";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);

		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					String titulo = rs.getString("titulo");
					recursosTitulos.add(titulo);


				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return recursosTitulos;

	}

	//CRIAR UM DICIONARIO (IDRECURSO,IDADERECURSOEMDIAS)

	public HashMap<Integer, Long> recursosidades(){

		HashMap<Integer, Long> dic = new HashMap<Integer, Long>();

		String queryComment = "select dataCarregamento,idRecurso from sbd1.recurso_multimedia";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);
		int idRecurso;


		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					java.sql.Date dbSqlDate = rs.getDate("dataCarregamento");
					String strDate = String.valueOf(dbSqlDate);

					YearHandler yh = new YearHandler();	
					long days = yh.yearsToDays(strDate);
					idRecurso = rs.getInt("idRecurso");

					dic.put(idRecurso,days);


				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dic;
	}


	public HashMap<Integer, String> getRecursoNameDic(String tituloRecurso, String recursoId){

		HashMap<Integer, String> dic = new HashMap<Integer, String>();

		String queryComment = "select titulo,idRecurso from sbd1.recurso_multimedia where titulo!= '"+tituloRecurso+"'";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);
		int idRecurso;


		if (rs != null) {
			try {

				while (rs.next()) {
					String recursoTitulo = rs.getString("titulo");
					idRecurso = rs.getInt("idRecurso");
					if (checkAssociacaoExiste(recursoId,idRecurso)==false){
						dic.put(idRecurso,recursoTitulo);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dic;
	}

	public boolean checkAssociacaoExiste(String idRecursoPrimario, int idRecursoSecundario){


		String queryComment = "select * from sbd1.associacao where idRecursoPrincipal="+idRecursoPrimario+" and idRecursoSecundario="+idRecursoSecundario+"";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);
		int idRecurso;


		if (rs != null) {
			try {
				if (rs.isBeforeFirst() ) {    
					return true;
				} 


			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	
	public HashMap<Integer, Integer> getClassificacaoDic(String recursoId){
		
		HashMap<Integer, Integer> dic = new HashMap<Integer, Integer>();
		String queryComment = "select UserClassificador,classificacaoVal from sbd1.classificacao where RecursoId="+recursoId+"";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);

		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					
					int userClass = rs.getInt("UserClassificador");
					int val = rs.getInt("classificacaoVal");
					

					dic.put(userClass,val);


				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dic;
	}
	
public HashMap<Integer, String> getComentarioDic(String recursoId){
		
		HashMap<Integer, String> dic = new HashMap<Integer, String>();
		String queryComment = "select UserComentador,comentario from sbd1.comentario where RecursoId="+recursoId+"";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);
		int c=0;

		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					c++;
					//int userComentador = rs.getInt("UserComentador");
					String com = rs.getString("comentario");
					//System.out.println(userComentador);

					dic.put(c,com);


				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dic;
	}

public ArrayList<Integer> getComentarioIds(String recursoId){
	
	ArrayList<Integer> arr = new ArrayList<Integer>();
	String queryComment = "select UserComentador from sbd1.comentario where RecursoId="+recursoId+"";
	DatabaseConnector db = new DatabaseConnector();
	ResultSet rs = db.makeStatement(queryComment);
	

	if (rs != null) {
		try {
			//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
			while (rs.next()) {
				
				int userComentador = rs.getInt("UserComentador");
				arr.add(userComentador);


			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	return arr;
	
}

public ArrayList<String> getComentariotexts(String recursoId){
	
	ArrayList<String> arr = new ArrayList<String>();
	String queryComment = "select comentario from sbd1.comentario where RecursoId="+recursoId+"";
	DatabaseConnector db = new DatabaseConnector();
	ResultSet rs = db.makeStatement(queryComment);
	

	if (rs != null) {
		try {
			//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
			while (rs.next()) {
			
				String com = rs.getString("comentario");
				arr.add(com);


			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	return arr;
	
}

public ArrayList<String> getComentariodates(String recursoId){
	
	ArrayList<String> arr = new ArrayList<String>();
	String queryComment = "select dataComentario from sbd1.comentario where RecursoId="+recursoId+"";
	DatabaseConnector db = new DatabaseConnector();
	ResultSet rs = db.makeStatement(queryComment);
	
	

	if (rs != null) {
		try {
			//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
			while (rs.next()) {
			
				java.sql.Date com = rs.getDate("dataComentario");
				String dateS = String.valueOf(com);
				arr.add(dateS);


			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	return arr;
	
}



	






	

	public HashMap<String, String> getRecursoTituloAssociacoes(String idRecurso){

		HashMap<String, String> dic = new HashMap<String, String>();

		String queryComment = "select idRecursoSecundario,titulo from associacao inner join sbd1.recurso_multimedia on  recurso_multimedia.idRecurso = associacao.idRecursoSecundario where idRecursoPrincipal="+idRecurso+"" ;
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);

		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					String recursoTitulo = rs.getString("titulo");
					int idRecursox = rs.getInt("idRecursoSecundario");
					String idRecursoS = String.valueOf(idRecursox);

					dic.put(idRecursoS,recursoTitulo);


				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dic;
	}

	


	//ORGANIZA O DICIONARIO

	public static HashMap<Integer, Long> sortByValue(HashMap<Integer, Long> hm) 
	{ 
		// Create a list from elements of HashMap 
		List<Map.Entry<Integer, Long> > list = 
				new LinkedList<Map.Entry<Integer, Long> >(hm.entrySet()); 

		// Sort the list 
		Collections.sort(list, new Comparator<Map.Entry<Integer, Long> >() { 
			@Override
			public int compare(Map.Entry<Integer, Long> o1,  
					Map.Entry<Integer, Long> o2) 
			{ 
				return (o1.getValue()).compareTo(o2.getValue()); 
			} 
		}); 

		// put data from sorted list to hashmap  
		HashMap<Integer, Long> temp = new LinkedHashMap<Integer, Long>(); 
		for (Map.Entry<Integer, Long> aa : list) { 
			temp.put(aa.getKey(), aa.getValue()); 
		} 
		return temp; 
	} 	


	public static HashMap<Integer, Integer> sortByValueInt(HashMap<Integer, Integer> hm) 
	{ 
		// Create a list from elements of HashMap 
		List<Map.Entry<Integer, Integer> > list = 
				new LinkedList<Map.Entry<Integer, Integer> >(hm.entrySet()); 

		// Sort the list 
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() {
			@Override
			public int compare(Map.Entry<Integer, Integer> o1,  
					Map.Entry<Integer, Integer> o2) 
			{ 
				return (o2.getValue()).compareTo(o1.getValue()); 
			} 
		}); 

		// put data from sorted list to hashmap  
		HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>(); 
		for (Map.Entry<Integer, Integer> aa : list) { 
			temp.put(aa.getKey(), aa.getValue()); 
		} 
		return temp; 
	} 	





	public  ArrayList<String> getAtoresNames(String idFilme){

		ArrayList<String> atores = new ArrayList<String>();

		String queryComment = "select  idAtor,artista_name from  sbd1.ator_atua_filme inner join sbd1.artista on artista.idArtista = ator_atua_filme.idAtor where idFilme ="+idFilme+"";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);


		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					String ator = rs.getString("artista_name");
					atores.add(ator);



				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return atores;

	}

	public  ArrayList<Integer> getAtoresIds(String idFilme){

		ArrayList<Integer> atores = new ArrayList<Integer>();

		String queryComment = "select  idAtor  from  sbd1.ator_atua_filme inner join sbd1.artista on artista.idArtista = ator_atua_filme.idAtor where idFilme ="+idFilme+"";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);


		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {

					int idAtor = rs.getInt("idAtor");
					atores.add(idAtor);



				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return atores;

	}


	public ArrayList<Integer> getArtistasIdList(){

		ArrayList<Integer> atores = new ArrayList<Integer>();

		String queryComment = "select idArtista from sbd1.artista";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);


		if (rs != null) {
			try {

				while (rs.next()) {

					int idAtor = rs.getInt("idArtista");
					atores.add(idAtor);



				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return atores;

	}





	public  ArrayList<String> getPoetasNames(String idPoema){

		ArrayList<String> atores = new ArrayList<String>();

		String queryComment = "select  artista_name from  sbd1.poeta_atua_poesia inner join sbd1.artista on artista.idArtista = poeta_atua_poesia.idPoeta where idPoema ="+idPoema+"";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);


		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					String poeta = rs.getString("artista_name");
					atores.add(poeta);



				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return atores;

	}


	public  ArrayList<Integer> getPoetasIds(String idPoema){

		ArrayList<Integer> atores = new ArrayList<Integer>();

		String queryComment = "select  idArtista from  sbd1.poeta_atua_poesia inner join sbd1.artista on artista.idArtista = poeta_atua_poesia.idPoeta where idPoema ="+idPoema+"";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);


		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					int poeta = rs.getInt("idArtista");
					atores.add(poeta);



				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return atores;

	}
















	//GET DICIONARIO COM IDS COM COMENTARIOS ASSOCIADOS

	public  int  getComentariosMedia(int recursoId){


		String queryComment = "select classificacaoVal from sbd1.recurso_multimedia inner join sbd1.classificacao on classificacao.RecursoId = recurso_multimedia.idRecurso where recurso_multimedia.idRecurso = "+recursoId+"";

		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);

		int c= 0;
		int val=0;
		int finalVal=0;

		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					val= val + rs.getInt("classificacaoVal");
					c=c+1;




				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (c!=0) {
			finalVal = (val/c);
		}

		return finalVal;

	}


	public HashMap<Integer, Integer>  getComentariosIdDictionary(ArrayList<Integer> arr) {

		HashMap<Integer, Integer> dic = new HashMap<Integer,Integer>();

		for ( int i =0; i< arr.size();i++) {

			int idRecurso = (arr.get(i));
			int val = getComentariosMedia(arr.get(i));
			dic.put(idRecurso, val);

		}

		return dic;




	}

	public HashMap<Integer, Integer> getDicionarioRecursoIdClassificacao(){
		DatabaseListings dl = new DatabaseListings();
		HashMap<Integer, Long> dic = dl.recursosidades() ;
		HashMap<Integer, Long> dicOrganized = sortByValue(dic);
		ArrayList<Integer> recursosIdsOrganized = new ArrayList<Integer>();

		int c= 0;
		for ( Integer key : dicOrganized.keySet() ) {
			if ( c< Parameters.N) {

				c=c+1;
				recursosIdsOrganized.add(key);
			}
			else { 
				break;
			}

		}

		HashMap<Integer, Integer> dicFinalUnorganized = dl.getComentariosIdDictionary(recursosIdsOrganized);
		HashMap<Integer, Integer> dicFinalOrganized = sortByValueInt(dicFinalUnorganized) ;

		return dicFinalOrganized;


	}

	
	public HashMap<Integer, Integer> getUserReputacaoMap( ){

		HashMap<Integer, Integer> dic = new HashMap<Integer, Integer>();

		String queryComment = "select idUser,reputacao from sbd1.user";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);

		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {

					int idUser = rs.getInt("idUser");
					int reputacao = rs.getInt("reputacao");

					dic.put(idUser,reputacao);


				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dic;
	}

	public  HashMap<Integer, Integer> getUserReputacaoDic() {

		HashMap<Integer, Integer> dic = getUserReputacaoMap();
		HashMap<Integer, Integer> dicOrganized= sortByValueInt(dic) ;
		return dicOrganized;



	}

	public ArrayList<String> getUserAttributes(int idUser){

		ArrayList<String> arr = new ArrayList<String>();
		String queryComment = "select name,nacionalidade,dataNascimento,userTipo,blocked from sbd1.user where idUser="+idUser+"";
		System.out.println(queryComment);
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);


		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					String name = rs.getString("name");
					String nacionalidade = rs.getString("nacionalidade");
					java.sql.Date date = rs.getDate("dataNascimento");
					String dateS = String.valueOf(date);
					int type = rs.getInt("userTipo");
					String typeS = String.valueOf(type);
					boolean state = rs.getBoolean("blocked");
					String stateS = String.valueOf(state);

					arr.add(0, name);
					arr.add(1, nacionalidade);
					arr.add(2,dateS);
					arr.add(3, typeS);
					arr.add(4, stateS);


					//boolean blocked = rs.getBoolean("blocked")


				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return arr;
	}

	public ArrayList<String> getRecursoAttributes(String idRecurso){

		ArrayList<String> arr = new ArrayList<String>();
		String queryComment = "select dataCarregamento,RecursoTipo from sbd1.recurso_multimedia where idRecurso="+idRecurso+"";
		System.out.println(queryComment);
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);


		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					String name = rs.getString("RecursoTipo");
					java.sql.Date date = rs.getDate("dataCarregamento");
					String dateS = String.valueOf(date);
					
					arr.add(0, name);
					arr.add(1, dateS);
				

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return arr;
	}
	public String getNameUser(int id) {
		
		
		String queryComment = "select name from sbd1.user where idUser="+id+"";
		System.out.println(queryComment);
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);
		String userName = "";


		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					 userName = rs.getString("name");
					
				

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return userName;
	}
	
public Boolean getEstadoRecurso(int recursoId) {
		
		
		String queryComment = "select estadoRecurso from sbd1.recurso_multimedia where idRecurso="+recursoId;
		System.out.println(queryComment);
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);
		Boolean estado = null;


		if (rs != null) {
			try {
				
				while (rs.next()) {
					estado = rs.getBoolean("estadoRecurso");
					
				

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return estado;
	}
	

	
	


	public static void main(String[] args) {

		DatabaseListings dl = new DatabaseListings();

		
		




		/*DatabaseListings dbL = new DatabaseListings();
		HashMap<String,String> array=dbL.getRecursoUserId("1");
		System.out.println(array);*/

		/*
		DatabaseListings dl = new DatabaseListings();
		HashMap<Integer, Long> dic = dl.recursosidades() ;
		HashMap<Integer, Long> dicOrganized = sortByValue(dic);
		ArrayList<Integer> recursosIdsOrganized = new ArrayList<Integer>();

		int c= 0;
		for ( Integer key : dicOrganized.keySet() ) {
			if ( c< Parameters.N) {

				c=c+1;
				recursosIdsOrganized.add(key);
			}
			else { 
				break;
			}

		}

		HashMap<Integer, Integer> dicFinalUnorganized = dl.getComentariosIdDictionary(recursosIdsOrganized);
		HashMap<Integer, Integer> dicFinalOrganized = sortByValueInt(dicFinalUnorganized) ;





		for (Integer i : dicFinalOrganized.keySet()) {
			System.out.println(i);
			System.out.println(dicFinalOrganized.get(i));
		}
		 */
	}


}
