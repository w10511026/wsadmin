package com.thinkgem.jeesite.modules.ele.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ele.entity.BizSiteMeterinfo;
import com.thinkgem.jeesite.modules.ele.dao.BizSiteMeterinfoDao;

/**
 * 表站对应信息Service
 * @author ws
 * @version 2017-11-06
 */
@Service
@Transactional(readOnly = true)
public class BizSiteMeterinfoService extends CrudService<BizSiteMeterinfoDao, BizSiteMeterinfo> {

	public BizSiteMeterinfo get(String id) {
		return super.get(id);
	}
	
	public List<BizSiteMeterinfo> findList(BizSiteMeterinfo bizSiteMeterinfo) {
		return super.findList(bizSiteMeterinfo);
	}
	
	public Page<BizSiteMeterinfo> findPage(Page<BizSiteMeterinfo> page, BizSiteMeterinfo bizSiteMeterinfo) {
		return super.findPage(page, bizSiteMeterinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(BizSiteMeterinfo bizSiteMeterinfo) {
		super.save(bizSiteMeterinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizSiteMeterinfo bizSiteMeterinfo) {
		super.delete(bizSiteMeterinfo);
	}
	
}