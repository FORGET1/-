package com.pingdu.entity.entType;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enterpriseType")
public class EntType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6716304115930975715L;

	
	@Id
	@Column(name = "entTypeCode", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer entTypeCode;
	
	@Column(name = "entTypeName", nullable = false, length = 30)
	private String entTypeName;
	
	@Column(name = "needSafety",nullable = false)
	private Short needSafety;

	@Column(name = "note", length = 100)
	private String note;

	
	public EntType(){}
	public EntType(String entTypeName){
		this.entTypeName = entTypeName;
	}
	
	public Integer getEntTypeCode() {
		return entTypeCode;
	}

	public void setEntTypeCode(Integer entTypeCode) {
		this.entTypeCode = entTypeCode;
	}

	public String getEntTypeName() {
		return entTypeName;
	}

	public void setEntTypeName(String entTypeName) {
		this.entTypeName = entTypeName;
	}

	public Short getNeedSafety() {
		return needSafety;
	}

	public void setNeedSafety(Short needSafety) {
		this.needSafety = needSafety;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
