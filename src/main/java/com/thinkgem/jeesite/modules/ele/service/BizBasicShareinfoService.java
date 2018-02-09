package com.thinkgem.jeesite.modules.ele.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ele.entity.BizBasicShareinfo;
import com.thinkgem.jeesite.modules.ele.dao.BizBasicShareinfoDao;

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
	
}