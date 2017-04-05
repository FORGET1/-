package com.pingdu.entity.archive;

import javax.xml.bind.annotation.XmlRootElement;

import com.pingdu.baseModel.BaseEntity;



@XmlRootElement
public class ArchiveItemList extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3291833547903688904L;
	//档案类型管理编号
	private int archTypeCode;
	//档案名称
	private String archName;
	//有效期
	private String validTerm;
	//档案编号
	private int archiveCode;
	//企业编号
	private int entCode;
	//企业名称
	private String entName;
	//有效日期
	private String expireDate;
	//上传日期
	private String uploadDate;
	//是否上传
	private String isExpire;
	//最大页数
	private int sumpage;
	
	public int getArchTypeCode() {
		return archTypeCode;
	}

	public void setArchTypeCode(int archTypeCode) {
		this.archTypeCode = archTypeCode;
	}

	public String getArchName() {
		return archName;
	}

	public void setArchName(String archName) {
		this.archName = archName;
	}

	public String getValidTerm() {
		return validTerm;
	}

	public void setValidTerm(String validTerm) {
		this.validTerm = validTerm;
	}

	public int getArchiveCode() {
		return archiveCode;
	}

	public void setArchiveCode(int archiveCode) {
		this.archiveCode = archiveCode;
	}

	public int getEntCode() {
		return entCode;
	}

	public void setEntCode(int entCode) {
		this.entCode = entCode;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getIsExpire() {
		return isExpire;
	}

	public void setIsExpire(String isExpire) {
		this.isExpire = isExpire;
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

	public int getSumpage() {
		return sumpage;
	}

	public void setSumpage(int sumpage) {
		this.sumpage = sumpage;
	}

}
