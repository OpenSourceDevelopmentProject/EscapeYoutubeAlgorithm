package youtube;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;


public class YoutubeNewVideos {
  
    public final static String YOUTUBE_New_Video_Start_URL = "\"/watch";
    public final static String YOUTUBE_New_Video_End_URL = "\"";
    public static ArrayList getYoutubeNewVideos(String channelUrl, YoutubeCrawler youtubeCrawler) throws IOException
    {
        Document document = Jsoup.connect(channelUrl).get();
    
        String html = document.toString();
        
        Pattern pattern = Pattern.compile(YOUTUBE_New_Video_Start_URL + "(.*?)" + YOUTUBE_New_Video_End_URL);
        Matcher matcher = pattern.matcher(html); 
        
        ArrayList newVideoUrlArray = new ArrayList(); 
        
        while (matcher.find()) { 
        	newVideoUrlArray.add(matcher.group());
        } ;
        
        ArrayList returnArray = new ArrayList(); 
        System.out.println(newVideoUrlArray.size());
        
        if(newVideoUrlArray.size()<5)
        	youtubeCrawler.newVideosNum = newVideoUrlArray.size();
        
        for(int i=0; i<5 && i<newVideoUrlArray.size(); i++) {
        	String newVideoUrl = "youtube.com"+newVideoUrlArray.get(i).toString().replace("\"", "");
        	newVideoUrl = newVideoUrl.replace("watch?v=", "embed/");
        	returnArray.add(newVideoUrl);
        }
        
        
        System.out.println(returnArray);
        return returnArray;
       
    }
 
}