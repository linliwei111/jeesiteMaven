/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.web;

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
import com.thinkgem.jeesite.modules.oa.entity.OaTestDimission;
import com.thinkgem.jeesite.modules.oa.service.OaTestDimissionService;

/**
 * 离职表单Controller
 * @author 阿伟
 * @version 2019-11-08
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaTestDimission")
public class OaTestDimissionController extends BaseController {

	@Autowired
	private OaTestDimissionService oaTestDimissionService;
	
	@ModelAttribute
	public OaTestDimission get(@RequestParam(required=false) String id) {
		OaTestDimission entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaTestDimissionService.get(id);
		}
		if (entity == null){
			entity = new OaTestDimission();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaTestDimission:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaTestDimission oaTestDimission, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaTestDimission> page = oaTestDimissionService.findPage(new Page<OaTestDimission>(request, response), oaTestDimission); 
		model.addAttribute("page", page);
		return "modules/oa/oaTestDimissionList";
	}





	/**
	 * 申请单填写
	 * @param oaTestDimission
	 * @param model
	 * @return
	 */
	@RequiresPermissions("oa:oaTestDimission:view")
	@RequestMapping(value = "form")
	public String form(OaTestDimission oaTestDimission, Model model) {


		String view = "oaTestDimissionForm";

		// 查看审批申请单
		if (org.apache.commons.lang3.StringUtils.isNotBlank(oaTestDimission.getId())){//.getAct().getProcInsId())){

			// 环节编号
			String taskDefKey = oaTestDimission.getAct().getTaskDefKey();

			// 查看工单
			if(oaTestDimission.getAct().isFinishTask()){
				view = "oaTestDimissionView";
			}
			// 修改环节
			else if ("modify".equals(taskDefKey)){
				view = "oaTestDimissionForm";
			}
			// 审核环节
			else if ("audit".equals(taskDefKey)){
				view = "oaTestDimissionAudit";

			}
			// 审核环节2
			else if ("audit2".equals(taskDefKey)){
				view = "oaTestDimissionAudit";
			}
			// 审核环节3
			else if ("audit3".equals(taskDefKey)){
				view = "oaTestDimissionAudit";
			}

			// 兑现环节
			else if ("apply_end".equals(taskDefKey)){
				view = "oaTestDimissionAudit";
			}
		}

		model.addAttribute("oaTestDimission", oaTestDimission);
		return "modules/oa/" + view;
	/*	return "modules/oa/oaTestDimissionview";*/
	}

	/**
	 * 申请单保存/修改
	 * @param oaTestDimission
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:oaTestDimission:edit")
	@RequestMapping(value = "save")
	public String save(OaTestDimission oaTestDimission, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaTestDimission)){
			return form(oaTestDimission, model);
		}
		oaTestDimissionService.save(oaTestDimission);
		addMessage(redirectAttributes, "提交审批'" + oaTestDimission.getUser().getName() + "'成功");
		return "redirect:"+adminPath+"/act/task/todo/";/* "/oa/oaTestDimission/?repage"*/
	}

	/**
	 * 工单执行（完成任务）
	 * @param oaTestDimission
	 * @param model
	 * @return
	 */
	@RequiresPermissions("oa:oaTestDimission:edit")
	@RequestMapping(value = "saveDimission")
	public String saveDimission(OaTestDimission oaTestDimission, Model model) {
		if (StringUtils.isBlank(oaTestDimission.getAct().getFlag())
				||StringUtils.isBlank(oaTestDimission.getAct().getComment())){
			addMessage(model, "请填写审核意见。");
			return form(oaTestDimission, model);
		}
		oaTestDimissionService.DimissionSave(oaTestDimission);
		return "redirect:" + adminPath + "/act/task/todo/";
	}


	/**
	 * 删除工单
	 * @param oaTestDimission
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:oaTestDimission:edit")
	@RequestMapping(value = "delete")
	public String delete(OaTestDimission oaTestDimission, RedirectAttributes redirectAttributes) {
		oaTestDimissionService.delete(oaTestDimission);
		addMessage(redirectAttributes, "删除离职表成功");
		return "redirect:"+adminPath+"/oa/oaTestDimission/?repage";
	}



}