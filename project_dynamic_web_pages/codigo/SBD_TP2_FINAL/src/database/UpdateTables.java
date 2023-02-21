package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UpdateTables {
	
	public void UpdateUser(String userId, String val) {

		Connection connection = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "UPDATE sbd1.user SET blocked = "+val+" WHERE idUser = "+userId+"";
		// String query = "SELECT * from sbd1.comentario where RecursoId= "+id+";";

		try {
			statement = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {

			statement.executeUpdate(query);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	
	public int CountResourcesUser(String userId) {

		Connection connection = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "SELECT COUNT(idRecurso) AS numberr FROM sbd1.recurso_multimedia where userCriador= "+userId+"" ;
		// String query = "SELECT * from sbd1.comentario where RecursoId= "+id+";";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(query);
		
		int count = 0;
		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					if (rs.getInt("numberr")!=0) {
						 count = rs.getInt("numberr");
					}
					else {
						return 0;
					}
				
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public void updateRecursoEstado(Boolean estadoRecurso,int idRecurso) throws SQLException {

		Connection connection = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "update sbd1.recurso_multimedia set estadoRecurso=? where idRecurso=? ";
		DatabaseConnector db = new DatabaseConnector();
		PreparedStatement ps = null;
		ps = connection.prepareStatement(query);
        ps.setBoolean(1, estadoRecurso);
        ps.setInt(2,idRecurso);
        ps.executeUpdate();
		
	}
	
	public int getUserRep(String userPublisher) {
		
		String queryCommentType = "select reputacao from sbd1.user where name = '"+userPublisher+"'"; 
		
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs1 = db.makeStatement(queryCommentType);
		int rep=0;
		
		try {
			
			if (rs1!=null) {
				while (rs1.next()) {
					rep = rs1.getInt("reputacao");	
			
				}

			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rep;
	}
		
	
	
	public void updateUserRep(int reputacao,String userPublisher) throws SQLException {
		
		
		int repNow=getUserRep(userPublisher);

		Connection connection = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "update sbd1.user set reputacao=? where name=? ";
		DatabaseConnector db = new DatabaseConnector();
		PreparedStatement ps = null;
		ps = connection.prepareStatement(query);
		ps.setInt(1,reputacao+repNow);
        ps.setString(2,userPublisher);
        ps.executeUpdate();
		
	}
	
	
	public static void main(String[] args) throws SQLException {

		UpdateTables ut = new UpdateTables();
		//ut.updateRecursoEstado(true, 1);
		//ut.updateRecursoEstado(true, 6);
		//ut.updateUserRep(10, 3);
		
	}
	

}
