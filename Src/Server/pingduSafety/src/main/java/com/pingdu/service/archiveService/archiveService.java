package com.pingdu.service.archiveService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.pingdu.entity.archive.Archive;



public interface archiveService {
	// 查看档案列表
	public List getArchivesSQL(int page);

	// 查看档案图片
	public List getArchivesImgSQL(int archiveCode, int companyCode);

	// 删除档案
	public void deleteArchivesSQL(int archiveCode, int companyCode);

	// 查询档案
	public List searchArchivesSQL(String searchType, int keyword, int page);

	// 分页总数
	public int getPag(int page);

	// 根据特定条件查询时最后一页
	public int lastPage(String searchType, int keyword);

	// 已经过期的App
	public List GetAlreadyExpiredArchiveSQL(int userCode,int page)
			throws ParseException;

	// 过期时上面的最大页数
	public int calPageGetAlreadyExp(int UserCode);

	// 过期时获取最大日期
	public  Map<String, Integer> BetweenDays(int UserCode, Date NowDate);

	/**
	 * 获取全部档案
	 */
	public List GetAllExpiredArchiveSQL(int UserCode, int page)
			throws Exception;

	/**
	 * 获取全部档案--最后一页
	 */
	public int calPageGetAllExp(int UserCode);

	
	public List GetWillExpiredArchiveSQL(int UserCode,int page)throws Exception ;//将要过期

	public Map<String, Integer> WillBetweenDays(int UserCode, Date NowDate);//将要过期
	
	public int calPageGetWillExp(int UserCode);//将要过期
	
	public boolean GenerateImage(String imgStr, String Path, String imageName);

	public Archive getArchiveInfo(int parseInt);


	public void modifyArchive(Archive archive);
}
