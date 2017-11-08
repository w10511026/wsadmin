package com.thinkgem.jeesite.modules.ele.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ele.entity.BizTransitPayinfo;
import com.thinkgem.jeesite.modules.ele.dao.BizTransitPayinfoDao;

/**
 * 转供缴费信息Service
 * @author ws
 * @version 2017-11-06
 */
@Service
@Transactional(readOnly = true)
public class BizTransitPayinfoService extends CrudService<BizTransitPayinfoDao, BizTransitPayinfo> {

	public BizTransitPayinfo get(String id) {
		return super.get(id);
	}
	
	public List<BizTransitPayinfo> findList(BizTransitPayinfo bizTransitPayinfo) {
		return super.findList(bizTransitPayinfo);
	}
	
	public Page<BizTransitPayinfo> findPage(Page<BizTransitPayinfo> page, BizTransitPayinfo bizTransitPayinfo) {
		return super.findPage(page, bizTransitPayinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(BizTransitPayinfo bizTransitPayinfo) {
		super.save(bizTransitPayinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizTransitPayinfo bizTransitPayinfo) {
		super.delete(bizTransitPayinfo);
	}
	
}