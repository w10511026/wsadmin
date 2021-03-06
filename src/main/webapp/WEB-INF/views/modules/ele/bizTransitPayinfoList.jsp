<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转供缴费信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出转供缴费信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/ele/bizTransitPayinfo/export");
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
		<form id="importForm" action="${ctx}/ele/bizTransitPayinfo/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/ele/bizTransitPayinfo/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ele/bizTransitPayinfo/">转供缴费信息列表</a></li>
		<shiro:hasPermission name="ele:bizTransitPayinfo:edit"><li><a href="${ctx}/ele/bizTransitPayinfo/form">转供缴费信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizTransitPayinfo" action="${ctx}/ele/bizTransitPayinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>户号：</label>
				<form:input path="tpaccnum" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
			<li><label>缴费日期：</label>
				<input name="beginTppaydate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizTransitPayinfo.beginTppaydate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endTppaydate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizTransitPayinfo.endTppaydate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>起始日期：</label>
				<input name="tpstartdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizTransitPayinfo.tpstartdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>截止日期：</label>
				<input name="tpenddate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizTransitPayinfo.tpenddate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>供电方名称：</label>
				<form:input path="tpsupplier" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input onclick="deletebatch('${ctx}/ele/bizTransitPayinfo/deletebatch')" class="btn btn-primary" type="button" value="删除"/>
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
				<th>户号</th>
				<th>缴费日期</th>
				<th>起始日期</th>
				<th>截止日期</th>
				<th>起始读数</th>
				<th>截止读数</th>
				<th>老表起始读数</th>
				<th>老表截止读数</th>
				<th>倍率</th>
				<th>损耗</th>
				<th>计费电量</th>
				<th>电费单价</th>
				<th>票面金额</th>
				<th>代开税金</th>
				<th>票据类型（代开）</th>
				<th>总金额</th>
				<th>供电方名称</th>
				<th>核销日期</th>
				<th>报账单号</th>
				<th>支付单号</th>
				<th>核销单号</th>
				<shiro:hasPermission name="ele:bizTransitPayinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizTransitPayinfo">
			<tr>
				<td hidden><span id="${bizTransitPayinfo.id}ids">${bizTransitPayinfo.id}</span></td>
				<td><a href="${ctx}/ele/bizTransitPayinfo/form?id=${bizTransitPayinfo.id}">
					${bizTransitPayinfo.tpaccnum}
				</a></td>
				<td>
					<fmt:formatDate value="${bizTransitPayinfo.tppaydate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizTransitPayinfo.tpstartdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizTransitPayinfo.tpenddate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizTransitPayinfo.tpstartdisplay}
				</td>
				<td>
					${bizTransitPayinfo.tpenddisplay}
				</td>
				<td>
					${bizTransitPayinfo.tpprestartdisplay}
				</td>
				<td>
					${bizTransitPayinfo.tpperenddisplay}
				</td>
				<td>
					${bizTransitPayinfo.tprate}
				</td>
				<td>
					${bizTransitPayinfo.tploss}
				</td>
				<td>
					${bizTransitPayinfo.tptotalq}
				</td>
				<td>
					${bizTransitPayinfo.tpbillprice}
				</td>
				<td>
					${bizTransitPayinfo.tpmainc}
				</td>
				<td>
					${bizTransitPayinfo.tpdktax}
				</td>
				<td>
					${bizTransitPayinfo.tpbilltype}
				</td>
				<td>
					${bizTransitPayinfo.tptotalc}
				</td>
				<td>
					${bizTransitPayinfo.tpsupplier}
				</td>
				<td>
					<fmt:formatDate value="${bizTransitPayinfo.tpdestdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizTransitPayinfo.tpbillnum}
				</td>
				<td>
					${bizTransitPayinfo.tppaynum}
				</td>
				<td>
					${bizTransitPayinfo.tpdestnum}
				</td>
				<shiro:hasPermission name="ele:bizTransitPayinfo:edit"><td>
    				<a href="${ctx}/ele/bizTransitPayinfo/form?id=${bizTransitPayinfo.id}">修改</a>
					<a href="${ctx}/ele/bizTransitPayinfo/delete?id=${bizTransitPayinfo.id}" onclick="return confirmx('确认要删除该转供缴费信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>