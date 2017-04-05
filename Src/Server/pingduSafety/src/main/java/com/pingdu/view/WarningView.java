package com.pingdu.view;

import com.pingdu.baseModel.BaseEntity;
import com.pingdu.entity.warning.Warning;

public class WarningView extends BaseEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer warningCode;
	String  warningTitle;
	String  warningInfo;
	String  warningDate;
	
	public WarningView(){}
	public WarningView(Warning warning ){
		this.warningCode=warning.getWarningCode();
		this.warningTitle=warning.getWarningTitle();
		this.warningInfo=warning.getWarningInfo();
		this.warningDate=warning.getWarningDate();
	}
	public Integer getWarningCode() {
		return warningCode;
	}
	public void setWarningCode(Integer warningCode) {
		this.warningCode = warningCode;
	}
	public String getWarningTitle() {
		return warningTitle;
	}
	public void setWarningTitle(String warningTitle) {
		this.warningTitle = warningTitle;
	}
	public String getWarningInfo() {
		return warningInfo;
	}
	public void setWarningInfo(String warningInfo) {
		this.warningInfo = warningInfo;
	}
	public String getWarningDate() {
		return warningDate;
	}
	public void setWarningDate(String warningDate) {
		this.warningDate = warningDate;
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
