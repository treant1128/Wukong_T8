package com.wukong.t8.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xml.sax.InputSource;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndImage;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.wukong.t8.pojo.Feed;
import com.wukong.t8.pojo.Opml4channel;

public class FeedParser {
	
	/**
	 * 一个Opml4channel只对应一个Feed   //暂时废弃不用
	 * @param o4c
	 * @return
	 */
	public static Feed getFeedByOpmlOutlineXmlUrl(Opml4channel o4c){
		Feed feed=null;
		if(o4c!=null){
			SyndFeed f=getSyndFeedByOpmlOutlineXmlUrl(o4c.getOpmlOutlineXmlUrl());
			String temp=null;
			if(f!=null){
			
				feed=new Feed();
				temp=f.getFeedType();
				if(temp!=null){
					feed.setFeedType(temp.trim());
					temp=null;
				}
				temp=f.getTitle();
				if(temp!=null){
					feed.setFeedTitle(temp.trim());     
					temp=null;
				}else{
					feed.setFeedTitle("NoTitle");     
					temp=null;
				}
				
				SyndImage img=f.getImage();
				if(img!=null){
					feed.setFeedImageTitle(img.getTitle().trim());	
					feed.setFeedImageLink(img.getLink().trim());  	 
					feed.setFeedImageUrl(img.getUrl().trim()); 
				}
				 
				temp=f.getDescription();
				if(temp!=null){
					feed.setFeedDescription(temp.trim()); 
					temp=null;
				}
				 
				temp=f.getLink();
				if(temp!=null){
					feed.setFeedLink(temp.trim()); 
					temp=null;
				}else{
					feed.setFeedLink("NoLink");
					temp=null;
				}
				  	
				temp=f.getLanguage();
				if(temp!=null){
					feed.setFeedLauguage(temp.trim()); 
					temp=null;
				}
				  		
				Date date=f.getPublishedDate();
				if(date!=null){
					feed.setFeedPubDate(new Timestamp(date.getTime()));
				}else{
					feed.setFeedPubDate(new Timestamp(System.currentTimeMillis()));
				}
				 
				temp=f.getCopyright();
				if(temp!=null){
					feed.setFeedCopyright(temp.trim());
					temp=null;
				}
				
				feed.setOpml4channel(o4c);
			}
			
		}
		return feed;
	}
	
	public static SyndFeed getSyndFeedByOpmlOutlineXmlUrl(String urlPath){
		URL url=null;
		try {
			url=new URL(urlPath);
//			System.out.println("PPPPPP="+urlPath);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		URLConnection conn=null;
		try {
			conn=url.openConnection();
			conn.setRequestProperty(
                    "User-Agent",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.3) Gecko/20100401 Firefox/3.6.3");
			conn.setReadTimeout(8000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
		
		// Reading the Feed
		SyndFeedInput input=new SyndFeedInput();
		SyndFeed feed=null;
		try {
//			System.out.println("StatusCode=="+((HttpURLConnection)conn).getResponseCode());
	//		XmlReader reader=new XmlReader();	
	//		System.out.println("@@@@@"+urlPath);if(reader!=null){System.out.println("reader!=null");}
			if(((HttpURLConnection)conn).getResponseCode()==HttpURLConnection.HTTP_OK){
				InputStream stream=conn.getInputStream();              
				//saveXML(stream);
				InputSource source=new InputSource(stream);
				source.setEncoding("UTF-8");
				if(urlPath.contains("baidu")){
					//Sina-->UTF-8  Baidu-->GB2312
					source.setEncoding("GB2312");
				}
				feed=input.build(source); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return feed;
	}

	public static void main(String[] args){
		//
		SyndFeed feed=getSyndFeedByOpmlOutlineXmlUrl("http://news.baidu.com/n?cmd=4&class=internews&tn=rss");
		
		System.out.println("feedType="+feed.getFeedType());
		System.out.println("Feed--标题="+feed.getTitle());
		SyndImage img=feed.getImage();
		if(img!=null){
			System.out.println("图片标题="+img.getTitle());
			System.out.println("图片link="+img.getLink());
			System.out.println("图片url="+img.getUrl());
			System.out.println("图片描述="+img.getDescription());//null
		}else{
			System.out.println("木有图片");
		}
		
		System.out.println("描述="+feed.getDescription());
		System.out.println("链接="+feed.getLink());
		System.out.println("语言="+feed.getLanguage());
		
		System.out.println("Author作者="+feed.getAuthor());//null
		System.out.println("CST日期="+feed.getPublishedDate());
//		String gmtTime=getGMT(feed.getPublishedDate());
//		System.out.println("GMT日期="+gmtTime);
		System.out.println("版权"+feed.getCopyright());
		System.out.println("编码方式="+feed.getEncoding());//null
		
		
		
		@SuppressWarnings("unchecked")
		List<SyndEntry> entries = feed.getEntries();
		Iterator<SyndEntry> itEntries = entries.iterator();
		SyndEntry entry=null; // System.out.println("路径="+urlPath);
//		String entryCategory=Utils.extractKeyWordFromUrl(urlPath);  // System.out.println("关键字="+entryCategory);
		while (itEntries.hasNext()) {
			entry = itEntries.next();
			String title=entry.getTitle();//System.out.println("标题长度="+title.length());
			System.out.println("Title: " + entry.getTitle().trim());
//			System.out.println("Link: " + entry.getLink());
//			System.out.println("Author: " + entry.getAuthor());
//			System.out.println("Source:"+entry.getSource());
//			//Note: The URI is the unique identifier, in the RSS 2.0/atom case this is the GUID, for RSS 1.0 this is the URI attribute of the item
//			System.out.println("GUID?Uri:"+entry.getUri().replace("http://go.rss.sina.com.cn/redirect.php?url=", ""));
//	//		System.out.println("Category="+((SyndCategoryImpl)entry.getCategories().get(0)).getName().trim());
//			System.out.println("Publish Date: " + entry.getPublishedDate());
//	//		System.out.println("Comments:"+entry.get)
//			System.out.println("Description: " + entry.getDescription().getValue().trim());
			System.out.println();
		}
		
	}
}
