package com.pingdu.autoBatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pingdu.dao.safePromiseDao.SafePromiseDao;
import com.pingdu.dao.deptDao.DeptDao;
import com.pingdu.dao.entDao.EntDao;
import com.pingdu.dao.noticeDao.NoticeDao;
import com.pingdu.dao.userDao.UserDao;
import com.pingdu.entity.department.Department;
import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.entity.notice.Notice;
import com.pingdu.entity.notice.NoticeSRInfo;
import com.pingdu.entity.safePromise.SafePromise;
import com.pingdu.utility.PublicVariable;
import com.pingdu.view.UserView;

@Service
public class SafetyPromiseCheckServiceImpl implements SafetyPromiseCheckService {

	final String NOTICETITLE = "【通知】entName企业安全承诺未上传通知";
	final String NOTICEINFO = "entName企业，您好！贵公司的安全承诺未上传，请及时更新。谢谢！【deptName】";
	final int NOTICE_TYPE_CODE= 3;
	final String DB_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	final int SafePromise_NOT_VERIFY=1;
	final String DEPT_TYPE="0";//部门
	final String ENT_TYPE="1";//企业
	final String STSTEM_SENDER="SYSTEM";

	@Autowired
	private SafePromiseDao safePromiseDao;

	@Autowired
	private DeptDao deptDao;

	@Autowired
	private EntDao entDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private NoticeDao noticeDao;

	@Autowired
//	private NoticeSRInfoDao noticeSRInfoDao;

	@Override
	@Transactional
	public int checkSafePromise() {
		int result = 0;
		try {
			// 获取安全承诺一览信息
			List<SafePromise> safePromiseList = safePromiseDao.getCurrentSafePromiseList();

			for (SafePromise safePromise:safePromiseList) {
				//从征兆征召一览获取一个征召信息
				short isUpload = safePromise.getIsUpload();
				
				//选择设定通知类型.标题.内容
				String noticeTitle;
				String noticeInfo;
				int noticeTypeCode=NOTICE_TYPE_CODE;
				if (isUpload == 0) {
					noticeTitle = NOTICETITLE;
				} else {
					continue;
				}
				
				//获取企业编号
				int entCode = safePromise.getEntCode();// get entCode

				//获取企业名称
				Enterprise enterPrise = entDao.findByCode(entCode);
				String entName = enterPrise.getEntName();// get entName

				//获取企业上级部门编号
				int entLevel = enterPrise.getEntLevel();// get entLevel
				int parentCode = enterPrise.getParentCode();// get parentCode
				if (entLevel == 2) {
					Enterprise enterprise = entDao.findByCode(parentCode);
					parentCode = enterprise.getParentCode();// get parentCode
				}
				int deptCode = parentCode;

				//获取企业上级部门名称
				Department department = deptDao.departmentInfo(deptCode);
				String deptName = department.getDeptName();
				
				//计算接受通知人数
				List<UserView> users = userDao.getEntUserByEntCode(entCode);
				int receiverPersonNum = users.size();

				//设定通知功能广告公告管理信息内容
				Notice notice = new Notice();
				notice.setNoticeTypeCode(noticeTypeCode);
				noticeTitle=noticeTitle.replaceAll("entName", entName);
				notice.setNoticeTitle(noticeTitle);			
				noticeInfo=NOTICEINFO.replaceAll("entName", entName);
				noticeInfo=NOTICEINFO.replaceAll("deptName", deptName);
				notice.setNoticeInfo(noticeInfo);	
				notice.setNote("");
				//插入通知管理信息
				int noticeCode = noticeDao.addNotice(notice);
				
				//设定通知收发信息
				NoticeSRInfo noticeSRInfo = new NoticeSRInfo();
				noticeSRInfo.setSenderType(DEPT_TYPE);
				noticeSRInfo.setSenderName(STSTEM_SENDER);
				noticeSRInfo.setSenderCode(deptCode);
				String dateStr =PublicVariable.sdf.format(new Date());
				noticeSRInfo.setSendDate(dateStr);
				noticeSRInfo.setReceiverType(ENT_TYPE);
				noticeSRInfo.setReceiverCode(entCode);
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
