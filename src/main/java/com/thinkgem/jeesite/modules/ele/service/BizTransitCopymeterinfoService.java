package com.thinkgem.jeesite.modules.ele.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ele.entity.BizTransitCopymeterinfo;
import com.thinkgem.jeesite.modules.ele.dao.BizTransitCopymeterinfoDao;

/**
 * 转供抄表信息Service
 * @author ws
 * @version 2017-11-09
 */
@Service
@Transactional(readOnly = true)
public class BizTransitCopymeterinfoService extends CrudService<BizTransitCopymeterinfoDao, BizTransitCopymeterinfo> {

	public BizTransitCopymeterinfo get(String id) {
		return super.get(id);
	}
	
	public List<BizTransitCopymeterinfo> findList(BizTransitCopymeterinfo bizTransitCopymeterinfo) {
		return super.findList(bizTransitCopymeterinfo);
	}
	
	public Page<BizTransitCopymeterinfo> findPage(Page<BizTransitCopymeterinfo> page, BizTransitCopymeterinfo bizTransitCopymeterinfo) {
		return super.findPage(page, bizTransitCopymeterinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(BizTransitCopymeterinfo bizTransitCopymeterinfo) {
		super.save(bizTransitCopymeterinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizTransitCopymeterinfo bizTransitCopymeterinfo) {
		super.delete(bizTransitCopymeterinfo);
	}
	
}