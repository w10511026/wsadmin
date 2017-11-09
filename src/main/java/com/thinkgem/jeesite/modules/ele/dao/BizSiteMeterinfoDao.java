package com.thinkgem.jeesite.modules.ele.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ele.entity.BizSiteMeterinfo;

/**
 * 表站对应信息DAO接口
 * @author ws
 * @version 2017-11-08
 */
@MyBatisDao
public interface BizSiteMeterinfoDao extends CrudDao<BizSiteMeterinfo> {
	
}