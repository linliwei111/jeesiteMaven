/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao.leave;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.leave.SysLeave;

/**
 * 离职申请表DAO接口
 * @author 林礼炜
 * @version 2019-11-08
 */
@MyBatisDao
public interface SysLeaveDao extends CrudDao<SysLeave> {
	
}