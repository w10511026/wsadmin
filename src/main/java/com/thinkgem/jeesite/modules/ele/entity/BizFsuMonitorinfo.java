package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * FSU监控信息Entity
 * @author ws
 * @version 2017-11-06
 */
public class BizFsuMonitorinfo extends DataEntity<BizFsuMonitorinfo> {
	
	private static final long serialVersionUID = 1L;
	private String fsusitenum;		// 站址编码
	private String fsuq;		// 监控电流
	private Date fsudate;		// 采集日期
	private Date beginFsudate;		// 开始 采集日期
	private Date endFsudate;		// 结束 采集日期
	
	public BizFsuMonitorinfo() {
		super();
	}

	public BizFsuMonitorinfo(String id){
		super(id);
	}

	@Length(min=1, max=30, message="站址编码长度必须介于 1 和 30 之间")
	public String getFsusitenum() {
		return fsusitenum;
	}

	public void setFsusitenum(String fsusitenum) {
		this.fsusitenum = fsusitenum;
	}
	
	public String getFsuq() {
		return fsuq;
	}

	public void setFsuq(String fsuq) {
		this.fsuq = fsuq;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="采集日期不能为空")
	public Date getFsudate() {
		return fsudate;
	}

	public void setFsudate(Date fsudate) {
		this.fsudate = fsudate;
	}
	
	public Date getBeginFsudate() {
		return beginFsudate;
	}

	public void setBeginFsudate(Date beginFsudate) {
		this.beginFsudate = beginFsudate;
	}
	
	public Date getEndFsudate() {
		return endFsudate;
	}

	public void setEndFsudate(Date endFsudate) {
		this.endFsudate = endFsudate;
	}
		
}