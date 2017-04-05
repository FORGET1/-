package com.pingdu.entity.license;
/**
 * @description 返回类型-- 证照类型
 */
public class LicenseTypeReturn {
	private String entTypeName;
	private Integer licTypeCode;
	private Integer validTerm;
	private String licName;
	public String getEntTypeName() {
		return entTypeName;
	}
	public void setEntTypeName(String entTypeName) {
		this.entTypeName = entTypeName;
	}
	public Integer getLicTypeCode() {
		return licTypeCode;
	}
	public void setLicTypeCode(Integer licTypeCode) {
		this.licTypeCode = licTypeCode;
	}
	public Integer getValidTerm() {
		return validTerm;
	}
	public void setValidTerm(Integer validTerm) {
		this.validTerm = validTerm;
	}
	public String getLicName() {
		return licName;
	}
	public void setLicName(String licName) {
		this.licName = licName;
	}
	
}
