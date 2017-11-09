package com.thinkgem.jeesite.modules.ele.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ele.entity.BizFsuMonitorinfo;
import com.thinkgem.jeesite.modules.ele.dao.BizFsuMonitorinfoDao;

/**
 * FSU监控信息Service
 * @author ws
 * @version 2017-11-08
 */
@Service
@Transactional(readOnly = true)
public class BizFsuMonitorinfoService extends CrudService<BizFsuMonitorinfoDao, BizFsuMonitorinfo> {

	public BizFsuMonitorinfo get(String id) {
		return super.get(id);
	}
	
	public List<BizFsuMonitorinfo> findList(BizFsuMonitorinfo bizFsuMonitorinfo) {
		return super.findList(bizFsuMonitorinfo);
	}
	
	public Page<BizFsuMonitorinfo> findPage(Page<BizFsuMonitorinfo> page, BizFsuMonitorinfo bizFsuMonitorinfo) {
		return super.findPage(page, bizFsuMonitorinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(BizFsuMonitorinfo bizFsuMonitorinfo) {
		super.save(bizFsuMonitorinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizFsuMonitorinfo bizFsuMonitorinfo) {
		super.delete(bizFsuMonitorinfo);
	}
	
}