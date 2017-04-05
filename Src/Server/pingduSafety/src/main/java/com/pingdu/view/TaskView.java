package com.pingdu.view;

import com.pingdu.baseModel.BaseEntity;
import com.pingdu.entity.task.Task;


public class TaskView extends BaseEntity{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7477289367744639599L;
	//task
	private Integer taskCode;
	private String issueTime;
	private String uploadTime;
	private String completeTime;
	private short exception;
	private String note;
	private Integer entCode;
	private String expireDate;
	private Integer pointCode;
	private short isComplete;
	private String probDesc;
	
	//enterprise
	private String entName;
	private Integer parentCode;
	private Integer entLevel;
	//point
	private  String  pointName;
	private String pointAddr;
	private Integer patrolCircle;
	
	//userInfo
	private  String  name;

	private String deptName;
	private Integer deptCode;
	
	
	
	public Integer getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(Integer deptCode) {
		this.deptCode = deptCode;
	}
	public Integer getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(Integer taskCode) {
		this.taskCode = taskCode;
	}
	
	public short getIsComplete() {
		return isComplete;
	}
	public void setIsComplete(short isComplete) {
		this.isComplete = isComplete;
	}
	public String getEntName() {
		return entName;
	}
	
	public Integer getParentCode() {
		return parentCode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public String getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
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
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	


	public Integer getEntCode() {
		return entCode;
	}
	public void setEntCode(Integer entCode) {
		this.entCode = entCode;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public Integer getPointCode() {
		return pointCode;
	}
	public void setPointCode(Integer pointCode) {
		this.pointCode = pointCode;
	}
	public String getProbDesc() {
		return probDesc;
	}
	public void setProbDesc(String probDesc) {
		this.probDesc = probDesc;
	}
	
	public Integer getPatrolCircle() {
		return patrolCircle;
	}
	public void setPatrolCircle(Integer patrolCircle) {
		this.patrolCircle = patrolCircle;
	}
	
	
	public short getException() {
		return exception;
	}
	public void setException(short exception) {
		this.exception = exception;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public TaskView(Task task){
		this.taskCode=task.getTaskCode();
		this.entName=task.getEnterprise().getEntName();
		this.entCode=taskCode=task.getEnterprise().getEntCode();
		this.entLevel=task.getEnterprise().getEntLevel();
		this.parentCode=task.getEnterprise().getParentCode();
		this.note=task.getNote();
		this.isComplete=task.getIsComplete();
		this.deptCode=task.getEnterprise().getDepartment().getDeptCode();
	}
	
	/*
	 * getList
	 */
	public TaskView(Integer taskCode,String entName, String deptName,String pointName ,String completeTime,short exception,String probDis) {
		super();
		this.taskCode = taskCode;
		this.entName = entName;
		this.deptName = deptName;
		this.pointName = pointName;
		this.completeTime = completeTime;
		this.exception = exception;
		this.probDesc = probDis;

	}
	/*
	 * getUnfinished
	 */
	
	public TaskView(Integer taskCode, String issueTime,String uploadTime, String entName, String pointName, String pointAddr) {
		super();
		this.taskCode = taskCode;
		this.issueTime = issueTime;
		this.entName = entName;
		this.pointName = pointName;
		this.uploadTime = uploadTime;
		this.pointAddr = pointAddr;


	}

	
	public TaskView() {
		super();
		
		this.taskCode = 0;
		this.issueTime = "/";
		this.entName = "/";
		this.pointName = "/";
		this.uploadTime = "/";
		this.pointAddr = "/";
		this.completeTime = "/";
		this.name = "/";
		
	}	
	/*
	 * getFinished
	 */
	
	public TaskView(Integer taskCode, String issueTime, String uploadTime, String completeTime, String entName,
			String pointName, String pointAddr, String name) {
		super();
		this.taskCode = taskCode;
		this.issueTime = issueTime;
		this.uploadTime = uploadTime;
		this.completeTime = completeTime;
		this.entName = entName;
		this.pointName = pointName;
		this.pointAddr = pointAddr;
		this.name = name;
		
	}
	/*
	 * appgetUnfinished
	 */
	
	public TaskView(Integer taskCode, String issueTime, String pointName, Integer entCode, String expireDate,
			Integer pointCode, String probDesc, String pointAddr, Integer patrolCircle) {
		super();
		this.taskCode = taskCode;
		this.issueTime = issueTime;
		this.pointName = pointName;
		this.entCode = entCode;
		this.expireDate = expireDate;
		this.pointCode = pointCode;
		this.probDesc = probDesc;
		this.pointAddr = pointAddr;
		this.patrolCircle = patrolCircle;
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
