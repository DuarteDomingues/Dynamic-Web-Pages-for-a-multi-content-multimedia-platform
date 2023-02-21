package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UpdateTables;
import parameters.Parameters;

@WebServlet("/Manage_blockContent")
public class BlockContent extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public BlockContent() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ler input do user

		String estadoRecurso = request.getParameter("estadoRecurso");
		String idRecurso = request.getParameter("idRecurso");
		String recursoTipo = request.getParameter("recursoTipo");
		String idUser = request.getParameter("idUser");
		String reputacao = request.getParameter("reputacao");
		System.out.println("estado: "+estadoRecurso);
		System.out.println("id recurso:"+idRecurso);
		System.out.println("recursoTipo:"+recursoTipo);
		System.out.println("idUser:"+idUser);
		System.out.println("reputacao:"+reputacao);
		
		UpdateTables ut = new UpdateTables();
		try {
			ut.updateRecursoEstado(Boolean.parseBoolean(estadoRecurso), Integer.valueOf(idRecurso));
			ut.updateUserRep( Integer.valueOf(reputacao), (idUser));
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//response.sendRedirect(request.getContextPath() + "/filme.jsp");
		
		
		
		forwarding ( request,  idRecurso, response, recursoTipo);


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
