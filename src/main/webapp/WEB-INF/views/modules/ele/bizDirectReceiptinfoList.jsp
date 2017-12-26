<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>直供回款信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出直供回款信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/ele/bizDirectReceiptinfo/export");
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
		<form id="importForm" action="${ctx}/ele/bizDirectReceiptinfo/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/ele/bizDirectReceiptinfo/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ele/bizDirectReceiptinfo/">直供回款信息列表</a></li>
		<shiro:hasPermission name="ele:bizDirectReceiptinfo:edit"><li><a href="${ctx}/ele/bizDirectReceiptinfo/form">直供回款信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizDirectReceiptinfo" action="${ctx}/ele/bizDirectReceiptinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用电户号：</label>
				<form:input path="scaccnum" htmlEscape="false" maxlength="25" class="input-medium"/>
			</li>
			<li><label>运营商：</label>
				<form:input path="scoperator" htmlEscape="false" maxlength="6" class="input-medium"/>
			</li>
			<li><label>站点编码：</label>
				<form:input path="scsitenum" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>回款日期：</label>
				<input name="beginSprecdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizDirectReceiptinfo.beginSprecdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endSprecdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizDirectReceiptinfo.endSprecdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>账期：</label>
				<form:input path="scbilldate" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input onclick="deletebatch('${ctx}/ele/bizDirectReceiptinfo/deletebatch')" class="btn btn-primary" type="button" value="删除"/>
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
				<th>用电户号</th>
				<th>运营商</th>
				<th>站点编码</th>
				<th>回款日期</th>
				<th>账期</th>
				<th>上期日期</th>
				<th>本期日期</th>
				<th>上期示度</th>
				<th>本期示度</th>
				<th>老表起始读数</th>
				<th>老表截止读数</th>
				<th>倍率</th>
				<th>损耗</th>
				<th>计费电量</th>
				<th>电费单价</th>
				<th>票面金额</th>
				<th>票据类型</th>
				<th>铁塔开票类型</th>
				<th>移动电流</th>
				<th>电信电流</th>
				<th>联通电流</th>
				<th>分摊电费总额</th>
				<th>PUE</th>
				<th>核对金额</th>
				<th>回款金额</th>
				<th>本月分摊比例是否发生变动</th>
				<th>铁塔起始日期</th>
				<th>铁塔截止日期</th>
				<th>备注</th>
				<shiro:hasPermission name="ele:bizDirectReceiptinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizDirectReceiptinfo">
			<tr>
				<td hidden><span id="${bizDirectReceiptinfo.id}ids">${bizDirectReceiptinfo.id}</span></td>
				<td><a href="${ctx}/ele/bizDirectReceiptinfo/form?id=${bizDirectReceiptinfo.id}">
					${bizDirectReceiptinfo.scaccnum}
				</a></td>
				<td>
					${bizDirectReceiptinfo.scoperator}
				</td>
				<td>
					${bizDirectReceiptinfo.scsitenum}
				</td>
				<td>
					<fmt:formatDate value="${bizDirectReceiptinfo.sprecdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizDirectReceiptinfo.scbilldate}
				</td>
				<td>
					<fmt:formatDate value="${bizDirectReceiptinfo.scpredate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizDirectReceiptinfo.sccurdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizDirectReceiptinfo.scpredisplay}
				</td>
				<td>
					${bizDirectReceiptinfo.sccurdisplay}
				</td>
				<td>
					${bizDirectReceiptinfo.scprestartdisplay}
				</td>
				<td>
					${bizDirectReceiptinfo.scpreenddisplay}
				</td>
				<td>
					${bizDirectReceiptinfo.scrate}
				</td>
				<td>
					${bizDirectReceiptinfo.scloss}
				</td>
				<td>
					${bizDirectReceiptinfo.sctotalq}
				</td>
				<td>
					${bizDirectReceiptinfo.scprice}
				</td>
				<td>
					${bizDirectReceiptinfo.scparc}
				</td>
				<td>
					${bizDirectReceiptinfo.scpartype}
				</td>
				<td>
					${bizDirectReceiptinfo.scpartypet}
				</td>
				<td>
					${bizDirectReceiptinfo.sccmq}
				</td>
				<td>
					${bizDirectReceiptinfo.scctq}
				</td>
				<td>
					${bizDirectReceiptinfo.sccuq}
				</td>
				<td>
					${bizDirectReceiptinfo.sctotalc}
				</td>
				<td>
					${bizDirectReceiptinfo.scpue}
				</td>
				<td>
					${bizDirectReceiptinfo.sccheck}
				</td>
				<td>
					${bizDirectReceiptinfo.scc}
				</td>
				<td>
					${bizDirectReceiptinfo.scapportchange}
				</td>
				<td>
					<fmt:formatDate value="${bizDirectReceiptinfo.spttstartdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizDirectReceiptinfo.spttenddate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizDirectReceiptinfo.scremarks}
				</td>
				<shiro:hasPermission name="ele:bizDirectReceiptinfo:edit"><td>
    				<a href="${ctx}/ele/bizDirectReceiptinfo/form?id=${bizDirectReceiptinfo.id}">修改</a>
					<a href="${ctx}/ele/bizDirectReceiptinfo/delete?id=${bizDirectReceiptinfo.id}" onclick="return confirmx('确认要删除该直供回款信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>