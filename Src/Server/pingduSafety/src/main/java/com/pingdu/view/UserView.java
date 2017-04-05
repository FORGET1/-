package com.pingdu.view;

import com.pingdu.baseModel.BaseEntity;
import com.pingdu.entity.user.User;

public class UserView extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3042038344806791510L;
	private String userCode;
	private String userName;
	private Integer deptCode;
	private String deptName;
	private String roleName;
	private String gender;
	private String birthDate;
	private String password;
	private String salt;
	private String id;
	private String phone;
	private String mac;
	private String note;
	
	
	public UserView(){}
	
	public UserView(String userCode, String userName, Integer deptCode,String deptName,
		 String roleName){
		this.userCode = userCode;
		this.userName = userName;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.roleName = roleName;
	}
	
	public UserView(String userCode, String userName, Integer deptCode, String deptName,String roleName, String gender, String birthDate, String id, String phone, String mac,
			String note) {
		super();
		this.userCode = userCode;
		this.userName = userName;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.roleName = roleName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.id = id;
		this.phone = phone;
		this.mac = mac;
		this.note = note;
	}
	
	public UserView(User user){
		super();
		this.userCode = user.getUserCode();
		this.userName = user.getName();
		int roleCode = user.getRole().getRoleCode();
		if(roleCode <= 3){
			this.deptCode = user.getDept().getDeptCode();
			this.deptName = user.getDept().getDeptName();
		}
		else{
			this.deptCode = user.getEnterprise().getEntCode();
			this.deptName = user.getEnterprise().getEntName();
		}
		this.password = user.getPassword();
		this.salt = user.getSalt();
		this.roleName = user.getRole().getRoleName();
		this.gender = user.getGender();
		this.birthDate = user.getBirthDate();
		this.id = user.getId();
		this.phone = user.getPhone();
		this.mac = user.getMac();
		this.note = user.getNote();
	}
	
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
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
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
