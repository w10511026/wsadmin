package com.thinkgem.jeesite.modules.ele.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ele.entity.BizBasicShareinfo;

/**
 * 基础电流分摊信息DAO接口
 * @author ws
 * @version 2018-02-09
 */
@MyBatisDao
public interface BizBasicShareinfoDao extends CrudDao<BizBasicShareinfo> {
	
}