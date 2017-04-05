package com.pingdu.view;

import com.pingdu.baseModel.BaseEntity;

public class archiveApp	extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5378784029145843321L;
	private int  archiveCode;
	private String archName;
	public int getArchiveCode() {
		return archiveCode;
	}
	public void setArchiveCode(int archiveCode) {
		this.archiveCode = archiveCode;
	}
	public String getArchName() {
		return archName;
	}
	public void setArchName(String archName) {
		this.archName = archName;
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
