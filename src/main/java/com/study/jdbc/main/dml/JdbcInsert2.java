package main.java.com.study.jdbc.main.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.com.study.jdbc.util.DBConnection;

public class JdbcInsert2 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		List<String> usernameList = new ArrayList<>();

		while (true) {
			System.out.print("등록할 아이디 입력: ");
			usernameList.add(scanner.nextLine());
			System.out.println("아이디를 추가로 등록하시겠습니까?(Y/y, 취소하려면 아무키나 입력하세요.)");
			String selected = scanner.nextLine();
			if (!"yY".contains(scanner.nextLine().isBlank() ? "n" : selected)) {// substring(0, 1): 0번 인덱스에서 1번 전까지. 0번
																				// 인덱스 글자하나
				break; // 입력한 문자열에서 자른 것(scanner.nextLine().substring(0, 1))이 yY를 포함하지 (!)않으면 break를
						// 건다.
				// 무한루프는 break걸어야 멈춤
			} // scanner.nextLine().isBlank() ? "n" : selected에서 isBlank면 n이 나오고, 공백이 아니면 입력값이 나옴
				// 그걸 yY에 포함되지 않으면(!) break.
				// isBlank 비어있으면 true
		}

		Connection con = DBConnection.getInstance().getConnection();
		String prefixSql = "insert into user_mst values";
		String valuesBody = "";
		String suffixSql = ";";

		for (int i = 0; i < usernameList.size(); i++) {
			valuesBody += "(0, ?)";
			if (i < usernameList.size() - 1) {
				valuesBody += ", ";
			}
		}
		//System.out.println(valuesBody);

		try {
			PreparedStatement pstmt = con.prepareStatement(prefixSql + valuesBody + suffixSql);
			for (int i = 0; i < usernameList.size(); i++) {
				pstmt.setString(i + 1, usernameList.get(i));//위에서 찍은 (0, ?) 중 i+1번째로 본 ?에 usernameList의 i번째 값을 대입.
			}
			int successCount = pstmt.executeUpdate();
			System.out.println(successCount + "건 등록완료!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
