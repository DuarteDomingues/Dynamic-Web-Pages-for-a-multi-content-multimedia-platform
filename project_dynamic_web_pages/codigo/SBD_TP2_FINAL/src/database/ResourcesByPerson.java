package database;

import java.sql.ResultSet;
import java.util.ArrayList;


public class ResourcesByPerson {



	public ArrayList<Integer> getRecursosByArtista(String table,String idType, String id, String Recurso){

		ArrayList<Integer> recursos = new ArrayList<Integer>();


		String queryComment = "select "+Recurso+" from sbd1."+table+" where "+idType+" = "+id+"";
		System.out.println(queryComment);

		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);


		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					int recursoId = rs.getInt(Recurso);

					if (recursoId!=0) {

						 recursos.add(recursoId);
					}


				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (recursos.size()!=0) {
			return recursos;
		}
		else {
			return null;
		}

	}
	
	
}
