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
import com.thinkgem.jeesite.modules.ele.entity.BizElectricShareinfo;
import com.thinkgem.jeesite.modules.ele.service.BizElectricShareinfoService;

/**
 * 电流分摊信息Controller
 * @author ws
 * @version 2017-12-26
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizElectricShareinfo")
public class BizElectricShareinfoController extends BaseController {

	@Autowired
	private BizElectricShareinfoService bizElectricShareinfoService;
	
	@ModelAttribute
	public BizElectricShareinfo get(@RequestParam(required=false) String id) {
		BizElectricShareinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizElectricShareinfoService.get(id);
		}
		if (entity == null){
			entity = new BizElectricShareinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizElectricShareinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizElectricShareinfo bizElectricShareinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizElectricShareinfo> page = bizElectricShareinfoService.findPage(new Page<BizElectricShareinfo>(request, response), bizElectricShareinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizElectricShareinfoList";
	}

	@RequiresPermissions("ele:bizElectricShareinfo:view")
	@RequestMapping(value = "form")
	public String form(BizElectricShareinfo bizElectricShareinfo, Model model) {
		model.addAttribute("bizElectricShareinfo", bizElectricShareinfo);
		return "modules/ele/bizElectricShareinfoForm";
	}

	@RequiresPermissions("ele:bizElectricShareinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizElectricShareinfo bizElectricShareinfo, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, bizElectricShareinfo)){
				return form(bizElectricShareinfo, model);
			}
			bizElectricShareinfoService.save(bizElectricShareinfo);
			addMessage(redirectAttributes, "保存电流分摊信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "导入失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizElectricShareinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizElectricShareinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizElectricShareinfo bizElectricShareinfo, RedirectAttributes redirectAttributes) {
		try {
			bizElectricShareinfoService.delete(bizElectricShareinfo);
			addMessage(redirectAttributes, "删除电流分摊信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "删除失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizElectricShareinfo/?repage";
	}

	@RequiresPermissions("ele:bizElectricShareinfo:edit")
	@RequestMapping(value = "deletebatch")
	@ResponseBody
	public AjaxMsg deleteBatch(@RequestParam("ids[]")List<String> ids) {
		AjaxMsg ajaxMsg = new AjaxMsg(String.valueOf(HttpStatus.OK), "删除电流分摊信息成功");
		try {
			for (String id : ids) {
				BizElectricShareinfo bizElectricShareinfo = new BizElectricShareinfo();
				bizElectricShareinfo.setId(id);
				bizElectricShareinfoService.delete(bizElectricShareinfo);
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

	@RequiresPermissions("ele:bizElectricShareinfo:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(BizElectricShareinfo bizElectricShareinfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "电流分摊信息"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			List<BizElectricShareinfo> list = bizElectricShareinfoService.findList(bizElectricShareinfo);
			new ExportExcel("电流分摊信息", BizElectricShareinfo.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出电流分摊信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizElectricShareinfo/?repage";
	}

	@RequiresPermissions("ele:bizElectricShareinfo:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/ele/bizElectricShareinfo/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BizElectricShareinfo> list = ei.getDataList(BizElectricShareinfo.class);
			for (BizElectricShareinfo bizElectricShareinfo : list) {
				try {
					if (true) {
						BeanValidators.validateWithException(validator, bizElectricShareinfo);
						bizElectricShareinfoService.save(bizElectricShareinfo);
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
					failureMsg.append("导入失败：" + InitImportData.getPrimaryValue(bizElectricShareinfo) + "=>" + exceptionMsg +"; ");
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizElectricShareinfo/?repage";
	}

	@RequiresPermissions("ele:bizElectricShareinfo:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "电流分摊信息导入模板.xlsx";
			List<BizElectricShareinfo> list = Lists.newArrayList();
			list.add((BizElectricShareinfo) InitImportData.getImportData(new BizElectricShareinfo()));
			new ExportExcel("电流分摊信息", BizElectricShareinfo.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "电流分摊信息导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizElectricShareinfo/?repage";
	}
}