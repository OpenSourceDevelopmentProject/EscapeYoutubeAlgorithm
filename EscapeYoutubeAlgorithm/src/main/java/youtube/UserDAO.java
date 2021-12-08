package youtube;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONException;
import org.json.JSONObject;

public class UserDAO {
//데이터 베이스 접근 객체의 약자. 데이터베이스에서 정보를 불러오거나 넣고자 할 때 사용
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS?useSSL=false&autoReconnection=true&characterEncoding=utf8"; //컴퓨터에 설치된 mysql자체를 의미
			String dbID = "root";
			String dbPassword = "qlalfqjsgh312!"; //비밀번호
			Class.forName("com.mysql.jdbc.Driver");//드라이버는 mysql에 접근할 수 있도록 매개체 역활을 함
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			System.out.println("conn :" +conn);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외발생1");
		}
	}
	
	//json 관련 코드
//	public void json() {
//		String jsonString = "{youtube:{\"professor\":\"김교수\",\"student\":\"이학생\"}}";
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
		//Qeury 작성
		String SQL = "SELECT userPassword FROM user_db WHERE userID = ?"; //user테이블에서 userPassword를 가져옴
		try {
			//Query 저장
			pstmt = conn.prepareStatement(SQL); // pstmt에 위의 query 저장 후 DB에 연결 준비
			pstmt.setString(1,  userID); //
			//Query 실행
			rs = pstmt.executeQuery(); //select문을 수행할 때 작성
			if(rs.next()) {
				//데이터 타입에 따라 getInt(1) 등으로 사용
				if(rs.getString(1).equals(userPassword)) {
					return 1;//로그인 성공
				}
				else
					return 0;//비밀번호 불일치
			}
			else
				return -1;//아이디가 없음
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외발생");
		}
		return-2;//데이터베이스 오류
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
			return pstmt.executeUpdate(); //select문을 제외한 나머지를 수행할 때 사용

		} catch (Exception e) {

			e.printStackTrace();
		}
		return -1; // DB 오류

	}


}

