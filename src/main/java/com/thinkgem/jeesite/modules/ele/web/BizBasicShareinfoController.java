package com.thinkgem.jeesite.modules.ele.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import java.util.List;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.logrecord.LogRecordUtil;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.ele.util.InitImportData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.dto.AjaxMsg;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ele.entity.BizBasicShareinfo;
import com.thinkgem.jeesite.modules.ele.service.BizBasicShareinfoService;

/**
 * 基础电流分摊信息Controller
 * @author ws
 * @version 2018-02-09
 */
@Controller
@RequestMapping(value = "${adminPath}/ele/bizBasicShareinfo")
public class BizBasicShareinfoController extends BaseController {

	@Autowired
	private BizBasicShareinfoService bizBasicShareinfoService;
	
	@ModelAttribute
	public BizBasicShareinfo get(@RequestParam(required=false) String id) {
		BizBasicShareinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizBasicShareinfoService.get(id);
		}
		if (entity == null){
			entity = new BizBasicShareinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("ele:bizBasicShareinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizBasicShareinfo bizBasicShareinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizBasicShareinfo> page = bizBasicShareinfoService.findPage(new Page<BizBasicShareinfo>(request, response), bizBasicShareinfo); 
		model.addAttribute("page", page);
		return "modules/ele/bizBasicShareinfoList";
	}

	@RequiresPermissions("ele:bizBasicShareinfo:view")
	@RequestMapping(value = "form")
	public String form(BizBasicShareinfo bizBasicShareinfo, Model model) {
		model.addAttribute("bizBasicShareinfo", bizBasicShareinfo);
		return "modules/ele/bizBasicShareinfoForm";
	}

	@RequiresPermissions("ele:bizBasicShareinfo:edit")
	@RequestMapping(value = "save")
    public String save(BizBasicShareinfo bizBasicShareinfo, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        BizBasicShareinfo oldObj = null;
        try {
            if (!beanValidator(model, bizBasicShareinfo)){
                return form(bizBasicShareinfo, model);
            }
            if (!bizBasicShareinfo.getIsNewRecord()) {
                oldObj = bizBasicShareinfoService.get(bizBasicShareinfo.getId());
            }
            bizBasicShareinfoService.save(bizBasicShareinfo);
            addMessage(redirectAttributes, "保存基础电流分摊信息成功");
        } catch (Exception ex) {
            String exceptionMsg = ex.getMessage();
            if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
                exceptionMsg = "导入失败：数据违反主外键约束！";
            }
            addMessage(redirectAttributes, exceptionMsg);
        }
        if (!bizBasicShareinfo.getIsNewRecord()) {
            String result = LogRecordUtil.saveUpdateLog(oldObj, bizBasicShareinfo);
            request.setAttribute("updateLog", result);
        }
		bizBasicShareinfoService.reCalcData(bizBasicShareinfo);
        return "redirect:"+Global.getAdminPath()+"/ele/bizBasicShareinfo/?repage";
    }
	
	@RequiresPermissions("ele:bizBasicShareinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(BizBasicShareinfo bizBasicShareinfo, RedirectAttributes redirectAttributes) {
		try {
			bizBasicShareinfoService.delete(bizBasicShareinfo);
			addMessage(redirectAttributes, "删除基础电流分摊信息成功");
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "删除失败：数据违反主外键约束！";
			}
			addMessage(redirectAttributes, exceptionMsg);
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizBasicShareinfo/?repage";
	}

	@RequiresPermissions("ele:bizBasicShareinfo:edit")
	@RequestMapping(value = "deletebatch")
	@ResponseBody
	public AjaxMsg deleteBatch(@RequestParam("ids[]")List<String> ids) {
		AjaxMsg ajaxMsg = new AjaxMsg(String.valueOf(HttpStatus.OK), "删除基础电流分摊信息成功");
		try {
			for (String id : ids) {
				BizBasicShareinfo bizBasicShareinfo = new BizBasicShareinfo();
				bizBasicShareinfo.setId(id);
				bizBasicShareinfoService.delete(bizBasicShareinfo);
			}
		} catch (Exception ex) {
			String exceptionMsg = ex.getMessage();
			if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
				exceptionMsg = "删除失败：数据违反主外键约束！";
			}
			ajaxMsg = new AjaxMsg(String.valueOf(HttpStatus.BAD_REQUEST), exceptionMsg);
		}
		return ajaxMsg;
	}

	@RequiresPermissions("ele:bizBasicShareinfo:view")
	@RequestMapping(value = "export", method= RequestMethod.POST)
	public String exportFile(BizBasicShareinfo bizBasicShareinfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "基础电流分摊信息"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			List<BizBasicShareinfo> list = bizBasicShareinfoService.findList(bizBasicShareinfo);
			new ExportExcel("基础电流分摊信息", BizBasicShareinfo.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出基础电流分摊信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizBasicShareinfo/?repage";
	}

	@RequiresPermissions("ele:bizBasicShareinfo:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/ele/bizBasicShareinfo/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<BizBasicShareinfo> list = ei.getDataList(BizBasicShareinfo.class);
			for (BizBasicShareinfo bizBasicShareinfo : list) {
                try {
                    if (true) {
                        BeanValidators.validateWithException(validator, bizBasicShareinfo);
                        double meterMQ = 0;
                        double meterTQ = 0;
                        double meterUQ = 0;
                        for (BizBasicShareinfo shareinfo : list) {
                            if (shareinfo.getRohistnum().equals(bizBasicShareinfo.getRohistnum())) {
                                meterMQ += null != shareinfo.getSinglestatmq() ? shareinfo.getSinglestatmq() : 0;
                                meterTQ += null != shareinfo.getSinglestattq() ? shareinfo.getSinglestattq() : 0;
                                meterUQ += null != shareinfo.getSinglestatuq() ? shareinfo.getSinglestatuq() : 0;
                            }
                        }
                        //共表移动
                        bizBasicShareinfo.setSharedmetermq(meterMQ);
                        //共表电信
                        bizBasicShareinfo.setSharedmetertq(meterTQ);
                        //共表联通
                        bizBasicShareinfo.setSharedmeteruq(meterUQ);
                        //单站总电流
						double singleTotal = bizBasicShareinfo.getSinglestatmq() + bizBasicShareinfo.getSinglestattq() + bizBasicShareinfo.getSinglestatuq();
						bizBasicShareinfo.setSingletotal(singleTotal);
						//共表总电流
						double shareTotal = bizBasicShareinfo.getSharedmetermq() + bizBasicShareinfo.getSharedmetertq() + bizBasicShareinfo.getSharedmeteruq();
						bizBasicShareinfo.setSharedmetertotal(shareTotal);
						//一表多站信息
						bizBasicShareinfo.setSingletomanystatinfo(singleTotal == shareTotal ? "否" : "是");
                        bizBasicShareinfoService.save(bizBasicShareinfo);
                        successNum++;
                    } else {
                        failureMsg.append("<br/>");
                        failureNum++;
                    }
                } catch (ConstraintViolationException ex) {
					failureMsg.append("导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message+"; ");
						failureNum++;
					}
				} catch (Exception ex) {
					String exceptionMsg = ex.getMessage();
					if (exceptionMsg.contains("MySQLIntegrityConstraintViolationException")) {
						exceptionMsg = "导入数据违反主外键约束！";
					}
					failureMsg.append("导入失败：" + InitImportData.getPrimaryValue(bizBasicShareinfo) + "=>" + exceptionMsg +"; ");
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizBasicShareinfo/?repage";
	}

	@RequiresPermissions("ele:bizBasicShareinfo:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "基础电流分摊信息导入模板.xlsx";
			List<BizBasicShareinfo> list = Lists.newArrayList();
			list.add((BizBasicShareinfo) InitImportData.getImportData(new BizBasicShareinfo()));
			new ExportExcel("基础电流分摊信息", BizBasicShareinfo.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "基础电流分摊信息导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/ele/bizBasicShareinfo/?repage";
	}

}