package com.thinkgem.jeesite.common.dto;

import java.io.Serializable;

public class AjaxMsg implements Serializable {
	
	private static final long serialVersionUID = -7280762947965610763L;

	/**
	 * 状态码
	 */
	private String status;

	/**
	 * 消息内容
	 */
	private String message;

	/**
	 * 数据(List或者Map)
	 */
	private Object data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public AjaxMsg(String status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public AjaxMsg(String status, String message) {
		this.status = status;
		this.message = message;
	}

	@Override
	public String toString() {
		return "AjaxMsg [status=" + status + ", message=" + message + "]";
	}

}
