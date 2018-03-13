package com.thinkgem.jeesite.modules.ele.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import java.util.List;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.ele.util.InitImportData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.dto.AjaxMsg;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ele.entity.BizOperateLog;
import com.thinkgem.jeesite.modules.ele.service.BizOperateLogService;

/**
 * 操作日志Controller
 * @author 操作日志
 * @version 2018-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizOperateLog")
public class BizOperateLogController extends BaseController {

	@Autowired
	private BizOperateLogService bizOperateLogService;
	
	@ModelAttribute
	public BizOperateLog get(@RequestParam(required=false) String id) {
		BizOperateLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizOperateLogService.get(id);
		}
		if (entity == null){
			entity = new BizOperateLog();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizOperateLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizOperateLog bizOperateLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizOperateLog> page = bizOperateLogService.findPage(new Page<BizOperateLog>(request, response), bizOperateLog); 
		model.addAttribute("page", page);
		return "modules/ele/bizOperateLogList";
	}

	@RequiresPermissions("ele:bizOperateLog:view")
	@RequestMapping(value = "form")
	public String form(BizOperateLog bizOperateLog, Model model) {
		model.addAttribute("bizOperateLog", bizOperateLog);
		return "modules/ele/bizOperateLogForm";
	}

	@RequiresPermissions("ele:bizOperateLog:edit")
	@RequestMapping(value = "save")
	public String save(BizOperateLog bizOperateLog, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, bizOperateLog)){
				return form(bizOperateLog, model);
			}
			bizOperateLogService.save(bizOperateLog);
			addMessage(redirectAttributes, "保存操作日志成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "导入失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizOperateLog/?repage";
	}
	
	@RequiresPermissions("ele:bizOperateLog:edit")
	@RequestMapping(value = "delete")
	public String delete(BizOperateLog bizOperateLog, RedirectAttributes redirectAttributes) {
		try {
			bizOperateLogService.delete(bizOperateLog);
			addMessage(redirectAttributes, "删除操作日志成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "删除失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizOperateLog/?repage";
	}

	@RequiresPermissions("ele:bizOperateLog:edit")
	@RequestMapping(value = "deletebatch")
	@ResponseBody
	public AjaxMsg deleteBatch(@RequestParam("ids[]")List<String> ids) {
		AjaxMsg ajaxMsg = new AjaxMsg(String.valueOf(HttpStatus.OK), "删除操作日志成功");
		try {
			for (String id : ids) {
				BizOperateLog bizOperateLog = new BizOperateLog();
				bizOperateLog.setId(id);
				bizOperateLogService.delete(bizOperateLog);
			}
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "删除失败：数据违反主外键约束！";
			}
			ajaxMsg = new AjaxMsg(String.valueOf(HttpStatus.BAD_REQUEST), exceptionMsg);
		}
		return ajaxMsg;
	}

	@RequiresPermissions("ele:bizOperateLog:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(BizOperateLog bizOperateLog, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "操作日志"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			List<BizOperateLog> list = bizOperateLogService.findList(bizOperateLog);
			new ExportExcel("操作日志", BizOperateLog.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出操作日志失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizOperateLog/?repage";
	}

	@RequiresPermissions("ele:bizOperateLog:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/ele/bizOperateLog/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BizOperateLog> list = ei.getDataList(BizOperateLog.class);
			for (BizOperateLog bizOperateLog : list) {
				try {
					if (true) {
						BeanValidators.validateWithException(validator, bizOperateLog);
						bizOperateLogService.save(bizOperateLog);
						successNum++;
					} else {
						failureMsg.append("<br/>");
						failureNum++;
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message+"; ");
						failureNum++;
					}
				} catch (Exception ex) {
					String exceptionMsg = ex.getMessage();
					if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
						exceptionMsg = "导入数据违反主外键约束！";
					}
					failureMsg.append("导入失败：" + InitImportData.getPrimaryValue(bizOperateLog) + "=>" + exceptionMsg +"; ");
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizOperateLog/?repage";
	}

	@RequiresPermissions("ele:bizOperateLog:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "操作日志导入模板.xlsx";
			List<BizOperateLog> list = Lists.newArrayList();
			list.add((BizOperateLog) InitImportData.getImportData(new BizOperateLog()));
			new ExportExcel("操作日志", BizOperateLog.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "操作日志导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizOperateLog/?repage";
	}
}