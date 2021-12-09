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
	public ArrayList newVideoUrls = null;
	public String searchUrl = null;
	public int newVideosNum = 5;
	
	public YoutubeCrawler(String url) {
		searchUrl = url;
		// Jsoup를 이용해서 http://www.cgv.co.kr/movies/ 크롤링
		//String url = "https://www.youtube.com/user/strongheartsbs";//크롤링할 url지정
		System.out.println("Crawler, url : "+url);
		

		//이름 추출
		YoutubeChanelName youtubeChanelName = new YoutubeChanelName();
		chanelName = youtubeChanelName.getYoutubeChanelName(url);
		System.out.println("Crawler, 통과1");
		//이미지 추출
		YoutubeProfileImage profileImage = new YoutubeProfileImage();
		try {
			profileImageUrl = profileImage.getYoutubeChannelProfileImage(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Crawler, 통과2");
		//비디오 추출
		url = url+"/videos";
		YoutubeNewVideos newVideo = new YoutubeNewVideos();
		try {
			newVideoUrls = newVideo.getYoutubeNewVideos(url, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Crawler, 통과3");
		 System.out.println("============================================================");
		 System.out.println(chanelName); 
		 System.out.println(profileImageUrl);
		 System.out.println(newVideoUrls); 
		 System.out.println("============================================================");
		 

	}
}