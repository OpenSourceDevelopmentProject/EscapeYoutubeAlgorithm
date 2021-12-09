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
			String dbURL = "jdbc:mysql://localhost:3306/BBS?useSSL=false&autoReconnection=true&characterEncoding=utf8"; //��ǻ�Ϳ� ��ġ�� mysql��ü�� �ǹ�
			String dbID = "root";
			String dbPassword = "qlalfqjsgh312!";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			//System.out.println(setSubscribe_db("kkegoz", "�ռ�ĭ"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int setYoutube_db(YoutubeCrawler youtubeCrawler) {
		String chanelName = youtubeCrawler.chanelName;
		String profileImageUrl = youtubeCrawler.profileImageUrl;
		String newVideoUrl = youtubeCrawler.newVideoUrl;
		int chanelNum = 0;
		//�̹� �ش��ϴ� ��Ʃ�� ä���� youtube_db�� �������� Ȯ��
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
				System.out.println("����");
				pstmt.executeUpdate(); //select���� ������ �������� ������ �� ���
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
		//Qeury �ۼ�
		String SQL = "SELECT profileImgUrl, newVideoUrl FROM youtube_db WHERE chanelName = ?"; //user���̺��� userPassword�� ������
		try {
			
			//Query ����
			pstmt = conn.prepareStatement(SQL); // pstmt�� ���� query ���� �� DB�� ���� �غ�
			pstmt.setString(1,  chanelName);
			//Query ����
			rs = pstmt.executeQuery(); //select���� ������ �� �ۼ�
			if(rs.next()) {
				//������ Ÿ�Կ� ���� getInt(1) ������ ���
				System.out.println("profileImgUrl : " + rs.getString(1));
				System.out.println("newVideoUrl : " +rs.getString(2));
				return 1;
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("���ܹ߻�");
		}
		return-2;//�����ͺ��̽� ����
	}
	
	public int setSubscribe_db(String userID, YoutubeCrawler youtubeCrawler) {
		String SQL = "SELECT count(subscribeIndex) FROM subscribe_db;"; //user���̺��� userPassword�� ������
		int subscribeIndexNum = -1;
		try {
			pstmt = conn.prepareStatement(SQL); 
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				subscribeIndexNum = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("���ܹ߻�");
		}
		SQL = "INSERT INTO subscribe_db VALUES (?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, subscribeIndexNum+1);
			pstmt.setString(2, userID);
			pstmt.setString(3, youtubeCrawler.chanelName);
			pstmt.setString(4, "0");
			System.out.println("����");
			return pstmt.executeUpdate(); //select���� ������ �������� ������ �� ���
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ν���");
		}
		return -1; // DB ����
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
			System.out.println("���ܹ߻�");
			e1.printStackTrace();
		}
		return "-2";
	}
	
	public String getProfileImgUrl(int tagNum, String userID) {
		
		String SQL = "SELECT chanelName FROM subscribe_db WHERE userID = ?;"; //user���̺��� userPassword�� ������
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
			System.out.println("���ܹ߻�");
			return "-2";//�����ͺ��̽� ����
		}
		
		SQL = "SELECT profileImgUrl FROM youtube_db WHERE chanelName = ?;"; //user���̺��� userPassword�� ������
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
			System.out.println("���ܹ߻�");
		}
		return "-4";//�����ͺ��̽� ����
		
	}
	
	public String getNewVideoUrl(int tagNum, String userID) {
		String SQL = "SELECT chanelName FROM subscribe_db WHERE userID = ?;"; //user���̺��� userPassword�� ������
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
			System.out.println("���ܹ߻�");
			return "-2";//�����ͺ��̽� ����
		}
		
		SQL = "SELECT newVideoUrl FROM youtube_db WHERE chanelName = ?;"; //user���̺��� userPassword�� ������
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
			System.out.println("���ܹ߻�");
		}
		return "-4";//�����ͺ��̽� ����
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
			System.out.println("���ܹ߻�");
		}
		return -1;//�����ͺ��̽� ����
	}
	
	public String checkExistTag(int tagNum) {
		User user = new User();
		int chanelNum = getChanelNum(user.getUserID());
		if(chanelNum >= tagNum)
			return "is-exist-in";
		else
			return "is-empty-in";//�����ͺ��̽� ����
	}
}
