package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;

import parameters.Parameters;

public class YearHandler {

	public java.sql.Date getDaysUser(String idUser) {

		String query = "SELECT dataNascimento from sbd1.user where idUser = '"+idUser+"'";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(query);
		java.sql.Date dbSqlDate = null;

		if (rs!=null) {
			try {
				while (rs.next()) {

					dbSqlDate = rs.getDate("dataNascimento");

				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}
		return dbSqlDate;
	}


	public  long yearsToDays(String dateBeforeString) {

		LocalDate now = LocalDate.now();
		//Parsing the date
		LocalDate dateBefore = LocalDate.parse(dateBeforeString);
		//LocalDate dateAfter = LocalDate.parse(dateAfterString);
		//calculating number of days in between
		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, now);
		return noOfDaysBetween;
	}   


	public ArrayList<Long> ageUsers(){

		ArrayList<Long> recursosAges = new ArrayList<Long>();


		String queryComment = "select dataNascimento from sbd1.user;";
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.makeStatement(queryComment);

		if (rs != null) {
			try {
				//NAO VOU FAZER AS BLOBS AGORA, DEPOIS NAO ESQUECER DE ADICIONAR AS BLOBS
				while (rs.next()) {
					java.sql.Date dateuser = rs.getDate("dataNascimento");
					String yearss = String.valueOf(dateuser);
					long xd = yearsToDays(yearss);
					recursosAges.add(xd);



				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return recursosAges;

	}



	public boolean canAccesContent(long userAge,String userAgeType) {

		if (userAgeType.equals(Parameters.G)) {

			return true;

		}

		if (userAgeType.equals(Parameters.PG)) {

			if (userAge <3652.421) { //10 years in days
				return false;
			}

			else {
				return true;
			}
		}

		if (userAgeType.equals(Parameters.PG13)) {

			if (userAge <4748.148) { //13 in days
				return false;
			}
			else {
				return true;
			}

		}
		if ( userAgeType.equals(Parameters.R)) {
			
			if (userAge <5843.87) { //16 in days
				return false;
			}
			else {
				return true;
			}
		}
		
			if ( userAgeType.equals(Parameters.NC17)) {
			
			if (userAge <6574.359) { //18 in days
				return false;
			}
				else {
				return true;
			}
		}
			return false;

	}

	
	
	


	/*
	  		18 6574.359582000001
			16 5843.875184
			13 4748.148587000001
			6 2191.453194
	 */





	public static void main(String[] args) {
			/*
		YearHandler yh = new YearHandler();

		ArrayList<Long> ageUsers = yh.ageUsers();
		
		boolean xd = yh.canAccesContent(6000,Parameters.R);
		
		System.out.println(xd);

*/
	}
}

