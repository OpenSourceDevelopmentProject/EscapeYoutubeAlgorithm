package youtube;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONException;
import org.json.JSONObject;

public class UserDAO {
//������ ���̽� ���� ��ü�� ����. �����ͺ��̽����� ������ �ҷ����ų� �ְ��� �� �� ���
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS?useSSL=false&autoReconnection=true&characterEncoding=utf8"; //��ǻ�Ϳ� ��ġ�� mysql��ü�� �ǹ�
			String dbID = "root";
			String dbPassword = "qlalfqjsgh312!"; //��й�ȣ
			Class.forName("com.mysql.jdbc.Driver");//����̹��� mysql�� ������ �� �ֵ��� �Ű�ü ��Ȱ�� ��
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			System.out.println("conn :" +conn);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("���ܹ߻�1");
		}
	}
	
	//json ���� �ڵ�
//	public void json() {
//		String jsonString = "{youtube:{\"professor\":\"�豳��\",\"student\":\"���л�\"}}";
//		JSONObject jObject = null;
//		try {
//			jObject = new JSONObject(jsonString);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    JSONObject youtube1 = null;
//		try {
//			youtube1 = jObject.getJSONObject("youtube");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    System.out.println(youtube1.toString());
//	}
	
	public int login(String userID, String userPassword) {
		//Qeury �ۼ�
		String SQL = "SELECT userPassword FROM user_db WHERE userID = ?"; //user���̺��� userPassword�� ������
		try {
			//Query ����
			pstmt = conn.prepareStatement(SQL); // pstmt�� ���� query ���� �� DB�� ���� �غ�
			pstmt.setString(1,  userID); //
			//Query ����
			rs = pstmt.executeQuery(); //select���� ������ �� �ۼ�
			if(rs.next()) {
				//������ Ÿ�Կ� ���� getInt(1) ������ ���
				if(rs.getString(1).equals(userPassword)) {
					return 1;//�α��� ����
				}
				else
					return 0;//��й�ȣ ����ġ
			}
			else
				return -1;//���̵� ����
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("���ܹ߻�");
		}
		return-2;//�����ͺ��̽� ����
	}
	
	public int join(User user) {

		String SQL = "INSERT INTO user_db VALUES (?,?,?,?, ?)";

		try {

			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, user.getUserID());

			pstmt.setString(2, user.getUserPassword());

			pstmt.setString(3, user.getUserName());

			pstmt.setString(4, user.getUserGender());
			
			pstmt.setString(5, null);
			return pstmt.executeUpdate(); //select���� ������ �������� ������ �� ���

		} catch (Exception e) {

			e.printStackTrace();
		}
		return -1; // DB ����

	}


}

