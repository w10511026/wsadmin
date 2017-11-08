<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>FSU监控信息管理</title>
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
		<li class="active"><a href="${ctx}/ele/bizFsuMonitorinfo/">FSU监控信息列表</a></li>
		<shiro:hasPermission name="ele:bizFsuMonitorinfo:edit"><li><a href="${ctx}/ele/bizFsuMonitorinfo/form">FSU监控信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizFsuMonitorinfo" action="${ctx}/ele/bizFsuMonitorinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>站址编码：</label>
				<form:input path="fsusitenum" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>监控电流：</label>
				<form:input path="fsuq" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>采集日期：</label>
				<input name="beginFsudate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizFsuMonitorinfo.beginFsudate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endFsudate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizFsuMonitorinfo.endFsudate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>站址编码</th>
				<th>监控电流</th>
				<th>采集日期</th>
				<th>创建者</th>
				<th>创建时间</th>
				<shiro:hasPermission name="ele:bizFsuMonitorinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizFsuMonitorinfo">
			<tr>
				<td><a href="${ctx}/ele/bizFsuMonitorinfo/form?id=${bizFsuMonitorinfo.id}">
					${bizFsuMonitorinfo.fsusitenum}
				</a></td>
				<td>
					${bizFsuMonitorinfo.fsuq}
				</td>
				<td>
					<fmt:formatDate value="${bizFsuMonitorinfo.fsudate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizFsuMonitorinfo.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${bizFsuMonitorinfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ele:bizFsuMonitorinfo:edit"><td>
    				<a href="${ctx}/ele/bizFsuMonitorinfo/form?id=${bizFsuMonitorinfo.id}">修改</a>
					<a href="${ctx}/ele/bizFsuMonitorinfo/delete?id=${bizFsuMonitorinfo.id}" onclick="return confirmx('确认要删除该FSU监控信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>