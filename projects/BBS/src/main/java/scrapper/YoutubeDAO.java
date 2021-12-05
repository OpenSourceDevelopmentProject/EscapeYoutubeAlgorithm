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
			String dbURL = "jdbc:mysql://localhost:3306/BBS?useSSL=false&autoReconnection=true&characterEncoding=utf8"; //��ǻ�Ϳ� ��ġ�� mysql��ü�� �ǹ�
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
			return pstmt.executeUpdate(); //select���� ������ �������� ������ �� ���
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // DB ����

	}
	public int getData(String chanelName) {
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
			System.out.println(rs.getString(1));
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
	
	public int getChanelNum() {
		//Qeury �ۼ�
		String SQL = "SELECT count(chanelName) FROM youtube_db; "; //user���̺��� userPassword�� ������
		try {
			
			//Query ����
			pstmt = conn.prepareStatement(SQL); // pstmt�� ���� query ���� �� DB�� ���� �غ�
			//Query ����
			rs = pstmt.executeQuery(); //select���� ������ �� �ۼ�
			if(rs.next()) {
				//������ Ÿ�Կ� ���� getInt(1) ������ ���
				//System.out.println("rs.getString(1) : " + rs.getString(1));
				return rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("���ܹ߻�");
		}
		return -1;//�����ͺ��̽� ����
	}
	
	public String checkExistTag(int tagNum) {
		int chanelNum = getChanelNum();
		if(chanelNum >= tagNum)
			return "is-exist-in";
		else
			return "is-empty-in";//�����ͺ��̽� ����
	}
}
