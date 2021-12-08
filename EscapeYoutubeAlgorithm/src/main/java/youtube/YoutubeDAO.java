package youtube;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			System.out.println(setSubscribe_db("kkegoz", "�ռ�ĭ"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int setYoutube_db(YoutubeCrawler youtube) {

		String SQL = "INSERT INTO youtube_db VALUES (?,?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, youtube.chanelName);
			pstmt.setString(2, youtube.profileImageUrl);
			pstmt.setString(3, youtube.newVideoUrl);
			return pstmt.executeUpdate(); //select���� ������ �������� ������ �� ���
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB ����

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
	public int setSubscribe_db(String userID, String chanelName) {

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
			pstmt.setString(3, chanelName);
			pstmt.setString(4, "0");
			System.out.println("����");
			return pstmt.executeUpdate(); //select���� ������ �������� ������ �� ���
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ν���");
		}
		return -1; // DB ����
	}
	
	
	public String getChanelName(int tagNum) {
		//Qeury �ۼ�
		String SQL = "SELECT chanelName FROM youtube_db; "; //user���̺��� userPassword�� ������
		try {
			
			//Query ����
			pstmt = conn.prepareStatement(SQL); // pstmt�� ���� query ���� �� DB�� ���� �غ�
			//Query ����
			rs = pstmt.executeQuery(); //select���� ������ �� �ۼ�
			for(int i=0; i<tagNum; i++) {
				if(!rs.next())
					return "-1";
			}
				//������ Ÿ�Կ� ���� getInt(1) ������ ���
				//System.out.println("rs.getString(1) : " + rs.getString(1));
				return rs.getString(1);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("���ܹ߻�");
		}
		return "-2";//�����ͺ��̽� ����
	}
	
	public String getProfileImgUrl(int tagNum) {
		//Qeury �ۼ�
		String SQL = "SELECT profileImgUrl FROM youtube_db; "; //user���̺��� userPassword�� ������
		try {
			
			//Query ����
			pstmt = conn.prepareStatement(SQL); // pstmt�� ���� query ���� �� DB�� ���� �غ�
			//Query ����
			rs = pstmt.executeQuery(); //select���� ������ �� �ۼ�
			for(int i=0; i<tagNum; i++) {
				if(!rs.next())
					return "-1";
			}
				//������ Ÿ�Կ� ���� getInt(1) ������ ���
//System.out.println("rs.getString(1) : " + rs.getString(1));
//			System.out.println(rs.getString(1));
				return "https://"+rs.getString(1);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("���ܹ߻�");
		}
		return "-2";//�����ͺ��̽� ����
	}
	
	public String getNewVideoUrl(int tagNum) {
		//Qeury �ۼ�
		String SQL = "SELECT newVideoUrl FROM youtube_db; "; //user���̺��� userPassword�� ������
		try {
			
			//Query ����
			pstmt = conn.prepareStatement(SQL); // pstmt�� ���� query ���� �� DB�� ���� �غ�
			//Query ����
			rs = pstmt.executeQuery(); //select���� ������ �� �ۼ�
			for(int i=0; i<tagNum; i++) {
				if(!rs.next())
					return "-1";
			}
				//������ Ÿ�Կ� ���� getInt(1) ������ ���
				//System.out.println("rs.getString(1) : " + rs.getString(1));
				return "https://"+rs.getString(1);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("���ܹ߻�");
		}
		return "-2";//�����ͺ��̽� ����
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
