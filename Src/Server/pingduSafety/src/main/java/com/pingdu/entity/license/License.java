package com.pingdu.entity.license;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pingdu.baseModel.BaseEntity;
/**
 * @description 模型-- 证照
 */
@Entity
@Table(name = "license")
public class License extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3204517077658874660L;
	/**
	 * 证照编号
	 */
	@Id
	@Column(name = "licenseCode", nullable = false, length = 11)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer licenseCode;
	/**
	 * 企业编号
	 */
	@Column(name = "entCode", nullable = false, length = 11)
	private Integer entCode;
	/**
	 * 证照类型编号
	 */
	@Column(name = "licTypeCode", nullable = false, length = 11)
	private Integer licTypeCode;
	/**
	 * 是否上传
	 */
	@Column(name = "isUpload", nullable = false, length = 4)
	private Boolean isUpload;
	/**
	 * 上传日期
	 */
	@Column(name = "uploadDate")
	private String uploadDate;
	/**
	 * 有效日期
	 */
	@Column(name = "expireDate")
	private String expireDate;
	/**
	 * 是否过期
	 */
	@Column(name = "isExpire", length = 4)
	private Boolean isExpire;
	/**
	 * 是否审核
	 */
	@Column(name = "isVerify", length = 4)
	private Boolean isVerify;
	/**
	 * 图片路径
	 */
	@Column(name = "imagePath", length = 200)
	private String imagePath;
	/**
	 * 备注
	 */
	@Column(name = "note", length = 100)
	private String note;
	
	@Transient
	private String entName;
	
	@Transient
	private String licName;
	
	/*----------------------------------get()&set()-------------------------------------*/


	public Integer getLicenseCode() {
		return licenseCode;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getLicName() {
		return licName;
	}

	public void setLicName(String licName) {
		this.licName = licName;
	}

	public void setLicenseCode(Integer licenseCode) {
		this.licenseCode = licenseCode;
	}

	public Integer getEntCode() {
		return entCode;
	}

	public void setEntCode(Integer entCode) {
		this.entCode = entCode;
	}

	public Integer getLicTypeCode() {
		return licTypeCode;
	}

	public void setLicTypeCode(Integer licTypeCode) {
		this.licTypeCode = licTypeCode;
	}

	public Boolean getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(Boolean isUpload) {
		this.isUpload = isUpload;
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

	public Boolean getIsExpire() {
		return isExpire;
	}

	public void setIsExpire(Boolean isExpire) {
		this.isExpire = isExpire;
	}

	public Boolean getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(Boolean isVerify) {
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
