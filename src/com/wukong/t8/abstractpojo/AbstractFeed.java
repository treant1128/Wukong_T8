package com.wukong.t8.abstractpojo;

import java.sql.Timestamp;

import com.wukong.t8.pojo.Opml4channel;

/**
 * AbstractFeed entity provides the base persistence definition of the Feed
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractFeed extends AbstractPojo implements java.io.Serializable {

	// Fields

	private Integer feedId;
	private Opml4channel opml4channel;
	private String feedTitle;
	private String feedImageTitle;
	private String feedImageLink;
	private String feedImageUrl;
	private String feedDescription;
	private String feedLink;
	private String feedLauguage;
	private Timestamp feedPubDate;
	private String feedCopyright;
	private String feedType;

	// Constructors

	/** default constructor */
	public AbstractFeed() {
	}

	/** minimal constructor */
	public AbstractFeed(Opml4channel opml4channel, String feedTitle,
			String feedLink, Timestamp feedPubDate) {
		this.opml4channel = opml4channel;
		this.feedTitle = feedTitle;
		this.feedLink = feedLink;
		this.feedPubDate = feedPubDate;
	}

	/** full constructor */
	public AbstractFeed(Opml4channel opml4channel, String feedTitle,
			String feedImageTitle, String feedImageLink, String feedImageUrl,
			String feedDescription, String feedLink, String feedLauguage,
			Timestamp feedPubDate, String feedCopyright, String feedType) {
		this.opml4channel = opml4channel;
		this.feedTitle = feedTitle;
		this.feedImageTitle = feedImageTitle;
		this.feedImageLink = feedImageLink;
		this.feedImageUrl = feedImageUrl;
		this.feedDescription = feedDescription;
		this.feedLink = feedLink;
		this.feedLauguage = feedLauguage;
		this.feedPubDate = feedPubDate;
		this.feedCopyright = feedCopyright;
		this.feedType = feedType;
	}

	// Property accessors

	public Integer getFeedId() {
		return this.feedId;
	}

	public void setFeedId(Integer feedId) {
		this.feedId = feedId;
	}

	public Opml4channel getOpml4channel() {
		return this.opml4channel;
	}

	public void setOpml4channel(Opml4channel opml4channel) {
		this.opml4channel = opml4channel;
	}

	public String getFeedTitle() {
		return this.feedTitle;
	}

	public void setFeedTitle(String feedTitle) {
		this.feedTitle = feedTitle;
	}

	public String getFeedImageTitle() {
		return this.feedImageTitle;
	}

	public void setFeedImageTitle(String feedImageTitle) {
		this.feedImageTitle = feedImageTitle;
	}

	public String getFeedImageLink() {
		return this.feedImageLink;
	}

	public void setFeedImageLink(String feedImageLink) {
		this.feedImageLink = feedImageLink;
	}

	public String getFeedImageUrl() {
		return this.feedImageUrl;
	}

	public void setFeedImageUrl(String feedImageUrl) {
		this.feedImageUrl = feedImageUrl;
	}

	public String getFeedDescription() {
		return this.feedDescription;
	}

	public void setFeedDescription(String feedDescription) {
		this.feedDescription = feedDescription;
	}

	public String getFeedLink() {
		return this.feedLink;
	}

	public void setFeedLink(String feedLink) {
		this.feedLink = feedLink;
	}

	public String getFeedLauguage() {
		return this.feedLauguage;
	}

	public void setFeedLauguage(String feedLauguage) {
		this.feedLauguage = feedLauguage;
	}

	public Timestamp getFeedPubDate() {
		return this.feedPubDate;
	}

	public void setFeedPubDate(Timestamp feedPubDate) {
		this.feedPubDate = feedPubDate;
	}

	public String getFeedCopyright() {
		return this.feedCopyright;
	}

	public void setFeedCopyright(String feedCopyright) {
		this.feedCopyright = feedCopyright;
	}

	public String getFeedType() {
		return this.feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

}