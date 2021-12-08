package youtube;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class YoutubeChanelName {

	public String getYoutubeChanelName(String channelUrl){
		Document doc = null;        //Document에는 페이지의 전체 소스가 저장
		try {

			doc = Jsoup.connect(channelUrl).get();

		} catch (IOException e) {

			e.printStackTrace();
		}
		String chanelName = doc.select("meta[itemprop=name]").attr("content");    
		return chanelName;
	}
}
