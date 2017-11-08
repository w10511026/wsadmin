package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 代维巡检信息Entity
 * @author ws
 * @version 2017-11-06
 */
public class BizReplaceCheckinfo extends DataEntity<BizReplaceCheckinfo> {
	
	private static final long serialVersionUID = 1L;
	private String inspsitenum;		// 站址编码
	private Date inspdate;		// 巡检日期
	private String inspdisplay;		// 抄表读数
	private String inspcmq;		// 移动电流
	private String inspctq;		// 电信电流
	private String inspcuq;		// 联通电流
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
	public String getInspsitenum() {
		return inspsitenum;
	}

	public void setInspsitenum(String inspsitenum) {
		this.inspsitenum = inspsitenum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="巡检日期不能为空")
	public Date getInspdate() {
		return inspdate;
	}

	public void setInspdate(Date inspdate) {
		this.inspdate = inspdate;
	}
	
	public String getInspdisplay() {
		return inspdisplay;
	}

	public void setInspdisplay(String inspdisplay) {
		this.inspdisplay = inspdisplay;
	}
	
	public String getInspcmq() {
		return inspcmq;
	}

	public void setInspcmq(String inspcmq) {
		this.inspcmq = inspcmq;
	}
	
	public String getInspctq() {
		return inspctq;
	}

	public void setInspctq(String inspctq) {
		this.inspctq = inspctq;
	}
	
	public String getInspcuq() {
		return inspcuq;
	}

	public void setInspcuq(String inspcuq) {
		this.inspcuq = inspcuq;
	}
	
	@Length(min=0, max=15, message="巡检人员长度必须介于 0 和 15 之间")
	public String getInsppeople() {
		return insppeople;
	}

	public void setInsppeople(String insppeople) {
		this.insppeople = insppeople;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
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