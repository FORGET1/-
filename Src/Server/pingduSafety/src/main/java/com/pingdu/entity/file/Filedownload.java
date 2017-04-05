package com.pingdu.entity.file;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.pingdu.baseModel.BaseEntity;

@Entity
@Table(name = "fileDownload")
public class Filedownload  extends BaseEntity{
    /** 版本号 */
    private static final long serialVersionUID = 7128988884413339167L;
    
    /**  */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "file_DownCode", length = 10)
    private Integer fileDowncode;
    
    /**  */
    @Column(name = "fileCode", length = 10)
    private Integer fileCode;
    
    /**  */
    @Column(name = "userCode", length = 10)
    private Integer userCode;
    
    /**  */
    @Column(name = "isDownload", length = 3)
    private Integer isDownload;
    
    /**  */
    @Column(name = "note" ,length = 100)
    private String note;
    
    /**  */
    @Column(name = "downloadDate")
    private String downloadDate;

	public Integer getFileDowncode() {
		return fileDowncode;
	}

	public void setFileDowncode(Integer fileDowncode) {
		this.fileDowncode = fileDowncode;
	}

	public Integer getFileCode() {
		return fileCode;
	}

	public void setFileCode(Integer fileCode) {
		this.fileCode = fileCode;
	}

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}

	public Integer getIsDownload() {
		return isDownload;
	}

	public void setIsDownload(Integer isDownload) {
		this.isDownload = isDownload;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDownloadDate() {
		return downloadDate;
	}

	public void setDownloadDate(String downloadDate) {
		this.downloadDate = downloadDate;
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