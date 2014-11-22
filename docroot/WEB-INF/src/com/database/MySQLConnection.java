package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;


public class MySQLConnection {
	private Connection connection;
	/***
	 * Конструктор, создает соединение
	 * @param url путь к серверу
	 * @param user имя пользовователя
	 * @param pass пароль
	 * @throws ClassNotFoundException выбрасывает исключение если файл не найдет
	 * @throws SQLException выбрасывает SQL исключение 
	 */
	static Logger log = Logger.getLogger(MySQLConnection.class.getName());
	public MySQLConnection(String url, String user, String pass) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection(url, user, pass);
		log.info("Database connection success");
	}
	
	public Connection getConnection(){
		return this.connection;
	}
	
	public void closeConnection(){
		log.info("Database connection closed!");
		try {
			this.connection.close();
		} catch (SQLException e) {
			log.error("SQL Exception. Trying to close MySQL connection.", e);
		}
	}

}
