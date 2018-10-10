package org.moreno.wolak.project.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/bar_app_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1Bladerz1";
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	
	public static Connection getConnection() {
		
		try {
			Class.forName(JDBC_DRIVER);
			return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("FAILED: inside constructor BarsRepository()");
			throw new RuntimeException("Error connection to the database.");
		}
	}

}
