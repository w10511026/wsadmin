package com.thinkgem.jeesite.modules.ele.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * 操作日志Entity
 * @author 操作日志
 * @version 2018-03-13
 */
public class BizOperateLog extends DataEntity<BizOperateLog> {
	
	private static final long serialVersionUID = 1L;
	private String username;		// 登录名
	private String sisitenum;		// 站址编码
	private String sisitename;		// 站址名称
	private String type;		// 操作方式
	private String content;		// 备注
	private Date createDate;	// 创建日期

	public BizOperateLog() {
		super();
	}

	public BizOperateLog(String id){
		super(id);
	}

	public BizOperateLog(boolean isNewRecord, String id, String username, String sisitenum, String sisitename, String type, String content){
		this.isNewRecord = isNewRecord;
		this.id = id;
		this.username = username;
		this.sisitenum = sisitenum;
		this.sisitename = sisitename;
		this.type = type;
		this.content = content;
	}

	@Length(min=0, max=50, message="登录名长度必须介于 0 和 50 之间")
	@ExcelField(title="登录名", align=2, sort=26)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=1, max=30, message="站址编码长度必须介于 1 和 30 之间")
	@ExcelField(title="站址编码", align=2, sort=27)
	public String getSisitenum() {
		return sisitenum;
	}

	public void setSisitenum(String sisitenum) {
		this.sisitenum = sisitenum;
	}
	
	@Length(min=1, max=50, message="站址名称长度必须介于 1 和 50 之间")
	@ExcelField(title="站址名称", align=2, sort=28)
	public String getSisitename() {
		return sisitename;
	}

	public void setSisitename(String sisitename) {
		this.sisitename = sisitename;
	}
	
	@Length(min=0, max=30, message="操作方式长度必须介于 0 和 30 之间")
	@ExcelField(title="操作方式", align=2, sort=29)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	@ExcelField(title="备注", align=2, sort=30)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="操作时间", align=2, sort=2000)
	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}