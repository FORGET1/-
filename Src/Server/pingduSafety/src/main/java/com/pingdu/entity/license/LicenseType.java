package com.pingdu.entity.license;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @description 模型-- 证照类型
 */
@Entity
@Table(name = "licenseType")
public class LicenseType {

	/**
	 * 证照类型编号
	 */
	@Id
	@Column(name = "licTypeCode", nullable = false, length = 11)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer licTypeCode;
	/**
	 * 证件名称
	 */
	@Column(name = "licName", nullable = false, length = 30)
	private String licName;
	/**
	 * 有效期
	 */
	@Column(name = "validTerm", length = 11)
	private Integer validTerm;
	/**
	 * 备注
	 */
	@Column(name = "note", length = 100)
	private String note;
	
	/*----------------------------------get()&set()-------------------------------------*/
	
	public Integer getLicTypeCode() {
		return licTypeCode;
	}

	public void setLicTypeCode(Integer licTypeCode) {
		this.licTypeCode = licTypeCode;
	}

	public String getLicName() {
		return licName;
	}

	public void setLicName(String licName) {
		this.licName = licName;
	}

	public Integer getValidTerm() {
		return validTerm;
	}

	public void setValidTerm(Integer validTerm) {
		this.validTerm = validTerm;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}



}