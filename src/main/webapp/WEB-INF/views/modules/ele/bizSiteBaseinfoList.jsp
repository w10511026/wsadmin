<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>站址基础信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出站址基础信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/ele/bizSiteBaseinfo/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true},
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			initTableCheckbox();
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
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/ele/bizSiteBaseinfo/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/ele/bizSiteBaseinfo/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ele/bizSiteBaseinfo/">站址基础信息列表</a></li>
		<shiro:hasPermission name="ele:bizSiteBaseinfo:edit"><li><a href="${ctx}/ele/bizSiteBaseinfo/form">站址基础信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizSiteBaseinfo" action="${ctx}/ele/bizSiteBaseinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>站址编码：</label>
				<form:input path="sisitenum" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>区县：</label>
				<form:input path="sidistrict" htmlEscape="false" maxlength="6" class="input-medium"/>
			</li>
			<li><label>站址名称：</label>
				<form:input path="sisitename" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input onclick="deletebatch('${ctx}/ele/bizSiteBaseinfo/deletebatch')" class="btn btn-primary" type="button" value="删除"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
		<thead>
			<tr>
				<th hidden></th>
				<th>站址编码</th>
				<th>区县</th>
				<th>站址名称</th>
				<th>原产权单位</th>
				<th>机房类型</th>
				<th>保留站信息</th>
				<th>移动起租日期</th>
				<th>电信起租日期</th>
				<th>联通起租日期</th>
				<shiro:hasPermission name="ele:bizSiteBaseinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizSiteBaseinfo">
			<tr>
				<td hidden><span id="${bizSiteBaseinfo.id}ids">${bizSiteBaseinfo.id}</span></td>
				<td><a href="${ctx}/ele/bizSiteBaseinfo/form?id=${bizSiteBaseinfo.id}">
					${bizSiteBaseinfo.sisitenum}
				</a></td>
				<td>
					${bizSiteBaseinfo.sidistrict}
				</td>
				<td>
					${bizSiteBaseinfo.sisitename}
				</td>
				<td>
					${bizSiteBaseinfo.sipropertyunit}
				</td>
				<td>
					${bizSiteBaseinfo.siroomstyle}
				</td>
				<td>
					${bizSiteBaseinfo.siretain}
				</td>
				<td>
					<fmt:formatDate value="${bizSiteBaseinfo.sicmq}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizSiteBaseinfo.sictq}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizSiteBaseinfo.sicuq}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="ele:bizSiteBaseinfo:edit"><td>
    				<a href="${ctx}/ele/bizSiteBaseinfo/form?id=${bizSiteBaseinfo.id}">修改</a>
					<a href="${ctx}/ele/bizSiteBaseinfo/delete?id=${bizSiteBaseinfo.id}" onclick="return confirmx('确认要删除该站址基础信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>