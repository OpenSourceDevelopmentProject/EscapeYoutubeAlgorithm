package youtube;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			//System.out.println(setSubscribe_db("kkegoz", "롱수칸"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int setYoutube_db(YoutubeCrawler youtubeCrawler) {
		String chanelName = youtubeCrawler.chanelName;
		String profileImageUrl = youtubeCrawler.profileImageUrl;
		String newVideoUrl = youtubeCrawler.newVideoUrl;
		int chanelNum = 0;
		//이미 해당하는 유튜브 채널이 youtube_db에 저장됬는지 확인
		String SQL = "SELECT count(chanelName) FROM youtube_db WHERE chanelName = ?"; 
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  chanelName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				chanelNum = rs.getInt(1);
			}
			else {
				return -1;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(chanelNum == 0) {
			SQL = "INSERT INTO youtube_db VALUES (?,?,?)";
			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, chanelName);
				pstmt.setString(2, profileImageUrl);
				pstmt.setString(3, newVideoUrl);
				System.out.println("성공");
				pstmt.executeUpdate(); //select문을 제외한 나머지를 수행할 때 사용
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 1;
		}
		else 
		{
			return -2;
		}
			
		
	}
	public int printYoutube_db(String chanelName) {
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
	
	public int setSubscribe_db(String userID, YoutubeCrawler youtubeCrawler) {
		String SQL = "SELECT count(subscribeIndex) FROM subscribe_db;"; //user테이블에서 userPassword를 가져옴
		int subscribeIndexNum = -1;
		try {
			pstmt = conn.prepareStatement(SQL); 
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				subscribeIndexNum = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외발생");
		}
		SQL = "INSERT INTO subscribe_db VALUES (?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, subscribeIndexNum+1);
			pstmt.setString(2, userID);
			pstmt.setString(3, youtubeCrawler.chanelName);
			pstmt.setString(4, "0");
			System.out.println("성공");
			return pstmt.executeUpdate(); //select문을 제외한 나머지를 수행할 때 사용
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("내부실패");
		}
		return -1; // DB 오류
	}
	
	
	public String getChanelName(int tagNum, String userID) {
		String SQL = "SELECT chanelName FROM subscribe_db WHERE userID = ?;";
		
		try 
		{
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  userID);
			rs = pstmt.executeQuery(); 
			for(int i=0; i<tagNum; i++) {
				if(!rs.next())
					return "-1";
			}
			if(rs.getString(1).length() > 7) {
				return rs.getString(1).substring(0,6)+"..";
			}
			else
			{
				return rs.getString(1);
			}
		} catch (SQLException e1) 
		{
			System.out.println("예외발생");
			e1.printStackTrace();
		}
		return "-2";
	}
	
	public String getProfileImgUrl(int tagNum, String userID) {
		
		String SQL = "SELECT chanelName FROM subscribe_db WHERE userID = ?;"; //user테이블에서 userPassword를 가져옴
		String chanelName = null;
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  userID);
			rs = pstmt.executeQuery(); 
			for(int i=0; i<tagNum; i++) {
				if(!rs.next())
					return "-1";
			}
			chanelName = rs.getString(1);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외발생");
			return "-2";//데이터베이스 오류
		}
		
		SQL = "SELECT profileImgUrl FROM youtube_db WHERE chanelName = ?;"; //user테이블에서 userPassword를 가져옴
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  chanelName);
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				return "https://"+rs.getString(1);
			}
			else {
				return "-3";
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외발생");
		}
		return "-4";//데이터베이스 오류
		
	}
	
	public String getNewVideoUrl(int tagNum, String userID) {
		String SQL = "SELECT chanelName FROM subscribe_db WHERE userID = ?;"; //user테이블에서 userPassword를 가져옴
		String chanelName = null;
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  userID);
			rs = pstmt.executeQuery(); 
			for(int i=0; i<tagNum; i++) {
				if(!rs.next())
					return "-1";
			}
			chanelName = rs.getString(1);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외발생");
			return "-2";//데이터베이스 오류
		}
		
		SQL = "SELECT newVideoUrl FROM youtube_db WHERE chanelName = ?;"; //user테이블에서 userPassword를 가져옴
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  chanelName);
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				return "https://"+rs.getString(1);
			}
			else {
				return "-3";
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외발생");
		}
		return "-4";//데이터베이스 오류
	}
	
	public int getChanelNum(String userID) {
		String SQL = "SELECT count(subscribeIndex) FROM subscribe_db where userID = \""+userID+"\";";
		try {
		
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외발생");
		}
		return -1;//데이터베이스 오류
	}
	
	public String checkExistTag(int tagNum) {
		User user = new User();
		int chanelNum = getChanelNum(user.getUserID());
		if(chanelNum >= tagNum)
			return "is-exist-in";
		else
			return "is-empty-in";//데이터베이스 오류
	}
}
