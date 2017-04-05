package com.pingdu.entity.file;
/*
 * Welcome to use the TableGo Tools.
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 * 
 * Author:bianj
 * Email:edinsker@163.com
 * Version:4.1.2
 */

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pingdu.baseModel.BaseEntity;

/**
 * (FILE)
 * 
 * @author bianj
 * @version 1.0.0 2017-03-18
 */
@Entity
@Table(name = "file")
public class Files extends BaseEntity{
    /** 版本号 */
    private static final long serialVersionUID = 8648227485980216523L;
    
    /**  */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "fileCode", length = 10)
    private Integer fileCode;
    
    /**  */
    @Column(name = "fileName", length = 100)
    private String fileName;
    
    /**  */
    @Column(name = "filePath", length = 200)
    private String filePath;
    
    /**  */
    @Column(name = "note", length = 100)
    private String note;
  
	public Integer getFileCode() {
		return fileCode;
	}

	public void setFileCode(Integer fileCode) {
		this.fileCode = fileCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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