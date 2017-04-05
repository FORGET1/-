package com.pingdu.entity.task_item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pingdu.baseModel.BaseEntity;

/**
 * @description 模型-- 
 */
@Entity
@Table(name = "task_item")
public class Task_item  extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1250540390488181204L;


	@Id
	@Column(name = "task_itemCode", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer task_itemCode;
	
	
	@Column(name = "taskCode", nullable = false)
	private Integer taskCode;
	

	@Column(name = "itemCode", nullable = false)
	private Integer itemCode;
	
	
	@Column(name = "isException")
	private Short isException;

	@Column(name = "note", length = 100)
	private String note;

	public Integer getTask_itemCode() {
		return task_itemCode;
	}

	public void setTask_itemCode(Integer task_itemCode) {
		this.task_itemCode = task_itemCode;
	}

	public Integer getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(Integer taskCode) {
		this.taskCode = taskCode;
	}

	public Integer getItemCode() {
		return itemCode;
	}

	public void setItemCode(Integer itemCode) {
		this.itemCode = itemCode;
	}

	public Short getIsException() {
		return isException;
	}

	public void setIsException(Short isException) {
		this.isException = isException;
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

//	public Task getTask() {
//		return task;
//	}
//
//	public void setTask(Task task) {
//		this.task = task;
//	}
	
	
	
}

