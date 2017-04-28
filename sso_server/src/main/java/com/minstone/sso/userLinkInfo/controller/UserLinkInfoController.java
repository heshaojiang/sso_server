package com.minstone.sso.userLinkInfo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.minstone.common.util.DBManager;
import com.minstone.common.util.Tools;

import com.minstone.sso.userLinkInfo.model.UserLinkInfo;
import com.minstone.sso.userLinkInfo.service.UserLinkInfoService;

/**
 * 单点登录用户信息表 controller 
 * @author hesj
 * @version v1.0
 */

@Controller
@RequestMapping(value = "/userLinkInfo")
public class UserLinkInfoController{
	
	/**
	 * this loger  can be used by controller in anywhere
	 */
	//private Logger log = LoggerFactory.getLogger(UserLinkInfoController.class);
	
	@Autowired
	private UserLinkInfoService userLinkInfoService;
	
	/**
	 * 新增
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add")
	public String add(HttpServletRequest request) {
		return "modules//userLinkInfo/userLinkInfoForm";
	}
	
	/**
	 * 修改
	 * @param code
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit")
	public String edit(@RequestParam(value = "code", required = false) String code, HttpServletRequest request, Model model){
		UserLinkInfo userLinkInfo= null;
		if (Tools.isNotEmpty(code)) {
			userLinkInfo = userLinkInfoService.getById(code);
		} else {
			userLinkInfo = new UserLinkInfo();
		}
		model.addAttribute("userLinkInfo", userLinkInfo);
		return "modules//userLinkInfo/userLinkInfoForm";
	}
	
	/**
	 * 保存
	 * @param userLinkInfo
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "save")
	public boolean save(UserLinkInfo userLinkInfo, Model model, HttpServletRequest request,
			HttpServletResponse response){
		
		
		return true;
	}
	
	/**
	 * 删除
	 * @param ids
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "delete")
	public void delete(String[] ids, HttpServletResponse response){
		
	}
	
}
