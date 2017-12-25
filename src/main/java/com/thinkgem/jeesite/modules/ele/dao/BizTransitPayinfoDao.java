package com.thinkgem.jeesite.modules.ele.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ele.entity.BizTransitPayinfo;

/**
 * 转供缴费信息DAO接口
 * @author ws
 * @version 2017-12-25
 */
@MyBatisDao
public interface BizTransitPayinfoDao extends CrudDao<BizTransitPayinfo> {
	
}