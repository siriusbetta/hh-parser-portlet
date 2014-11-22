package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;



public class VacancyJDBC {
	Connection connection;
	PreparedStatement ps;
	ResultSet rs;
	static Logger log = Logger.getLogger(VacancyJDBC.class.getName());
	public VacancyJDBC(Connection con) {
		this.connection = con;
	}
	public void insert(Vacancy vacancy) throws SQLException{
		String sql = "insert into vacancy (name, employer, datacreation, salaryfrom, salaryto) "
				+ "values (?, ?, ?, ?, ?)";
		
		//Date data = new Date(vacancy.getDateCreatetion().getTime());
		ps = connection.prepareStatement(sql);
		ps.setString(1, vacancy.getName());
		ps.setString(2, vacancy.getEmployer());
		ps.setString(3, vacancy.getDateCreatetion());
		ps.setInt(4, vacancy.getSalaryFrom());
		ps.setInt(5, vacancy.getSalaryTo());
		ps.execute();
		log.info("Insert to database one item");
	}
	public void insert(List<Vacancy> vacancy) throws SQLException{
		String sql = "insert into vacancy (name, employer, datacreation, salaryfrom, salaryto) "
				+ "values (?, ?, ?, ?, ?)";
		ps = connection.prepareStatement(sql);
		for(Vacancy v: vacancy){
			ps.setString(1, v.getName());
			ps.setString(2, v.getEmployer());
			ps.setString(3, v.getDateCreatetion());
			ps.setInt(4, v.getSalaryFrom());
			ps.setInt(5, v.getSalaryTo());
			ps.execute();
		}
		log.info("Insert to database " + vacancy.size() + "items");
		
	}
	
	public ArrayList<Vacancy> getVacncy() throws SQLException, ParseException{
		String sql = "select * from vacancy limit 10";
		ArrayList<Vacancy> vacancyList = new ArrayList<Vacancy>();
		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()){
			vacancyList.add(new Vacancy(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));
		}
		log.info("Select from database items");
		return vacancyList;
	}
}
