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
@Table(name = "acvType_entType")
public class ArchTypeEntType extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5442344358513201701L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "archType_entTypeCode",length = 11)
    private int archtypeEnttypecode;
    
    /**  */
    @Column(name = "entTypeCode",length = 11)
    private int entTypeCode;
    
    /**  */
    @Column(name = "archTypeCode", length = 11)
    private int archTypeCode;
    
    /**  */
    @Column(name = "note",length = 100)
    private String note;

	public int getArchtypeEnttypecode() {
		return archtypeEnttypecode;
	}

	public void setArchtypeEnttypecode(int archtypeEnttypecode) {
		this.archtypeEnttypecode = archtypeEnttypecode;
	}

	public int getEntTypeCode() {
		return entTypeCode;
	}

	public void setEntTypeCode(int entTypeCode) {
		this.entTypeCode = entTypeCode;
	}

	public int getArchTypeCode() {
		return archTypeCode;
	}

	public void setArchTypeCode(int archTypeCode) {
		this.archTypeCode = archTypeCode;
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