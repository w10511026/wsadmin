package com.thinkgem.jeesite.modules.ele.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ele.entity.BizDirectPayinfo;
import com.thinkgem.jeesite.modules.ele.dao.BizDirectPayinfoDao;

/**
 * 直供缴费信息Service
 * @author ws
 * @version 2017-11-09
 */
@Service
@Transactional(readOnly = true)
public class BizDirectPayinfoService extends CrudService<BizDirectPayinfoDao, BizDirectPayinfo> {

	public BizDirectPayinfo get(String id) {
		return super.get(id);
	}
	
	public List<BizDirectPayinfo> findList(BizDirectPayinfo bizDirectPayinfo) {
		return super.findList(bizDirectPayinfo);
	}
	
	public Page<BizDirectPayinfo> findPage(Page<BizDirectPayinfo> page, BizDirectPayinfo bizDirectPayinfo) {
		return super.findPage(page, bizDirectPayinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(BizDirectPayinfo bizDirectPayinfo) {
		super.save(bizDirectPayinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizDirectPayinfo bizDirectPayinfo) {
		super.delete(bizDirectPayinfo);
	}
	
}