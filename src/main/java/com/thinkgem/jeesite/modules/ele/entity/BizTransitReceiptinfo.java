package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 转供回款信息Entity
 * @author ws
 * @version 2017-11-09
 */
public class BizTransitReceiptinfo extends DataEntity<BizTransitReceiptinfo> {
	
	private static final long serialVersionUID = 1L;
	private String tcaccnum;		// 用电户号
	private String tcoperator;		// 运营商
	private String tcsitenum;		// 站点编码
	private Date tcpaydate;		// 缴费日期
	private Date tcrecdate;		// 回款日期
	private Date tcpredate;		// 上期日期
	private Date tccurdate;		// 本期日期
	private Double tcpredisplay;		// 起始读数
	private Double tccurdisplay;		// 截止读数
	private Double tcprestartdisplay;		// 老表起始读数
	private Double tcpreenddisplay;		// 老表截止读数
	private String tcrate;		// 倍率
	private Double tcloss;		// 损耗
	private Double tctotalq;		// 计费电量
	private Double tcpriceq;		// 电费单价
	private Double tcparc;		// 票面金额
	private String tcpartype;		// 票据类型
	private String tcpartypet;		// 铁塔开票类型
	private Double tctaxfactor;		// 税负因子
	private Double tccmq;		// 移动电流
	private Double tcctq;		// 电信电流
	private Double tccuq;		// 联通电流
	private Double tctotalc;		// 分摊电费总额
	private Double tcpue;		// PUE
	private Double tccheck;		// 核对金额
	private Double tcc;		// 回款金额
	private String tcapportchange;		// 本月分摊比例是否发生变动
	private String tcremarks;		// 备注
	private Date beginTcpaydate;		// 开始 缴费日期
	private Date endTcpaydate;		// 结束 缴费日期
	private Date beginTcrecdate;		// 开始 回款日期
	private Date endTcrecdate;		// 结束 回款日期
	
	public BizTransitReceiptinfo() {
		super();
	}

	public BizTransitReceiptinfo(String id){
		super(id);
	}

	@Length(min=1, max=15, message="用电户号长度必须介于 1 和 15 之间")
	@ExcelField(title="用电户号", align=2, sort=10)
	public String getTcaccnum() {
		return tcaccnum;
	}

	public void setTcaccnum(String tcaccnum) {
		this.tcaccnum = tcaccnum;
	}
	
	@Length(min=1, max=20, message="运营商长度必须介于 1 和 20 之间")
	@ExcelField(title="运营商", align=2, sort=10)
	public String getTcoperator() {
		return tcoperator;
	}

	public void setTcoperator(String tcoperator) {
		this.tcoperator = tcoperator;
	}
	
	@Length(min=1, max=30, message="站点编码长度必须介于 1 和 30 之间")
	@ExcelField(title="站点编码", align=2, sort=10)
	public String getTcsitenum() {
		return tcsitenum;
	}

	public void setTcsitenum(String tcsitenum) {
		this.tcsitenum = tcsitenum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="缴费日期不能为空")
	@ExcelField(title="缴费日期", align=2, sort=10)
	public Date getTcpaydate() {
		return tcpaydate;
	}

	public void setTcpaydate(Date tcpaydate) {
		this.tcpaydate = tcpaydate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="回款日期不能为空")
	@ExcelField(title="回款日期", align=2, sort=10)
	public Date getTcrecdate() {
		return tcrecdate;
	}

	public void setTcrecdate(Date tcrecdate) {
		this.tcrecdate = tcrecdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="上期日期不能为空")
	@ExcelField(title="上期日期", align=2, sort=10)
	public Date getTcpredate() {
		return tcpredate;
	}

	public void setTcpredate(Date tcpredate) {
		this.tcpredate = tcpredate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="本期日期不能为空")
	@ExcelField(title="本期日期", align=2, sort=10)
	public Date getTccurdate() {
		return tccurdate;
	}

	public void setTccurdate(Date tccurdate) {
		this.tccurdate = tccurdate;
	}
	
	@NotNull(message="起始读数不能为空")
	@ExcelField(title="起始读数", align=2, sort=10)
	public Double getTcpredisplay() {
		return tcpredisplay;
	}

	public void setTcpredisplay(Double tcpredisplay) {
		this.tcpredisplay = tcpredisplay;
	}
	
	@NotNull(message="截止读数不能为空")
	@ExcelField(title="截止读数", align=2, sort=10)
	public Double getTccurdisplay() {
		return tccurdisplay;
	}

	public void setTccurdisplay(Double tccurdisplay) {
		this.tccurdisplay = tccurdisplay;
	}
	
	@NotNull(message="老表起始读数不能为空")
	@ExcelField(title="老表起始读数", align=2, sort=10)
	public Double getTcprestartdisplay() {
		return tcprestartdisplay;
	}

	public void setTcprestartdisplay(Double tcprestartdisplay) {
		this.tcprestartdisplay = tcprestartdisplay;
	}
	
	@NotNull(message="老表截止读数不能为空")
	@ExcelField(title="老表截止读数", align=2, sort=10)
	public Double getTcpreenddisplay() {
		return tcpreenddisplay;
	}

	public void setTcpreenddisplay(Double tcpreenddisplay) {
		this.tcpreenddisplay = tcpreenddisplay;
	}
	
	@Length(min=1, max=20, message="倍率长度必须介于 1 和 20 之间")
	@ExcelField(title="倍率", align=2, sort=10)
	public String getTcrate() {
		return tcrate;
	}

	public void setTcrate(String tcrate) {
		this.tcrate = tcrate;
	}
	
	@NotNull(message="损耗不能为空")
	@ExcelField(title="损耗", align=2, sort=10)
	public Double getTcloss() {
		return tcloss;
	}

	public void setTcloss(Double tcloss) {
		this.tcloss = tcloss;
	}
	
	@ExcelField(title="计费电量", align=2, sort=10)
	public Double getTctotalq() {
		return tctotalq;
	}

	public void setTctotalq(Double tctotalq) {
		this.tctotalq = tctotalq;
	}
	
	@ExcelField(title="电费单价", align=2, sort=10)
	public Double getTcpriceq() {
		return tcpriceq;
	}

	public void setTcpriceq(Double tcpriceq) {
		this.tcpriceq = tcpriceq;
	}
	
	@NotNull(message="票面金额不能为空")
	@ExcelField(title="票面金额", align=2, sort=10)
	public Double getTcparc() {
		return tcparc;
	}

	public void setTcparc(Double tcparc) {
		this.tcparc = tcparc;
	}
	
	@Length(min=1, max=20, message="票据类型长度必须介于 1 和 20 之间")
	@ExcelField(title="票据类型", align=2, sort=10)
	public String getTcpartype() {
		return tcpartype;
	}

	public void setTcpartype(String tcpartype) {
		this.tcpartype = tcpartype;
	}
	
	@Length(min=1, max=20, message="铁塔开票类型长度必须介于 1 和 20 之间")
	@ExcelField(title="铁塔开票类型", align=2, sort=10)
	public String getTcpartypet() {
		return tcpartypet;
	}

	public void setTcpartypet(String tcpartypet) {
		this.tcpartypet = tcpartypet;
	}
	
	@NotNull(message="税负因子不能为空")
	@ExcelField(title="税负因子", align=2, sort=10)
	public Double getTctaxfactor() {
		return tctaxfactor;
	}

	public void setTctaxfactor(Double tctaxfactor) {
		this.tctaxfactor = tctaxfactor;
	}
	
	@NotNull(message="移动电流不能为空")
	@ExcelField(title="移动电流", align=2, sort=10)
	public Double getTccmq() {
		return tccmq;
	}

	public void setTccmq(Double tccmq) {
		this.tccmq = tccmq;
	}
	
	@NotNull(message="电信电流不能为空")
	@ExcelField(title="电信电流", align=2, sort=10)
	public Double getTcctq() {
		return tcctq;
	}

	public void setTcctq(Double tcctq) {
		this.tcctq = tcctq;
	}
	
	@ExcelField(title="联通电流", align=2, sort=10)
	public Double getTccuq() {
		return tccuq;
	}

	public void setTccuq(Double tccuq) {
		this.tccuq = tccuq;
	}
	
	@NotNull(message="分摊电费总额不能为空")
	@ExcelField(title="分摊电费总额", align=2, sort=10)
	public Double getTctotalc() {
		return tctotalc;
	}

	public void setTctotalc(Double tctotalc) {
		this.tctotalc = tctotalc;
	}
	
	@NotNull(message="PUE不能为空")
	@ExcelField(title="PUE", align=2, sort=10)
	public Double getTcpue() {
		return tcpue;
	}

	public void setTcpue(Double tcpue) {
		this.tcpue = tcpue;
	}
	
	@NotNull(message="核对金额不能为空")
	@ExcelField(title="核对金额", align=2, sort=10)
	public Double getTccheck() {
		return tccheck;
	}

	public void setTccheck(Double tccheck) {
		this.tccheck = tccheck;
	}
	
	@NotNull(message="回款金额不能为空")
	@ExcelField(title="回款金额", align=2, sort=10)
	public Double getTcc() {
		return tcc;
	}

	public void setTcc(Double tcc) {
		this.tcc = tcc;
	}
	
	@Length(min=0, max=1, message="本月分摊比例是否发生变动长度必须介于 0 和 1 之间")
	@ExcelField(title="本月分摊比例是否发生变动", align=2, sort=10)
	public String getTcapportchange() {
		return tcapportchange;
	}

	public void setTcapportchange(String tcapportchange) {
		this.tcapportchange = tcapportchange;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	@ExcelField(title="备注", align=2, sort=10)
	public String getTcremarks() {
		return tcremarks;
	}

	public void setTcremarks(String tcremarks) {
		this.tcremarks = tcremarks;
	}
	
	public Date getBeginTcpaydate() {
		return beginTcpaydate;
	}

	public void setBeginTcpaydate(Date beginTcpaydate) {
		this.beginTcpaydate = beginTcpaydate;
	}
	
	public Date getEndTcpaydate() {
		return endTcpaydate;
	}

	public void setEndTcpaydate(Date endTcpaydate) {
		this.endTcpaydate = endTcpaydate;
	}
		
	public Date getBeginTcrecdate() {
		return beginTcrecdate;
	}

	public void setBeginTcrecdate(Date beginTcrecdate) {
		this.beginTcrecdate = beginTcrecdate;
	}
	
	public Date getEndTcrecdate() {
		return endTcrecdate;
	}

	public void setEndTcrecdate(Date endTcrecdate) {
		this.endTcrecdate = endTcrecdate;
	}
		
}