package servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseConnector;

@WebServlet("/Manage_login")
public class Login extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// ler username e password do form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DatabaseConnector db = new DatabaseConnector();
		HttpSession session = request.getSession(false);
		
		session.setAttribute("idUser", "");
		session.setAttribute("tryAgain", "");
		session.setAttribute("LogedInUserType", "");
		
		int userType = (db.checkType(username, password));
		
		if (userType!=0) {

			int id = db.gedId(username, password);
			
			String idUser = String.valueOf(id);
			String stUserType = String.valueOf(userType);
			
			session.setAttribute("idUser", idUser);
			session.setAttribute("LogedInUserType", stUserType);
			response.sendRedirect(request.getContextPath() + "/recursos.jsp");
			
			
		}
		else{ 
			session.setAttribute("tryAgain", "Unknow User");
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			
			
		}  


	}


}
