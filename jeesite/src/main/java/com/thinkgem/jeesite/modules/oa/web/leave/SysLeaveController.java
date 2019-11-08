/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web.leave;

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
import com.thinkgem.jeesite.modules.oa.entity.leave.SysLeave;
import com.thinkgem.jeesite.modules.oa.service.leave.SysLeaveService;

/**
 * 离职申请表Controller
 * @author 林礼炜
 * @version 2019-11-08
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/leave/sysLeave")
public class SysLeaveController extends BaseController {

	@Autowired
	private SysLeaveService sysLeaveService;
	
	@ModelAttribute
	public SysLeave get(@RequestParam(required=false) String id) {
		SysLeave entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysLeaveService.get(id);
		}
		if (entity == null){
			entity = new SysLeave();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:leave:sysLeave:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysLeave sysLeave, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysLeave> page = sysLeaveService.findPage(new Page<SysLeave>(request, response), sysLeave); 
		model.addAttribute("page", page);
		return "modules/oa/leave/sysLeaveList";
	}

	@RequiresPermissions("oa:leave:sysLeave:view")
	@RequestMapping(value = "form")
	public String form(SysLeave sysLeave, Model model) {
		model.addAttribute("sysLeave", sysLeave);
		return "modules/oa/leave/sysLeaveForm";
	}

	@RequiresPermissions("oa:leave:sysLeave:edit")
	@RequestMapping(value = "save")
	public String save(SysLeave sysLeave, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysLeave)){
			return form(sysLeave, model);
		}
		sysLeaveService.save(sysLeave);
		addMessage(redirectAttributes, "保存离职申请表成功");
		return "redirect:"+Global.getAdminPath()+"/oa/leave/sysLeave/?repage";
	}
	
	@RequiresPermissions("oa:leave:sysLeave:edit")
	@RequestMapping(value = "delete")
	public String delete(SysLeave sysLeave, RedirectAttributes redirectAttributes) {
		sysLeaveService.delete(sysLeave);
		addMessage(redirectAttributes, "删除离职申请表成功");
		return "redirect:"+Global.getAdminPath()+"/oa/leave/sysLeave/?repage";
	}

}