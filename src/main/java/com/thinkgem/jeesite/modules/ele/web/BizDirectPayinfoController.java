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
import com.thinkgem.jeesite.modules.ele.entity.BizDirectPayinfo;
import com.thinkgem.jeesite.modules.ele.service.BizDirectPayinfoService;

/**
 * 直供缴费信息Controller
 * @author ws
 * @version 2017-11-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizDirectPayinfo")
public class BizDirectPayinfoController extends BaseController {

	@Autowired
	private BizDirectPayinfoService bizDirectPayinfoService;
	
	@ModelAttribute
	public BizDirectPayinfo get(@RequestParam(required=false) String id) {
		BizDirectPayinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizDirectPayinfoService.get(id);
		}
		if (entity == null){
			entity = new BizDirectPayinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizDirectPayinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizDirectPayinfo bizDirectPayinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizDirectPayinfo> page = bizDirectPayinfoService.findPage(new Page<BizDirectPayinfo>(request, response), bizDirectPayinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizDirectPayinfoList";
	}

	@RequiresPermissions("ele:bizDirectPayinfo:view")
	@RequestMapping(value = "form")
	public String form(BizDirectPayinfo bizDirectPayinfo, Model model) {
		model.addAttribute("bizDirectPayinfo", bizDirectPayinfo);
		return "modules/ele/bizDirectPayinfoForm";
	}

	@RequiresPermissions("ele:bizDirectPayinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizDirectPayinfo bizDirectPayinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizDirectPayinfo)){
			return form(bizDirectPayinfo, model);
		}
		bizDirectPayinfoService.save(bizDirectPayinfo);
		addMessage(redirectAttributes, "保存直供缴费信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizDirectPayinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizDirectPayinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizDirectPayinfo bizDirectPayinfo, RedirectAttributes redirectAttributes) {
		bizDirectPayinfoService.delete(bizDirectPayinfo);
		addMessage(redirectAttributes, "删除直供缴费信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizDirectPayinfo/?repage";
	}

}