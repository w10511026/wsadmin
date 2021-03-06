package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 站址基础信息Entity
 * @author ws
 * @version 2017-12-26
 */
public class BizSiteBaseinfo extends DataEntity<BizSiteBaseinfo> {
	
	private static final long serialVersionUID = 1L;
	private String sidistrict;		// 区县
	private String sisitenum;		// 站址编码
	private String sisitename;		// 站址名称
	private String sipropertyunit;		// 原产权单位
	private String siroomstyle;		// 机房类型
	private String siretain;		// 保留站信息
	private Date sicmq;		// 移动起租日期
	private Date sictq;		// 电信起租日期
	private Date sicuq;		// 联通起租日期
	private String siremarks;		// 备注
	
	public BizSiteBaseinfo() {
		super();
	}

	public BizSiteBaseinfo(String id){
		super(id);
	}

	@Length(min=1, max=6, message="区县长度必须介于 1 和 6 之间")
	@ExcelField(title="区县", align=2, sort=31)
	public String getSidistrict() {
		return sidistrict;
	}

	public void setSidistrict(String sidistrict) {
		this.sidistrict = sidistrict;
	}
	
	@Length(min=1, max=30, message="站址编码长度必须介于 1 和 30 之间")
	@ExcelField(title="站址编码", align=2, sort=32)
	public String getSisitenum() {
		return sisitenum;
	}

	public void setSisitenum(String sisitenum) {
		this.sisitenum = sisitenum;
	}
	
	@Length(min=1, max=50, message="站址名称长度必须介于 1 和 50 之间")
	@ExcelField(title="站址名称", align=2, sort=33)
	public String getSisitename() {
		return sisitename;
	}

	public void setSisitename(String sisitename) {
		this.sisitename = sisitename;
	}
	
	@Length(min=1, max=6, message="原产权单位长度必须介于 1 和 6 之间")
	@ExcelField(title="原产权单位", align=2, sort=34)
	public String getSipropertyunit() {
		return sipropertyunit;
	}

	public void setSipropertyunit(String sipropertyunit) {
		this.sipropertyunit = sipropertyunit;
	}
	
	@Length(min=1, max=20, message="机房类型长度必须介于 1 和 20 之间")
	@ExcelField(title="机房类型", align=2, sort=35)
	public String getSiroomstyle() {
		return siroomstyle;
	}

	public void setSiroomstyle(String siroomstyle) {
		this.siroomstyle = siroomstyle;
	}
	
	@Length(min=0, max=6, message="保留站信息长度必须介于 0 和 6 之间")
	@ExcelField(title="保留站信息", align=2, sort=36)
	public String getSiretain() {
		return siretain;
	}

	public void setSiretain(String siretain) {
		this.siretain = siretain;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="移动起租日期", align=2, sort=37)
	public Date getSicmq() {
		return sicmq;
	}

	public void setSicmq(Date sicmq) {
		this.sicmq = sicmq;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="电信起租日期", align=2, sort=38)
	public Date getSictq() {
		return sictq;
	}

	public void setSictq(Date sictq) {
		this.sictq = sictq;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="联通起租日期", align=2, sort=39)
	public Date getSicuq() {
		return sicuq;
	}

	public void setSicuq(Date sicuq) {
		this.sicuq = sicuq;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	@ExcelField(title="备注", align=2, sort=40)
	public String getSiremarks() {
		return siremarks;
	}

	public void setSiremarks(String siremarks) {
		this.siremarks = siremarks;
	}
	
}