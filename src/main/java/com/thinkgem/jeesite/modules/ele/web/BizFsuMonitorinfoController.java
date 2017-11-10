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
import com.thinkgem.jeesite.modules.ele.entity.BizFsuMonitorinfo;
import com.thinkgem.jeesite.modules.ele.service.BizFsuMonitorinfoService;

/**
 * FSU监控信息Controller
 * @author ws
 * @version 2017-11-09
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizFsuMonitorinfo")
public class BizFsuMonitorinfoController extends BaseController {

	@Autowired
	private BizFsuMonitorinfoService bizFsuMonitorinfoService;
	
	@ModelAttribute
	public BizFsuMonitorinfo get(@RequestParam(required=false) String id) {
		BizFsuMonitorinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizFsuMonitorinfoService.get(id);
		}
		if (entity == null){
			entity = new BizFsuMonitorinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizFsuMonitorinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizFsuMonitorinfo bizFsuMonitorinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizFsuMonitorinfo> page = bizFsuMonitorinfoService.findPage(new Page<BizFsuMonitorinfo>(request, response), bizFsuMonitorinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizFsuMonitorinfoList";
	}

	@RequiresPermissions("ele:bizFsuMonitorinfo:view")
	@RequestMapping(value = "form")
	public String form(BizFsuMonitorinfo bizFsuMonitorinfo, Model model) {
		model.addAttribute("bizFsuMonitorinfo", bizFsuMonitorinfo);
		return "modules/ele/bizFsuMonitorinfoForm";
	}

	@RequiresPermissions("ele:bizFsuMonitorinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizFsuMonitorinfo bizFsuMonitorinfo, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, bizFsuMonitorinfo)){
				return form(bizFsuMonitorinfo, model);
			}
			bizFsuMonitorinfoService.save(bizFsuMonitorinfo);
			addMessage(redirectAttributes, "保存FSU监控信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "导入失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizFsuMonitorinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizFsuMonitorinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizFsuMonitorinfo bizFsuMonitorinfo, RedirectAttributes redirectAttributes) {
		try {
			bizFsuMonitorinfoService.delete(bizFsuMonitorinfo);
			addMessage(redirectAttributes, "删除FSU监控信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "删除失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizFsuMonitorinfo/?repage";
	}

	@RequiresPermissions("ele:bizFsuMonitorinfo:edit")
	@RequestMapping(value = "deletebatch")
	@ResponseBody
	public AjaxMsg deleteBatch(@RequestParam("ids[]")List<String> ids) {
		AjaxMsg ajaxMsg = new AjaxMsg(String.valueOf(HttpStatus.OK), "删除FSU监控信息成功");
		try {
			for (String id : ids) {
				BizFsuMonitorinfo bizFsuMonitorinfo = new BizFsuMonitorinfo();
				bizFsuMonitorinfo.setId(id);
				bizFsuMonitorinfoService.delete(bizFsuMonitorinfo);
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

	@RequiresPermissions("ele:bizFsuMonitorinfo:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(BizFsuMonitorinfo bizFsuMonitorinfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "FSU监控信息"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<BizFsuMonitorinfo> page = bizFsuMonitorinfoService.findPage(new Page<BizFsuMonitorinfo>(request, response, -1), bizFsuMonitorinfo);
			new ExportExcel("FSU监控信息", BizFsuMonitorinfo.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出FSU监控信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizFsuMonitorinfo/?repage";
	}

	@RequiresPermissions("ele:bizFsuMonitorinfo:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/ele/bizFsuMonitorinfo/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BizFsuMonitorinfo> list = ei.getDataList(BizFsuMonitorinfo.class);
			for (BizFsuMonitorinfo bizFsuMonitorinfo : list) {
				try {
					if (true) {
						BeanValidators.validateWithException(validator, bizFsuMonitorinfo);
						bizFsuMonitorinfoService.save(bizFsuMonitorinfo);
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
					failureMsg.append("导入失败：" + InitImportData.getPrimaryValue(bizFsuMonitorinfo) + "=>" + exceptionMsg +"; ");
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizFsuMonitorinfo/?repage";
	}

	@RequiresPermissions("ele:bizFsuMonitorinfo:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "FSU监控信息导入模板.xlsx";
			List<BizFsuMonitorinfo> list = Lists.newArrayList();
			list.add((BizFsuMonitorinfo) InitImportData.getImportData(new BizFsuMonitorinfo()));
			new ExportExcel("FSU监控信息", BizFsuMonitorinfo.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "FSU监控信息导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizFsuMonitorinfo/?repage";
	}
}