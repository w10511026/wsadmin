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
import com.thinkgem.jeesite.modules.ele.entity.BizSiteMeterinfo;
import com.thinkgem.jeesite.modules.ele.service.BizSiteMeterinfoService;

/**
 * 表站对应信息Controller
 * @author ws
 * @version 2017-11-06
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
		if (!beanValidator(model, bizSiteMeterinfo)){
			return form(bizSiteMeterinfo, model);
		}
		bizSiteMeterinfoService.save(bizSiteMeterinfo);
		addMessage(redirectAttributes, "保存表站对应信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizSiteMeterinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizSiteMeterinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizSiteMeterinfo bizSiteMeterinfo, RedirectAttributes redirectAttributes) {
		bizSiteMeterinfoService.delete(bizSiteMeterinfo);
		addMessage(redirectAttributes, "删除表站对应信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizSiteMeterinfo/?repage";
	}

}