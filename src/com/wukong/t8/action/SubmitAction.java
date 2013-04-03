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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wukong.t8.dao.EntryDAO;
import com.wukong.t8.dao.RepositoryDAO;
import com.wukong.t8.pojo.Entry;
import com.wukong.t8.pojo.Repository;

public class SubmitAction extends ActionSupport {

	private static final String UUID_URL_GET="http://audits.wukong.com/uuid";
	private static final String TASK_URL_POST="http://audits.wukong.com/task_add";
//  My Test Host URL Just Change The Port Without Nginx
//	private static final String UUID_URL_GET="http://220.181.49.164:8001/uuid";
//	private static final String TASK_URL_POST="http://220.181.49.164:8001/task_add";
	private HttpServletRequest mRequest;
		
	public String toSubmit(){
System.out.println("这块地方");
return SUCCESS;
//		String uuid=getUUIDByGet(UUID_URL_GET, "UTF-8");
//		if(uuid==null){
//			return ERROR;
//		}
//		
//		mRequest=ServletActionContext.getRequest();
//		String[] os=mRequest.getParameterValues("osCheckbox");
//		String[] status=mRequest.getParameterValues("userStatus");
//		String guid=mRequest.getParameter("addSendPlan.sendUrl");
//		
//		String json=composeJSONString(uuid, os, status); //  System.out.println("JSON="+json);
//		if(submitTaskByPost(TASK_URL_POST, json, "UTF-8")){
//			logSubmitToRepository(guid);
//			return SUCCESS;
//		}
//		return ERROR;	
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
			r.setRepoStatus("待审...");
			RepositoryDAO.getInstance().save(r);
		}
		
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
			//System.out.println("Get状态码="+conn.getResponseCode());
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
		try { // System.out.println("111Post状态码="+conn.getResponseCode());  放在setRequestProperty之前就报异常
//			if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
				byte[] b=jsonData.getBytes(charsetName);
				conn.setRequestProperty("Content-Length", String.valueOf(b.length));
				stream=conn.getOutputStream();
				stream.write(b);
				stream.flush();
				
//				System.out.println("Post状态码="+conn.getResponseCode());
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
		
		sb.append("\"沉默\":");
		if(status==null||(status!=null&&Arrays.binarySearch(status, "status1")<0)){
			temp="false";
		}else{
			temp="true";
		}
		sb.append(temp+",");
		temp=null;
		
		sb.append("\"活跃\":");
		if(status==null||(status!=null&&Arrays.binarySearch(status, "status2")<0)){
			temp="false";
		}else{
			temp="true";
		}
		sb.append(temp+",");
		temp=null;
		
		sb.append("\"非活跃\":");
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
