package com.pingdu.service.fileService;

import java.util.List;

import com.pingdu.entity.file.Files;



public interface fileService {
	public List getFileList(int page);//文件列表
	public List searchFile(String searchType,String searchKey,int page);//特定文件
	//public void dowloadFile(String fileName);//下载文件
	public void addFile(Files files,int UserCode,String senderType,int receiverCode,String recvType);//添加文件
	public Files getOneDevInfo(String fileName,int UserCode) ;
	//无条件查询时查询多表的页数
	public int calPage(int page) ;
	public int calPageSearch(String searchType,String keyword);
	public List appFileList(int userCode, int page);
	public int appGetFileListPage(int userCode);//接收通知列表页数
	public Files getAppDownloadFile(int fileCode,int userCode) ;
	public void deleteFile(int fileCode,int userCode);// app 删除文件
	public void deleteFiles(int fileCode);//删除文件管理表
	public void deleteFilesrinfo(int fileCode);//删除收发信息表
	public void deletefileDowwnload(int fileCode);//删除文件下载信息表
	public void appSendDownloadFlag(int fileCode, int userCode, int flag);

}
