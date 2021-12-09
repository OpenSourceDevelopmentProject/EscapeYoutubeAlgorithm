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


public class YoutubeProfileImage {
  
    public final static String YOUTUBE_PROFILE_IMAGE_START_URL = "yt3.ggpht.com/";
    public final static String YOUTUBE_PROFILE_IMAGE_END_URL = "-c-k-c0x00ffffff-no-rj";
 
    public static String getYoutubeChannelProfileImage(String channelUrl) throws IOException
    {
        Document document = Jsoup.connect(channelUrl).get();
    
        String html = document.toString();
        Pattern pattern = Pattern.compile(YOUTUBE_PROFILE_IMAGE_START_URL + "(.*?)" + YOUTUBE_PROFILE_IMAGE_END_URL);
        Matcher matcher = pattern.matcher(html); 
        ArrayList imgUrl = new ArrayList();
        for (int i=0; matcher.find(); i++) { 
           imgUrl.add(matcher.group());
        }  
        return imgUrl.get(imgUrl.size()-1).toString();
       
    }
 
}