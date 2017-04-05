package com.pingdu.controler.safePromiseControler;

import java.io.File;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingdu.baseModel.BaseController;
import com.pingdu.baseModel.Root;
import com.pingdu.entity.safePromise.SafePromise;
import com.pingdu.service.safePromiseService.SafePromiseService;
import com.pingdu.utility.CaculateDate;
import com.pingdu.utility.PublicVariable;

@RequestMapping(value = "safePromise")
@Controller
public class safePromisControl extends BaseController {

	@Autowired
	private Root root;
	@Autowired
	SafePromiseService service;
	 /**
	  * 上传图片
	  * @param taskCode
	  * @param image
	  * 
	  * @return
	  */
	@RequestMapping(value = "appsSendPromise", method = RequestMethod.POST)
	@ResponseBody
	public Root sendImage(@RequestParam("userCode") String userCode,@RequestParam("enterprisePhoto") String enterprisePhoto,
			@RequestParam("signPhoto") String signPhoto){
		 String imageName = PublicVariable.sdf.format(new Date());
		 
		 Integer entCode = service.userCodeChangeEntCode(userCode);
		 
		 System.out.println(signPhoto);
		 System.out.println("-----------------------------------------------");
		 System.out.println(enterprisePhoto);
		 
		 
		 service.GenerateImage(enterprisePhoto, entCode.toString(), "enterprisePhoto"+imageName);
		 service.GenerateImage(signPhoto, entCode.toString(), "signPhoto"+imageName);
		 SafePromise safe = new SafePromise();
		 safe.setEntCode(entCode);
		 
		 String currrentDate = PublicVariable.sdfDate.format(new Date());
		 String newExp = CaculateDate.getNextDate(currrentDate, 1, Calendar.DATE, "yyyy-MM-dd");
		 safe.setExpireDate(newExp);
		 safe.setIsUpload((short)1);
		 safe.setUploadDate(imageName);
		 safe.setImagePath(this.getClass().getClassLoader().getResource("").getPath()+"promise"+File.separator+entCode);
		 
		 root.setMessage(0,"success");
		 return root;
		 
	 }
}