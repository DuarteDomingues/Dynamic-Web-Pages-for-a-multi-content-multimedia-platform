package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parameters.Parameters;



@WebServlet("/Smart_search")
public class SmartSearch extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public SmartSearch() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ler input do user

		String userInput = request.getParameter("nameInput");
		doForwarding(request,userInput,response,"");



	}

	private void doForwarding(HttpServletRequest request, String userInput,HttpServletResponse response, String path) {

		RequestDispatcher rd = request.getRequestDispatcher(Parameters.searchesPaths);
		request.setAttribute("userInput", userInput);
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
