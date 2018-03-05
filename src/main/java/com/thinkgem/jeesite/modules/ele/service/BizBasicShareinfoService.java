package com.thinkgem.jeesite.modules.ele.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ele.dao.BizBasicShareinfoDao;
import com.thinkgem.jeesite.modules.ele.entity.BizBasicShareinfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 基础电流分摊信息Service
 * @author ws
 * @version 2018-02-09
 */
@Service
@Transactional(readOnly = true)
public class BizBasicShareinfoService extends CrudService<BizBasicShareinfoDao, BizBasicShareinfo> {

	public BizBasicShareinfo get(String id) {
		return super.get(id);
	}
	
	public List<BizBasicShareinfo> findList(BizBasicShareinfo bizBasicShareinfo) {
		return super.findList(bizBasicShareinfo);
	}
	
	public Page<BizBasicShareinfo> findPage(Page<BizBasicShareinfo> page, BizBasicShareinfo bizBasicShareinfo) {
		return super.findPage(page, bizBasicShareinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(BizBasicShareinfo bizBasicShareinfo) {
		super.save(bizBasicShareinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizBasicShareinfo bizBasicShareinfo) {
		super.delete(bizBasicShareinfo);
	}

	@Transactional(readOnly = false)
	public void reCalcData(BizBasicShareinfo obj) {
		BizBasicShareinfo basicShareinfo = new BizBasicShareinfo();
		basicShareinfo.setRohistnum(obj.getRohistnum());
		List<BizBasicShareinfo> list = super.findList(basicShareinfo);
		for (BizBasicShareinfo bizBasicShareinfo : list) {
			double meterMQ = 0;
			double meterTQ = 0;
			double meterUQ = 0;
			for (BizBasicShareinfo shareinfo : list) {
				if (shareinfo.getRohistnum().equals(bizBasicShareinfo.getRohistnum())) {
					meterMQ += null != shareinfo.getSinglestatmq() ? shareinfo.getSinglestatmq() : 0;
					meterTQ += null != shareinfo.getSinglestattq() ? shareinfo.getSinglestattq() : 0;
					meterUQ += null != shareinfo.getSinglestatuq() ? shareinfo.getSinglestatuq() : 0;
				}
			}
			//共表移动
			bizBasicShareinfo.setSharedmetermq(meterMQ);
			//共表电信
			bizBasicShareinfo.setSharedmetertq(meterTQ);
			//共表联通
			bizBasicShareinfo.setSharedmeteruq(meterUQ);
			//单站总电流
			double singleTotal = bizBasicShareinfo.getSinglestatmq() + bizBasicShareinfo.getSinglestattq() + bizBasicShareinfo.getSinglestatuq();
			bizBasicShareinfo.setSingletotal(singleTotal);
			//共表总电流
			double shareTotal = bizBasicShareinfo.getSharedmetermq() + bizBasicShareinfo.getSharedmetertq() + bizBasicShareinfo.getSharedmeteruq();
			bizBasicShareinfo.setSharedmetertotal(shareTotal);
			//一表多站信息
			bizBasicShareinfo.setSingletomanystatinfo(singleTotal == shareTotal ? "否" : "是");
			super.save(bizBasicShareinfo);
		}
	}
	
}