package com.pingdu.entity.notice;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.pingdu.baseModel.BaseEntity;


@Entity
@Table(name = "notice")
public class Notice extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**  */
    @Id
    @Column(name = "noticeCode",  length = 11)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int noticeCode;
    
    /**  */
    @Column(name = "noticeTypeCode", length = 255)
    private int noticeTypeCode;
    
    /**  */
    @Column(name = "noticeTitle", length = 100)
    private String noticeTitle;
    
    /**  */
    @Column(name = "noticeInfo",length = 200)
    private String noticeInfo;
    
    /**  */
    @Column(name = "note", length = 100)
    private String note;

	public int getNoticeCode() {
		return noticeCode;
	}

	public void setNoticeCode(int noticeCode) {
		this.noticeCode = noticeCode;
	}

	public int getNoticeTypeCode() {
		return noticeTypeCode;
	}

	public void setNoticeTypeCode(int noticeTypeCode) {
		this.noticeTypeCode = noticeTypeCode;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeInfo() {
		return noticeInfo;
	}

	public void setNoticeInfo(String noticeInfo) {
		this.noticeInfo = noticeInfo;
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