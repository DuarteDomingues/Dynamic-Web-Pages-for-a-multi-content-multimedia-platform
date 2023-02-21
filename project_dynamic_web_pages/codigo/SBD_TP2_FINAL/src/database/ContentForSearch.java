package database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class ContentForSearch {
	
	
	public String  getAgeTipoRecurso(  int inputVal ) {

		String queryComment = "select idadeTipo from sbd1.recurso_multimedia where idRecurso="+inputVal+"";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);
		String strFinal = "";

		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					strFinal = rs.getString("idadeTipo");
					

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		return strFinal;
	}
	
	
	public String  getNames(   ) {

		String queryComment = "select artista_name from sbd1.artista";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);
		String strFinal = "";

		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					String artista_name = rs.getString("artista_name");
					strFinal = strFinal+artista_name+",";

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		return strFinal;
	}

	public String  getTitulos(   ) {

		String queryComment = "select titulo from sbd1.recurso_multimedia";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);
		String strFinal = "";

		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					String artista_name = rs.getString("titulo");
					strFinal = strFinal+artista_name+",";

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		return strFinal;
	}
	
	public String  getTitulosByRecursoId( int idRecurso   ) {

		String queryComment = "select titulo from sbd1.recurso_multimedia where idRecurso="+idRecurso+"";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);
		String strFinal = "";

		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					 strFinal = rs.getString("titulo");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		return strFinal;
	}
	
	
	
	
	
	

	public String  getResumo(   ) {

		String queryComment = "select resumo from sbd1.recurso_multimedia";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);
		String strFinal = "";

		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					String artista_name = rs.getString("resumo");
					if ( artista_name!="");
					strFinal = strFinal+artista_name;

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		return strFinal;
	}


	//select idRecurso from sbd1.recurso_multimedia inner join sbd1.artista on artista.artista_name WHERE artista_name LIKE '%a%';


	public HashMap<Integer, String> getTitulosWithKey( String inputUser  ) {

		String queryComment = "select idRecurso,titulo from sbd1.recurso_multimedia where (resumo)  LIKE '%"+inputUser+"%' or titulo LIKE '%"+inputUser+"%'";
		System.out.println(queryComment);
		//ArrayList<Integer> ids = new ArrayList<Integer>();
		HashMap<Integer, String> dic = new  HashMap<Integer, String>();
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);

		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {



					int recursoId = rs.getInt("idRecurso");
					String recursoNome = rs.getString("titulo");
					dic.put(recursoId, recursoNome);



				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		return dic;
	}

	public String makeStringPoema( String userInput) {

		String query = "select recurso_multimedia.idRecurso,titulo from sbd1.poeta_atua_poesia inner join sbd1.recurso_multimedia on recurso_multimedia.idRecurso=poeta_atua_poesia.idPoema inner join  sbd1.artista on artista.idArtista=poeta_atua_poesia.idPoeta where (artista_name  LIKE '%"+userInput+"%')";
		return query;


	}

	public String makeStringFilme( String userInput) {

		String query = "select recurso_multimedia.idRecurso,titulo from sbd1.filme inner join sbd1.recurso_multimedia on recurso_multimedia.idRecurso=filme.idRecurso inner join  sbd1.artista on artista.idArtista=filme.idRealizador where (artista_name  LIKE '%"+userInput+"%')";

		return query;


	}

	public String makeStringAtorAtuaFilme(String userInput) {

		String query = "select recurso_multimedia.idRecurso,titulo from sbd1.ator_atua_filme inner join sbd1.recurso_multimedia on recurso_multimedia.idRecurso=ator_atua_filme.idFilme inner join  sbd1.artista on artista.idArtista=ator_atua_filme.idAtor where (artista_name  LIKE '%"+userInput+"%')";
		return query;

	}

	public String makeStringFoto(String userInput) {

		String query = "select recurso_multimedia.idRecurso,titulo from sbd1.fotografia inner join sbd1.recurso_multimedia on recurso_multimedia.idRecurso=fotografia.idRecurso inner join  sbd1.artista on artista.idArtista=fotografia.idFotografo where (artista_name  LIKE '%"+userInput+"%')";
		return query;
	}

	public String makeStringMusica(String userInput) {

		String query = "select recurso_multimedia.idRecurso,titulo from sbd1.musica inner join sbd1.recurso_multimedia on recurso_multimedia.idRecurso=musica.idRecurso inner join  sbd1.artista on artista.idArtista=musica.idAutor where (artista_name LIKE '%"+userInput+"%')";
		return query;
	}


	public HashMap<Integer, String> getRecursoTituloIds(String queryComment ) {

		HashMap<Integer, String> dic = new HashMap<Integer, String>();
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);

		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					String titulo = rs.getString("titulo");
					int recursoId = rs.getInt("idRecurso");
					dic.put(recursoId, titulo);



				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		return dic;
	}


	public static void main(String[] args) {
		/*
		ContentForSearch cs = new ContentForSearch();
		String nome = cs.getNames();
		String titulo = cs.getTitulos();
		String resumo = cs.getResumo();
		String finals = nome+titulo+resumo;
		String pal = "a";
		
		String filmeStr = cs.makeStringFilme(pal);
		String poemaStr = cs.makeStringPoema(pal);
		String musicaStr = cs.makeStringMusica(pal);
		String atorStr = cs.makeStringAtorAtuaFilme(pal);
		String photoStr = cs.makeStringFoto(pal);
		
		
		HashMap<Integer, String> xdfilme = cs.getRecursoTituloIds(filmeStr);
		HashMap<Integer, String> xdpoema = cs.getRecursoTituloIds(poemaStr);
		HashMap<Integer, String> xdmusica = cs.getRecursoTituloIds(musicaStr);
		HashMap<Integer, String> xdator = cs.getRecursoTituloIds(atorStr);
		HashMap<Integer, String> xddphoto = cs.getRecursoTituloIds(photoStr);
		xdfilme.putAll(xdpoema);
		xdfilme.putAll(xdmusica);
		xdfilme.putAll(xdator);
		xdfilme.putAll(xddphoto);
		
		
		
		
		System.out.println("boo");
		for (Integer i : xdfilme.keySet()) {
			System.out.println(i);
			System.out.println(xdfilme.get(i));
		}

		/*
		HashMap<Integer, String> xd = new HashMap<Integer, String>();
		HashMap<Integer, String> xd2 = new HashMap<Integer, String>();
		// using put method
		xd.put(1, "Apple");
		xd.put(2, "Banana");
		xd2.put(4, "dsc");
		xd.putAll(xd2);
		System.out.println(":d");

		for (Integer i : xd.keySet()) {
			System.out.println(i);
			System.out.println(xd.get(i));
		}

		 */


	}



}
