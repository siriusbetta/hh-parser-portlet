package com.portlets;

import com.controller.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class LinksAjaxController extends HttpServlet {
	Controller controller = null;
	public void init(){
		controller = new Controller();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		System.out.println("asdf");
		int count = controller.getVacanciesCount();
		response.setContentType("text/html; charset=UTF-8");
		int pages = 0;

		if (count % 10 != 0) {
			pages = (count /10) + 1;
		}else {
			pages = count / 10;
		}
		System.out.println(count);
		System.out.println(pages);
		out.println(pages);
	}

}