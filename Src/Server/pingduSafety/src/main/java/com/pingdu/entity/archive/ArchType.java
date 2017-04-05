package com.pingdu.entity.archive;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.pingdu.baseModel.BaseEntity;


@XmlRootElement
@Entity
@Table(name = "archiveType")
public class ArchType extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2175699715410960375L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "archTypeCode", length = 10)
    private Integer archTypeCode;
   
    @Column(name = "archName", length = 100)
    private String archName;
    
    @Column(name = "validTerm", length = 10)
    private Integer validTerm;
    
    @Column(name = "note", length = 100)
    private String note;



	public Integer getArchTypeCode() {
		return archTypeCode;
	}

	public void setArchTypeCode(Integer archTypeCode) {
		this.archTypeCode = archTypeCode;
	}

	public String getArchName() {
		return archName;
	}

	public void setArchName(String archName) {
		this.archName = archName;
	}

	public Integer getValidTerm() {
		return validTerm;
	}

	public void setValidTerm(Integer validTerm) {
		this.validTerm = validTerm;
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