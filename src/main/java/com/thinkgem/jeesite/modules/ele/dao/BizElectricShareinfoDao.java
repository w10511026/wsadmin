package com.thinkgem.jeesite.modules.ele.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ele.entity.BizElectricShareinfo;

/**
 * 电流分摊信息DAO接口
 * @author ws
 * @version 2017-11-06
 */
@MyBatisDao
public interface BizElectricShareinfoDao extends CrudDao<BizElectricShareinfo> {
	
}