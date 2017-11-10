package com.thinkgem.jeesite.modules.ele.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ele.entity.BizDirectReceiptinfo;
import com.thinkgem.jeesite.modules.ele.dao.BizDirectReceiptinfoDao;

/**
 * 直供回款信息Service
 * @author ws
 * @version 2017-11-09
 */
@Service
@Transactional(readOnly = true)
public class BizDirectReceiptinfoService extends CrudService<BizDirectReceiptinfoDao, BizDirectReceiptinfo> {

	public BizDirectReceiptinfo get(String id) {
		return super.get(id);
	}
	
	public List<BizDirectReceiptinfo> findList(BizDirectReceiptinfo bizDirectReceiptinfo) {
		return super.findList(bizDirectReceiptinfo);
	}
	
	public Page<BizDirectReceiptinfo> findPage(Page<BizDirectReceiptinfo> page, BizDirectReceiptinfo bizDirectReceiptinfo) {
		return super.findPage(page, bizDirectReceiptinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(BizDirectReceiptinfo bizDirectReceiptinfo) {
		super.save(bizDirectReceiptinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizDirectReceiptinfo bizDirectReceiptinfo) {
		super.delete(bizDirectReceiptinfo);
	}
	
}