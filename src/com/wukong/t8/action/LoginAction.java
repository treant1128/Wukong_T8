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
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.WriterAppender;
import org.apache.struts2.interceptor.SessionAware;


import com.opensymphony.xwork2.ActionSupport;
import com.wukong.t4.common.DBToolkit;
import com.wukong.t4.common.DESUtils;
import com.wukong.t8.dao.ChannelDAO;
import com.wukong.t8.pojo.Channel;
import com.wukong.t8.utils.CheckLoginInterceptor;
import com.wukong.t8.utils.Utils;

public class LoginAction extends ActionSupport implements SessionAware, BaseAction {
	public static final String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCAAzp8YDDp7y3OmWeoobO6YuR3DF1gougKsY3RulBwlTQqQz5HuqxgkDDVNUFlfExWMe5mEr0qdZ1n/vLepLcT6thAHRadi+CfhypT10B0HDblyB7W6OIREZEQErrOSLcc9Knjn3tL41yxemVp0XK0C3MG6q1ikwWQXNLW6nPXaQIDAQAB";
	public static String secretKeys=null;
	public String userName=null;
	public String userPassword=null;
	private Channel channel=null;
	private Map<String, Object> session=null;
	public static List<Channel> channels=null;
//	private String loggerPath=null;
	private Logger logger=getLogger();
	public static int TIME_DELAY=3*3600*1000; // one hour -> Milliseconds
	
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
//		
//		if(input == null || "".equals(input)){
//			return "error";
//		}
//		String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCAAzp8YDDp7y3OmWeoobO6YuR3DF1gougKsY3RulBwlTQqQz5HuqxgkDDVNUFlfExWMe5mEr0qdZ1n/vLepLcT6thAHRadi+CfhypT10B0HDblyB7W6OIREZEQErrOSLcc9Knjn3tL41yxemVp0XK0C3MG6q1ikwWQXNLW6nPXaQIDAQAB";
//		
//		DESUtils crypt = new DESUtils(key);
//		try {
//			input =  crypt.decrypt(input);
//		} catch (Exception e) {
//			System.out.println("2");
//			e.printStackTrace();
//			return "error";
//		}
//		String[] userInfo = input.split("&");
//		if(userInfo==null||userInfo.length<3){
//			return "error";
//		}
//		String username =userInfo[0];
//		String password = userInfo[1];
//		String service = userInfo[2];
//		String path = WebUtil.getBasePath();
//		String localPower="";
//		boolean flag=false;
//	    Properties props = new Properties();
//	    try {
//			props.load(DBToolkit.class.getResourceAsStream("/properties/t4-configuration.properties"));
//			localPower = (props.getProperty("power"));
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		Utils.initHTMLLogger(logger, Utils.getWebRootPath()+"SnatchLog.html", true, Level.DEBUG);
		Connection conn  = DBToolkit.getConnection();
		if(conn==null){System.out.println("链接失败");}
		StringBuffer sql = new StringBuffer("select * from operator where name = '");  //table operator
		sql.append(userName).append("' and password = '").append(userPassword).append("'");
		ResultSet rs = DBToolkit.executeQuery(conn, sql.toString());
		try {
			if(rs.next()){
//				String powers = rs.getString("power");
//				String power[] = powers.split(",");
//				for(int i = 0 ; i < power.length ; i ++){
//					if(power[i].equals("-1")){
//						flag=true;
//					}else if(power[i].equals(localPower)){
//						flag=true;
//					}
//					
//				}
				String name=rs.getString("name");
				String password=rs.getString("password");
				region=rs.getString("region");
				power=rs.getString("upower");   System.out.println("POWER="+power);
				if(name==null||password==null){
					return "LoginFailture";
				}
				
				DESUtils crypt=new DESUtils(key);
				try {
					secretKeys = URLEncoder.encode(crypt.encrypt(name+"&"+password+"&sms"), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				nickname=rs.getString("region")+"-"+name;    //field-->region&&name
				session.put(CheckLoginInterceptor.USER_SESSION_KEY, nickname);
				//加载频道列表
				channels=ChannelDAO.getInstance().findAll();
				session.put("channels", channels);
				
				initTimeDelay();
			}else{
				return "LoginFailture";
								
//			///服务器连不上暂且饶过	
//				
//				Map<String, Object> session=(Map<String, Object>) ActionContext.getContext().getSession();
//
//				//加载频道列表
////				request=(Map<String, Object>) ActionContext.getContext().get("request");
//				List<Channel> channels=ChannelDAO.getInstance().findAll();
//				session.put("channels", channels);
//				
//				return "LoginSuccess";
//			///服务器连不上暂且饶过	
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
	
	/**
	 * 跟踪登录信息
	 */
//	public Logger getLogger() {
//		// TODO Auto-generated method stub
//		Logger logger=Logger.getLogger(LoginAction.class);
//		SimpleLayout layout=new SimpleLayout();
//		FileAppender appender=null;
//		
//		try {
//			appender=new FileAppender(layout, loggerPath, true);//true to append
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		logger.addAppender(appender);
//		logger.setLevel((Level)Level.DEBUG);
//		return logger;
//	}
	private void initTimeDelay(){
		String bakPath=Utils.getWebRootPath()+"lastLoginTime.bak";
		FileWriter writer=null;
		String lastLogin=Utils.getLocalBak(bakPath);
		try {
			writer = new FileWriter(bakPath, false);  		//override
			if(lastLogin==null||lastLogin.length()==0){     //第一次部署时bak文件根本不存在
				TIME_DELAY=5000;
				logger.info(nickname+"--第一次登录"+new Date()+",5S后开始抓取");
				
			}else {
				long interval=System.currentTimeMillis()-Long.valueOf(lastLogin);
//				TIME_DELAY=(int) ((interval>=TIME_DELAY)?3000:(TIME_DELAY-interval));
				logger.info(nickname+"--距上次登录间隔"+interval+"毫秒,"
//						+TIME_DELAY+"毫秒后开始下一次抓取"
						);
				Opml4channelAction.timer.cancel();
				Opml4channelAction.timer=null;
			}
			
			new Timer().schedule(new TimerTask(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Opml4channelAction o4cAction=new Opml4channelAction();
					o4cAction.toSnatch();
				}
				
			}, TIME_DELAY);
			
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
	
	public String toSnatch() {
		// TODO Auto-generated method stub
		return null;
	}
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return Logger.getLogger(LoginAction.class);
	}
	
}
