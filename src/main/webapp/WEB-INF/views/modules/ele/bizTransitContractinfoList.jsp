<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转供合同信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出转供合同信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/ele/bizTransitContractinfo/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true},
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
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
		<form id="importForm" action="${ctx}/ele/bizTransitContractinfo/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/ele/bizTransitContractinfo/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ele/bizTransitContractinfo/">转供合同信息列表</a></li>
		<shiro:hasPermission name="ele:bizTransitContractinfo:edit"><li><a href="${ctx}/ele/bizTransitContractinfo/form">转供合同信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizTransitContractinfo" action="${ctx}/ele/bizTransitContractinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>站址编码：</label>
				<form:input path="tconsitename" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>合同编码：</label>
				<form:input path="tconsitenum" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>供电方名称：</label>
				<form:input path="tconpa" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>合同单价：</label>
				<form:input path="tconprice" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>站址编码</th>
				<th>合同编码</th>
				<th>供电方名称</th>
				<th>合同起始日期</th>
				<th>合同截止日期</th>
				<th>合同单价</th>
				<th>票据类型</th>
				<th>备注</th>
				<th>创建者</th>
				<th>创建时间</th>
				<shiro:hasPermission name="ele:bizTransitContractinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizTransitContractinfo">
			<tr>
				<td><a href="${ctx}/ele/bizTransitContractinfo/form?id=${bizTransitContractinfo.id}">
					${bizTransitContractinfo.tconsitename}
				</a></td>
				<td>
					${bizTransitContractinfo.tconsitenum}
				</td>
				<td>
					${bizTransitContractinfo.tconpa}
				</td>
				<td>
					<fmt:formatDate value="${bizTransitContractinfo.tconstartdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizTransitContractinfo.tconenddate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizTransitContractinfo.tconprice}
				</td>
				<td>
					${bizTransitContractinfo.tcontype}
				</td>
				<td>
					${bizTransitContractinfo.tcremark}
				</td>
				<td>
					${bizTransitContractinfo.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${bizTransitContractinfo.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="ele:bizTransitContractinfo:edit"><td>
    				<a href="${ctx}/ele/bizTransitContractinfo/form?id=${bizTransitContractinfo.id}">修改</a>
					<a href="${ctx}/ele/bizTransitContractinfo/delete?id=${bizTransitContractinfo.id}" onclick="return confirmx('确认要删除该转供合同信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>