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
import com.thinkgem.jeesite.modules.ele.entity.BizTransitPayinfo;
import com.thinkgem.jeesite.modules.ele.service.BizTransitPayinfoService;

/**
 * 转供缴费信息Controller
 * @author ws
 * @version 2017-11-09
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizTransitPayinfo")
public class BizTransitPayinfoController extends BaseController {

	@Autowired
	private BizTransitPayinfoService bizTransitPayinfoService;
	
	@ModelAttribute
	public BizTransitPayinfo get(@RequestParam(required=false) String id) {
		BizTransitPayinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizTransitPayinfoService.get(id);
		}
		if (entity == null){
			entity = new BizTransitPayinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizTransitPayinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizTransitPayinfo bizTransitPayinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizTransitPayinfo> page = bizTransitPayinfoService.findPage(new Page<BizTransitPayinfo>(request, response), bizTransitPayinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizTransitPayinfoList";
	}

	@RequiresPermissions("ele:bizTransitPayinfo:view")
	@RequestMapping(value = "form")
	public String form(BizTransitPayinfo bizTransitPayinfo, Model model) {
		model.addAttribute("bizTransitPayinfo", bizTransitPayinfo);
		return "modules/ele/bizTransitPayinfoForm";
	}

	@RequiresPermissions("ele:bizTransitPayinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizTransitPayinfo bizTransitPayinfo, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, bizTransitPayinfo)){
				return form(bizTransitPayinfo, model);
			}
			bizTransitPayinfoService.save(bizTransitPayinfo);
			addMessage(redirectAttributes, "保存转供缴费信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "导入失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitPayinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizTransitPayinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizTransitPayinfo bizTransitPayinfo, RedirectAttributes redirectAttributes) {
		try {
			bizTransitPayinfoService.delete(bizTransitPayinfo);
			addMessage(redirectAttributes, "删除转供缴费信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "删除失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitPayinfo/?repage";
	}

	@RequiresPermissions("ele:bizTransitPayinfo:edit")
	@RequestMapping(value = "deletebatch")
	@ResponseBody
	public AjaxMsg deleteBatch(@RequestParam("ids[]")List<String> ids) {
		AjaxMsg ajaxMsg = new AjaxMsg(String.valueOf(HttpStatus.OK), "删除转供缴费信息成功");
		try {
			for (String id : ids) {
				BizTransitPayinfo bizTransitPayinfo = new BizTransitPayinfo();
				bizTransitPayinfo.setId(id);
				bizTransitPayinfoService.delete(bizTransitPayinfo);
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

	@RequiresPermissions("ele:bizTransitPayinfo:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(BizTransitPayinfo bizTransitPayinfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "转供缴费信息"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<BizTransitPayinfo> page = bizTransitPayinfoService.findPage(new Page<BizTransitPayinfo>(request, response, -1), bizTransitPayinfo);
			new ExportExcel("转供缴费信息", BizTransitPayinfo.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出转供缴费信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitPayinfo/?repage";
	}

	@RequiresPermissions("ele:bizTransitPayinfo:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/ele/bizTransitPayinfo/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BizTransitPayinfo> list = ei.getDataList(BizTransitPayinfo.class);
			for (BizTransitPayinfo bizTransitPayinfo : list) {
				try {
					if (true) {
						BeanValidators.validateWithException(validator, bizTransitPayinfo);
						bizTransitPayinfoService.save(bizTransitPayinfo);
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
					failureMsg.append("导入失败：" + InitImportData.getPrimaryValue(bizTransitPayinfo) + "=>" + exceptionMsg +"; ");
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitPayinfo/?repage";
	}

	@RequiresPermissions("ele:bizTransitPayinfo:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "转供缴费信息导入模板.xlsx";
			List<BizTransitPayinfo> list = Lists.newArrayList();
			list.add((BizTransitPayinfo) InitImportData.getImportData(new BizTransitPayinfo()));
			new ExportExcel("转供缴费信息", BizTransitPayinfo.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "转供缴费信息导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitPayinfo/?repage";
	}
}