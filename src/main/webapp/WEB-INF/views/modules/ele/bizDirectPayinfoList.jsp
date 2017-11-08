<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>直供缴费信息管理</title>
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
		<li class="active"><a href="${ctx}/ele/bizDirectPayinfo/">直供缴费信息列表</a></li>
		<shiro:hasPermission name="ele:bizDirectPayinfo:edit"><li><a href="${ctx}/ele/bizDirectPayinfo/form">直供缴费信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizDirectPayinfo" action="${ctx}/ele/bizDirectPayinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>票据日期：</label>
				<input name="spbilldate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizDirectPayinfo.spbilldate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>起始日期：</label>
				<input name="spstartdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizDirectPayinfo.spstartdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>截止日期：</label>
				<input name="spenddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizDirectPayinfo.spenddate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>票据日期</th>
				<th>户号</th>
				<th>起始日期</th>
				<th>截止日期</th>
				<th>起始读数</th>
				<th>截止读数</th>
				<th>老表起始读数</th>
				<th>老表截止读数</th>
				<th>倍率</th>
				<th>损耗</th>
				<th>计费电量</th>
				<th>票面金额</th>
				<th>票据类型</th>
				<shiro:hasPermission name="ele:bizDirectPayinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizDirectPayinfo">
			<tr>
				<td><a href="${ctx}/ele/bizDirectPayinfo/form?id=${bizDirectPayinfo.id}">
					<fmt:formatDate value="${bizDirectPayinfo.spbilldate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${bizDirectPayinfo.spaccnum}
				</td>
				<td>
					<fmt:formatDate value="${bizDirectPayinfo.spstartdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${bizDirectPayinfo.spenddate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${bizDirectPayinfo.spstartdisplay}
				</td>
				<td>
					${bizDirectPayinfo.spenddisplay}
				</td>
				<td>
					${bizDirectPayinfo.spprestartdisplay}
				</td>
				<td>
					${bizDirectPayinfo.sppreenddisplay}
				</td>
				<td>
					${bizDirectPayinfo.sprate}
				</td>
				<td>
					${bizDirectPayinfo.sploss}
				</td>
				<td>
					${bizDirectPayinfo.sptotalq}
				</td>
				<td>
					${bizDirectPayinfo.sptotalc}
				</td>
				<td>
					${bizDirectPayinfo.spbilltype}
				</td>
				<shiro:hasPermission name="ele:bizDirectPayinfo:edit"><td>
    				<a href="${ctx}/ele/bizDirectPayinfo/form?id=${bizDirectPayinfo.id}">修改</a>
					<a href="${ctx}/ele/bizDirectPayinfo/delete?id=${bizDirectPayinfo.id}" onclick="return confirmx('确认要删除该直供缴费信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>