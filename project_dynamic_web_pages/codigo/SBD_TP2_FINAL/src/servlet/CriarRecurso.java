package servlet;

import java.io.IOException;
import java.sql.Blob;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import database.DatabaseConnector;
import parameters.Parameters;

@WebServlet("/Manage_CriarRecurso")
public class CriarRecurso extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public String tipo;
	Blob ola;

	public CriarRecurso() {
		super();
	
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ler input do user

		String tipo = (String) request.getSession().getAttribute("tipo");
		
		
		if (tipo.equals(Parameters.filmType)) {
			doForwarding(request,  response,  Parameters.criarFilmPath);
			
			
		}

		if (tipo.equals(Parameters.musicType)) {
			doForwarding(request,  response,  Parameters.criarMusicPath);
		}


		if (tipo.equals(Parameters.poemType)) {
			doForwarding(request,  response,  Parameters.criarPoemPath);
		}

		if (tipo.equals(Parameters.photoType)) {
			doForwarding(request,  response,  Parameters.criarPhotoPath);
		}
	}

	private void doForwarding(HttpServletRequest request, HttpServletResponse response, String path) {

		RequestDispatcher rd = request.getRequestDispatcher(path);
		
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void setType(String tipo) {
		this.tipo=tipo;
		
		System.out.println("tipo: "+tipo);
	}
}