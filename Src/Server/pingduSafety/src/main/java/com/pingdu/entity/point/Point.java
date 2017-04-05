package com.pingdu.entity.point;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.pingdu.baseModel.BaseEntity;
import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.entity.item.Item;
import com.pingdu.entity.task.Task;


@Entity
@Table(name="point")
public class Point extends BaseEntity{

	
	private static final long serialVersionUID = 8580785751737594644L;

	//项点编号
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="pointCode",nullable=false)
	private Integer pointCode;
	
	// 企业编号
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="entCode",nullable = false)
	private Enterprise enterprise;
	
	//NFC标签编号
	@Column(name = "NFCCode",length=10)
	private String NFCCode;
	
	//项点名称
	@Column(name = "pointName",length=30, nullable = false)
	private String pointName;
	
	//项点地址
	@Column(name = "pointAddr",length=100, nullable = false)
	private String pointAddr;
	
	//安全提示
	@Column(name = "safetyTips",length=200)
	private String safetyTips;
	
	//项点信息
	@Column(name = "pointInfo",length = 200, nullable = false)
	private String pointInfo;
	
	//巡检周期
	@Column(name = "patrolCircle", nullable = false)
	private Integer patrolCircle;
	//负责人
	@Column(name = "pointPerson",length = 20)
	private String pointPerson;
	//负责人电话
	@Column(name = "pointPersonTel",length = 20)
	private String pointPersonTel;
	//备注
	@Column(name = "note",length = 100)
	private String note;
	//项点纬度
	@Column(name = "lantitude")
	private Double lantitude;
	//项点经度
	@Column(name = "longtitude")
	private Double longtitude;
	
	@OneToMany(cascade = {CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "point")
	@JsonIgnore
	private Set<Task> tasks = new HashSet<Task>();
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name = "point_item",
			joinColumns = {	@JoinColumn(name = "pointCode", referencedColumnName = "pointCode")},
			inverseJoinColumns = { @JoinColumn(name = "itemCode", referencedColumnName = "itemCode")})
	private Set<Item> items = new HashSet<Item>();
    /*
     * 构造器
     */
	public Point(){
		
	}
	public Point(Integer pointCode,String pointName){
		super();
		this.pointCode = pointCode;
		this.pointName = pointName;
	}
	public Point(Integer pointCode,  String pointName, String pointAddr,
			 String pointPerson, String pointPersonTel,
			Double lantitude, Double longtitude,String safetyTips) {
		super();
		this.pointCode = pointCode;
		this.pointName = pointName;
		this.pointAddr = pointAddr;
		this.safetyTips = safetyTips;
		this.pointPerson = pointPerson;
		this.pointPersonTel = pointPersonTel;
		this.lantitude = lantitude;
		this.longtitude = longtitude;
	}
	public Point(Integer pointCode, Enterprise enterprise, String nFCCode, String pointName, String pointAddr,
			String safetyTips, String pointInfo, Integer patrolCircle, String pointPerson, String pointPersonTel,
			String note, Double lantitude, Double longtitude) {
		super();
		this.pointCode = pointCode;
		this.enterprise = enterprise;
		NFCCode = nFCCode;
		this.pointName = pointName;
		this.pointAddr = pointAddr;
		this.safetyTips = safetyTips;
		this.pointInfo = pointInfo;
		this.patrolCircle = patrolCircle;
		this.pointPerson = pointPerson;
		this.pointPersonTel = pointPersonTel;
		this.note = note;
		this.lantitude = lantitude;
		this.longtitude = longtitude;
	}



	public Point(Integer pointCode, Enterprise enterprise, String nFCCode, String pointName, String pointAddr,
			String safetyTips, String pointInfo, Integer patrolCircle, String pointPerson, String pointPersonTel,
			String note, Double lantitude, Double longtitude, Set<Task> tasks, Set<Item> items) {
		super();
		this.pointCode = pointCode;
		this.enterprise = enterprise;
		NFCCode = nFCCode;
		this.pointName = pointName;
		this.pointAddr = pointAddr;
		this.safetyTips = safetyTips;
		this.pointInfo = pointInfo;
		this.patrolCircle = patrolCircle;
		this.pointPerson = pointPerson;
		this.pointPersonTel = pointPersonTel;
		this.note = note;
		this.lantitude = lantitude;
		this.longtitude = longtitude;
		this.tasks = tasks;
		this.items = items;
	}



	/*
	 * setter and getter
	 */
	public Integer getPointCode() {
		return pointCode;
	}

	public void setPointCode(Integer pointCode) {
		this.pointCode = pointCode;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getNFCCode() {
		return NFCCode;
	}

	public void setNFCCode(String nFCCode) {
		NFCCode = nFCCode;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public String getPointAddr() {
		return pointAddr;
	}

	public void setPointAddr(String pointAddr) {
		this.pointAddr = pointAddr;
	}

	public String getSafetyTips() {
		return safetyTips;
	}

	public void setSafetyTips(String safetyTips) {
		this.safetyTips = safetyTips;
	}

	public String getPointInfo() {
		return pointInfo;
	}

	public void setPointInfo(String pointInfo) {
		this.pointInfo = pointInfo;
	}

	public Integer getPatrolCircle() {
		return patrolCircle;
	}

	public void setPatrolCircle(Integer patrolCircle) {
		this.patrolCircle = patrolCircle;
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

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String getPointPerson() {
		return pointPerson;
	}



	public void setPointPerson(String pointPerson) {
		this.pointPerson = pointPerson;
	}



	public String getPointPersonTel() {
		return pointPersonTel;
	}



	public void setPointPersonTel(String pointPersonTel) {
		this.pointPersonTel = pointPersonTel;
	}



	public Double getLantitude() {
		return lantitude;
	}



	public void setLantitude(Double lantitude) {
		this.lantitude = lantitude;
	}

	public Double getLongtitude() {
		return longtitude;
	}


	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
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
