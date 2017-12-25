package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 直供缴费信息Entity
 * @author ws
 * @version 2017-12-25
 */
public class BizDirectPayinfo extends DataEntity<BizDirectPayinfo> {
	
	private static final long serialVersionUID = 1L;
	private String spbilldate;		// 账期
	private String spaccnum;		// 户号
	private Date spstartdate;		// 起始日期
	private Date spenddate;		// 截止日期
	private Double spstartdisplay;		// 起始读数
	private Double spenddisplay;		// 截止读数
	private Double spprestartdisplay;		// 老表起始读数
	private Double sppreenddisplay;		// 老表截止读数
	private String sprate;		// 倍率
	private Double sploss;		// 损耗
	private Double sptotalq;		// 计费电量
	private Double sptotalc;		// 票面金额
	private String spbilltype;		// 票据类型
	private Date spttstartdate;		// 铁塔起始日期
	private Date spttenddate;		// 铁塔截止日期
	
	public BizDirectPayinfo() {
		super();
	}

	public BizDirectPayinfo(String id){
		super(id);
	}

	@Length(min=1, max=20, message="账期长度必须介于 1 和 20 之间")
	@ExcelField(title="账期", align=2, sort=16)
	public String getSpbilldate() {
		return spbilldate;
	}

	public void setSpbilldate(String spbilldate) {
		this.spbilldate = spbilldate;
	}
	
	@Length(min=1, max=20, message="户号长度必须介于 1 和 20 之间")
	@ExcelField(title="户号", align=2, sort=17)
	public String getSpaccnum() {
		return spaccnum;
	}

	public void setSpaccnum(String spaccnum) {
		this.spaccnum = spaccnum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="起始日期不能为空")
	@ExcelField(title="起始日期", align=2, sort=18)
	public Date getSpstartdate() {
		return spstartdate;
	}

	public void setSpstartdate(Date spstartdate) {
		this.spstartdate = spstartdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="截止日期不能为空")
	@ExcelField(title="截止日期", align=2, sort=19)
	public Date getSpenddate() {
		return spenddate;
	}

	public void setSpenddate(Date spenddate) {
		this.spenddate = spenddate;
	}
	
	@NotNull(message="起始读数不能为空")
	@ExcelField(title="起始读数", align=2, sort=20)
	public Double getSpstartdisplay() {
		return spstartdisplay;
	}

	public void setSpstartdisplay(Double spstartdisplay) {
		this.spstartdisplay = spstartdisplay;
	}
	
	@NotNull(message="截止读数不能为空")
	@ExcelField(title="截止读数", align=2, sort=21)
	public Double getSpenddisplay() {
		return spenddisplay;
	}

	public void setSpenddisplay(Double spenddisplay) {
		this.spenddisplay = spenddisplay;
	}
	
	@NotNull(message="老表起始读数不能为空")
	@ExcelField(title="老表起始读数", align=2, sort=22)
	public Double getSpprestartdisplay() {
		return spprestartdisplay;
	}

	public void setSpprestartdisplay(Double spprestartdisplay) {
		this.spprestartdisplay = spprestartdisplay;
	}
	
	@NotNull(message="老表截止读数不能为空")
	@ExcelField(title="老表截止读数", align=2, sort=23)
	public Double getSppreenddisplay() {
		return sppreenddisplay;
	}

	public void setSppreenddisplay(Double sppreenddisplay) {
		this.sppreenddisplay = sppreenddisplay;
	}
	
	@Length(min=1, max=20, message="倍率长度必须介于 1 和 20 之间")
	@ExcelField(title="倍率", align=2, sort=24)
	public String getSprate() {
		return sprate;
	}

	public void setSprate(String sprate) {
		this.sprate = sprate;
	}
	
	@NotNull(message="损耗不能为空")
	@ExcelField(title="损耗", align=2, sort=25)
	public Double getSploss() {
		return sploss;
	}

	public void setSploss(Double sploss) {
		this.sploss = sploss;
	}
	
	@ExcelField(title="计费电量", align=2, sort=26)
	public Double getSptotalq() {
		return sptotalq;
	}

	public void setSptotalq(Double sptotalq) {
		this.sptotalq = sptotalq;
	}
	
	@NotNull(message="票面金额不能为空")
	@ExcelField(title="票面金额", align=2, sort=27)
	public Double getSptotalc() {
		return sptotalc;
	}

	public void setSptotalc(Double sptotalc) {
		this.sptotalc = sptotalc;
	}
	
	@ExcelField(title="票据类型", align=2, sort=28)
	public String getSpbilltype() {
		return spbilltype;
	}

	public void setSpbilltype(String spbilltype) {
		this.spbilltype = spbilltype;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="铁塔起始日期不能为空")
	@ExcelField(title="铁塔起始日期", align=2, sort=29)
	public Date getSpttstartdate() {
		return spttstartdate;
	}

	public void setSpttstartdate(Date spttstartdate) {
		this.spttstartdate = spttstartdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="铁塔截止日期不能为空")
	@ExcelField(title="铁塔截止日期", align=2, sort=30)
	public Date getSpttenddate() {
		return spttenddate;
	}

	public void setSpttenddate(Date spttenddate) {
		this.spttenddate = spttenddate;
	}
	
}