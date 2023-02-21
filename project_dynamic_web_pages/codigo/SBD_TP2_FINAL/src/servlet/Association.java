package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.CreateTables;
import database.DatabaseListings;
import parameters.Parameters;

@WebServlet("/Manage_association")
public class Association extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public Association() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		DatabaseListings dl = new DatabaseListings();
		CreateTables ct = new CreateTables();
		String idRecurso = request.getParameter("idRecurso");
		String type = request.getParameter("tipoRecurso");
		System.out.println("xd");
		System.out.println(idRecurso);
		String title = request.getParameter("titleRecurso");
		HashMap<Integer,String> dic = dl.getRecursoNameDic(title,idRecurso);
		ArrayList<String> keysSecundarias = new ArrayList<String>();

		Iterator hmIterator = dic.entrySet().iterator();
		while (hmIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) hmIterator.next();
			int ind = (int) mapElement.getKey();
			String KeyAs = String.valueOf(ind);
			System.out.println("xddd");
			System.out.println(KeyAs);
			String key = request.getParameter(KeyAs);
			System.out.println("xddd");
			System.out.println(key);

			if (key != null){

				keysSecundarias.add(key);
			}

		}
		
		for ( int i =0; i< keysSecundarias.size();i++) {
			System.out.println(keysSecundarias.get(i));
			ct.createAssociation(idRecurso, keysSecundarias.get(i));
		}
		
		
		if (type.equals(Parameters.filmType)) {
			doForwarding(request,  idRecurso,  response,  Parameters.filmPath);
			System.out.println("filme");
		}


		if (type.equals(Parameters.musicType)) {
			doForwarding(request,  idRecurso,  response,  Parameters.musicPath);
		}


		if (type.equals(Parameters.photoType)) {
			doForwarding(request,  idRecurso,  response,  Parameters.photoPath);
		}

		if (type.equals(Parameters.poemType)) {
			doForwarding(request,  idRecurso,  response,  Parameters.poemPath);


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


