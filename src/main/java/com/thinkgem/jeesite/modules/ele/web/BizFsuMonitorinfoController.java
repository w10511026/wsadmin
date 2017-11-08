package com.thinkgem.jeesite.modules.ele.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ele.entity.BizFsuMonitorinfo;
import com.thinkgem.jeesite.modules.ele.service.BizFsuMonitorinfoService;

/**
 * FSU监控信息Controller
 * @author ws
 * @version 2017-11-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizFsuMonitorinfo")
public class BizFsuMonitorinfoController extends BaseController {

	@Autowired
	private BizFsuMonitorinfoService bizFsuMonitorinfoService;
	
	@ModelAttribute
	public BizFsuMonitorinfo get(@RequestParam(required=false) String id) {
		BizFsuMonitorinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizFsuMonitorinfoService.get(id);
		}
		if (entity == null){
			entity = new BizFsuMonitorinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizFsuMonitorinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizFsuMonitorinfo bizFsuMonitorinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizFsuMonitorinfo> page = bizFsuMonitorinfoService.findPage(new Page<BizFsuMonitorinfo>(request, response), bizFsuMonitorinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizFsuMonitorinfoList";
	}

	@RequiresPermissions("ele:bizFsuMonitorinfo:view")
	@RequestMapping(value = "form")
	public String form(BizFsuMonitorinfo bizFsuMonitorinfo, Model model) {
		model.addAttribute("bizFsuMonitorinfo", bizFsuMonitorinfo);
		return "modules/ele/bizFsuMonitorinfoForm";
	}

	@RequiresPermissions("ele:bizFsuMonitorinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizFsuMonitorinfo bizFsuMonitorinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizFsuMonitorinfo)){
			return form(bizFsuMonitorinfo, model);
		}
		bizFsuMonitorinfoService.save(bizFsuMonitorinfo);
		addMessage(redirectAttributes, "保存FSU监控信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizFsuMonitorinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizFsuMonitorinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizFsuMonitorinfo bizFsuMonitorinfo, RedirectAttributes redirectAttributes) {
		bizFsuMonitorinfoService.delete(bizFsuMonitorinfo);
		addMessage(redirectAttributes, "删除FSU监控信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizFsuMonitorinfo/?repage";
	}

}