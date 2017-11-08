package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 转供缴费信息Entity
 * @author ws
 * @version 2017-11-06
 */
public class BizTransitPayinfo extends DataEntity<BizTransitPayinfo> {
	
	private static final long serialVersionUID = 1L;
	private String tpaccnum;		// 户号
	private Date tppaydate;		// 缴费日期
	private Date tpstartdate;		// 起始日期
	private Date tpenddate;		// 截止日期
	private String tpstartdisplay;		// 起始读数
	private String tpenddisplay;		// 截止读数
	private String tpprestartdisplay;		// 老表起始读数
	private String tpperenddisplay;		// 老表截止读数
	private String tprate;		// 倍率
	private String tploss;		// 损耗
	private String tptotalq;		// 计费电量
	private String tpbillprice;		// 电费单价
	private String tpmainc;		// 票面金额
	private String tpdktax;		// 代开税金
	private String tpbilltype;		// 票据类型（代开）
	private String tptotalc;		// 总金额
	private String tpsupplier;		// 供电方名称
	private Date tpdestdate;		// 核销日期
	private String tpbillnum;		// 报账单号
	private String tppaynum;		// 支付单号
	private String tpdestnum;		// 核销单号
	
	public BizTransitPayinfo() {
		super();
	}

	public BizTransitPayinfo(String id){
		super(id);
	}

	@Length(min=1, max=15, message="户号长度必须介于 1 和 15 之间")
	public String getTpaccnum() {
		return tpaccnum;
	}

	public void setTpaccnum(String tpaccnum) {
		this.tpaccnum = tpaccnum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="缴费日期不能为空")
	public Date getTppaydate() {
		return tppaydate;
	}

	public void setTppaydate(Date tppaydate) {
		this.tppaydate = tppaydate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="起始日期不能为空")
	public Date getTpstartdate() {
		return tpstartdate;
	}

	public void setTpstartdate(Date tpstartdate) {
		this.tpstartdate = tpstartdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="截止日期不能为空")
	public Date getTpenddate() {
		return tpenddate;
	}

	public void setTpenddate(Date tpenddate) {
		this.tpenddate = tpenddate;
	}
	
	public String getTpstartdisplay() {
		return tpstartdisplay;
	}

	public void setTpstartdisplay(String tpstartdisplay) {
		this.tpstartdisplay = tpstartdisplay;
	}
	
	public String getTpenddisplay() {
		return tpenddisplay;
	}

	public void setTpenddisplay(String tpenddisplay) {
		this.tpenddisplay = tpenddisplay;
	}
	
	public String getTpprestartdisplay() {
		return tpprestartdisplay;
	}

	public void setTpprestartdisplay(String tpprestartdisplay) {
		this.tpprestartdisplay = tpprestartdisplay;
	}
	
	public String getTpperenddisplay() {
		return tpperenddisplay;
	}

	public void setTpperenddisplay(String tpperenddisplay) {
		this.tpperenddisplay = tpperenddisplay;
	}
	
	@Length(min=1, max=20, message="倍率长度必须介于 1 和 20 之间")
	public String getTprate() {
		return tprate;
	}

	public void setTprate(String tprate) {
		this.tprate = tprate;
	}
	
	public String getTploss() {
		return tploss;
	}

	public void setTploss(String tploss) {
		this.tploss = tploss;
	}
	
	public String getTptotalq() {
		return tptotalq;
	}

	public void setTptotalq(String tptotalq) {
		this.tptotalq = tptotalq;
	}
	
	public String getTpbillprice() {
		return tpbillprice;
	}

	public void setTpbillprice(String tpbillprice) {
		this.tpbillprice = tpbillprice;
	}
	
	public String getTpmainc() {
		return tpmainc;
	}

	public void setTpmainc(String tpmainc) {
		this.tpmainc = tpmainc;
	}
	
	public String getTpdktax() {
		return tpdktax;
	}

	public void setTpdktax(String tpdktax) {
		this.tpdktax = tpdktax;
	}
	
	@Length(min=1, max=20, message="票据类型（代开）长度必须介于 1 和 20 之间")
	public String getTpbilltype() {
		return tpbilltype;
	}

	public void setTpbilltype(String tpbilltype) {
		this.tpbilltype = tpbilltype;
	}
	
	public String getTptotalc() {
		return tptotalc;
	}

	public void setTptotalc(String tptotalc) {
		this.tptotalc = tptotalc;
	}
	
	@Length(min=0, max=20, message="供电方名称长度必须介于 0 和 20 之间")
	public String getTpsupplier() {
		return tpsupplier;
	}

	public void setTpsupplier(String tpsupplier) {
		this.tpsupplier = tpsupplier;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTpdestdate() {
		return tpdestdate;
	}

	public void setTpdestdate(Date tpdestdate) {
		this.tpdestdate = tpdestdate;
	}
	
	@Length(min=0, max=30, message="报账单号长度必须介于 0 和 30 之间")
	public String getTpbillnum() {
		return tpbillnum;
	}

	public void setTpbillnum(String tpbillnum) {
		this.tpbillnum = tpbillnum;
	}
	
	@Length(min=0, max=35, message="支付单号长度必须介于 0 和 35 之间")
	public String getTppaynum() {
		return tppaynum;
	}

	public void setTppaynum(String tppaynum) {
		this.tppaynum = tppaynum;
	}
	
	@Length(min=0, max=35, message="核销单号长度必须介于 0 和 35 之间")
	public String getTpdestnum() {
		return tpdestnum;
	}

	public void setTpdestnum(String tpdestnum) {
		this.tpdestnum = tpdestnum;
	}
	
}