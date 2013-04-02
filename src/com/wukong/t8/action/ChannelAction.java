package com.wukong.t8.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wukong.t8.dao.ChannelDAO;
import com.wukong.t8.dao.FeedDAO;
import com.wukong.t8.dao.Opml4channelDAO;
import com.wukong.t8.pojo.Channel;
import com.wukong.t8.pojo.Feed;
import com.wukong.t8.pojo.Opml4channel;


public class ChannelAction extends ActionSupport implements BaseAction{
	private String channelOpmlUrl;
	public static List<Opml4channel> o4cList=null;
	
	public String getChannelOpmlUrl() {
		return channelOpmlUrl;
	}

	public void setChannelOpmlUrl(String channelOpmlUrl) {
		this.channelOpmlUrl = channelOpmlUrl;
	}

	/**
	 * 根据传来的channelOpmlUrl确定到唯一的Channel 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String toSubChannels(){
		if(channelOpmlUrl!=null){
			channelOpmlUrl=channelOpmlUrl.replace("\\", "\\\\");
			
			List<Channel> channelList=(List<Channel>) ChannelDAO.getInstance().findByChannelOpmlUrl(channelOpmlUrl);
			
			if(channelList!=null&&channelList.size()!=0){ 
				Channel channel= channelList.get(0);
				o4cList=getReferencedOpml4channelsByChannel(channel);
				//一个Opml4channel其实只对应一个Feed
				List<Feed> feedList=getReferencedFeedListByO4CList(o4cList);
	//			List<Map<Opml4channel, Feed>> bundle=JoinTwoListForSession(o4cList, feedList);
				
				Map<String, Object> session=(Map<String, Object>) ActionContext.getContext().getSession();
				if(o4cList!=null){
					session.put("o4cList",o4cList);
					session.put("o4cListSize",o4cList.size());
					session.put("o4cListRows",Math.floor(o4cList.size()/3));
				}
				
				if(feedList!=null){
					session.put("feedList", feedList);
				}
//				
				session.put("channel", channel);   //载入OPML超链接用
				return "toSubChannels";
			}
		}
		return ERROR;
	}
	
	/**
	 * 根据唯一的Channel去从子表的所有Opml4channel中匹配主表外键所关联的子表集合
	 * @param channel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Opml4channel> getReferencedOpml4channelsByChannel(Channel channel){
		List<Opml4channel> all=null;
		List<Opml4channel> o4cList=Opml4channelDAO.getInstance().findAll();
		if(o4cList!=null&&o4cList.size()!=0){
			all=new ArrayList<Opml4channel>();
			for(Opml4channel o4c:o4cList){
				if(o4c.getChannel()==channel){
					all.add(o4c);
				}
			}
		}
		return all;
	}
	
	@SuppressWarnings("unchecked")
	public List<Feed> getReferencedFeedListByO4CList(List<Opml4channel> o4cList){
		List<Feed> all=null;
		if(o4cList!=null){
			all=new ArrayList<Feed>();
			List<Feed> feedList=FeedDAO.getInstance().findAll();
			for(Opml4channel o4c:o4cList){
				for(Feed f:feedList){
					if(f.getOpml4channel()==o4c){
						all.add(f);
					}
				}
			}
		}
		return all;
	}
	
	public String toLog(){
		return "toLogger";
	}
//	public List<Map<Opml4channel, Feed>> JoinTwoListForSession(List<Opml4channel> o4cList, List<Feed> feedList){
//		if(o4cList==null||feedList==null||o4cList.size()!=feedList.size()){
//			return null;
//		}
//		List<Map<Opml4channel, Feed>> list=new ArrayList<Map<Opml4channel, Feed>>();
//	//	Map<Opml4channel, Feed> map=new HashMap<Opml4channel, Feed>();
//		for(int i=0;i<o4cList.size();i++){
//			Map<Opml4channel, Feed> map=new HashMap<Opml4channel, Feed>();
//			map.put(o4cList.get(i), feedList.get(i));
//			list.add(map);
//	//		map.clear();
//		}
//		return list;
//	}

	public Logger getLogger() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toSnatch() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
