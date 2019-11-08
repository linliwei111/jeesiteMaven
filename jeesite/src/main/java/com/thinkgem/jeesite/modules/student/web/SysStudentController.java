/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.student.entity.SysStudent;
import com.thinkgem.jeesite.modules.student.service.SysStudentService;

/**
 * 这个是学生信息表Controller
 * @author 林礼炜
 * @version 2019-11-07
 */
@Controller
@RequestMapping(value = "${adminPath}/student/sysStudent")
public class SysStudentController extends BaseController {

	@Autowired
	private SysStudentService sysStudentService;
	
	@ModelAttribute
	public SysStudent get(@RequestParam(required=false) String id) {
		SysStudent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysStudentService.get(id);
		}
		if (entity == null){
			entity = new SysStudent();
		}
		return entity;
	}
	
	@RequiresPermissions("student:sysStudent:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysStudent sysStudent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysStudent> page = sysStudentService.findPage(new Page<SysStudent>(request, response), sysStudent); 
		model.addAttribute("page", page);
		return "modules/student/sysStudentList";
	}

	@RequiresPermissions("student:sysStudent:view")
	@RequestMapping(value = "form")
	public String form(SysStudent sysStudent, Model model) {
		model.addAttribute("sysStudent", sysStudent);
		return "modules/student/sysStudentForm";
	}

	@RequiresPermissions("student:sysStudent:edit")
	@RequestMapping(value = "save")
	public String save(SysStudent sysStudent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysStudent)){
			return form(sysStudent, model);
		}
		sysStudentService.save(sysStudent);
		addMessage(redirectAttributes, "保存这个是学生信息表成功");
		return "redirect:"+Global.getAdminPath()+"/student/sysStudent/?repage";
	}
	
	@RequiresPermissions("student:sysStudent:edit")
	@RequestMapping(value = "delete")
	public String delete(SysStudent sysStudent, RedirectAttributes redirectAttributes) {
		sysStudentService.delete(sysStudent);
		addMessage(redirectAttributes, "删除这个是学生信息表成功");
		return "redirect:"+Global.getAdminPath()+"/student/sysStudent/?repage";
	}

}