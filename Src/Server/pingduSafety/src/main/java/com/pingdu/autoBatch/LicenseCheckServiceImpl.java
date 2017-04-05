package com.pingdu.autoBatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pingdu.dao.deptDao.DeptDao;
import com.pingdu.dao.entDao.EntDao;
import com.pingdu.dao.licenseDao.LicenseDao;
import com.pingdu.dao.licenseDao.LicenseTypeDao;
import com.pingdu.dao.noticeDao.NoticeDao;
import com.pingdu.dao.userDao.UserDao;
import com.pingdu.entity.department.Department;
import com.pingdu.entity.license.License;
import com.pingdu.entity.license.LicenseType;
import com.pingdu.entity.notice.Notice;
import com.pingdu.entity.notice.NoticeSRInfo;
import com.pingdu.view.EnterpriseView;
import com.pingdu.view.UserView;


@Service
public class LicenseCheckServiceImpl implements LicenseCheckService {

	final int INTERVALDAYS1 = 90;
	final int INTERVALDAYS2 = 60;
	final int INTERVALDAYS3 = 30;
	final int INTERVALDAYS4 = -1;
	final int INTERVALDAYS5 = -4;
	final int INTERVALDAYS6 = -7;
	final String NOTICETITLE1 = "【通知】entName企业licName证件到期通知";
	final String NOTICETITLE2 = "【通知】entName企业licName证件到期通知";

	final String NOTICEINFO1 = "entName企业，您好！贵公司的licName证件快要到期，请及时更新。谢谢！【deptName】";
	final String NOTICEINFO2 = "entName企业，您好！贵公司的licName证件快要到期，请及时更新。谢谢！【deptName】";
	final int NOTICETYPECODE1 = 2;
	final int NOTICETYPECODE2 = 3;
	final String DB_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	final boolean LICENSE_EXPIRED=true;
	final String DEPT_TYPE="0";
	final String ENT_TYPE="1";
	final String STSTEM_SENDER="SYSTEM";

	@Autowired
	private LicenseDao licenseDao;

	@Autowired
	private LicenseTypeDao licenseTypeDao;

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
	public int checkLicense() {
		int result = 0;
		try {
			// TODO Auto-generated method stub
			// 获取证照一览信息
			List<License> licenseList = licenseDao.getAllLicenseList();
			int size = licenseList.size(); 

			for (int i = 0; i < size; i++) {
				//从征兆征召一览获取一个征召信息
				License license = licenseList.get(i);
				
				//获取证照编号
				int licTypeCode = license.getLicTypeCode();
				
				//获取证照类型				
				LicenseType licenseType = licenseTypeDao.findById(licTypeCode);
				
				//获取证照类型名称
				String licName = licenseType.getLicName();
				
				//获取证照有效时间
				String expireDateStr = license.getExpireDate();
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
					noticeTypeCode = NOTICETYPECODE1;
					license.setIsVerify(LICENSE_EXPIRED);
//					licenseDao.updateLicense(license);
					
				} else if (dayDiff == INTERVALDAYS4 || dayDiff == INTERVALDAYS6 || dayDiff == INTERVALDAYS6) {
					noticeTitle = NOTICETITLE2;
					noticeInfo = NOTICEINFO2;
					noticeTypeCode = NOTICETYPECODE2;

				} else {
					continue;
				}
				
				//获取企业编号
				int entCode = license.getEntCode();// get entCode

				//获取企业名称
				EnterpriseView enterPrise = entDao.getEnterpriseByCode(entCode);
				String entName = enterPrise.getEntName();// get entName

				//获取企业上级部门编号			
				int entLevel = enterPrise.getEntLevel();// get entLevel
				int parentCode = enterPrise.getParentCode();// get parentCode
				if (entLevel == 2) {
					EnterpriseView enterprise = entDao.getEnterpriseByCode(parentCode);
					parentCode = enterprise.getParentCode();// get parentCode
				}
				int deptCode = parentCode;

				//获取企业上级部门名称
				Department department = deptDao.getDepartmentInfo(deptCode);
				String deptName = department.getDeptName();
				
				//计算接受通知人数
				List<UserView> users = userDao.getEntUserByEntCode(entCode);
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
				int noticeCode =noticeDao.addNotice(notice);
				
				//设定通知收发信息
				NoticeSRInfo noticeSRInfo = new NoticeSRInfo();
				noticeSRInfo.setSenderType("部门");
				noticeSRInfo.setSenderName(STSTEM_SENDER);
				noticeSRInfo.setSenderCode(deptCode);
				String dateStr =sdf.format(new Date());
				noticeSRInfo.setSendDate(dateStr);
				noticeSRInfo.setReceiverType("企业");
				noticeSRInfo.setReceiverCode(entCode);
				noticeSRInfo.setReceiverPersonNum(receiverPersonNum);
				noticeSRInfo.setReadPersonNum(0);
				noticeSRInfo.setNoticeCode(noticeCode);
				//插入通知收发信息
				noticeDao.addNoticeSRInfo(noticeSRInfo);
				
				//插入通知阅读信息表！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}
}
