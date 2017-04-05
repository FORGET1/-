package com.pingdu.entity.task;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pingdu.baseModel.BaseEntity;
import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.entity.item.Item;
import com.pingdu.entity.point.Point;
import com.pingdu.entity.user.User;

/**
 * @description 模型-- 巡检任务
 */
@Entity
@Table(name = "task")
public class Task extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1081892690399787078L;

	/**
	 * 巡检任务编号
	 */
	@Id
	@Column(name = "taskCode", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer taskCode;
	
	@ManyToOne
    @JoinColumn(name= "entCode", nullable= false)
    private Enterprise enterprise;
	
	@ManyToMany(mappedBy = "tasks")
	private Set<Item> items = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "userCode")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "pointCode", nullable = false)
	private Point point;
	
	/**
	 * 下达时间
	 */
	@Column(name = "issueTime", nullable = false, length = 50)
	private String issueTime;
	/**
	 * 是否完成
	 */
	@Column(name = "isComplete", nullable = false)
	private short isComplete;
	/**
	 * 完成时间
	 */
	@Column(name = "completeTime", length = 50)
	private String completeTime;
	
	/**
	 * 上传时间
	 */
	@Column(name = "uploadTime", length = 50)
	private String uploadTime;
	
	/**
	 * 审核时间
	 */
	@Column(name = "verifyTime", length = 50)
	private String verifyTime;
	/**
	 * 是否审核
	 */
	@Column(name = "isVerify", nullable = false)
	private short isVerify;
	/**
	 * 有效日期
	 */
	@Column(name = "expireDate",nullable = false,length = 50)
	private String expireDate;
	/**
	 * 是否异常
	 */
	@Column(name = "exception", nullable = false)
	private short exception;
	/**
	 *图片路径
	 */
	@Column(name = "imagePath", length = 200)
	private String imagePath;
	/**
	 *备注
	 */
	@Column(name = "note", length = 100)
	private String note;
	/**
	 *问题描述
	 */
	@Column(name = "probDesc", length = 200)
	private String probDesc;
	/**
	 *意见描述
	 */
	@Column(name = "advDesc", length = 200)
	private String advDesc;
	/* *********************************************getter  ang  setter*********************************************/
	public Integer getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(Integer taskCode) {
		this.taskCode = taskCode;
	}
//	public Integer getPointCode() {
//		return pointCode;
//	}
//	public void setPointCode(Integer pointCode) {
//		this.pointCode = pointCode;
//	}

	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
//	public String getUserCode() {
//		return userCode;
//	}
//	public void setUserCode(String userCode) {
//		this.userCode = userCode;
//	}
	public String getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(String issueTime) {
		this.issueTime = issueTime;
	}
	public short getIsComplete() {
		return isComplete;
	}
	public void setIsComplete(short isComplete) {
		this.isComplete = isComplete;
	}
	public String getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}
	public short getIsVerify() {
		return isVerify;
	}
	public void setIsVerify(short isVerify) {
		this.isVerify = isVerify;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public short getException() {
		return exception;
	}
	public void setException(short exception) {
		this.exception = exception;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getProbDesc() {
		return probDesc;
	}
	public void setProbDesc(String probDesc) {
		this.probDesc = probDesc;
	}
	public String getAdvDesc() {
		return advDesc;
	}
	public void setAdvDesc(String advDesc) {
		this.advDesc = advDesc;
	}
	public Set<Item> getItems() {
		return items;
	}
	public void setItems(Set<Item> items) {
		this.items = items;
	}
	public User getUserInfo() {
		return user;
	}
	public void setUserInfo(User user) {
		this.user = user;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
//	public Set<Task_item> getTask_item() {
//		return task_item;
//	}
//	public void setTask_item(Set<Task_item> task_item) {
//		this.task_item = task_item;
//	}
//	
	public Task() {}
	
	public Task(Enterprise enterprise, Set<Item> items, Point point, String issueTime,
			short isComplete, String completeTime, String uploadTime, String verifyTime, short isVerify,
			String expireDate, short exception, String imagePath, String note, String probDesc, String advDesc) {
		super();
		this.enterprise = enterprise;
		this.items = items;
		this.user = null;
		this.point = point;
		this.issueTime = issueTime;
		this.isComplete = isComplete;
		this.completeTime = completeTime;
		this.uploadTime = uploadTime;
		this.verifyTime = verifyTime;
		this.isVerify = isVerify;
		this.expireDate = expireDate;
		this.exception = exception;
		this.imagePath = imagePath;
		this.note = note;
		this.probDesc = probDesc;
		this.advDesc = advDesc;
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
