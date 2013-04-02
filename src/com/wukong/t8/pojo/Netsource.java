package com.wukong.t8.pojo;

import java.util.Set;

import com.wukong.t8.abstractpojo.AbstractNetsource;

/**
 * Netsource entity. @author MyEclipse Persistence Tools
 */
public class Netsource extends AbstractNetsource implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Netsource() {
	}

	/** minimal constructor */
	public Netsource(String sourceName, String sourceUrl) {
		super(sourceName, sourceUrl);
	}

	/** full constructor */
	public Netsource(String sourceName, String sourceUrl, Set channels) {
		super(sourceName, sourceUrl, channels);
	}

}
