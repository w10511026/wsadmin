<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代维巡检信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出代维巡检信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/ele/bizReplaceCheckinfo/export");
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
		<form id="importForm" action="${ctx}/ele/bizReplaceCheckinfo/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/ele/bizReplaceCheckinfo/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ele/bizReplaceCheckinfo/">代维巡检信息列表</a></li>
		<shiro:hasPermission name="ele:bizReplaceCheckinfo:edit"><li><a href="${ctx}/ele/bizReplaceCheckinfo/form">代维巡检信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizReplaceCheckinfo" action="${ctx}/ele/bizReplaceCheckinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>站址编码：</label>
				<form:input path="inspsitenum" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>巡检日期：</label>
				<input name="beginInspdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizReplaceCheckinfo.beginInspdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endInspdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizReplaceCheckinfo.endInspdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>巡检人员：</label>
				<form:input path="insppeople" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input onclick="deletebatch('${ctx}/ele/bizReplaceCheckinfo/deletebatch')" class="btn btn-primary" type="button" value="删除"/>
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
				<th>巡检日期</th>
				<th>抄表读数</th>
				<th>移动电流</th>
				<th>电信电流</th>
				<th>联通电流</th>
				<th>巡检人员</th>
				<th>备注</th>
				<th>创建者</th>
				<th>创建时间</th>
				<shiro:hasPermission name="ele:bizReplaceCheckinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizReplaceCheckinfo">
			<tr>
				<td hidden><span id="${bizReplaceCheckinfo.id}ids">${bizReplaceCheckinfo.id}</span></td>
				<td><a href="${ctx}/ele/bizReplaceCheckinfo/form?id=${bizReplaceCheckinfo.id}">
					${bizReplaceCheckinfo.inspsitenum}
				</a></td>
				<td>
					<fmt:formatDate value="${bizReplaceCheckinfo.inspdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizReplaceCheckinfo.inspdisplay}
				</td>
				<td>
					${bizReplaceCheckinfo.inspcmq}
				</td>
				<td>
					${bizReplaceCheckinfo.inspctq}
				</td>
				<td>
					${bizReplaceCheckinfo.inspcuq}
				</td>
				<td>
					${bizReplaceCheckinfo.insppeople}
				</td>
				<td>
					${bizReplaceCheckinfo.inspremarks}
				</td>
				<td>
					${bizReplaceCheckinfo.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${bizReplaceCheckinfo.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="ele:bizReplaceCheckinfo:edit"><td>
    				<a href="${ctx}/ele/bizReplaceCheckinfo/form?id=${bizReplaceCheckinfo.id}">修改</a>
					<a href="${ctx}/ele/bizReplaceCheckinfo/delete?id=${bizReplaceCheckinfo.id}" onclick="return confirmx('确认要删除该代维巡检信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>