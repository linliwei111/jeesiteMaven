/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.oa.entity.OaTestDimission;
import com.thinkgem.jeesite.modules.oa.entity.TestAudit;

/**
 * 离职表单DAO接口
 * @author 阿伟
 * @version 2019-11-08
 */
@MyBatisDao
public interface OaTestDimissionDao extends CrudDao<OaTestDimission> {

    public TestAudit getByProcInsId(String procInsId);

    public int updateInsId(OaTestDimission oaTestDimission);

    public int updateHrText(OaTestDimission oaTestDimission);

    public int updateLeadText(OaTestDimission oaTestDimission);

    public int updateMainLeadText(OaTestDimission oaTestDimission);

}