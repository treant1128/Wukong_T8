package com.wukong.t8.action;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;


import com.opensymphony.xwork2.ActionSupport;
import com.wukong.t4.common.DBToolkit;
import com.wukong.t4.common.DESUtils;
import com.wukong.t8.dao.ChannelDAO;
import com.wukong.t8.pojo.Channel;
import com.wukong.t8.utils.CheckLoginInterceptor;
import com.wukong.t8.utils.Utils;

public class LoginAction extends ActionSupport implements SessionAware{
	public static final String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCAAzp8YDDp7y3OmWeoobO6YuR3DF1gougKsY3RulBwlTQqQz5HuqxgkDDVNUFlfExWMe5mEr0qdZ1n/vLepLcT6thAHRadi+CfhypT10B0HDblyB7W6OIREZEQErrOSLcc9Knjn3tL41yxemVp0XK0C3MG6q1ikwWQXNLW6nPXaQIDAQAB";
	public static String secretKeys=null;
	public String userName=null;
	public String userPassword=null;
	private Channel channel=null;
	private Map<String, Object> session=null;
	public static List<Channel> channels=null;

	private Logger logger=null;
	private static Opml4channelAction o4cAction=new Opml4channelAction();
//	public static int TIME_DELAY=3*3600*1000; // one hour -> Milliseconds
	
	public static String nickname=null;
	public static String region=null;
	public static String power=null;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	@SuppressWarnings("unchecked")
	public String execute(){
		logger=Logger.getLogger(LoginAction.class);
		String rootPath=Utils.getWebRootPath();
		Utils.initHTMLLogger(logger, rootPath+"SnatchLog.html", true, Level.DEBUG);
		Connection conn  = DBToolkit.getConnection();

		StringBuffer sql = new StringBuffer("select * from operator where name = '");  //table operator
		sql.append(userName).append("' and password = '").append(userPassword).append("'");
		ResultSet rs = DBToolkit.executeQuery(conn, sql.toString());
		
		try {
			if(rs.next()){
				String name=rs.getString("name");
				String password=rs.getString("password");
				region=rs.getString("region");
				power=rs.getString("upower"); //  System.out.println("POWER="+power);
				if(name==null||password==null){
					return "LoginFailture";
				}
				
				DESUtils crypt=new DESUtils(key);
				try {
					secretKeys = URLEncoder.encode(crypt.encrypt(name+"&"+password+"&sms"), "UTF-8");
	//				secretKeys="o9goW9fNwdfe9AL%2BxcU%2BH0%2F8gOlMWRi%2F";
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				nickname=rs.getString("region")+"-"+name;
				session.put(CheckLoginInterceptor.USER_SESSION_KEY, nickname);
				//加载频道列表
				channels=ChannelDAO.getInstance().findAll();
				session.put("channels", channels);
				
				initTimeDelay(rootPath+"lastLoginTime.bak", rootPath+"SnatchLog.html");
			}else{
				return "LoginFailture";	
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			DBToolkit.closeConnection(conn, null, rs);
		}
		
		return "LoginSuccess";
	}
	
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}
	
	private synchronized void initTimeDelay(String bakPath, String logPath){
		FileWriter writer=null;
		String lastLogin=Utils.getLocalBak(bakPath);
		try {
			if(lastLogin==null||lastLogin.length()==0){     //第一次部署时bak文件根本不存在
				logger.info(nickname+"--第一次登录"+new Date()+", 2S后开始抓取");
				o4cAction.toSnatch();
			}else {
				long interval=System.currentTimeMillis()-Long.valueOf(lastLogin);
				writer=new FileWriter(logPath, true);
				StringBuffer sb=new StringBuffer();
				sb.append("<tr bgcolor='#CCAA88'>");
				sb.append("<td>").append(new Date()).append("</td>");
				sb.append("<td>").append(Thread.currentThread().getName()).append("</td>");
				sb.append("<td>INFO</td>");
				sb.append("<td>Login-Log</td>");
				sb.append("<td>").append(nickname+"--距上一位访问者"+interval+"毫秒").append("</td>");
				sb.append("<tr>");
				writer.write(sb.toString());
				writer.flush();
			}
			
			writer = new FileWriter(bakPath, false);  		//override bak time
			writer.write(Long.toString(System.currentTimeMillis()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
