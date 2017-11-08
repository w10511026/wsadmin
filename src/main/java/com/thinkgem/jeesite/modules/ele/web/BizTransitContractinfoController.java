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
import com.thinkgem.jeesite.modules.ele.entity.BizTransitContractinfo;
import com.thinkgem.jeesite.modules.ele.service.BizTransitContractinfoService;

/**
 * 转供合同信息Controller
 * @author ws
 * @version 2017-11-06
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

}