/**
 * 
 */
package com.wukong.t4.admin.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;
import com.wukong.t4.common.DBToolkit;
import com.wukong.t4.common.DESUtils;
import com.wukong.t4.common.WebUtil;
import com.wukong.t4.entitys.Admin;

/**
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class AdminAction extends ActionSupport {

	private String input;
	private String action;
	/**
	 * request
	 */
	protected HttpServletRequest request;
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String login() throws IOException {
		if(input == null || "".equals(input)){
			return "error";
		}
		String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCAAzp8YDDp7y3OmWeoobO6YuR3DF1gougKsY3RulBwlTQqQz5HuqxgkDDVNUFlfExWMe5mEr0qdZ1n/vLepLcT6thAHRadi+CfhypT10B0HDblyB7W6OIREZEQErrOSLcc9Knjn3tL41yxemVp0XK0C3MG6q1ikwWQXNLW6nPXaQIDAQAB";
		
		DESUtils crypt = new DESUtils(key);
		try {
			input =  crypt.decrypt(input);
		} catch (Exception e) {
			System.out.println("2");
			e.printStackTrace();
			return "error";
		}
		String[] userInfo = input.split("&");
		if(userInfo==null||userInfo.length<3){
			return "error";
		}
		String username =userInfo[0];
		String password = userInfo[1];
		String service = userInfo[2];
		String path = WebUtil.getBasePath();
		String localPower="";
		boolean flag=false;
	    Properties props = new Properties();
	    try {
			props.load(DBToolkit.class.getResourceAsStream("/properties/t4-configuration.properties"));
			localPower = (props.getProperty("power"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	 
		Connection conn  = DBToolkit.getConnection();
		StringBuffer sql = new StringBuffer("select * from users where name = '");
		sql.append(username).append("' and password = '").append(password).append("'");
		ResultSet rs = DBToolkit.executeQuery(conn, sql.toString());
		try {
			if(rs.next()){
				String powers = rs.getString("power");
				String power[] = powers.split(",");
				for(int i = 0 ; i < power.length ; i ++){
					if(power[i].equals("-1")){
						flag=true;
					}else if(power[i].equals(localPower)){
						flag=true;
					}
					
				}
			}else{
				return "loginerror";
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DBToolkit.closeConnection(conn, null, rs);
		try{
		    if(flag){
			    Admin admin = new Admin();
			    admin.setAdminname(username);
			    admin.setPassword(password);
			    request.getSession().setAttribute("usersession", admin);
			
			    HttpSession session = request.getSession();
			    Object usertest = session.getAttribute("usersession");
			    request.getSession().setAttribute("serverName",request.getSession().getServletContext().getInitParameter("serverName"));
			
			    if(service==null||"sms".equals(service)){
			    	this.action="/smsManage/newSmsTask.do";
			    }else if("mms".equals(service)){
			    	this.action="/mmsManage/newMmsTask.do";
			    }
		    }else{
		    	return "selectServer";
		    }
		}catch(Exception e){
		  e.printStackTrace();
		}
		 
		return SUCCESS;
	}

	

	public String logout() {

		return SUCCESS;

	}

}
