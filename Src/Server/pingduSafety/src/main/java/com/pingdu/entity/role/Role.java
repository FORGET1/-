package com.pingdu.entity.role;

import java.io.Serializable;
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

import com.pingdu.entity.user.User;

/**
 * @description 模型-- 角色
 */
@Entity
@Table(name = "role")
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2412329306397741125L;
	/**
	 * 角色编号
	 */
	@Id
	@Column(name = "roleCode", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleCode;
	/**
	 * 角色名称
	 */
	@Column(name = "roleName", nullable = false, length = 20)
	private String roleName;
	/**
	 * 权限
	 */
	@Column(name = "permissions", nullable = false)
	private Integer permissions;
	/**
	 * 状态
	 */
	@Column(name = "state", nullable = false)
	private Short state;
	
	/**
	 * 备注
	 */
	@Column(name = "note", nullable = false, length =100)
	private String note;
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY,   
	            targetEntity = User.class, mappedBy = "role")
	 private Set<User> users;

	public Integer getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(Integer roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getPermissions() {
		return permissions;
	}

	public void setPermissions(Integer permissions) {
		this.permissions = permissions;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
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
	 
	 
	
}
