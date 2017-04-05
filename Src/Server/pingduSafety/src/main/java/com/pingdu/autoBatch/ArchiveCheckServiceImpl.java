package com.pingdu.autoBatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pingdu.dao.archiveDao.ArchiveDao;
import com.pingdu.dao.deptDao.DeptDao;
import com.pingdu.dao.entDao.EntDao;
import com.pingdu.dao.noticeDao.NoticeDao;
import com.pingdu.dao.userDao.UserDao;
import com.pingdu.entity.archive.ArchType;
import com.pingdu.entity.archive.Archive;
import com.pingdu.entity.department.Department;
import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.entity.notice.Notice;
import com.pingdu.entity.notice.NoticeSRInfo;
import com.pingdu.view.UserView;

@Service
public class ArchiveCheckServiceImpl implements ArchiveCheckService {

	final int INTERVALDAYS1 = 90;
	final int INTERVALDAYS2 = 60;
	final int INTERVALDAYS3 = 30;
	final int INTERVALDAYS4 = -1;
	final int INTERVALDAYS5 = -4;
	final int INTERVALDAYS6 = -7;
	final String NOTICETITLE1 = "【通知】entName企业licName档案到期通知";
	final String NOTICETITLE2 = "【通知】entName企业licName档案到期通知";

	final String NOTICEINFO1 = "entName企业，您好！贵公司的licName档案快要到期，请及时更新。谢谢！【deptName】";
	final String NOTICEINFO2 = "entName企业，您好！贵公司的licName档案快要到期，请及时更新。谢谢！【deptName】";
	final int NOTICE_TYPE_CODE1 = 1;
	final int NOTICE_TYPE_CODE2 = 2;
	final String DB_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	final boolean ARCHIVE_VERIFY=false;
	final String SENDER_TYPE="0";
	final String RECEIVER_TYPE="1";
	final String STSTEM_SENDER="SYSTEM";

	@Autowired
	private ArchiveDao archiveDao;

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
	public int checkArchive() {
		int result = 0;
		try {
			// 获取文档一览信息
			List<Archive> archiveList = archiveDao.getAllArchive();
			int size = archiveList.size(); 

			for (int i = 0; i < size; i++) {
				//从档案一览获取一个征召信息
				Archive archive = archiveList.get(i);
				
				//获取档案编号
				int archTypeCode = archive.getArchTypeCode();
				
				//获取档案类型				
				ArchType archType = archiveDao.getArchiveTypeById(archTypeCode);
				
				//获取档案类型名称
				String licName = archType.getArchName();
				
				//获取档案有效时间
				String expireDateStr = archive.getExpireDate();
				SimpleDateFormat sdf = new SimpleDateFormat(DB_DATE_FORMAT);
				Date expireDate = (Date) sdf.parse(expireDateStr);
				
				//获取系统时间
				Date nowDate = new Date();
				
				//计算过期期限
				int dayDiff = (int) ((expireDate.getTime() - nowDate.getTime()) / 86400000);
				
				//选择设定通知类型.标题.内容
				String noticeTitle;
				String noticeInfo;
				int noticeTypeCode;
				if (dayDiff == INTERVALDAYS1 || dayDiff == INTERVALDAYS2 || dayDiff == INTERVALDAYS3) {
					noticeTitle = NOTICETITLE1;
					noticeInfo = NOTICEINFO1;
					noticeTypeCode = NOTICE_TYPE_CODE1;
					
					archive.setVerify(ARCHIVE_VERIFY);
					archiveDao.modifyArchive(archive);
					
				} else if (dayDiff == INTERVALDAYS4 || dayDiff == INTERVALDAYS6 || dayDiff == INTERVALDAYS6) {
					noticeTitle = NOTICETITLE2;
					noticeInfo = NOTICEINFO2;
					noticeTypeCode = NOTICE_TYPE_CODE2;

				} else {
					continue;
				}
				
				//获取企业编号
				int entCode = archive.getEntCode();// get entCode

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
				List<UserView> users = userDao.getEntUserListByDeptCode(entCode);
				int receiverPersonNum = users.size();

				//设定通知功能广告公告管理信息内容
				Notice notice = new Notice();
				notice.setNoticeTypeCode(noticeTypeCode);
				
				noticeTitle=noticeTitle.replaceAll("entName", entName);
				noticeTitle=noticeTitle.replaceAll("licName", licName);
				notice.setNoticeTitle(noticeTitle);			
				noticeInfo=noticeInfo.replaceAll("entName", entName);
				noticeInfo=noticeInfo.replaceAll("licName", licName);
				noticeInfo=noticeInfo.replaceAll("deptName", deptName);
				notice.setNoticeInfo(noticeInfo);	
				notice.setNote("");
				//插入通知管理信息
				int noticeCode = noticeDao.addNotice(notice);
				
				//设定通知收发信息
				NoticeSRInfo noticeSRInfo = new NoticeSRInfo();
				noticeSRInfo.setSenderType(SENDER_TYPE);
				noticeSRInfo.setSenderName(STSTEM_SENDER);
				noticeSRInfo.setSenderCode(deptCode);
				String dateStr =sdf.format(new Date());
				noticeSRInfo.setSendDate(dateStr);
				noticeSRInfo.setReceiverType(RECEIVER_TYPE);
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
