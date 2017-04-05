package com.pingdu.view;

import com.pingdu.baseModel.BaseEntity;
import com.pingdu.entity.point.Point;

public class PointView extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3065738546275489125L;

	/*
	 * 项点编号
	 */
	private Integer pointCode;
	/*
	 * 项点名称
	 */
	private String pointName;
	/*
	 * 项点地址
	 */
	private String pointAddr;
	/*
	 * 安全提示
	 */
	private String safetyTips;
	/*
	 * 巡检周期
	 */
	private Integer patrolCircle;
	/*
	 * NFC编码
	 */
	private String NFCCode;
	/*
	 * 企业编号
	 */
	private Integer entCode;
	
	/*
	 * 企业名称
	 */
	private String entName;
	/*
	 * 管辖部门编号
	 */
	private Integer deptCode;
	/*
	 * 管辖部门名称
	 */
	private String deptName;
	/*
	 * 负责人
	 */
	private String pointPerson;
	/*
	 * 负责人电话
	 */
	private String pointPersonTel;
	/*
	 * 项点经度
	 */
	private Double longtitude;
	/*
	 * 纬度
	 */
	private Double lantitude;
	
	/*
	 * 构造函数
	 */
	public PointView(){
		super();
	};
	

	
	public PointView(Integer pointCode, String pointName,Integer entCode,String entName,String pointAddr,
			String pointPerson,String pointPersonTel,Double lantitude,Double longtitude){
		super();
		this.pointCode = pointCode;
		this.pointName = pointName;
		this.entCode = entCode;
		this.entName = entName;
		this.pointPerson = pointPerson;
		this.pointPersonTel = pointPersonTel;
		this.pointAddr = pointAddr;
		this.lantitude = lantitude;
		this.longtitude = longtitude;
	}
	
	public PointView(Integer pointCode, String pointName,Integer entCode,String entName,
			String pointPerson,String pointPersonTel){
		super();
		this.pointCode = pointCode;
		this.pointName = pointName;
		this.entCode = entCode;
		this.entName = entName;
		this.pointPerson = pointPerson;
		this.pointPersonTel = pointPersonTel;
	}
	
	public PointView(Integer pointCode, String pointName,Integer entCode,String entName,
			String pointPerson,String pointPersonTel,
			Double lantitude,Double longtitude){
		super();
		this.pointCode = pointCode;
		this.pointName = pointName;
		this.entCode = entCode;
		this.entName = entName;
		this.pointPerson = pointPerson;
		this.pointPersonTel = pointPersonTel;
		this.lantitude = lantitude;
		this.longtitude = longtitude;
	}
	
	public PointView(Integer pointCode, String pointName, String pointAddr, String safetyTips, Integer patrolCircle,
			String nFCCode, Integer entCode, String entName, String pointPerson, String pointPersonTel,
			Double longtitude, Double lantitude) {
		super();
		this.pointCode = pointCode;
		this.pointName = pointName;
		this.pointAddr = pointAddr;
		this.safetyTips = safetyTips;
		this.patrolCircle = patrolCircle;
		NFCCode = nFCCode;
		this.entCode = entCode;
		this.entName = entName;
		this.pointPerson = pointPerson;
		this.pointPersonTel = pointPersonTel;
		this.longtitude = longtitude;
		this.lantitude = lantitude;
	}
	
	public PointView(Point point,Integer entCode,String entName) {
		super();
		this.pointCode = point.getPointCode();
		this.pointName = point.getPointName();
		this.pointAddr = point.getPointAddr();
		this.safetyTips = point.getSafetyTips();
		this.patrolCircle = point.getPatrolCircle();
		this.NFCCode = point.getNFCCode();
		this.entCode = entCode;
		this.entName = entName;
		this.entName = point.getEnterprise().getEntName();
		this.pointPerson = point.getPointPerson();
		this.pointPersonTel = point.getPointPersonTel();
		this.longtitude = point.getLongtitude();
		this.lantitude = point.getLantitude();
		this.deptCode = point.getEnterprise().getDepartment().getDeptCode();
		this.deptName = point.getEnterprise().getDepartment().getDeptName();
	}

	public PointView(Point point){
		super();
		this.pointCode = point.getPointCode();
		this.pointName = point.getPointName();
		this.pointAddr = point.getPointAddr();
		this.safetyTips = point.getSafetyTips();
		this.patrolCircle = point.getPatrolCircle();
		this.NFCCode = point.getNFCCode();
		this.pointPerson = point.getPointPerson();
		this.pointPersonTel = point.getPointPersonTel();
		this.longtitude = point.getLongtitude();
		this.lantitude = point.getLantitude();
		this.entCode = point.getEnterprise().getEntCode();
		this.entName = point.getEnterprise().getEntName();
		this.deptCode = point.getEnterprise().getDepartment().getDeptCode();
		this.deptName = point.getEnterprise().getDepartment().getDeptName();
	}

	/*
	 * setter getter
	 */
	public Integer getPointCode() {
		return pointCode;
	}
	public void setPointCode(Integer pointCode) {
		this.pointCode = pointCode;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
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
	public Integer getPatrolCircle() {
		return patrolCircle;
	}
	public void setPatrolCircle(Integer patrolCircle) {
		this.patrolCircle = patrolCircle;
	}
	public String getNFCCode() {
		return NFCCode;
	}
	public void setNFCCode(String nFCCode) {
		NFCCode = nFCCode;
	}
	public Integer getEntCode() {
		return entCode;
	}
	public void setEntCode(Integer entCode) {
		this.entCode = entCode;
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

	public Double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}

	public Double getLantitude() {
		return lantitude;
	}

	public void setLantitude(Double lantitude) {
		this.lantitude = lantitude;
	}



	public Integer getDeptCode() {
		return deptCode;
	}



	public void setDeptCode(Integer dptCode) {
		this.deptCode = dptCode;
	}



	public String getDeptName() {
		return deptName;
	}



	public void setDeptName(String dptName) {
		this.deptName = dptName;
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
