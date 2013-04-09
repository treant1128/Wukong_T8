package com.wukong.t8.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wukong.t8.dao.EntryDAO;
import com.wukong.t8.dao.FeedDAO;
import com.wukong.t8.dao.Opml4channelDAO;
import com.wukong.t8.pojo.Entry;
import com.wukong.t8.pojo.Feed;
import com.wukong.t8.pojo.Opml4channel;
import com.wukong.t8.utils.EntryParser;
import com.wukong.t8.utils.FeedParser;
import com.wukong.t8.utils.Utils;

public class EntryAction extends ActionSupport implements BaseAction {

	private HttpServletRequest mRequest;
	private List<Entry> entries;
	private static EntryDAO entryDAO=EntryDAO.getInstance();
	public static Opml4channelAction o4cAction=new Opml4channelAction();
	private static int i=0;
//	private static FeedDAO feedDAO=FeedDAO.getInstance();
//	private static List<String> allFeedTitle=null;
	
	private static Set<Opml4channel> allO4C=null;
//	private static Set<String> allFeedFK_URLs=null;
	private static Set<String> allEntryGuids=null;
//	private Logger logger=getLogger();
	private static ExecutorService executorService2=Executors.newFixedThreadPool(THREAD_NUMBER, new ThreadFactory(){

		public Thread newThread(Runnable r) {
			// TODO Auto-generated method stub
			return new Thread(r);
		}
		
	});
	private static boolean isFirst=false;
	private String property;
	
	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
	
	public String getProperty(){
		return property;
	}
	
	public void setProperty(String property){
		this.property=property;
	}
	
	public String toSnatch(){
//		if(!isFirst){
			init();  //only once
//			isFirst=!isFirst;
//		}
		
		if(allO4C!=null&&allO4C.size()!=0){
			final Iterator<Opml4channel> iterator=allO4C.iterator();
			i++;
//			logger.fatal(new Date()+"抓取次数="+i);
//			System.out.println("Count=="+i);
			while(iterator.hasNext()){
				executorService2.submit(new Runnable(){

					public void run() {
						// TODO Auto-generated method stub
						saveEntry(iterator.next());
					}
					
				});
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 填充entry表
	 * @return
	 */
	private void saveEntry(Opml4channel o4c){
		if(o4c!=null){			
			List<Entry> entryList=EntryParser.getEntryListByOpmlOutlineXmlUrl(o4c);
			for(Entry entry:entryList){//System.out.println("==="+entry.getEntryTitle());
				if(allEntryGuids.add(entry.getEntryGuid())){
					entryDAO.save(entry);
				}
			}
		}
	}
	
	/**
	 * 初次从数据库加载到内存HashSet
	 */
	private void init(){		
		allO4C=new HashSet<Opml4channel>();
		allEntryGuids=new HashSet<String>();

		initO4CSet();
		initEntrySet();
		System.out.println("初始化");
//		Utils.initHTMLLogger(logger, Utils.getWebRootPath()+"SnatchLog.html", true, Level.DEBUG);
//		logger.info("Entry之toSnatch初始化完成-only once");
//		o4cAction.mainToSnatch();
	}
	
	@SuppressWarnings("unchecked")
	private void initO4CSet(){
		List<Opml4channel> originalO4C=Opml4channelDAO.getInstance().findAll();
		for(Opml4channel o4c:originalO4C){
			allO4C.add(o4c);
		}
	}

	@SuppressWarnings("unchecked")
	private void initEntrySet(){
		List<Entry> originalEntries=EntryDAO.getInstance().findAll();
		for(Entry e:originalEntries){
			allEntryGuids.add(e.getEntryGuid());
		}
	}
	
	public String toSearch(){
		if(property==null||property.trim().length()==0){
			return "NoKeywords";
		}
		ActionContext.getContext().getSession().put("userSecretKeys", SECRET_KEY_PRE_URL+LoginAction.secretKeys);
		mRequest=ServletActionContext.getRequest();
		String seleFieldValue=mRequest.getParameter("optionRadio");
		
		if(seleFieldValue==null){
			seleFieldValue="entryTitle";//System.out.println("ppp="+property);
			entries=EntryDAO.getInstance().getEntryListByProperty(seleFieldValue , property, null, null);
		}else{
			String timePicker1=mRequest.getParameter("timePicker1");
			String timePicker2=mRequest.getParameter("timePicker2");
			
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(timePicker1.length()==0&&timePicker2.length()==0){
				System.out.println("没有选择起始和终结时间----查所有的");
				entries=EntryDAO.getInstance().getEntryListByProperty(seleFieldValue , property, null, null);
			}else if(timePicker1.length()==0&&timePicker2.length()>0){
				try {
					Date seleDate=format.parse(timePicker2);
					Date currDate=new Date();
					if(seleDate.before(currDate)){
						entries=EntryDAO.getInstance().getEntryListByProperty(seleFieldValue, property, timePicker2, format.format(currDate));
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(timePicker1.length()>0&&timePicker2.length()==0){
				try {
					Date seleDate=format.parse(timePicker1);
					Date currDate=new Date();
					if(seleDate.before(currDate)){
						entries=EntryDAO.getInstance().getEntryListByProperty(seleFieldValue, property, timePicker1, format.format(currDate));
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				try {
					Date seleDate1=format.parse(timePicker1);
					Date seleDate2=format.parse(timePicker2);
					System.out.println(333);
					if(seleDate1.before(seleDate2)){
						entries=EntryDAO.getInstance().getEntryListByProperty(seleFieldValue, property, timePicker1, timePicker2);
					}else{
						entries=EntryDAO.getInstance().getEntryListByProperty(seleFieldValue, property, timePicker2, timePicker1);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		Map<String, Object> session=(Map<String, Object>) ActionContext.getContext().getSession();
		if(entries!=null){
			session.put("entries", entries);
			session.put("entrySize", entries.size());
//			logger.info("执行了一次时间查询");
			return "toEntries";
		}else{
			return "NoKeywords";
		}
		
	}

	public Logger getLogger() {
		// TODO Auto-generated method stub
		return Logger.getLogger(EntryAction.class);
	}
	
	public static void main(String[] args){
		EntryAction action=new EntryAction();
		action.toSnatch();
	}
}
