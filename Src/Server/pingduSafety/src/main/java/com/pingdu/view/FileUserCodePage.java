package com.pingdu.view;

import com.pingdu.baseModel.BaseEntity;

public class FileUserCodePage extends BaseEntity{
	private String fileName;//文件名称
	private int fileCode;//文件编号
	private String downDate;//下载日期
	private int isDownload;//是否下载
	
	
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
	public String getDownDate() {
		return downDate;
	}
	public void setDownDate(String downDate) {
		this.downDate = downDate;
	}
	public int getIsDownload() {
		return isDownload;
	}
	public void setIsDownload(int isDownload) {
		this.isDownload = isDownload;
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
