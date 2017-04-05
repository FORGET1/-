package com.pingdu.view;

import java.util.List;

import com.pingdu.baseModel.BaseEntity;
import com.pingdu.entity.item.Item;
import com.pingdu.entity.task.Task;

public class TaskMap extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1878285699783445715L;
	private Task tasks;
	private TaskView taskView;
	private List<TaskView> taskViews;
	private List<WorkOrderItem> items;
	private  int page;
	private  int lastpage;
	private String status;
	public List<TaskView> getTaskViews() {
		return taskViews;
	}
	public void setTaskViews(List<TaskView> taskViews) {
		this.taskViews = taskViews;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLastpage() {
		return lastpage;
	}
	public void setLastpage(int lastpage) {
		this.lastpage = lastpage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Task getTasks() {
		return tasks;
	}
	public void setTasks(Task tasks) {
		this.tasks = tasks;
	}
	public List<WorkOrderItem> getItems() {
		return items;
	}
	public void setItems(List<WorkOrderItem> items) {
		this.items = items;
	}
	public TaskView getTaskView() {
		return taskView;
	}
	public void setTaskView(TaskView taskView) {
		this.taskView = taskView;
	}
	
	
	@Override
	public String toString() {
		
		return null;
	}
	@Override
	public boolean equals(Object o) {
		
		return false;
	}
	@Override
	public int hashCode() {
		
		return 0;
	}

	
	
}
