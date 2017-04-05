package com.pingdu.view;

import com.pingdu.baseModel.BaseEntity;

public class FileAllPageList extends BaseEntity{
	private String fileName;//文件名称
	private int fileCode;//文件编号
	private int senderCode;//发送者编号
	private String senderName;//发送者姓名
	private int downloadPersonNum;//收件人总数

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFileCode() {
		return fileCode;
	}

	public void setFileCode(int fileCode) {
		this.fileCode = fileCode;
	}

	public int getSenderCode() {
		return senderCode;
	}

	public void setSenderCode(int senderCode) {
		this.senderCode = senderCode;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public int getDownloadPersonNum() {
		return downloadPersonNum;
	}

	public void setDownloadPersonNum(int downloadPersonNum) {
		this.downloadPersonNum = downloadPersonNum;
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
