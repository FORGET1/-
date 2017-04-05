package com.pingdu.service.noticeService;

import java.util.List;

import com.pingdu.view.NoticeListapp;
import com.pingdu.view.NoticecheckRead;
import com.pingdu.view.getNoticeAllItemList;
import com.pingdu.view.noticeItemList;
import com.pingdu.view.noticeRecIsRead;

public interface NoticeService {
	public void insertSendNotice(String recvType, int recvCode,
			String senderType, int userID, 
			String noticeTitle,String noticeContent, int noticeTypeCode); // 发送通知

	public void insertBackNotice(int noticeCode,String recvType, int recvCode,String senderType, int userID,int noticeRecipient);// 转发通知

	public List<noticeItemList> getSendNoticeList(int userID, int page);// 查看发送的通知列表

	public List<noticeItemList> getReceivedNoticeList(int userID, int page);// 查看接收的通知列表

	public List<getNoticeAllItemList> getNoticeAllList(int noticeCode);// 获得具体通知

	public List<NoticecheckRead> checkNoticeReadList(String noticeTitle,
			int page);// 查看阅读情况

	public int NoticeReceivedPage(int userID);// 接收通知列表页数

	public int NoticeSendPage(int userID);// 发送通知列表页数

	public int NoticeCheckPage(String noticeTitle);// 查看阅读情况时页数
	
	List<NoticeListapp> getUnReadNoticeList(int userCode, int page);//查看未读的通知列表app
	public int NoticeUnReadPage(int userCode);// 未读列表的页数

	List<NoticeListapp> getAppNoticeList(int userCode, int page);//全部消息列表app

	int NoticePageapp(int userCode);//全部消息列表页数app

	List<NoticeListapp> getAppNoticeInFoList(int userCode, int noticeCode);// 获取通知详情
	
	/**
	 * 1：发送方类型
	 * 2:接收方类型
	 */
	public List getSendNoticeSearchList(int userID,String searchType,String keyWord);

	/**
	 * 通知特定条件查询通知信息
	 * 1：发送方类型
	 * 2:接收方类型
	 */
	public List getRecNoticeSearchList(int userID,String searchType,String keyWord);
	
	public void deletenoticerinfoSendORec(int noticeCode) ;
	//查询发送人列表
	public List selectItemZFTonice(int userID);
	/**
	 * 接收人信息列表
	 */
	public List<noticeRecIsRead> RecUserAndIsRead(int noticeCode,int page);
	/**
	 * 接收文件的接收列表查询
	 */
	public int NoticeRecListSumPage(int noticeCode) ;
	/**
	 * 获得当前userID
	 */
	public int getPDNowUuser(int userID);

}
