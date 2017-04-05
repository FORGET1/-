package com.pingdu.view;

import com.pingdu.baseModel.BaseEntity;

public class getNoticeAllItemList extends BaseEntity{
	private String noticeTitle;//标题
	private String noticeInfo;//具体内容
	private int noticeTypeCode;//通知类型编号
	private String receiverType;//接收方类型
	private int receiverCode;//接收方编号
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeInfo() {
		return noticeInfo;
	}
	public void setNoticeInfo(String noticeInfo) {
		this.noticeInfo = noticeInfo;
	}
	public int getNoticeTypeCode() {
		return noticeTypeCode;
	}
	public void setNoticeTypeCode(int noticeTypeCode) {
		this.noticeTypeCode = noticeTypeCode;
	}
	public String getReceiverType() {
		return receiverType;
	}
	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}
	public int getReceiverCode() {
		return receiverCode;
	}
	public void setReceiverCode(int receiverCode) {
		this.receiverCode = receiverCode;
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
