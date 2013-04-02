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
//		{ "新浪", "http://www.sina.com.cn" },
		{ "百度", "http://www.baidu.com" },
//		{ "网易", "http://www.163.com" },
	};
	
	public static  LinkedHashMap<String, String[]> allChannels=Utils.initChannels();
	
	public static final String[][] channels = new String[][] {
//		{"齐鲁大地", SD+"treant_shandong_opml.xml"},
		{"国内", ""},
		{"国际", ""},
		{"军事", ""},
		{"财经", ""},
		{"互联网", ""},
		{"房产", ""},
		{"汽车", ""},
		{"体育", ""},
		{"娱乐", ""},
		{"", ""},
		{"", ""},
		{"", ""},
		{"", ""},
//			{ "新浪新闻中心", "http://rss.sina.com.cn/sina_news_opml.xml" },
//			{ "百度焦点新闻", Utils.getWebRootPath()+"baidu_news_opml.xml"},
//			{ "网易新闻聚焦", Utils.getWebRootPath()+"163_news_opml.xml"},
			
//			{ "体育新闻", "http://rss.sina.com.cn/sina_sports_opml.xml" },
//			{ "博客频道", "http://rss.sina.com.cn/sina_blog_opml.xml" },
//			{ "科技新闻", "http://rss.sina.com.cn/sina_tech_opml.xml" },
//			{ "财经新闻", "http://rss.sina.com.cn/sina_finance_opml.xml" },
//			{ "军事新闻", "http://rss.sina.com.cn/sina_jczs_opml.xml" },
//			{ "女性新闻", "http://rss.sina.com.cn/sina_eladies_opml.xml" },
//			{ "汽车新闻", "http://rss.sina.com.cn/sina_auto_opml.xml" },
//			{ "娱乐新闻", "http://rss.sina.com.cn/sina_ent_opml.xml" },
//			{ "读书新闻", "http://rss.sina.com.cn/sina_book_opml.xml" },
//			{ "文化教育", "http://rss.sina.com.cn/sina_edu_opml.xml" },
//			{ "房产新闻", "http://rss.sina.com.cn/sina_house_opml.xml" },
//			{ "游戏新闻", "http://rss.sina.com.cn/sina_games_opml.xml" },
//			{ "星座新闻", "http://rss.sina.com.cn/sina_astro_opml.xml" },
//			{ "视频新闻", "http://rss.sina.com.cn/sina_bn_opml.xml" }, 
			};
	
	public abstract String toSnatch();
	public Logger getLogger();
}
