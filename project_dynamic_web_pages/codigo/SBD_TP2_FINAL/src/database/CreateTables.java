package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CreateTables {

	public void createComment(String RecursoId, String commentText, String userName, String dateCurrent) {

		ArrayList<Integer> ids = new ArrayList<Integer>();

		Connection connection = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "INSERT INTO comentario( UserComentador, RecursoId, comentario, dataComentario) VALUES( "
				+ userName + "," + RecursoId + ",'" + commentText + "','" + dateCurrent + "')";
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

	public void createClassificacao(String RecursoId, String classVal, String userName) {

		Connection connection = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "INSERT INTO classificacao( UserClassificador, RecursoId, classificacaoVal) VALUES( " + userName
				+ "," + RecursoId + "," + classVal + ")";
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


	//CRIAR USER 

	public void createUser(String name, String password, String nacionalidade, String dataNascimento) {


		Connection connection = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "INSERT INTO user( name,password, nacionalidade, dataNascimento) VALUES( '"
				+ name + "','" + password + "','"+nacionalidade+"','" + dataNascimento + "')";
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

	public void createAssociation(String idRecursoPrincipal, String idRecursoSecundario) {
		DatabaseConnector dc = new DatabaseConnector();
		boolean canAssociate = dc.checkAssociation( idRecursoPrincipal,  idRecursoSecundario);

		if (canAssociate==false) {

			Connection connection = ConnectionConfigurate.getConnection();
			Statement statement = null;
			String queryPhrase = "insert into sbd1.associacao( idRecursoPrincipal, idRecursoSecundario) values( " + idRecursoPrincipal + "," + idRecursoSecundario + ")";
			System.out.println("created association");

			try {
				statement = connection.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {

				statement.executeUpdate(queryPhrase);

			} catch (Exception e) {

				e.printStackTrace();
			}

		}
	}


	public void createRecurso(String recursoTipo, String titulo, String ilustracao, String conteudo,
			String dataCarregamento, String resumo, int userCriador, String idadeTipo) {

		Connection connection = ConnectionConfigurate.getConnection();
		Statement statement = null;
		// String query = "INSERT INTO recurso_multimedia( RecursoTipo, titulo,
		// ilustracao,conteudo,dataCarregamento,resumo,userCriador,idadeTipo) VALUES(
		// "+recursoTipo+","+ilustracao+","+conteudo+","+titulo+","+dataCarregamento+","+resumo+","+""+","+userCriador+","+idadeTipo+")";
		// String query = "SELECT * from sbd1.comentario where RecursoId= "+id+";";
		String query = "INSERT INTO recurso_multimedia (RecursoTipo,titulo,ilustracao,conteudo,dataCarregamento,resumo,userCriador,idadeTipo) VALUES ('"
				+ recursoTipo + "','" + titulo + "','" + ilustracao + "','" + conteudo + "','" + dataCarregamento
				+ "','" + resumo + "'," + userCriador + ",'" + idadeTipo + "')";
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

	public void createRecurso2(String recursoTipo, String titulo, String ilustracao, String conteudo,
			String dataCarregamento, String resumo, int userCriador, String idadeTipo, Boolean  estadoRecurso)
					throws SQLException, FileNotFoundException {

		Connection connection = ConnectionConfigurate.getConnection();

		System.out.println("data de carregamento: "+dataCarregamento);

		File ilustracaoFile = new File(ilustracao);


		PreparedStatement pstmt = null;

		pstmt = connection.prepareStatement(
				"insert into recurso_multimedia(RecursoTipo,titulo,ilustracao,conteudo,dataCarregamento,resumo,userCriador,idadeTipo,estadoRecurso) "
						+ "values(?,?,?,?,?,?,?,?,?)");

		pstmt.setString(1, recursoTipo);
		pstmt.setString(2, titulo);

		FileInputStream fis1 = new FileInputStream(ilustracaoFile);
		pstmt.setBinaryStream(3, (InputStream) fis1, (int) (ilustracaoFile.length()));
		if(!(recursoTipo.equals("pma"))) {

			File conteudoFile = new File(conteudo);
			FileInputStream fis2 = new FileInputStream(conteudoFile);
			pstmt.setBinaryStream(4, (InputStream) fis2, (int) (conteudoFile.length()));

		}else {
			pstmt.setString(4, conteudo);
		}


		pstmt.setString(5, dataCarregamento);
		pstmt.setString(6, resumo);
		pstmt.setInt(7, userCriador);
		pstmt.setString(8, idadeTipo);
		pstmt.setBoolean(9, estadoRecurso);

		int count = pstmt.executeUpdate();
		if (count > 0) {
			System.out.println("insert successfully");
		} else {
			System.out.println("not successfully");
		}

	}

	public void removerRecursos(ArrayList<Integer> array) throws SQLException {

		Connection connection = ConnectionConfigurate.getConnection();

		String query = "delete from recurso_multimedia where idRecurso = ?";
		PreparedStatement preparedStmt = connection.prepareStatement(query);

		for(Integer r : array) {
			preparedStmt.setInt(1,r);
			preparedStmt.execute();
		}


	}

	public void createFilme(String anoLancamento,String idRealizador,String idRecurso) {


		Connection connection = ConnectionConfigurate.getConnection();
		Statement statement = null;
		String query = "INSERT INTO filme( anoLancamento, idRealizador, idRecurso) VALUES( '"+anoLancamento+"',"+idRealizador+","+idRecurso+")";
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

	public void atorAtuaFilme(ArrayList<Integer> atores,int idFilme) throws SQLException {

		Connection connection = ConnectionConfigurate.getConnection();

		String query = "INSERT INTO ator_atua_filme(idAtor,idFilme) VALUES(?,?)";
		PreparedStatement preparedStmt = connection.prepareStatement(query);

		for(Integer r : atores) {
			preparedStmt.setInt(1,r);
			preparedStmt.setInt(2,idFilme);
			preparedStmt.execute();
		}


	}
	public void createMusica(int idAutor,int idAutorLetra,int idMusica) throws SQLException {

		Connection connection = ConnectionConfigurate.getConnection();

		String query = "INSERT INTO musica(idAutor,idAutorLetra,idRecurso) VALUES(?,?,?)";
		PreparedStatement preparedStmt = connection.prepareStatement(query);

		preparedStmt.setInt(1,idAutor);
		preparedStmt.setInt(2,idAutorLetra);
		preparedStmt.setInt(3,idMusica);

		preparedStmt.execute();


	}

	public void createFotografia(int idFotografo,int idFoto) throws SQLException {

		Connection connection = ConnectionConfigurate.getConnection();

		String query = "INSERT INTO fotografia(idFotografo,idRecurso) VALUES(?,?)";
		PreparedStatement preparedStmt = connection.prepareStatement(query);

		preparedStmt.setInt(1,idFotografo);
		preparedStmt.setInt(2,idFoto);
		preparedStmt.execute();


	}

	public void createPoema(int idPoema) throws SQLException {

		Connection connection = ConnectionConfigurate.getConnection();

		String query = "INSERT INTO poema(idRecurso) VALUES(?)";
		PreparedStatement preparedStmt = connection.prepareStatement(query);

		preparedStmt.setInt(1,idPoema);

		preparedStmt.execute();


	}
	public void poeta_atua_poesia(ArrayList<Integer> poetas,int idPoema) throws SQLException {

		Connection connection = ConnectionConfigurate.getConnection();

		String query = "INSERT INTO poeta_atua_poesia(idPoeta,idPoema) VALUES(?,?)";
		PreparedStatement preparedStmt = connection.prepareStatement(query);

		for(Integer r : poetas) {
			preparedStmt.setInt(1,r);
			preparedStmt.setInt(2,idPoema);
			preparedStmt.execute();
		}

	}
	public void removerFilmes(ArrayList<Integer> array) throws SQLException {

		Connection connection = ConnectionConfigurate.getConnection();

		String query = "delete from sbd1.filme where idRecurso = ?";
		PreparedStatement preparedStmt = connection.prepareStatement(query);

		for(Integer r : array) {
			preparedStmt.setInt(1,r);
			preparedStmt.execute();
		}


	}

	public void removerAtor_atua_filme(ArrayList<Integer> array) throws SQLException {

		Connection connection = ConnectionConfigurate.getConnection();

		String query = "delete from sbd1.ator_atua_filme where idFilme = ?";
		PreparedStatement preparedStmt = connection.prepareStatement(query);

		for(Integer r : array) {
			preparedStmt.setInt(1,r);
			preparedStmt.execute();
		}


	}

	public void removerMusicas(ArrayList<Integer> array) throws SQLException {

		Connection connection = ConnectionConfigurate.getConnection();

		String query = "delete from sbd1.musica where idRecurso = ?";
		PreparedStatement preparedStmt = connection.prepareStatement(query);

		for(Integer r : array) {
			preparedStmt.setInt(1,r);
			preparedStmt.execute();
		}


	}

	public void removerFotografias(ArrayList<Integer> array) throws SQLException {

		Connection connection = ConnectionConfigurate.getConnection();

		String query = "delete from sbd1.fotografia where idRecurso = ?";
		PreparedStatement preparedStmt = connection.prepareStatement(query);

		for(Integer r : array) {
			preparedStmt.setInt(1,r);
			preparedStmt.execute();
		}


	}

	public void removerPoemas(ArrayList<Integer> array) throws SQLException {

		Connection connection = ConnectionConfigurate.getConnection();

		String query = "delete from sbd1.poema where idRecurso = ?";
		PreparedStatement preparedStmt = connection.prepareStatement(query);

		for(Integer r : array) {
			preparedStmt.setInt(1,r);
			preparedStmt.execute();
		}


	}

	public void removerPoeta_atua_poesia(ArrayList<Integer> array) throws SQLException {

		Connection connection = ConnectionConfigurate.getConnection();

		String query = "delete from sbd1.poeta_atua_poesia where idPoema = ?";
		PreparedStatement preparedStmt = connection.prepareStatement(query);

		for(Integer r : array) {
			preparedStmt.setInt(1,r);
			preparedStmt.execute();
		}


	}







	public static void main(String[] args) throws SQLException {
		CreateTables cr = new CreateTables();
		cr.createAssociation("4", "4");

	}

}
