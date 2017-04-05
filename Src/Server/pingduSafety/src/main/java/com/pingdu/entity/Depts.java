package com.pingdu.entity;
  
import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;  
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table; 
  
@Entity  
@Table(name="depts")  
public class Depts implements Serializable {  
      
    /**
	 * 
	 */
	private static final long serialVersionUID = -267959016633266801L;

    @Id  
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name= "deptId")
    private Integer deptId;
      
    @Column(name= "deptName", length= 50, nullable= false)
    private String deptName;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY,   
            targetEntity = Students.class, mappedBy = "depts")
    private Set<Students> students;
    
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
    
}  