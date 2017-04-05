package com.pingdu.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pingdu.entity.role.Role;
import com.pingdu.baseModel.BaseEntity;
import com.pingdu.entity.department.Department;
import com.pingdu.entity.enterprise.Enterprise;

/**
 * @description 模型-- 用户
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -632205770325222322L;
	/**
	 * 工号
	 */
	@Id
	@Column(name = "userCode", nullable = false, length = 20)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String userCode;
	/**
	 * 密码
	 */
	@Column(name = "password", nullable = false, length = 16)
	private String password;
	/**
	 * 密码盐
	 */
	@Column(name = "salt", nullable = false,length = 10)
	private String salt;
	/**
	 * 角色
	 */
	@ManyToOne
	@JoinColumn(name = "roleCode", nullable = false)
	private Role role;
	/**
	 * 企业
	 */
	@ManyToOne
	@JoinColumn(name = "entCode")
	private Enterprise enterprise;
	/**
	 * 部门
	 */
	@ManyToOne
	@JoinColumn(name = "deptCode")
	private Department department;
	/**
	 * 姓名
	 */
	@Column(name = "name", nullable = false, length = 20)
	private String name;
	/**
	 * 性别
	 */
	@Column(name = "gender", nullable = false, length = 10)
	private String gender;
	/**
	 * 出生日期
	 */
	@Column(name = "birthDate",nullable = false, length = 50)
	private String birthDate;
	/**
	 * 身份证
	 */
	@Column(name = "id", length = 18)
	private String id;
	/**
	 * 电话
	 */
	@Column(name = "phone", nullable = false, length = 20)
	private String phone;
	/**
	 * 物理地址
	 */
	@Column(name = "mac", length = 100)
	private String mac;
	/**
	 * 备注
	 */
	@Column(name = "note", length = 100)
	private String note;

	public void modifyUser(User user){
		this.birthDate = user.getBirthDate();
		this.gender =user.getGender();
		this.id = user.getId();
		this.name = user.getName();
		this.phone = user.getPhone();
		this.password = user.getPassword();
		this.salt = user.getSalt();
	}

	/*----------------------------------get()&set()-------------------------------------*/

	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	public Department getDept() {
		return department;
	}
	public void setDept(Department department) {
		this.department = department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
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