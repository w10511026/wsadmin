package com.thinkgem.jeesite.modules.ele.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ele.entity.SysLog;

/**
 * 操作日志DAO接口
 * @author 操作日志
 * @version 2018-03-05
 */
@MyBatisDao
public interface SysLogDao extends CrudDao<SysLog> {
	
}