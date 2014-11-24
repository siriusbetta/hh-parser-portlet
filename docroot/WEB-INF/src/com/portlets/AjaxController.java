package com.portlets;

import com.controller.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
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
		System.out.println(page);
		System.out.println(orderBy);
		//System.out.println(desk);
		int pageNumber = Integer.parseInt(page);
		int pageLimitStart = pageNumber;
		int pageLimitEnd = pageNumber + 10;
		String xml = controller.getVacanciesDatabase(pageLimitStart,
				pageLimitEnd, orderBy, descBool);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//response.setCharacterEncoding("UTF-8");
		//System.out.println(page);
		//ServletContext sc = getServletContext();
		//int count = controller.getVacanciesCount();
		//String links = getPages(pageLimitStart, count);
		//request.setAttribute("links", getPages(pageLimitStart, count));
		//RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/jsp/vacancies.jsp");
		//rd.forward(request, response);
		//System.out.println(xml + links);
		out.println(xml);
	}

}