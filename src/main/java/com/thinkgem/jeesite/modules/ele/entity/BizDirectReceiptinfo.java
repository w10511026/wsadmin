package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 直供回款信息Entity
 * @author ws
 * @version 2017-11-06
 */
public class BizDirectReceiptinfo extends DataEntity<BizDirectReceiptinfo> {
	
	private static final long serialVersionUID = 1L;
	private String scoperator;		// 运营商
	private String scaccnum;		// 用电户号
	private String scsitenum;		// 站点编码
	private Date sprecdate;		// 回款日期
	private Date scbilldate;		// 票据日期
	private Date scpredate;		// 上期日期
	private Date sccurdate;		// 本期日期
	private String scpredisplay;		// 上期示度
	private String sccurdisplay;		// 本期示度
	private String scprestartdisplay;		// 老表起始读数
	private String scpreenddisplay;		// 老表截止读数
	private String scrate;		// 倍率
	private String scloss;		// 损耗
	private String sctotalq;		// 计费电量
	private String scprice;		// 电费单价
	private String scparc;		// 票面金额
	private String scpartype;		// 票据类型
	private String scpartypet;		// 铁塔开票类型
	private String sccmq;		// 移动电流
	private String scctq;		// 电信电流
	private String sccuq;		// 联通电流
	private String sctotalc;		// 分摊电费总额
	private String scpue;		// PUE
	private String sccheck;		// 核对金额
	private String scc;		// 回款金额
	private String scapportchange;		// 本月分摊比例是否发生变动
	private String scremarks;		// 备注
	private Date beginSprecdate;		// 开始 回款日期
	private Date endSprecdate;		// 结束 回款日期
	private Date beginScbilldate;		// 开始 票据日期
	private Date endScbilldate;		// 结束 票据日期
	
	public BizDirectReceiptinfo() {
		super();
	}

	public BizDirectReceiptinfo(String id){
		super(id);
	}

	@Length(min=1, max=6, message="运营商长度必须介于 1 和 6 之间")
	public String getScoperator() {
		return scoperator;
	}

	public void setScoperator(String scoperator) {
		this.scoperator = scoperator;
	}
	
	@Length(min=1, max=15, message="用电户号长度必须介于 1 和 15 之间")
	public String getScaccnum() {
		return scaccnum;
	}

	public void setScaccnum(String scaccnum) {
		this.scaccnum = scaccnum;
	}
	
	@Length(min=1, max=30, message="站点编码长度必须介于 1 和 30 之间")
	public String getScsitenum() {
		return scsitenum;
	}

	public void setScsitenum(String scsitenum) {
		this.scsitenum = scsitenum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="回款日期不能为空")
	public Date getSprecdate() {
		return sprecdate;
	}

	public void setSprecdate(Date sprecdate) {
		this.sprecdate = sprecdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="票据日期不能为空")
	public Date getScbilldate() {
		return scbilldate;
	}

	public void setScbilldate(Date scbilldate) {
		this.scbilldate = scbilldate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="上期日期不能为空")
	public Date getScpredate() {
		return scpredate;
	}

	public void setScpredate(Date scpredate) {
		this.scpredate = scpredate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="本期日期不能为空")
	public Date getSccurdate() {
		return sccurdate;
	}

	public void setSccurdate(Date sccurdate) {
		this.sccurdate = sccurdate;
	}
	
	public String getScpredisplay() {
		return scpredisplay;
	}

	public void setScpredisplay(String scpredisplay) {
		this.scpredisplay = scpredisplay;
	}
	
	public String getSccurdisplay() {
		return sccurdisplay;
	}

	public void setSccurdisplay(String sccurdisplay) {
		this.sccurdisplay = sccurdisplay;
	}
	
	public String getScprestartdisplay() {
		return scprestartdisplay;
	}

	public void setScprestartdisplay(String scprestartdisplay) {
		this.scprestartdisplay = scprestartdisplay;
	}
	
	public String getScpreenddisplay() {
		return scpreenddisplay;
	}

	public void setScpreenddisplay(String scpreenddisplay) {
		this.scpreenddisplay = scpreenddisplay;
	}
	
	@Length(min=1, max=20, message="倍率长度必须介于 1 和 20 之间")
	public String getScrate() {
		return scrate;
	}

	public void setScrate(String scrate) {
		this.scrate = scrate;
	}
	
	public String getScloss() {
		return scloss;
	}

	public void setScloss(String scloss) {
		this.scloss = scloss;
	}
	
	public String getSctotalq() {
		return sctotalq;
	}

	public void setSctotalq(String sctotalq) {
		this.sctotalq = sctotalq;
	}
	
	public String getScprice() {
		return scprice;
	}

	public void setScprice(String scprice) {
		this.scprice = scprice;
	}
	
	public String getScparc() {
		return scparc;
	}

	public void setScparc(String scparc) {
		this.scparc = scparc;
	}
	
	@Length(min=1, max=20, message="票据类型长度必须介于 1 和 20 之间")
	public String getScpartype() {
		return scpartype;
	}

	public void setScpartype(String scpartype) {
		this.scpartype = scpartype;
	}
	
	@Length(min=1, max=20, message="铁塔开票类型长度必须介于 1 和 20 之间")
	public String getScpartypet() {
		return scpartypet;
	}

	public void setScpartypet(String scpartypet) {
		this.scpartypet = scpartypet;
	}
	
	public String getSccmq() {
		return sccmq;
	}

	public void setSccmq(String sccmq) {
		this.sccmq = sccmq;
	}
	
	public String getScctq() {
		return scctq;
	}

	public void setScctq(String scctq) {
		this.scctq = scctq;
	}
	
	public String getSccuq() {
		return sccuq;
	}

	public void setSccuq(String sccuq) {
		this.sccuq = sccuq;
	}
	
	public String getSctotalc() {
		return sctotalc;
	}

	public void setSctotalc(String sctotalc) {
		this.sctotalc = sctotalc;
	}
	
	public String getScpue() {
		return scpue;
	}

	public void setScpue(String scpue) {
		this.scpue = scpue;
	}
	
	public String getSccheck() {
		return sccheck;
	}

	public void setSccheck(String sccheck) {
		this.sccheck = sccheck;
	}
	
	public String getScc() {
		return scc;
	}

	public void setScc(String scc) {
		this.scc = scc;
	}
	
	@Length(min=0, max=5, message="本月分摊比例是否发生变动长度必须介于 0 和 5 之间")
	public String getScapportchange() {
		return scapportchange;
	}

	public void setScapportchange(String scapportchange) {
		this.scapportchange = scapportchange;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	public String getScremarks() {
		return scremarks;
	}

	public void setScremarks(String scremarks) {
		this.scremarks = scremarks;
	}
	
	public Date getBeginSprecdate() {
		return beginSprecdate;
	}

	public void setBeginSprecdate(Date beginSprecdate) {
		this.beginSprecdate = beginSprecdate;
	}
	
	public Date getEndSprecdate() {
		return endSprecdate;
	}

	public void setEndSprecdate(Date endSprecdate) {
		this.endSprecdate = endSprecdate;
	}
		
	public Date getBeginScbilldate() {
		return beginScbilldate;
	}

	public void setBeginScbilldate(Date beginScbilldate) {
		this.beginScbilldate = beginScbilldate;
	}
	
	public Date getEndScbilldate() {
		return endScbilldate;
	}

	public void setEndScbilldate(Date endScbilldate) {
		this.endScbilldate = endScbilldate;
	}
		
}