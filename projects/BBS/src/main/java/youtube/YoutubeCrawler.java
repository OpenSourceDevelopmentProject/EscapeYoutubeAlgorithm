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
//		 System.out.println(chanelName); 
//		 System.out.println(profileImageUrl);
//		 System.out.println(newVideoUrl); 
		 System.out.println("============================================================");
		 

	}
}