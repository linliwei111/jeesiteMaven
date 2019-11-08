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
		<li class="active"><a href="${ctx}/oa/leave/sysLeave/">离职申请表列表</a></li>
		<shiro:hasPermission name="oa:leave:sysLeave:edit"><li><a href="${ctx}/oa/leave/sysLeave/form">离职申请表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sysLeave" action="${ctx}/oa/leave/sysLeave/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>process_instance_id：</label>
				<form:input path="processInstanceId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>start_time：</label>
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${sysLeave.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>end_time：</label>
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${sysLeave.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>leave_type：</label>
				<form:input path="leaveType" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>reason：</label>
				<form:input path="reason" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>apply_time：</label>
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
				<th>process_instance_id</th>
				<th>start_time</th>
				<th>end_time</th>
				<th>leave_type</th>
				<th>reason</th>
				<th>apply_time</th>
				<th>reality_start_time</th>
				<th>reality_end_time</th>
				<th>update_date</th>
				<th>remarks</th>
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