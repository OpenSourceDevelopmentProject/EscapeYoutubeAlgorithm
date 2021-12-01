package scrapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import user.User;

public class YoutubeDAO {//database Access Object

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public YoutubeDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS?useSSL=false&autoReconnection=true&characterEncoding=utf8"; //컴퓨터에 설치된 mysql자체를 의미
			String dbID = "root";
			String dbPassword = "qlalfqjsgh312!";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int setData(Main youtube) {

		String SQL = "INSERT INTO youtube_db VALUES (?,?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, youtube.chanelName);
			pstmt.setString(2, youtube.profileImageUrl);
			pstmt.setString(3, youtube.newVideoUrl);
			return pstmt.executeUpdate(); //select문을 제외한 나머지를 수행할 때 사용
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB 오류

	}
	public int getData(String chanelName) {
		//Qeury 작성
		String SQL = "SELECT profileImgUrl, newVideoUrl FROM youtube_db WHERE chanelName = ?"; //user테이블에서 userPassword를 가져옴
		try {
			
			//Query 저장
			pstmt = conn.prepareStatement(SQL); // pstmt에 위의 query 저장 후 DB에 연결 준비
			pstmt.setString(1,  chanelName);
			//Query 실행
			rs = pstmt.executeQuery(); //select문을 수행할 때 작성
			if(rs.next()) {
				//데이터 타입에 따라 getInt(1) 등으로 사용
				System.out.println("profileImgUrl : " + rs.getString(1));
				System.out.println("newVideoUrl : " +rs.getString(2));
				return 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외발생");
		}
		return-2;//데이터베이스 오류
	}
	
}
