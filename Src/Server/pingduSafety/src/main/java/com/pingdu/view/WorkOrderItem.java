package com.pingdu.view;

import com.pingdu.baseModel.BaseEntity;

public class WorkOrderItem extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1915400543456751559L;
	public int itemCode; // 条目编号
	public String itemInfo; // 条目信息
	public Short  isException;
	public boolean yesIsChecked = false; // 是否选中

	
	public WorkOrderItem(int itemCode, short isException) {
		super();
		this.itemCode = itemCode;
		this.isException = isException;
	}

//	public WorkOrderItem(int itemCode, String itemInfo) {
//		this.itemCode = itemCode;
//		this.itemInfo = itemInfo;
//	}

	public WorkOrderItem(int itemCode, String itemInfo, boolean yesIsChecked) {
		this.itemCode = itemCode;
		this.itemInfo = itemInfo;
		this.yesIsChecked = yesIsChecked;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public int getItemCode() {
		return itemCode;
	}

	public String getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(String itemInfo) {
		this.itemInfo = itemInfo;
	}

	public boolean isYesIsChecked() {
		return yesIsChecked;
	}

	public void setYesIsChecked(boolean yesIsChecked) {
		this.yesIsChecked = yesIsChecked;
	}

	public Short getIsException() {
		return isException;
	}

	public void setIsException(Short isException) {
		this.isException = isException;
	}

	public WorkOrderItem(int itemCode, String itemInfo, short isException) {
		super();
		this.itemCode = itemCode;
		this.itemInfo = itemInfo;
		this.isException = isException;
	}

	public WorkOrderItem() {
	
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
