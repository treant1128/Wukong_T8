package com.wukong.t4.entitys;

// default package

import java.util.Date;

@SuppressWarnings("serial")
public class Admin implements java.io.Serializable {

	// Fields

	private Integer adminid;
	private String adminname;
	private Date createdate;
	private String password;

	// Constructors

	/** default constructor */
	public Admin() {
	}

	public Admin(Integer adminid, String adminname, Date createdate,
			String password) {
		this.adminid = adminid;
		this.adminname = adminname;
		this.createdate = createdate;
		this.password = password;
	}

	public Integer getAdminid() {
		return adminid;
	}

	public void setAdminid(Integer adminid) {
		this.adminid = adminid;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}