package com.wukong.t8.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;


import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wukong.t4.common.DBUUID;
import com.wukong.t4.entitys.SmsTask;

public class AuditAction extends ActionSupport {
	private HttpServletRequest mRequest;
	private Properties props=null;
	
	private String send_title;
	private String send_o_url;
	private String content;

	public String getSend_title() {
		return send_title;
	}

	public void setSend_title(String sendTitle) {
		send_title = sendTitle;
	}

	public String getSend_o_url() {
		return send_o_url;
	}

	public void setSend_o_url(String sendOUrl) {
		send_o_url = sendOUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String apply2Audit() throws Exception {
		mRequest=ServletActionContext.getRequest();
		mRequest.setCharacterEncoding("UTF-8");
		props=new Properties();
		props.load(AuditAction.class.getResourceAsStream("/properties/jslt.http-post.properties"));
		String url=props.getProperty("datasource.jslt.http-post.url");
//		构造HttpPost请求 
//		根据115.239.134.51  cd ~/treant/node.js audit2MySQL.js的字段构造JSON数据包
//		插入到数据库   给审核平台提供数据
		System.out.println("PostURL="+url);
		
		
		
		
		String json=composeJSONString(); 
		System.out.println("JSON="+json);
		if(submitTaskByPost(url, json, "UTF-8")){
	//		logSubmitToRepository(guid);  
			System.out.println("OOOKKKOOOKKK");
			return SUCCESS;
			}
		
		return ERROR;
	}
	
	public String composeJSONString(){
		String[] numFiles=mRequest.getParameterValues("numFiles");
		String srcfile=formatSRCFile(numFiles);   //选择号码包
										//去重任务
		String send_time_type=mRequest.getParameter("smsTask.sendTimeType");   //下发时间
		String method=mRequest.getParameter("smsTask.method");   //方式
//		String send_title=mRequest.getParameter("smsTask.sendTitle");  //下发语
//		String send_o_url=mRequest.getParameter("smsTask.sendOUrl");//下发URL
//		String content=mRequest.getParameter("smsTask.content");//描述
		String priority=mRequest.getParameter("smsTask.priority");//优先级
		String long_title=mRequest.getParameter("smsTask.longTitle");//是否保留长标题
		if(long_title==null){
			long_title="0";
		}
		String jump=mRequest.getParameter("smsTask.jump");//跳出31597
		if(jump==null){
			jump="0";
		}
		String qc_type=mRequest.getParameter("smsTask.qcType");//去重规则
//		String interval_time=mRequest.getParameter(name)//下发时间段
		String dataDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
//		System.out.println("下发语send_title=="+send_title);
//		System.out.println("send_o_url="+send_o_url);
//		System.out.println("下发语content=="+content);
		
		String send_time=null;  System.out.println("smsTask.sendTimeType="+send_time_type);
		if(Integer.parseInt(send_time_type)==0){
			send_time=new SimpleDateFormat("yyyy-MM-dd HH:mm:00").format(new Date());
		}else if(Integer.parseInt(send_time_type)==1){
			send_time=mRequest.getParameter("smsTask.sendTime");
		}else{
			send_time="";
		}
		
		//需要一个临时是String交替使用  减少新对象的创建
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SmsTask task=new SmsTask();
		task.setPriority(Integer.parseInt(priority));
		task.setMethod(Integer.parseInt(method));
		task.setContent(content);
		task.setLongTitle(Integer.parseInt(long_title));
		task.setJump(Integer.parseInt(jump));
		task.setSendTitle(send_title);
		task.setSendOUrl(send_o_url);
		task.setSendTimeType(Integer.parseInt(send_time_type));
		task.setSendTime(send_time);/////使用send_time
		task.setIntervalTime("!!interval_time!!");
		task.setCreateTime(format.format(new Date()));
		task.setQcType(Integer.parseInt(qc_type));
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		task.setExcfile(sf.format(new Date())+System.getProperty("file.separator")+DBUUID.getID());
		task.setSendfile(sf.format(new Date())+System.getProperty("file.separator")+DBUUID.getID());
	//	task.setExcfile("--excfile--");
	//	task.setSendfile("--sendfile--");
	//	task.setSrcfile("--srcfile--");
		task.setSrcfile(srcfile);
		String name=LoginAction.nickname.substring(LoginAction.nickname.lastIndexOf("-")+1);
		task.setOpadmin(name);
		task.setOpStep(3);
		task.setNumCount(123321);
		task.setDataDate(dataDate);
		
//		StringBuffer sb=new StringBuffer();
//		sb.append("{");
//		sb.append("'priority':'").append(priority).append("',");
//		sb.append("'method':'").append(method).append("',");
//		sb.append("'content':'").append(content).append("',");
//		sb.append("'long_title':'").append(long_title).append("',");
//		sb.append("'jump':'").append(jump).append("',");
//		sb.append("'send_title':'").append(send_title).append("',");
//		sb.append("'send_o_url':'").append(send_o_url).append("',");
//		sb.append("'send_time_type':'").append(send_time_type).append("',");
//		sb.append("'send_time':'").append(send_time).append("',");
//		sb.append("'interval_time':'").append("!!interval_time!!").append("',");
//		sb.append("'create_time':'").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("',");
//		sb.append("'qc_type':'").append(qc_type).append("',");
//		sb.append("'excfile':'").append("**excfile**").append("',");
//		sb.append("'srcfile':'").append(srcfile).append("',");
//		sb.append("'sendfile':'").append("&&sendfile&&").append("',");
//		String name=LoginAction.nickname.substring(LoginAction.nickname.lastIndexOf("-")+1);
//		sb.append("'opadmin':'").append(name).append("',");
//		sb.append("'op_step':'").append("##op_step##").append("',");
//		sb.append("'num_count':'").append("@@num_count@@").append("',");
//		sb.append("'data_date':'").append(dataDate).append("'");
//		sb.append("}");
		
		JSONObject jsonObj=JSONObject.fromObject(task);
		return jsonObj.toString();
	}
	
	/**
	 * 把多选框的checkbox值拼接为用"|"分开的String
	 * @param srcfile
	 * @return
	 */
	private String formatSRCFile(String[] srcfile){
		StringBuffer sb=new StringBuffer();
		String filePath=mRequest.getParameter("filePath");
		for(String s:srcfile){
			sb.append(filePath+System.getProperty("file.separator")).append(s).append("|");
		}
		return sb.toString();
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
				
				System.out.println("Post状态码="+conn.getResponseCode());
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
	
	public static void main(String[] args){
		System.out.println(System.getProperty("file.separator"));
	}
}
