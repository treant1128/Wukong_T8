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
	private static Set<String> o4cOpmlUrlSet=new HashSet<String>();  //ȫ��  Ψһ ȥ��

//	private static List<Entry> allEntryList=null;
//	private Set<Entry> allEntrySet=null;
	private String opmlOutlineXmlUrl;
	
	private static int TIMER_INTERVAL=3*3600*1000;//
	private static int TIMER_DELAY=5000;
	private static boolean isAutomatic=true;
	private static EntryAction entryAction=new EntryAction();
	private int errorCount=0;
	private Logger logger=getLogger();
	
	private Future<String> o4cFuture;
//	private Future<Integer> feedFuture;
	private static Opml4channelDAO o4cDAO=Opml4channelDAO.getInstance();
	private static ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory(){

		public Thread newThread(Runnable r) {
			// TODO Auto-generated method stub
			return new Thread(r);
		}
		
	});
	//����Ϊstatic ȫ�ֱ���
	private static int flag=0, ratio=8; //ʱ������ֵ         ����ÿ��ץȡEntry֮ǰ����ʼ��  	

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

	static{
		refreshO4COpmlUrl();
	}
	
	public String toSnatch(){
		init();
		logger.info(new Date()+"->��ʼ�����");
		
		if(isAutomatic){
			
			getTimer().schedule(new TimerTask(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					logger.info(new Date()+"->��ʼmainToSnatch");
					
					mainToSnatch();   
					
				}

			}, TIMER_DELAY, TIMER_INTERVAL);
//			LoginAction.TIME_DELAY=3600*1000;  // Reset time delay
		}else{		
			logger.info("���ֶ�ģʽץȡ");
			mainToSnatch();
		}
		
		return SUCCESS;
	}
	
	/**
	 * ִ��Snatch��������
	 * @return
	 */
	public String mainToSnatch() {

		if(initperiodically()&& o4cFuture.isDone()){ //block until retrieve result 
			logger.info(new Date()+"->fill Feed OverȥEntryAction->Snatch");
//			System.out.println("ִ��entryAction.toSnatch=="+new Date());
			entryAction.toSnatch();
		}else{
			errorCount++;
			logger.error("ErrorCount="+errorCount);
			
			if(errorCount>8){
				logger.error("��ʼ��uncomplicated,rollback����="+errorCount);
				return ERROR;
			}
			snooze();
		}	
		return SUCCESS;
	}
	
	private void snooze(){
		try {
			Thread.sleep(5000);
			logger.warn("Sleep���������Snatch...");
			mainToSnatch();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ڵ���֤opml
	 * @return
	 */
	public boolean initperiodically(){
		if(flag%ratio==0){ 
			logger.debug("��ȡlocalʱflag="+flag);//System.out.println("��֤opmlʱflag="+flag);
			return initWhenFirstlyExecute();
		}else{
			flag+=1;  //�������ƹ�
			logger.info("����--��·��flag="+flag);
			return true;
		}
	}
	
	/**
	 * ���μ���netsource->channel->opml4channel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean initWhenFirstlyExecute(){
		NetsourceDAO netsourceDAO=NetsourceDAO.getInstance();
			
		if(netsourceDAO.findAll().size()!=0){
			
			ChannelDAO channelDAO=ChannelDAO.getInstance();
			List<Channel> channelList=(List<Channel>)channelDAO.findAll();
			int channelCount=channelList.size();
			
			if(channelCount!=0){   //channel������������
				logger.info("����Ƶ����Count="+channelCount);
				
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
				
				flag+=1;  //ִ�гɹ��ż���  ��ֵΪ0  ���ɹ�����
				logger.info(new Date()+"opml4channelˢ�����flag="+flag);
//				allEntryList=null;//���  �´β�ѯ��ˢ��
			}else{
				loadDataToChannel(channelDAO);
				logger.debug(new Date()+"->���ε���channel���");
				return false;
			}
		}else{
			loadDataToNetsource(netsourceDAO);
			logger.debug(new Date()+"->��������,����netsource���");
			return false;
		}
		
		try {
			TimeUnit.SECONDS.sleep(5);   //���߳�sleep 5Sȷ�������ļ��������
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
		
//	private void initFeed(){
//		final FeedDAO feedDAO=FeedDAO.getInstance();
//		for(Iterator<Opml4channel> iterator=o4cSet.iterator();iterator.hasNext();){
//			final Opml4channel o=iterator.next();
//			feedFuture=executorService.submit(new Callable<Integer>(){
//
//				public Integer call() throws Exception {
//					Feed feed=FeedParser.getFeedByOpmlOutlineXmlUrl(o);
//					if(feed!=null){
//						feedDAO.save(feed);
//					}
//					return 888;
//				}
//				
//			});
//		}
//	}
	/**
	 * ����Ƶ��channel������صı�-->opml4channel�в�����Ƶ�����ݸ���"ȥ��"
	 * @param channel
	 */
	private void loadDataToOpml4Channel(Channel channel){
		//��Ƶ��channel_opmlurl·�����»�ȡ��Ӧ��Opml4channel�ļ���   
		List<Opml4channel> o4cList=OPMLParser.getOPML4ChannelList(channel);
		
		for(Opml4channel o4c:o4cList){
			//opml4channel�в����вŲ���(ȥ��)  ��xmlUrlȥ�ж�
			if(o4cOpmlUrlSet.add(o4c.getOpmlOutlineXmlUrl())){
				o4cDAO.save(o4c);  
			}	
		}
		
	}
		
	/**
	 * ��ȡȥ�ع���Opml��URL����
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
		}//System.out.println("��opml4channel���ֶ�opml_outline_xmlUrl����(�Ѿ�ȥ�ظ���ȥ��)= "+newList.size());
	}
		
	/**
	 *  Ƶ���� channelΪ��,����Ԥ������
	 * @param channelDAO
	 */
	private void loadDataToChannel(ChannelDAO channelDAO){
		logger.debug("��һ����channel���в���="+new Date());
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
	 * ��netsourceΪ��  ��ʼ����Ԥ������
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
	public String toSubChannelEntry(){
		if(opmlOutlineXmlUrl!=null){
			System.out.println("��ת��ĵ�ַ="+opmlOutlineXmlUrl);
			
			try {
				opmlOutlineXmlUrl=URLDecoder.decode(opmlOutlineXmlUrl, "UTF-8");
//				System.out.println("URLDecoder��һ��ת���="+opmlOutlineXmlUrl);
				opmlOutlineXmlUrl=URLDecoder.decode(opmlOutlineXmlUrl, "UTF-8");
//				System.out.println("Utils�ڶ���ת���="+opmlOutlineXmlUrl);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			List<Opml4channel> o4cList=Opml4channelDAO.getInstance().findByOpmlOutlineXmlUrl(opmlOutlineXmlUrl);
			
			if(o4cList!=null&&o4cList.size()!=0){
//				Opml4channel o4c=o4cList.get(0);
//				Set<Entry> entries=getReferencedEntriesByOpml4channel(o4c);
				List<Entry> entries=EntryDAO.getInstance().getReferencedEntriesByOpml4channel(opmlOutlineXmlUrl);
				
				Map<String, Object> session=(Map<String, Object>) ActionContext.getContext().getSession();
				if(entries!=null){
					session.put("entries", entries);
					session.put("entrySize", entries.size());
					session.put("o4cList", ChannelAction.o4cList);
					session.put("channels", LoginAction.channels);
//					session.put("userSecretKeys", SECRET_KEY_PRE_URL+LoginAction.secretKeys);   //����û�õ�
//					allEntrySet=null;
					if(LoginAction.power.contains("w")){
						return "toWkBg";
					}else{
						////Waiting
					}
					return "toEntries";
				}
			}
		}
		return ERROR;
	}
		
	/**
	 * ����Opml4channel���ض�Ӧ��Entry��HashSet
	 * @param o4c
	 * @return
	 */
//	@SuppressWarnings("unchecked")
//	public Set<Entry> getReferencedEntriesByOpml4channel(Opml4channel o4c){  
//
////		if(allEntryList==null){  //����պ��ȥfindAll
//			allEntryList=EntryDAO.getInstance().findAll();
////		}
//		
////		logger.fatal("Entry��С��С="+allEntryList.size());
//		
//		allEntrySet=new HashSet<Entry>();
//		for(Entry e: allEntryList){
//			if(e.getOpml4channel()==o4c){
//				allEntrySet.add(e);
//			}
//		}
//		allEntryList=null;//���  �´β�ѯ��ˢ��
//		return allEntrySet;
//	}
		
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
	
	public String setTimeDelay(){
		if(TIMER_INTERVAL!=0){
			logger.fatal(LoginAction.nickname+"����"+new Date()+"�趨ץȡ���Ϊ"+TIMER_INTERVAL+"Сʱ");
			TIMER_INTERVAL*=3600*1000;  //hour--> Milliseconds
			TIMER_DELAY=TIMER_INTERVAL;
			timer.cancel();
			timer=null;
			toSnatch();
		}else{
			isAutomatic=false;
			timer.cancel();
			timer=null;
			logger.fatal(LoginAction.nickname+"����"+new Date()+"�趨ץȡģʽΪ�ֶ�");
		}
		return "setSnatchInterval";
	}
		
}
