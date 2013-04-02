package com.wukong.t8.pojo;

import java.sql.Timestamp;

import com.wukong.t8.abstractpojo.AbstractFeed;

/**
 * Feed entity. @author MyEclipse Persistence Tools
 */
public class Feed extends AbstractFeed implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Feed() {
	}

	/** minimal constructor */
	public Feed(Opml4channel opml4channel, String feedTitle, String feedLink,
			Timestamp feedPubDate) {
		super(opml4channel, feedTitle, feedLink, feedPubDate);
	}

	/** full constructor */
	public Feed(Opml4channel opml4channel, String feedTitle,
			String feedImageTitle, String feedImageLink, String feedImageUrl,
			String feedDescription, String feedLink, String feedLauguage,
			Timestamp feedPubDate, String feedCopyright, String feedType) {
		super(opml4channel, feedTitle, feedImageTitle, feedImageLink,
				feedImageUrl, feedDescription, feedLink, feedLauguage,
				feedPubDate, feedCopyright, feedType);
	}

}
