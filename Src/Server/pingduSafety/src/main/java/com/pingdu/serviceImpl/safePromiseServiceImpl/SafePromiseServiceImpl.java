package com.pingdu.serviceImpl.safePromiseServiceImpl;

import java.io.File;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.safePromiseDao.SafePromiseDao;
import com.pingdu.entity.safePromise.SafePromise;
import com.pingdu.service.safePromiseService.SafePromiseService;
import com.pingdu.utility.PublicVariable;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

@Service
public class SafePromiseServiceImpl implements SafePromiseService{

	@Autowired
	private SafePromiseDao dao;
	@Override
	public Integer calSum() {
		return dao.sum(PublicVariable.rows);
	}

	@Override
	public List<SafePromise> getSafePromiseList(int page, int rows) {
		return dao.getSafePromiseList(page, rows);
	}

	@Override
	public SafePromise getSafePromiseInfo(int entCode, int safePromiseCode) {
		return dao.getSafePromiseInfo(entCode,safePromiseCode);
	}

	@Override
	public boolean del(int entCode, int safePromiseCode) {
		return dao.delete(entCode, safePromiseCode);
	}

	@Override
	public Integer sumOfSearch(String type, String keyWord) {
		// TODO Auto-generated method stub
		return dao.sumOfSearch(type,keyWord);
	}

	@Override
	public List<SafePromise> search(String searchType, String keyWord, int page, int rows) {
		return dao.searchSafePromise(searchType, keyWord, page, rows);
	}

	@Override
	public int userCodeChangeEntCode(String userCode) {
		
		return dao.userCodeChangeEntCode(userCode);
	}
	
	@Override
	public boolean GenerateImage(String imgStr, String Path, String imageName) {// 对字节数组字符串进行Base64解码并生成图片
		String pic = imgStr.substring(imgStr.indexOf(",") + 1);
		if (pic == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = Base64.encodeBase64(pic.getBytes());
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			System.out.println(bytes);

			String taskImagePath = this.getClass().getClassLoader().getResource("").getPath() + "tasks"
					+ File.separator;
			File file = new File(taskImagePath + Path);
			// 如果文件夹不存在则创建
			if (!file.exists() && !file.isDirectory()) {
				System.out.println("//不存在");
				file.mkdirs();
			} else {
				System.out.println("//目录存在");
			}
			// 生成png图片
			OutputStream out = new FileOutputStream(taskImagePath + Path + File.separator + imageName + ".jpg");
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean modifySafePromise(SafePromise safe) {
		return dao.modifySafetyPromise(safe);
	}

}
