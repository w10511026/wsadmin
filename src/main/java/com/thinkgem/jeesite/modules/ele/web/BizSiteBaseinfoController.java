package com.thinkgem.jeesite.modules.ele.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.ele.util.InitImportData;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ele.entity.BizSiteBaseinfo;
import com.thinkgem.jeesite.modules.ele.service.BizSiteBaseinfoService;

import java.util.Date;
import java.util.List;

/**
 * 站址基础信息Controller
 * @author ws
 * @version 2017-11-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizSiteBaseinfo")
public class BizSiteBaseinfoController extends BaseController {

	@Autowired
	private BizSiteBaseinfoService bizSiteBaseinfoService;
	
	@ModelAttribute
	public BizSiteBaseinfo get(@RequestParam(required=false) String id) {
		BizSiteBaseinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizSiteBaseinfoService.get(id);
		}
		if (entity == null){
			entity = new BizSiteBaseinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizSiteBaseinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizSiteBaseinfo bizSiteBaseinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizSiteBaseinfo> page = bizSiteBaseinfoService.findPage(new Page<BizSiteBaseinfo>(request, response), bizSiteBaseinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizSiteBaseinfoList";
	}

	@RequiresPermissions("ele:bizSiteBaseinfo:view")
	@RequestMapping(value = "form")
	public String form(BizSiteBaseinfo bizSiteBaseinfo, Model model) {
		model.addAttribute("bizSiteBaseinfo", bizSiteBaseinfo);
		return "modules/ele/bizSiteBaseinfoForm";
	}

	@RequiresPermissions("ele:bizSiteBaseinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizSiteBaseinfo bizSiteBaseinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizSiteBaseinfo)){
			return form(bizSiteBaseinfo, model);
		}
		bizSiteBaseinfoService.save(bizSiteBaseinfo);
		addMessage(redirectAttributes, "保存站址基础信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizSiteBaseinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizSiteBaseinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizSiteBaseinfo bizSiteBaseinfo, RedirectAttributes redirectAttributes) {
		bizSiteBaseinfoService.delete(bizSiteBaseinfo);
		addMessage(redirectAttributes, "删除站址基础信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizSiteBaseinfo/?repage";
	}


	/**
	 * 导出站址基础信息
	 * @param bizSiteBaseinfo
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ele:bizSiteBaseinfo:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(BizSiteBaseinfo bizSiteBaseinfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "站址基础信息"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<BizSiteBaseinfo> page = bizSiteBaseinfoService.findPage(new Page<BizSiteBaseinfo>(request, response, -1), bizSiteBaseinfo);
			new ExportExcel("站址基础信息", BizSiteBaseinfo.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出站址基础信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/ele/bizSiteBaseinfo/?repage";
	}

	/**
	 * 导入站址基础信息
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ele:bizSiteBaseinfo:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BizSiteBaseinfo> list = ei.getDataList(BizSiteBaseinfo.class);
			for (BizSiteBaseinfo baseinfo : list) {
				try {
					BeanValidators.validateWithException(validator, baseinfo);
					bizSiteBaseinfoService.save(baseinfo);
					successNum++;
				} catch (ConstraintViolationException ex) {
					failureMsg.append("导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("导入失败：" + ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/ele/bizSiteBaseinfo?repage";
	}

	/**
	 * 下载导入站址基础信息模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ele:bizSiteBaseinfo:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "站址基础信息导入模板.xlsx";
			List<BizSiteBaseinfo> list = Lists.newArrayList();
			InitImportData.getImportData(BizSiteBaseinfo.class);
			//list.add();
			new ExportExcel("站址基础信息", BizSiteBaseinfo.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/ele/bizSiteBaseinfo?repage";
	}

	public BizSiteBaseinfo initData(){
		BizSiteBaseinfo baseinfo = new BizSiteBaseinfo();
		baseinfo.setSicmq(new Date());
		baseinfo.setSictq(new Date());
		baseinfo.setSicuq(new Date());
		baseinfo.setSidistrict("2121");
		baseinfo.setSipropertyunit("646");
		baseinfo.setSiretain("65");
		baseinfo.setSiroomstyle("llla");
		baseinfo.setSisitename("kkkkk");
		baseinfo.setSisitenum("tttttttt");
		return baseinfo;
	}
}