package com.wukong.t8.pojo;

import java.sql.Timestamp;

import com.wukong.t8.abstractpojo.AbstractRepository;

/**
 * Repository entity. @author MyEclipse Persistence Tools
 */
public class Repository extends AbstractRepository implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Repository() {
	}

	/** minimal constructor */
	public Repository(String repoTitle, String repoLink, String repoOperator,
			String repoStatus) {
		super(repoTitle, repoLink, repoOperator, repoStatus);
	}

	/** full constructor */
	public Repository(String repoTitle, String repoLink, String repoCategory,
			String repoOperator, Timestamp repoSubDate, String repoStatus) {
		super(repoTitle, repoLink, repoCategory, repoOperator, repoSubDate,
				repoStatus);
	}

}
