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
		// Jsoup�� �̿��ؼ� http://www.cgv.co.kr/movies/ ũ�Ѹ�
		String url = "https://www.youtube.com/user/strongheartsbs";//ũ�Ѹ��� url����
		System.out.println(url);
		Document doc = null;        //Document���� �������� ��ü �ҽ��� ����
		try {

			doc = Jsoup.connect(url).get();

		} catch (IOException e) {

			e.printStackTrace();
		}

		//�̸� ����
		chanelName = doc.select("meta[itemprop=name]").attr("content");    
		//�̹��� ����
		YoutubeProfileImage profileImage = new YoutubeProfileImage();
		try {
			profileImageUrl = profileImage.getYoutubeChannelProfileImage(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//���ο� ���� ������ ����
		url = url+"/videos";
		
		//�� ���� ����
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
			System.out.println("�����߻�");
			e.printStackTrace();
		}
		System.out.println(jsonObject.get("name")); // apple
		System.out.println(jsonObject.get("id")); // 1
		System.out.println(jsonObject.get("price")); // 1000

		
    }
}