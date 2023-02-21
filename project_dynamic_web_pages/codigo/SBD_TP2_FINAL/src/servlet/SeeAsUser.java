package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseConnector;
import database.UpdateTables;

@WebServlet("/Manage_see_user")
public class SeeAsUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public SeeAsUser() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ler input do user

		String userId = request.getParameter("userId");
		String userIdcorrente = request.getParameter("userIdcorrente");
		DatabaseConnector dc = new DatabaseConnector();
		
		int xd = dc.checkType(userId);
		String xds = String.valueOf(xd);
		
		// 	session.setAttribute("idUser", idUser);
		// 	session.setAttribute("LogedInUserType", stUserType);

		HttpSession session = request.getSession(false);
		
		session.removeAttribute("idUser");
		session.removeAttribute("LogedInUserType");
		
		
		
		session.setAttribute("idUser", userId);
		session.setAttribute("LogedInUserType", xds);
		
		
		
		
		
		response.sendRedirect(request.getContextPath() + "/recursos.jsp");
		



	}


}
