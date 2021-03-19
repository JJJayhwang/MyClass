package com.java.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
//			오라클 버전에 따라서 sid가 다르다 xe가 붙는지 g가 붙는지 orcl인지.. 등등...
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";    
			String id = "mvc";
			String pass = "mvc";
			
			conn = DriverManager.getConnection(url, id, pass);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
}
