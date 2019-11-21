/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.service;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.oa.entity.OaTestDimission;
import com.thinkgem.jeesite.modules.oa.dao.OaTestDimissionDao;

/**
 * 离职表单Service
 * @author 阿伟
 * @version 2019-11-08
 */
@Service
@Transactional(readOnly = true)
public class OaTestDimissionService extends CrudService<OaTestDimissionDao, OaTestDimission> {


	@Autowired
	private ActTaskService actTaskService;

	public OaTestDimission get(String id) {
		return super.get(id);
	}
	
	public List<OaTestDimission> findList(OaTestDimission oaTestDimission) {
		return super.findList(oaTestDimission);
	}
	
	public Page<OaTestDimission> findPage(Page<OaTestDimission> page, OaTestDimission oaTestDimission) {
		return super.findPage(page, oaTestDimission);
	}

	/**
	 * 审核新增或编辑
	 * @param oaTestDimission
	 */
	@Transactional(readOnly = false)
	public void save(OaTestDimission oaTestDimission) {

		// 申请发起
		if (StringUtils.isBlank(oaTestDimission.getId())){
			oaTestDimission.preInsert();
			dao.insert(oaTestDimission);

			// 启动流程
			actTaskService.startProcess(ActUtils.PD_TEST_DIMISSION[0], ActUtils.PD_TEST_DIMISSION[1], oaTestDimission.getId(), oaTestDimission.getContent());

		}

		// 重新编辑申请
		else{
			oaTestDimission.preUpdate();
			dao.update(oaTestDimission);

			oaTestDimission.getAct().setComment(("yes".equals(oaTestDimission.getAct().getFlag())?"[重申] ":"[销毁] ")+oaTestDimission.getAct().getComment());

			// 完成流程任务
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(oaTestDimission.getAct().getFlag())? "1" : "0");
			actTaskService.complete(oaTestDimission.getAct().getTaskId(), oaTestDimission.getAct().getProcInsId(), oaTestDimission.getAct().getComment(), oaTestDimission.getContent(), vars);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(OaTestDimission oaTestDimission) {
		super.delete(oaTestDimission);
	}


	/**
	 * 审核审批保存
	 * @param oaTestDimission
	 */
	@Transactional(readOnly = false)
	public void DimissionSave(OaTestDimission oaTestDimission) {

		// 设置意见
		oaTestDimission.getAct().setComment(("yes".equals(oaTestDimission.getAct().getFlag())?"[同意] ":"[驳回] ")+oaTestDimission.getAct().getComment());

		oaTestDimission.preUpdate();

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = oaTestDimission.getAct().getTaskDefKey();

		// 审核环节
		if ("audit".equals(taskDefKey)){

		}
		else if ("audit2".equals(taskDefKey)){
			oaTestDimission.setHrText(oaTestDimission.getAct().getComment());
			dao.updateHrText(oaTestDimission);
		}
		else if ("audit3".equals(taskDefKey)){
			oaTestDimission.setLeadText(oaTestDimission.getAct().getComment());
			dao.updateLeadText(oaTestDimission);
		}

		else if ("apply_end".equals(taskDefKey)){
		}

		// 未知环节，直接返回
		else{
			return;
		}

		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(oaTestDimission.getAct().getFlag())? "1" : "0");
		actTaskService.complete(oaTestDimission.getAct().getTaskId(), oaTestDimission.getAct().getProcInsId(), oaTestDimission.getAct().getComment(), vars);

	}

}