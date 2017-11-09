package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 转供合同信息Entity
 * @author ws
 * @version 2017-11-08
 */
public class BizTransitContractinfo extends DataEntity<BizTransitContractinfo> {
	
	private static final long serialVersionUID = 1L;
	private String tconsitename;		// 站址编码
	private String tconsitenum;		// 合同编码
	private String tconpa;		// 供电方名称
	private Date tconstartdate;		// 合同起始日期
	private Date tconenddate;		// 合同截止日期
	private Double tconprice;		// 合同单价
	private String tcontype;		// 票据类型
	private String tcremark;		// 备注
	private Double beginTconprice;		// 开始 合同单价
	private Double endTconprice;		// 结束 合同单价
	
	public BizTransitContractinfo() {
		super();
	}

	public BizTransitContractinfo(String id){
		super(id);
	}

	@Length(min=1, max=30, message="站址编码长度必须介于 1 和 30 之间")
	@ExcelField(title="站址编码", align=2, sort=10)
	public String getTconsitename() {
		return tconsitename;
	}

	public void setTconsitename(String tconsitename) {
		this.tconsitename = tconsitename;
	}
	
	@Length(min=1, max=20, message="合同编码长度必须介于 1 和 20 之间")
	@ExcelField(title="合同编码", align=2, sort=10)
	public String getTconsitenum() {
		return tconsitenum;
	}

	public void setTconsitenum(String tconsitenum) {
		this.tconsitenum = tconsitenum;
	}
	
	@Length(min=1, max=20, message="供电方名称长度必须介于 1 和 20 之间")
	@ExcelField(title="供电方名称", align=2, sort=10)
	public String getTconpa() {
		return tconpa;
	}

	public void setTconpa(String tconpa) {
		this.tconpa = tconpa;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="合同起始日期不能为空")
	@ExcelField(title="合同起始日期", align=2, sort=10)
	public Date getTconstartdate() {
		return tconstartdate;
	}

	public void setTconstartdate(Date tconstartdate) {
		this.tconstartdate = tconstartdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="合同截止日期不能为空")
	@ExcelField(title="合同截止日期", align=2, sort=10)
	public Date getTconenddate() {
		return tconenddate;
	}

	public void setTconenddate(Date tconenddate) {
		this.tconenddate = tconenddate;
	}
	
	@NotNull(message="合同单价不能为空")
	@ExcelField(title="合同单价", align=2, sort=10)
	public Double getTconprice() {
		return tconprice;
	}

	public void setTconprice(Double tconprice) {
		this.tconprice = tconprice;
	}
	
	@Length(min=1, max=20, message="票据类型长度必须介于 1 和 20 之间")
	@ExcelField(title="票据类型", align=2, sort=10)
	public String getTcontype() {
		return tcontype;
	}

	public void setTcontype(String tcontype) {
		this.tcontype = tcontype;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	@ExcelField(title="备注", align=2, sort=10)
	public String getTcremark() {
		return tcremark;
	}

	public void setTcremark(String tcremark) {
		this.tcremark = tcremark;
	}
	
	public Double getBeginTconprice() {
		return beginTconprice;
	}

	public void setBeginTconprice(Double beginTconprice) {
		this.beginTconprice = beginTconprice;
	}
	
	public Double getEndTconprice() {
		return endTconprice;
	}

	public void setEndTconprice(Double endTconprice) {
		this.endTconprice = endTconprice;
	}
		
}