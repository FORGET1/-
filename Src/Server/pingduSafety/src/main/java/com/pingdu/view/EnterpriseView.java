package com.pingdu.view;

import com.pingdu.baseModel.BaseEntity;
import com.pingdu.entity.enterprise.Enterprise;

public class EnterpriseView extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3764011491798243363L;

	private int entCode;
	
	private String entTypeName;
	
	private int deptCode;
	
	private String deptName;
	
	private String entName;
	
	private Integer parentCode;
	
	private Integer entLevel;
	
	private String entAddr;
	
	private String principle;
	
	private String entTel;
	
	private String prinPhone;
	
	private String note;

	public EnterpriseView(){}
	
	public EnterpriseView(Enterprise enterprise) {
		super();
		this.entCode = enterprise.getEntCode();
		this.entTypeName = enterprise.getEntType().getEntTypeName();
		this.deptCode = enterprise.getDepartment().getDeptCode();
		this.deptName = enterprise.getDepartment().getDeptName();
		this.entName = enterprise.getEntName();
		this.parentCode = enterprise.getParentCode();
		this.entLevel = enterprise.getEntLevel();
		this.entAddr = enterprise.getEntAddr();
		this.principle = enterprise.getPrinciple();
		this.entTel = enterprise.getEntTel();
		this.prinPhone = enterprise.getPrinPhone();
		this.note = enterprise.getNote();
	}

	public int getEntCode() {
		return entCode;
	}

	public void setEntCode(int entCode) {
		this.entCode = entCode;
	}


	public String getEntTypeName() {
		return entTypeName;
	}

	public void setEntTypeName(String entTypeName) {
		this.entTypeName = entTypeName;
	}

	public int getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(int deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getEntLevel() {
		return entLevel;
	}

	public void setEntLevel(Integer entLevel) {
		this.entLevel = entLevel;
	}

	public String getEntAddr() {
		return entAddr;
	}

	public void setEntAddr(String entAddr) {
		this.entAddr = entAddr;
	}

	public String getPrinciple() {
		return principle;
	}

	public void setPrinciple(String principle) {
		this.principle = principle;
	}

	public String getEntTel() {
		return entTel;
	}

	public void setEntTel(String entTel) {
		this.entTel = entTel;
	}

	public String getPrinPhone() {
		return prinPhone;
	}

	public void setPrinPhone(String prinPhone) {
		this.prinPhone = prinPhone;
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
