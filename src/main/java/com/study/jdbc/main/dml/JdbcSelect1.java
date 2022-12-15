package main.java.com.study.jdbc.main.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;


import main.java.com.study.jdbc.util.DBConnection;

public class JdbcSelect1 {

	public static void main(String[] args) {
		Connection connection = DBConnection.getInstance().getConnection();//싱글톤으로 getInstance안의 getConnection() 데려와서 connection 반환
		
		//System.out.println(connection);//주소가 들어오면 객체 생성
		
		String sql = "select * from score_mst";//쿼리문을 여기다 작성
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);//PreparedStatement 쿼리 쓰는 공간, 
			//connection객체에 PrepareStatement를 만드는 메소드가 있다. 
			ResultSet rs = pstmt.executeQuery();//executeQuery() 쿼리 실행. ResultSet 결과를 가진 Set
			
			System.out.println("id:\t\tname\t\tscore");
			
			while(rs.next()) {//rs.next() 데이터의 행을 하나씩 꺼내서(id: 1	 name: 신경수	 score:100) ResultSet rs에서 데이터 다 소진될 때 거짓이 됨 
				System.out.println("id: " + rs.getInt(1)//rs.getInt(1) 소괄호 안은 컬럼번호(1부터 시작) 이 행의 첫번째 컬럼은 id로 int다
				+ "\t name: " + rs.getString(2)//두번째 컬럼은 이름으로 String 
				+ "\t score:" + rs.getInt(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
