package com.wukong.t8.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.wukong.t8.pojo.Entry;
import com.wukong.t8.pojo.Opml4channel;

public class EntryParser {

	public static final String REDIRECT_URL="http://go.rss.sina.com.cn/redirect.php?url=";
	
	@SuppressWarnings("unchecked")
	public static List<Entry> getEntryListByOpmlOutlineXmlUrl(Opml4channel o4c){
		List<Entry> list=null;
		if(o4c!=null){
			list=new ArrayList<Entry>();
			SyndFeed feed=FeedParser.getSyndFeedByOpmlOutlineXmlUrl(o4c.getOpmlOutlineXmlUrl());
			if(feed!=null){
				List<SyndEntry> entryList=feed.getEntries();
				Iterator<SyndEntry> iterator=entryList.iterator();
				SyndEntry e=null;
				Entry entry=null;
				String temp=null;
				
				while(iterator.hasNext()){
					e=iterator.next();
					entry=new Entry();
					entry.setEntryTitle(e.getTitle().trim());
					
					temp=e.getLink();
					if(temp!=null){
						entry.setEntryLink(temp.trim());
						temp=null;
					}
					
					temp=e.getAuthor();
					if(temp!=null){
						entry.setEntryAuthor(temp.trim());
						temp=null;
					}
					
					entry.setEntryGuid(e.getUri().trim().replace(REDIRECT_URL, ""));
					
//					temp=((SyndCategoryImpl)e.getCategories().get(0)).getName();
//					if(temp!=null){
//						entry.setEntryCategory(temp.trim());
//						temp=null;
//					}
					
					entry.setEntryPubDate(new Timestamp((e.getPublishedDate()!=null)?e.getPublishedDate().getTime():System.currentTimeMillis()));
					
					temp=e.getDescription().getValue();
					if(temp!=null){
						entry.setEntryDescription(temp.trim());
						temp=null;
					}
					
					entry.setOpml4channel(o4c);
					list.add(entry);
				}
			}
		}
		return list;
	}
	
	
}
