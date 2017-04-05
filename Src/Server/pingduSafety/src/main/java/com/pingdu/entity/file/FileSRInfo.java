package com.pingdu.entity.file;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pingdu.baseModel.BaseEntity;



@Entity
@Table(name = "fileSRInfo")
public class FileSRInfo extends BaseEntity {
    /** 版本号 */
    private static final long serialVersionUID = 7550248671810609377L;
    
    /**  */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "fileSRInfocode", length = 10)
    private Integer filesrinfocode;
    
    /**  */
    @Column(name = "fileCode", length = 10)
    private Integer fileCode;
    
    /**  */
    @Column(name = "senderType", length = 10)
    private String senderType;
    
    /**  */
    @Column(name = "senderCode", length = 10)
    private Integer senderCode;
    
    /**  */
    @Column(name = "receiverType", length = 10)
    private String receiverType;
    
    /**  */
    @Column(name = "receiverCode", length = 10)
    private Integer receiverCode;
    
    /**  */
    @Column(name = "downloadPersonNum", length = 10)
    private Integer downloadPersonNum;
    
    /**  */
    @Column(name = "sendPersonNum", length = 10)
    private Integer sendPersonNum;
    
    /**  */
    @Column(name = "note", length = 100)
    private String note;
    
    /**  */
    @Column(name = "uploadDate", nullable = false)
    private String uploadDate;
    
    /**  */
    @Column(name = "senderName", length = 20)
    private String senderName;


	public Integer getFilesrinfocode() {
		return filesrinfocode;
	}

	public void setFilesrinfocode(Integer filesrinfocode) {
		this.filesrinfocode = filesrinfocode;
	}

	public Integer getFileCode() {
		return fileCode;
	}

	public void setFileCode(Integer fileCode) {
		this.fileCode = fileCode;
	}

	public String getSenderType() {
		return senderType;
	}

	public void setSenderType(String senderType) {
		this.senderType = senderType;
	}

	public Integer getSenderCode() {
		return senderCode;
	}

	public void setSenderCode(Integer senderCode) {
		this.senderCode = senderCode;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public Integer getReceiverCode() {
		return receiverCode;
	}

	public void setReceiverCode(Integer receiverCode) {
		this.receiverCode = receiverCode;
	}

	public Integer getDownloadPersonNum() {
		return downloadPersonNum;
	}

	public void setDownloadPersonNum(Integer downloadPersonNum) {
		this.downloadPersonNum = downloadPersonNum;
	}

	public Integer getSendPersonNum() {
		return sendPersonNum;
	}

	public void setSendPersonNum(Integer sendPersonNum) {
		this.sendPersonNum = sendPersonNum;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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