package youtube;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		String newVideoUrl[] = new String[5];
		int newVideosNum = youtubeCrawler.newVideosNum;
		for(int i=0; i<newVideosNum; i++) {
			newVideoUrl[i] = youtubeCrawler.newVideoUrls.get(i).toString();
		}
		
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
			SQL = "INSERT INTO youtube_db VALUES (?,?,?,?,?,?,?,?,?)";
			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, chanelName);
				pstmt.setString(2, youtubeCrawler.searchUrl);
				pstmt.setString(3, profileImageUrl);
				pstmt.setInt(4, 5);
				for(int i=0; i<newVideosNum; i++) {
					pstmt.setString(i+5, newVideoUrl[i]);
				}
				if(newVideosNum < 5) {
					for(int i=0; i<5-newVideosNum; i++) {
						pstmt.setString(i+(5+newVideosNum), null);
					}
				}
				System.out.println("setYoutube_db : 성공");
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
	public void updateNewVideoUrls(String userID) {
		String SQL = "SELECT chanelName FROM subscribe_db WHERE userID = ?"; 
		String chanelName[] = new String[10];
		String profileImgUrl[] = new String[10];
		String newVideoUrl[][] = new String[10][5];
		String preVideoUrl[][] = new String[10][5];
		int newVideosNum[] = new int[10];
		int chanelNum = 0;
		System.out.println("통과1");
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  userID);
			rs = pstmt.executeQuery();
			for(chanelNum = 0; rs.next(); chanelNum++) {
				chanelName[chanelNum] = rs.getString(1);
			}
			System.out.println("chanelNum : "+chanelNum); 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("통과2");
		for(int i=0; i<chanelNum; i++) {
			SQL = "SELECT chanelURL FROM youtube_db WHERE chanelName = ?"; 
			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1,  chanelName[i]);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					YoutubeCrawler youtubeCrawler = new YoutubeCrawler(rs.getString(1));
					//프로필 이미지 추출
					profileImgUrl[i] = youtubeCrawler.profileImageUrl;
					//새 비디오 추출
					ArrayList newVideoUrls = youtubeCrawler.newVideoUrls;
					newVideosNum[i] = youtubeCrawler.newVideosNum;
					//혹시 새 영상이 5개가 안된다면j<newVideoUrls.size() 
					for(int j=0; j<newVideosNum[i]; j++) {
						newVideoUrl[i][j] = newVideoUrls.get(j).toString();
					}
					
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.out.println("통과3");
		for(int i=0; i<chanelNum; i++) {
			SQL = "SELECT newVideoUrl1, newVideoUrl2, newVideoUrl3, newVideoUrl4, newVideoUrl5 FROM youtube_db WHERE chanelName = ?";
			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, chanelName[i]);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					for(int j=0; j<newVideosNum[i]; j++) {
						preVideoUrl[i][j] = rs.getString(j+1);
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("예외발생");
			}
		}
		System.out.println("통과4");
		boolean isEquals = false;
		int howManyNewVideoAreThere[] = new int[chanelNum]; 
		
		for(int i=0; i<chanelNum; i++) {
			howManyNewVideoAreThere[i] = newVideosNum[i];
			System.out.println("채널 : "+ chanelName[i] + "/ howManyNew... : " + howManyNewVideoAreThere[i]);
			isEquals = false;
			for(int j=newVideosNum[i]-1; j>=0; j--) {
				for(int k=0; k<5; k++) {
					System.out.println(newVideoUrl[i][j]+"/"+preVideoUrl[i][0]);
					if(newVideoUrl[i][j].equals(preVideoUrl[i][k])) {
						isEquals = true;
						break;
					}
				}
				if(isEquals == true) {
					howManyNewVideoAreThere[i] -= 1;
				}
				else
					break;
			}
		}
		System.out.println("통과5");
		int nowLastVideoNum[] = new int[chanelNum];
		for(int i=0; i<chanelNum; i++) {
			SQL = "SELECT nowLastVideoNum FROM youtube_db WHERE chanelName = ?";
			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, chanelName[i]);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					nowLastVideoNum[i] = rs.getInt(1);
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("예외발생");
			}
		}
		System.out.println("통과6");
		for(int i=0; i<chanelNum; i++) {
			System.out.print(chanelName[i]+" : " );
			for(int j=0; j<newVideosNum[i]; j++) {
				System.out.print(newVideoUrl[i][j]);
			}
			System.out.println();
		}
		
		for(int i=0; i<chanelNum; i++) {
			for(int j=howManyNewVideoAreThere[i]; j>0; j--) {
				SQL = "UPDATE youtube_db SET newVideoUrl"+nowLastVideoNum[i]+"= ? WHERE chanelName = ?;";
				try {
					System.out.println("채널 : "+ chanelName[i] + "/ howManyNew... : " + howManyNewVideoAreThere[i] + "/ nowLastVideo... : " + nowLastVideoNum[i]);
					pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1, newVideoUrl[i][j-1]);
					pstmt.setString(2, chanelName[i]);
					pstmt.executeUpdate();
					System.out.println("1-"+i+"번째 : "+ "성공");
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("1-"+i+"번째 : "+ "예외발생");
				}	
				if(nowLastVideoNum[i] != 1) {
					nowLastVideoNum[i] -= 1;
				}
				else {
					nowLastVideoNum[i] = 5;
				}
			}
			SQL = "UPDATE youtube_db SET nowLastVideoNum = ? WHERE chanelName = ?;";
			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, nowLastVideoNum[i]);
				pstmt.setString(2, chanelName[i]);
				pstmt.executeUpdate();
				System.out.println("2-"+i+"번째 : "+ "성공");
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("2-"+i+"번째 : "+ "예외발생");
			}	
		}
		System.out.println("통과7");
	}
	//subscirbe_db에서 본인
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
			System.out.println(rs.getString(1) + " 길이 : "+rs.getString(1).length());
			if(rs.getString(1).length() >= 7) {
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
	
	public String getNewVideoUrl(int tagNum, String userID, int videoNum) {
		String SQL = "SELECT chanelName FROM subscribe_db WHERE userID = ?;"; //user테이블에서 userPassword를 가져옴
		String chanelName = null;
		int nowLastVideoNum = -1;
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
		SQL = "SELECT nowLastVideoNum FROM youtube_db WHERE chanelName = ?;"; //user테이블에서 userPassword를 가져옴
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, chanelName);
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				nowLastVideoNum = rs.getInt(1);	
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외발생");
			return "-2";//데이터베이스 오류
		}
		System.out.println("getNewVideo : 통과1");
		int existVideoNum = 5;
		System.out.println("videoNum : " + videoNum);
		System.out.println("nowLastVideoNum : " + nowLastVideoNum);
		SQL = "SELECT newVideoUrl1, newVideoUrl2, newVideoUrl3, newVideoUrl4, newVideoUrl5 FROM youtube_db WHERE chanelName = ?;"; //user테이블에서 userPassword를 가져옴
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  chanelName);
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				for(int i=5; i>=1; i--) {
					if(rs.getString(i) == null) {
						existVideoNum--;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외발생");
		}
		System.out.println("getNewVideo : 통과2 / existVideoNum :" + existVideoNum);
		if(existVideoNum == 5) {
			videoNum+=nowLastVideoNum;
			if(videoNum>5)
				videoNum-=5;
		}
		
		if(videoNum > existVideoNum) {
			return "-5";
		}
		SQL = "SELECT newVideoUrl"+videoNum+" FROM youtube_db WHERE chanelName = ?;"; //user테이블에서 userPassword를 가져옴
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
