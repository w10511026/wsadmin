<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电流分摊信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出电流分摊信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/ele/bizElectricShareinfo/export");
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
		<form id="importForm" action="${ctx}/ele/bizElectricShareinfo/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/ele/bizElectricShareinfo/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ele/bizElectricShareinfo/">电流分摊信息列表</a></li>
		<shiro:hasPermission name="ele:bizElectricShareinfo:edit"><li><a href="${ctx}/ele/bizElectricShareinfo/form">电流分摊信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizElectricShareinfo" action="${ctx}/ele/bizElectricShareinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>站址编码：</label>
				<form:input path="rositenum" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>宿主站站址编码：</label>
				<form:input path="rohistnum" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input onclick="deletebatch('${ctx}/ele/bizElectricShareinfo/deletebatch')" class="btn btn-primary" type="button" value="删除"/>
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
				<th>移动电流</th>
				<th>电信电流</th>
				<th>联通电流</th>
				<th>宿主站站址编码</th>
				<th>更新日期</th>
				<th>备注</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>更新者</th>
				<shiro:hasPermission name="ele:bizElectricShareinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizElectricShareinfo">
			<tr>
				<td hidden><span id="${bizElectricShareinfo.id}ids">${bizElectricShareinfo.id}</span></td>
				<td><a href="${ctx}/ele/bizElectricShareinfo/form?id=${bizElectricShareinfo.id}">
					${bizElectricShareinfo.rositenum}
				</a></td>
				<td>
					${bizElectricShareinfo.rocmq}
				</td>
				<td>
					${bizElectricShareinfo.roctq}
				</td>
				<td>
					${bizElectricShareinfo.rocuq}
				</td>
				<td>
					${bizElectricShareinfo.rohistnum}
				</td>
				<td>
					<fmt:formatDate value="${bizElectricShareinfo.roupdatedate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizElectricShareinfo.roremarks}
				</td>
				<td>
					${bizElectricShareinfo.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${bizElectricShareinfo.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizElectricShareinfo.updateBy.id}
				</td>
				<shiro:hasPermission name="ele:bizElectricShareinfo:edit"><td>
    				<a href="${ctx}/ele/bizElectricShareinfo/form?id=${bizElectricShareinfo.id}">修改</a>
					<a href="${ctx}/ele/bizElectricShareinfo/delete?id=${bizElectricShareinfo.id}" onclick="return confirmx('确认要删除该电流分摊信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>