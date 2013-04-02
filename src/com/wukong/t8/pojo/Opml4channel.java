package com.wukong.t8.pojo;

import java.util.Set;

import com.wukong.t8.abstractpojo.AbstractOpml4channel;

/**
 * Opml4channel entity. @author MyEclipse Persistence Tools
 */
public class Opml4channel extends AbstractOpml4channel implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Opml4channel() {
	}

	/** minimal constructor */
	public Opml4channel(Channel channel, String opmlHeadTitle,
			String opmlOutlineTitle, String opmlOutlineXmlUrl) {
		super(channel, opmlHeadTitle, opmlOutlineTitle, opmlOutlineXmlUrl);
	}

	/** full constructor */
	public Opml4channel(Channel channel, String opmlHeadTitle,
			String opmlBodyTitle, String opmlBodyText, String opmlOutlineText,
			String opmlOutlineTitle, String opmlOutlineType,
			String opmlOutlineXmlUrl, String opmlOutlineHtmlUrl, Set feeds,
			Set entries) {
		super(channel, opmlHeadTitle, opmlBodyTitle, opmlBodyText,
				opmlOutlineText, opmlOutlineTitle, opmlOutlineType,
				opmlOutlineXmlUrl, opmlOutlineHtmlUrl, feeds, entries);
	}

}
