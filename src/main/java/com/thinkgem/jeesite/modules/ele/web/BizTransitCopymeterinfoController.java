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
import com.thinkgem.jeesite.modules.ele.entity.BizTransitCopymeterinfo;
import com.thinkgem.jeesite.modules.ele.service.BizTransitCopymeterinfoService;

/**
 * 转供抄表信息Controller
 * @author ws
 * @version 2017-11-08
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizTransitCopymeterinfo")
public class BizTransitCopymeterinfoController extends BaseController {

	@Autowired
	private BizTransitCopymeterinfoService bizTransitCopymeterinfoService;
	
	@ModelAttribute
	public BizTransitCopymeterinfo get(@RequestParam(required=false) String id) {
		BizTransitCopymeterinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizTransitCopymeterinfoService.get(id);
		}
		if (entity == null){
			entity = new BizTransitCopymeterinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizTransitCopymeterinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizTransitCopymeterinfo bizTransitCopymeterinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizTransitCopymeterinfo> page = bizTransitCopymeterinfoService.findPage(new Page<BizTransitCopymeterinfo>(request, response), bizTransitCopymeterinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizTransitCopymeterinfoList";
	}

	@RequiresPermissions("ele:bizTransitCopymeterinfo:view")
	@RequestMapping(value = "form")
	public String form(BizTransitCopymeterinfo bizTransitCopymeterinfo, Model model) {
		model.addAttribute("bizTransitCopymeterinfo", bizTransitCopymeterinfo);
		return "modules/ele/bizTransitCopymeterinfoForm";
	}

	@RequiresPermissions("ele:bizTransitCopymeterinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizTransitCopymeterinfo bizTransitCopymeterinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizTransitCopymeterinfo)){
			return form(bizTransitCopymeterinfo, model);
		}
		bizTransitCopymeterinfoService.save(bizTransitCopymeterinfo);
		addMessage(redirectAttributes, "保存转供抄表信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitCopymeterinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizTransitCopymeterinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizTransitCopymeterinfo bizTransitCopymeterinfo, RedirectAttributes redirectAttributes) {
		bizTransitCopymeterinfoService.delete(bizTransitCopymeterinfo);
		addMessage(redirectAttributes, "删除转供抄表信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitCopymeterinfo/?repage";
	}

	@RequiresPermissions("ele:bizTransitCopymeterinfo:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(BizTransitCopymeterinfo bizTransitCopymeterinfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "转供抄表信息"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<BizTransitCopymeterinfo> page = bizTransitCopymeterinfoService.findPage(new Page<BizTransitCopymeterinfo>(request, response, -1), bizTransitCopymeterinfo);
			new ExportExcel("转供抄表信息", BizTransitCopymeterinfo.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出转供抄表信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitCopymeterinfo/?repage";
	}

	@RequiresPermissions("ele:bizTransitCopymeterinfo:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/ele/bizTransitCopymeterinfo/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BizTransitCopymeterinfo> list = ei.getDataList(BizTransitCopymeterinfo.class);
			for (BizTransitCopymeterinfo bizTransitCopymeterinfo : list) {
				try {
					if (true) {
						BeanValidators.validateWithException(validator, bizTransitCopymeterinfo);
						bizTransitCopymeterinfoService.save(bizTransitCopymeterinfo);
						successNum++;
					} else {
						failureMsg.append("<br/>");
						failureNum++;
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					String exceptionMsg = ex.getMessage();
					if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
						exceptionMsg = "导入数据违反主外键约束！";
					}
					failureMsg.append("导入失败：" + exceptionMsg);
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitCopymeterinfo/?repage";
	}

	@RequiresPermissions("ele:bizTransitCopymeterinfo:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "转供抄表信息导入模板.xlsx";
			List<BizTransitCopymeterinfo> list = Lists.newArrayList();
			list.add((BizTransitCopymeterinfo) InitImportData.getImportData(new BizTransitCopymeterinfo()));
			new ExportExcel("转供抄表信息", BizTransitCopymeterinfo.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "转供抄表信息导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitCopymeterinfo/?repage";
	}
}