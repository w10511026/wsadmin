package com.thinkgem.jeesite.modules.ele.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ele.entity.BizReplaceCheckinfo;

/**
 * 代维巡检信息DAO接口
 * @author ws
 * @version 2017-12-26
 */
@MyBatisDao
public interface BizReplaceCheckinfoDao extends CrudDao<BizReplaceCheckinfo> {
	
}