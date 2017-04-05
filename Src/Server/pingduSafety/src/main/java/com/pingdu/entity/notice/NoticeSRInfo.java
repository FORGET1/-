package com.pingdu.entity.notice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pingdu.baseModel.BaseEntity;


@Entity
@Table(name = "noticeSRInfo")

public class NoticeSRInfo extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2756624595590697330L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "noticeSRInfoCode",length = 11)
    
    private int noticeSRInfoCode;
    
    /**  */
    @Column(name = "noticeCode",length = 11)
    private int noticeCode;
    
    /**  */
    @Column(name = "senderType", length = 20)
    private String senderType;
    
    /**  */
    @Column(name = "senderCode", length = 11)
    private int senderCode;
    
    /**  */
    @Column(name = "receiverType", length = 20)
    private String receiverType;
    
    /**  */
    @Column(name = "receiverCode",length = 11)
    private int receiverCode;
    
    /**  */
    @Column(name = "readPersonNum",length = 11)
    private int readPersonNum;
    
    /**  */
    @Column(name = "receiverPersonNum", length = 11)
    private int receiverPersonNum;
    
    /**  */
    @Column(name = "note",  length = 100)
    private String note;
    
    /**  */
    @Column(name = "senderName",length = 20)
    private String senderName;
    
    /**  */
    @Column(name = "sendDate")
    private String sendDate;

	public int getNoticeSRInfoCode() {
		return noticeSRInfoCode;
	}

	public void setNoticeSRInfoCode(int noticeSRInfoCode) {
		this.noticeSRInfoCode = noticeSRInfoCode;
	}

	public int getNoticeCode() {
		return noticeCode;
	}

	public void setNoticeCode(int noticeCode) {
		this.noticeCode = noticeCode;
	}

	public String getSenderType() {
		return senderType;
	}

	public void setSenderType(String senderType) {
		this.senderType = senderType;
	}

	public int getSenderCode() {
		return senderCode;
	}

	public void setSenderCode(int senderCode) {
		this.senderCode = senderCode;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public int getReceiverCode() {
		return receiverCode;
	}

	public void setReceiverCode(int receiverCode) {
		this.receiverCode = receiverCode;
	}

	public int getReadPersonNum() {
		return readPersonNum;
	}

	public void setReadPersonNum(int readPersonNum) {
		this.readPersonNum = readPersonNum;
	}

	public int getReceiverPersonNum() {
		return receiverPersonNum;
	}

	public void setReceiverPersonNum(int receiverPersonNum) {
		this.receiverPersonNum = receiverPersonNum;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
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