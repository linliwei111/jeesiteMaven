<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>离职表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oa/oaTestDimission/">离职列表</a></li>
		<shiro:hasPermission name="oa:oaTestDimission:edit"><li><a href="${ctx}/oa/oaTestDimission/form">离职申请流程</a></li></shiro:hasPermission>
	</ul>
<%--	<form:form id="searchForm" modelAttribute="oaTestDimission" action="${ctx}/oa/oaTestDimission/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>流程实例ID：</label>
				<form:input path="procInsId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>变动用户：</label>
				<sys:treeselect id="user" name="user.id" value="${oaTestDimission.user.id}" labelName="user.name" labelValue="${oaTestDimission.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>归属部门：</label>
				<sys:treeselect id="office" name="office.id" value="${oaTestDimission.office.id}" labelName="office.name" labelValue="${oaTestDimission.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>岗位：</label>
				<form:input path="post" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>性别：</label>
				<form:select path="age" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>学历：</label>
				<form:input path="edu" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>离职原因：</label>
				<form:input path="content" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>执行时间：</label>
				<form:input path="exeDate" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>人力资源部门意见：</label>
				<form:input path="hrText" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>分管领导意见：</label>
				<form:input path="leadText" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>集团主要领导意见：</label>
				<form:input path="mainLeadText" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>--%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>归属部门</th>
				<th>岗位</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="oa:oaTestDimission:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaTestDimission">
			<tr>
				<td><a href="${ctx}/oa/oaTestDimission/form?id=${oaTestDimission.id}">
					${oaTestDimission.user.id}
				</a></td>
				<td>
					${oaTestDimission.office.name}
				</td>
				<td>
					${oaTestDimission.post}
				</td>
				<td>
					<fmt:formatDate value="${oaTestDimission.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${oaTestDimission.remarks}
				</td>
				<shiro:hasPermission name="oa:oaTestDimission:edit"><td>
    				<a href="${ctx}/oa/oaTestDimission/form?id=${oaTestDimission.id}">查看</a>
					<a href="${ctx}/oa/oaTestDimission/delete?id=${oaTestDimission.id}" onclick="return confirmx('确认要删除该离职表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>