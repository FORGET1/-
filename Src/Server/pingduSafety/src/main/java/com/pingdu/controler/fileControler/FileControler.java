package com.pingdu.controler.fileControler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pingdu.baseModel.BaseController;
import com.pingdu.baseModel.Root;
import com.pingdu.entity.file.Files;
import com.pingdu.service.fileService.fileService;
import com.pingdu.view.ArchiveItemList;
import com.pingdu.view.ManyLlistToJSON;
@RequestMapping(value = "file")
@Controller
public class FileControler extends BaseController {
	@Autowired
	private Root root;
	@Autowired
	fileService fileService;
	private Logger log = Logger.getLogger(ArchiveItemList.class);

	/**
	 * 查看文件列表
	 * 
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "getFileList", method = RequestMethod.GET)
	@ResponseBody
	public Root getFileList(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response, @RequestParam int page)
			throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		log.info("查看文件列表");
		try {
			int sumpage = fileService.calPage(page);
			System.out.println("AAA" + sumpage);
			if (page > sumpage || page == 0) {
				page = sumpage;
			}
			List fileAllList = fileService.getFileList(page);
			if (fileAllList.size() == 0 && sumpage == 1) {
				page = 0;
				sumpage = 0;
			}
			ManyLlistToJSON followGive = new ManyLlistToJSON();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("page", page);
			map.put("lastPage", sumpage);
			followGive.setPage(map);
			followGive.setDataList(fileAllList);
			root.setObject(followGive);
			root.setMessage(0, "");
			return root;

		} catch (Exception e) {
			root.setMessage(1, "服务器异常");
			return null;
		}

	}

	/**
	 * 查询文件
	 * 
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param searchType
	 * @param keyword
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "searchFile", method = RequestMethod.GET)
	@ResponseBody
	public Root searchFile(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response, String searchType, String keyword,
			int page) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		log.info("查询档案");
		try {
			Integer sumpage = fileService.calPageSearch(searchType, keyword);
			if (page > sumpage || page == 0) {
				page = sumpage;
			}
			List fileSpecList = fileService.searchFile(searchType, keyword,
					page);
			if (fileSpecList.size() == 0 && sumpage == 1) {
				page = 0;
				sumpage = 0;
			}

			ManyLlistToJSON followGive = new ManyLlistToJSON();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("page", page);
			map.put("lastPage", sumpage);
			followGive.setPage(map);
			followGive.setDataList(fileSpecList);
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
	 * 添加文件
	 * 
	 * @param files
	 * @param fileName
	 * @param fileRoute
	 * @param file
	 * @throws IOException
	 */
	@RequestMapping(value = "addFile", method = RequestMethod.POST)
	@ResponseBody
	public void addFile(@RequestParam("fileName") String fileName,
			@RequestParam("UserCode") int UserCode,
			@RequestParam("fileRoute") String fileRoute,
			@RequestParam("file") MultipartFile file, Files files,
			String senderType, int receiverCode, String recvType)
			throws IOException {
		try {
			if (!file.isEmpty()) {
				String devPath = this.getClass().getClassLoader()
						.getResource("").getPath()
						+ "files" + "/";
				File dir = new File(devPath + files.getFileName());
				// 如果文件夹不存在则创建
				if (!dir.exists() && !dir.isDirectory()) {
					System.out.println("//不存在");
					dir.mkdirs();
				} else {
					System.out.println("//目录存在");
				}
				String path = devPath + files.getFileName() + "/"
						+ file.getOriginalFilename();
				byte[] bytes = file.getBytes();
				FileOutputStream fileOutputStream = new FileOutputStream(path);
				fileOutputStream.write(bytes);
				fileOutputStream.close();
				files.setFileName(fileName);
				files.setFilePath(path);
			}

			fileService.addFile(files, UserCode, senderType, receiverCode,
					recvType);// 将文件管理表和文件收发信息表插入
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("上传文件做两个表插入操作时异常" + e.getMessage());
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "dowloadFile", method = RequestMethod.GET)
	@ResponseBody
	public void getfile(String fileName,int UserCode, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Files file = fileService.getOneDevInfo(fileName,UserCode);
		String path = file.getFilePath();
		if (!(path == null)) {
			String filename = path.substring(path.lastIndexOf("/") + 1,
					path.length());
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ java.net.URLEncoder.encode(filename, "UTF-8"));
			try {
				InputStream inputStream = new FileInputStream(new File(path));
				OutputStream os = response.getOutputStream();
				byte[] b = new byte[2048];
				int length;
				while ((length = inputStream.read(b)) > 0) {
					os.write(b, 0, length);
				}
				os.close();
				inputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 文件管理app获取全部列表
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "appGetFileList", method = RequestMethod.GET)
	public @ResponseBody Root appGetFileList(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response, int page,
			int userCode) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			int sumpage = fileService.appGetFileListPage(userCode);
			if (page > sumpage || page == 0) {
				page = sumpage;
			}
			List fileUserCodePge = fileService.appFileList(userCode,
					page);
			if (fileUserCodePge.size() == 0 && sumpage == 1) {
				page = 0;
				sumpage = 0;
			}
			//fileUserCodePge.add(sumpage);
			root.setObject(fileUserCodePge);
			root.setMessage(0, "");
			return root;

		} catch (Exception e) {
			root.setMessage(1, "服务器异常");
			return null;
		}

	}
	/**
	 * 下载文件
	 * 
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "appDownloadFile", method = RequestMethod.GET)
	public void getappDownloadFile(int fileCode, int userCode,HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Files file = fileService.getAppDownloadFile(fileCode,userCode);
		
		String path = file.getFilePath();
		if (!(path == null)) {
			String filename = path.substring(path.lastIndexOf("/") + 1,
					path.length());
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ java.net.URLEncoder.encode(filename, "UTF-8"));
			try {
				InputStream inputStream = new FileInputStream(new File(path));
				OutputStream os = response.getOutputStream();
				byte[] b = new byte[2048];
				int length;
				while ((length = inputStream.read(b)) > 0) {
					os.write(b, 0, length);
				}
				os.close();
				inputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 删除文件
	 * 
	 * @param page
	 * @return
	 * 
	 *         companyCode,archivesCode SUCCESS 删除成功，FAILED 删除失败
	 */
	@RequestMapping(value = "deletefiles", method = { RequestMethod.GET })
	public @ResponseBody Root deletefiles(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,int fileCode,
			int userCode) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		log.info(" 删除文件");
		Root root = new Root();
		try {
			fileService.deleteFile(fileCode, userCode);
			root.setMessage(0, "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			root.setMessage(1, "服务器异常");
			System.out.println("AAA"+e.getMessage());
		}	
		return root;

	}
	
	
	/**
	 * 文件列表删除方法
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param fileCode
	 * @param userCode
	 * @return
	 */
	@RequestMapping(value = "deletefileList", method = { RequestMethod.GET })
	public @ResponseBody Root deletefileList(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,int fileCode) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		log.info(" 删除文件");
		Root root = new Root();
		try {
			fileService.deletefileDowwnload(fileCode);
			fileService.deleteFiles(fileCode);
			fileService.deleteFilesrinfo(fileCode);
			root.setMessage(0, "");
			return root;
		} catch (Exception e) {
			System.out.println("AAA0"+e.getMessage());
			return null;
		}
	
		
		
	}
	/**
	 * 重置isDownload为1
	 * 
	 * @param fileCode
	 * @param userCode
	 * @param flag
	 * @return
	 */
	@RequestMapping(value = "appSendDownloadFlag", method = { RequestMethod.GET })
	public @ResponseBody Root appSendDownloadFlag(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response,int fileCode,
			int userCode,int flag) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		log.info(" 填写isDownload字段");
		try {
			if(flag==1){
				fileService.appSendDownloadFlag(fileCode, userCode,flag);
				root.setMessage(0, "isDownload已置1");
			}
			
		} catch (Exception e) {
			root.setMessage(1, "服务器异常");
			System.out.println("AAA"+e.getMessage());
		}	
		return root;

	}
	
	
}
