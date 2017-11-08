<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>表站对应信息管理</title>
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
		<li class="active"><a href="${ctx}/ele/bizSiteMeterinfo/">表站对应信息列表</a></li>
		<shiro:hasPermission name="ele:bizSiteMeterinfo:edit"><li><a href="${ctx}/ele/bizSiteMeterinfo/form">表站对应信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizSiteMeterinfo" action="${ctx}/ele/bizSiteMeterinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>站址编码：</label>
				<form:input path="amsitenum" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>电表户号：</label>
				<form:input path="amnum" htmlEscape="false" maxlength="30" class="input-medium"/>
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
				<th>电表户号</th>
				<th>起始日期</th>
				<th>备注</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>更新者</th>
				<shiro:hasPermission name="ele:bizSiteMeterinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizSiteMeterinfo">
			<tr>
				<td><a href="${ctx}/ele/bizSiteMeterinfo/form?id=${bizSiteMeterinfo.id}">
					${bizSiteMeterinfo.amsitenum}
				</a></td>
				<td>
					${bizSiteMeterinfo.amnum}
				</td>
				<td>
					<fmt:formatDate value="${bizSiteMeterinfo.amstartdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizSiteMeterinfo.amremarks}
				</td>
				<td>
					${bizSiteMeterinfo.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${bizSiteMeterinfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizSiteMeterinfo.updateBy.id}
				</td>
				<shiro:hasPermission name="ele:bizSiteMeterinfo:edit"><td>
    				<a href="${ctx}/ele/bizSiteMeterinfo/form?id=${bizSiteMeterinfo.id}">修改</a>
					<a href="${ctx}/ele/bizSiteMeterinfo/delete?id=${bizSiteMeterinfo.id}" onclick="return confirmx('确认要删除该表站对应信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>