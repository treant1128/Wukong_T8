package com.wukong.t4.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBToolkit {

	private static final Log log=LogFactory.getLog(DBToolkit.class);
	private static String url=null;
	private static String username=null;
	private static String password=null;
	private static Properties props=new Properties();
	
	static {
		try {
			props.load(DBToolkit.class.getResourceAsStream("/properties/c3p0.datasource.properties"));
		} catch (IOException e) {
			 log.error("#ERROR# :系统加载sysconfig.properties配置文件异常，请检查！", e);
			e.printStackTrace();
		}
		
		url = (props.getProperty("datasource.sp049.jdbc.url"));
        username = (props.getProperty("datasource.sp049.jdbc.username"));
        password = (props.getProperty("datasource.sp049.jdbc.password"));
        //加载驱动
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			log.error("#ERROR# :加载数据库驱动异常，请检查！", e);
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
			log.error("#ERROR# :创建数据库连接发生异常，请检查！", e);
			e.printStackTrace();
		}
		return conn;
	}
	
	
	/**
     * 在一个数据库连接上执行一个静态SQL语句查询
     *
     * @param conn            数据库连接
     * @param staticSql 静态SQL语句字符串
     * @return 返回查询结果集ResultSet对象
     */
    public static ResultSet executeQuery(Connection conn, String staticSql) {
            ResultSet rs = null;
            try {
                    //创建执行SQL的对象
                    Statement stmt = conn.createStatement();
                    //执行SQL，并获取返回结果
                    rs = stmt.executeQuery(staticSql);
            } catch (SQLException e) {
                    log.error("#ERROR# :执行SQL语句出错，请检查！\n" + staticSql, e);
            }
            return rs;
    }

    /**
     * 在一个数据库连接上执行一个静态SQL语句
     *
     * @param conn            数据库连接
     * @param staticSql 静态SQL语句字符串
     */
    public static void executeSQL(Connection conn, String staticSql) {
            try {
                    //创建执行SQL的对象
                    Statement stmt = conn.createStatement();
                    //执行SQL，并获取返回结果
                    stmt.execute(staticSql);
            } catch (SQLException e) {
                    log.error("#ERROR# :执行SQL语句出错，请检查！\n" + staticSql, e);
            }
    }

    /**
     * 在一个数据库连接上执行一批静态SQL语句
     *
     * @param conn        数据库连接
     * @param sqlList 静态SQL语句字符串集合
     */
    public static void executeBatchSQL(Connection conn, List<String> sqlList) {
            try {
                    //创建执行SQL的对象
                    Statement stmt = conn.createStatement();
                    for (String sql : sqlList) {
                            stmt.addBatch(sql);
                    }
                    //执行SQL，并获取返回结果
                    stmt.executeBatch();
            } catch (SQLException e) {
                    log.error("#ERROR# :执行批量SQL语句出错，请检查！", e);
            }
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
            if (conn == null) return;
            try {
                    if (conn!=null&&!conn.isClosed()) {
                            //关闭数据库连接
                            conn.close();
                    }
                    if(stmt!=null&&!stmt.isClosed()){
                    	stmt.close();
                    }
                    if(rs!=null){
                    	rs.close();
                    }
            } catch (SQLException e) {
                    log.error("#ERROR# :关闭数据库连接发生异常，请检查！", e);
            }
    }

}
