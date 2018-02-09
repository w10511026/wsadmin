<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基础电流分摊信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ele/bizBasicShareinfo/">基础电流分摊信息列表</a></li>
		<li class="active"><a href="${ctx}/ele/bizBasicShareinfo/form?id=${bizBasicShareinfo.id}">基础电流分摊信息<shiro:hasPermission name="ele:bizBasicShareinfo:edit">${not empty bizBasicShareinfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ele:bizBasicShareinfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="bizBasicShareinfo" action="${ctx}/ele/bizBasicShareinfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">站址编码：</label>
			<div class="controls">
				<form:input path="sisitenum" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">原产权：</label>
			<div class="controls">
				<form:input path="sipropertyunit" htmlEscape="false" maxlength="10" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区县：</label>
			<div class="controls">
				<form:input path="sidistrict" htmlEscape="false" maxlength="6" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">基站名称：</label>
			<div class="controls">
				<form:input path="sisitename" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">移动起租：</label>
			<div class="controls">
				<form:input path="sicmq" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电信起租：</label>
			<div class="controls">
				<form:input path="sictq" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联通起租：</label>
			<div class="controls">
				<form:input path="sicuq" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维护状态：</label>
			<div class="controls">
				<form:input path="simaintainstatus" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机房类型：</label>
			<div class="controls">
				<form:input path="siroomstyle" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否保留站：</label>
			<div class="controls">
				<form:input path="siisretain" htmlEscape="false" maxlength="6" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供电方式：</label>
			<div class="controls">
				<form:input path="supplytype" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">引电方式：</label>
			<div class="controls">
				<form:input path="leadtype" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">宿主站编码：</label>
			<div class="controls">
				<form:input path="rohistnum" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">宿主站站名：</label>
			<div class="controls">
				<form:input path="rohistname" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电表户号：</label>
			<div class="controls">
				<form:input path="sinum" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">票据类型：</label>
			<div class="controls">
				<form:input path="sibilltype" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">外市电信息备注：</label>
			<div class="controls">
				<form:input path="outcityeleremarks" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单站移动：</label>
			<div class="controls">
				<form:input path="singlestatmq" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单站电信：</label>
			<div class="controls">
				<form:input path="singlestattq" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单站联通：</label>
			<div class="controls">
				<form:input path="singlestatuq" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单站总电流：</label>
			<div class="controls">
				<form:input path="singletotal" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">共表移动：</label>
			<div class="controls">
				<form:input path="sharedmetermq" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">共表电信：</label>
			<div class="controls">
				<form:input path="sharedmetertq" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">共表联通：</label>
			<div class="controls">
				<form:input path="sharedmeteruq" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">共表总电流：</label>
			<div class="controls">
				<form:input path="sharedmetertotal" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">一表多站信息：</label>
			<div class="controls">
				<form:input path="singletomanystatinfo" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">偷电搭电信息：</label>
			<div class="controls">
				<form:input path="stealeleinfo" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他信息：</label>
			<div class="controls">
				<form:input path="otherinfo" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ele:bizBasicShareinfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>