package com.pingdu.controler.warningControler;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pingdu.entity.warning.Warning;
import com.pingdu.service.warningService.WarningService;
import com.pingdu.view.WarningView;

@Controller
@RequestMapping(value = "warning")
public class WarningControler {
	@Autowired
	private WarningService service;
	
	
	public class WarningResult{
		List<WarningView> warnings;
		int page;
		int sumPage;
		public List<WarningView> getWarnings() {
			return warnings;
		}
		public void setWarnings(List<WarningView> warnings) {
			this.warnings = warnings;
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) {
			this.page = page;
		}
		public int getSumPage() {
			return sumPage;
		}
		public void setSumPage(int sumPage) {
			this.sumPage = sumPage;
		}
		
	}

	/**
	 * 报警信息列表
	 */
	@RequestMapping(value = "getWarningList", method = RequestMethod.GET)
	@ResponseBody
	public WarningResult getWarningList(@RequestParam("page") int page/* 页数 */) {
		int sum = service.sumPage();
		List<WarningView> warnings = service.getWarningList(page);
		WarningResult warningResult = new WarningResult();
		warningResult.setSumPage(sum);
		warningResult.setPage(page);
		warningResult.setWarnings(warnings);
		return warningResult;
		
	}

	/**
	 * 删除报警信息
	 */
	@RequestMapping(value = "deleteWarning", method = RequestMethod.GET)
	@ResponseBody
	public boolean delete(@RequestParam("warningCode") Integer warningCode) {
		
		return service.deleteByWarningCode(warningCode);
	}

	/**
	 * 查询报警信息
	 */
	@RequestMapping(value = "searchWarning", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam("keyWord") String keyWord, @RequestParam("searchType") String searchType,
			@RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime,
			@RequestParam("page") int page/* 页数 */) {
		int sum = service.searchPage(keyWord, searchType, beginTime, endTime);
		
		List<WarningView> warnings = service.searchByKey(keyWord, searchType, beginTime, endTime, page);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("warning/searchWarning");
		mav.addObject("warnings", warnings);
		mav.addObject("page", page);
		mav.addObject("lastpage", sum);
		return mav;
	}

	/* app端接口 */
	/**
	 * 获得报警标题
	 * 
	 * @return
	 */
	@RequestMapping(value = "appGetWarningTitle", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getWarningTitle() {
		Map<String, Object> map = service.getLastWarningTitle();
		return map;
	}

	/**
	 * 获取报警详细信息
	 * 
	 * @param warningTitle
	 * @return
	 */
	@RequestMapping(value = "appGetWarningInfo", method = RequestMethod.GET)
	@ResponseBody
	public WarningView getWarningInfo(@RequestParam("warningCode") Integer warningCode) {
		WarningView warn = service.getWarningInfo(warningCode);
		return warn;
	}

	/**
	 * 添加报警信息的接口 成功返回"success",失败返回"failed"
	 * 
	 * @param warningTitle
	 * @param warningPerson
	 * @param warningContent
	 * @return
	 */
	@RequestMapping(value = "appAddWarning", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> sendWarning(@RequestParam("warningTitle") String warningTitle,
			@RequestParam("warningPerson") String userName,
			@RequestParam("warningContent") String warningInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		Warning wa = new Warning();
		wa.setWarningTitle(warningTitle);
		wa.setUserName(userName);
		wa.setWarningInfo(warningInfo);
		
		Date nowTime = new Date(System.currentTimeMillis());
		wa.setWarningDate(nowTime.toString());
		map.put("result", service.addWarning(wa));
		return map;
	}

	/**
	 * 获取报警列表
	 */
	@RequestMapping(value = "appGetWarningList", method = RequestMethod.GET)
	@ResponseBody
	public List<WarningView> appGetWarningList(@RequestParam("page") int page) {
		List<WarningView> warns = service.getWarningList(page);
		return warns;

	}
}
