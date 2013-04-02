/**
 * 
 */
package com.wukong.t4.common;

/**
 * @author Administrator
 *
 */
import java.net.URL;
	public class WebUtil {
		// 获取应用绝对路径。
	    public static String getBasePath() {
        URL url = WebUtil.class.getResource("WebUtil.class");
        String path = url.getPath();
	        path = path.substring(0, path.lastIndexOf("WEB-INF"));
	        return path;
	    }
	}