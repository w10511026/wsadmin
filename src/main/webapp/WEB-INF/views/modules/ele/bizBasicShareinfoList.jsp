<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基础电流分摊信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出基础电流分摊信息吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/ele/bizBasicShareinfo/export");
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
		<form id="importForm" action="${ctx}/ele/bizBasicShareinfo/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/ele/bizBasicShareinfo/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ele/bizBasicShareinfo/">基础电流分摊信息列表</a></li>
		<shiro:hasPermission name="ele:bizBasicShareinfo:edit"><li><a href="${ctx}/ele/bizBasicShareinfo/form">基础电流分摊信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="bizBasicShareinfo" action="${ctx}/ele/bizBasicShareinfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>站址编码：</label>
				<form:input path="sisitenum" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>基站名称：</label>
				<form:input path="sisitename" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>宿主站编码：</label>
				<form:input path="rohistnum" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>宿主站站名：</label>
				<form:input path="rohistname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input onclick="deletebatch('${ctx}/ele/bizBasicShareinfo/deletebatch')" class="btn btn-primary" type="button" value="删除"/>
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
				<th>原产权</th>
				<th>区县</th>
				<th>基站名称</th>
				<th>移动起租</th>
				<th>电信起租</th>
				<th>联通起租</th>
				<th>维护状态</th>
				<th>机房类型</th>
				<th>是否保留站</th>
				<th>供电方式</th>
				<th>引电方式</th>
				<th>宿主站编码</th>
				<th>宿主站站名</th>
				<th>电表户号</th>
				<th>票据类型</th>
				<th>外市电信息备注</th>
				<th>单站移动</th>
				<th>单站电信</th>
				<th>单站联通</th>
				<th>单站总电流</th>
				<th>共表移动</th>
				<th>共表电信</th>
				<th>共表联通</th>
				<th>共表总电流</th>
				<th>一表多站信息</th>
				<th>偷电搭电信息</th>
				<th>其他信息</th>
				<shiro:hasPermission name="ele:bizBasicShareinfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bizBasicShareinfo">
			<tr>
				<td hidden><span id="${bizBasicShareinfo.id}ids">${bizBasicShareinfo.id}</span></td>
				<td><a href="${ctx}/ele/bizBasicShareinfo/form?id=${bizBasicShareinfo.id}">
					${bizBasicShareinfo.sisitenum}
				</a></td>
				<td>
					${bizBasicShareinfo.sipropertyunit}
				</td>
				<td>
					${bizBasicShareinfo.sidistrict}
				</td>
				<td>
					${bizBasicShareinfo.sisitename}
				</td>
				<td>
					${bizBasicShareinfo.sicmq}
				</td>
				<td>
					${bizBasicShareinfo.sictq}
				</td>
				<td>
					${bizBasicShareinfo.sicuq}
				</td>
				<td>
					${bizBasicShareinfo.simaintainstatus}
				</td>
				<td>
					${bizBasicShareinfo.siroomstyle}
				</td>
				<td>
					${bizBasicShareinfo.siisretain}
				</td>
				<td>
					${bizBasicShareinfo.supplytype}
				</td>
				<td>
					${bizBasicShareinfo.leadtype}
				</td>
				<td>
					${bizBasicShareinfo.rohistnum}
				</td>
				<td>
					${bizBasicShareinfo.rohistname}
				</td>
				<td>
					${bizBasicShareinfo.sinum}
				</td>
				<td>
					${bizBasicShareinfo.sibilltype}
				</td>
				<td>
					${bizBasicShareinfo.outcityeleremarks}
				</td>
				<td>
					${bizBasicShareinfo.singlestatmq}
				</td>
				<td>
					${bizBasicShareinfo.singlestattq}
				</td>
				<td>
					${bizBasicShareinfo.singlestatuq}
				</td>
				<td>
					${bizBasicShareinfo.singletotal}
				</td>
				<td>
					${bizBasicShareinfo.sharedmetermq}
				</td>
				<td>
					${bizBasicShareinfo.sharedmetertq}
				</td>
				<td>
					${bizBasicShareinfo.sharedmeteruq}
				</td>
				<td>
					${bizBasicShareinfo.sharedmetertotal}
				</td>
				<td>
					${bizBasicShareinfo.singletomanystatinfo}
				</td>
				<td>
					${bizBasicShareinfo.stealeleinfo}
				</td>
				<td>
					${bizBasicShareinfo.otherinfo}
				</td>
				<shiro:hasPermission name="ele:bizBasicShareinfo:edit"><td>
    				<a href="${ctx}/ele/bizBasicShareinfo/form?id=${bizBasicShareinfo.id}">修改</a>
					<a href="${ctx}/ele/bizBasicShareinfo/delete?id=${bizBasicShareinfo.id}" onclick="return confirmx('确认要删除该基础电流分摊信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>