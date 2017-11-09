package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 转供抄表信息Entity
 * @author ws
 * @version 2017-11-08
 */
public class BizTransitCopymeterinfo extends DataEntity<BizTransitCopymeterinfo> {
	
	private static final long serialVersionUID = 1L;
	private String tdsitenum;		// 站址编码
	private Date tddate;		// 抄表日期
	private Double tddisplay;		// 抄表读数
	private String tdperson;		// 抄表人
	private String tdremark;		// 抄表读数
	private Date beginTddate;		// 开始 抄表日期
	private Date endTddate;		// 结束 抄表日期
	
	public BizTransitCopymeterinfo() {
		super();
	}

	public BizTransitCopymeterinfo(String id){
		super(id);
	}

	@Length(min=1, max=30, message="站址编码长度必须介于 1 和 30 之间")
	@ExcelField(title="站址编码", align=2, sort=10)
	public String getTdsitenum() {
		return tdsitenum;
	}

	public void setTdsitenum(String tdsitenum) {
		this.tdsitenum = tdsitenum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="抄表日期不能为空")
	@ExcelField(title="抄表日期", align=2, sort=10)
	public Date getTddate() {
		return tddate;
	}

	public void setTddate(Date tddate) {
		this.tddate = tddate;
	}
	
	@NotNull(message="抄表读数不能为空")
	@ExcelField(title="抄表读数", align=2, sort=10)
	public Double getTddisplay() {
		return tddisplay;
	}

	public void setTddisplay(Double tddisplay) {
		this.tddisplay = tddisplay;
	}
	
	@Length(min=0, max=10, message="抄表人长度必须介于 0 和 10 之间")
	@ExcelField(title="抄表人", align=2, sort=10)
	public String getTdperson() {
		return tdperson;
	}

	public void setTdperson(String tdperson) {
		this.tdperson = tdperson;
	}
	
	@Length(min=0, max=100, message="抄表读数长度必须介于 0 和 100 之间")
	@ExcelField(title="抄表读数", align=2, sort=10)
	public String getTdremark() {
		return tdremark;
	}

	public void setTdremark(String tdremark) {
		this.tdremark = tdremark;
	}
	
	public Date getBeginTddate() {
		return beginTddate;
	}

	public void setBeginTddate(Date beginTddate) {
		this.beginTddate = beginTddate;
	}
	
	public Date getEndTddate() {
		return endTddate;
	}

	public void setEndTddate(Date endTddate) {
		this.endTddate = endTddate;
	}
		
}