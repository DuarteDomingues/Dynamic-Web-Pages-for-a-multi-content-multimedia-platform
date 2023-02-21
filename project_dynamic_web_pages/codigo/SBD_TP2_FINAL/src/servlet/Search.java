package servlet;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseConnector;
import parameters.Parameters;

@WebServlet("/Manage_search")
public class Search extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public Search() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ler username e password do form
		String type = request.getParameter("type");
		String title = request.getParameter("title");
		System.out.println(type);
		System.out.println(title);
		DatabaseConnector db = new DatabaseConnector();
		
		if (db.checkTypeTitulo(type, title)) {

			if (type.equals(Parameters.filmType)) {
				doForwarding( request,  title,  type, response,Parameters.filmPath);
			}

			
			if (type.equals(Parameters.musicType)) {
				doForwarding( request,  title,  type, response,Parameters.musicPath);
			}

			
			if (type.equals(Parameters.photoType)) {
				doForwarding( request,  title,  type, response,Parameters.photoPath);
			}

			if (type.equals(Parameters.poemType)) {
				doForwarding( request,  title,  type, response,Parameters.poemPath);

			}

		}

		else{ 

			System.out.println("CONTEUDO NAO EXISTE");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(request.getContextPath() + "/index.jsp" );
			dispatcher.forward(request, response);

		}  
	}

	private void doForwarding(HttpServletRequest request, String title, String type,HttpServletResponse response, String path) {

		RequestDispatcher rd = request.getRequestDispatcher(path);
		request.setAttribute("titulo", title);
		request.setAttribute("type", type);
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
