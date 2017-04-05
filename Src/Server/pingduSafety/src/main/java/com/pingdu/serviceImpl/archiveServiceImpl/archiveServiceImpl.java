package com.pingdu.serviceImpl.archiveServiceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.archiveDao.ArchiveDao;
import com.pingdu.entity.archive.Archive;
import com.pingdu.service.archiveService.archiveService;
import com.pingdu.view.ArchiveImgListView;
import com.pingdu.view.ArchiveItemList;

@Service
public class archiveServiceImpl implements archiveService {
	@Autowired
	ArchiveDao archiveDao;

	@Override
	public List<ArchiveItemList> getArchivesSQL(int page) {
		List<ArchiveItemList> archive = archiveDao.getArchiveList(page);

		return archive;
	}

	@Override
	public List<ArchiveImgListView> getArchivesImgSQL(int archiveCode,
			int companyCode) {
		// TODO Auto-generated method stub
		List<ArchiveImgListView> arcImgList = archiveDao.getArchivesImgList(
				archiveCode, companyCode);
		return arcImgList;
	}

	@Override
	public void deleteArchivesSQL(int archiveCode, int companyCode) {
		// TODO Auto-generated method stub
		archiveDao.delete(archiveCode, companyCode);
	}

	@Override
	public List<ArchiveItemList> searchArchivesSQL(String searchType,
			int keyword, int page) {
		// TODO Auto-generated method stub
		List<ArchiveItemList> list = archiveDao.getArchiveSpecList(searchType,
				keyword, page);
		return list;
	}

	@Override
	public int getPag(int page) {
		// TODO Auto-generated method stub

		return archiveDao.calPage();
	}

	@Override
	public int lastPage(String searchType, int keyword) {
		// TODO Auto-generated method stub
		return archiveDao.calPageSearch(searchType, keyword);
	}

	@Override
	public List GetAlreadyExpiredArchiveSQL(int userCode, int page)
			throws ParseException {
		// TODO Auto-generated method stub
		List GetAlreadyList = archiveDao.GetAlreadyExpiredArchiveSQL(userCode,
				page);
		return GetAlreadyList;
	}

	@Override
	public int calPageGetAlreadyExp(int UserCode) {
		// TODO Auto-generated method stub
		return archiveDao.calPageGetAlreadyExp(UserCode);
	}

	@Override
	public Map<String, Integer> BetweenDays(int UserCode, Date NowDate) {
		// TODO Auto-generated method stub
		Map<String, Integer> days = archiveDao.BetweenDays(UserCode, NowDate);
		return days;
	}

	@Override
	public List GetAllExpiredArchiveSQL(int UserCode, int page)
			throws Exception {
		// TODO Auto-generated method stub
		List getAllEXList = archiveDao.GetAllExpiredArchiveSQL(UserCode, page);
		return getAllEXList;
	}

	@Override
	public int calPageGetAllExp(int UserCode) {
		// TODO Auto-generated method stub
		return archiveDao.calPageGetAllExp(UserCode);
	}

	@Override
	public List GetWillExpiredArchiveSQL(int UserCode, int page)
			throws Exception {
		// TODO Auto-generated method stub
		List willList = archiveDao.GetWillExpiredArchiveSQL(UserCode, page);
		return willList;
	}

	@Override
	public Map<String, Integer> WillBetweenDays(int UserCode, Date NowDate) {
		// TODO Auto-generated method stub

		Map<String, Integer> map = archiveDao
				.WillBetweenDays(UserCode, NowDate);
		return map;
	}

	@Override
	public int calPageGetWillExp(int UserCode) {
		// TODO Auto-generated method stub
		return archiveDao.calPageGetWillExp(UserCode);
	}

	@Override
	public boolean GenerateImage(String imgStr, String Path, String imageName) {
		String pic = imgStr.substring(imgStr.indexOf(",") + 1);
		if (pic == null) // 图像数据为空
			return false;
		try {
			// Base64解码
			byte[] bytes = Base64.decodeBase64(pic.getBytes());
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}

			String taskImagePath = this.getClass().getClassLoader()
					.getResource("").getPath()
					+ "archive" + File.separator;
			File file = new File(taskImagePath + Path);
			// 如果文件夹不存在则创建
			if (!file.exists() && !file.isDirectory()) {
				System.out.println("//不存在");
				file.mkdirs();
			} else {
				System.out.println("//目录存在");
			}
			// 生成png图片
			OutputStream out = new FileOutputStream(taskImagePath + Path
					+ File.separator + imageName + ".jpg");
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Archive getArchiveInfo(int parseInt) {

		return archiveDao.getArchiveInfo(parseInt);
	}

	@Override
	public void modifyArchive(Archive archive) {

		archiveDao.modifyArchive(archive);

	}

}
