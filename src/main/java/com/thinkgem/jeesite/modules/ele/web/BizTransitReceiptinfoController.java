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
import com.thinkgem.jeesite.modules.ele.entity.BizTransitReceiptinfo;
import com.thinkgem.jeesite.modules.ele.service.BizTransitReceiptinfoService;

/**
 * 转供回款信息Controller
 * @author ws
 * @version 2017-11-09
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizTransitReceiptinfo")
public class BizTransitReceiptinfoController extends BaseController {

	@Autowired
	private BizTransitReceiptinfoService bizTransitReceiptinfoService;
	
	@ModelAttribute
	public BizTransitReceiptinfo get(@RequestParam(required=false) String id) {
		BizTransitReceiptinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizTransitReceiptinfoService.get(id);
		}
		if (entity == null){
			entity = new BizTransitReceiptinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizTransitReceiptinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizTransitReceiptinfo bizTransitReceiptinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizTransitReceiptinfo> page = bizTransitReceiptinfoService.findPage(new Page<BizTransitReceiptinfo>(request, response), bizTransitReceiptinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizTransitReceiptinfoList";
	}

	@RequiresPermissions("ele:bizTransitReceiptinfo:view")
	@RequestMapping(value = "form")
	public String form(BizTransitReceiptinfo bizTransitReceiptinfo, Model model) {
		model.addAttribute("bizTransitReceiptinfo", bizTransitReceiptinfo);
		return "modules/ele/bizTransitReceiptinfoForm";
	}

	@RequiresPermissions("ele:bizTransitReceiptinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizTransitReceiptinfo bizTransitReceiptinfo, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, bizTransitReceiptinfo)){
				return form(bizTransitReceiptinfo, model);
			}
			bizTransitReceiptinfoService.save(bizTransitReceiptinfo);
			addMessage(redirectAttributes, "保存转供回款信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "导入失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitReceiptinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizTransitReceiptinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizTransitReceiptinfo bizTransitReceiptinfo, RedirectAttributes redirectAttributes) {
		try {
			bizTransitReceiptinfoService.delete(bizTransitReceiptinfo);
			addMessage(redirectAttributes, "删除转供回款信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "删除失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitReceiptinfo/?repage";
	}

	@RequiresPermissions("ele:bizTransitReceiptinfo:edit")
	@RequestMapping(value = "deletebatch")
	@ResponseBody
	public AjaxMsg deleteBatch(@RequestParam("ids[]")List<String> ids) {
		AjaxMsg ajaxMsg = new AjaxMsg(String.valueOf(HttpStatus.OK), "删除转供回款信息成功");
		try {
			for (String id : ids) {
				BizTransitReceiptinfo bizTransitReceiptinfo = new BizTransitReceiptinfo();
				bizTransitReceiptinfo.setId(id);
				bizTransitReceiptinfoService.delete(bizTransitReceiptinfo);
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

	@RequiresPermissions("ele:bizTransitReceiptinfo:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(BizTransitReceiptinfo bizTransitReceiptinfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "转供回款信息"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<BizTransitReceiptinfo> page = bizTransitReceiptinfoService.findPage(new Page<BizTransitReceiptinfo>(request, response, -1), bizTransitReceiptinfo);
			new ExportExcel("转供回款信息", BizTransitReceiptinfo.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出转供回款信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitReceiptinfo/?repage";
	}

	@RequiresPermissions("ele:bizTransitReceiptinfo:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/ele/bizTransitReceiptinfo/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BizTransitReceiptinfo> list = ei.getDataList(BizTransitReceiptinfo.class);
			for (BizTransitReceiptinfo bizTransitReceiptinfo : list) {
				try {
					if (true) {
						BeanValidators.validateWithException(validator, bizTransitReceiptinfo);
						bizTransitReceiptinfoService.save(bizTransitReceiptinfo);
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
					failureMsg.append("导入失败：" + InitImportData.getPrimaryValue(bizTransitReceiptinfo) + "=>" + exceptionMsg +"; ");
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitReceiptinfo/?repage";
	}

	@RequiresPermissions("ele:bizTransitReceiptinfo:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "转供回款信息导入模板.xlsx";
			List<BizTransitReceiptinfo> list = Lists.newArrayList();
			list.add((BizTransitReceiptinfo) InitImportData.getImportData(new BizTransitReceiptinfo()));
			new ExportExcel("转供回款信息", BizTransitReceiptinfo.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "转供回款信息导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitReceiptinfo/?repage";
	}
}