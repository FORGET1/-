package com.pingdu.view;

import java.util.List;

public class PointAndPage {
	/*
	 * 项点列表
	 */
	private List<PointView> points;
	/*
	 * 当前页码
	 */
	private Integer page;
	/*
	 * 总页数，最后一页的页码
	 */
	private Integer lastpage;
	
	/*
	 * 构造函数
	 */
	public PointAndPage(){
		
	}

	public PointAndPage(List<PointView> points, Integer page, Integer lastpage) {
		super();
		this.points = points;
		this.page = page;
		this.lastpage = lastpage;
	}

	/*
	 * setter and getter
	 */
	public List<PointView> getPoints() {
		return points;
	}

	public void setPoints(List<PointView> points) {
		this.points = points;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLastpage() {
		return lastpage;
	}

	public void setLastpage(Integer lastpage) {
		this.lastpage = lastpage;
	}
	
	
}
