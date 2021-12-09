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
		// Jsoup�� �̿��ؼ� http://www.cgv.co.kr/movies/ ũ�Ѹ�
		//String url = "https://www.youtube.com/user/strongheartsbs";//ũ�Ѹ��� url����
		System.out.println("Crawler, url : "+url);
		

		//�̸� ����
		YoutubeChanelName youtubeChanelName = new YoutubeChanelName();
		chanelName = youtubeChanelName.getYoutubeChanelName(url);
		System.out.println("Crawler, ���1");
		//�̹��� ����
		YoutubeProfileImage profileImage = new YoutubeProfileImage();
		try {
			profileImageUrl = profileImage.getYoutubeChannelProfileImage(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Crawler, ���2");
		//���� ����
		url = url+"/videos";
		YoutubeNewVideos newVideo = new YoutubeNewVideos();
		try {
			newVideoUrls = newVideo.getYoutubeNewVideos(url, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Crawler, ���3");
		 System.out.println("============================================================");
		 System.out.println(chanelName); 
		 System.out.println(profileImageUrl);
		 System.out.println(newVideoUrls); 
		 System.out.println("============================================================");
		 

	}
}