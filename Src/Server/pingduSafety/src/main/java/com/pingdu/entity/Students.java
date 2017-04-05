package com.pingdu.entity;  
  
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id;  
import javax.persistence.JoinColumn;  
import javax.persistence.ManyToOne;  
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;  
  
@Entity  
@Table(name= "students")  
public class Students implements Serializable {  
      
    /**
	 * 
	 */
	private static final long serialVersionUID = 8579685696251603834L;

	@Id  
    @GeneratedValue(strategy= GenerationType.AUTO)  
    @Column(name= "stuNo")  
    private Long stuNo;
      
    @ManyToOne
    @JoinColumn(name= "deptId", nullable= false)
    private Depts depts;
      
    @Column(name= "stuName", length= 50, nullable= false)
    private String stuName;
    
    @Column(name= "resTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resTime;

    
    //getters and setters...  
	public Long getStuNo() {
		return stuNo;
	}

	public void setStuNo(Long stuNo) {
		this.stuNo = stuNo;
	}

	public Depts getDepts() {
		return depts;
	}

	public void setDepts(Depts depts) {
		this.depts = depts;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Date getResTime() {
		return resTime;
	}

	public void setResTime(Date resTime) {
		this.resTime = resTime;
	}
  

      
}  