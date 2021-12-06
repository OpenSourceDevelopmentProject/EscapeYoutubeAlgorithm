package youtube;

import java.awt.FileDialog;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Iterator;

 

import javax.swing.JFrame;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;


public class YoutubeCrawler {
	public String chanelName = null;
	public String profileImageUrl = null;
	public String newVideoUrl = null;
	
	public YoutubeCrawler() {
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
//		 System.out.println(chanelName); 
//		 System.out.println(profileImageUrl);
//		 System.out.println(newVideoUrl); 
		 System.out.println("============================================================");
		 

	}
}