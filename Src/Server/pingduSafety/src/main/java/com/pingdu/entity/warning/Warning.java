package com.pingdu.entity.warning;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="warning")
public class Warning implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5690293533749007552L;

	/**
	 * 报警编号
	 */
	@Id
	@Column(name = "warningCode", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer warningCode;
	/**
	 * 标题
	 */
	@Column(name = "warningTitle", nullable = false, length = 30)
	private String warningTitle;
	/**
	 * 内容
	 */
	@Column(name = "warningInfo", nullable = false,length = 200)
	private String warningInfo;
	/**
	 * 用户名字
	 */
	@Column(name = "userName", nullable = false,length = 30)
	private String userName;
	/**
	 * 部门名称
	 */
	@Column(name = "deptName",nullable = false,length = 30)
	private String deptName;

	/**
	 * 报警日期
	 */
	@Column(name = "warningDate", nullable = false, length = 20)
	private String warningDate;
	
	/**
	 * 备注
	 */
	@Column(name = "note", nullable = false, length = 100)
	private String note;

	public Integer getWarningCode() {
		return warningCode;
	}

	public void setWarningCode(Integer warningCode) {
		this.warningCode = warningCode;
	}

	public String getWarningTitle() {
		return warningTitle;
	}

	public void setWarningTitle(String warningTitle) {
		this.warningTitle = warningTitle;
	}

	public String getWarningInfo() {
		return warningInfo;
	}

	public void setWarningInfo(String warningInfo) {
		this.warningInfo = warningInfo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getWarningDate() {
		return warningDate;
	}

	public void setWarningDate(String warningDate) {
		this.warningDate = warningDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	
	
	
}
