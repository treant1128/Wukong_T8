package com.wukong.t8.abstractpojo;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractNetsource entity provides the base persistence definition of the
 * Netsource entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNetsource extends AbstractPojo implements java.io.Serializable {

	// Fields

	private Integer sourceId;
	private String sourceName;
	private String sourceUrl;
	private Set channels = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractNetsource() {
	}

	/** minimal constructor */
	public AbstractNetsource(String sourceName, String sourceUrl) {
		this.sourceName = sourceName;
		this.sourceUrl = sourceUrl;
	}

	/** full constructor */
	public AbstractNetsource(String sourceName, String sourceUrl, Set channels) {
		this.sourceName = sourceName;
		this.sourceUrl = sourceUrl;
		this.channels = channels;
	}

	// Property accessors

	public Integer getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return this.sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceUrl() {
		return this.sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public Set getChannels() {
		return this.channels;
	}

	public void setChannels(Set channels) {
		this.channels = channels;
	}

}