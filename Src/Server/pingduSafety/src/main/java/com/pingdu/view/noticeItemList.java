package com.pingdu.view;

import com.pingdu.baseModel.BaseEntity;

public class noticeItemList extends BaseEntity{
	private String senderName;//发送方姓名
	private int senderCode;//发送方编码
	private int receiverCode;//接收方编号
	private String sendDate;//发送日期
	private String noticeTitle;//标题
	private Boolean isRead;//是否阅读
	private int receiverPersonNum;//收件人数
	private int readPersonNum;//接收人数
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public int getSenderCode() {
		return senderCode;
	}
	public void setSenderCode(int senderCode) {
		this.senderCode = senderCode;
	}
	public int getReceiverCode() {
		return receiverCode;
	}
	public void setReceiverCode(int receiverCode) {
		this.receiverCode = receiverCode;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	public int getReceiverPersonNum() {
		return receiverPersonNum;
	}
	public void setReceiverPersonNum(int receiverPersonNum) {
		this.receiverPersonNum = receiverPersonNum;
	}
	public int getReadPersonNum() {
		return readPersonNum;
	}
	public void setReadPersonNum(int readPersonNum) {
		this.readPersonNum = readPersonNum;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	
		
}
