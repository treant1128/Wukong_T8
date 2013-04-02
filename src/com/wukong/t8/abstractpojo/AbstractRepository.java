package com.wukong.t8.abstractpojo;

import java.sql.Timestamp;

/**
 * AbstractRepository entity provides the base persistence definition of the
 * Repository entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRepository extends AbstractPojo implements java.io.Serializable {

	// Fields

	private Integer repoId;
	private String repoTitle;
	private String repoLink;
	private String repoCategory;
	private String repoOperator;
	private Timestamp repoSubDate;
	private String repoStatus;

	// Constructors

	/** default constructor */
	public AbstractRepository() {
	}

	/** minimal constructor */
	public AbstractRepository(String repoTitle, String repoLink,
			String repoOperator, String repoStatus) {
		this.repoTitle = repoTitle;
		this.repoLink = repoLink;
		this.repoOperator = repoOperator;
		this.repoStatus = repoStatus;
	}

	/** full constructor */
	public AbstractRepository(String repoTitle, String repoLink,
			String repoCategory, String repoOperator, Timestamp repoSubDate,
			String repoStatus) {
		this.repoTitle = repoTitle;
		this.repoLink = repoLink;
		this.repoCategory = repoCategory;
		this.repoOperator = repoOperator;
		this.repoSubDate = repoSubDate;
		this.repoStatus = repoStatus;
	}

	// Property accessors

	public Integer getRepoId() {
		return this.repoId;
	}

	public void setRepoId(Integer repoId) {
		this.repoId = repoId;
	}

	public String getRepoTitle() {
		return this.repoTitle;
	}

	public void setRepoTitle(String repoTitle) {
		this.repoTitle = repoTitle;
	}

	public String getRepoLink() {
		return this.repoLink;
	}

	public void setRepoLink(String repoLink) {
		this.repoLink = repoLink;
	}

	public String getRepoCategory() {
		return this.repoCategory;
	}

	public void setRepoCategory(String repoCategory) {
		this.repoCategory = repoCategory;
	}

	public String getRepoOperator() {
		return this.repoOperator;
	}

	public void setRepoOperator(String repoOperator) {
		this.repoOperator = repoOperator;
	}

	public Timestamp getRepoSubDate() {
		return this.repoSubDate;
	}

	public void setRepoSubDate(Timestamp repoSubDate) {
		this.repoSubDate = repoSubDate;
	}

	public String getRepoStatus() {
		return this.repoStatus;
	}

	public void setRepoStatus(String repoStatus) {
		this.repoStatus = repoStatus;
	}

}