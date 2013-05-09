package com.wukong.t8.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import javax.faces.application.Application;

import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.WriterAppender;

public class Utils {
	
	public static String getLocalBak(String path) {
		StringBuffer sb=null;
		BufferedReader br=null;
		if(!new File(path).exists()){
			return null;
		}else{
			try {
				String rootPath=URLDecoder.decode(path, "UTF-8"); // System.out.println("路径="+URLDecoder.decode(rootPath,"utf-8"));
				br= new BufferedReader(new InputStreamReader(
						new FileInputStream(new File(rootPath))));
				String line="";
				sb=new StringBuffer();
				while((line=br.readLine())!=null){
					sb.append(line);	
				}
				return sb.toString();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if(br!=null){
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return null;
		}
	}
	
	/**
	 * OPML文件路径放在项目根目录下,不同的路径以字符串"!!!!!!!!!!"相隔
	 * @return
	 */
	public static String getRootPath() {
		// 因为类名为"Application"，因此" Application.class"一定能找到
		String result = Application.class.getResource("Application.class")
				.toString();  System.out.println(result);
		int index = result.indexOf("WEB-INF");
		if (index == -1) {
			index = result.indexOf("bin");
		}
		result = result.substring(0, index);
		if (result.startsWith("jar")) {
			// 当class文件在jar文件中时，返回"jar:file:/F:/ ..."样的路径
			result = result.substring(10);
		} else if (result.startsWith("file")) {
			// 当class文件在class文件中时，返回"file:/F:/ ..."样的路径
			result = result.substring(6);
		}
		if (result.endsWith("/"))
			result = result.substring(0, result.length() - 1);// 不包含最后的"/"
		return result+File.separator+"opml_urls.txt";
	}
	
	public static String extractKeyWordFromUrl(String url){
		int pre="http://rss.sina.com.cn/".length();
		String path=url.substring(pre);
		return path.substring(0, path.lastIndexOf("."));
	}
	
	public static String cutOffMillis(String time){
		if(time!=null){
			return time.substring(0, time.lastIndexOf("."));
		}
		return "UnKnown";
	}
	
	public static String encodeURL(String url){
		String result="";
		if(url!=null){
			try {
				result=URLEncoder.encode(url, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static String decodeURL(String url){
		String result="";
		if(url!=null){
			try {
				result=URLDecoder.decode(url, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static String getWebRootPath(){
		 URL url = Utils.class.getResource("Utils.class");
	     String path = url.getPath();         //       System.out.println("url.getPath="+path);
	     if(path!=null&&path.length()!=0){
	    	 path = path.substring(0, path.lastIndexOf("WEB-INF"));   
	     }
	     try {
			return URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	
	public static String encodedByUTF8(String url){
		String s=null;
		if(url!=null){
			try {
				s=URLEncoder.encode(url, "UTF-8");
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
	
	public static void initHTMLLogger(Logger logger, String path, boolean isAppend, Level level){
		HTMLLayout layout=new HTMLLayout();
		WriterAppender appender=null;
		try {
			FileOutputStream output=new FileOutputStream(path, isAppend);
//			OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(path), "GB2312");
			appender=new WriterAppender(layout, output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		logger.addAppender(appender);
		logger.setLevel((Level)level);
	}
	
	
	public static void main(String[] args){
//		System.out.println(encodeURL("http://news.baidu.com/n?cmd=1&class=taiwan&tn=rss"));
		System.out.println(getWebRootPath());
	
//		System.out.println(encodedByUTF8("http://news.baidu.com/n?cmd=7&loc=3996&name=%C9%BD%B6%AB&tn=rss"));
		
		
	//	System.out.println(System.getProperty("java.vm.vendor")); 
	}

	public static String separator=File.separator;
	public static String URL_HOME=Utils.getWebRootPath()+separator+"local"+separator;
	public static String NE=URL_HOME+"163"+separator;
	public static String BD=URL_HOME+"baidu"+separator;
	public static String TR=URL_HOME+"treant"+separator;
	
	public static LinkedHashMap<String, String[]> initChannels() {
		
		LinkedHashMap<String, String[]> map=new LinkedHashMap<String, String[]>();
		try{
			
//			map.put("齐鲁大地", new String[]{"treant_shandong_opml.xml"});
			map.put("国内", new String[]{"baidu_focus_civil_opml.xml", "baidu_news_civil_opml.xml"});
			map.put("国际", new String[]{"baidu_focus_inter_opml.xml", "baidu_news_inter_opml.xml"});
			map.put("军事", new String[]{"baidu_focus_mil_opml.xml", "baidu_news_mil_opml.xml"});
			map.put("财经", new String[]{"baidu_focus_finance_opml.xml", "baidu_news_finance_opml.xml"});
			map.put("互联网", new String[]{"baidu_focus_internet_opml.xml", "baidu_news_internet_opml.xml"});
			map.put("房产", new String[]{"baidu_focus_house_opml.xml", "baidu_news_house_opml.xml"});
			map.put("汽车", new String[]{"baidu_focus_auto_opml.xml", "baidu_news_auto_opml.xml"});
			map.put("体育", new String[]{"baidu_focus_sport_opml.xml", "baidu_news_sport_opml.xml"});
			map.put("娱乐", new String[]{"baidu_focus_ent_opml.xml", "baidu_news_ent_opml.xml"});
			map.put("游戏", new String[]{"baidu_focus_game_opml.xml", "baidu_news_game_opml.xml"});
			map.put("教育", new String[]{"baidu_focus_edu_opml.xml", "baidu_news_edu_opml.xml"});
			map.put("女人", new String[]{"baidu_focus_lady_opml.xml", "baidu_news_lady_opml.xml"});
			map.put("科技", new String[]{"baidu_focus_tech_opml.xml", "baidu_news_tech_opml.xml"});
			map.put("社会", new String[]{"baidu_focus_society_opml.xml", "baidu_news_society_opml.xml"});
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
}

