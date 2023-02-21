package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DatabaseConnector;
import parameters.Parameters;

@WebServlet("/Manage_search_button")
public class SearchButton extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public SearchButton() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ler input do user

		DatabaseConnector dc = new DatabaseConnector();

		String userInput = request.getParameter("userInput");
		System.out.println("RECURSO ID");
		System.out.println(userInput);
		String type = dc.getTypeRecurso(userInput);
		System.out.println(userInput);

		if (type.equals(Parameters.filmType)) {
			doForwarding(request,  userInput,  response,"/filme.jsp");
			System.out.println("filme");
		}


		if (type.equals(Parameters.musicType)) {
			doForwarding(request,  userInput,  response,  Parameters.musicPath);
		}


		if (type.equals(Parameters.photoType)) {
			doForwarding(request,  userInput,  response,  Parameters.photoPath);
		}

		if (type.equals(Parameters.poemType)) {
			doForwarding(request,  userInput,  response,  Parameters.poemPath);


		}

	}

	private void doForwarding(HttpServletRequest request, String userInput, HttpServletResponse response, String path) {

		RequestDispatcher rd = request.getRequestDispatcher(path);
		request.setAttribute("idRecurso", userInput);
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