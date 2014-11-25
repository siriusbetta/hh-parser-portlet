package com.portlets;

import com.controller.Controller;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class AjaxController extends HttpServlet {
	Controller controller = null;
	public void init(){
		controller = new Controller();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		

		String page = request.getParameter("page");
		String orderBy = request.getParameter("sort");
		String desc = request.getParameter("desk");
		boolean descBool = false;
		if(desc.equals("desc")){
			descBool = true;
		}
		
		int pageNumber = Integer.parseInt(page);
		int pageLimitStart = pageNumber;
		int pageLimitEnd = pageNumber + 10;
		String xml = controller.getVacanciesDatabase(pageLimitStart,
				pageLimitEnd, orderBy, descBool);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(xml);
	}

}