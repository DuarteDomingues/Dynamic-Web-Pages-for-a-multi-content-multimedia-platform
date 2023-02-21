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

@WebServlet("/Manage_comment")
public class Comment extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public Comment() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		CreateTables ct = new CreateTables();
		
		String userComentador = request.getParameter("userComentador");
		String currentDate = request.getParameter("currentData");
		String recursoId = request.getParameter("recursoId");
		String recursoTipo = request.getParameter("recursoTipo");
		String comment = request.getParameter("commentText");

		String classification = request.getParameter("classification");
		System.out.println("xdddddddddddddddddddddddddddddddddddddddddddddddd");
		System.out.println(userComentador);
		System.out.println(currentDate);
		System.out.println(recursoId);
		System.out.println(recursoTipo);
		System.out.println(comment);
		if (classification!=null ) {

			ct.createClassificacao(recursoId, classification, userComentador);
			forwarding ( request,  recursoId, response, recursoTipo);
		}
		else {

			ct.createComment( recursoId, comment, userComentador, currentDate);
			forwarding (  request,  recursoId,  response, recursoTipo);


		}
	}



	private void doForwarding(HttpServletRequest request, String recursoId,HttpServletResponse response, String path) {

		RequestDispatcher rd = request.getRequestDispatcher(path);
		request.setAttribute("idRecurso", recursoId);

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
	private void forwarding (HttpServletRequest request, String recursoId,HttpServletResponse response, String recursoTipo) {

		if( recursoTipo.equals(Parameters.filmType)){
			doForwarding(request,recursoId,response,Parameters.filmPath);

		}
		if( recursoTipo.equals(Parameters.musicType)){
			doForwarding(request,recursoId,response,Parameters.musicPath);

		}

		if( recursoTipo.equals(Parameters.poemType)){
			doForwarding(request,recursoId,response,Parameters.poemPath);

		}

		if( recursoTipo.equals(Parameters.photoType)){
			doForwarding(request,recursoId,response,Parameters.photoPath);

		}
	}












}