package scrapper;

import java.awt.FileDialog;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Iterator;

 

import javax.swing.JFrame;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;


public class Main {
	public String chanelName = null;
	public String profileImageUrl = null;
	public String newVideoUrl = null;
	
	public Main() {
		// Jsoup를 이용해서 http://www.cgv.co.kr/movies/ 크롤링
		String url = "https://www.youtube.com/user/strongheartsbs";//크롤링할 url지정
		System.out.println(url);
		Document doc = null;        //Document에는 페이지의 전체 소스가 저장
		try {

			doc = Jsoup.connect(url).get();

		} catch (IOException e) {

			e.printStackTrace();
		}

		//이름 추출
		chanelName = doc.select("meta[itemprop=name]").attr("content");    
		//이미지 추출
		YoutubeProfileImage profileImage = new YoutubeProfileImage();
		try {
			profileImageUrl = profileImage.getYoutubeChannelProfileImage(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//새로운 비디오 페이지 문서
		url = url+"/videos";
		
		//새 비디오 추출
		try {

			doc = Jsoup.connect(url).get();

		} catch (IOException e) {

			e.printStackTrace();
		}
		
		ArrayList newVidoeUrlArray = null;
		YoutubeNewVideos newVideo = new YoutubeNewVideos();
		try {
			newVidoeUrlArray = newVideo.getYoutubeNewVideos(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newVideoUrl = newVidoeUrlArray.get(0).toString();
		
		
		 System.out.println("============================================================");
		 System.out.println(chanelName); 
		 System.out.println(profileImageUrl);
		 System.out.println(newVideoUrl); 
		 System.out.println("============================================================");
		 

	}
	static void jsonadd(String parseString){
		String json;
		
		String str = "{\"name\" : \"apple\", \"id\" : 1, \"price\" : 1000}";
		
		System.out.println(str);
		System.out.println(parseString);
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("오류발생");
			e.printStackTrace();
		}
		System.out.println(jsonObject.get("name")); // apple
		System.out.println(jsonObject.get("id")); // 1
		System.out.println(jsonObject.get("price")); // 1000

		
    }
}