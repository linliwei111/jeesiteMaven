<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>离职表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/oaTestDimission/">离职列表</a></li>
		<li class="active"><a href="${ctx}/oa/oaTestDimission/form?id=${oaTestDimission.id}">离职表<shiro:hasPermission name="oa:oaTestDimission:edit">${not empty oaTestDimission.id?'修改':'添加'}
		</shiro:hasPermission><shiro:lacksPermission name="oa:oaTestDimission:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaTestDimission" action="${ctx}/oa/oaTestDimission/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="act.taskId"/>
		<form:hidden path="act.taskName"/>
		<form:hidden path="act.taskDefKey"/>
		<form:hidden path="act.procInsId"/>
		<form:hidden path="act.procDefId"/>
		<form:hidden id="flag" path="act.flag"/>
		<sys:message content="${message}"/>
	<fieldset>
		<legend>审批申请</legend>
		<table class="table-form">

		<div class="control-group">
			<label class="control-label">流程实例ID：</label>
			<div class="controls">
				<form:input path="procInsId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">变动用户：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${oaTestDimission.user.id}" labelName="user.name" labelValue="${oaTestDimission.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属部门：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${oaTestDimission.office.id}" labelName="office.name" labelValue="${oaTestDimission.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">岗位：</label>
			<div class="controls">
				<form:input path="post" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				<form:select path="age" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学历：</label>
			<div class="controls">
				<form:input path="edu" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">离职原因：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">执行时间：</label>
			<div class="controls">
				<form:input path="exeDate" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">人力资源部门意见：</label>
			<div class="controls">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">分管领导意见： </label>
			<div class="controls">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">集团主要领导意见：</label>
			<div class="controls">
			</div>
		</div>

		</table>

	</fieldset>
		<div class="form-actions">
			<shiro:hasPermission name="oa:oaTestDimission:edit">

				<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交 申请" onclick="$('#flag').val('yes')"/>&nbsp;
				<c:if test="${not empty oaTestDimission.id}">
					<input id="btnSubmit2" class="btn btn-inverse" type="submit" value="销毁申请" onclick="$('#flag').val('no')"/>&nbsp;
				</c:if>
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<c:if test="${not empty oaTestDimission.id}">
			<act:histoicFlow procInsId="${oaTestDimission.act.procInsId}" />
		</c:if>
	</form:form>
</body>
</html>