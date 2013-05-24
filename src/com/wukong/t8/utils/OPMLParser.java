package com.wukong.t8.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.InputSource;

import com.sun.syndication.feed.opml.Opml;
import com.sun.syndication.feed.opml.Outline;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedInput;
import com.wukong.t8.pojo.Channel;
import com.wukong.t8.pojo.Opml4channel;


public class OPMLParser {

	/**
	 * 根据频道去获取旗下的Opml4Channel  
	 * 一般情况下返回值不会改变   除非官方服务器对频道资源(路径)进行过升级
	 * @param urlPath
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Opml4channel> getOPML4ChannelList(Channel channel){
		List<Opml4channel> list=null;
		Opml opml=null;
		Opml4channel o4c=null;
		if(channel!=null){
			list=new ArrayList<Opml4channel>();
			String[] channelOpmlUrlArray=channel.getChannelOpmlUrl().split("-_-!!");
			String opmlHeadTitle=null;
			String opmlBodyTitle=null;
			String opmlBodyText=null;
			String temp=null;
			List<Outline> outlines=null;
			
			for(String url:channelOpmlUrlArray){
				WireFeedInput input =new WireFeedInput();

				try {                     
					System.out.println("opml路径="+url);
					//从网络获取-sina-shandong
					if(url.startsWith("http://")){
						opml=(Opml) input.build(
								new InputSource(getInputStreamByURL(url)));
					//从本地文件获取-baidu-163-	
					}else {
						if(url.startsWith("baidu")){
							opml=(Opml) input.build(
									new InputStreamReader(new FileInputStream(Utils.BD+url), "UTF-8"));
						}
						if(url.startsWith("163")){
							opml=(Opml) input.build(
									new InputStreamReader(new FileInputStream(Utils.NE+url), "UTF-8"));
						}
						if(url.startsWith("treant")){
							opml=(Opml) input.build(
									new InputStreamReader(new FileInputStream(Utils.TR+url), "UTF-8"));
						}
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FeedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
				Outline outlineParent=(Outline) opml.getOutlines().get(0);   //第一个大OutLine
				
				opmlHeadTitle=opml.getTitle().trim();
				opmlBodyTitle=outlineParent.getTitle().trim();
				opmlBodyText=outlineParent.getText().trim(); 
				
				outlines=outlineParent.getChildren();
				
				for(Outline o:outlines){
					o4c=new Opml4channel();
					o4c.setOpmlHeadTitle(opmlHeadTitle);
					o4c.setOpmlBodyTitle(opmlBodyTitle);
					o4c.setOpmlBodyText(opmlBodyText);
					o4c.setOpmlOutlineText(o.getText().trim());
					o4c.setOpmlOutlineTitle(o.getTitle().trim());
					o4c.setOpmlOutlineType(o.getType().trim());
					
//					temp=o.getXmlUrl();
//					if(temp!=null){
//					System.out.println("00="+encodedByUTF8(temp.trim()));
//						o4c.setOpmlOutlineXmlUrl(encodedByUTF8(o.getXmlUrl().trim()));
						o4c.setOpmlOutlineXmlUrl(o.getXmlUrl());
//					}
//					
//					temp=o.getHtmlUrl();
//					if(temp!=null){
						o4c.setOpmlOutlineHtmlUrl(o.getHtmlUrl().trim());
//					}
					//innerSetException多线程的FutureTask异常 待解决
					o4c.setChannel(channel);
					list.add(o4c);
//					temp=null;
				}
			}
		}
		return list;
	}

	private static InputStream getInputStreamByURL(String channelOpmlUrl) {
		InputStream inputStream=null;
		URL url=null;
		try {
			url=new URL(channelOpmlUrl);
		} catch (MalformedURLException e) {
			System.out.println("频道Url解析有误!");
			e.printStackTrace();
		}
		
		URLConnection conn=null;
		try {
			conn=url.openConnection();
			conn.setReadTimeout(5000);
			conn.setRequestProperty(
                    "User-Agent",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.3) Gecko/20100401 Firefox/3.6.3");
			inputStream=conn.getInputStream();
		} catch (IOException e) {
			System.out.println("不能打开频道Url的Connection!");
			e.printStackTrace();
		}
		
		return inputStream;
	}

	
	public static String encodedByUTF8(String url){
		String s=null;
		if(url!=null){
			try {
				s=URLEncoder.encode(s, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return s;
	}
	
	public static String decodedFromUTF8(String url){
		String s=null;
		if(url!=null){
			try {
				s=URLDecoder.decode(url, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return s;
	} 
}
