package com.pingdu.entity.item;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.pingdu.baseModel.BaseEntity;
import com.pingdu.entity.point.Point;
import com.pingdu.entity.task.Task;

@Entity
@Table(name = "item")
public class Item extends BaseEntity implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "itemCode", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemCode;
	
	@Column(name = "itemInfo", length = 100)
	private String itemInfo;

	@Column(name = "note", length = 100)
	private String note;

	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.LAZY) 
	@JoinTable(name = "task_item",
    joinColumns ={@JoinColumn(name = "itemCode", referencedColumnName = "itemCode") },   
    inverseJoinColumns = { @JoinColumn(name = "taskCode", referencedColumnName = "taskCode")
})  
	@JsonIgnore
	private Set<Task> tasks = new HashSet<>();
	
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, fetch = FetchType.LAZY )
	@JoinTable(name = "point_item",
	joinColumns = {@JoinColumn(name = "itemCode", referencedColumnName = "itemCode")},
	inverseJoinColumns = {@JoinColumn(name = "pointCode", referencedColumnName = "pointCode")}
	)
	@JsonIgnore
	private Set<Point> points = new HashSet<>();


	public Integer getItemCode() {
		return itemCode;
	}

	public void setItemCode(Integer itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(String itemInfo) {
		this.itemInfo = itemInfo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<Point> getPoints() {
		return points;
	}

	public void setPoints(Set<Point> points) {
		this.points = points;
	}

	
	public Item() {
		
	}

	public Item( String itemInfo) {
		super();
		this.itemInfo = itemInfo;
		

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

