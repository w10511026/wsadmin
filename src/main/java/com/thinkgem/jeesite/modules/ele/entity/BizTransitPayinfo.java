package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 转供缴费信息Entity
 * @author ws
 * @version 2017-11-08
 */
public class BizTransitPayinfo extends DataEntity<BizTransitPayinfo> {
	
	private static final long serialVersionUID = 1L;
	private String tpaccnum;		// 户号
	private Date tppaydate;		// 缴费日期
	private Date tpstartdate;		// 起始日期
	private Date tpenddate;		// 截止日期
	private Double tpstartdisplay;		// 起始读数
	private Double tpenddisplay;		// 截止读数
	private Double tpprestartdisplay;		// 老表起始读数
	private Double tpperenddisplay;		// 老表截止读数
	private Integer tprate;		// 倍率
	private Double tploss;		// 损耗
	private Double tptotalq;		// 计费电量
	private Double tpbillprice;		// 电费单价
	private Double tpmainc;		// 票面金额
	private Double tpdktax;		// 代开税金
	private String tpbilltype;		// 票据类型（代开）
	private Double tptotalc;		// 总金额
	private String tpsupplier;		// 供电方名称
	private Date tpdestdate;		// 核销日期
	private String tpbillnum;		// 报账单号
	private String tppaynum;		// 支付单号
	private String tpdestnum;		// 核销单号
	private Date beginTppaydate;		// 开始 缴费日期
	private Date endTppaydate;		// 结束 缴费日期
	
	public BizTransitPayinfo() {
		super();
	}

	public BizTransitPayinfo(String id){
		super(id);
	}

	@Length(min=1, max=15, message="户号长度必须介于 1 和 15 之间")
	@ExcelField(title="户号", align=2, sort=10)
	public String getTpaccnum() {
		return tpaccnum;
	}

	public void setTpaccnum(String tpaccnum) {
		this.tpaccnum = tpaccnum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="缴费日期不能为空")
	@ExcelField(title="缴费日期", align=2, sort=10)
	public Date getTppaydate() {
		return tppaydate;
	}

	public void setTppaydate(Date tppaydate) {
		this.tppaydate = tppaydate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="起始日期不能为空")
	@ExcelField(title="起始日期", align=2, sort=10)
	public Date getTpstartdate() {
		return tpstartdate;
	}

	public void setTpstartdate(Date tpstartdate) {
		this.tpstartdate = tpstartdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="截止日期不能为空")
	@ExcelField(title="截止日期", align=2, sort=10)
	public Date getTpenddate() {
		return tpenddate;
	}

	public void setTpenddate(Date tpenddate) {
		this.tpenddate = tpenddate;
	}
	
	@NotNull(message="起始读数不能为空")
	@ExcelField(title="起始读数", align=2, sort=10)
	public Double getTpstartdisplay() {
		return tpstartdisplay;
	}

	public void setTpstartdisplay(Double tpstartdisplay) {
		this.tpstartdisplay = tpstartdisplay;
	}
	
	@NotNull(message="截止读数不能为空")
	@ExcelField(title="截止读数", align=2, sort=10)
	public Double getTpenddisplay() {
		return tpenddisplay;
	}

	public void setTpenddisplay(Double tpenddisplay) {
		this.tpenddisplay = tpenddisplay;
	}
	
	@NotNull(message="老表起始读数不能为空")
	@ExcelField(title="老表起始读数", align=2, sort=10)
	public Double getTpprestartdisplay() {
		return tpprestartdisplay;
	}

	public void setTpprestartdisplay(Double tpprestartdisplay) {
		this.tpprestartdisplay = tpprestartdisplay;
	}
	
	@NotNull(message="老表截止读数不能为空")
	@ExcelField(title="老表截止读数", align=2, sort=10)
	public Double getTpperenddisplay() {
		return tpperenddisplay;
	}

	public void setTpperenddisplay(Double tpperenddisplay) {
		this.tpperenddisplay = tpperenddisplay;
	}
	
	@NotNull(message="倍率不能为空")
	@ExcelField(title="倍率", align=2, sort=10)
	public Integer getTprate() {
		return tprate;
	}

	public void setTprate(Integer tprate) {
		this.tprate = tprate;
	}
	
	@NotNull(message="损耗不能为空")
	@ExcelField(title="损耗", align=2, sort=10)
	public Double getTploss() {
		return tploss;
	}

	public void setTploss(Double tploss) {
		this.tploss = tploss;
	}
	
	@ExcelField(title="计费电量", align=2, sort=10)
	public Double getTptotalq() {
		return tptotalq;
	}

	public void setTptotalq(Double tptotalq) {
		this.tptotalq = tptotalq;
	}
	
	@ExcelField(title="电费单价", align=2, sort=10)
	public Double getTpbillprice() {
		return tpbillprice;
	}

	public void setTpbillprice(Double tpbillprice) {
		this.tpbillprice = tpbillprice;
	}
	
	@NotNull(message="票面金额不能为空")
	@ExcelField(title="票面金额", align=2, sort=10)
	public Double getTpmainc() {
		return tpmainc;
	}

	public void setTpmainc(Double tpmainc) {
		this.tpmainc = tpmainc;
	}
	
	@NotNull(message="代开税金不能为空")
	@ExcelField(title="代开税金", align=2, sort=10)
	public Double getTpdktax() {
		return tpdktax;
	}

	public void setTpdktax(Double tpdktax) {
		this.tpdktax = tpdktax;
	}
	
	@Length(min=1, max=20, message="票据类型（代开）长度必须介于 1 和 20 之间")
	@ExcelField(title="票据类型（代开）", align=2, sort=10)
	public String getTpbilltype() {
		return tpbilltype;
	}

	public void setTpbilltype(String tpbilltype) {
		this.tpbilltype = tpbilltype;
	}
	
	@ExcelField(title="总金额", align=2, sort=10)
	public Double getTptotalc() {
		return tptotalc;
	}

	public void setTptotalc(Double tptotalc) {
		this.tptotalc = tptotalc;
	}
	
	@Length(min=0, max=20, message="供电方名称长度必须介于 0 和 20 之间")
	@ExcelField(title="供电方名称", align=2, sort=10)
	public String getTpsupplier() {
		return tpsupplier;
	}

	public void setTpsupplier(String tpsupplier) {
		this.tpsupplier = tpsupplier;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="核销日期", align=2, sort=10)
	public Date getTpdestdate() {
		return tpdestdate;
	}

	public void setTpdestdate(Date tpdestdate) {
		this.tpdestdate = tpdestdate;
	}
	
	@Length(min=0, max=30, message="报账单号长度必须介于 0 和 30 之间")
	@ExcelField(title="报账单号", align=2, sort=10)
	public String getTpbillnum() {
		return tpbillnum;
	}

	public void setTpbillnum(String tpbillnum) {
		this.tpbillnum = tpbillnum;
	}
	
	@Length(min=0, max=35, message="支付单号长度必须介于 0 和 35 之间")
	@ExcelField(title="支付单号", align=2, sort=10)
	public String getTppaynum() {
		return tppaynum;
	}

	public void setTppaynum(String tppaynum) {
		this.tppaynum = tppaynum;
	}
	
	@Length(min=0, max=35, message="核销单号长度必须介于 0 和 35 之间")
	@ExcelField(title="核销单号", align=2, sort=10)
	public String getTpdestnum() {
		return tpdestnum;
	}

	public void setTpdestnum(String tpdestnum) {
		this.tpdestnum = tpdestnum;
	}
	
	public Date getBeginTppaydate() {
		return beginTppaydate;
	}

	public void setBeginTppaydate(Date beginTppaydate) {
		this.beginTppaydate = beginTppaydate;
	}
	
	public Date getEndTppaydate() {
		return endTppaydate;
	}

	public void setEndTppaydate(Date endTppaydate) {
		this.endTppaydate = endTppaydate;
	}
		
}