/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.student.entity.SysStudent;
import com.thinkgem.jeesite.modules.student.dao.SysStudentDao;

/**
 * 这个是学生信息表Service
 * @author 林礼炜
 * @version 2019-11-07
 */
@Service
@Transactional(readOnly = true)
public class SysStudentService extends CrudService<SysStudentDao, SysStudent> {

	public SysStudent get(String id) {
		return super.get(id);
	}
	
	public List<SysStudent> findList(SysStudent sysStudent) {
		return super.findList(sysStudent);
	}
	
	public Page<SysStudent> findPage(Page<SysStudent> page, SysStudent sysStudent) {
		return super.findPage(page, sysStudent);
	}
	
	@Transactional(readOnly = false)
	public void save(SysStudent sysStudent) {
		super.save(sysStudent);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysStudent sysStudent) {
		super.delete(sysStudent);
	}
	
}