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
import com.thinkgem.jeesite.modules.ele.entity.BizDirectPayinfo;
import com.thinkgem.jeesite.modules.ele.service.BizDirectPayinfoService;

/**
 * 直供缴费信息Controller
 * @author ws
 * @version 2017-11-09
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizDirectPayinfo")
public class BizDirectPayinfoController extends BaseController {

	@Autowired
	private BizDirectPayinfoService bizDirectPayinfoService;
	
	@ModelAttribute
	public BizDirectPayinfo get(@RequestParam(required=false) String id) {
		BizDirectPayinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizDirectPayinfoService.get(id);
		}
		if (entity == null){
			entity = new BizDirectPayinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizDirectPayinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizDirectPayinfo bizDirectPayinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizDirectPayinfo> page = bizDirectPayinfoService.findPage(new Page<BizDirectPayinfo>(request, response), bizDirectPayinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizDirectPayinfoList";
	}

	@RequiresPermissions("ele:bizDirectPayinfo:view")
	@RequestMapping(value = "form")
	public String form(BizDirectPayinfo bizDirectPayinfo, Model model) {
		model.addAttribute("bizDirectPayinfo", bizDirectPayinfo);
		return "modules/ele/bizDirectPayinfoForm";
	}

	@RequiresPermissions("ele:bizDirectPayinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizDirectPayinfo bizDirectPayinfo, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, bizDirectPayinfo)){
				return form(bizDirectPayinfo, model);
			}
			bizDirectPayinfoService.save(bizDirectPayinfo);
			addMessage(redirectAttributes, "保存直供缴费信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "导入失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizDirectPayinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizDirectPayinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizDirectPayinfo bizDirectPayinfo, RedirectAttributes redirectAttributes) {
		try {
			bizDirectPayinfoService.delete(bizDirectPayinfo);
			addMessage(redirectAttributes, "删除直供缴费信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "删除失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizDirectPayinfo/?repage";
	}

	@RequiresPermissions("ele:bizDirectPayinfo:edit")
	@RequestMapping(value = "deletebatch")
	@ResponseBody
	public AjaxMsg deleteBatch(@RequestParam("ids[]")List<String> ids) {
		AjaxMsg ajaxMsg = new AjaxMsg(String.valueOf(HttpStatus.OK), "删除直供缴费信息成功");
		try {
			for (String id : ids) {
				BizDirectPayinfo bizDirectPayinfo = new BizDirectPayinfo();
				bizDirectPayinfo.setId(id);
				bizDirectPayinfoService.delete(bizDirectPayinfo);
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

	@RequiresPermissions("ele:bizDirectPayinfo:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(BizDirectPayinfo bizDirectPayinfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "直供缴费信息"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<BizDirectPayinfo> page = bizDirectPayinfoService.findPage(new Page<BizDirectPayinfo>(request, response, -1), bizDirectPayinfo);
			new ExportExcel("直供缴费信息", BizDirectPayinfo.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出直供缴费信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizDirectPayinfo/?repage";
	}

	@RequiresPermissions("ele:bizDirectPayinfo:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/ele/bizDirectPayinfo/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BizDirectPayinfo> list = ei.getDataList(BizDirectPayinfo.class);
			for (BizDirectPayinfo bizDirectPayinfo : list) {
				try {
					if (true) {
						BeanValidators.validateWithException(validator, bizDirectPayinfo);
						bizDirectPayinfoService.save(bizDirectPayinfo);
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
					failureMsg.append("导入失败：" + InitImportData.getPrimaryValue(bizDirectPayinfo) + "=>" + exceptionMsg +"; ");
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizDirectPayinfo/?repage";
	}

	@RequiresPermissions("ele:bizDirectPayinfo:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "直供缴费信息导入模板.xlsx";
			List<BizDirectPayinfo> list = Lists.newArrayList();
			list.add((BizDirectPayinfo) InitImportData.getImportData(new BizDirectPayinfo()));
			new ExportExcel("直供缴费信息", BizDirectPayinfo.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "直供缴费信息导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizDirectPayinfo/?repage";
	}
}