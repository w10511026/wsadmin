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
import com.thinkgem.jeesite.modules.ele.entity.BizTransitReceiptinfo;
import com.thinkgem.jeesite.modules.ele.service.BizTransitReceiptinfoService;

/**
 * 转供回款信息Controller
 * @author ws
 * @version 2017-11-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizTransitReceiptinfo")
public class BizTransitReceiptinfoController extends BaseController {

	@Autowired
	private BizTransitReceiptinfoService bizTransitReceiptinfoService;
	
	@ModelAttribute
	public BizTransitReceiptinfo get(@RequestParam(required=false) String id) {
		BizTransitReceiptinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizTransitReceiptinfoService.get(id);
		}
		if (entity == null){
			entity = new BizTransitReceiptinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizTransitReceiptinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizTransitReceiptinfo bizTransitReceiptinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizTransitReceiptinfo> page = bizTransitReceiptinfoService.findPage(new Page<BizTransitReceiptinfo>(request, response), bizTransitReceiptinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizTransitReceiptinfoList";
	}

	@RequiresPermissions("ele:bizTransitReceiptinfo:view")
	@RequestMapping(value = "form")
	public String form(BizTransitReceiptinfo bizTransitReceiptinfo, Model model) {
		model.addAttribute("bizTransitReceiptinfo", bizTransitReceiptinfo);
		return "modules/ele/bizTransitReceiptinfoForm";
	}

	@RequiresPermissions("ele:bizTransitReceiptinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizTransitReceiptinfo bizTransitReceiptinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizTransitReceiptinfo)){
			return form(bizTransitReceiptinfo, model);
		}
		bizTransitReceiptinfoService.save(bizTransitReceiptinfo);
		addMessage(redirectAttributes, "保存转供回款信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitReceiptinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizTransitReceiptinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizTransitReceiptinfo bizTransitReceiptinfo, RedirectAttributes redirectAttributes) {
		bizTransitReceiptinfoService.delete(bizTransitReceiptinfo);
		addMessage(redirectAttributes, "删除转供回款信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizTransitReceiptinfo/?repage";
	}

}