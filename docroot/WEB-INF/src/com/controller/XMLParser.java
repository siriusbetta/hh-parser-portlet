package com.controller;

import com.database.Vacancy;

import com.jamesmurty.utils.XMLBuilder;

import java.util.ArrayList;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
public class XMLParser {
	public String createXML(ArrayList<Vacancy> vacancies) throws FactoryConfigurationError, ParserConfigurationException, TransformerException {
		XMLBuilder builder = XMLBuilder.create("vacancies");

		for (Vacancy i : vacancies) {
			//System.out.println(i.getName());
			builder.element("vacancy")
									.element("name").text(i.getName()).up()
									.element("employer").text(i.getEmployer()).up()
									.element("date").text(i.getDateCreatetion()).up()
									.element("from").text(String.valueOf(i.getSalaryFrom())).up()
									.element("to").text(String.valueOf(i.getSalaryTo())).up()
									.up();
		}

		builder.up();
		return builder.asString();
	}

	public String getPages(int id, int count) {
		int pages = Math.round(count / 10);
		String links = "<links>";
		links += "<link>";

		if (count % 10 != 0) {
			pages++;
		}

		if (pages <= 10) {
			for (int i = 1; i <= 10; i++) {
				//builder.e(String.valueOf(i)).t(String.valueOf(i)).up();
				links += "<" + i +">" + i + "</" + i +">";
			}

			links += "<link>";
			links += "</links>";
		}

		int limitUp = 0;
		int limitDown = 0;

		if (id <= 6) {
			limitUp = 6;
			limitDown = 1;
		}else {
			limitUp = id + 1;
			limitDown = limitUp - 6;
		}

		if (pages > 10) {
				links += "<one>" + limitDown + "</one>";
				links += "<2>" + (limitUp - 4) + "</2>";
				links += "<3>" + (limitUp - 3) + "</3>";
				links += "<4>" + (limitUp - 2) + "</4>";
				links += "<5>" + (limitUp - 1) + "</5>";
				links += "<6>" + limitUp + "</6>";
				links += "<7>" + "." + "</7>";
				links += "<8>" + "." + "</8>";
				links += "<9>" + "." + "</9>";
				links += "<10>" + pages + "</10>";
				links += "</link>";
				links += "</links>";
		}

		return links;
	}
}