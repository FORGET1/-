package com.pingdu.entity.archive;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.pingdu.baseModel.BaseEntity;

@XmlRootElement
@Entity
@Table(name = "archive")
public class Archive extends BaseEntity{
	private static final long serialVersionUID = -7828330583061229497L;
	@SuppressWarnings("unused")
	private static final boolean flase = false;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "archiveCode")
	private int archiveCode;
	@Column(name = "entCode")
	private int entCode;
	@Column(name = "archTypeCode")
	private int archTypeCode;
	@Column(name = "uploadDate")
	private String uploadDate;
	@Column(name = "expireDate")
	private String expireDate;
	@Column(name = "isExpire")
	private boolean isExpire;
	@Column(name = "isVerify")
	private boolean isVerify;
	@Column(name = "imagePath")
	private String imagePath;
	@Column(name = "note")
	private String note;
	
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
	public int getArchTypeCode() {
		return archTypeCode;
	}
	public void setArchTypeCode(int archTypeCode) {
		this.archTypeCode = archTypeCode;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public boolean isExpire() {
		return isExpire;
	}
	public void setExpire(boolean isExpire) {
		this.isExpire = isExpire;
	}
	public boolean isVerify() {
		return isVerify;
	}
	public void setVerify(boolean isVerify) {
		this.isVerify = isVerify;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
