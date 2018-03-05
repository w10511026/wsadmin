package com.thinkgem.jeesite.modules.ele.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ele.entity.SysLog;
import com.thinkgem.jeesite.modules.ele.dao.SysLogDao;

/**
 * 操作日志Service
 * @author 操作日志
 * @version 2018-03-05
 */
@Service
@Transactional(readOnly = true)
public class SysLogService extends CrudService<SysLogDao, SysLog> {

	public SysLog get(String id) {
		return super.get(id);
	}
	
	public List<SysLog> findList(SysLog sysLog) {
		return super.findList(sysLog);
	}
	
	public Page<SysLog> findPage(Page<SysLog> page, SysLog sysLog) {
		return super.findPage(page, sysLog);
	}
	
	@Transactional(readOnly = false)
	public void save(SysLog sysLog) {
		super.save(sysLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysLog sysLog) {
		super.delete(sysLog);
	}
	
}