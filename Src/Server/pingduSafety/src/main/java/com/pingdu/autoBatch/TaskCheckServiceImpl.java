package com.pingdu.autoBatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pingdu.dao.taskDao.TaskDao;
import com.pingdu.dao.deptDao.DeptDao;
import com.pingdu.dao.entDao.EntDao;
import com.pingdu.dao.noticeDao.NoticeDao;
import com.pingdu.dao.userDao.UserDao;
import com.pingdu.entity.task.Task;
import com.pingdu.entity.department.Department;
import com.pingdu.entity.notice.Notice;
import com.pingdu.entity.notice.NoticeSRInfo;
import com.pingdu.view.TaskView;
import com.pingdu.view.UserView;

@Service
public class TaskCheckServiceImpl implements TaskCheckService {

	final String NOTICETITLE = "【通知】entName企业pointName项点未巡通知";
	final String NOTICEINFO = "entName企业， 您好！贵公司的pointName项点没有在规定的时间内进行巡检，请及时处理。谢谢！【deptName安监办】";
	final int NOTICE_TYPE_CODE = 3;
	final String DB_DATE_FORMAT = "yyyy-MM-dd";
	final String DEPT_TYPE="0";//部门
	final String ENT_TYPE="1";//企业
	final String STSTEM_SENDER="SYSTEM";

	@Autowired
	private TaskDao taskDao;

	@Autowired
	private DeptDao deptDao;

	@Autowired
	private EntDao entDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private NoticeDao noticeDao;

	@Override
	@Transactional
	public int checkTask() {
		int result = 0;
		try {
			// 获取巡检任务一览信息
			List<TaskView> taskList= taskDao.getArroundMonthTaskList();
			int size = taskList.size(); 

			for (TaskView task:taskList) {
				//从巡检任务一览获取一个巡检任务信息
				//获取企业编号
				int entCode = task.getEntCode();

				//获取企业名称
				String entName = task.getEntName();
						
				//获取企业级别		
				int entLevel = task.getEntLevel();
						
				//获取上级编号		
				int parentCode = task.getParentCode();
				
				//获取管辖部门编号
				int deptCode = task.getDeptCode();
				
				//获取项点名称			
				String pointName = task.getPointName();
				
				//获取档案有效时间
				String expireDateStr = task.getExpireDate();
				SimpleDateFormat sdf = new SimpleDateFormat(DB_DATE_FORMAT);
				Date expireDate = (Date) sdf.parse(expireDateStr);

				//获取系统时间
				Date nowDate = new Date();

				//计算过期期限(小时)
				int dayDiff = (int) ((expireDate.getTime() - nowDate.getTime()) / 3600000);

				//获取企业所属部门名称
				Department department = deptDao.departmentInfo(deptCode);
				String deptName = department.getDeptName();

				//获取备注信息
				String note=task.getNote();
				
				
				String senderName=STSTEM_SENDER;
				String senderType=DEPT_TYPE;
				int senderCode=deptCode;
				
				String receiverType;
				int receiverCode;
				

				if(task.getIsComplete() == 1){
					continue;
				}
				
				//选择接收部门
				if ((note.equals("") || note.equals("0")) && dayDiff < 24) {
					//获取企业编号.上级部门编号
					receiverType=ENT_TYPE;
					receiverCode = entCode;
					Task toTask = taskDao.findBycode(task.getTaskCode());
					toTask.setNote("1");
					taskDao.modifyTask(toTask);
				} else if (note.equals("1")) {
					//获取企业编号.上级部门编号				
					if(entLevel==2){
						receiverType=ENT_TYPE;
						receiverCode =parentCode;
					}else {
						receiverType=DEPT_TYPE;
						receiverCode =deptCode;
					}
					Task toTask = taskDao.findBycode(task.getTaskCode());
					toTask.setNote("2");
					taskDao.modifyTask(toTask);
				} else if (note.equals("2")) {
					//获取企业编号.上级部门编号				
					receiverType=DEPT_TYPE;
					//获取企业编号.上级部门编号				
					if(entLevel==2){
						receiverCode =deptCode;
					}else {
						int parentDeptCode=deptDao.getDepartmentInfo(deptCode).getParentCode();
						receiverCode =parentDeptCode;
					}
					
					Task toTask = taskDao.findBycode(task.getTaskCode());
					toTask.setNote("3");
					taskDao.modifyTask(toTask);
				} else if (note.equals("3")) {
					//获取企业编号.上级部门编号
					receiverType=DEPT_TYPE;
					if(entLevel==2){
						int parentDeptCode=deptDao.getDepartmentInfo(deptCode).getParentCode();
						receiverCode =parentDeptCode;
						Task toTask = taskDao.findBycode(task.getTaskCode());
						toTask.setNote("4");
						taskDao.modifyTask(toTask);
					}else {
						continue;
					}
				} else {
					continue;
				}

				//计算接受通知人数
				List<UserView> users;
				switch (receiverType) {
				case DEPT_TYPE:
					users = userDao.getDeptUserByDeptCode(receiverCode);
					break;

				default:
					users = userDao.getEntUserByEntCode(receiverCode);
					break;
				}
				
				int receiverPersonNum = users.size();

				//设定通知类型.标题.内容
				String noticeTitle = NOTICETITLE;
				String noticeInfo = NOTICEINFO;
				int noticeTypeCode = NOTICE_TYPE_CODE;
				
				//设定通知功能广告公告管理信息内容
				Notice notice = new Notice();
				notice.setNoticeTypeCode(noticeTypeCode);
				noticeTitle=noticeTitle.replaceAll("entName", entName);
				noticeTitle=noticeTitle.replaceAll("pointName", pointName);
				notice.setNoticeTitle(noticeTitle);		
				
				noticeInfo=noticeInfo.replaceAll("entName", entName);
				noticeInfo=noticeInfo.replaceAll("pointName", pointName);
				noticeInfo=noticeInfo.replaceAll("deptName", deptName);
				notice.setNoticeInfo(noticeInfo);
				
				notice.setNote("");
				//插入通知管理信息
				int noticeCode=noticeDao.addNotice(notice);
				//设定通知收发信息
				NoticeSRInfo noticeSRInfo = new NoticeSRInfo();
				noticeSRInfo.setSenderName(senderName);
				noticeSRInfo.setSenderType(senderType);
				noticeSRInfo.setSenderCode(senderCode);
				String dateStr =sdf.format(new Date());
				noticeSRInfo.setSendDate(dateStr);
				noticeSRInfo.setReceiverType(receiverType);
				noticeSRInfo.setReceiverCode(receiverCode);
				noticeSRInfo.setReceiverPersonNum(receiverPersonNum);
				noticeSRInfo.setReadPersonNum(0);
				noticeSRInfo.setNoticeCode(noticeCode);
				//插入通知收发信息
				noticeDao.addNoticeSRInfo(noticeSRInfo);
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}
}
