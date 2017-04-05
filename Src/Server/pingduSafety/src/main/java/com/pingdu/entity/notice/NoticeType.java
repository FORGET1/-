package com.pingdu.entity.notice;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pingdu.baseModel.BaseEntity;

@Entity
@Table(name = "noticeType")
public class NoticeType  extends BaseEntity{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -3104802422376657623L;

	/**  */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "noticeTypeCode",length = 11)
    
    private int noticeTypeCode;
    
    /**  */
    @Column(name = "noticeTypeName",  length = 30)
    private String noticeTypeName;
    
    /**  */
    @Column(name = "note",  length = 100)
    private String note;

	public int getNoticeTypeCode() {
		return noticeTypeCode;
	}

	public void setNoticeTypeCode(Integer noticeTypeCode) {
		this.noticeTypeCode = noticeTypeCode;
	}

	public String getNoticeTypeName() {
		return noticeTypeName;
	}

	public void setNoticeTypeName(String noticeTypeName) {
		this.noticeTypeName = noticeTypeName;
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