package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 转供回款信息Entity
 * @author ws
 * @version 2017-11-06
 */
public class BizTransitReceiptinfo extends DataEntity<BizTransitReceiptinfo> {
	
	private static final long serialVersionUID = 1L;
	private String tcoperator;		// 运营商
	private String tcaccnum;		// 用电户号
	private String tcsitenum;		// 站点编码
	private Date tcpaydate;		// 缴费日期
	private Date tcpredate;		// 上期日期
	private Date tccurdate;		// 本期日期
	private String tcpredisplay;		// 起始读数
	private String tccurdisplay;		// 截止读数
	private String tcprestartdisplay;		// 老表起始读数
	private String tcpreenddisplay;		// 老表截止读数
	private String tcrate;		// 倍率
	private String tcloss;		// 损耗
	private String tctotalq;		// 计费电量
	private String tcpriceq;		// 电费单价
	private String tcparc;		// 票面金额
	private String tcpartype;		// 票据类型
	private String tcpartypet;		// 铁塔开票类型
	private String tctaxfactor;		// 税负因子
	private String tccmq;		// 移动电流
	private String tcctq;		// 电信电流
	private String tccuq;		// 联通电流
	private String tctotalc;		// 分摊电费总额
	private String tcpue;		// PUE
	private String tccheck;		// 核对金额
	private String tcc;		// 回款金额
	private String tcapportchange;		// 本月分摊比例是否发生变动
	private String tcremarks;		// 备注
	
	public BizTransitReceiptinfo() {
		super();
	}

	public BizTransitReceiptinfo(String id){
		super(id);
	}

	@Length(min=1, max=20, message="运营商长度必须介于 1 和 20 之间")
	public String getTcoperator() {
		return tcoperator;
	}

	public void setTcoperator(String tcoperator) {
		this.tcoperator = tcoperator;
	}
	
	@Length(min=1, max=15, message="用电户号长度必须介于 1 和 15 之间")
	public String getTcaccnum() {
		return tcaccnum;
	}

	public void setTcaccnum(String tcaccnum) {
		this.tcaccnum = tcaccnum;
	}
	
	@Length(min=1, max=30, message="站点编码长度必须介于 1 和 30 之间")
	public String getTcsitenum() {
		return tcsitenum;
	}

	public void setTcsitenum(String tcsitenum) {
		this.tcsitenum = tcsitenum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="缴费日期不能为空")
	public Date getTcpaydate() {
		return tcpaydate;
	}

	public void setTcpaydate(Date tcpaydate) {
		this.tcpaydate = tcpaydate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="上期日期不能为空")
	public Date getTcpredate() {
		return tcpredate;
	}

	public void setTcpredate(Date tcpredate) {
		this.tcpredate = tcpredate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="本期日期不能为空")
	public Date getTccurdate() {
		return tccurdate;
	}

	public void setTccurdate(Date tccurdate) {
		this.tccurdate = tccurdate;
	}
	
	public String getTcpredisplay() {
		return tcpredisplay;
	}

	public void setTcpredisplay(String tcpredisplay) {
		this.tcpredisplay = tcpredisplay;
	}
	
	public String getTccurdisplay() {
		return tccurdisplay;
	}

	public void setTccurdisplay(String tccurdisplay) {
		this.tccurdisplay = tccurdisplay;
	}
	
	public String getTcprestartdisplay() {
		return tcprestartdisplay;
	}

	public void setTcprestartdisplay(String tcprestartdisplay) {
		this.tcprestartdisplay = tcprestartdisplay;
	}
	
	public String getTcpreenddisplay() {
		return tcpreenddisplay;
	}

	public void setTcpreenddisplay(String tcpreenddisplay) {
		this.tcpreenddisplay = tcpreenddisplay;
	}
	
	@Length(min=1, max=20, message="倍率长度必须介于 1 和 20 之间")
	public String getTcrate() {
		return tcrate;
	}

	public void setTcrate(String tcrate) {
		this.tcrate = tcrate;
	}
	
	public String getTcloss() {
		return tcloss;
	}

	public void setTcloss(String tcloss) {
		this.tcloss = tcloss;
	}
	
	public String getTctotalq() {
		return tctotalq;
	}

	public void setTctotalq(String tctotalq) {
		this.tctotalq = tctotalq;
	}
	
	public String getTcpriceq() {
		return tcpriceq;
	}

	public void setTcpriceq(String tcpriceq) {
		this.tcpriceq = tcpriceq;
	}
	
	public String getTcparc() {
		return tcparc;
	}

	public void setTcparc(String tcparc) {
		this.tcparc = tcparc;
	}
	
	@Length(min=1, max=20, message="票据类型长度必须介于 1 和 20 之间")
	public String getTcpartype() {
		return tcpartype;
	}

	public void setTcpartype(String tcpartype) {
		this.tcpartype = tcpartype;
	}
	
	@Length(min=1, max=20, message="铁塔开票类型长度必须介于 1 和 20 之间")
	public String getTcpartypet() {
		return tcpartypet;
	}

	public void setTcpartypet(String tcpartypet) {
		this.tcpartypet = tcpartypet;
	}
	
	public String getTctaxfactor() {
		return tctaxfactor;
	}

	public void setTctaxfactor(String tctaxfactor) {
		this.tctaxfactor = tctaxfactor;
	}
	
	public String getTccmq() {
		return tccmq;
	}

	public void setTccmq(String tccmq) {
		this.tccmq = tccmq;
	}
	
	public String getTcctq() {
		return tcctq;
	}

	public void setTcctq(String tcctq) {
		this.tcctq = tcctq;
	}
	
	public String getTccuq() {
		return tccuq;
	}

	public void setTccuq(String tccuq) {
		this.tccuq = tccuq;
	}
	
	public String getTctotalc() {
		return tctotalc;
	}

	public void setTctotalc(String tctotalc) {
		this.tctotalc = tctotalc;
	}
	
	public String getTcpue() {
		return tcpue;
	}

	public void setTcpue(String tcpue) {
		this.tcpue = tcpue;
	}
	
	public String getTccheck() {
		return tccheck;
	}

	public void setTccheck(String tccheck) {
		this.tccheck = tccheck;
	}
	
	public String getTcc() {
		return tcc;
	}

	public void setTcc(String tcc) {
		this.tcc = tcc;
	}
	
	@Length(min=0, max=1, message="本月分摊比例是否发生变动长度必须介于 0 和 1 之间")
	public String getTcapportchange() {
		return tcapportchange;
	}

	public void setTcapportchange(String tcapportchange) {
		this.tcapportchange = tcapportchange;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	public String getTcremarks() {
		return tcremarks;
	}

	public void setTcremarks(String tcremarks) {
		this.tcremarks = tcremarks;
	}
	
}