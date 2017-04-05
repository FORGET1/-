package com.pingdu.controler.noticeControler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingdu.baseModel.BaseController;
import com.pingdu.baseModel.Root;
import com.pingdu.service.noticeService.NoticeService;
import com.pingdu.utility.*;
import com.pingdu.view.ManyLlistToJSON;
@RequestMapping(value = "notice")
@Controller
public class NoticeControler extends BaseController {

	@Autowired
	private Root root;
	@Autowired
	private NoticeService noticeService;

	/**
	 * page,userID 查看接收的通知列表 notice:{ noticeTitle：通知标题 noticeIssuedTime：发布日期
	 * noticeSender：发送人 noticeRecipient：接收人 hasReadNum：已读 recipientNum：接收者者数量 }
	 * page，lastpage
	 * 
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getReceivedNoticeList", method = RequestMethod.GET)
	public @ResponseBody Root getReceivedNoticeList(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response, int page,
			int userID) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {
			int sumpage = noticeService.NoticeReceivedPage(userID);
			if (page > sumpage || page == 0) {
				page = sumpage;
			}
			List noticeRecList = noticeService.getReceivedNoticeList(userID,
					page);
			if (noticeRecList.size() == 0 && sumpage == 1) {
				page = 0;
				sumpage = 0;
			}
			ManyLlistToJSON followGive = new ManyLlistToJSON();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("lastPage", sumpage);
			map.put("page", page);
			followGive.setPage(map);
			followGive.setDataList(noticeRecList);
			root.setObject(followGive);
			root.setMessage(0, "");
			return root;

		} catch (Exception e) {
			root.setMessage(1, "服务器异常");
			return null;
		}

	}

	/**
	 * 查看发送的通知列表
	 * 
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param page
	 * @param userID
	 * @return
	 */
	@RequestMapping(value = "getSentNoticeList", method = RequestMethod.GET)
	public @ResponseBody Root getSentNoticeList(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response, int page,
			int userID) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {
			int sumpage = noticeService.NoticeSendPage(userID);
			if (page > sumpage || page == 0) {
				page = sumpage;
			}
			List noticeSendList = noticeService.getSendNoticeList(userID, page);
			if (noticeSendList.size() == 0 && sumpage == 1) {
				page = 0;
				sumpage = 0;
			}
			ManyLlistToJSON followGive = new ManyLlistToJSON();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("lastPage", sumpage);
			map.put("page", page);
			followGive.setPage(map);
			followGive.setDataList(noticeSendList);
			root.setObject(followGive);
			root.setMessage(0, "");
			return root;

		} catch (Exception e) {
			root.setMessage(1, "服务器异常");
			return null;
		}

	}

	/**
	 * 查看具体通知信息
	 * 
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param noticeTitle
	 * @return
	 */
	@RequestMapping(value = "getNoticeInfo", method = RequestMethod.GET)
	public @ResponseBody Root getNoticeInfo(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			int noticeCode) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {
			List noticeAllList = noticeService.getNoticeAllList(noticeCode);
			root.setObject(noticeAllList);
			root.setMessage(0, "");
			return root;
		} catch (Exception e) {
			root.setMessage(1, "服务器异常");
			return null;
		}

	}

	/**
	 * 发送通知 notice:{ noticeTitle, noticeType, noticeRecipient, noticeContent }
	 * userID
	 * 
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "sendNotice", method = RequestMethod.POST)
	public @ResponseBody Root sendNotice(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			String recvType, int recvCode,
			String senderType, int userID, 
			String noticeTitle,String noticeContent, int noticeTypeCode) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {
			noticeService.insertSendNotice(recvType, recvCode, senderType, userID, noticeTitle, noticeContent, noticeTypeCode);

			root.setMessage(0, "SUCCESS");
			return root;

		} catch (Exception e) {
			root.setMessage(1, "FAILED");
			return null;
		}

	}
/**
 * 转发通知
 * @param modelMap
 * @param request
 * @param response
 * @param noticeCode
 * @param recvType
 * @param recvCode
 * @param senderType
 * @param userID
 * @param noticeRecipient
 * @return
 */
	@RequestMapping(value = "retransmitNotice", method = RequestMethod.POST)
	public @ResponseBody Root retransmitNotice(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			int noticeCode, String recvType, int recvCode, String senderType,
			int userID, int noticeRecipient) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {
			noticeService.insertBackNotice(noticeCode, recvType, recvCode,
					senderType, userID, noticeRecipient);
			return root;

		} catch (Exception e) {
			root.setMessage(1, "FAILED");
			return null;
		}

	}

	/**
	 * 查看阅读情况
	 * 
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param noticeTitle
	 * @return
	 */
	@RequestMapping(value = "getReadCon", method = RequestMethod.GET)
	public @ResponseBody Root getReadCon(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			String noticeTitle, int page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {
			int sumpage = noticeService.NoticeCheckPage(noticeTitle);
			if (page > sumpage || page == 0) {
				page = sumpage;
			}
			List noticeCheckList = noticeService.checkNoticeReadList(
					noticeTitle, page);
			if (noticeCheckList.size() == 0 && sumpage == 1) {
				page = 0;
				sumpage = 0;
			}
			ManyLlistToJSON followGive = new ManyLlistToJSON();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("page", page);
			followGive.setPage(map);
			followGive.setDataList(noticeCheckList);
			root.setObject(followGive);

			root.setMessage(0, "SUCCESS");
			return root;

		} catch (Exception e) {
			root.setMessage(1, "FAILED");
			return null;
		}

	}
	/**
	 * 查看未读的通知列表 app
	 * @param userCode
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "getUnReadNoticeList", method = RequestMethod.GET)
	public @ResponseBody Root getUnReadNoticeList(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			int userCode , int page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {
			int sumpage = noticeService.NoticeUnReadPage(userCode);
			if (page > sumpage || page == 0) {
				page = sumpage;
			}
			List noticeRecList = noticeService.getUnReadNoticeList(userCode,
					page);
			if (noticeRecList.size() == 0 && sumpage == 1) {
				page = 0;
				sumpage = 0;
			}
			root.setObject(noticeRecList);
			root.setMessage(0, "");
			return root;

		} catch (Exception e) {
			root.setMessage(1, "服务器异常");
			return null;
		}

	}
	/**
	 * 查看全部的通知列表 app
	 * @param userCode
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "getAppNoticeList", method = RequestMethod.GET)
	public @ResponseBody Root getAppNoticeList(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			int userCode , int page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {
			int sumpage = noticeService.NoticePageapp(userCode);
			if (page > sumpage || page == 0) {
				page = sumpage;
			}
			List noticeRecList = noticeService.getAppNoticeList(userCode,
					page);
			if (noticeRecList.size() == 0 && sumpage == 1) {
				page = 0;
				sumpage = 0;
			}
			root.setObject(noticeRecList);
			root.setMessage(0, "");
			return root;

		} catch (Exception e) {
			root.setMessage(1, "服务器异常");
			return null;
		}

	}
	/**
	 * 查看通知详情 app
	 * @param userCode
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "getAppNoticeInFoList", method = RequestMethod.GET)
	public @ResponseBody Root getAppNoticeInFoList(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			int userCode , int noticeCode) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {
			List noticeRecList = noticeService.getAppNoticeInFoList(userCode,
					noticeCode);
			root.setObject(noticeRecList);
			root.setMessage(0, "");
			return root;

		} catch (Exception e) {
			root.setMessage(1, "服务器异常");
			return null;
		}

	}

	
	/*********************************************************/
/**
 *  * 发送的具体通知查询
	 * 1：发送方类型
	 * 2:接收方类型
 * @param modelMap
 * @param request
 * @param response
 * @param userID
 * @param searchType
 * @param keyWord
 * @param key 1发送 2接收
 * @return
 */
	@RequestMapping(value = "getSendOrRecSearch", method = RequestMethod.GET)
	public @ResponseBody Root getSendNoticeSearch(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			int userID,String searchType,String keyWord,int key) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {
			if(key==1){
				List noticeSendList = noticeService.getSendNoticeSearchList(userID,searchType,keyWord);
				root.setObject(noticeSendList);
				root.setMessage(0, "");
			}else if (key==2) {
				List noticeRecList = noticeService.getRecNoticeSearchList(userID, searchType, keyWord);
				root.setObject(noticeRecList);
				root.setMessage(0, "");
			}
			return root;
		} catch (Exception e) {
			root.setMessage(1, "服务器异常");
			return null;
		}

	}
	

	/**
	 * 删除接收通知列表
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param noticeCode
	 * @return
	 */
	@RequestMapping(value = "deleteRecORSendNotice", method = RequestMethod.GET)
	public @ResponseBody Root deleteRecNotice(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			int noticeCode) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {
			
		
				noticeService.deletenoticerinfoSendORec(noticeCode);
			
			
		
				
				root.setMessage(0, "");
				return root;
		} catch (Exception e) {
			// TODO: handle exception
			root.setMessage(1, "");
			return null;
		}
		
	}
	/**
	 * 获得全部的列表
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param userID
	 * @return
	 */
	@RequestMapping(value = "getAllZFItem", method = RequestMethod.GET)
	public @ResponseBody Root getAllZFItem(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			int userID) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {
			int NowUserRole=noticeService.getPDNowUuser(userID);
			List NoticeItemList=null;
			if (NowUserRole<4) {
				NoticeItemList=noticeService.selectItemZFTonice(userID);
			}
			ManyLlistToJSON json=new ManyLlistToJSON();
			Map<String ,Integer >map=new HashMap<String, Integer>();
			map.put("UserRole", NowUserRole);
			json.setPage(map);
			json.setDataList(NoticeItemList);
			root.setObject(json);
			root.setMessage(0, "");
			return root;
		} catch (Exception e) {
			// TODO: handle exception
			root.setMessage(1, "服务器异常");
			return null;
		}
	}

	/**
	 * 文件发送和转发时获取发送列表
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param userID
	 * @return
	 */
	@RequestMapping(value = "getSenORBackNoticeList", method = RequestMethod.GET)
	public @ResponseBody Root getSenORBackNoticeList(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			int page,int noticeCode) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {
			int sumPage=noticeService.NoticeRecListSumPage(noticeCode);
			if (page > sumPage || page == 0) {
				page = sumPage;
			}
			List getSenORBackNoticeList=noticeService.RecUserAndIsRead(noticeCode, page);
			if (getSenORBackNoticeList.size() == 0 && sumPage == 1) {
				page = 0;
				sumPage = 0;
			}
			ManyLlistToJSON json=new ManyLlistToJSON();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("page", page);
			map.put("sumpage",sumPage);
			json.setPage(map);
			json.setDataList(getSenORBackNoticeList);
			root.setObject(json);
			root.setMessage(0, "");
			return root;
		} catch (Exception e) {
			// TODO: handle exception
			root.setMessage(1, "服务器异常");
			return null;
		}
		
		
	
	
	
	}
	
	
	
	
	
}
