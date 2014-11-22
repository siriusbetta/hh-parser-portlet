package com.portlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.Controller;


public class AjaxController extends HttpServlet{
	Controller controller;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		controller = new Controller();
		PrintWriter out = response.getWriter();
		String page = request.getParameter("page");
		String money = request.getParameter("money");
		boolean orderByMoney = false;
		if(money.equals("By money")){
			orderByMoney = true;
		}
		
		String date = request.getParameter("date");
		boolean orderByDate = false;
		if(date.equals("date")){
			orderByDate = true;
		}
		
		int pageNumber = Integer.parseInt(page);
		int pageLimitStart = pageNumber;
		int pageLimitEnd = pageNumber + 10;
		String xml = controller.getVacanciesDatabase(pageLimitStart,
				pageLimitEnd, orderByDate, orderByMoney);
		out.println(page);
	}
	
}
