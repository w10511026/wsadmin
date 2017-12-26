package com.thinkgem.jeesite.modules.ele.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ele.entity.BizDirectPayinfo;

/**
 * 直供缴费信息DAO接口
 * @author ws
 * @version 2017-12-26
 */
@MyBatisDao
public interface BizDirectPayinfoDao extends CrudDao<BizDirectPayinfo> {
	
}