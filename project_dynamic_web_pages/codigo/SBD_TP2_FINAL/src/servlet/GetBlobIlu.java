package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DatabaseConnector;
import database.DatabaseListings;


@WebServlet("/BlobIlu")
public class GetBlobIlu extends HttpServlet{
	
	private static String id;
	private static final long serialVersionUID = 1L;


	protected void doGet (HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Blob image = null;
		byte[ ] imgData = null ;
		ServletOutputStream sos=null;
		InputStream input=null;
		
		//DatabaseListings dbl = new DatabaseListings();
		//String id=dbl.getTypeName().get(1);
		
		//String id = (String) request.getAttribute("idR");
	    //request.removeAttribute("idR");
		
		String idR = request.getParameter("recursoId");

	    
	
		System.out.println("idRecurso do get get blob: "+idR);
		String queryCommentContent = "select ilustracao from sbd1.recurso_multimedia where idRecurso = "+idR; 
		DatabaseConnector db = new DatabaseConnector();
		
		ResultSet rs2 = db.makeStatement(queryCommentContent);
		
		
		try {
			if (rs2.next()) {

				image = rs2.getBlob(1);
				imgData = image.getBytes(1,(int)image.length());
				System.out.println(imgData.length);
				} 
			
			else {

				System.out.println("Display Blob Example");

				System.out.println("image not found for given id>");


				}
				//input = rs2.getBinaryStream("ilustracao");
				response.setContentType("image/gif");
				
				sos = response.getOutputStream();
				
				sos.write(imgData);
				
				sos.flush();
				
				sos.close();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	public void setIdRecurso(String id) {
		System.out.println("idRecurso afetado get blob: "+id);
		this.id=id;
		
	}

}
