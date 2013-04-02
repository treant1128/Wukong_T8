package com.wukong.t8.abstractpojo;

import java.sql.Timestamp;

import com.wukong.t8.pojo.Opml4channel;

/**
 * AbstractEntry entity provides the base persistence definition of the Entry
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractEntry extends AbstractPojo implements java.io.Serializable {

	// Fields

	private Integer entryId;
	private Opml4channel opml4channel;
	private String entryTitle;
	private String entryLink;
	private String entryAuthor;
	private String entryGuid;
	private String entryCategory;
	private Timestamp entryPubDate;
	private String entryComments;
	private String entryDescription;
	private String entryAbstract;
	private String entryImageUrl;
	private Integer entryPriority;
	private Float entryRespondRatio;

	// Constructors

	/** default constructor */
	public AbstractEntry() {
	}

	/** minimal constructor */
	public AbstractEntry(Opml4channel opml4channel, String entryTitle,
			String entryGuid, Timestamp entryPubDate) {
		this.opml4channel = opml4channel;
		this.entryTitle = entryTitle;
		this.entryGuid = entryGuid;
		this.entryPubDate = entryPubDate;
	}

	/** full constructor */
	public AbstractEntry(Opml4channel opml4channel, String entryTitle,
			String entryLink, String entryAuthor, String entryGuid,
			String entryCategory, Timestamp entryPubDate, String entryComments,
			String entryDescription, String entryAbstract,
			String entryImageUrl, Integer entryPriority, Float entryRespondRatio) {
		this.opml4channel = opml4channel;
		this.entryTitle = entryTitle;
		this.entryLink = entryLink;
		this.entryAuthor = entryAuthor;
		this.entryGuid = entryGuid;
		this.entryCategory = entryCategory;
		this.entryPubDate = entryPubDate;
		this.entryComments = entryComments;
		this.entryDescription = entryDescription;
		this.entryAbstract = entryAbstract;
		this.entryImageUrl = entryImageUrl;
		this.entryPriority = entryPriority;
		this.entryRespondRatio = entryRespondRatio;
	}

	// Property accessors

	public Integer getEntryId() {
		return this.entryId;
	}

	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}

	public Opml4channel getOpml4channel() {
		return this.opml4channel;
	}

	public void setOpml4channel(Opml4channel opml4channel) {
		this.opml4channel = opml4channel;
	}

	public String getEntryTitle() {
		return this.entryTitle;
	}

	public void setEntryTitle(String entryTitle) {
		this.entryTitle = entryTitle;
	}

	public String getEntryLink() {
		return this.entryLink;
	}

	public void setEntryLink(String entryLink) {
		this.entryLink = entryLink;
	}

	public String getEntryAuthor() {
		return this.entryAuthor;
	}

	public void setEntryAuthor(String entryAuthor) {
		this.entryAuthor = entryAuthor;
	}

	public String getEntryGuid() {
		return this.entryGuid;
	}

	public void setEntryGuid(String entryGuid) {
		this.entryGuid = entryGuid;
	}

	public String getEntryCategory() {
		return this.entryCategory;
	}

	public void setEntryCategory(String entryCategory) {
		this.entryCategory = entryCategory;
	}

	public Timestamp getEntryPubDate() {
		return this.entryPubDate;
	}

	public void setEntryPubDate(Timestamp entryPubDate) {
		this.entryPubDate = entryPubDate;
	}

	public String getEntryComments() {
		return this.entryComments;
	}

	public void setEntryComments(String entryComments) {
		this.entryComments = entryComments;
	}

	public String getEntryDescription() {
		return this.entryDescription;
	}

	public void setEntryDescription(String entryDescription) {
		this.entryDescription = entryDescription;
	}

	public String getEntryAbstract() {
		return this.entryAbstract;
	}

	public void setEntryAbstract(String entryAbstract) {
		this.entryAbstract = entryAbstract;
	}

	public String getEntryImageUrl() {
		return this.entryImageUrl;
	}

	public void setEntryImageUrl(String entryImageUrl) {
		this.entryImageUrl = entryImageUrl;
	}

	public Integer getEntryPriority() {
		return this.entryPriority;
	}

	public void setEntryPriority(Integer entryPriority) {
		this.entryPriority = entryPriority;
	}

	public Float getEntryRespondRatio() {
		return this.entryRespondRatio;
	}

	public void setEntryRespondRatio(Float entryRespondRatio) {
		this.entryRespondRatio = entryRespondRatio;
	}

}