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
import com.thinkgem.jeesite.modules.ele.entity.BizElectricShareinfo;
import com.thinkgem.jeesite.modules.ele.service.BizElectricShareinfoService;

/**
 * 电流分摊信息Controller
 * @author ws
 * @version 2017-11-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizElectricShareinfo")
public class BizElectricShareinfoController extends BaseController {

	@Autowired
	private BizElectricShareinfoService bizElectricShareinfoService;
	
	@ModelAttribute
	public BizElectricShareinfo get(@RequestParam(required=false) String id) {
		BizElectricShareinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizElectricShareinfoService.get(id);
		}
		if (entity == null){
			entity = new BizElectricShareinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizElectricShareinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizElectricShareinfo bizElectricShareinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizElectricShareinfo> page = bizElectricShareinfoService.findPage(new Page<BizElectricShareinfo>(request, response), bizElectricShareinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizElectricShareinfoList";
	}

	@RequiresPermissions("ele:bizElectricShareinfo:view")
	@RequestMapping(value = "form")
	public String form(BizElectricShareinfo bizElectricShareinfo, Model model) {
		model.addAttribute("bizElectricShareinfo", bizElectricShareinfo);
		return "modules/ele/bizElectricShareinfoForm";
	}

	@RequiresPermissions("ele:bizElectricShareinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizElectricShareinfo bizElectricShareinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizElectricShareinfo)){
			return form(bizElectricShareinfo, model);
		}
		bizElectricShareinfoService.save(bizElectricShareinfo);
		addMessage(redirectAttributes, "保存电流分摊信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizElectricShareinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizElectricShareinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizElectricShareinfo bizElectricShareinfo, RedirectAttributes redirectAttributes) {
		bizElectricShareinfoService.delete(bizElectricShareinfo);
		addMessage(redirectAttributes, "删除电流分摊信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizElectricShareinfo/?repage";
	}

}