package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.CreateTables;
import parameters.Parameters;

@WebServlet("/Manage_Artista")
public class Artista extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public Artista() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {


		String idArtista = request.getParameter("idArtista");
		System.out.println("xd");
		System.out.println(idArtista);
		doForwarding(request,idArtista,response,Parameters.artistaPath);


	}

	private void doForwarding(HttpServletRequest request, String idArtista,HttpServletResponse response, String path) {

		RequestDispatcher rd = request.getRequestDispatcher(path);
		request.setAttribute("idArtista", idArtista);

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

}












