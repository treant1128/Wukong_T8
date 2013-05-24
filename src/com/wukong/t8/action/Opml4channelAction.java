package com.wukong.t8.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wukong.t8.abstractpojo.AbstractChannel;
import com.wukong.t8.abstractpojo.AbstractNetsource;
import com.wukong.t8.dao.ChannelDAO;
import com.wukong.t8.dao.EntryDAO;
import com.wukong.t8.dao.NetsourceDAO;
import com.wukong.t8.dao.Opml4channelDAO;
import com.wukong.t8.pojo.Channel;
import com.wukong.t8.pojo.Entry;
import com.wukong.t8.pojo.Netsource;
import com.wukong.t8.pojo.Opml4channel;
import com.wukong.t8.utils.OPMLParser;
import com.wukong.t8.utils.Utils;


public class Opml4channelAction extends ActionSupport implements BaseAction {
	
	public static Timer timer=null;
	private static Set<String> o4cOpmlUrlSet=new HashSet<String>();  //全局  唯一 去重

//	private static List<Entry> allEntryList=null;
//	private Set<Entry> allEntrySet=null;
	private String opmlOutlineXmlUrl;
	
	private static int TIMER_INTERVAL=3*3600*1000;
	private static int TIMER_DELAY=5000;
	private static int currentPage=1;
	private String pageKey="1st";
	private static String tempURL="";
	private int totalPages=0;
	private int totalRows=0;
	private static final int NUM_PER_PAGE=50;
	private static boolean isAutomatic=true;
	private static EntryAction entryAction=null;
	private static EntryDAO entryDAO=null;
	private int errorCount=0;
	private Logger logger=getLogger();
	
	private Future<String> o4cFuture;

	private static Opml4channelDAO o4cDAO=Opml4channelDAO.getInstance();
	private static ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory(){

		public Thread newThread(Runnable r) {
			// TODO Auto-generated method stub
			return new Thread(r);
		}
		
	});
	//设置为static 全局变量
	private static int flag=0, ratio=10; //时间间隔比值         无需每次抓取Entry之前都初始化  	

	public String getOpmlOutlineXmlUrl() {
		return opmlOutlineXmlUrl;
	}

	public void setOpmlOutlineXmlUrl(String opmlOutlineXmlUrl) {
		this.opmlOutlineXmlUrl = opmlOutlineXmlUrl;
	}

	public static int getTIMER_INTERVAL() {
		return TIMER_INTERVAL;
	}

	public static void setTIMER_INTERVAL(int tIMERINTERVAL) {
		TIMER_INTERVAL = tIMERINTERVAL;
	}
	
	public static int getCurrentPage() {
		return currentPage;
	}

	public static void setCurrentPage(int currentPage) {
		Opml4channelAction.currentPage = currentPage;
	}

	public String getPageKey() {
		return pageKey;
	}

	public void setPageKey(String pageKey) {
		this.pageKey = pageKey;
	}


	static{
		refreshO4COpmlUrl();
		entryAction=new EntryAction();
		entryDAO=EntryDAO.getInstance();
	}
	
	public String toSnatch(){
		init();
		logger.info(new Date()+"->初始化完成");
		
		if(isAutomatic){
			
			getTimer().schedule(new TimerTask(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					logger.info(new Date()+"->开始mainToSnatch");
					
					mainToSnatch();   
					
				}

			}, TIMER_DELAY, TIMER_INTERVAL);

		}else{		
			logger.info(LoginAction.nickname+" 改为手动抓取");
			mainToSnatch();
		}
		
		return SUCCESS;
	}
	
	/**
	 * 执行Snatch的主函数
	 * @return
	 */
	public String mainToSnatch() {

		if(initperiodically()&& o4cFuture.isDone()){ //block until retrieve result 
			logger.info(new Date()+"->去EntryAction->Snatch");
			entryAction.toSnatch();//  System.out.println("+%%%%********");
		}else{
			errorCount++;
			logger.error("ErrorCount="+errorCount);
			
			if(errorCount>8){
				logger.error("初始化uncomplicated,rollback次数="+errorCount);
				return ERROR;
			}
			snooze();
		}	
		return SUCCESS;
	}
	
	private void snooze(){
		try {
			Thread.sleep(5000);
			logger.warn("Sleep五秒后重新Snatch...");
			mainToSnatch();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 周期地验证opml
	 * @return
	 */
	public boolean initperiodically(){
		if(flag%ratio==0){ 
			logger.debug("读取local时flag="+flag);//System.out.println("验证opml时flag="+flag);
			return initWhenFirstlyExecute();
		}else{
			flag+=1;  //计数并绕过
			logger.info("计数--且路过flag="+flag);
			return true;
		}
	}
	
	/**
	 * 依次加载netsource->channel->opml4channel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean initWhenFirstlyExecute(){
		NetsourceDAO netsourceDAO=NetsourceDAO.getInstance();
			
		if(netsourceDAO.findAll().size()!=0){
			
			ChannelDAO channelDAO=ChannelDAO.getInstance();
			List<Channel> channelList=(List<Channel>)channelDAO.findAll();
			int channelCount=channelList.size();
			
			if(channelCount!=0){   //channel表中已有数据
				logger.info("已有频道数="+channelCount);
				
				for(int i=0;i<channelCount;i++){
					final Channel channel=channelList.get(i);
					
					o4cFuture=executorService.submit(new Callable<String>(){

						public String call() throws Exception {
							// TODO Auto-generated method stub
							loadDataToOpml4Channel(channel);
							return "^_^";
						}
							
					});
				}
				
				flag+=1;  //执行成功才计数  初值为0  不成功不变
				logger.info(new Date()+"opml4channel刷新完成flag="+flag);
//				allEntryList=null;//清空  下次查询再刷新
			}else{
				loadDataToChannel(channelDAO);
				logger.debug(new Date()+"->初次导入channel完成");
				return false;
			}
		}else{
			loadDataToNetsource(netsourceDAO);
			logger.debug(new Date()+"->初次运行,加载netsource完成");
			return false;
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);   //主线程sleep 5S确保本地文件加载完毕
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
		
	/**
	 * 根据频道channel向其相关的表-->opml4channel中插入子频道数据附带"去重"
	 * @param channel
	 */
	private void loadDataToOpml4Channel(Channel channel){
		//由频道channel_opmlurl路径重新获取对应的Opml4channel的集合   
		List<Opml4channel> o4cList=OPMLParser.getOPML4ChannelList(channel);
		
		for(Opml4channel o4c:o4cList){
			//opml4channel中不含有才插入(去重)  用xmlUrl去判定
			if(o4cOpmlUrlSet.add(o4c.getOpmlOutlineXmlUrl())){
				o4cDAO.save(o4c);  
			}	
		}
	}
		
	/**
	 * 获取去重过的Opml的URL集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static void refreshO4COpmlUrl(){
		List<Opml4channel> allO4C=o4cDAO.findAll();
		
		if(allO4C!=null&&allO4C.size()>0){
			Opml4channel o4c=null;
			String xmlUrl=null;
			
			for(Iterator iterator=allO4C.iterator();iterator.hasNext();){
				o4c=(Opml4channel) iterator.next();
				xmlUrl=o4c.getOpmlOutlineXmlUrl();
				o4cOpmlUrlSet.add(xmlUrl);
			}		
		}//System.out.println("表opml4channel中字段opml_outline_xmlUrl数量(已经去重复已去重)= "+newList.size());
	}
		
	/**
	 *  频道表 channel为空,载入预定数据
	 * @param channelDAO
	 */
	private void loadDataToChannel(ChannelDAO channelDAO){
		logger.debug("第一次向channel表中插入="+new Date());
		try{
			AbstractChannel channel=new Channel();

			StringBuffer sb=new StringBuffer();
			System.out.println("LinkedHasmMap="+allChannels.size());
			for(Map.Entry<String, String[]> e:allChannels.entrySet()){
				channel.setChannelName(e.getKey());
				String[] opmlArray=e.getValue();
				for(int i=0;i<opmlArray.length;i++){
					sb.append(opmlArray[i]+"-_-!!");
				}
				channel.setChannelOpmlUrl(sb.substring(0, sb.length()-5));
				channel.setNetsource(findNetsourceByUrl(netsources[0][1]));
				
				channelDAO.save(channel);
				sb.delete(0, sb.length());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
	/**
	 * 表netsource为空  开始载入预定数据
	 * @param netsourceDAO
	 */
	private void loadDataToNetsource(NetsourceDAO netsourceDAO){
		
		try{
			AbstractNetsource netsource=new Netsource();
			for(int i=0;i<netsources.length;i++){
				
				netsource.setSourceName(netsources[i][0]);
				netsource.setSourceUrl(netsources[i][1]);
				netsourceDAO.save(netsource);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

		
	@SuppressWarnings("unchecked")
	private Netsource findNetsourceByUrl(String url){
		List<Netsource> netsourceList=(List<Netsource>) NetsourceDAO.getInstance().findBySourceUrl(url);
		
		for(Netsource ns:netsourceList){
			if(ns.getSourceUrl().equals(url)){
				return ns;
			}
		}
		return null;
	}
		
		
	@SuppressWarnings("unchecked")
	public String toSubChannelEntry(){System.out.println("当前页码=="+currentPage);
	
		if(opmlOutlineXmlUrl!=null){
//			System.out.println("不转码的地址="+opmlOutlineXmlUrl);
			
			try {
				opmlOutlineXmlUrl=URLDecoder.decode(opmlOutlineXmlUrl, "UTF-8");
//				System.out.println("URLDecoder第一次转码后="+opmlOutlineXmlUrl);
				opmlOutlineXmlUrl=URLDecoder.decode(opmlOutlineXmlUrl, "UTF-8");
//				System.out.println("Utils第二次转码后="+opmlOutlineXmlUrl);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			if(!tempURL.equals(opmlOutlineXmlUrl)){
				currentPage=1;  System.out.println("换子频道了,从第一页开始");
			}
			
			tempURL=opmlOutlineXmlUrl;
			List<Opml4channel> o4cList=Opml4channelDAO.getInstance().findByOpmlOutlineXmlUrl(opmlOutlineXmlUrl);
			
			if(o4cList!=null&&o4cList.size()!=0){
//				Opml4channel o4c=o4cList.get(0);
//				Set<Entry> entries=getReferencedEntriesByOpml4channel(o4c);
			
				Map<String, Integer>map=entryDAO.getEntryNumInfo(opmlOutlineXmlUrl, NUM_PER_PAGE);
				totalPages=map.get("totalPages");
				totalRows=map.get("totalRows");
				
				verifyPageKey(pageKey);
				
				List<Entry> entries=entryDAO.getReferencedEntriesByOpml4channel(opmlOutlineXmlUrl, currentPage, NUM_PER_PAGE);

				
				Map<String, Object> session=(Map<String, Object>) ActionContext.getContext().getSession();
				if(entries!=null){
					session.put("entries", entries);
					session.put("entrySize", entries.size());
					session.put("o4cList", ChannelAction.o4cList);
					session.put("channels", LoginAction.channels);
					
					session.put("opmlOutlineXmlUrl", opmlOutlineXmlUrl);
					session.put("currentPage", currentPage);
//					session.put("pageKey", pageKey);  System.out.println("pppppppp="+pageKey);
					session.put("totalPages", totalPages);
					session.put("totalRows", totalRows);
					
//					System.out.println(
//							"当前页="+currentPage+
//							",总页码="+totalPages+
//							",总数目="+totalRows
//							);
					
//					session.put("userSecretKeys", SECRET_KEY_PRE_URL+LoginAction.secretKeys);   //现在没用到
//					allEntrySet=null;
					if(LoginAction.power.contains("w")){
						return "toWkBg";
					}else if(LoginAction.power.contains("j")){
						return "auditJslt";
					}else{
						
					}
					return "toEntries";
				}
			}
		}
		return ERROR;
	}
		
	private void init(){
		
//		if(timer==null){
//			timer=new Timer();
//		}
//		if(entryAction==null){
//			entryAction=new EntryAction();
//		}
		
		String loggerPath=Utils.getWebRootPath()+"SnatchLog.html";
		Utils.initHTMLLogger(logger, loggerPath, true, Level.DEBUG);
	}

	public static Timer getTimer(){
		if(timer==null){
			timer=new Timer();
		}
		return timer;
	}

	public Logger getLogger() {
		// TODO Auto-generated method stub
		return Logger.getLogger(Opml4channelAction.class);
	}
	
	/**
	 * 该功能暂时关闭 前段屏蔽...
	 * @return
	 */
	public String setTimeDelay(){
		if(TIMER_INTERVAL!=0){
			logger.fatal(LoginAction.nickname+"已于"+new Date()+"设定抓取间隔为"+TIMER_INTERVAL+"小时");
			TIMER_INTERVAL*=3600*1000;  //hour--> Milliseconds
			TIMER_DELAY=TIMER_INTERVAL;
			if(timer!=null){
				timer.cancel();
				timer=null;
			}
			toSnatch();
		}else{
			isAutomatic=false;
			if(timer!=null){
				timer.cancel();
				timer=null;
			}
			logger.fatal(LoginAction.nickname+"已于"+new Date()+"设定抓取模式为手动");
		}
		return "setSnatchInterval";
	}
	
	public void verifyPageKey(String pageKey){
		if(pageKey.equals("last")&&Opml4channelAction.currentPage>1){
			Opml4channelAction.currentPage--;
		}else if(pageKey.equals("1st")&&totalPages>=1){
			Opml4channelAction.currentPage=1;
		}else if(pageKey.equals("2nd")&&totalPages>=2){
			Opml4channelAction.currentPage=2;
		}else if(pageKey.equals("3rd")&&totalPages>=3){
			Opml4channelAction.currentPage=3;
		}else if(pageKey.equals("4th")&&totalPages>=4){
			Opml4channelAction.currentPage=4;
		}else if(pageKey.equals("5th")&&totalPages>=5){
			Opml4channelAction.currentPage=5;
		}else if(pageKey.equals("next")&&Opml4channelAction.currentPage<this.totalPages){
			Opml4channelAction.currentPage++;
		}else{
			Opml4channelAction.currentPage=1;
		}
	}
}
