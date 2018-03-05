package com.thinkgem.jeesite.modules.ele.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * 操作日志Entity
 * @author 操作日志
 * @version 2018-03-05
 */
public class SysLog extends DataEntity<SysLog> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 日志类型
	private String title;		// 日志标题
	private String remoteAddr;		// 操作IP地址
	private String userAgent;		// 用户代理
	private String requestUri;		// 请求URI
	private String method;		// 操作方式
	private String params;		// 操作提交的数据
	private String exception;		// 异常信息
	private User createBy;	// 创建者
	private Date createDate;	// 创建日期

	public SysLog() {
		super();
	}

	public SysLog(String id){
		super(id);
	}

	@Length(min=0, max=1, message="日志类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="日志标题长度必须介于 0 和 255 之间")
	@ExcelField(title="日志标题", align=2, sort=42)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="操作IP地址长度必须介于 0 和 255 之间")
	@ExcelField(title="操作IP地址", align=2, sort=43)
	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	
	@Length(min=0, max=255, message="用户代理长度必须介于 0 和 255 之间")
	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	@Length(min=0, max=255, message="请求URI长度必须介于 0 和 255 之间")
	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	
	@Length(min=0, max=5, message="操作方式长度必须介于 0 和 5 之间")
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	@JsonIgnore
	@ExcelField(title="操作人", align=2, sort=1000)
	@Override
	public User getCreateBy() {
		return createBy;
	}
	@Override
	public void setCreateBy(User createBy) {
		this.createBy = createBy;
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