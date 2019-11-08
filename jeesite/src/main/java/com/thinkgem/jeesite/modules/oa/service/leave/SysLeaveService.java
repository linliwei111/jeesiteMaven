/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service.leave;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.leave.SysLeave;
import com.thinkgem.jeesite.modules.oa.dao.leave.SysLeaveDao;

/**
 * 离职申请表Service
 * @author 林礼炜
 * @version 2019-11-08
 */
@Service
@Transactional(readOnly = true)
public class SysLeaveService extends CrudService<SysLeaveDao, SysLeave> {

	public SysLeave get(String id) {
		return super.get(id);
	}
	
	public List<SysLeave> findList(SysLeave sysLeave) {
		return super.findList(sysLeave);
	}
	
	public Page<SysLeave> findPage(Page<SysLeave> page, SysLeave sysLeave) {
		return super.findPage(page, sysLeave);
	}
	
	@Transactional(readOnly = false)
	public void save(SysLeave sysLeave) {
		super.save(sysLeave);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysLeave sysLeave) {
		super.delete(sysLeave);
	}
	
}