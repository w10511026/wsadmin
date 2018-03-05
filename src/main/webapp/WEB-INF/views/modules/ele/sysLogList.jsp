<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>操作日志管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出操作日志吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/ele/sysLog/export");
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
		<form id="importForm" action="${ctx}/ele/sysLog/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/ele/sysLog/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ele/sysLog/">操作日志列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sysLog" action="${ctx}/ele/sysLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>日志标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input onclick="deletebatch('${ctx}/ele/sysLog/deletebatch')" class="btn btn-primary" type="button" value="删除"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed table-hover">
		<thead>
			<tr>
				<th hidden></th>
				<th style="text-align: center">日志标题</th>
				<th style="text-align: center">操作人</th>
				<th style="text-align: center">操作时间</th>
				<th style="text-align: center">操作IP地址</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysLog">
			<tr>
				<td hidden><span id="${sysLog.id}ids">${sysLog.id}</span></td>
				<td style="white-space:nowrap;overflow:hidden;word-break:keep-all;text-align: center"><a href="${ctx}/ele/sysLog/form?id=${sysLog.id}">
					${sysLog.title}
				</a></td>
				<td style="white-space:nowrap;overflow:hidden;word-break:keep-all;text-align: center">
					${sysLog.createBy.id}
				</td>
				<td style="white-space:nowrap;overflow:hidden;word-break:keep-all;text-align: center">
					<fmt:formatDate value="${sysLog.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td style="white-space:nowrap;overflow:hidden;word-break:keep-all;text-align: center">
					${sysLog.remoteAddr}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
<div class="pagination">${page}</div>
</body>
</html>