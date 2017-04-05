package com.pingdu.view;
  
import java.io.Serializable;
import java.util.Date;

import com.pingdu.utility.PublicVariable;  
  
public class Report implements Serializable {  
      
    /** 
     *  
     */  
    private static final long serialVersionUID = 4497500574990765498L;  
  
    private Integer deptId;
      
    private String deptName;
    
    private Long stuID;
      
    private String stuName;
    
    private Date resTime;
    
    private String realTime;
      
    public Report(){};
  
    public Report(Integer deptId, String deptName, Long stuID,String stuName,Date resTime) {
        this.deptId = deptId;  
        this.deptName = deptName;
        this.stuID = stuID;
        this.stuName =stuName;
        this.resTime = resTime;
        this.realTime = PublicVariable.sdf.format(resTime);
    }
    //getters and setters...  

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getStuID() {
		return stuID;
	}

	public void setStuID(Long stuID) {
		this.stuID = stuID;
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

	public String getRealTime() {
		return realTime;
	}

	public void setRealTime(String realTime) {
		this.realTime = realTime;
	}


      
}  