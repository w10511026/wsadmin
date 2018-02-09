package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 基础电流分摊信息Entity
 * @author ws
 * @version 2018-02-09
 */
public class BizBasicShareinfo extends DataEntity<BizBasicShareinfo> {
	
	private static final long serialVersionUID = 1L;
	private String sisitenum;		// 站址编码
	private String sipropertyunit;		// 原产权
	private String sidistrict;		// 区县
	private String sisitename;		// 基站名称
	private String sicmq;		// 移动起租
	private String sictq;		// 电信起租
	private String sicuq;		// 联通起租
	private String simaintainstatus;		// 维护状态
	private String siroomstyle;		// 机房类型
	private String siisretain;		// 是否保留站
	private String supplytype;		// 供电方式
	private String leadtype;		// 引电方式
	private String rohistnum;		// 宿主站编码
	private String rohistname;		// 宿主站站名
	private String sinum;		// 电表户号
	private String sibilltype;		// 票据类型
	private String outcityeleremarks;		// 外市电信息备注
	private Double singlestatmq;		// 单站移动
	private Double singlestattq;		// 单站电信
	private Double singlestatuq;		// 单站联通
	private Double singletotal;		// 单站总电流
	private Double sharedmetermq;		// 共表移动
	private Double sharedmetertq;		// 共表电信
	private Double sharedmeteruq;		// 共表联通
	private Double sharedmetertotal;		// 共表总电流
	private String singletomanystatinfo;		// 一表多站信息
	private String stealeleinfo;		// 偷电搭电信息
	private String otherinfo;		// 其他信息
	
	public BizBasicShareinfo() {
		super();
	}

	public BizBasicShareinfo(String id){
		super(id);
	}

	@Length(min=1, max=30, message="站址编码长度必须介于 1 和 30 之间")
	@ExcelField(title="站址编码", align=2, sort=29)
	public String getSisitenum() {
		return sisitenum;
	}

	public void setSisitenum(String sisitenum) {
		this.sisitenum = sisitenum;
	}
	
	@Length(min=1, max=10, message="原产权长度必须介于 1 和 10 之间")
	@ExcelField(title="原产权", align=2, sort=30)
	public String getSipropertyunit() {
		return sipropertyunit;
	}

	public void setSipropertyunit(String sipropertyunit) {
		this.sipropertyunit = sipropertyunit;
	}
	
	@Length(min=1, max=6, message="区县长度必须介于 1 和 6 之间")
	@ExcelField(title="区县", align=2, sort=31)
	public String getSidistrict() {
		return sidistrict;
	}

	public void setSidistrict(String sidistrict) {
		this.sidistrict = sidistrict;
	}
	
	@Length(min=1, max=50, message="基站名称长度必须介于 1 和 50 之间")
	@ExcelField(title="基站名称", align=2, sort=32)
	public String getSisitename() {
		return sisitename;
	}

	public void setSisitename(String sisitename) {
		this.sisitename = sisitename;
	}
	
	@Length(min=0, max=20, message="移动起租长度必须介于 0 和 20 之间")
	@ExcelField(title="移动起租", align=2, sort=33)
	public String getSicmq() {
		return sicmq;
	}

	public void setSicmq(String sicmq) {
		this.sicmq = sicmq;
	}
	
	@Length(min=0, max=20, message="电信起租长度必须介于 0 和 20 之间")
	@ExcelField(title="电信起租", align=2, sort=34)
	public String getSictq() {
		return sictq;
	}

	public void setSictq(String sictq) {
		this.sictq = sictq;
	}
	
	@Length(min=0, max=20, message="联通起租长度必须介于 0 和 20 之间")
	@ExcelField(title="联通起租", align=2, sort=35)
	public String getSicuq() {
		return sicuq;
	}

	public void setSicuq(String sicuq) {
		this.sicuq = sicuq;
	}
	
	@Length(min=0, max=20, message="维护状态长度必须介于 0 和 20 之间")
	@ExcelField(title="维护状态", align=2, sort=36)
	public String getSimaintainstatus() {
		return simaintainstatus;
	}

	public void setSimaintainstatus(String simaintainstatus) {
		this.simaintainstatus = simaintainstatus;
	}
	
	@Length(min=0, max=20, message="机房类型长度必须介于 0 和 20 之间")
	@ExcelField(title="机房类型", align=2, sort=37)
	public String getSiroomstyle() {
		return siroomstyle;
	}

	public void setSiroomstyle(String siroomstyle) {
		this.siroomstyle = siroomstyle;
	}
	
	@Length(min=0, max=6, message="是否保留站长度必须介于 0 和 6 之间")
	@ExcelField(title="是否保留站", align=2, sort=38)
	public String getSiisretain() {
		return siisretain;
	}

	public void setSiisretain(String siisretain) {
		this.siisretain = siisretain;
	}
	
	@Length(min=0, max=20, message="供电方式长度必须介于 0 和 20 之间")
	@ExcelField(title="供电方式", align=2, sort=39)
	public String getSupplytype() {
		return supplytype;
	}

	public void setSupplytype(String supplytype) {
		this.supplytype = supplytype;
	}
	
	@Length(min=0, max=20, message="引电方式长度必须介于 0 和 20 之间")
	@ExcelField(title="引电方式", align=2, sort=40)
	public String getLeadtype() {
		return leadtype;
	}

	public void setLeadtype(String leadtype) {
		this.leadtype = leadtype;
	}
	
	@Length(min=1, max=30, message="宿主站编码长度必须介于 1 和 30 之间")
	@ExcelField(title="宿主站编码", align=2, sort=41)
	public String getRohistnum() {
		return rohistnum;
	}

	public void setRohistnum(String rohistnum) {
		this.rohistnum = rohistnum;
	}
	
	@Length(min=1, max=50, message="宿主站站名长度必须介于 1 和 50 之间")
	@ExcelField(title="宿主站站名", align=2, sort=42)
	public String getRohistname() {
		return rohistname;
	}

	public void setRohistname(String rohistname) {
		this.rohistname = rohistname;
	}
	
	@Length(min=0, max=30, message="电表户号长度必须介于 0 和 30 之间")
	@ExcelField(title="电表户号", align=2, sort=43)
	public String getSinum() {
		return sinum;
	}

	public void setSinum(String sinum) {
		this.sinum = sinum;
	}
	
	@Length(min=0, max=20, message="票据类型长度必须介于 0 和 20 之间")
	@ExcelField(title="票据类型", align=2, sort=44)
	public String getSibilltype() {
		return sibilltype;
	}

	public void setSibilltype(String sibilltype) {
		this.sibilltype = sibilltype;
	}
	
	@Length(min=0, max=200, message="外市电信息备注长度必须介于 0 和 200 之间")
	@ExcelField(title="外市电信息备注", align=2, sort=45)
	public String getOutcityeleremarks() {
		return outcityeleremarks;
	}

	public void setOutcityeleremarks(String outcityeleremarks) {
		this.outcityeleremarks = outcityeleremarks;
	}
	
	@ExcelField(title="单站移动", align=2, sort=46)
	public Double getSinglestatmq() {
		return singlestatmq;
	}

	public void setSinglestatmq(Double singlestatmq) {
		this.singlestatmq = singlestatmq;
	}
	
	@ExcelField(title="单站电信", align=2, sort=47)
	public Double getSinglestattq() {
		return singlestattq;
	}

	public void setSinglestattq(Double singlestattq) {
		this.singlestattq = singlestattq;
	}
	
	@ExcelField(title="单站联通", align=2, sort=48)
	public Double getSinglestatuq() {
		return singlestatuq;
	}

	public void setSinglestatuq(Double singlestatuq) {
		this.singlestatuq = singlestatuq;
	}
	
	@ExcelField(title="单站总电流", align=2, sort=49)
	public Double getSingletotal() {
		return singletotal;
	}

	public void setSingletotal(Double singletotal) {
		this.singletotal = singletotal;
	}
	
	@ExcelField(title="共表移动", align=2, sort=50)
	public Double getSharedmetermq() {
		return sharedmetermq;
	}

	public void setSharedmetermq(Double sharedmetermq) {
		this.sharedmetermq = sharedmetermq;
	}
	
	@ExcelField(title="共表电信", align=2, sort=51)
	public Double getSharedmetertq() {
		return sharedmetertq;
	}

	public void setSharedmetertq(Double sharedmetertq) {
		this.sharedmetertq = sharedmetertq;
	}
	
	@ExcelField(title="共表联通", align=2, sort=52)
	public Double getSharedmeteruq() {
		return sharedmeteruq;
	}

	public void setSharedmeteruq(Double sharedmeteruq) {
		this.sharedmeteruq = sharedmeteruq;
	}
	
	@ExcelField(title="共表总电流", align=2, sort=53)
	public Double getSharedmetertotal() {
		return sharedmetertotal;
	}

	public void setSharedmetertotal(Double sharedmetertotal) {
		this.sharedmetertotal = sharedmetertotal;
	}
	
	@Length(min=0, max=20, message="一表多站信息长度必须介于 0 和 20 之间")
	@ExcelField(title="一表多站信息", align=2, sort=54)
	public String getSingletomanystatinfo() {
		return singletomanystatinfo;
	}

	public void setSingletomanystatinfo(String singletomanystatinfo) {
		this.singletomanystatinfo = singletomanystatinfo;
	}
	
	@Length(min=0, max=20, message="偷电搭电信息长度必须介于 0 和 20 之间")
	@ExcelField(title="偷电搭电信息", align=2, sort=55)
	public String getStealeleinfo() {
		return stealeleinfo;
	}

	public void setStealeleinfo(String stealeleinfo) {
		this.stealeleinfo = stealeleinfo;
	}
	
	@Length(min=0, max=20, message="其他信息长度必须介于 0 和 20 之间")
	@ExcelField(title="其他信息", align=2, sort=56)
	public String getOtherinfo() {
		return otherinfo;
	}

	public void setOtherinfo(String otherinfo) {
		this.otherinfo = otherinfo;
	}
	
}