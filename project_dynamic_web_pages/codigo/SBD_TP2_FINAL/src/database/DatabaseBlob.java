package database;

import java.sql.Blob;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseBlob {

	public byte[] blob(){

		

		String queryComment = "select conteudo from sbd1.recurso_multimedia where idRecurso = '1'"; 
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);
		
		Blob image = null;  
		byte[] imgData = null;  

		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				image = rs.getBlob(0);
				imgData = image.getBytes(1, (int) image.length());  
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return imgData;

	}

}
