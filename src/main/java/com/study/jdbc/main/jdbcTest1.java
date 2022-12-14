package main.java.com.study.jdbc.main;

import java.sql.Connection;

import com.mysql.cj.jdbc.Driver;

import main.java.com.study.jdbc.util.DBConnection;

public class jdbcTest1 {

	public static void main(String[] args) {
		Connection connection = DBConnection.getInstance().getConnection();
		
		System.out.println(connection);//주소가 들어오면 객체 생성
	}

}
