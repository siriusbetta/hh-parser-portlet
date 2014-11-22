package com.portlets;


import java.io.IOException;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.apache.log4j.Logger;

import com.controller.Controller;


//import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class HelloWorld
 */
public class HHPortlet extends GenericPortlet{
	static Logger log = Logger.getLogger(HHPortlet.class.getName());
	
	@ProcessAction(name = "registerUserAction")
	public void registerUser(RenderRequest request, RenderResponse respond) throws IOException, 
	PortletException{
		
	}
	
	@RenderMode (name = "VIEW")
	public void renderForm(RenderRequest request, RenderResponse response){
		//Controller controller = new Controller();
		
		try {
			getPortletContext().getRequestDispatcher("/WEB-INF/jsp/vacancies.jsp").include(request, response);
		} catch (PortletException pe) {
			pe.printStackTrace();
			log.error("Portlet exception: ", pe);
		}catch(IOException ioe){
			ioe.printStackTrace();
			log.error("IO Exception: ", ioe);
		}
	}
	
	@RenderMode (name = "EDIT")
	public void renderPref(RenderRequest request, RenderResponse response) throws IOException{
		
	}
	
	@RenderMode (name = "HELP")
	public void renderHelp(RenderRequest request, RenderResponse response) throws IOException{
		
	}
	
	
}
