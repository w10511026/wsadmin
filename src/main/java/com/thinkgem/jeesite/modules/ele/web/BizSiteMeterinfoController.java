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
import com.thinkgem.jeesite.modules.ele.entity.BizSiteMeterinfo;
import com.thinkgem.jeesite.modules.ele.service.BizSiteMeterinfoService;

/**
 * 表站对应信息Controller
 * @author ws
 * @version 2017-12-26
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizSiteMeterinfo")
public class BizSiteMeterinfoController extends BaseController {

	@Autowired
	private BizSiteMeterinfoService bizSiteMeterinfoService;
	
	@ModelAttribute
	public BizSiteMeterinfo get(@RequestParam(required=false) String id) {
		BizSiteMeterinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizSiteMeterinfoService.get(id);
		}
		if (entity == null){
			entity = new BizSiteMeterinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizSiteMeterinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizSiteMeterinfo bizSiteMeterinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizSiteMeterinfo> page = bizSiteMeterinfoService.findPage(new Page<BizSiteMeterinfo>(request, response), bizSiteMeterinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizSiteMeterinfoList";
	}

	@RequiresPermissions("ele:bizSiteMeterinfo:view")
	@RequestMapping(value = "form")
	public String form(BizSiteMeterinfo bizSiteMeterinfo, Model model) {
		model.addAttribute("bizSiteMeterinfo", bizSiteMeterinfo);
		return "modules/ele/bizSiteMeterinfoForm";
	}

	@RequiresPermissions("ele:bizSiteMeterinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizSiteMeterinfo bizSiteMeterinfo, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, bizSiteMeterinfo)){
				return form(bizSiteMeterinfo, model);
			}
			bizSiteMeterinfoService.save(bizSiteMeterinfo);
			addMessage(redirectAttributes, "保存表站对应信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "导入失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizSiteMeterinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizSiteMeterinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizSiteMeterinfo bizSiteMeterinfo, RedirectAttributes redirectAttributes) {
		try {
			bizSiteMeterinfoService.delete(bizSiteMeterinfo);
			addMessage(redirectAttributes, "删除表站对应信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "删除失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizSiteMeterinfo/?repage";
	}

	@RequiresPermissions("ele:bizSiteMeterinfo:edit")
	@RequestMapping(value = "deletebatch")
	@ResponseBody
	public AjaxMsg deleteBatch(@RequestParam("ids[]")List<String> ids) {
		AjaxMsg ajaxMsg = new AjaxMsg(String.valueOf(HttpStatus.OK), "删除表站对应信息成功");
		try {
			for (String id : ids) {
				BizSiteMeterinfo bizSiteMeterinfo = new BizSiteMeterinfo();
				bizSiteMeterinfo.setId(id);
				bizSiteMeterinfoService.delete(bizSiteMeterinfo);
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

	@RequiresPermissions("ele:bizSiteMeterinfo:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(BizSiteMeterinfo bizSiteMeterinfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "表站对应信息"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<BizSiteMeterinfo> page = bizSiteMeterinfoService.findPage(new Page<BizSiteMeterinfo>(request, response, -1), bizSiteMeterinfo);
			new ExportExcel("表站对应信息", BizSiteMeterinfo.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出表站对应信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizSiteMeterinfo/?repage";
	}

	@RequiresPermissions("ele:bizSiteMeterinfo:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/ele/bizSiteMeterinfo/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BizSiteMeterinfo> list = ei.getDataList(BizSiteMeterinfo.class);
			for (BizSiteMeterinfo bizSiteMeterinfo : list) {
				try {
					if (true) {
						BeanValidators.validateWithException(validator, bizSiteMeterinfo);
						bizSiteMeterinfoService.save(bizSiteMeterinfo);
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
					failureMsg.append("导入失败：" + InitImportData.getPrimaryValue(bizSiteMeterinfo) + "=>" + exceptionMsg +"; ");
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizSiteMeterinfo/?repage";
	}

	@RequiresPermissions("ele:bizSiteMeterinfo:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "表站对应信息导入模板.xlsx";
			List<BizSiteMeterinfo> list = Lists.newArrayList();
			list.add((BizSiteMeterinfo) InitImportData.getImportData(new BizSiteMeterinfo()));
			new ExportExcel("表站对应信息", BizSiteMeterinfo.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "表站对应信息导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizSiteMeterinfo/?repage";
	}
}