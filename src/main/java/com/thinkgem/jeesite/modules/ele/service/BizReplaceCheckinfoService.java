package com.thinkgem.jeesite.modules.ele.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ele.entity.BizReplaceCheckinfo;
import com.thinkgem.jeesite.modules.ele.dao.BizReplaceCheckinfoDao;

/**
 * 代维巡检信息Service
 * @author ws
 * @version 2017-11-06
 */
@Service
@Transactional(readOnly = true)
public class BizReplaceCheckinfoService extends CrudService<BizReplaceCheckinfoDao, BizReplaceCheckinfo> {

	public BizReplaceCheckinfo get(String id) {
		return super.get(id);
	}
	
	public List<BizReplaceCheckinfo> findList(BizReplaceCheckinfo bizReplaceCheckinfo) {
		return super.findList(bizReplaceCheckinfo);
	}
	
	public Page<BizReplaceCheckinfo> findPage(Page<BizReplaceCheckinfo> page, BizReplaceCheckinfo bizReplaceCheckinfo) {
		return super.findPage(page, bizReplaceCheckinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(BizReplaceCheckinfo bizReplaceCheckinfo) {
		super.save(bizReplaceCheckinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizReplaceCheckinfo bizReplaceCheckinfo) {
		super.delete(bizReplaceCheckinfo);
	}
	
}