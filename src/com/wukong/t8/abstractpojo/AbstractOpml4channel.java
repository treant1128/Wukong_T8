package com.wukong.t8.abstractpojo;

import java.util.HashSet;
import java.util.Set;

import com.wukong.t8.pojo.Channel;

/**
 * AbstractOpml4channel entity provides the base persistence definition of the
 * Opml4channel entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOpml4channel extends AbstractPojo implements java.io.Serializable {

	// Fields

	private Integer opmlId;
	private Channel channel;
	private String opmlHeadTitle;
	private String opmlBodyTitle;
	private String opmlBodyText;
	private String opmlOutlineText;
	private String opmlOutlineTitle;
	private String opmlOutlineType;
	private String opmlOutlineXmlUrl;
	private String opmlOutlineHtmlUrl;
	private Set feeds = new HashSet(0);
	private Set entries = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractOpml4channel() {
	}

	/** minimal constructor */
	public AbstractOpml4channel(Channel channel, String opmlHeadTitle,
			String opmlOutlineTitle, String opmlOutlineXmlUrl) {
		this.channel = channel;
		this.opmlHeadTitle = opmlHeadTitle;
		this.opmlOutlineTitle = opmlOutlineTitle;
		this.opmlOutlineXmlUrl = opmlOutlineXmlUrl;
	}

	/** full constructor */
	public AbstractOpml4channel(Channel channel, String opmlHeadTitle,
			String opmlBodyTitle, String opmlBodyText, String opmlOutlineText,
			String opmlOutlineTitle, String opmlOutlineType,
			String opmlOutlineXmlUrl, String opmlOutlineHtmlUrl, Set feeds,
			Set entries) {
		this.channel = channel;
		this.opmlHeadTitle = opmlHeadTitle;
		this.opmlBodyTitle = opmlBodyTitle;
		this.opmlBodyText = opmlBodyText;
		this.opmlOutlineText = opmlOutlineText;
		this.opmlOutlineTitle = opmlOutlineTitle;
		this.opmlOutlineType = opmlOutlineType;
		this.opmlOutlineXmlUrl = opmlOutlineXmlUrl;
		this.opmlOutlineHtmlUrl = opmlOutlineHtmlUrl;
		this.feeds = feeds;
		this.entries = entries;
	}

	// Property accessors

	public Integer getOpmlId() {
		return this.opmlId;
	}

	public void setOpmlId(Integer opmlId) {
		this.opmlId = opmlId;
	}

	public Channel getChannel() {
		return this.channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getOpmlHeadTitle() {
		return this.opmlHeadTitle;
	}

	public void setOpmlHeadTitle(String opmlHeadTitle) {
		this.opmlHeadTitle = opmlHeadTitle;
	}

	public String getOpmlBodyTitle() {
		return this.opmlBodyTitle;
	}

	public void setOpmlBodyTitle(String opmlBodyTitle) {
		this.opmlBodyTitle = opmlBodyTitle;
	}

	public String getOpmlBodyText() {
		return this.opmlBodyText;
	}

	public void setOpmlBodyText(String opmlBodyText) {
		this.opmlBodyText = opmlBodyText;
	}

	public String getOpmlOutlineText() {
		return this.opmlOutlineText;
	}

	public void setOpmlOutlineText(String opmlOutlineText) {
		this.opmlOutlineText = opmlOutlineText;
	}

	public String getOpmlOutlineTitle() {
		return this.opmlOutlineTitle;
	}

	public void setOpmlOutlineTitle(String opmlOutlineTitle) {
		this.opmlOutlineTitle = opmlOutlineTitle;
	}

	public String getOpmlOutlineType() {
		return this.opmlOutlineType;
	}

	public void setOpmlOutlineType(String opmlOutlineType) {
		this.opmlOutlineType = opmlOutlineType;
	}

	public String getOpmlOutlineXmlUrl() {
		return this.opmlOutlineXmlUrl;
	}

	public void setOpmlOutlineXmlUrl(String opmlOutlineXmlUrl) {
		this.opmlOutlineXmlUrl = opmlOutlineXmlUrl;
	}

	public String getOpmlOutlineHtmlUrl() {
		return this.opmlOutlineHtmlUrl;
	}

	public void setOpmlOutlineHtmlUrl(String opmlOutlineHtmlUrl) {
		this.opmlOutlineHtmlUrl = opmlOutlineHtmlUrl;
	}

	public Set getFeeds() {
		return this.feeds;
	}

	public void setFeeds(Set feeds) {
		this.feeds = feeds;
	}

	public Set getEntries() {
		return this.entries;
	}

	public void setEntries(Set entries) {
		this.entries = entries;
	}

}