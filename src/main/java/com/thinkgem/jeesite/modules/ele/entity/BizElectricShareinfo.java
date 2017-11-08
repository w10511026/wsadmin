package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 电流分摊信息Entity
 * @author ws
 * @version 2017-11-06
 */
public class BizElectricShareinfo extends DataEntity<BizElectricShareinfo> {
	
	private static final long serialVersionUID = 1L;
	private String rositenum;		// 站址编码
	private String rocmq;		// 移动电流
	private String roctq;		// 电信电流
	private String rocuq;		// 联通电流
	private String rohistnum;		// 宿主站站址编码
	private Date roupdatedate;		// 更新日期
	private String roremarks;		// 备注
	
	public BizElectricShareinfo() {
		super();
	}

	public BizElectricShareinfo(String id){
		super(id);
	}

	@Length(min=1, max=30, message="站址编码长度必须介于 1 和 30 之间")
	public String getRositenum() {
		return rositenum;
	}

	public void setRositenum(String rositenum) {
		this.rositenum = rositenum;
	}
	
	public String getRocmq() {
		return rocmq;
	}

	public void setRocmq(String rocmq) {
		this.rocmq = rocmq;
	}
	
	public String getRoctq() {
		return roctq;
	}

	public void setRoctq(String roctq) {
		this.roctq = roctq;
	}
	
	public String getRocuq() {
		return rocuq;
	}

	public void setRocuq(String rocuq) {
		this.rocuq = rocuq;
	}
	
	@Length(min=0, max=30, message="宿主站站址编码长度必须介于 0 和 30 之间")
	public String getRohistnum() {
		return rohistnum;
	}

	public void setRohistnum(String rohistnum) {
		this.rohistnum = rohistnum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="更新日期不能为空")
	public Date getRoupdatedate() {
		return roupdatedate;
	}

	public void setRoupdatedate(Date roupdatedate) {
		this.roupdatedate = roupdatedate;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	public String getRoremarks() {
		return roremarks;
	}

	public void setRoremarks(String roremarks) {
		this.roremarks = roremarks;
	}
	
}