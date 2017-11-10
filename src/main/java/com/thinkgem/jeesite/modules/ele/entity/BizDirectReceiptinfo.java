package com.thinkgem.jeesite.modules.ele.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 直供回款信息Entity
 * @author ws
 * @version 2017-11-09
 */
public class BizDirectReceiptinfo extends DataEntity<BizDirectReceiptinfo> {
	
	private static final long serialVersionUID = 1L;
	private String scaccnum;		// 用电户号
	private String scoperator;		// 运营商
	private String scsitenum;		// 站点编码
	private Date sprecdate;		// 回款日期
	private String scbilldate;		// 账期
	private Date scpredate;		// 上期日期
	private Date sccurdate;		// 本期日期
	private Double scpredisplay;		// 上期示度
	private Double sccurdisplay;		// 本期示度
	private Double scprestartdisplay;		// 老表起始读数
	private Double scpreenddisplay;		// 老表截止读数
	private String scrate;		// 倍率
	private Double scloss;		// 损耗
	private Double sctotalq;		// 计费电量
	private Double scprice;		// 电费单价
	private Double scparc;		// 票面金额
	private String scpartype;		// 票据类型
	private String scpartypet;		// 铁塔开票类型
	private Double sccmq;		// 移动电流
	private Double scctq;		// 电信电流
	private Double sccuq;		// 联通电流
	private Double sctotalc;		// 分摊电费总额
	private Double scpue;		// PUE
	private Double sccheck;		// 核对金额
	private Double scc;		// 回款金额
	private String scapportchange;		// 本月分摊比例是否发生变动
	private Date spttstartdate;		// 铁塔起始日期
	private Date spttenddate;		// 铁塔截止日期
	private String scremarks;		// 备注
	private Date beginSprecdate;		// 开始 回款日期
	private Date endSprecdate;		// 结束 回款日期
	
	public BizDirectReceiptinfo() {
		super();
	}

	public BizDirectReceiptinfo(String id){
		super(id);
	}

	@Length(min=1, max=15, message="用电户号长度必须介于 1 和 15 之间")
	@ExcelField(title="用电户号", align=2, sort=10)
	public String getScaccnum() {
		return scaccnum;
	}

	public void setScaccnum(String scaccnum) {
		this.scaccnum = scaccnum;
	}
	
	@Length(min=1, max=6, message="运营商长度必须介于 1 和 6 之间")
	@ExcelField(title="运营商", align=2, sort=10)
	public String getScoperator() {
		return scoperator;
	}

	public void setScoperator(String scoperator) {
		this.scoperator = scoperator;
	}
	
	@Length(min=1, max=30, message="站点编码长度必须介于 1 和 30 之间")
	@ExcelField(title="站点编码", align=2, sort=10)
	public String getScsitenum() {
		return scsitenum;
	}

	public void setScsitenum(String scsitenum) {
		this.scsitenum = scsitenum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="回款日期不能为空")
	@ExcelField(title="回款日期", align=2, sort=10)
	public Date getSprecdate() {
		return sprecdate;
	}

	public void setSprecdate(Date sprecdate) {
		this.sprecdate = sprecdate;
	}
	
	@Length(min=1, max=20, message="账期长度必须介于 1 和 20 之间")
	@ExcelField(title="账期", align=2, sort=10)
	public String getScbilldate() {
		return scbilldate;
	}

	public void setScbilldate(String scbilldate) {
		this.scbilldate = scbilldate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="上期日期不能为空")
	@ExcelField(title="上期日期", align=2, sort=10)
	public Date getScpredate() {
		return scpredate;
	}

	public void setScpredate(Date scpredate) {
		this.scpredate = scpredate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="本期日期不能为空")
	@ExcelField(title="本期日期", align=2, sort=10)
	public Date getSccurdate() {
		return sccurdate;
	}

	public void setSccurdate(Date sccurdate) {
		this.sccurdate = sccurdate;
	}
	
	@NotNull(message="上期示度不能为空")
	@ExcelField(title="上期示度", align=2, sort=10)
	public Double getScpredisplay() {
		return scpredisplay;
	}

	public void setScpredisplay(Double scpredisplay) {
		this.scpredisplay = scpredisplay;
	}
	
	@NotNull(message="本期示度不能为空")
	@ExcelField(title="本期示度", align=2, sort=10)
	public Double getSccurdisplay() {
		return sccurdisplay;
	}

	public void setSccurdisplay(Double sccurdisplay) {
		this.sccurdisplay = sccurdisplay;
	}
	
	@NotNull(message="老表起始读数不能为空")
	@ExcelField(title="老表起始读数", align=2, sort=10)
	public Double getScprestartdisplay() {
		return scprestartdisplay;
	}

	public void setScprestartdisplay(Double scprestartdisplay) {
		this.scprestartdisplay = scprestartdisplay;
	}
	
	@NotNull(message="老表截止读数不能为空")
	@ExcelField(title="老表截止读数", align=2, sort=10)
	public Double getScpreenddisplay() {
		return scpreenddisplay;
	}

	public void setScpreenddisplay(Double scpreenddisplay) {
		this.scpreenddisplay = scpreenddisplay;
	}
	
	@Length(min=1, max=20, message="倍率长度必须介于 1 和 20 之间")
	@ExcelField(title="倍率", align=2, sort=10)
	public String getScrate() {
		return scrate;
	}

	public void setScrate(String scrate) {
		this.scrate = scrate;
	}
	
	@NotNull(message="损耗不能为空")
	@ExcelField(title="损耗", align=2, sort=10)
	public Double getScloss() {
		return scloss;
	}

	public void setScloss(Double scloss) {
		this.scloss = scloss;
	}
	
	@ExcelField(title="计费电量", align=2, sort=10)
	public Double getSctotalq() {
		return sctotalq;
	}

	public void setSctotalq(Double sctotalq) {
		this.sctotalq = sctotalq;
	}
	
	@ExcelField(title="电费单价", align=2, sort=10)
	public Double getScprice() {
		return scprice;
	}

	public void setScprice(Double scprice) {
		this.scprice = scprice;
	}
	
	@NotNull(message="票面金额不能为空")
	@ExcelField(title="票面金额", align=2, sort=10)
	public Double getScparc() {
		return scparc;
	}

	public void setScparc(Double scparc) {
		this.scparc = scparc;
	}
	
	@Length(min=1, max=20, message="票据类型长度必须介于 1 和 20 之间")
	@ExcelField(title="票据类型", align=2, sort=10)
	public String getScpartype() {
		return scpartype;
	}

	public void setScpartype(String scpartype) {
		this.scpartype = scpartype;
	}
	
	@Length(min=1, max=20, message="铁塔开票类型长度必须介于 1 和 20 之间")
	@ExcelField(title="铁塔开票类型", align=2, sort=10)
	public String getScpartypet() {
		return scpartypet;
	}

	public void setScpartypet(String scpartypet) {
		this.scpartypet = scpartypet;
	}
	
	@NotNull(message="移动电流不能为空")
	@ExcelField(title="移动电流", align=2, sort=10)
	public Double getSccmq() {
		return sccmq;
	}

	public void setSccmq(Double sccmq) {
		this.sccmq = sccmq;
	}
	
	@NotNull(message="电信电流不能为空")
	@ExcelField(title="电信电流", align=2, sort=10)
	public Double getScctq() {
		return scctq;
	}

	public void setScctq(Double scctq) {
		this.scctq = scctq;
	}
	
	@NotNull(message="联通电流不能为空")
	@ExcelField(title="联通电流", align=2, sort=10)
	public Double getSccuq() {
		return sccuq;
	}

	public void setSccuq(Double sccuq) {
		this.sccuq = sccuq;
	}
	
	@NotNull(message="分摊电费总额不能为空")
	@ExcelField(title="分摊电费总额", align=2, sort=10)
	public Double getSctotalc() {
		return sctotalc;
	}

	public void setSctotalc(Double sctotalc) {
		this.sctotalc = sctotalc;
	}
	
	@ExcelField(title="PUE", align=2, sort=10)
	public Double getScpue() {
		return scpue;
	}

	public void setScpue(Double scpue) {
		this.scpue = scpue;
	}
	
	@ExcelField(title="核对金额", align=2, sort=10)
	public Double getSccheck() {
		return sccheck;
	}

	public void setSccheck(Double sccheck) {
		this.sccheck = sccheck;
	}
	
	@ExcelField(title="回款金额", align=2, sort=10)
	public Double getScc() {
		return scc;
	}

	public void setScc(Double scc) {
		this.scc = scc;
	}
	
	@Length(min=0, max=5, message="本月分摊比例是否发生变动长度必须介于 0 和 5 之间")
	@ExcelField(title="本月分摊比例是否发生变动", align=2, sort=10)
	public String getScapportchange() {
		return scapportchange;
	}

	public void setScapportchange(String scapportchange) {
		this.scapportchange = scapportchange;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="铁塔起始日期不能为空")
	@ExcelField(title="铁塔起始日期", align=2, sort=10)
	public Date getSpttstartdate() {
		return spttstartdate;
	}

	public void setSpttstartdate(Date spttstartdate) {
		this.spttstartdate = spttstartdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="铁塔截止日期不能为空")
	@ExcelField(title="铁塔截止日期", align=2, sort=10)
	public Date getSpttenddate() {
		return spttenddate;
	}

	public void setSpttenddate(Date spttenddate) {
		this.spttenddate = spttenddate;
	}
	
	@Length(min=0, max=100, message="备注长度必须介于 0 和 100 之间")
	@ExcelField(title="备注", align=2, sort=10)
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
		
}