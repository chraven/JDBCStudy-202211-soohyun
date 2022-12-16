package main.java.com.study.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import lombok.RequiredArgsConstructor;
import main.java.com.study.jdbc.entity.User;
import main.java.com.study.jdbc.util.DBConnectionMgr;

//@RequiredArgsConstructor
//arguemnt가 필수

public class UserDao {

	
	//데이터베이스에 접근하는 객체
	
//	private final DBConnectionMgr pool;
	
//	public UserDao(DBConnectionMgr pool) {
//		this.pool = pool;
//	}
	
	private DBConnectionMgr pool;
	
	public UserDao() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public int insertUser(User user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int successCount = 0;
		
		try {
			con = pool.getConnection();
			sql = "insert into user_mst values(0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//Statement.RETURN_GENERATED_KEYS : 키값 받아옴
			pstmt.setString(1, user.getUsername());//Main클래스에서 user의 builder에서 "abcd"로 입력된 username을 가져와서 setString한다.
			successCount = pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();//getGeneratedKeys() : AutoIncerement된 키값을 rs에 가져다 줌.
			if(rs.next()) {
				user.setUser_id(rs.getInt(1));//getInt(1) 첫번째 컬럼은 int.첫번째 컬럼인 id에 AI된 키값을 set한다
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);//커넥션을 끊어줌. 데이터 잡아먹으니까.
		}
		return successCount;
	}


}
