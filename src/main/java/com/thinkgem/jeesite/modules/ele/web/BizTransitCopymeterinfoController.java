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
import com.thinkgem.jeesite.modules.ele.entity.BizTransitCopymeterinfo;
import com.thinkgem.jeesite.modules.ele.service.BizTransitCopymeterinfoService;

/**
 * 转供抄表信息Controller
 * @author ws
 * @version 2017-11-06
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

}