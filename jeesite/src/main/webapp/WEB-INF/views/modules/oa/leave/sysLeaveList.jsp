<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>离职申请表管理</title>
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
		<li class="active"><a href="${ctx}/oa/leave/sysLeave/">离职申请表</a></li>
		<shiro:hasPermission name="oa:leave:sysLeave:edit"><li><a href="${ctx}/oa/leave/sysLeave/form">添加离职申请表</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysLeave" action="${ctx}/oa/leave/sysLeave/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>id：</label>
				<form:input path="processInstanceId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>开始时间：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${sysLeave.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结束时间：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${sysLeave.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>离职类型：</label>
				<form:input path="leaveType" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>离职理由：</label>
				<form:input path="reason" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>工作时间：</label>
				<input name="applyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${sysLeave.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>离职类型</th>
				<th>离职理由</th>
				<th>工作时间</th>
				<th>实际开始时间</th>
				<th>实际结束时间</th>
				<th>更新日期</th>
				<th>备注</th>
				<shiro:hasPermission name="oa:leave:sysLeave:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysLeave">
			<tr>
				<td><a href="${ctx}/oa/leave/sysLeave/form?id=${sysLeave.id}">
					${sysLeave.processInstanceId}
				</a></td>
				<td>
					<fmt:formatDate value="${sysLeave.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${sysLeave.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sysLeave.leaveType}
				</td>
				<td>
					${sysLeave.reason}
				</td>
				<td>
					<fmt:formatDate value="${sysLeave.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${sysLeave.realityStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${sysLeave.realityEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${sysLeave.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sysLeave.remarks}
				</td>
				<shiro:hasPermission name="oa:leave:sysLeave:edit"><td>
    				<a href="${ctx}/oa/leave/sysLeave/form?id=${sysLeave.id}">修改</a>
					<a href="${ctx}/oa/leave/sysLeave/delete?id=${sysLeave.id}" onclick="return confirmx('确认要删除该离职申请表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>