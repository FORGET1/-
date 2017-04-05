package com.pingdu.serviceImpl.noticeServiceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pingdu.dao.noticeDao.NoticeDao;
import com.pingdu.service.noticeService.NoticeService;
import com.pingdu.view.NoticeListapp;
import com.pingdu.view.NoticecheckRead;
import com.pingdu.view.noticeItemList;
import com.pingdu.view.noticeRecIsRead;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	NoticeDao dao;

	@Override
	public void insertSendNotice(String recvType, int recvCode,
			String senderType, int userID, String noticeTitle,
			String noticeContent, int noticeTypeCode) {
		dao.insertSendNotice(recvType, recvCode, senderType, userID, noticeTitle, noticeContent, noticeTypeCode);

	}

	@Override
	public void insertBackNotice(int noticeCode, String recvType,
			int recvCode, String senderType, int userID, int noticeRecipient) {
		dao.insertBackNotice(noticeCode, recvType, recvCode, senderType,
				userID, noticeRecipient);

	}

	@Override
	public List<noticeItemList> getSendNoticeList(int userID, int page) {
		// TODO Auto-generated method stub
		List<noticeItemList> getSendList = dao.getSendNoticeList(userID, page);
		return getSendList;
	}

	@Override
	public List<noticeItemList> getReceivedNoticeList(int userID, int page) {
		// TODO Auto-generated method stub
		List<noticeItemList> receivedList = dao.getReceivedNoticeList(userID,
				page);
		return receivedList;
	}

	@Override
	public List getNoticeAllList(int noticeCode) {
		// TODO Auto-generated method stub
		List notiveAllList = dao.getNoticeAllList(noticeCode);
		return notiveAllList;
	}

	@Override
	public List<NoticecheckRead> checkNoticeReadList(String noticeTitle,
			int page) {
		// TODO Auto-generated method stub
		List<NoticecheckRead> noticeCheckList = dao.checkNoticeReadList(
				noticeTitle, page);
		return noticeCheckList;
	}

	@Override
	public int NoticeReceivedPage(int userID) {
		// TODO Auto-generated method stub
		return dao.NoticeReceivedPage(userID);
	}

	@Override
	public int NoticeSendPage(int userID) {
		// TODO Auto-generated method stub
		return dao.NoticeSendPage(userID);
	}

	@Override
	public int NoticeCheckPage(String noticeTitle) {
		// TODO Auto-generated method stub
		return dao.NoticeCheckPage(noticeTitle);
	}
	@Override
	public List<NoticeListapp> getUnReadNoticeList(int userCode, int page) {
		// TODO Auto-generated method stub
		List<NoticeListapp> getSendList=dao.getUnReadNoticeList(userCode, page);
		return getSendList;
	}

	@Override
	public int NoticeUnReadPage(int userCode) {
		// TODO Auto-generated method stub
		return dao.NoticeUnReadPage(userCode);
	}
	@Override
	public List<NoticeListapp> getAppNoticeList(int userCode, int page) {
		// TODO Auto-generated method stub
		List<NoticeListapp> getSendList=dao.getAppNoticeList(userCode, page);
		return getSendList;
	}

	@Override
	public int NoticePageapp(int userCode) {
		// TODO Auto-generated method stub
		return dao.NoticeUnReadPage(userCode);
	}
	
	@Override
	public List<NoticeListapp> getAppNoticeInFoList(int userCode, int noticeCode) {
		// TODO Auto-generated method stub
		List<NoticeListapp> getSendList=dao.getAppNoticeInFoList(userCode, noticeCode);
		return getSendList;
	}

	@Override
	public List getSendNoticeSearchList(int userID, String searchType,
			String keyWord) {
		// TODO Auto-generated method stub
		List getSendNoticeSeaLlist=dao.getSendNoticeSearchList(userID, searchType, keyWord);
		return getSendNoticeSeaLlist;
	}

	@Override
	public List getRecNoticeSearchList(int userID, String searchType,
			String keyWord) {
		// TODO Auto-generated method stub
		List getReceivedSeaList=dao.getRecNoticeSearchList(userID, searchType, keyWord);
		return getReceivedSeaList;
	}

	@Override
	public void deletenoticerinfoSendORec(int noticeCode) {
		// TODO Auto-generated method stub
		dao.deletenoticerinfoSendORec(noticeCode);
	}

	@Override
	public List selectItemZFTonice(int userID) {
		// TODO Auto-generated method stub
		List TZlist=dao.selectItemZFTonice(userID);
		return TZlist;
	}

	@Override
	public List<noticeRecIsRead> RecUserAndIsRead(int noticeCode, int page) {
		List<noticeRecIsRead> recNoticeList=dao.RecUserAndIsRead(noticeCode, page);
		return recNoticeList;
	}

	@Override
	public int NoticeRecListSumPage(int noticeCode) {
		// TODO Auto-generated method stub
		
		return (int)dao.NoticeRecListSumPage(noticeCode);
	}

	@Override
	public int getPDNowUuser(int userID) {
		// TODO Auto-generated method stub
		return (int)dao.getPDNowUuser(userID);
	}




	

}
