package com.wukong.t8.pojo;

import java.util.Set;

import com.wukong.t8.abstractpojo.AbstractChannel;

/**
 * Channel entity. @author MyEclipse Persistence Tools
 */
public class Channel extends AbstractChannel implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Channel() {
	}

	/** minimal constructor */
	public Channel(Netsource netsource, String channelName,
			String channelOpmlUrl) {
		super(netsource, channelName, channelOpmlUrl);
	}

	/** full constructor */
	public Channel(Netsource netsource, String channelName,
			String channelOpmlUrl, Set opml4channels) {
		super(netsource, channelName, channelOpmlUrl, opml4channels);
	}

}
