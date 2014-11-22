package com.portlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.controller.Controller;
import com.jamesmurty.utils.XMLBuilder;


public class AjaxController extends HttpServlet{
	Controller controller;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		controller = new Controller();
		
		String page = request.getParameter("page");
		String money = request.getParameter("money");
		boolean orderByMoney = false;
		if(money.equals("By money")){
			orderByMoney = true;
		}
		
		String date = request.getParameter("date");
		boolean orderByDate = false;
		if(date.equals("By date")){
			orderByDate = true;
		}
		
		int pageNumber = Integer.parseInt(page);
		int pageLimitStart = pageNumber;
		int pageLimitEnd = pageNumber + 10;
		String xml = controller.getVacanciesDatabase(pageLimitStart,
				pageLimitEnd, orderByDate, orderByMoney);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//response.setCharacterEncoding("UTF-8");
		//System.out.println(xml);
		out.println(xml);
	}
	
	public String getPages(int id, int count){
		int pages = Math.round(count / 10);
		XMLBuilder builder = null;;
		try {
			builder = XMLBuilder.create("links");
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FactoryConfigurationError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String links = "";
		if(count % 10 != 0){
			pages++;
		}
		if(pages <= 10){
			for(int i = 1; i <= 10; i++){
				builder.e(String.valueOf(i)).t(String.valueOf(i)).up();
			}
		}
		int limitUp = 0;
		int limitDown = 0;
		if(id <= 6){
			limitUp = 6;
			limitDown = 0;
		}else{
			limitUp = id + 1;
			limitDown = limitUp - 6;
		}
		if(pages > 10){
//			for(int i = limitDown; i <limitUp; i++){
//				builder.e(String.valueOf(i)).t(String.valueOf(i)).up();
//			}
			builder.e(String.valueOf(1)).t(String.valueOf(limitDown)).up();
			builder.e(String.valueOf(2)).t(String.valueOf(limitUp - 4)).up();
			builder.e(String.valueOf(3)).t(String.valueOf(limitUp - 3)).up();
			builder.e(String.valueOf(4)).t(String.valueOf(limitUp - 2)).up();
			builder.e(String.valueOf(5)).t(String.valueOf(limitUp - 1)).up();
			builder.e(String.valueOf(6)).t(String.valueOf(limitUp)).up();
			builder.e(String.valueOf(7)).t(".").up();
			builder.e(String.valueOf(8)).t(".").up();
			builder.e(String.valueOf(9)).t(".").up();
			builder.e(String.valueOf(10)).t(String.valueOf(pages)).up();
		}
		try {
			links = builder.asString();
		} catch (TransformerException e) {
			
		}
		return links;
		
	}
	
}
