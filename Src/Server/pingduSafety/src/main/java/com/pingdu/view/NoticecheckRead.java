package com.pingdu.view;

import com.pingdu.baseModel.BaseEntity;

public class NoticecheckRead extends BaseEntity{
	private int receiverCode;//接收方编号
	private Boolean  isRead;//是否阅读
	public int getReceiverCode() {
		return receiverCode;
	}
	public void setReceiverCode(int receiverCode) {
		this.receiverCode = receiverCode;
	}
	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
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
