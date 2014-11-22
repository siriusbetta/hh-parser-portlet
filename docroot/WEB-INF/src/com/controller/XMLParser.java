package com.controller;

import java.util.ArrayList;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.database.Vacancy;
import com.jamesmurty.utils.XMLBuilder;

public class XMLParser {
	public String createXML(ArrayList<Vacancy> vacancies) throws ParserConfigurationException, FactoryConfigurationError, TransformerException{
		XMLBuilder builder = XMLBuilder.create("vacancies");
		for(Vacancy i : vacancies){
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
}
