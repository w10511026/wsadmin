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
import com.thinkgem.jeesite.modules.ele.entity.BizReplaceCheckinfo;
import com.thinkgem.jeesite.modules.ele.service.BizReplaceCheckinfoService;

/**
 * 代维巡检信息Controller
 * @author ws
 * @version 2017-11-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizReplaceCheckinfo")
public class BizReplaceCheckinfoController extends BaseController {

	@Autowired
	private BizReplaceCheckinfoService bizReplaceCheckinfoService;
	
	@ModelAttribute
	public BizReplaceCheckinfo get(@RequestParam(required=false) String id) {
		BizReplaceCheckinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizReplaceCheckinfoService.get(id);
		}
		if (entity == null){
			entity = new BizReplaceCheckinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizReplaceCheckinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizReplaceCheckinfo bizReplaceCheckinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizReplaceCheckinfo> page = bizReplaceCheckinfoService.findPage(new Page<BizReplaceCheckinfo>(request, response), bizReplaceCheckinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizReplaceCheckinfoList";
	}

	@RequiresPermissions("ele:bizReplaceCheckinfo:view")
	@RequestMapping(value = "form")
	public String form(BizReplaceCheckinfo bizReplaceCheckinfo, Model model) {
		model.addAttribute("bizReplaceCheckinfo", bizReplaceCheckinfo);
		return "modules/ele/bizReplaceCheckinfoForm";
	}

	@RequiresPermissions("ele:bizReplaceCheckinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizReplaceCheckinfo bizReplaceCheckinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizReplaceCheckinfo)){
			return form(bizReplaceCheckinfo, model);
		}
		bizReplaceCheckinfoService.save(bizReplaceCheckinfo);
		addMessage(redirectAttributes, "保存代维巡检信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizReplaceCheckinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizReplaceCheckinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizReplaceCheckinfo bizReplaceCheckinfo, RedirectAttributes redirectAttributes) {
		bizReplaceCheckinfoService.delete(bizReplaceCheckinfo);
		addMessage(redirectAttributes, "删除代维巡检信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizReplaceCheckinfo/?repage";
	}

}