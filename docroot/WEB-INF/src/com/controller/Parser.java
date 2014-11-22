package com.controller;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import com.database.Vacancy;
public class Parser {
	static Logger log = Logger.getLogger(Parser.class.getName());
	public ArrayList<Vacancy> getVacancies() throws IOException, ParseException{
		ArrayList<Vacancy> vacancyList = new ArrayList<Vacancy>();
		String url = "https://api.hh.ru/vacancies/?area=4&text=&specialization=1";
		String doc = "";
		Connection.Response response =Jsoup.connect(url).ignoreContentType(true).timeout(10000).execute();
		if(response.statusCode() == 200){
			log.info("Connection succesful");
			doc = response.body();
		}else{
			log.error("No connection: status -" + response.statusCode());
		}
	
		JSONObject obj = null;
		try {
			obj = new JSONObject(doc);
		} catch (JSONException e2) {
			log.error("JSON exception: ", e2);
		}


		int num = 4;
//		try {
//			num = obj.getInt("pages");
//		} catch (JSONException e1) {
//			
//			e1.printStackTrace();
//		} 
		for(int j = 0; j < num; j++){
			response =Jsoup.connect(url + "&page=" + j).ignoreContentType(true).timeout(10000).execute();
			if(response.statusCode() == 200){
				log.info("Connection succesful");
				doc = response.body();
			}else{
				log.error("No connection: status -" + response.statusCode());
			}

		try {
			obj = new JSONObject(doc);
			JSONArray arr = (JSONArray) obj.get("items");
			
			for(int i = 0; i < arr.length(); i++){
				JSONObject obj1 = (JSONObject) arr.get(i);
				String nameVac = (String) obj1.get("name");
				String dateCre = (String) obj1.get("created_at");
				int to = 0;
				int from = 0;		
				if(!obj1.get("salary").equals(null)){
					JSONObject obj23 = (JSONObject) obj1.get("salary");
					try{
						from = Integer.parseInt(obj23.getString("from"));
						to = Integer.parseInt(obj23.getString("to"));
					}catch(NumberFormatException ne){
						log.error("A symbol wrong, have to be a gigit: ", ne);
					}
				}
				JSONObject obj2 = (JSONObject) obj1.get("employer");
				String employer = (String) obj2.get("name");
				
				Vacancy vacancy = new Vacancy(nameVac, employer, dateCre.substring(0, 10) +" "+ dateCre.substring(11, 16) , from, to);
				vacancyList.add(vacancy);
			}
		} catch (JSONException je) {
			log.error("JSON exception: ", je);
		}
		}
				
				return vacancyList;
			
	}

}
