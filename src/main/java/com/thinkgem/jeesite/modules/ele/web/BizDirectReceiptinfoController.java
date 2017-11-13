package com.thinkgem.jeesite.modules.ele.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import java.util.*;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.ele.entity.BizSiteMeterinfo;
import com.thinkgem.jeesite.modules.ele.service.BizSiteMeterinfoService;
import com.thinkgem.jeesite.modules.ele.util.InitImportData;
import org.apache.commons.collections.CollectionUtils;
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
import com.thinkgem.jeesite.modules.ele.entity.BizDirectReceiptinfo;
import com.thinkgem.jeesite.modules.ele.service.BizDirectReceiptinfoService;

/**
 * 直供回款信息Controller
 * @author ws
 * @version 2017-11-09
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizDirectReceiptinfo")
public class BizDirectReceiptinfoController extends BaseController {

	@Autowired
	private BizDirectReceiptinfoService bizDirectReceiptinfoService;
	@Autowired
	private BizSiteMeterinfoService bizSiteMeterinfoService;
	
	@ModelAttribute
	public BizDirectReceiptinfo get(@RequestParam(required=false) String id) {
		BizDirectReceiptinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizDirectReceiptinfoService.get(id);
		}
		if (entity == null){
			entity = new BizDirectReceiptinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizDirectReceiptinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizDirectReceiptinfo bizDirectReceiptinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizDirectReceiptinfo> page = bizDirectReceiptinfoService.findPage(new Page<BizDirectReceiptinfo>(request, response), bizDirectReceiptinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizDirectReceiptinfoList";
	}

	@RequiresPermissions("ele:bizDirectReceiptinfo:view")
	@RequestMapping(value = "form")
	public String form(BizDirectReceiptinfo bizDirectReceiptinfo, Model model) {
		model.addAttribute("bizDirectReceiptinfo", bizDirectReceiptinfo);
		return "modules/ele/bizDirectReceiptinfoForm";
	}

	@RequiresPermissions("ele:bizDirectReceiptinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizDirectReceiptinfo bizDirectReceiptinfo, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (!beanValidator(model, bizDirectReceiptinfo)){
				return form(bizDirectReceiptinfo, model);
			}
			bizDirectReceiptinfoService.save(bizDirectReceiptinfo);
			addMessage(redirectAttributes, "保存直供回款信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "导入失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizDirectReceiptinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizDirectReceiptinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizDirectReceiptinfo bizDirectReceiptinfo, RedirectAttributes redirectAttributes) {
		try {
			bizDirectReceiptinfoService.delete(bizDirectReceiptinfo);
			addMessage(redirectAttributes, "删除直供回款信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "删除失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizDirectReceiptinfo/?repage";
	}

	@RequiresPermissions("ele:bizDirectReceiptinfo:edit")
	@RequestMapping(value = "deletebatch")
	@ResponseBody
	public AjaxMsg deleteBatch(@RequestParam("ids[]")List<String> ids) {
		AjaxMsg ajaxMsg = new AjaxMsg(String.valueOf(HttpStatus.OK), "删除直供回款信息成功");
		try {
			for (String id : ids) {
				BizDirectReceiptinfo bizDirectReceiptinfo = new BizDirectReceiptinfo();
				bizDirectReceiptinfo.setId(id);
				bizDirectReceiptinfoService.delete(bizDirectReceiptinfo);
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

	@RequiresPermissions("ele:bizDirectReceiptinfo:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(BizDirectReceiptinfo bizDirectReceiptinfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "直供回款信息"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<BizDirectReceiptinfo> page = bizDirectReceiptinfoService.findPage(new Page<BizDirectReceiptinfo>(request, response, -1), bizDirectReceiptinfo);
			new ExportExcel("直供回款信息", BizDirectReceiptinfo.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出直供回款信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizDirectReceiptinfo/?repage";
	}

	@RequiresPermissions("ele:bizDirectReceiptinfo:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/ele/bizDirectReceiptinfo/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BizDirectReceiptinfo> list = ei.getDataList(BizDirectReceiptinfo.class);
			for (BizDirectReceiptinfo bizDirectReceiptinfo : list) {
				try {
					boolean isTrue = false;
					BizSiteMeterinfo bizSiteMeterinfo = new BizSiteMeterinfo();
					bizSiteMeterinfo.setAmnum(bizDirectReceiptinfo.getScaccnum());
					List<BizSiteMeterinfo> bizSiteMeterinfoList = bizSiteMeterinfoService.findList(bizSiteMeterinfo);
					List<Date> dateList = new ArrayList<>();
					for (BizSiteMeterinfo siteMeterinfo : bizSiteMeterinfoList) {
						dateList.add(siteMeterinfo.getAmstartdate());
					}
					if (CollectionUtils.isNotEmpty(dateList)) {
						Date maxDate = Collections.max(dateList);
						Date minDate = Collections.min(dateList);
						if (bizDirectReceiptinfo.getSprecdate().getTime() > minDate.getTime() && bizDirectReceiptinfo.getSprecdate().getTime() < maxDate.getTime()) {
							isTrue = true;
						}
						if (isTrue) {
							BeanValidators.validateWithException(validator, bizDirectReceiptinfo);
							bizDirectReceiptinfoService.save(bizDirectReceiptinfo);
							successNum++;
						} else {
							failureMsg.append("<br/>");
							failureNum++;
						}
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
					failureMsg.append("导入失败：" + InitImportData.getPrimaryValue(bizDirectReceiptinfo) + "=>" + exceptionMsg +"; ");
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizDirectReceiptinfo/?repage";
	}

	@RequiresPermissions("ele:bizDirectReceiptinfo:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "直供回款信息导入模板.xlsx";
			List<BizDirectReceiptinfo> list = Lists.newArrayList();
			list.add((BizDirectReceiptinfo) InitImportData.getImportData(new BizDirectReceiptinfo()));
			new ExportExcel("直供回款信息", BizDirectReceiptinfo.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "直供回款信息导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizDirectReceiptinfo/?repage";
	}
}