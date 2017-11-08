<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转供抄表信息管理</title>
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
		<li class="active"><a href="${ctx}/ele/bizTransitCopymeterinfo/">转供抄表信息列表</a></li>
		<shiro:hasPermission name="ele:bizTransitCopymeterinfo:edit"><li><a href="${ctx}/ele/bizTransitCopymeterinfo/form">转供抄表信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizTransitCopymeterinfo" action="${ctx}/ele/bizTransitCopymeterinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>站址编码：</label>
				<form:input path="tdsitenum" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>抄表日期：</label>
				<input name="beginTddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizTransitCopymeterinfo.beginTddate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endTddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizTransitCopymeterinfo.endTddate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>抄表读数：</label>
				<form:input path="tddisplay" htmlEscape="false" class="input-medium"/>
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
				<th>抄表日期</th>
				<th>抄表读数</th>
				<th>抄表人</th>
				<th>抄表读数</th>
				<th>创建者</th>
				<th>创建时间</th>
				<shiro:hasPermission name="ele:bizTransitCopymeterinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizTransitCopymeterinfo">
			<tr>
				<td><a href="${ctx}/ele/bizTransitCopymeterinfo/form?id=${bizTransitCopymeterinfo.id}">
					${bizTransitCopymeterinfo.tdsitenum}
				</a></td>
				<td>
					<fmt:formatDate value="${bizTransitCopymeterinfo.tddate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizTransitCopymeterinfo.tddisplay}
				</td>
				<td>
					${bizTransitCopymeterinfo.tdperson}
				</td>
				<td>
					${bizTransitCopymeterinfo.tdremark}
				</td>
				<td>
					${bizTransitCopymeterinfo.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${bizTransitCopymeterinfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="ele:bizTransitCopymeterinfo:edit"><td>
    				<a href="${ctx}/ele/bizTransitCopymeterinfo/form?id=${bizTransitCopymeterinfo.id}">修改</a>
					<a href="${ctx}/ele/bizTransitCopymeterinfo/delete?id=${bizTransitCopymeterinfo.id}" onclick="return confirmx('确认要删除该转供抄表信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>