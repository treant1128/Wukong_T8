package com.wukong.t8.action;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.wukong.t8.utils.Utils;

public interface BaseAction {
	public static final int THREAD_NUMBER=8;
	
	public ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUMBER);
	
//	public static final String SECRET_KEY_PRE_URL="http://t4.wukong.com/ahlt/login.do?input=";
	public static final String SECRET_KEY_PRE_URL="http://42.96.157.232/sdlt/login.do?input=";
	
	public static final String[][] netsources = new String[][] { 
//		{ "����", "http://www.sina.com.cn" },
		{ "�ٶ�", "http://www.baidu.com" },
//		{ "����", "http://www.163.com" },
	};
	
	public static  LinkedHashMap<String, String[]> allChannels=Utils.initChannels();
	
	public static final String[][] channels = new String[][] {
//		{"��³���", SD+"treant_shandong_opml.xml"},
		{"����", ""},
		{"����", ""},
		{"����", ""},
		{"�ƾ�", ""},
		{"������", ""},
		{"����", ""},
		{"����", ""},
		{"����", ""},
		{"����", ""},
		{"", ""},
		{"", ""},
		{"", ""},
		{"", ""},
//			{ "������������", "http://rss.sina.com.cn/sina_news_opml.xml" },
//			{ "�ٶȽ�������", Utils.getWebRootPath()+"baidu_news_opml.xml"},
//			{ "�������ž۽�", Utils.getWebRootPath()+"163_news_opml.xml"},
			
//			{ "��������", "http://rss.sina.com.cn/sina_sports_opml.xml" },
//			{ "����Ƶ��", "http://rss.sina.com.cn/sina_blog_opml.xml" },
//			{ "�Ƽ�����", "http://rss.sina.com.cn/sina_tech_opml.xml" },
//			{ "�ƾ�����", "http://rss.sina.com.cn/sina_finance_opml.xml" },
//			{ "��������", "http://rss.sina.com.cn/sina_jczs_opml.xml" },
//			{ "Ů������", "http://rss.sina.com.cn/sina_eladies_opml.xml" },
//			{ "��������", "http://rss.sina.com.cn/sina_auto_opml.xml" },
//			{ "��������", "http://rss.sina.com.cn/sina_ent_opml.xml" },
//			{ "��������", "http://rss.sina.com.cn/sina_book_opml.xml" },
//			{ "�Ļ�����", "http://rss.sina.com.cn/sina_edu_opml.xml" },
//			{ "��������", "http://rss.sina.com.cn/sina_house_opml.xml" },
//			{ "��Ϸ����", "http://rss.sina.com.cn/sina_games_opml.xml" },
//			{ "��������", "http://rss.sina.com.cn/sina_astro_opml.xml" },
//			{ "��Ƶ����", "http://rss.sina.com.cn/sina_bn_opml.xml" }, 
			};
	
	public abstract String toSnatch();
	public Logger getLogger();
}
