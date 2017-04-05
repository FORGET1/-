package com.pingdu.view;

import java.util.List;

import java.util.Map;

import com.pingdu.baseModel.BaseEntity;

public class arcAppRoot extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9161986267044229431L;
	private List dataList;
	private Map<String ,Integer> page;
	private Map<String , Integer> Days;
	public Map<String, Integer> getDays() {
		return Days;
	}
	public void setDays(Map<String, Integer> days) {
		Days = days;
	}
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public Map<String, Integer> getPage() {
		return page;
	}
	public void setPage(Map<String, Integer> page) {
		this.page = page;
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
