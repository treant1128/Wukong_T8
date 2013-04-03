package com.wukong.t8.action;

import java.util.LinkedHashMap;
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
	
//	public abstract String toSnatch();
	public Logger getLogger();
}
