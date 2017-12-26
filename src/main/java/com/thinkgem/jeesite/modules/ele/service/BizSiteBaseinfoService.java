package com.thinkgem.jeesite.modules.ele.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ele.entity.BizSiteBaseinfo;
import com.thinkgem.jeesite.modules.ele.dao.BizSiteBaseinfoDao;

/**
 * 站址基础信息Service
 * @author ws
 * @version 2017-12-26
 */
@Service
@Transactional(readOnly = true)
public class BizSiteBaseinfoService extends CrudService<BizSiteBaseinfoDao, BizSiteBaseinfo> {

	public BizSiteBaseinfo get(String id) {
		return super.get(id);
	}
	
	public List<BizSiteBaseinfo> findList(BizSiteBaseinfo bizSiteBaseinfo) {
		return super.findList(bizSiteBaseinfo);
	}
	
	public Page<BizSiteBaseinfo> findPage(Page<BizSiteBaseinfo> page, BizSiteBaseinfo bizSiteBaseinfo) {
		return super.findPage(page, bizSiteBaseinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(BizSiteBaseinfo bizSiteBaseinfo) {
		super.save(bizSiteBaseinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizSiteBaseinfo bizSiteBaseinfo) {
		super.delete(bizSiteBaseinfo);
	}
	
}