package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.CreateTables;
import database.DatabaseConnector;

@WebServlet("/Manage_create_account")
public class CreateAccount extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public CreateAccount() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("uName");
		String password = request.getParameter("newPassword");
		String nationality = request.getParameter("nationality");
		String birthDate = request.getParameter("birthDate");
		DatabaseConnector db = new DatabaseConnector();
		CreateTables ct = new CreateTables();

		boolean userNameExists = db.checkUserNameExists(username);
		
		System.out.println(userNameExists);

		HttpSession session = request.getSession(false);

		session.setAttribute("userNameAgain", "");

		if (userNameExists==false) {
			
			ct.createUser(username, password, nationality,birthDate);
			
			int idUser = db.getIdByUserName(username);
			
			String idUserS = String.valueOf(idUser);
			
			session.setAttribute("idUser", idUserS);
			session.setAttribute("LogedInUserType", "1");
			response.sendRedirect(request.getContextPath() + "/recursos.jsp");



		}

		else {

		request.getSession().setAttribute("userNameAgain", "User Name already exists");
		response.sendRedirect(request.getContextPath() + "/create_account.jsp");



		}


	}





}