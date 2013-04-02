package com.wukong.t8.pojo;

import java.sql.Timestamp;

import com.wukong.t8.abstractpojo.AbstractEntry;

/**
 * Entry entity. @author MyEclipse Persistence Tools
 */
public class Entry extends AbstractEntry implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Entry() {
	}

	/** minimal constructor */
	public Entry(Opml4channel opml4channel, String entryTitle,
			String entryGuid, Timestamp entryPubDate) {
		super(opml4channel, entryTitle, entryGuid, entryPubDate);
	}

	/** full constructor */
	public Entry(Opml4channel opml4channel, String entryTitle,
			String entryLink, String entryAuthor, String entryGuid,
			String entryCategory, Timestamp entryPubDate, String entryComments,
			String entryDescription, String entryAbstract,
			String entryImageUrl, Integer entryPriority, Float entryRespondRatio) {
		super(opml4channel, entryTitle, entryLink, entryAuthor, entryGuid,
				entryCategory, entryPubDate, entryComments, entryDescription,
				entryAbstract, entryImageUrl, entryPriority, entryRespondRatio);
	}

}
