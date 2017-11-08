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
import com.thinkgem.jeesite.modules.ele.entity.BizDirectReceiptinfo;
import com.thinkgem.jeesite.modules.ele.service.BizDirectReceiptinfoService;

/**
 * 直供回款信息Controller
 * @author ws
 * @version 2017-11-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizDirectReceiptinfo")
public class BizDirectReceiptinfoController extends BaseController {

	@Autowired
	private BizDirectReceiptinfoService bizDirectReceiptinfoService;
	
	@ModelAttribute
	public BizDirectReceiptinfo get(@RequestParam(required=false) String id) {
		BizDirectReceiptinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizDirectReceiptinfoService.get(id);
		}
		if (entity == null){
			entity = new BizDirectReceiptinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizDirectReceiptinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizDirectReceiptinfo bizDirectReceiptinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizDirectReceiptinfo> page = bizDirectReceiptinfoService.findPage(new Page<BizDirectReceiptinfo>(request, response), bizDirectReceiptinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizDirectReceiptinfoList";
	}

	@RequiresPermissions("ele:bizDirectReceiptinfo:view")
	@RequestMapping(value = "form")
	public String form(BizDirectReceiptinfo bizDirectReceiptinfo, Model model) {
		model.addAttribute("bizDirectReceiptinfo", bizDirectReceiptinfo);
		return "modules/ele/bizDirectReceiptinfoForm";
	}

	@RequiresPermissions("ele:bizDirectReceiptinfo:edit")
	@RequestMapping(value = "save")
	public String save(BizDirectReceiptinfo bizDirectReceiptinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizDirectReceiptinfo)){
			return form(bizDirectReceiptinfo, model);
		}
		bizDirectReceiptinfoService.save(bizDirectReceiptinfo);
		addMessage(redirectAttributes, "保存直供回款信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizDirectReceiptinfo/?repage";
	}
	
	@RequiresPermissions("ele:bizDirectReceiptinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizDirectReceiptinfo bizDirectReceiptinfo, RedirectAttributes redirectAttributes) {
		bizDirectReceiptinfoService.delete(bizDirectReceiptinfo);
		addMessage(redirectAttributes, "删除直供回款信息成功");
		return "redirect:"+Global.getAdminPath()+"/ele/bizDirectReceiptinfo/?repage";
	}

}