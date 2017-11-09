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
import com.thinkgem.jeesite.modules.ele.entity.BizTransitContractinfo;
import com.thinkgem.jeesite.modules.ele.service.BizTransitContractinfoService;

/**
 * 转供合同信息Controller
 * @author ws
 * @version 2017-11-08
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizTransitContractinfo")
public class BizTransitContractinfoController extends BaseController {

	@Autowired
	private BizTransitContractinfoService bizTransitContractinfoService;
	
	@ModelAttribute
	public BizTransitContractinfo get(@RequestParam(required=false) String id) {
		BizTransitContractinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizTransitContractinfoService.get(id);
		}
		if (entity == null){
			entity = new BizTransitContractinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizTransitContractinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizTransitContractinfo bizTransitContractinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizTransitContractinfo> page = bizTransitContractinfoService.findPage(new Page<BizTransitContractinfo>(request, response), bizTransitContractinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizTransitContractinfoList";
	}

	@RequiresPermissions("ele:bizTransitContractinfo:view")
	@RequestMapping(value = "form")
	public String form(BizTransitContractinfo bizTransitContractinfo, Model model) {
		model.addAttribute("bizTransitContractinfo", bizTransitContractinfo);
		return "modules/ele/bizTransitContractinfoForm";
	}

	@RequiresPermissions("ele:bizTransitContractinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizTransitContractinfo bizTransitContractinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizTransitContractinfo)){
			return form(bizTransitContractinfo, model);
		}
		bizTransitContractinfoService.save(bizTransitContractinfo);
		addMessage(redirectAttributes, "保存转供合同信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitContractinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizTransitContractinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizTransitContractinfo bizTransitContractinfo, RedirectAttributes redirectAttributes) {
		bizTransitContractinfoService.delete(bizTransitContractinfo);
		addMessage(redirectAttributes, "删除转供合同信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitContractinfo/?repage";
	}

	@RequiresPermissions("ele:bizTransitContractinfo:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(BizTransitContractinfo bizTransitContractinfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "转供合同信息"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<BizTransitContractinfo> page = bizTransitContractinfoService.findPage(new Page<BizTransitContractinfo>(request, response, -1), bizTransitContractinfo);
			new ExportExcel("转供合同信息", BizTransitContractinfo.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出转供合同信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitContractinfo/?repage";
	}

	@RequiresPermissions("ele:bizTransitContractinfo:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/ele/bizTransitContractinfo/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BizTransitContractinfo> list = ei.getDataList(BizTransitContractinfo.class);
			for (BizTransitContractinfo bizTransitContractinfo : list) {
				try {
					if (true) {
						BeanValidators.validateWithException(validator, bizTransitContractinfo);
						bizTransitContractinfoService.save(bizTransitContractinfo);
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
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitContractinfo/?repage";
	}

	@RequiresPermissions("ele:bizTransitContractinfo:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "转供合同信息导入模板.xlsx";
			List<BizTransitContractinfo> list = Lists.newArrayList();
			list.add((BizTransitContractinfo) InitImportData.getImportData(new BizTransitContractinfo()));
			new ExportExcel("转供合同信息", BizTransitContractinfo.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "转供合同信息导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitContractinfo/?repage";
	}
}