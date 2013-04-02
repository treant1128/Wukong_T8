package com.wukong.t8.abstractpojo;

import java.util.HashSet;
import java.util.Set;

import com.wukong.t8.pojo.Netsource;

/**
 * AbstractChannel entity provides the base persistence definition of the
 * Channel entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractChannel extends AbstractPojo implements java.io.Serializable {

	// Fields

	private Integer channelId;
	private Netsource netsource;
	private String channelName;
	private String channelOpmlUrl;
	private Set opml4channels = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractChannel() {
	}

	/** minimal constructor */
	public AbstractChannel(Netsource netsource, String channelName,
			String channelOpmlUrl) {
		this.netsource = netsource;
		this.channelName = channelName;
		this.channelOpmlUrl = channelOpmlUrl;
	}

	/** full constructor */
	public AbstractChannel(Netsource netsource, String channelName,
			String channelOpmlUrl, Set opml4channels) {
		this.netsource = netsource;
		this.channelName = channelName;
		this.channelOpmlUrl = channelOpmlUrl;
		this.opml4channels = opml4channels;
	}

	// Property accessors

	public Integer getChannelId() {
		return this.channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Netsource getNetsource() {
		return this.netsource;
	}

	public void setNetsource(Netsource netsource) {
		this.netsource = netsource;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelOpmlUrl() {
		return this.channelOpmlUrl;
	}

	public void setChannelOpmlUrl(String channelOpmlUrl) {
		this.channelOpmlUrl = channelOpmlUrl;
	}

	public Set getOpml4channels() {
		return this.opml4channels;
	}

	public void setOpml4channels(Set opml4channels) {
		this.opml4channels = opml4channels;
	}

}