package com.thinkgem.jeesite.modules.ele.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ele.entity.BizTransitContractinfo;
import com.thinkgem.jeesite.modules.ele.dao.BizTransitContractinfoDao;

/**
 * 转供合同信息Service
 * @author ws
 * @version 2017-11-09
 */
@Service
@Transactional(readOnly = true)
public class BizTransitContractinfoService extends CrudService<BizTransitContractinfoDao, BizTransitContractinfo> {

	public BizTransitContractinfo get(String id) {
		return super.get(id);
	}
	
	public List<BizTransitContractinfo> findList(BizTransitContractinfo bizTransitContractinfo) {
		return super.findList(bizTransitContractinfo);
	}
	
	public Page<BizTransitContractinfo> findPage(Page<BizTransitContractinfo> page, BizTransitContractinfo bizTransitContractinfo) {
		return super.findPage(page, bizTransitContractinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(BizTransitContractinfo bizTransitContractinfo) {
		super.save(bizTransitContractinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizTransitContractinfo bizTransitContractinfo) {
		super.delete(bizTransitContractinfo);
	}
	
}