package com.wukong.t8.utils;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.wukong.t8.action.LoginAction;

public class CheckLoginInterceptor extends AbstractInterceptor  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String USER_SESSION_KEY="userName";
	private static Logger logger=Logger.getLogger(CheckLoginInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		// TODO Auto-generated method stub
	
//		//对LoginAction不做拦截
//		Object object=actionInvocation.getAction();
//		if(object instanceof LoginAction){
//			System.out.println("退出拦截, 是LoginAction");
//			return actionInvocation.invoke();
//		}
//		
//		Map session=actionInvocation.getInvocationContext().getSession();
//		String userName=(String) session.get(USER_SESSION_KEY);
//		
//		if(userName!=null){
//			//存在的情况下进行后续操作
//			System.out.println(userName+"已经登录哦");
//			return actionInvocation.invoke();
//		}else{
//			//不存在的情况下终止操作  返回LOGIN
//			System.out.println("还没登录,返回登录界面");return "dadinggou2";
//		//	return Action.LOGIN;
//		}
		
		Utils.initHTMLLogger(logger, Utils.getWebRootPath()+"SnatchLog.html", true, Level.DEBUG);
		// Don't check user login action itself
		Object object=actionInvocation.getAction();
		if(object instanceof LoginAction){
			System.out.println("begin check login interceptor");
//			logger.fatal("执行拦截判断");
			return actionInvocation.invoke();
		}
		
		// check user login session
		ActionContext ctx=ActionContext.getContext();
		HttpServletRequest request=
			(HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
		Object userSession=request.getSession().getAttribute(USER_SESSION_KEY);
		
		// get session use session name
		if(userSession!=null){
//			logger.info(userSession+"已经登录,拦截通过"+new Date().toString());
			return actionInvocation.invoke();
		}else{
			System.out.println("no Login, forward login again");
			logger.info(new Date()+"没有登录,跳转至Login");
			return Action.LOGIN;
		}
		
	}
	
}
