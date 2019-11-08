/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.student.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 这个是学生信息表Entity
 * @author 林礼炜
 * @version 2019-11-07
 */
public class SysStudent extends DataEntity<SysStudent> {
	
	private static final long serialVersionUID = 1L;
	private String studentId;		// student_id
	private String studentName;		// student_name
	private String studentAge;		// student_age
	private String studentGenger;		// student_genger
	private String studentPhone;		// student_phone
	private String studentAddress;		// student_address
	private String studentNum1;		// student_num1
	private String studentNum2;		// student_num2
	private String studentNum3;		// student_num3
	private String studentNum4;		// student_num4
	
	public SysStudent() {
		super();
	}

	public SysStudent(String id){
		super(id);
	}

	@Length(min=0, max=255, message="student_id长度必须介于 0 和 255 之间")
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	@Length(min=0, max=255, message="student_name长度必须介于 0 和 255 之间")
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	@Length(min=0, max=255, message="student_age长度必须介于 0 和 255 之间")
	public String getStudentAge() {
		return studentAge;
	}

	public void setStudentAge(String studentAge) {
		this.studentAge = studentAge;
	}
	
	@Length(min=0, max=255, message="student_genger长度必须介于 0 和 255 之间")
	public String getStudentGenger() {
		return studentGenger;
	}

	public void setStudentGenger(String studentGenger) {
		this.studentGenger = studentGenger;
	}
	
	@Length(min=0, max=255, message="student_phone长度必须介于 0 和 255 之间")
	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}
	
	@Length(min=0, max=255, message="student_address长度必须介于 0 和 255 之间")
	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	
	@Length(min=0, max=1000, message="student_num1长度必须介于 0 和 1000 之间")
	public String getStudentNum1() {
		return studentNum1;
	}

	public void setStudentNum1(String studentNum1) {
		this.studentNum1 = studentNum1;
	}
	
	@Length(min=0, max=255, message="student_num2长度必须介于 0 和 255 之间")
	public String getStudentNum2() {
		return studentNum2;
	}

	public void setStudentNum2(String studentNum2) {
		this.studentNum2 = studentNum2;
	}
	
	@Length(min=0, max=255, message="student_num3长度必须介于 0 和 255 之间")
	public String getStudentNum3() {
		return studentNum3;
	}

	public void setStudentNum3(String studentNum3) {
		this.studentNum3 = studentNum3;
	}
	
	@Length(min=0, max=255, message="student_num4长度必须介于 0 和 255 之间")
	public String getStudentNum4() {
		return studentNum4;
	}

	public void setStudentNum4(String studentNum4) {
		this.studentNum4 = studentNum4;
	}
	
}