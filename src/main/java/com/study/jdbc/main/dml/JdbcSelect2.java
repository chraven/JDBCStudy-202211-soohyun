package main.java.com.study.jdbc.main.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcSelect2 {

	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
		System.out.print("작성자 id: ");
		int writerId = scanner.nextInt();
		
		Connection connection = DBConnection.getInstance().getConnection();
		
		String sql2 = "select * from board_mst where writer_id = ?";//prepareStatement에서만 쓰는 문법
		
		PreparedStatement pstmt;//prepareStatement에서 DB와 연결하는 connection 객체에서 prepareStatement객체 만듦. 그걸 쿼리문 담아 pstmt에 담음. 
		try {
			pstmt = connection.prepareStatement(sql2);//prepareStatement 출력 명령? sql2 출력 내용 . 쿼리문 작성
			pstmt.setInt(1, writerId);//1은 왼->오 쿼리 읽다가 첫번째 물음표. writerId를 int(입력한 숫자값)로 set함.
			ResultSet rs = pstmt.executeQuery();
			
			System.out.println("id:\t\ttitle\t\tcontent\t\tread_count\t\twriter_id");
			
			while(rs.next()) {
				System.out.println("id: " + rs.getInt(1)
				+ "\t title: " + rs.getString(2)
				+ "\t content:" + rs.getString(3)
				+ "\t read_count: " + rs.getInt(4)
				+ "\t wroter_id: " + rs.getInt(5));
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
