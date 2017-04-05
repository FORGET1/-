package com.pingdu.controler;

import java.io.IOException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pingdu.service.ReportService;
import com.pingdu.service.userService.UserService;
import com.pingdu.utility.CaptchaUtil;
import com.pingdu.view.Report;

@Controller
public class IndexCtrl {
	@Autowired
	private UserService service;

	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value="getReport")
	@ResponseBody
	public List<Report> getReport() {
		
		return reportService.getReport();
	}
	/**
	 * 首页
	 */
	@RequestMapping(value = "index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/login");
		return mav;
	}


	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestParam("userCode") String userCode, @RequestParam("captcha") String captcha,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "init");
		String random = (String) (session.getAttribute("randomString"));
		if (!random.toUpperCase().equals(captcha.toUpperCase())) {
			map.put("status", "4003");
			map.put("userPwd", "");
			map.put("userSalt", "");
		} else if (!service.isExist(userCode)) {
			map.put("status", "4001");
			map.put("userPwd", "");
			map.put("userSalt", "");
		} else if (!service.isCanLogin(userCode)) {
			map.put("status", "4002");
			map.put("userPwd", "");
			map.put("userSalt", "");
		} else {
			HashMap<String, Object> getMap = service.getUserPwdAndSalt(userCode);
			map.put("status", "4000");
			map.put("userPwd", getMap.get("pwd"));
			map.put("userSalt", getMap.get("salt"));
			map.put("userType", getMap.get("role"));
			switch ((int)getMap.get("role")) {
			case 1:
				map.put("manageCode", "super");
				break;
			case 2:
				map.put("manageCode", "");
				break;
			default:
				map.put("manageCode", getMap.get("manageCode"));
				break;
			}
			
		}
		return map;
	}

	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	@ResponseBody
	public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CaptchaUtil.outputCaptcha(request, response);
	}

	@RequestMapping(value = "applogin", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> login(@RequestParam("userCode") String userCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (service.isExist(userCode)) {
			HashMap<String, Object> getMap = service.getUserPwdAndSalt(userCode);
			map.put("userPwd", getMap.get("pwd"));
			map.put("salt", getMap.get("salt"));
			map.put("role", getMap.get("role"));
			map.put("manageCode", getMap.get("manageCode"));
		} else {
			map.put("userPwd", "");
			map.put("salt", "");
		}
		return map;
	}

}
