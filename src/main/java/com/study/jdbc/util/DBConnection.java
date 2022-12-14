package main.java.com.study.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DBConnection {

		private static DBConnection instance = null;
		
		private DBConnection() {}
		
		public static DBConnection getInstance() {
			if(instance == null) {
				instance = new DBConnection();
			}
			return instance;
		}
		
		public Connection getConnection() {
			Connection connection = null;
			String url = null;
			String username = null;
			String password = null;
			try {
				Class.forName(Driver.class.getName());//클래스를 찾아서 객체 생성. com.mysql.cj.jdbc.Driver를 가져온다.
				System.out.println("데이터베이스 드라이브 로딩 성공!");
				url = "jdbc:mysql://localhost:3306/subquery_study";
				username = "root";
				password = "root";
				connection = DriverManager.getConnection(url, username, password);//add catch
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("드라이버 로딩 실패!");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("데이터베이스 연결 실패!");//url잘못됐거나 username, password가 틀린 경우
			}
			return connection;
		}
}
