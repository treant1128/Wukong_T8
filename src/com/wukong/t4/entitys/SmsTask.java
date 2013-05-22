package com.wukong.t4.entitys;

import static javax.persistence.GenerationType.IDENTITY;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
*/
@SuppressWarnings("serial")
@Entity
@Table(name = "t_sms_task")
public class SmsTask implements java.io.Serializable {
	
	private Integer id;                   
	private Integer priority;             
	private Integer type = 1;                 
	private Integer method;               
	private String content;              
	private Integer longTitle = 0;           
	private Integer jump = 0;                 
	private String sendTitle;           
	private String sendOUrl;           
	private String sendSUrl;           
	private Integer sendTimeType;       
	private String sendTime;            
	private String createTime;          
	private Integer lockFlag = 0;            
	private Integer blackFilter = 0;         
	private String excfile; 
	private String qcfile;
	private String srcfile;              
	private String sendfile;             
	private String flag;                 
	private String opadmin; 
	private Integer qcType;
	private Integer opStep = 0;
	private Integer delFlag = 0;
	private Integer vip = 0;
	private String intervalTime;
	private Integer numCount;
	private String dataDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	
	
	public SmsTask() {
	}

	public SmsTask(Integer id, Integer priority, Integer type, Integer method,
			String content, Integer longTitle, Integer jump, String sendTitle,
			String sendOUrl, String sendSUrl, Integer sendTimeType,
			String sendTime, String createTime, Integer lockFlag,
			Integer blackFilter, String excfile, String qcfile, String srcfile,
			String sendfile, String flag, String opadmin, Integer qcType,
			Integer opStep, Integer delFlag, Integer vip, String intervalTime,
			Integer numCount, String dataDate) {
		super();
		this.id = id;
		this.priority = priority;
		this.type = type;
		this.method = method;
		this.content = content;
		this.longTitle = longTitle;
		this.jump = jump;
		this.sendTitle = sendTitle;
		this.sendOUrl = sendOUrl;
		this.sendSUrl = sendSUrl;
		this.sendTimeType = sendTimeType;
		this.sendTime = sendTime;
		this.createTime = createTime;
		this.lockFlag = lockFlag;
		this.blackFilter = blackFilter;
		this.excfile = excfile;
		this.qcfile = qcfile;
		this.srcfile = srcfile;
		this.sendfile = sendfile;
		this.flag = flag;
		this.opadmin = opadmin;
		this.qcType = qcType;
		this.opStep = opStep;
		this.delFlag = delFlag;
		this.vip = vip;
		this.intervalTime = intervalTime;
		this.numCount = numCount;
		this.dataDate = dataDate;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "priority")
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name = "method")
	public Integer getMethod() {
		return method;
	}

	public void setMethod(Integer method) {
		this.method = method;
	}
	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "long_title")
	public Integer getLongTitle() {
		return longTitle;
	}

	public void setLongTitle(Integer longTitle) {
		this.longTitle = longTitle;
	}
	
	@Column(name = "jump")
	public Integer getJump() {
		return jump;
	}

	public void setJump(Integer jump) {
		this.jump = jump;
	}
	@Column(name = "send_title")
	public String getSendTitle() {
		return sendTitle;
	}

	public void setSendTitle(String sendTitle) {
		this.sendTitle = sendTitle;
	}
	@Column(name = "send_o_url")
	public String getSendOUrl() {
		return sendOUrl;
	}

	public void setSendOUrl(String sendOUrl) {
		this.sendOUrl = sendOUrl;
	}
	@Column(name = "send_s_url")
	public String getSendSUrl() {
		return sendSUrl;
	}

	public void setSendSUrl(String sendSUrl) {
		this.sendSUrl = sendSUrl;
	}
	@Column(name = "send_time_type")
	public Integer getSendTimeType() {
		return sendTimeType;
	}

	public void setSendTimeType(Integer sendTimeType) {
		this.sendTimeType = sendTimeType;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "send_time", length = 0)
	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 0)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name = "lock_flag")
	public Integer getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(Integer lockFlag) {
		this.lockFlag = lockFlag;
	}
	@Column(name = "black_filter")
	public Integer getBlackFilter() {
		return blackFilter;
	}

	public void setBlackFilter(Integer blackFilter) {
		this.blackFilter = blackFilter;
	}
	@Column(name = "excfile")
	public String getExcfile() {
		return excfile;
	}

	public void setExcfile(String excfile) {
		this.excfile = excfile;
	}
	@Column(name = "srcfile")
	public String getSrcfile() {
		return srcfile;
	}

	public void setSrcfile(String srcfile) {
		this.srcfile = srcfile;
	}
	@Column(name = "sendfile")
	public String getSendfile() {
		return sendfile;
	}

	public void setSendfile(String sendfile) {
		this.sendfile = sendfile;
	}
	@Column(name = "flag")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name = "opadmin")
	public String getOpadmin() {
		return opadmin;
	}

	public void setOpadmin(String opadmin) {
		this.opadmin = opadmin;
	}
	@Column(name = "qc_type")
	public Integer getQcType() {
		return qcType;
	}

	public void setQcType(Integer qcType) {
		this.qcType = qcType;
	}
	@Column(name = "op_step")
	public Integer getOpStep() {
		return opStep;
	}

	public void setOpStep(Integer opStep) {
		this.opStep = opStep;
	}
	@Column(name = "del_flag")
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	@Column(name = "vip")
	public Integer getVip() {
		return vip;
	}

	public void setVip(Integer vip) {
		this.vip = vip;
	}
	@Column(name = "qcfile")
	public String getQcfile() {
		return qcfile;
	}

	public void setQcfile(String qcfile) {
		this.qcfile = qcfile;
	}
	@Column(name = "interval_time")
	public String getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(String intervalTime) {
		this.intervalTime = intervalTime;
	}
	@Column(name = "num_count")
	public Integer getNumCount() {
		return numCount;
	}

	public void setNumCount(Integer numCount) {
		this.numCount = numCount;
	}

	@Column(name = "data_date")
	public String getDataDate() {
		return dataDate;
	}

	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}
	
	
}