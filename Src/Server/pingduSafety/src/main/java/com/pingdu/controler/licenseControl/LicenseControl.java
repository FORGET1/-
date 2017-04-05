package com.pingdu.controler.licenseControl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingdu.baseModel.BaseController;
import com.pingdu.baseModel.Root;
import com.pingdu.entity.license.LicType_entType;
import com.pingdu.entity.license.License;
import com.pingdu.entity.license.LicenseType;
import com.pingdu.entity.license.LicenseTypeReturn;
import com.pingdu.service.entService.EntService;
import com.pingdu.service.licenseService.LicType_entTypeService;
import com.pingdu.service.licenseService.LicenseService;
import com.pingdu.service.licenseService.LicenseTypeService;
import com.pingdu.utility.PublicVariable;
import com.pingdu.view.ManyLlistToJSON;

@Controller
@RequestMapping(value = "license")
public class LicenseControl extends BaseController {
	@Autowired
	private LicenseService service;
	@Autowired
	private LicenseTypeService service_type;
	@Autowired
	private LicType_entTypeService service_lic_ent;
	@Autowired
	Root root;
	@Autowired
	private EntService entService;

	/**
	 * 获得证照列表接口
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "getLicenseList", method = RequestMethod.GET)
	public @ResponseBody Root get(@RequestParam("page") int page,
			HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {

			Integer sumpage = service.calSum();
			if (page > sumpage || page == 0) {
				page = sumpage;
			}
			List<License> licenses = service.getLicenseList(page,
					PublicVariable.rows);
			if (licenses.size() == 0 && sumpage == 1) {
				page = 0;
				sumpage = 0;
			}
			for (int count = 0; count < licenses.size(); count++) {
				String licName = service_type.getLicenseTypeInfo(
						(((License) licenses.get(count)).getLicTypeCode()))
						.getLicName();
				((License) licenses.get(count)).setLicName(licName);
				// 差一个根据主键获取企业名称，要调用企业service

				String entName = entService.getEnterpriseByCode(
						(((License) licenses.get(count)).getEntCode()))
						.getEntName();

				((License) licenses.get(count)).setEntName(entName);

			}
			ManyLlistToJSON json = new ManyLlistToJSON();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("page", page);
			map.put("lastPage", sumpage);
			json.setPage(map);
			json.setDataList(licenses);
			root.setObject(json);
			return root;
		} catch (Exception e) {
			// TODO: handle exception
			root.setMessage(1, "服务器异常");
			return null;

		}
	}

	/**
	 * 删除证照接口
	 * 
	 * @param entCode
	 * @param licenseCode
	 * @return
	 */
	@RequestMapping(value = "deleteLicense", method = RequestMethod.GET)
	@ResponseBody
	public Root del(@RequestParam("entCode") int entCode,
			@RequestParam("licenseCode") int licenseCode,
			HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (service.del(entCode, licenseCode)) {
			root.setObject("SUCCESS");
		} else {
			root.setObject("FAILED");
		}
		return root;
	}

	/**
	 * 查看证照图片
	 * 
	 * @param entCode
	 * @param licenseCode
	 * @return
	 */

	@RequestMapping(value = "getLicenseImg", method = RequestMethod.GET)
	public @ResponseBody Root getLicenseImg(int entCode, int licenseCode,
			HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		try {
			License license = service.getLicenseInfo(entCode, licenseCode);
			String path = license.getImagePath();
			String[] arr = path.split(",");
			List<String> list = java.util.Arrays.asList(path);
			root.setObject(list);
			root.setMessage(0, "");

			return root;
		} catch (Exception e) {
			// TODO: handle exception
			root.setMessage(1, "服务器异常");
			return null;
		}

	}

	/**
	 * 查询 证照接口 证照名称 上传日期 有效日期
	 * 
	 * @param searchType
	 * @param keyword
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "searchLicense", method = RequestMethod.GET)
	public @ResponseBody Root searchLicense(HttpServletRequest request,
			HttpServletResponse response, String type, String key, int page) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Root root = new Root();
		Object keyWord = null;
		if (type.equals("证照名称")) {
			int licTypeCode = service_type.findByName(key).getLicTypeCode();
			if (licTypeCode != -1) {
				keyWord = licTypeCode;
			}
		}
		if (type.equals("上传日期") || type.equals("有效日期")) {
			keyWord = key;
		}
		Integer sumpage = service.calPageOfSearch(type, keyWord);
		if (page > sumpage || page == 0) {
			page = sumpage;
		}
		List licenses = service
				.search(type, keyWord, page, PublicVariable.rows);
		if (licenses.size() == 0 && sumpage == 1) {
			page = 0;
			sumpage = 0;
		}
		for (int count = 0; count < licenses.size(); count++) {
			String licName = service_type.getLicenseTypeInfo(
					(((License) licenses.get(count)).getLicTypeCode()))
					.getLicName();
			((License) licenses.get(count)).setLicName(licName);
			// 差一个根据主键获取企业名称，要调用企业service

		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("lastPage", sumpage);
		map.put("page", page);
		ManyLlistToJSON json = new ManyLlistToJSON();
		json.setDataList(licenses);
		json.setPage(map);
		root.setObject(json);
		return root;
	}

	/**
	 * 获得证件类型接口
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "getLicenseTypeList", method = RequestMethod.GET)
	public @ResponseBody Root getType(@RequestParam("page") int page) {
		List licTypeReturnList = new ArrayList();
		Integer sumpage = service_lic_ent.calSum();
		if (page > sumpage || page == 0) {
			page = sumpage;
		}
		List<LicType_entType> list = service_lic_ent.getLicType_entTypeList(
				page, PublicVariable.rows);
		if (list.size() == 0 && sumpage == 1) {
			page = 0;
			sumpage = 0;
		}
		for (int count = 0; count < list.size(); count++) {
			LicType_entType l_e = list.get(count);
			LicenseType licType = service_type.getLicenseTypeInfo(l_e
					.getLicTypeCode());
			LicenseTypeReturn ltr = LicenseTypeToLicenseTypeReturn(licType);
			// 根据主键获取企业类型名称--待完善
			if (l_e.getEntTypeCode() == 1) {
				ltr.setEntTypeName("化工厂");
			} else {
				ltr.setEntTypeName("加油站");
			}
			licTypeReturnList.add(ltr);
		}
		ManyLlistToJSON json = new ManyLlistToJSON();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("page", page);
		map.put("lastPage", sumpage);
		json.setPage(map);
		json.setDataList(licTypeReturnList);
		root.setObject(json);
		return root;

	}

	/**
	 * 添加证照类型接口
	 * 
	 * @param entTypeName
	 *            ,licName,validTerm
	 * @return
	 * @RequestMapping(value = "addLicenseType",method=RequestMethod.POST)
	 *                       public ModelAndView
	 *                       add(@RequestParam("entTypeName")String
	 *                       entTypeName,@RequestParam("licName")String
	 *                       licName,@RequestParam("validTerm") Integer
	 *                       validTerm){ LicenseType lt =
	 *                       service_type.findByName(licName); if(lt != null) {
	 * 
	 *                       } else {
	 * 
	 *                       } String jpql =
	 *                       "select lt from LicenseType lt where lt.licName=:licName"
	 *                       ; TypedQuery<LicenseType> query =
	 *                       em().createQuery(jpql, LicenseType.class);
	 *                       query.setParameter("licName", licName);
	 *                       List<LicenseType> lts = query.getResultList();
	 *                       if(lts.size() != 0){ LicenseType lt = lts.get(0);
	 *                       lt.setValidTerm(validTerm); em().merge(lt); } else{
	 *                       LicenseType lt = new LicenseType();
	 *                       lt.setLicName(licName); lt.setValidTerm(validTerm);
	 *                       em().persist(lt);
	 * 
	 *                       } if (!file.isEmpty()) { String userPath =
	 *                       this.getClass
	 *                       ().getClassLoader().getResource("").getPath
	 *                       ()+"users"+File.separator; File dir =new
	 *                       File(userPath+user.getUserCode()); //如果文件夹不存在则创建 if
	 *                       (!dir .exists() && !dir .isDirectory()) {
	 *                       System.out.println("//不存在"); dir .mkdirs(); } else
	 *                       { System.out.println("//目录存在"); } String path =
	 *                       userPath + user.getUserCode()+ File.separator +
	 *                       file.getOriginalFilename(); byte[] bytes =
	 *                       file.getBytes(); FileOutputStream fileOutputStream
	 *                       = new FileOutputStream(path);
	 *                       fileOutputStream.write(bytes);
	 *                       fileOutputStream.close(); user.setUserPhoto(path);
	 *                       } service.add(user); return new
	 *                       ModelAndView("redirect:getUserList.do?page=1"); }
	 */

	// /**
	// * 添加证照类型接口
	// *
	// * @param entTypeName
	// * ,licName,validTerm
	// * @return
	// */
	// @RequestMapping(value = "addLicenseType", method = RequestMethod.POST)
	// public @ResponseBody Root add(
	// @RequestParam("entTypeName") String entTypeName,
	// @RequestParam("licName") String licName,
	// @RequestParam("validTerm") Integer validTerm) {
	// if (service_type.insertaddLicense(licName, validTerm, entTypeName)){
	// root.setObject("SUCCESS");}
	// else{
	// root.setObject("FAILED");}
	// return root;
	// }
	//
	// /**
	// * 删除证照类型接口
	// *
	// * @param entTypeName
	// * ,licTypeCode
	// * @return
	// */
	// @RequestMapping(value = "deleteLicenseType", method = RequestMethod.POST)
	// public @ResponseBody Root delete(
	// @RequestParam("entTypeName") String entTypeName,
	// @RequestParam("licTypeCode") int licTypeCode) {
	// if (service_type.deleteLicenseType(entTypeName, licTypeCode))
	// root.setObject("SUCCESS");
	// else
	// root.setObject("FAILED");
	// return root;
	// }

	/**
	 * 查询证照类型接口 1 根据企业编号 2根据证照类型编号
	 * 
	 * @param searchType
	 * @param keyword
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "searchLicenseType", method = RequestMethod.GET)
	public @ResponseBody Root search(@RequestParam("type") String type,
			@RequestParam("key") String key, @RequestParam("page") int page) {
		Integer sumpage = service_type.calPageSearch(type, key);
		if (page > sumpage || page == 0) {
			page = sumpage;
		}
		List licenseTypes = service_type.LicenseTypeSpecList(type, key);
		if (licenseTypes.size() == 0 && sumpage == 1) {
			page = 0;
			sumpage = 0;
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("lastpage", sumpage);
		map.put("page", page);
		ManyLlistToJSON json = new ManyLlistToJSON();
		json.setDataList(licenseTypes);
		json.setPage(map);
		root.setObject(json);
		return root;

	}

	public LicenseTypeReturn LicenseTypeToLicenseTypeReturn(LicenseType lt) {
		LicenseTypeReturn ltr = new LicenseTypeReturn();
		ltr.setEntTypeName("");
		ltr.setLicName(lt.getLicName());
		ltr.setLicTypeCode(lt.getLicTypeCode());
		ltr.setValidTerm(lt.getValidTerm());
		return ltr;
	}

}
