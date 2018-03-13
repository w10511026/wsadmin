package com.thinkgem.jeesite.modules.ele.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ele.entity.BizOperateLog;
import com.thinkgem.jeesite.modules.ele.dao.BizOperateLogDao;

/**
 * 操作日志Service
 * @author 操作日志
 * @version 2018-03-13
 */
@Service
@Transactional(readOnly = true)
public class BizOperateLogService extends CrudService<BizOperateLogDao, BizOperateLog> {

	public BizOperateLog get(String id) {
		return super.get(id);
	}
	
	public List<BizOperateLog> findList(BizOperateLog bizOperateLog) {
		return super.findList(bizOperateLog);
	}
	
	public Page<BizOperateLog> findPage(Page<BizOperateLog> page, BizOperateLog bizOperateLog) {
		return super.findPage(page, bizOperateLog);
	}
	
	@Transactional(readOnly = false)
	public void save(BizOperateLog bizOperateLog) {
		super.save(bizOperateLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizOperateLog bizOperateLog) {
		super.delete(bizOperateLog);
	}
	
}