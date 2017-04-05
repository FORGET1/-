package com.pingdu.view;

import java.util.List;
import java.util.Map;

import com.pingdu.baseModel.BaseEntity;

public class ManyLlistToJSON extends BaseEntity{

	private Map<String,Integer> Page;
	
	private List DataList;
	
	public Map<String, Integer> getPage() {
		return Page;
	}

	public void setPage(Map<String, Integer> page) {
		Page = page;
	}

	public List getDataList() {
		return DataList;
	}

	public void setDataList(List dataList) {
		DataList = dataList;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}