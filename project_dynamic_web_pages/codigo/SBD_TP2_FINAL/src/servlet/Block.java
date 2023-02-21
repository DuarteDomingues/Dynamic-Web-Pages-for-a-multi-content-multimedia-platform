package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UpdateTables;
import parameters.Parameters;

@WebServlet("/Manage_block")
public class Block extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Block() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ler input do user

		String userId = request.getParameter("userId");
		String val = request.getParameter("val");
		UpdateTables ut = new UpdateTables();
		ut.UpdateUser(userId,val);
		response.sendRedirect(request.getContextPath() + "/users.jsp");
		



	}


}
