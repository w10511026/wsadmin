package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 表站对应信息Entity
 * @author ws
 * @version 2017-12-25
 */
public class BizSiteMeterinfo extends DataEntity<BizSiteMeterinfo> {
	
	private static final long serialVersionUID = 1L;
	private String amsitenum;		// 站址编码
	private String amnum;		// 电表户号
	private Date amstartdate;		// 起始日期
	private String amremarks;		// 备注
	
	public BizSiteMeterinfo() {
		super();
	}

	public BizSiteMeterinfo(String id){
		super(id);
	}

	@Length(min=1, max=30, message="站址编码长度必须介于 1 和 30 之间")
	@ExcelField(title="站址编码", align=2, sort=27)
	public String getAmsitenum() {
		return amsitenum;
	}

	public void setAmsitenum(String amsitenum) {
		this.amsitenum = amsitenum;
	}
	
	@Length(min=1, max=30, message="电表户号长度必须介于 1 和 30 之间")
	@ExcelField(title="电表户号", align=2, sort=28)
	public String getAmnum() {
		return amnum;
	}

	public void setAmnum(String amnum) {
		this.amnum = amnum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="起始日期不能为空")
	@ExcelField(title="起始日期", align=2, sort=29)
	public Date getAmstartdate() {
		return amstartdate;
	}

	public void setAmstartdate(Date amstartdate) {
		this.amstartdate = amstartdate;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	@ExcelField(title="备注", align=2, sort=30)
	public String getAmremarks() {
		return amremarks;
	}

	public void setAmremarks(String amremarks) {
		this.amremarks = amremarks;
	}
	
}