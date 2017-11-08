package com.thinkgem.jeesite.modules.ele.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ele.entity.BizElectricShareinfo;
import com.thinkgem.jeesite.modules.ele.dao.BizElectricShareinfoDao;

/**
 * 电流分摊信息Service
 * @author ws
 * @version 2017-11-06
 */
@Service
@Transactional(readOnly = true)
public class BizElectricShareinfoService extends CrudService<BizElectricShareinfoDao, BizElectricShareinfo> {

	public BizElectricShareinfo get(String id) {
		return super.get(id);
	}
	
	public List<BizElectricShareinfo> findList(BizElectricShareinfo bizElectricShareinfo) {
		return super.findList(bizElectricShareinfo);
	}
	
	public Page<BizElectricShareinfo> findPage(Page<BizElectricShareinfo> page, BizElectricShareinfo bizElectricShareinfo) {
		return super.findPage(page, bizElectricShareinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(BizElectricShareinfo bizElectricShareinfo) {
		super.save(bizElectricShareinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizElectricShareinfo bizElectricShareinfo) {
		super.delete(bizElectricShareinfo);
	}
	
}