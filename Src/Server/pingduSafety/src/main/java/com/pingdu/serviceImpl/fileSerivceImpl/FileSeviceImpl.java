package com.pingdu.serviceImpl.fileSerivceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.fileDao.FileDao;
import com.pingdu.entity.file.Files;
import com.pingdu.service.fileService.fileService;
import com.pingdu.view.FileAllPageList;
import com.pingdu.view.FileUserCodePage;

@Service
public class FileSeviceImpl implements fileService {
	@Autowired
	FileDao fileDao;
	@Override
	public List getFileList(int page) {
		// TODO Auto-generated method stub
		List<FileAllPageList>fileList=fileDao.getFileList(page);
		return fileList;
	}

	@Override
	public List searchFile(String searchType, String searchKey,int page) {
		// TODO Auto-generated method stub
		List<FileAllPageList>fileAllList=fileDao.getArchiveSpecList(searchType, searchKey,page);
		return fileAllList;
	}


	@Override
	public void addFile(Files files,int UserCode,String senderType,int receiverCode,String recvType) {
		// TODO Auto-generated method stub
		fileDao.insert(files, UserCode, senderType, receiverCode, recvType);

	}
	@Override
	public Files getOneDevInfo(String fileName,int UserCode) {
		// TODO Auto-generated method stub
		fileDao.insertfileDown(fileName,UserCode);
		fileDao.insertFilesrinfo(fileName);
		return (Files)fileDao.getOneDevInfo(fileName);
		//Files.getOneDevInfo(fileName);
	}

	@Override
	public int calPage(int page) {
		// TODO Auto-generated method stub
		return fileDao.calPage(page);
	}

	@Override
	public int calPageSearch(String searchType, String keyword) {
		// TODO Auto-generated method stub
		return fileDao.calPageSearch(searchType, keyword);
	}
	@Override
	public List appFileList(int userCode, int page) {
		// TODO Auto-generated method stub
		List<FileUserCodePage>fileUserCodePage=fileDao.appGetFileList(userCode, page);
		return fileUserCodePage;
	}

	@Override
	public int appGetFileListPage(int userCode) {
		// TODO Auto-generated method stub
		return fileDao.appGetFileListPage(userCode);
	}
	@Override
	public Files getAppDownloadFile(int fileCode,int userCode) {
		// TODO Auto-generated method stub
		fileDao.appinsertfileDown(fileCode,userCode);
		fileDao.appinsertFilesrinfo(fileCode,userCode);
		
		
		return (Files)fileDao.getAppDownloadFile(fileCode);
	}
	@Override
	public void deleteFile(int fileCode,int userCode) {
		// TODO Auto-generated method stub
		fileDao.delete(fileCode,userCode);
	}

	@Override
	public void deleteFiles(int fileCode) {
		// TODO Auto-generated method stub
		fileDao.deleteFiles(fileCode);
	}

	@Override
	public void deleteFilesrinfo(int fileCode) {
		// TODO Auto-generated method stub
		fileDao.deleteFilesrinfo(fileCode);
		
	}

	@Override
	public void deletefileDowwnload(int fileCode) {
		// TODO Auto-generated method stub
		fileDao.deletefileDowwnload(fileCode);
	}
	public void appSendDownloadFlag(int fileCode,int userCode,int flag)  {
		// TODO Auto-generated method stub
		fileDao.appSendDownloadFlag(fileCode,userCode,flag);
	}
}