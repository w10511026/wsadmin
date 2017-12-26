package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 代维巡检信息Entity
 * @author ws
 * @version 2017-12-26
 */
public class BizReplaceCheckinfo extends DataEntity<BizReplaceCheckinfo> {
	
	private static final long serialVersionUID = 1L;
	private String inspsitenum;		// 站址编码
	private Date inspdate;		// 巡检日期
	private Double inspdisplay;		// 抄表读数
	private Double inspcmq;		// 移动电流
	private Double inspctq;		// 电信电流
	private Double inspcuq;		// 联通电流
	private String insppeople;		// 巡检人员
	private String inspremarks;		// 备注
	private Date beginInspdate;		// 开始 巡检日期
	private Date endInspdate;		// 结束 巡检日期
	
	public BizReplaceCheckinfo() {
		super();
	}

	public BizReplaceCheckinfo(String id){
		super(id);
	}

	@Length(min=1, max=30, message="站址编码长度必须介于 1 和 30 之间")
	@ExcelField(title="站址编码", align=2, sort=25)
	public String getInspsitenum() {
		return inspsitenum;
	}

	public void setInspsitenum(String inspsitenum) {
		this.inspsitenum = inspsitenum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="巡检日期不能为空")
	@ExcelField(title="巡检日期", align=2, sort=26)
	public Date getInspdate() {
		return inspdate;
	}

	public void setInspdate(Date inspdate) {
		this.inspdate = inspdate;
	}
	
	@ExcelField(title="抄表读数", align=2, sort=27)
	public Double getInspdisplay() {
		return inspdisplay;
	}

	public void setInspdisplay(Double inspdisplay) {
		this.inspdisplay = inspdisplay;
	}
	
	@NotNull(message="移动电流不能为空")
	@ExcelField(title="移动电流", align=2, sort=28)
	public Double getInspcmq() {
		return inspcmq;
	}

	public void setInspcmq(Double inspcmq) {
		this.inspcmq = inspcmq;
	}
	
	@NotNull(message="电信电流不能为空")
	@ExcelField(title="电信电流", align=2, sort=29)
	public Double getInspctq() {
		return inspctq;
	}

	public void setInspctq(Double inspctq) {
		this.inspctq = inspctq;
	}
	
	@NotNull(message="联通电流不能为空")
	@ExcelField(title="联通电流", align=2, sort=30)
	public Double getInspcuq() {
		return inspcuq;
	}

	public void setInspcuq(Double inspcuq) {
		this.inspcuq = inspcuq;
	}
	
	@Length(min=0, max=15, message="巡检人员长度必须介于 0 和 15 之间")
	@ExcelField(title="巡检人员", align=2, sort=31)
	public String getInsppeople() {
		return insppeople;
	}

	public void setInsppeople(String insppeople) {
		this.insppeople = insppeople;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	@ExcelField(title="备注", align=2, sort=32)
	public String getInspremarks() {
		return inspremarks;
	}

	public void setInspremarks(String inspremarks) {
		this.inspremarks = inspremarks;
	}
	
	public Date getBeginInspdate() {
		return beginInspdate;
	}

	public void setBeginInspdate(Date beginInspdate) {
		this.beginInspdate = beginInspdate;
	}
	
	public Date getEndInspdate() {
		return endInspdate;
	}

	public void setEndInspdate(Date endInspdate) {
		this.endInspdate = endInspdate;
	}
		
}