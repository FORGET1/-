package com.pingdu.entity.notice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pingdu.baseModel.BaseEntity;

@Entity
@Table(name = "noticeRread")
public class NoticeRead extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -452757071859920842L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "noticeReadCode", length = 11)
    private int noticeReadCode;
    
    /**  */
    @Column(name = "noticeCode",  length = 11)
    private int noticeCode;
    
    /**  */
    @Column(name = "userCode", length = 11)
    private int userCode;
    
    /**  */
    @Column(name = "isRead", length = 4)
    private boolean isRead;
    
    /**  */
    @Column(name = "note",length = 100)
    private String note;
    
    /**  */
    @Column(name = "readDate")
    private String  readDate;

	public int getNoticeReadCode() {
		return noticeReadCode;
	}

	public void setNoticeReadCode(int noticeReadCode) {
		this.noticeReadCode = noticeReadCode;
	}

	public int getNoticeCode() {
		return noticeCode;
	}

	public void setNoticeCode(int noticeCode) {
		this.noticeCode = noticeCode;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getReadDate() {
		return readDate;
	}

	public void setReadDate(String readDate) {
		this.readDate = readDate;
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