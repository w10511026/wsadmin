<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转供回款信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出转供回款信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/ele/bizTransitReceiptinfo/export");
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
		<form id="importForm" action="${ctx}/ele/bizTransitReceiptinfo/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/ele/bizTransitReceiptinfo/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ele/bizTransitReceiptinfo/">转供回款信息列表</a></li>
		<shiro:hasPermission name="ele:bizTransitReceiptinfo:edit"><li><a href="${ctx}/ele/bizTransitReceiptinfo/form">转供回款信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizTransitReceiptinfo" action="${ctx}/ele/bizTransitReceiptinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>运营商：</label>
				<form:input path="tcoperator" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>用电户号：</label>
				<form:input path="tcaccnum" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>站点编码：</label>
				<form:input path="tcsitenum" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>缴费日期：</label>
				<input name="beginTcpaydate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizTransitReceiptinfo.beginTcpaydate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endTcpaydate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizTransitReceiptinfo.endTcpaydate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>回款日期：</label>
				<input name="beginTcrecdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizTransitReceiptinfo.beginTcrecdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endTcrecdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${bizTransitReceiptinfo.endTcrecdate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
				<th>运营商</th>
				<th>用电户号</th>
				<th>站点编码</th>
				<th>缴费日期</th>
				<th>回款日期</th>
				<th>上期日期</th>
				<th>本期日期</th>
				<th>起始读数</th>
				<th>截止读数</th>
				<th>老表起始读数</th>
				<th>老表截止读数</th>
				<th>倍率</th>
				<th>损耗</th>
				<th>计费电量</th>
				<th>电费单价</th>
				<th>票面金额</th>
				<th>票据类型</th>
				<th>铁塔开票类型</th>
				<th>税负因子</th>
				<th>移动电流</th>
				<th>电信电流</th>
				<th>联通电流</th>
				<th>分摊电费总额</th>
				<th>PUE</th>
				<th>核对金额</th>
				<th>回款金额</th>
				<th>本月分摊比例是否发生变动</th>
				<th>备注</th>
				<shiro:hasPermission name="ele:bizTransitReceiptinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizTransitReceiptinfo">
			<tr>
				<td><a href="${ctx}/ele/bizTransitReceiptinfo/form?id=${bizTransitReceiptinfo.id}">
					${bizTransitReceiptinfo.tcoperator}
				</a></td>
				<td>
					${bizTransitReceiptinfo.tcaccnum}
				</td>
				<td>
					${bizTransitReceiptinfo.tcsitenum}
				</td>
				<td>
					<fmt:formatDate value="${bizTransitReceiptinfo.tcpaydate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizTransitReceiptinfo.tcrecdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizTransitReceiptinfo.tcpredate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${bizTransitReceiptinfo.tccurdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${bizTransitReceiptinfo.tcpredisplay}
				</td>
				<td>
					${bizTransitReceiptinfo.tccurdisplay}
				</td>
				<td>
					${bizTransitReceiptinfo.tcprestartdisplay}
				</td>
				<td>
					${bizTransitReceiptinfo.tcpreenddisplay}
				</td>
				<td>
					${bizTransitReceiptinfo.tcrate}
				</td>
				<td>
					${bizTransitReceiptinfo.tcloss}
				</td>
				<td>
					${bizTransitReceiptinfo.tctotalq}
				</td>
				<td>
					${bizTransitReceiptinfo.tcpriceq}
				</td>
				<td>
					${bizTransitReceiptinfo.tcparc}
				</td>
				<td>
					${bizTransitReceiptinfo.tcpartype}
				</td>
				<td>
					${bizTransitReceiptinfo.tcpartypet}
				</td>
				<td>
					${bizTransitReceiptinfo.tctaxfactor}
				</td>
				<td>
					${bizTransitReceiptinfo.tccmq}
				</td>
				<td>
					${bizTransitReceiptinfo.tcctq}
				</td>
				<td>
					${bizTransitReceiptinfo.tccuq}
				</td>
				<td>
					${bizTransitReceiptinfo.tctotalc}
				</td>
				<td>
					${bizTransitReceiptinfo.tcpue}
				</td>
				<td>
					${bizTransitReceiptinfo.tccheck}
				</td>
				<td>
					${bizTransitReceiptinfo.tcc}
				</td>
				<td>
					${bizTransitReceiptinfo.tcapportchange}
				</td>
				<td>
					${bizTransitReceiptinfo.tcremarks}
				</td>
				<shiro:hasPermission name="ele:bizTransitReceiptinfo:edit"><td>
    				<a href="${ctx}/ele/bizTransitReceiptinfo/form?id=${bizTransitReceiptinfo.id}">修改</a>
					<a href="${ctx}/ele/bizTransitReceiptinfo/delete?id=${bizTransitReceiptinfo.id}" onclick="return confirmx('确认要删除该转供回款信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>