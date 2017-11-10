<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>直供缴费信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出直供缴费信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/ele/bizDirectPayinfo/export");
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
		<form id="importForm" action="${ctx}/ele/bizDirectPayinfo/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/ele/bizDirectPayinfo/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ele/bizDirectPayinfo/">直供缴费信息列表</a></li>
		<shiro:hasPermission name="ele:bizDirectPayinfo:edit"><li><a href="${ctx}/ele/bizDirectPayinfo/form">直供缴费信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizDirectPayinfo" action="${ctx}/ele/bizDirectPayinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>账期：</label>
				<form:input path="spbilldate" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>户号：</label>
				<form:input path="spaccnum" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>铁塔起始日期：</label>
				<input name="spttstartdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizDirectPayinfo.spttstartdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>铁塔截止日期：</label>
				<input name="spttenddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizDirectPayinfo.spttenddate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input onclick="deletebatch('${ctx}/ele/bizDirectPayinfo/deletebatch')" class="btn btn-primary" type="button" value="删除"/>
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
				<th>账期</th>
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
				<th>铁塔起始日期</th>
				<th>铁塔截止日期</th>
				<shiro:hasPermission name="ele:bizDirectPayinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizDirectPayinfo">
			<tr>
				<td hidden><span id="${bizDirectPayinfo.id}ids">${bizDirectPayinfo.id}</span></td>
				<td><a href="${ctx}/ele/bizDirectPayinfo/form?id=${bizDirectPayinfo.id}">
					${bizDirectPayinfo.spbilldate}
				</a></td>
				<td>
					${bizDirectPayinfo.spaccnum}
				</td>
				<td>
					<fmt:formatDate value="${bizDirectPayinfo.spstartdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizDirectPayinfo.spenddate}" pattern="yyyy-MM-dd"/>
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
				<td>
					<fmt:formatDate value="${bizDirectPayinfo.spttstartdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizDirectPayinfo.spttenddate}" pattern="yyyy-MM-dd"/>
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