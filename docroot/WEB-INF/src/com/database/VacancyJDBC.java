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

	public void insert(Vacancy vacancy) throws SQLException {
		String sql = "insert into vacancy (name, employer, datacreation, salaryfrom, salaryto) " +
				"values (?, ?, ?, ?, ?)";

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

	public void insert(List<Vacancy> vacancy) throws SQLException {
		String sql = "insert into vacancy (id, name, employer, datacreation, salaryfrom, salaryto) " +
				"values (0, ?, ?, ?, ?, ?)";
		ps = connection.prepareStatement(sql);

		for (Vacancy v : vacancy) {
			ps.setString(1, v.getName());
			ps.setString(2, v.getEmployer());
			ps.setString(3, v.getDateCreatetion());
			ps.setInt(4, v.getSalaryFrom());
			ps.setInt(5, v.getSalaryTo());
			ps.execute();
		}

		log.info("Insert to database " + vacancy.size() + "items");
	}

	public ArrayList<Vacancy> getVacncy(int pageLimitStart, int pageLimitEnd, String orderBy, boolean desk) throws ParseException, SQLException {
		String desc = "";
		String sql = "";
		String orderBy2 = "";
		//System.out.println(orderBy);
		if(desk){
			desc = "desc";
		}
		if(orderBy.equals("money")){
			sql = "select * from vacancy order by salaryfrom, salaryto limit ?, ?";
			ps = connection.prepareStatement(sql);
			log.info("Select from database items order by by money");
		}
		
		if(orderBy.equals("money") && desk){
			sql = "select * from vacancy order by salaryfrom desc, salaryto desc limit ?, ?";
			ps = connection.prepareStatement(sql);
			log.info("Select from database items order by money and desc");

		}
		
		if(orderBy.equals("datacreation")){
			sql = "select * from vacancy order by datacreation limit ?, ?";
			ps = connection.prepareStatement(sql);
			log.info("Select from database items order by date");
		}
		
		if(orderBy.equals("datacreation") && desk){
			sql = "select * from vacancy order by datacreation desc limit ?, ?";
			ps = connection.prepareStatement(sql);
			log.info("Select from database items order by date and desc");
		}

		if(orderBy.equals("money&date")){
			sql = "select * from vacancy order by datacreation, salaryfrom, salaryto limit ?, ?";
			ps = connection.prepareStatement(sql);
			log.info("Select from database items order by date and money");
		}
		
		if(orderBy.equals("money&date") && desk){
			sql = "select * from vacancy order by datacreation desc, salaryfrom desc, salaryto desc limit ?, ?";
			ps = connection.prepareStatement(sql);
			log.info("Select from database items order by date and money with desc");

		}
		
		ArrayList<Vacancy> vacancyList = new ArrayList<Vacancy>();
		ps.setInt(1, pageLimitStart);
		ps.setInt(2, pageLimitEnd);
		rs = ps.executeQuery();
		while (rs.next()) {
			vacancyList.add(new Vacancy(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));
		}
		return vacancyList;
	}

	public int getCount() throws SQLException {
		String sql = "select count(name) from vacancy";
		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		return count;
	}
}