<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
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
		<li class="active"><a href="${ctx}/student/sysStudent/">单表列表</a></li>
		<shiro:hasPermission name="student:sysStudent:edit"><li><a href="${ctx}/student/sysStudent/form">单表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysStudent" action="${ctx}/student/sysStudent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学生id：</label>
				<form:input path="studentId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>学生登录名：</label>
				<form:input path="studentloginName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>学生密码：</label>
				<form:input path="studentPassword" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>学生姓名：</label>
				<form:input path="studentName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>邮箱：</label>
				<form:input path="studentEmail" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>学生电话：</label>
				<form:input path="studentPhone" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>学生性别：</label>
				<form:radiobuttons path="studentGender" items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学生id</th>
				<th>学生登录名</th>
				<th>学生密码</th>
				<th>学生姓名</th>
				<th>邮箱</th>
				<th>学生电话</th>
				<th>学生性别</th>
				<th>学生头像</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<%--<th>排序</th>--%>
				<shiro:hasPermission name="student:sysStudent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysStudent">
			<tr>
				<td><a href="${ctx}/student/sysStudent/form?id=${sysStudent.id}">
					${sysStudent.studentId}
				</a></td>
				<td>
					${sysStudent.studentloginName}
				</td>
				<td>
					${sysStudent.studentPassword}
				</td>
				<td>
					${sysStudent.studentName}
				</td>
				<td>
					${sysStudent.studentEmail}
				</td>
				<td>
					${sysStudent.studentPhone}10


				</td>
				<td>
					${fns:getDictLabel(sysStudent.studentGender, 'sex', '')}
				</td>
				<td>
				<%--	${sysStudent.studentPhoto}--%>
					<img  style="max-height: 100px;max-width: 0100px " src="${sysStudent.studentPhoto}"/>


				</td>
				<td>
					<fmt:formatDate value="${sysStudent.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sysStudent.remarks}
				</td>
			<%--	<td>
					${sysStudent.sort}

				</td>--%>
				<shiro:hasPermission name="student:sysStudent:edit"><td>
    				<a href="${ctx}/student/sysStudent/form?id=${sysStudent.id}">修改</a>
					<a href="${ctx}/student/sysStudent/delete?id=${sysStudent.id}" onclick="return confirmx('确认要删除该单表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>