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
			 log.error("#ERROR# :ϵͳ����sysconfig.properties�����ļ��쳣�����飡", e);
			e.printStackTrace();
		}
		
		url = (props.getProperty("datasource.sp049.jdbc.url"));
        username = (props.getProperty("datasource.sp049.jdbc.username"));
        password = (props.getProperty("datasource.sp049.jdbc.password"));
        //��������
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			log.error("#ERROR# :�������ݿ������쳣�����飡", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * ����һ�����ݿ�����
	 * @return
	 */
	public static Connection getConnection(){
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			log.error("#ERROR# :�������ݿ����ӷ����쳣�����飡", e);
			e.printStackTrace();
		}
		return conn;
	}
	
	
	/**
     * ��һ�����ݿ�������ִ��һ����̬SQL����ѯ
     *
     * @param conn            ���ݿ�����
     * @param staticSql ��̬SQL����ַ���
     * @return ���ز�ѯ�����ResultSet����
     */
    public static ResultSet executeQuery(Connection conn, String staticSql) {
            ResultSet rs = null;
            try {
                    //����ִ��SQL�Ķ���
                    Statement stmt = conn.createStatement();
                    //ִ��SQL������ȡ���ؽ��
                    rs = stmt.executeQuery(staticSql);
            } catch (SQLException e) {
                    log.error("#ERROR# :ִ��SQL���������飡\n" + staticSql, e);
            }
            return rs;
    }

    /**
     * ��һ�����ݿ�������ִ��һ����̬SQL���
     *
     * @param conn            ���ݿ�����
     * @param staticSql ��̬SQL����ַ���
     */
    public static void executeSQL(Connection conn, String staticSql) {
            try {
                    //����ִ��SQL�Ķ���
                    Statement stmt = conn.createStatement();
                    //ִ��SQL������ȡ���ؽ��
                    stmt.execute(staticSql);
            } catch (SQLException e) {
                    log.error("#ERROR# :ִ��SQL���������飡\n" + staticSql, e);
            }
    }

    /**
     * ��һ�����ݿ�������ִ��һ����̬SQL���
     *
     * @param conn        ���ݿ�����
     * @param sqlList ��̬SQL����ַ�������
     */
    public static void executeBatchSQL(Connection conn, List<String> sqlList) {
            try {
                    //����ִ��SQL�Ķ���
                    Statement stmt = conn.createStatement();
                    for (String sql : sqlList) {
                            stmt.addBatch(sql);
                    }
                    //ִ��SQL������ȡ���ؽ��
                    stmt.executeBatch();
            } catch (SQLException e) {
                    log.error("#ERROR# :ִ������SQL���������飡", e);
            }
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
            if (conn == null) return;
            try {
                    if (conn!=null&&!conn.isClosed()) {
                            //�ر����ݿ�����
                            conn.close();
                    }
                    if(stmt!=null&&!stmt.isClosed()){
                    	stmt.close();
                    }
                    if(rs!=null){
                    	rs.close();
                    }
            } catch (SQLException e) {
                    log.error("#ERROR# :�ر����ݿ����ӷ����쳣�����飡", e);
            }
    }

}
