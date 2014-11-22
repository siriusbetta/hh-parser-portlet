package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;

import com.database.MySQLConnection;
import com.database.Vacancy;
import com.database.VacancyJDBC;
import com.liferay.util.portlet.PortletProps;


public class Controller {
	static Logger log = Logger.getLogger(Controller.class.getName());
	private MySQLConnection mySqlconnection = null;
	private Connection connection = null;
	VacancyJDBC	vd = null;
	XMLParser xmlParser = null;
	public Controller(){
		//System.out.println("Contrller");
		String dbURL = PortletProps.get("url");
		String dbUser = PortletProps.get("user");
		String dbPassword = PortletProps.get("pass");
		
		xmlParser = new XMLParser();
		try {
			mySqlconnection = new MySQLConnection(dbURL, dbUser, dbPassword);
			connection = mySqlconnection.getConnection();
			vd = new VacancyJDBC(connection);
		} catch (ClassNotFoundException cne) {
			log.error("Class not found exception in SQL connection: ", cne);
		} catch (SQLException sqle) {
			log.error("SQL exception in SQL connection: ", sqle);
		}
		
		
	}
	
	public void getAndWriteVacancies(){
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
		
		//VacancyJDBC	vd = new VacancyJDBC(connection);
		try {
			vd.insert(vacancyList);
		} catch (SQLException sqle) {
			log.error("Tryed to insert data to database. SQL exception: ", sqle);
		}
	}
	
	public String getVacanciesDatabase(int limitFrom, int limitTo, boolean orderByData,
			boolean orderByMoney){
		ArrayList<Vacancy> vacancyList = new ArrayList<Vacancy>();
		String xml = "";
		try {
			vacancyList = vd.getVacncy(limitFrom, limitTo, orderByData, orderByMoney);
		} catch (SQLException e) {
			log.error("SQL exception. Trying to get data from database with ordering", e);
		} catch (ParseException e) {
			log.error("Parsing exception. Trying to get data from database with ordering", e);
		}
		try {
			xml = xmlParser.createXML(vacancyList);
		} catch (ParserConfigurationException e) {
			log.error("ParserConfigurationException. Trying to get data from database with ordering", e);
		} catch (FactoryConfigurationError e) {
			log.error("FactoryConfigurationException. Trying to get data from database with ordering", e);
		} catch (TransformerException e) {
			log.error("TransformerException. Trying to get data from database with ordering", e);
		}
		return xml;
	}
}
