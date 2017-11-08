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
import com.thinkgem.jeesite.modules.ele.entity.BizTransitPayinfo;
import com.thinkgem.jeesite.modules.ele.service.BizTransitPayinfoService;

/**
 * 转供缴费信息Controller
 * @author ws
 * @version 2017-11-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizTransitPayinfo")
public class BizTransitPayinfoController extends BaseController {

	@Autowired
	private BizTransitPayinfoService bizTransitPayinfoService;
	
	@ModelAttribute
	public BizTransitPayinfo get(@RequestParam(required=false) String id) {
		BizTransitPayinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizTransitPayinfoService.get(id);
		}
		if (entity == null){
			entity = new BizTransitPayinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizTransitPayinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizTransitPayinfo bizTransitPayinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizTransitPayinfo> page = bizTransitPayinfoService.findPage(new Page<BizTransitPayinfo>(request, response), bizTransitPayinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizTransitPayinfoList";
	}

	@RequiresPermissions("ele:bizTransitPayinfo:view")
	@RequestMapping(value = "form")
	public String form(BizTransitPayinfo bizTransitPayinfo, Model model) {
		model.addAttribute("bizTransitPayinfo", bizTransitPayinfo);
		return "modules/ele/bizTransitPayinfoForm";
	}

	@RequiresPermissions("ele:bizTransitPayinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizTransitPayinfo bizTransitPayinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizTransitPayinfo)){
			return form(bizTransitPayinfo, model);
		}
		bizTransitPayinfoService.save(bizTransitPayinfo);
		addMessage(redirectAttributes, "保存转供缴费信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitPayinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizTransitPayinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizTransitPayinfo bizTransitPayinfo, RedirectAttributes redirectAttributes) {
		bizTransitPayinfoService.delete(bizTransitPayinfo);
		addMessage(redirectAttributes, "删除转供缴费信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitPayinfo/?repage";
	}

}