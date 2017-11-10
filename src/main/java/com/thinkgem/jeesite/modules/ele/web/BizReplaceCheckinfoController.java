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
import com.thinkgem.jeesite.modules.ele.entity.BizReplaceCheckinfo;
import com.thinkgem.jeesite.modules.ele.service.BizReplaceCheckinfoService;

/**
 * 代维巡检信息Controller
 * @author ws
 * @version 2017-11-09
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizReplaceCheckinfo")
public class BizReplaceCheckinfoController extends BaseController {

	@Autowired
	private BizReplaceCheckinfoService bizReplaceCheckinfoService;
	
	@ModelAttribute
	public BizReplaceCheckinfo get(@RequestParam(required=false) String id) {
		BizReplaceCheckinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizReplaceCheckinfoService.get(id);
		}
		if (entity == null){
			entity = new BizReplaceCheckinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizReplaceCheckinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizReplaceCheckinfo bizReplaceCheckinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizReplaceCheckinfo> page = bizReplaceCheckinfoService.findPage(new Page<BizReplaceCheckinfo>(request, response), bizReplaceCheckinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizReplaceCheckinfoList";
	}

	@RequiresPermissions("ele:bizReplaceCheckinfo:view")
	@RequestMapping(value = "form")
	public String form(BizReplaceCheckinfo bizReplaceCheckinfo, Model model) {
		model.addAttribute("bizReplaceCheckinfo", bizReplaceCheckinfo);
		return "modules/ele/bizReplaceCheckinfoForm";
	}

	@RequiresPermissions("ele:bizReplaceCheckinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizReplaceCheckinfo bizReplaceCheckinfo, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, bizReplaceCheckinfo)){
				return form(bizReplaceCheckinfo, model);
			}
			bizReplaceCheckinfoService.save(bizReplaceCheckinfo);
			addMessage(redirectAttributes, "保存代维巡检信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "导入失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizReplaceCheckinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizReplaceCheckinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizReplaceCheckinfo bizReplaceCheckinfo, RedirectAttributes redirectAttributes) {
		try {
			bizReplaceCheckinfoService.delete(bizReplaceCheckinfo);
			addMessage(redirectAttributes, "删除代维巡检信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "删除失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizReplaceCheckinfo/?repage";
	}

	@RequiresPermissions("ele:bizReplaceCheckinfo:edit")
	@RequestMapping(value = "deletebatch")
	@ResponseBody
	public AjaxMsg deleteBatch(@RequestParam("ids[]")List<String> ids) {
		AjaxMsg ajaxMsg = new AjaxMsg(String.valueOf(HttpStatus.OK), "删除代维巡检信息成功");
		try {
			for (String id : ids) {
				BizReplaceCheckinfo bizReplaceCheckinfo = new BizReplaceCheckinfo();
				bizReplaceCheckinfo.setId(id);
				bizReplaceCheckinfoService.delete(bizReplaceCheckinfo);
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

	@RequiresPermissions("ele:bizReplaceCheckinfo:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(BizReplaceCheckinfo bizReplaceCheckinfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "代维巡检信息"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<BizReplaceCheckinfo> page = bizReplaceCheckinfoService.findPage(new Page<BizReplaceCheckinfo>(request, response, -1), bizReplaceCheckinfo);
			new ExportExcel("代维巡检信息", BizReplaceCheckinfo.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出代维巡检信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizReplaceCheckinfo/?repage";
	}

	@RequiresPermissions("ele:bizReplaceCheckinfo:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/ele/bizReplaceCheckinfo/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BizReplaceCheckinfo> list = ei.getDataList(BizReplaceCheckinfo.class);
			for (BizReplaceCheckinfo bizReplaceCheckinfo : list) {
				try {
					if (true) {
						BeanValidators.validateWithException(validator, bizReplaceCheckinfo);
						bizReplaceCheckinfoService.save(bizReplaceCheckinfo);
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
					failureMsg.append("导入失败：" + InitImportData.getPrimaryValue(bizReplaceCheckinfo) + "=>" + exceptionMsg +"; ");
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizReplaceCheckinfo/?repage";
	}

	@RequiresPermissions("ele:bizReplaceCheckinfo:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "代维巡检信息导入模板.xlsx";
			List<BizReplaceCheckinfo> list = Lists.newArrayList();
			list.add((BizReplaceCheckinfo) InitImportData.getImportData(new BizReplaceCheckinfo()));
			new ExportExcel("代维巡检信息", BizReplaceCheckinfo.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "代维巡检信息导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizReplaceCheckinfo/?repage";
	}
}