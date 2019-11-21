/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.student.entity.SysStudent;

/**
 * 单表生成DAO接口
 * @author 阿伟
 * @version 2019-11-07
 */
@MyBatisDao
public interface SysStudentDao extends CrudDao<SysStudent> {
	
}