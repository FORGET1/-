package com.pingdu.entity.enterprise;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import com.pingdu.baseModel.BaseEntity;
import com.pingdu.entity.department.Department;
import com.pingdu.entity.entType.EntType;
import com.pingdu.entity.task.Task;
import com.pingdu.entity.user.User;

/**
 * @description 模型-- 企业信息管理
 */
@Entity
@Table(name = "enterprise")
public class Enterprise extends BaseEntity{

	private static final long serialVersionUID = 3284713003741715675L;

	/**
	 * 企业编号
	 */
	@Id
	@Column(name = "entCode", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer entCode;
	
	
	 @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY,   
	            targetEntity = User.class, mappedBy = "enterprise")
	 @JsonIgnore
	 private Set<User> users;
	

	 @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY,
	            targetEntity = Task.class, mappedBy = "enterprise")
	 @JsonIgnore
	 private Set<Task> task;

	/**
	 * 企业类型编号
	 */
	@ManyToOne
	@JoinColumn(name = "entTypeCode", nullable = false)
	private EntType entType;
	
	/**
	 * 部门编号
	 */
	@ManyToOne
	@JoinColumn(name = "deptCode", nullable = false)
	private Department department;
	
	/**
	 *企业名称
	 */
	@Column(name = "entName",length = 30)
	private String entName;
	
	/**
	 * 上级企业编号
	 */
	@Column(name = "parentCode")
	private Integer parentCode;
	
	/**
	 * 企业级别
	 */
	@Column(name = "entLevel", nullable = false)
	private Integer entLevel;
	/**
	 *企业地址
	 */
	@Column(name = "entAddr",length = 100, nullable = false)
	private String entAddr;
	
	/**
	 *负责人
	 */
	@Column(name = "principle",length = 30, nullable = false)
	private String principle;
	
	/**
	 *企业电话
	 */
	@Column(name = "entTel",length = 20, nullable = false)
	private String entTel;
	/**
	 *负责人电话
	 */
	@Column(name = "prinPhone",length = 20, nullable = false)
	private String prinPhone;
	
	/**
	 *备注
	 */
	@Column(name = "note", length = 100)
	private String note;
	
	
	public Integer getEntCode() {
		return entCode;
	}

	public void setEntCode(Integer entCode) {
		this.entCode = entCode;
	}

	
	public EntType getEntType() {
		return entType;
	}

	public void setEntType(EntType entType) {
		this.entType = entType;
	}


	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getEntLevel() {
		return entLevel;
	}

	public void setEntLevel(Integer entLevel) {
		this.entLevel = entLevel;
	}

	public String getEntAddr() {
		return entAddr;
	}

	public void setEntAddr(String entAddr) {
		this.entAddr = entAddr;
	}

	public String getPrinciple() {
		return principle;
	}

	public void setPrinciple(String principle) {
		this.principle = principle;
	}

	public String getEntTel() {
		return entTel;
	}

	public void setEntTel(String entTel) {
		this.entTel = entTel;
	}

	public String getPrinPhone() {
		return prinPhone;
	}

	public void setPrinPhone(String prinPhone) {
		this.prinPhone = prinPhone;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Task> getTask() {
		return task;
	}

	public void setTask(Set<Task> task) {
		this.task = task;
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
