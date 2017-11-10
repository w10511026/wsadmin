<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转供抄表信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出转供抄表信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/ele/bizTransitCopymeterinfo/export");
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
		<form id="importForm" action="${ctx}/ele/bizTransitCopymeterinfo/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/ele/bizTransitCopymeterinfo/import/template">下载模板</a>
		</form>
	</div>
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
					value="<fmt:formatDate value="${bizTransitCopymeterinfo.beginTddate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endTddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizTransitCopymeterinfo.endTddate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input onclick="deletebatch('${ctx}/ele/bizTransitCopymeterinfo/deletebatch')" class="btn btn-primary" type="button" value="删除"/>
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
				<td hidden><span id="${bizTransitCopymeterinfo.id}ids">${bizTransitCopymeterinfo.id}</span></td>
				<td><a href="${ctx}/ele/bizTransitCopymeterinfo/form?id=${bizTransitCopymeterinfo.id}">
					${bizTransitCopymeterinfo.tdsitenum}
				</a></td>
				<td>
					<fmt:formatDate value="${bizTransitCopymeterinfo.tddate}" pattern="yyyy-MM-dd"/>
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
					<fmt:formatDate value="${bizTransitCopymeterinfo.createDate}" pattern="yyyy-MM-dd"/>
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