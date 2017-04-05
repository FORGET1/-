package com.pingdu.entity.notice;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pingdu.baseModel.BaseEntity;

@Entity
@Table(name = "noticeAttach")
public class NoticeAttach extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1190521287479698667L;

	@Id
    @Column(name = "attachCode", length = 11)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int attachCode;
    
    /**  */
    @Column(name = "noticeCode",  length = 11)
    private int noticeCode;
    
    /**  */
    @Column(name = "attachName", length = 100)
    private String attachName;
    
    /**  */
    @Column(name = "attachPath", length = 200)
    private String attachPath;
    
    /**  */
    @Column(name = "note", length = 100)
    private String note;

	public int getAttachCode() {
		return attachCode;
	}

	public void setAttachCode(int attachCode) {
		this.attachCode = attachCode;
	}

	public int getNoticeCode() {
		return noticeCode;
	}

	public void setNoticeCode(int noticeCode) {
		this.noticeCode = noticeCode;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public String getAttachPath() {
		return attachPath;
	}

	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
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