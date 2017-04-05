package com.pingdu.entity.license;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @description 模型-- 企业证照类型
 */
@Entity
@Table(name = "licType_entType")
public class LicType_entType {

	/**
	 * 编号
	 */
	@Id
	@Column(name = "licType_entTypeCode", nullable = false, length = 11)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer licType_entTypeCode;
	/**
	 * 企业类型编号
	 */
	@Column(name = "entTypeCode", nullable = false, length = 11)
	private Integer entTypeCode;
	/**
	 * 证照类型编号
	 */
	@Column(name = "licTypeCode", nullable = false, length = 11)
	private Integer licTypeCode;
	/**
	 * 备注
	 */
	@Column(name = "note", length = 100)
	private String note;
	
	/*----------------------------------get()&set()-------------------------------------*/

	public Integer getLicType_entTypeCode() {
		return licType_entTypeCode;
	}

	public void setLicType_entTypeCode(Integer licType_entTypeCode) {
		this.licType_entTypeCode = licType_entTypeCode;
	}

	public Integer getEntTypeCode() {
		return entTypeCode;
	}

	public void setEntTypeCode(Integer entTypeCode) {
		this.entTypeCode = entTypeCode;
	}

	public Integer getLicTypeCode() {
		return licTypeCode;
	}

	public void setLicTypeCode(Integer licTypeCode) {
		this.licTypeCode = licTypeCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}