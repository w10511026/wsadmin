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
						$("#searchForm").attr("action","${ctx}/ele/bizOperateLog/export");
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
		<form id="importForm" action="${ctx}/ele/bizOperateLog/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/ele/bizOperateLog/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ele/bizOperateLog/">操作日志列表</a></li>
		<shiro:hasPermission name="ele:bizOperateLog:edit"><li><a href="${ctx}/ele/bizOperateLog/form">操作日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizOperateLog" action="${ctx}/ele/bizOperateLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>站址编码：</label>
				<form:input path="sisitenum" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>站址名称：</label>
				<form:input path="sisitename" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>操作方式：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('operate_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>备注：</label>
				<form:input path="content" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
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
				<th style="text-align: center">登录名</th>
				<th style="text-align: center">站址编码</th>
				<th style="text-align: center">站址名称</th>
				<th style="text-align: center">操作方式</th>
				<th style="text-align: center">备注</th>
				<th style="text-align: center">创建时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizOperateLog">
			<tr>
				<td hidden><span id="${bizOperateLog.id}ids">${bizOperateLog.id}</span></td>
				<td style="white-space:nowrap;overflow:hidden;word-break:keep-all;text-align: center"><a href="${ctx}/ele/bizOperateLog/form?id=${bizOperateLog.id}">
					${bizOperateLog.username}
				</a></td>
				<td style="white-space:nowrap;overflow:hidden;word-break:keep-all;text-align: center">
					${bizOperateLog.sisitenum}
				</td>
				<td style="white-space:nowrap;overflow:hidden;word-break:keep-all;text-align: center">
					${bizOperateLog.sisitename}
				</td>
				<td style="white-space:nowrap;overflow:hidden;word-break:keep-all;text-align: center">
					${fns:getDictLabel(bizOperateLog.type, '', '')}
				</td>
				<td style="white-space:nowrap;overflow:hidden;word-break:keep-all;text-align: center">
					${bizOperateLog.content}
				</td>
				<td style="white-space:nowrap;overflow:hidden;word-break:keep-all;text-align: center">
					<fmt:formatDate value="${bizOperateLog.createDate}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>