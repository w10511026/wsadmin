package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 电流分摊信息Entity
 * @author ws
 * @version 2017-11-08
 */
public class BizElectricShareinfo extends DataEntity<BizElectricShareinfo> {
	
	private static final long serialVersionUID = 1L;
	private String rositenum;		// 站址编码
	private Double rocmq;		// 移动电流
	private Double roctq;		// 电信电流
	private Double rocuq;		// 联通电流
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
	@ExcelField(title="站址编码", align=2, sort=10)
	public String getRositenum() {
		return rositenum;
	}

	public void setRositenum(String rositenum) {
		this.rositenum = rositenum;
	}
	
	@NotNull(message="移动电流不能为空")
	@ExcelField(title="移动电流", align=2, sort=10)
	public Double getRocmq() {
		return rocmq;
	}

	public void setRocmq(Double rocmq) {
		this.rocmq = rocmq;
	}
	
	@NotNull(message="电信电流不能为空")
	@ExcelField(title="电信电流", align=2, sort=10)
	public Double getRoctq() {
		return roctq;
	}

	public void setRoctq(Double roctq) {
		this.roctq = roctq;
	}
	
	@NotNull(message="联通电流不能为空")
	@ExcelField(title="联通电流", align=2, sort=10)
	public Double getRocuq() {
		return rocuq;
	}

	public void setRocuq(Double rocuq) {
		this.rocuq = rocuq;
	}
	
	@Length(min=0, max=30, message="宿主站站址编码长度必须介于 0 和 30 之间")
	@ExcelField(title="宿主站站址编码", align=2, sort=10)
	public String getRohistnum() {
		return rohistnum;
	}

	public void setRohistnum(String rohistnum) {
		this.rohistnum = rohistnum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="更新日期不能为空")
	@ExcelField(title="更新日期", align=2, sort=10)
	public Date getRoupdatedate() {
		return roupdatedate;
	}

	public void setRoupdatedate(Date roupdatedate) {
		this.roupdatedate = roupdatedate;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	@ExcelField(title="备注", align=2, sort=10)
	public String getRoremarks() {
		return roremarks;
	}

	public void setRoremarks(String roremarks) {
		this.roremarks = roremarks;
	}
	
}