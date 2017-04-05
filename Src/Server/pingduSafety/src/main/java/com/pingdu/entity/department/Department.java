package com.pingdu.entity.department;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pingdu.baseModel.BaseEntity;
import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.entity.user.User;

/**
 * @description 模型-- 部门
 */
@Entity
@Table(name = "department")
public class Department extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1371032151428327167L;
	/**
	 * 部门编号
	 */
	@Id
	@Column(name = "deptCode", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deptCode;
	/**
	 * 部门名称
	 */
	@Column(name = "deptName", nullable = false, length = 20)
	private String deptName;
	/**
	 * 上级部门编号
	 */
	@Column(name = "parentCode")
	private Integer parentCode;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY,   
	            targetEntity = User.class, mappedBy = "department")
	 private Set<User> users;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY,   
            targetEntity = Enterprise.class, mappedBy = "department")
	private Set<Enterprise> enterprises;
	
	/**
	 * 部门级别
	 */
	@Column(name = "deptLevel")
	private Integer deptLevel;
	
	/**
	 * 部门地址
	 */
	@Column(name = "deptAddr",length=100)
	private String deptAddr;
	
	/**
	 * 部门联系人
	 */
	@Column(name = "contact",length=30)
	private String contact;
	
	/**
	 * 部门电话
	 */
	@Column(name = "deptPhone",length=20)
	private String deptPhone;
	
	/**
	 * 联系人电话
	 */
	@Column(name = "contactPhone",length=20)
	private String contactPhone;
	
	
	/**
	 * 备注
	 */
	@Column(name = "note",length=100)
	private String note;
	
	

	public Integer getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(Integer deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getParentCode() {
		return parentCode;
	}

	public void setParentCode(Integer parentCode) {
		this.parentCode = parentCode;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Integer getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(Integer deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getDeptAddr() {
		return deptAddr;
	}

	public void setDeptAddr(String deptAddr) {
		this.deptAddr = deptAddr;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDeptPhone() {
		return deptPhone;
	}

	public void setDeptPhone(String deptPhone) {
		this.deptPhone = deptPhone;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
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
	
}
