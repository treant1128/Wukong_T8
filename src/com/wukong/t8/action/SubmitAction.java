package com.wukong.t8.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wukong.t8.dao.EntryDAO;
import com.wukong.t8.dao.RepositoryDAO;
import com.wukong.t8.pojo.Entry;
import com.wukong.t8.pojo.Repository;

public class SubmitAction extends ActionSupport {

	private static String UUID_URL_GET=null;
	private static String TASK_URL_POST=null;
	private HttpServletRequest mRequest;
	private static Properties props=new Properties();
	
	static{
		try {
			props.load(SubmitAction.class.getResourceAsStream("/properties/audit.url.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UUID_URL_GET=props.getProperty("shandong.getUUID.url");
		TASK_URL_POST=props.getProperty("shandong.postTask.url");
	}
	public String toSubmit(){
		mRequest=ServletActionContext.getRequest();
		
		if(LoginAction.power.contains("w")){
			updateEntry();
			return "modifyParamsSuccess";
		}else{
			
			if(LoginAction.nickname.contains("anhui")){
				UUID_URL_GET=props.getProperty("anhui.getUUID.url");
				TASK_URL_POST=props.getProperty("anhui.postTask.url");
			}
			
			String uuid=getUUIDByGet(UUID_URL_GET, "UTF-8");
			if(uuid==null){
				return ERROR;
			}
			
			String[] os=mRequest.getParameterValues("osCheckbox");
			String[] status=mRequest.getParameterValues("userStatus");
			String guid=mRequest.getParameter("addSendPlan.sendUrl");
			
			String json=composeJSONString(uuid, os, status); //  System.out.println("JSON="+json);
			if(submitTaskByPost(TASK_URL_POST, json, "UTF-8")){
				logSubmitToRepository(guid);
				return SUCCESS;
			}
		}
		return ERROR;	
	}
	
	@SuppressWarnings("unchecked")
	private void logSubmitToRepository(String guid){
	
		List<Entry> list=EntryDAO.getInstance().findByEntryGuid(guid);
		if(list!=null&&list.size()!=0){
			Entry entry=list.get(0);
			Repository r=new Repository();
			r.setRepoTitle(entry.getEntryTitle());
			r.setRepoLink(guid);
			if(entry.getEntryCategory()!=null){
				r.setRepoCategory((entry.getEntryCategory()));
			}
			r.setRepoOperator(LoginAction.nickname);
			r.setRepoSubDate(new Timestamp(System.currentTimeMillis()));
			r.setRepoStatus("´ýÉó...");
			RepositoryDAO.getInstance().save(r);
		}
		
	}
	
	private void updateEntry(){
		String guid=mRequest.getParameter("addSendPlan.sendUrl");
		String title=mRequest.getParameter("addSendPlan.sendTitle");
		String imgUrl=mRequest.getParameter("modifyTask_imgUrl");
		String priority=mRequest.getParameter("priority");
		int p=500;
		try{
			p=Integer.parseInt(priority);
		}catch(Exception e){
			
		}
		System.out.println("Title="+title);
		System.out.println("imgUrl="+imgUrl);
		System.out.println("priority="+priority);
		EntryDAO.getInstance().updateByGuid(title, imgUrl, p, guid);
	}
	
	public String getUUIDByGet(String urlPath, String charsetName){
		String uuid=null;
		HttpURLConnection conn=(HttpURLConnection) getConnectionByURL(urlPath);
		InputStream stream=null;
		try {
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			stream=conn.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			//System.out.println("Get×´Ì¬Âë="+conn.getResponseCode());
			if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
				StringBuffer buffer=new StringBuffer();
				BufferedReader reader=null;
				reader=new BufferedReader(new InputStreamReader(stream, charsetName));
				while((uuid=reader.readLine())!=null){
					buffer.append(uuid);
				}
				return buffer.toString();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally{
			if(stream!=null){
				try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			conn.disconnect();
		}
		return uuid;
	}
	
	public boolean submitTaskByPost(String urlPath, String jsonData, String charsetName){
		HttpURLConnection conn=(HttpURLConnection)getConnectionByURL(urlPath);
		
		try {
			conn.setRequestMethod("POST");
			conn.setReadTimeout(5000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type","application/json");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		OutputStream stream=null;
		try { // System.out.println("111Post×´Ì¬Âë="+conn.getResponseCode());  ·ÅÔÚsetRequestPropertyÖ®Ç°¾Í±¨Òì³£
//			if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
				byte[] b=jsonData.getBytes(charsetName);
				conn.setRequestProperty("Content-Length", String.valueOf(b.length));
				stream=conn.getOutputStream();
				stream.write(b);
				stream.flush();
				
//				System.out.println("Post×´Ì¬Âë="+conn.getResponseCode());
				if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
					return true;
				}
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(stream!=null){
				try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			conn.disconnect();
		}
		return false;
	}
	
	public URLConnection getConnectionByURL(String urlPath){
		URL url=null;
		try {
			url=new URL(urlPath);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		URLConnection conn=null;
		try {
			conn=url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	private String composeJSONString(String uuid, String[] os, String[] status){
		StringBuffer sb=new StringBuffer();
		String temp=null;
		sb.append("{\"uuid\":");
		sb.append("\""+uuid+"\",");
		
		sb.append("\"newTask\":{\"userType\":{\"Android\":");
		if(os==null||(os!=null&&Arrays.binarySearch(os, "os1")<0)){
			temp="false";
		}else{
			temp="true";
		}
		sb.append(temp+",");
		temp=null;
		
//		sb.append("\"iPhone\":");
		sb.append("\"Iphone\":");
		if(os==null||(os!=null&&Arrays.binarySearch(os, "os2")<0)){
			temp="false";
		}else{
			temp="true";
		}
		sb.append(temp+",");
		temp=null;
		
//		sb.append("\"WP8\":");
		sb.append("\"Nokia\":");
		if(os==null||(os!=null&&Arrays.binarySearch(os, "os3")<0)){
			temp="false";
		}else{
			temp="true";
		}
		sb.append(temp+",");
		temp=null;
		
		sb.append("\"³ÁÄ¬\":");
		if(status==null||(status!=null&&Arrays.binarySearch(status, "status1")<0)){
			temp="false";
		}else{
			temp="true";
		}
		sb.append(temp+",");
		temp=null;
		
		sb.append("\"»îÔ¾\":");
		if(status==null||(status!=null&&Arrays.binarySearch(status, "status2")<0)){
			temp="false";
		}else{
			temp="true";
		}
		sb.append(temp+",");
		temp=null;
		
		sb.append("\"·Ç»îÔ¾\":");
		if(status==null||(status!=null&&Arrays.binarySearch(status, "status3")<0)){
			temp="false";
		}else{
			temp="true";
		}
		sb.append(temp+"},");
		temp=null;
		
		sb.append("\"tHeader\":");
		sb.append("\""+mRequest.getParameter("addSendPlan.sendTitle")+"\",");
		
		sb.append("\"tURL\":");
		sb.append("\""+mRequest.getParameter("addSendPlan.sendUrl")+"\",");
		
		sb.append("\"tDateTime\":");
		sb.append("\""+mRequest.getParameter("addSendPlan.date")+"\",");
		
		sb.append("\"tPriv\":");
		sb.append("\""+mRequest.getParameter("addSendPlan.prio")+"\",");
		
		sb.append("\"statu\":{\"audit\":");
		sb.append("\""+"info\",");
		
		sb.append("\"icon\":");
		sb.append("\""+"icon-info-sign\"},");
		
		sb.append("\"region\":");
		sb.append("\""+LoginAction.region+"\",");
		
		sb.append("\"tOperator\":");
		sb.append("\""+LoginAction.nickname+"\",");
		
		sb.append("\"_id\":");
		sb.append("\""+uuid+"\"},");
		
		temp=LoginAction.nickname;
		sb.append("\"user_login\":");
		sb.append("\""+temp.substring(temp.lastIndexOf("-")+1));
		sb.append("\"}");
		temp=null;
		
		return sb.toString();
	}
		
}
