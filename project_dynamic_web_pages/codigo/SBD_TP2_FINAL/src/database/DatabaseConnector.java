package database;

import java.sql.Connection;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DatabaseConnector {


	//DA CHECK DE LOGIN , VENDO SE O IDTIPO DEU VALOR 1,2,3 SE DER VALOR 0, NOUTRA CLASS JAVA VOU
	// DIZER QUE O LOGIN NAO FOI FEITO COM SUCESSO

	public ResultSet makeStatement(String query) {

		Connection connect = ConnectionConfigurate.getConnection();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connect.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;


	}





	public int checkType(String name, String password) {

		Connection connect = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "SELECT userTipo from sbd1.user where name='"+name+"' and password ='"+password+"'";
		int userTipo = 0;


		try {
			statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(query);

			if (rs.isBeforeFirst() ) {    

				if (rs!=null) {
					while (rs.next()) {
						userTipo = rs.getInt("userTipo");

					}

				}

			} 
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return userTipo;
	}


	public int gedId(String name, String password) {

		Connection connect = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "SELECT idUser from sbd1.user where name='"+name+"' and password ='"+password+"'";
		int userId = 0;


		try {
			statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(query);

			if (rs!=null) {
				while (rs.next()) {
					userId = rs.getInt("idUser");

				}

			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();


		}
		return userId;
	}


	public String getUserName(String id) {

		Connection connect = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "Select name from sbd1.user where idUser= "+id+"";
		String username = "";


		try {
			statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(query);

			if (rs!=null) {
				while (rs.next()) {
					username = rs.getString("name");

				}

			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return username;
	}

	public   ArrayList<Integer>  getListings( String table, String RecursoId, String id) {

		ArrayList<Integer> ids = new ArrayList<Integer>();

		Connection connect = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "SELECT * from sbd1."+table+" where "+RecursoId+"= "+id+"";
		System.out.println(query);


		try {
			statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(query);

			if (rs!=null) {
				while (rs.next()) {
					int userTipo = rs.getInt("comentarioId");
					ids.add(userTipo);

				}

			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();


		}
		return ids;
	}


	public boolean checkTypeTitulo(String tipo, String titulo) {
		Connection connect = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query ="SELECT idRecurso from sbd1.recurso_multimedia where titulo='"+titulo+"' and RecursoTipo= '"+tipo+"'";

		try {
			statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(query);

			if (!rs.isBeforeFirst() ) {    
				return false;
			} 

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return true;
	}



	public java.sql.Date  getAgeUser(String id) {

		Connection connect = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "SELECT dataNascimento from sbd1.user where idUser = '"+id+"'";
		java.sql.Date dateBirth = null;

		try {
			statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(query);

			if (rs!=null) {
				while (rs.next()) {
					dateBirth = rs.getDate("dataNascimento");

				}

			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dateBirth;
	}


	public String getTypeRecurso(String id) {

		Connection connect = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "SELECT RecursoTipo from sbd1.recurso_multimedia where idRecurso = '"+id+"'";
		String type ="";

		try {
			statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(query);

			if (rs!=null) {
				while (rs.next()) {
					type = rs.getString("RecursoTipo");

				}

			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return type;
	}

	public String getTituloRecurso(int id) {

		Connection connect = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "SELECT titulo from sbd1.recurso_multimedia where idRecurso = '"+id+"'";
		String type ="";

		try {
			statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(query);

			if (rs!=null) {
				while (rs.next()) {
					type = rs.getString("titulo");

				}

			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return type;
	}




	public String getTituloArtista(String id) {

		Connection connect = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query =" select * from sbd1.artista where idArtista="+id+"";
		String type ="";

		try {
			statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(query);

			if (rs!=null) {
				while (rs.next()) {
					type = rs.getString("artista_name");

				}

			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return type;
	}

	//CHECK IF THERE IS ANY STRING EQUAL TO A NAME IN LIST OF NAMES IN USER NAME LIST

	public boolean checkUserNameExists(String newUserName) {

		Connection connect = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query ="select name from sbd1.user";
		boolean isTrue = false;

		try {
			statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(query);

			if (rs!=null) {
				while (rs.next()) {
					String userName = "";
					userName = rs.getString("name");

					if (userName.equals(newUserName)) {

						isTrue = true;
					}
				}

			}

		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return isTrue;
	}
	public int getIdByUserName(String userName) {

		Connection connect = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query ="select idUser from sbd1.user where name = '"+userName+"'";
		int userId= 0;

		try {
			statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			if (rs!=null) {
				while (rs.next()) {
					userId = rs.getInt("idUser");
				}
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;
	}

	
	public boolean checkAssociation(String idRecursoPrincipal, String idRecursoSecundario) {

		Connection connection = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "select * from associacao where idRecursoPrincipal="+idRecursoPrincipal+" and idRecursoSecundario="+idRecursoSecundario+"";
		// String query = "SELECT * from sbd1.comentario where RecursoId= "+id+";";
		System.out.println(query);

		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			if (!rs.isBeforeFirst() ) {    
				return false;
			} 
		} 
		
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return true;
	}
	
	
	public String getNomeAutorLetra(int id) {
	
		Connection connection = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = " select artista_name from sbd1.artista inner join sbd1.artistaMusica on artista.idArtista = artistaMusica.idArtistaMusica where sbd1.artista.idArtista= "+id+"";
		// String query = "SELECT * from sbd1.comentario where RecursoId= "+id+";";
		System.out.println(query);
		String x="";
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			if (rs!=null) {
				while (rs.next()) { 
				 x = rs.getString("artista_name");
				}
			} 
		} 
		
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return x;
	
	}
	
	public boolean getBlockedStateUser(String userId) {
		
		
		Connection connect = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query ="select blocked from sbd1.user where idUser = "+userId+"";
		boolean val = false;

		try {
			statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			if (rs!=null) {
				while (rs.next()) {
					val = rs.getBoolean("blocked");
				}
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return val;
	}
	

	public int checkType(String id) {

		Connection connect = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "SELECT userTipo from sbd1.user where idUser="+id+"";
		int userTipo = 0;


		try {
			statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(query);

			if (rs.isBeforeFirst() ) {    

				if (rs!=null) {
					while (rs.next()) {
						userTipo = rs.getInt("userTipo");

					}

				}

			} 
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return userTipo;
	}
	
	
	
	

	public static void main(String[] args)  {
			

	}
}