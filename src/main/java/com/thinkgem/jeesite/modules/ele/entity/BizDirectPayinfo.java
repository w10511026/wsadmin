package com.thinkgem.jeesite.modules.ele.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 直供缴费信息Entity
 * @author ws
 * @version 2017-11-06
 */
public class BizDirectPayinfo extends DataEntity<BizDirectPayinfo> {
	
	private static final long serialVersionUID = 1L;
	private Date spbilldate;		// 票据日期
	private String spaccnum;		// 户号
	private Date spstartdate;		// 起始日期
	private Date spenddate;		// 截止日期
	private String spstartdisplay;		// 起始读数
	private String spenddisplay;		// 截止读数
	private String spprestartdisplay;		// 老表起始读数
	private String sppreenddisplay;		// 老表截止读数
	private String sprate;		// 倍率
	private String sploss;		// 损耗
	private String sptotalq;		// 计费电量
	private String sptotalc;		// 票面金额
	private String spbilltype;		// 票据类型
	
	public BizDirectPayinfo() {
		super();
	}

	public BizDirectPayinfo(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="票据日期不能为空")
	public Date getSpbilldate() {
		return spbilldate;
	}

	public void setSpbilldate(Date spbilldate) {
		this.spbilldate = spbilldate;
	}
	
	@Length(min=1, max=20, message="户号长度必须介于 1 和 20 之间")
	public String getSpaccnum() {
		return spaccnum;
	}

	public void setSpaccnum(String spaccnum) {
		this.spaccnum = spaccnum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="起始日期不能为空")
	public Date getSpstartdate() {
		return spstartdate;
	}

	public void setSpstartdate(Date spstartdate) {
		this.spstartdate = spstartdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="截止日期不能为空")
	public Date getSpenddate() {
		return spenddate;
	}

	public void setSpenddate(Date spenddate) {
		this.spenddate = spenddate;
	}
	
	public String getSpstartdisplay() {
		return spstartdisplay;
	}

	public void setSpstartdisplay(String spstartdisplay) {
		this.spstartdisplay = spstartdisplay;
	}
	
	public String getSpenddisplay() {
		return spenddisplay;
	}

	public void setSpenddisplay(String spenddisplay) {
		this.spenddisplay = spenddisplay;
	}
	
	public String getSpprestartdisplay() {
		return spprestartdisplay;
	}

	public void setSpprestartdisplay(String spprestartdisplay) {
		this.spprestartdisplay = spprestartdisplay;
	}
	
	public String getSppreenddisplay() {
		return sppreenddisplay;
	}

	public void setSppreenddisplay(String sppreenddisplay) {
		this.sppreenddisplay = sppreenddisplay;
	}
	
	@Length(min=1, max=20, message="倍率长度必须介于 1 和 20 之间")
	public String getSprate() {
		return sprate;
	}

	public void setSprate(String sprate) {
		this.sprate = sprate;
	}
	
	public String getSploss() {
		return sploss;
	}

	public void setSploss(String sploss) {
		this.sploss = sploss;
	}
	
	public String getSptotalq() {
		return sptotalq;
	}

	public void setSptotalq(String sptotalq) {
		this.sptotalq = sptotalq;
	}
	
	public String getSptotalc() {
		return sptotalc;
	}

	public void setSptotalc(String sptotalc) {
		this.sptotalc = sptotalc;
	}
	
	@Length(min=1, max=20, message="票据类型长度必须介于 1 和 20 之间")
	public String getSpbilltype() {
		return spbilltype;
	}

	public void setSpbilltype(String spbilltype) {
		this.spbilltype = spbilltype;
	}
	
}