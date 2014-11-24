package com.database;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
public class Vacancy {
	public Vacancy() {
		name = "разраб Java";
		employer = "any";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		dateCreatetion = sdf.format(new Date());
	}

	public Vacancy(String name, String employer, String date, int salaryFrom, int salaryTo) throws ParseException {
		this.name = name;
		this.employer = employer;
		Date da = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(date);

		SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		dateCreatetion = sdm.format(da);
		this.salaryFrom = salaryFrom;
		this.salaryTo = salaryTo;
	}

	public String getDateCreatetion() {
		return dateCreatetion;
	}

	public String getEmployer() {
		return employer;
	}

	public String getName() {
		return name;
	}

	public int getSalaryFrom() {
		return salaryFrom;
	}

	public int getSalaryTo() {
		return salaryTo;
	}

	public void setDateCreatetion(String dateCreatetion) {
		this.dateCreatetion = dateCreatetion;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSalaryFrom(int salaryFrom) {
		this.salaryFrom = salaryFrom;
	}

	public void setSalaryTo(int salaryTo) {
		this.salaryTo = salaryTo;
	} private int salaryFrom;

	private String dateCreatetion;
	private String employer;
	private String name;
	private int salaryTo;

}