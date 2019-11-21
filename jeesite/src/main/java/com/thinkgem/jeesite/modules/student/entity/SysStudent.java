/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author 阿伟
 * @version 2019-11-07
 */
public class SysStudent extends DataEntity<SysStudent> {
	
	private static final long serialVersionUID = 1L;
	private String studentId;		// 学生id
	private String studentloginName;		// 学生登录名
	private String studentPassword;		// 学生密码
	private String studentName;		// 学生姓名
	private String studentEmail;		// 邮箱
	private String studentPhone;		// 学生电话
	private String studentGender;		// 学生性别
	private String studentPhoto;		// 学生头像
	private String loginIp;		// 最后登陆IP
	private Date loginDate;		// 最后登陆时间
	private String loginFlag;		// 是否可登录
	private Integer sort;		// 排序
	
	public SysStudent() {
		super();
	}

	public SysStudent(String id){
		super(id);
	}

	@Length(min=1, max=64, message="学生id长度必须介于 1 和 64 之间")
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	@Length(min=1, max=100, message="学生登录名长度必须介于 1 和 100 之间")
	public String getStudentloginName() {
		return studentloginName;
	}

	public void setStudentloginName(String studentloginName) {
		this.studentloginName = studentloginName;
	}
	
	@Length(min=1, max=100, message="学生密码长度必须介于 1 和 100 之间")
	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	
	@Length(min=1, max=100, message="学生姓名长度必须介于 1 和 100 之间")
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	@Length(min=0, max=200, message="邮箱长度必须介于 0 和 200 之间")
	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	@Length(min=0, max=200, message="学生电话长度必须介于 0 和 200 之间")
	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}
	
	@Length(min=0, max=1, message="学生性别长度必须介于 0 和 1 之间")
	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}
	
	@Length(min=0, max=1000, message="学生头像长度必须介于 0 和 1000 之间")
	public String getStudentPhoto() {
		return studentPhoto;
	}

	public void setStudentPhoto(String studentPhoto) {
		this.studentPhoto = studentPhoto;
	}
	
	@Length(min=0, max=100, message="最后登陆IP长度必须介于 0 和 100 之间")
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	@Length(min=0, max=64, message="是否可登录长度必须介于 0 和 64 之间")
	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
	
	@NotNull(message="排序不能为空")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}