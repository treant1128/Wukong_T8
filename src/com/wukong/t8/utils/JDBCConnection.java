package com.wukong.t8.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.wukong.t4.common.DBToolkit;

public class JDBCConnection {
	
	private static Connection conn = null;
	private static PreparedStatement prmt = null;
	private static ResultSet rs = null;

	private static String url=null;
	private static String username=null;
	private static String password=null;
	private static Properties props=new Properties();
	
	static {
		try {
			props.load(DBToolkit.class.getResourceAsStream("/properties/c3p0.datasource.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		url = (props.getProperty("datasource.sp049.jdbc.url"));
        username = (props.getProperty("datasource.sp049.jdbc.username"));
        password = (props.getProperty("datasource.sp049.jdbc.password"));
        //加载驱动
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建一个数据库链接
	 * @return
	 */
	public static Connection getConnection(){
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return conn;
	}
	
	
	
	
	public static void closeAll(Connection conn, PreparedStatement psmt,
			ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {

		}
	}
}
