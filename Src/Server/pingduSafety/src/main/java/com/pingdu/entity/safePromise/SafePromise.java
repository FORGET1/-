package com.pingdu.entity.safePromise;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pingdu.baseModel.BaseEntity;


/**
 * (SAFETYPROMISE)
 * 
 * @description---安全承诺书
 * 
 */
@Entity
@Table(name = "safetyPromise")
public class SafePromise extends BaseEntity implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7125825083803197961L;

	/**  */
    @Id
    @Column(name = "safePromiseCode", unique = true, nullable = false, length = 11)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer safePromiseCode;
    
    /**  */
    @Column(name = "entCode", nullable = false, length = 11)
    private Integer entCode;
    
    /**  */
    @Column(name = "uploadDate", length = 20)
    private String uploadDate;
    
    /**  */
    @Column(name = "expireDate", length = 20)
    private String expireDate;
    
    /**  */
    @Column(name = "isUpload", nullable = true)
    private Short isUpload;
    
    @Column(name = "issueDate", length = 20)
    private String issueDate;
    
    /**  */
    @Column(name = "imagePath", length = 200)
    private String imagePath;
    
    /**  */
    @Column(name = "note", length = 100)
    private String note;
    
    @Transient
    private String entName;
     
    @Transient
    private String deptName;
    
    
    public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	

	public Short getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(Short isUpload) {
		this.isUpload = isUpload;
	}

	/**
     * 获取
     * 
     * @return 
     */
    public Integer getSafePromiseCode() {
        return this.safePromiseCode;
    }
     
    /**
     * 设置
     * 
     * @param safePromiseCode
     *          
     */
    public void setSafePromiseCode(Integer safePromiseCode) {
        this.safePromiseCode = safePromiseCode;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public Integer getEntCode() {
        return this.entCode;
    }
     
    /**
     * 设置
     * 
     * @param entCode
     *          
     */
    public void setEntCode(Integer entCode) {
        this.entCode = entCode;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getUploadDate() {
        return this.uploadDate;
    }
     
    /**
     * 设置
     * 
     * @param uploadDate
     *          
     */
    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getExpireDate() {
        return this.expireDate;
    }
     
    /**
     * 设置
     * 
     * @param expireDate
     *          
     */
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
    

    


	/**
     * 获取
     * 
     * @return 
     */
    public String getImagePath() {
        return this.imagePath;
    }
     
    /**
     * 设置
     * 
     * @param imagePath
     *          
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getNote() {
        return this.note;
    }
     
    /**
     * 设置
     * 
     * @param note
     *          
     */
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