package com.thinkgem.jeesite.modules.ele.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ele.entity.BizTransitReceiptinfo;
import com.thinkgem.jeesite.modules.ele.dao.BizTransitReceiptinfoDao;

/**
 * 转供回款信息Service
 * @author ws
 * @version 2017-11-06
 */
@Service
@Transactional(readOnly = true)
public class BizTransitReceiptinfoService extends CrudService<BizTransitReceiptinfoDao, BizTransitReceiptinfo> {

	public BizTransitReceiptinfo get(String id) {
		return super.get(id);
	}
	
	public List<BizTransitReceiptinfo> findList(BizTransitReceiptinfo bizTransitReceiptinfo) {
		return super.findList(bizTransitReceiptinfo);
	}
	
	public Page<BizTransitReceiptinfo> findPage(Page<BizTransitReceiptinfo> page, BizTransitReceiptinfo bizTransitReceiptinfo) {
		return super.findPage(page, bizTransitReceiptinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(BizTransitReceiptinfo bizTransitReceiptinfo) {
		super.save(bizTransitReceiptinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizTransitReceiptinfo bizTransitReceiptinfo) {
		super.delete(bizTransitReceiptinfo);
	}
	
}