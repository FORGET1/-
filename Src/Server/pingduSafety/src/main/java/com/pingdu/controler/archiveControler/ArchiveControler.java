package com.pingdu.controler.archiveControler;

import java.io.File;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pingdu.baseModel.BaseController;
import com.pingdu.baseModel.Root;
import com.pingdu.dao.archiveDao.ArchiveDao;
import com.pingdu.entity.archive.Archive;
import com.pingdu.service.archiveService.archiveService;
import org.apache.log4j.Logger;
import com.pingdu.view.ArchiveImgListView;
import com.pingdu.view.ArchiveItemList;
import com.pingdu.view.ManyLlistToJSON;
import com.pingdu.view.arcAppRoot;
@RequestMapping(value = "archives")
@Controller
public class ArchiveControler extends BaseController {
	@Autowired
	private Root root;
	@Autowired
	private archiveService archiveService;

	private Logger log = Logger.getLogger(ArchiveItemList.class);

	/**
	 * 查看档案列表-杨博韬
	 * 
	 * @param page
	 * @return
	 * 
	 * 
	 *         archives:{companyName,companyCode,archivesName,expirationTime,
	 *         uploadTime,archivesCode},lastPage
	 */

	@RequestMapping(value = "getArchivesList", method = RequestMethod.GET)
	public @ResponseBody Root getArchivesList(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			ArchiveItemList archiveItemList, @RequestParam int page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		log.info("查看档案列表");
		Root root = new Root();
		try {
			Integer sumpage = archiveService.getPag(page);
			if (page > sumpage || page == 0) {
				page = sumpage;
			}
			List archiveList = archiveService.getArchivesSQL(page);
			if (archiveList.size() == 0 && sumpage == 1) {
				page = 0;
				sumpage = 0;
			}

			ManyLlistToJSON followGive = new ManyLlistToJSON();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("page", page);
			map.put("lastpage", sumpage);
			followGive.setPage(map);
			followGive.setDataList(archiveList);
			root.setObject(followGive);
			root.setMessage(0, "");
			return root;

		} catch (Exception e) {
			root.setMessage(1, "服务器异常");
			return null;
		}

	}

	/**
	 * 查看档案图片--杨博韬
	 * 
	 * @param page
	 * @return
	 * 
	 *         companyCode,archivesCode archivesImgSrc
	 */
	@RequestMapping(value = "getArchivesImg", method = { RequestMethod.GET })
	public @ResponseBody Root getArchivesImg(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			int companyCode, int archiveCode) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		log.info("查看档案图片");
		Root root = new Root();
		try {
			@SuppressWarnings("unchecked")
			List<ArchiveImgListView> arcImgList = archiveService
					.getArchivesImgSQL(archiveCode, companyCode);
			root.setObject(arcImgList);
			root.setMessage(0, "");
			return root;
		} catch (Exception e) {
			// TODO: handle exception
			root.setMessage(1, "服务器异常");
			return null;
		}

	}

	/**
	 * 删除档案--杨博韬
	 * 
	 * @param page
	 * @return
	 * 
	 *         companyCode,archivesCode SUCCESS 删除成功，FAILED 删除失败
	 */
	@RequestMapping(value = "deleteArchives", method = { RequestMethod.GET })
	public @ResponseBody Root deleteArchives(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			int companyCode, int archiveCode) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		log.info(" 删除档案");
		Root root = new Root();
		try {
			archiveService.deleteArchivesSQL(archiveCode, companyCode);
			root.setMessage(0, "SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			root.setMessage(1, "FAILED");
			System.out.println("AAA" + e.getMessage());
		}
		return root;

	}

	/**
	 * 查询档案--杨博韬
	 * 
	 * @param page
	 * @return 1档案编号 2企业编号 searchType,keyword
	 *         archives:{companyName,companyCode,archivesName,
	 *         expirationTime,uploadTime,archivesCode},lastPage
	 * 
	 * 
	 */

	@RequestMapping(value = "searchArchives", method = { RequestMethod.GET })
	public @ResponseBody Root searchArchives(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			String searchType, int keyword, int page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		log.info("查询档案");
		Root root = new Root();
		try {
			int sumpage = archiveService.lastPage(searchType, keyword);
			List archiveSpecList = archiveService.searchArchivesSQL(searchType,
					keyword, page);
			// 最后一页

			if (page > sumpage || page == 0) {
				page = sumpage;
			}
			List archiveList = archiveService.getArchivesSQL(page);
			if (archiveList.size() == 0 && sumpage == 1) {
				page = 0;
				sumpage = 0;
			}
			ManyLlistToJSON followGive = new ManyLlistToJSON();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("page", page);
			map.put("lastPage", sumpage);
			followGive.setPage(map);
			followGive.setDataList(archiveSpecList);
			root.setObject(followGive);
			root.setMessage(0, "");
			return root;
		} catch (Exception e) {
			// TODO: handle exception
			root.setMessage(1, "服务器异常");
			return null;
		}

	}
/**
 * 获取已经过期档案
 * @param modelMap
 * @param request
 * @param response
 * @param UserID
 * @param page
 * @return
 * @throws ParseException
 */
	@RequestMapping(value = "appGetExpiredArchiveList", method = RequestMethod.GET)
	@ResponseBody
	public Root appGetExpiredArchiveList(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			int UserID,int page) throws ParseException {
		response.setHeader("Access-Control-Allow-Origin", "*");

		Root root = new Root();

		try {
		
			int sumpage = archiveService.calPageGetAlreadyExp(UserID);// 最后一页
			
			if (page > sumpage || page == 0) {
				page = sumpage;
			}
			List archiveExAppList = archiveService.GetAlreadyExpiredArchiveSQL(UserID,page);
			if (archiveExAppList.size() == 0 && sumpage == 1) {
				page = 0;
				sumpage = 0;
			}
			 Map<String, Integer> betweenDays = archiveService.BetweenDays(UserID,
					ArchiveDao.getNowTime());
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("page", page);
			arcAppRoot arc = new arcAppRoot();
			arc.setDays(betweenDays);
			arc.setPage(map);
			arc.setDataList(archiveExAppList);
			root.setObject(arc);
			root.setMessage(0, "");

			return root;
		} catch (Exception e) {
			// TODO: handle exception
			root.setMessage(1, "服务器异常");
			return null;
		}

	}

	@RequestMapping(value = "appGetAllArchiveList", method = RequestMethod.GET)
	@ResponseBody
	public Root appGetAllArchiveList(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			int UserID, int page) throws ParseException {

		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {
			int sumpage = archiveService.calPageGetAllExp(UserID);// 最后一页
			if (page > sumpage || page == 0) {
				page = sumpage;
			}
			List getAllEXList = archiveService.GetAllExpiredArchiveSQL(UserID,
					page);
			if (getAllEXList.size() == 0 && sumpage == 1) {
				page = 0;
				sumpage = 0;
			}
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("page", page);
			arcAppRoot arc = new arcAppRoot();
			arc.setDataList(getAllEXList);
			arc.setPage(map);
			root.setObject(arc);
			root.setMessage(0, "");
			return root;

		} catch (Exception e) {
			return null;
		}

	}

	@RequestMapping(value = "appGetWillArchiveList", method = RequestMethod.GET)
	@ResponseBody
	public Root appGetWillArchiveList(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,
			int userID,int page) throws ParseException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {

		
			List getAllEXList = archiveService.GetWillExpiredArchiveSQL(userID,page);
//			int WillbetweenDays = archiveService.WillBetweenDays(UserID,
//					ArchiveDao.getNowTime());
//			
//			Map<String, Integer> map = new HashMap<String, Integer>();
//			map.put("剩余天数", WillbetweenDays);
//			arcAppRoot arc = new arcAppRoot();
//			arc.setDataList(getAllEXList);
//			arc.setPage(map);
			root.setObject(getAllEXList);
			root.setMessage(0, "");
			return root;

		} catch (Exception e) {
			return null;
		}

	}
	/**
	 * 上传图片
	 * 
	 * @param taskCode
	 * @param image
	 * @return
	 */
	@RequestMapping(value = "appUploadArchivePhoto", method = RequestMethod.POST)
	@ResponseBody
	public Root appUploadArchivePhoto(@RequestParam("archiveCode") String archiveCode, @RequestParam("image") String  image, @RequestParam("imageName") String imageName) {
		
		try {
			archiveService.GenerateImage(image, archiveCode, imageName);
			Archive archive = archiveService.getArchiveInfo(Integer.parseInt(archiveCode));//
			archive.setImagePath(
					this.getClass().getClassLoader().getResource("").getPath() + "archive" + File.separator + archiveCode);
			archiveService.modifyArchive(archive);
			root.setMessage(0, "success");
			return root;
		} catch (Exception e) {
			// TODO: handle exception
			root.setMessage(1, "failed");
			return null;
		}
		
		
	}
	
	
	@RequestMapping(value = "appUploadArchiveDate", method = RequestMethod.GET)
	@ResponseBody
	public Root appUploadArchiveDate(@RequestParam("archiveCode") String archiveCode, @RequestParam("date") String  date) {
		try {
			Archive archive = archiveService.getArchiveInfo(Integer.parseInt(archiveCode));
			archive.setExpireDate(date);
			archiveService.modifyArchive(archive);
			root.setMessage(0, "success");
			return root;
		} catch (Exception e) {
			// TODO: handle exception
			root.setMessage(1, "falied");
			return null;
		}
		
	}

}