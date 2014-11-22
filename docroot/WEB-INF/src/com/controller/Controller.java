package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.database.MySQLConnection;
import com.database.Vacancy;
import com.database.VacancyJDBC;
import com.liferay.util.portlet.PortletProps;


public class Controller {
	static Logger log = Logger.getLogger(Controller.class.getName());
	public Controller(){
		System.out.println("Contrller");
		ArrayList<Vacancy> vacanciesList = getVacancies();
		writeToDatabase(vacanciesList);
	}
	
	private ArrayList<Vacancy> getVacancies(){
		ArrayList<Vacancy> vacancyList = new ArrayList<Vacancy>();
		Parser parser = new Parser();
		try {
			vacancyList = parser.getVacancies();
		} catch (IOException ioe) {
			log.error("IO Exception: ", ioe);
		} catch(ParseException pe){
			log.error("Parse exception. Throwed from Parser", pe);
		}
		if(vacancyList.size()!=0){
			log.info("Recived data from site");
		}
		return vacancyList;
	}
	private void writeToDatabase(ArrayList<Vacancy> vacancyList){
		String dbURL = PortletProps.get("url");
		String dbUser = PortletProps.get("user");
		String dbPassword = PortletProps.get("pass");
		Connection connection = null;
		
		try {
			connection = new MySQLConnection(dbURL, dbUser, dbPassword).getConnection();
		} catch (ClassNotFoundException cne) {
			log.error("Class not found exception in SQL connection: ", cne);
		} catch (SQLException sqle) {
			log.error("SQL exception in SQL connection: ", sqle);
		}
		VacancyJDBC	vd = new VacancyJDBC(connection);
		try {
			vd.insert(vacancyList);
		} catch (SQLException sqle) {
			log.error("Tryed to insert data to database. SQL exception: ", sqle);
		}
	}
}
