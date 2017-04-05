package com.pingdu.dao.noticeDao;

import static com.pingdu.manager.ThreadLocalManager.em;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
import org.springframework.stereotype.Repository;
import com.pingdu.entity.notice.Notice;
import com.pingdu.entity.notice.NoticeSRInfo;
import com.pingdu.entity.notice.NoticeRead;
import com.pingdu.entity.user.User;
import com.pingdu.utility.PublicVariable;
import com.pingdu.view.NoticeListapp;
import com.pingdu.view.NoticecheckRead;
import com.pingdu.view.getNoticeAllItemList;
import com.pingdu.view.notDepList;
import com.pingdu.view.notEntList;
import com.pingdu.view.noticeItemList;
import com.pingdu.view.noticeRecIsRead;

@Repository
public class NoticeDao {

	public String getReceivedNoticeSql(int userID) {
		// 查看接收的通知列表
		String jpql1 = "SELECT user FROM User user WHERE user.userCode='"
				+ userID + "'";
		TypedQuery<User> query1 = em().createQuery(jpql1, User.class);
		List<User> users = query1.getResultList();
		int roleCode = users.get(0).getRole().getRoleCode();
		int Code = 0;
		if (roleCode >= 4) {
			Code = users.get(0).getEnterprise().getEntCode();
		}
		if (roleCode < 4) {
			Code = users.get(0).getDept().getDeptCode();
		}

		String jpql = "SELECT nte.noticeCode,noticeinfo.senderName,noticeinfo.senderCode,noticeinfo.receiverCode,"
				+ " noticeinfo.receiverCode,noticeinfo.sendDate,nte.noticeTitle,noticeinfo.readPersonNum,"
				+ " noticeinfo.receiverPersonNum FROM NoticeSRInfo noticeinfo "
				+ " INNER JOIN Notice nte ON nte.noticeCode = noticeinfo.noticeCode "
				+ " WHERE noticeinfo.receiverCode='" + Code + "'";
		return jpql;
	}

	public String getSendNoticeSql(int userID) {
		// 查看发送的通知列表
		String jpql1 = "SELECT user FROM User user WHERE user.userCode='"
				+ userID + "'";
		TypedQuery<User> query1 = em().createQuery(jpql1, User.class);
		List<User> users = query1.getResultList();
		int roleCode = users.get(0).getRole().getRoleCode();
		int Code = 0;
		if (roleCode >= 4) {
			Code = users.get(0).getEnterprise().getEntCode();
		}
		if (roleCode < 4) {
			Code = users.get(0).getDept().getDeptCode();
		}

		String jpql = "SELECT nte.noticeCode,noticeinfo.senderName,noticeinfo.senderCode,noticeinfo.receiverCode,"
				+ " noticeinfo.receiverCode,noticeinfo.sendDate,nte.noticeTitle,noticeinfo.readPersonNum,"
				+ " noticeinfo.receiverPersonNum FROM NoticeSRInfo noticeinfo "
				+ " INNER JOIN Notice nte ON nte.noticeCode = noticeinfo.noticeCode "
				+ " WHERE noticeinfo.senderCode='" + Code + "'";
		return jpql;
	}

	/**
	 * 通知特定条件查询通知信息 1：发送方类型 2:接收方类型
	 * 
	 * @param userID
	 * @param searchType
	 * @param keyWord
	 * @return
	 */

	public String getReceivedSearchSql(int userID, String searchType,
			String keyWord) {
		// 查看接收的通知列表
		String jpql1 = "SELECT user FROM User user WHERE user.userCode='"
				+ userID + "'";
		TypedQuery<User> query1 = em().createQuery(jpql1, User.class);
		List<User> users = query1.getResultList();
		int roleCode = users.get(0).getRole().getRoleCode();
		int Code = 0;
		if (roleCode >= 4) {
			Code = users.get(0).getEnterprise().getEntCode();
		}
		if (roleCode < 4) {
			Code = users.get(0).getDept().getDeptCode();
		}

		String jpql = "SELECT nte.noticeCode,noticeinfo.senderName,noticeinfo.senderCode,noticeinfo.receiverCode,"
				+ " noticeinfo.receiverCode,noticeinfo.sendDate,nte.noticeTitle,noticeinfo.readPersonNum,"
				+ " noticeinfo.receiverPersonNum FROM NoticeSRInfo noticeinfo "
				+ " INNER JOIN Notice nte ON nte.noticeCode = noticeinfo.noticeCode ";
		if ("1".equals(searchType)) {
			jpql = jpql + " where noticeinfo.receiverCode='" + Code
					+ "'AND noticeinfo.senderType like '" + "%" + keyWord + "%"
					+ "'";

		} else if ("2".equals(searchType)) {
			jpql = jpql + " where noticeinfo.receiverCode='" + Code
					+ "'noticeinfo.receiverType like '" + "%" + keyWord + "%"
					+ "'";
		}
		return jpql;

	}

	/**
	 * 1：发送方类型 2:接收方类型
	 * 
	 * @param userID
	 * @param searchType
	 * @param keyWord
	 * @return
	 */
	public String getSendNoticeSearchSql(int userID, String searchType,
			String keyWord) {
		// 查看接收的通知列表
		String jpql1 = "SELECT user FROM User user WHERE user.userCode='"
				+ userID + "'";
		TypedQuery<User> query1 = em().createQuery(jpql1, User.class);
		List<User> users = query1.getResultList();
		int roleCode = users.get(0).getRole().getRoleCode();
		int Code = 0;
		if (roleCode >= 4) {
			Code = users.get(0).getEnterprise().getEntCode();
		}
		if (roleCode < 4) {
			Code = users.get(0).getDept().getDeptCode();
		}

		String jpql = "SELECT nte.noticeCode,noticeinfo.senderName,noticeinfo.senderCode,noticeinfo.receiverCode,"
				+ " noticeinfo.receiverCode,noticeinfo.sendDate,nte.noticeTitle,noticeinfo.readPersonNum,"
				+ " noticeinfo.receiverPersonNum FROM NoticeSRInfo noticeinfo "
				+ " INNER JOIN Notice nte ON nte.noticeCode = noticeinfo.noticeCode ";
		if ("1".equals(searchType)) {
			jpql = jpql + "  WHERE noticeinfo.senderCode='" + Code
					+ "' AND noticeinfo.senderType like  '" + "%" + keyWord
					+ "%" + "'";

		} else if ("2".equals(searchType)) {
			jpql = jpql + " WHERE noticeinfo.senderCode='" + Code
					+ "' AND noticeinfo.receiverType like  '" + "%" + keyWord
					+ "%" + "'";
		}
		return jpql;

	}

	public String getNoticeAllSql(int noticeCode) {
		// 获得具体通知
		String jpql = "SELECT nte.noticeTitle,nte.noticeInfo,nte.noticeTypeCode,nteinfo.receiverType,nteinfo.receiverCode "
				+ "	FROM Notice nte "
				+ "	INNER JOIN NoticeSRInfo nteinfo ON nteinfo.noticeCode = nte.noticeCode "
				+ "	WHERE nte.noticeCode = '" + noticeCode + "'";

		return jpql;

	}

	public String checkNoticeReadSql(String noticeTitle) {
		// 查看通知的具体情况
		String jpql = "SELECT red.isRead,info.receiverCode FROM Notice nte "
				+ " INNER JOIN NoticeSRInfo info ON info.noticeCode=nte.noticeCode "
				+ " INNER JOIN NoticeRead red ON red.noticeCode=nte.noticeCode "
				+ " WHERE nte.noticeTitle='" + noticeTitle + "'";
		return jpql;
	}

	public String getappUnReadNoticeSql(int userCode) {
		// app 查看未读通知
		String jpql = "SELECT t1.noticeTitle,t1.noticeCode,t2.sendDate FROM NoticeRead t"
				+ " LEFT JOIN Notice t1 ON t.noticeCode = t1.noticeCode"
				+ " LEFT JOIN NoticeSRInfo t2 on t.noticeCode= t2.noticeCode"
				+ " WHERE t.userCode = '" + userCode + "'and t.isRead= 0 ";
		return jpql;
	}

	public String getappNoticeSql(int userCode) {
		// app 查看未读通知
		String jpql = "SELECT t1.noticeTitle,t1.noticeCode,t2.sendDate FROM NoticeRead t"
				+ " LEFT JOIN Notice t1 ON t.noticeCode = t1.noticeCode"
				+ " LEFT JOIN NoticeSRInfo t2 on t.noticeCode= t2.noticeCode"
				+ " WHERE t.userCode = '" + userCode + "'";
		return jpql;
	}

	public String getappNoticeInFoSql(int userCode, int noticeCode) {
		// app 查看通知详情
		String jpql = "SELECT t1.noticeTitle,t1.noticeInfo,t2.sendDate FROM NoticeRead t"
				+ " LEFT JOIN Notice t1 ON t.noticeCode = t1.noticeCode"
				+ " LEFT JOIN NoticeSRInfo t2 on t.noticeCode= t2.noticeCode"
				+ " WHERE t.userCode = '"
				+ userCode
				+ "'and t.noticeCode='"
				+ noticeCode + "'";
		return jpql;
	}

	public void insertSendNotice(String recvType, int recvCode,
			String senderType, int userID, String noticeTitle,
			String noticeContent, int noticeTypeCode) {
		// 发送通知
		try {
			Notice notice = new Notice();
			notice.setNoticeTypeCode(noticeTypeCode);// 通知类型编号
			notice.setNoticeTitle(noticeTitle);// 标题
			notice.setNoticeInfo(noticeContent);// 内容
			em().persist(notice);

			String jpql = "SELECT notice FROM Notice notice WHERE 1=1 order by notice.noticeCode DESC";
			TypedQuery<Notice> query = em().createQuery(jpql, Notice.class);
			int noticeCode = query.getResultList().get(0).getNoticeCode();

			String jpql1 = "SELECT user FROM User user WHERE user.userCode='"
					+ userID + "'";
			TypedQuery<User> query1 = em().createQuery(jpql1, User.class);
			List<User> users = query1.getResultList();
			int roleCode = users.get(0).getRole().getRoleCode();
			int senderCode = 0;
			String userName = users.get(0).getName();
			if (roleCode >= 4) {
				senderCode = users.get(0).getEnterprise().getEntCode();
			}
			if (roleCode < 4) {
				senderCode = users.get(0).getDept().getDeptCode();
			}
			NoticeSRInfo noticesrinfo = new NoticeSRInfo();
			noticesrinfo.setNoticeCode(noticeCode);
			noticesrinfo.setSenderCode(senderCode);
			noticesrinfo.setSenderName(userName);
			noticesrinfo.setSenderType(senderType);
			// noticesrinfo.setSenderCode(userID);// 发送方编号
			noticesrinfo.setReceiverCode(recvCode);
			noticesrinfo.setReceiverType(recvType);

			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			noticesrinfo.setSendDate(sdf.format(d));// 发送日期
			em().persist(noticesrinfo);
		} catch (Exception e) {
			System.out.println("发送通知时异常" + e.getMessage());
		}

	}

	public void insertBackNotice(int noticeCode, String recvType, int recvCode,
			String senderType, int userID, int noticeRecipient) {
		// 转发通知
		try {
			String jpql1 = "SELECT user FROM User user WHERE user.userCode='"
					+ userID + "'";
			TypedQuery<User> query1 = em().createQuery(jpql1, User.class);
			List<User> users = query1.getResultList();
			int roleCode = users.get(0).getRole().getRoleCode();
			int senderCode = 0;
			String userName = users.get(0).getName();
			if (roleCode >= 4) {
				senderCode = users.get(0).getEnterprise().getEntCode();
			}
			if (roleCode < 4) {
				senderCode = users.get(0).getDept().getDeptCode();
			}
			NoticeSRInfo noticesrinfo = new NoticeSRInfo();
			noticesrinfo.setNoticeCode(noticeCode);// 通知编号
			noticesrinfo.setSenderName(userName);// 发送方姓名
			noticesrinfo.setSenderType(senderType);// 发送方类型
			noticesrinfo.setSenderCode(senderCode);// 发送方编号
			noticesrinfo.setReceiverType(recvType);// 接收方类型
			noticesrinfo.setReceiverCode(noticeRecipient);// 接收方编号
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			noticesrinfo.setSendDate(sdf.format(d));// 发送日期
			em().persist(noticesrinfo);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("转发通知时异常" + e.getMessage());
		}
	}

	public List<noticeItemList> getSendNoticeList(int userID, int page) {
		try {
			// 查看发送的通知列表
			String jpql = getSendNoticeSql(userID);
			TypedQuery<noticeItemList> query = em().createQuery(jpql,
					noticeItemList.class);
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			int head = (page - 1) * 15;
			query.setFirstResult(head);
			query.setMaxResults(15);
			List<noticeItemList> noticeSendList = query.getResultList();
			System.out.println(" 查看发送通知sql语句成功执行");
			return noticeSendList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("查看发送通知sql语句异常" + e.getMessage());
			return null;
		}

	}

	public List<noticeItemList> getReceivedNoticeList(int userID, int page) {
		try {
			// 查看接收的通知列表
			String jpql = getReceivedNoticeSql(userID);
			TypedQuery<noticeItemList> query = em().createQuery(jpql,
					noticeItemList.class);
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			int head = (page - 1) * 15;
			query.setFirstResult(head);
			query.setMaxResults(15);
			List<noticeItemList> noticeRecList = query.getResultList();
			System.out.println(" 查看接收通知sql语句成功执行");
			return noticeRecList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("查看接收通知sql语句异常" + e.getMessage());
			return null;
		}
	}

	public List<getNoticeAllItemList> getNoticeAllList(int noticeCode) {
		try {
			// 获得具体通知
			String jpql = getNoticeAllSql(noticeCode);
			TypedQuery<getNoticeAllItemList> query = em().createQuery(jpql,
					getNoticeAllItemList.class);
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			List<getNoticeAllItemList> noticeAllList = query.getResultList();
			System.out.println(" 获得具体通知sql语句成功执行");
			return noticeAllList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("获得具体通知sql语句异常" + e.getMessage());
			return null;
		}
	}

	public List<NoticecheckRead> checkNoticeReadList(String noticeTitle,
			int page) {
		try {
			// 查看阅读情况
			String jpql = checkNoticeReadSql(noticeTitle);
			TypedQuery<NoticecheckRead> query = em().createQuery(jpql,
					NoticecheckRead.class);
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			int head = (page - 1) * 15;
			query.setFirstResult(head);
			query.setMaxResults(15);
			List<NoticecheckRead> noticeIsReadList = query.getResultList();
			System.out.println("查看阅读情况sql语句成功执行");
			return noticeIsReadList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" 查看阅读情况sql语句异常" + e.getMessage());
			return null;
		}
	}

	public int NoticeReceivedPage(int userID) {
		// 接收通知列表页数
		try {
			String jpql = getReceivedNoticeSql(userID);
			TypedQuery<noticeItemList> query = em().createQuery(jpql,
					noticeItemList.class);
			int sum = (query.getResultList().size() - 1) / PublicVariable.rows
					+ 1;
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("计算最后一页异常" + e.getMessage());
			return 1;
		}

	}

	public int NoticeSendPage(int userID) {
		// 发送通知列表页数
		try {
			String jpql = getSendNoticeSql(userID);
			TypedQuery<noticeItemList> query = em().createQuery(jpql,
					noticeItemList.class);

			int sum = (query.getResultList().size() - 1) / PublicVariable.rows
					+ 1;
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("计算最后一页异常" + e.getMessage());
			return 1;
		}

	}

	public int NoticeCheckPage(String noticeTitle) {
		// 发送通知列表页数
		try {
			String jpql = checkNoticeReadSql(noticeTitle);
			TypedQuery<NoticecheckRead> query = em().createQuery(jpql,
					NoticecheckRead.class);

			int sum = (query.getResultList().size() - 1) / PublicVariable.rows
					+ 1;
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("计算最后一页异常" + e.getMessage());
			return 1;
		}

	}

	public int NoticeUnReadPage(int userCode) {
		// app 未读通知列表页数
		try {
			String jpql = getappUnReadNoticeSql(userCode);
			TypedQuery<NoticeListapp> query = em().createQuery(jpql,
					NoticeListapp.class);

			int sum = (query.getResultList().size() - 1) / PublicVariable.rows
					+ 1;
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("计算最后一页异常" + e.getMessage());
			return 1;
		}

	}

	public List<NoticeListapp> getUnReadNoticeList(int userCode, int page) {
		try {
			// app 查看未读的通知列表
			String jpql = getappUnReadNoticeSql(userCode);

			TypedQuery<NoticeListapp> query = em().createQuery(jpql,
					NoticeListapp.class);
			int head = (page - 1) * 15;
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			query.setFirstResult(head);
			query.setMaxResults(15);
			List<NoticeListapp> noticeUnReadList = query.getResultList();
			System.out.println(" 查看消息列表的sql语句成功执行");
			return noticeUnReadList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("查看消息列表的sql语句异常" + e.getMessage());
			return null;
		}

	}

	public int NoticePageapp(int userCode) {
		// app 未读通知列表页数
		try {
			String jpql = getappNoticeSql(userCode);
			TypedQuery<NoticeListapp> query = em().createQuery(jpql,
					NoticeListapp.class);

			int sum = (query.getResultList().size() - 1) / PublicVariable.rows
					+ 1;
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("计算最后一页异常" + e.getMessage());
			return 1;
		}

	}

	public List<NoticeListapp> getAppNoticeList(int userCode, int page) {
		try {
			// app 查看未读的通知列表
			String jpql = getappNoticeSql(userCode);

			TypedQuery<NoticeListapp> query = em().createQuery(jpql,
					NoticeListapp.class);
			int head = (page - 1) * 15;
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			query.setFirstResult(head);
			query.setMaxResults(15);
			List<NoticeListapp> noticeUnReadList = query.getResultList();
			System.out.println(" 查看消息列表的sql语句成功执行");
			return noticeUnReadList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("查看消息列表的sql语句异常" + e.getMessage());
			return null;
		}

	}

	public List<NoticeListapp> getAppNoticeInFoList(int userCode, int noticeCode) {
		try {
			// app 通知详情
			String jpql = getappNoticeInFoSql(userCode, noticeCode);

			TypedQuery<NoticeListapp> query = em().createQuery(jpql,
					NoticeListapp.class);
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			List<NoticeListapp> noticeUnReadList = query.getResultList();
			System.out.println(" 查看通知详情的sql语句成功执行");
			return noticeUnReadList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("查看通知详情的sql语句异常" + e.getMessage());
			return null;
		}

	}

	public List getSendNoticeSearchList(int userID, String searchType,
			String keyWord) {
		try {
			String jpql = getSendNoticeSearchSql(userID, searchType, keyWord);
			TypedQuery<noticeItemList> query = em().createQuery(jpql,
					noticeItemList.class);
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			List<noticeItemList> getSendNoticeSearList = query.getResultList();
			System.out.println(" 查看发送通知sql语句成功执行");
			return getSendNoticeSearList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}

	}

	public List getRecNoticeSearchList(int userID, String searchType,
			String keyWord) {
		try {
			String jpql = getReceivedSearchSql(userID, searchType, keyWord);
			TypedQuery<noticeItemList> query = em().createQuery(jpql,
					noticeItemList.class);
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			List<noticeItemList> getRecNoticeSearList = query.getResultList();
			System.out.println(" 查看发送通知sql语句成功执行");
			return getRecNoticeSearList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void deletenoticerinfoSendORec(int noticeCode) {

		String jpql = "delete from NoticeSRInfo dev where dev.noticeCode=:noticeCode ";
		TypedQuery<NoticeSRInfo> query = em().createQuery(jpql,
				NoticeSRInfo.class);
		query.setParameter("noticeCode", noticeCode);
		query.executeUpdate();

		String jpql1 = "delete from NoticeRead dev where dev.noticeCode=:noticeCode ";
		TypedQuery<NoticeRead> query1 = em()
				.createQuery(jpql, NoticeRead.class);
		query1.setParameter("noticeCode", noticeCode);
		query1.executeUpdate();

		String jpql2 = "delete from NoticeRead dev where dev.noticeCode=:noticeCode ";
		TypedQuery<Notice> query2 = em().createQuery(jpql, Notice.class);
		query2.setParameter("noticeCode", noticeCode);
		query2.executeUpdate();
	}

	public List selectItemZFTonice(int userID) {
		try {
			String jpql = "SELECT user FROM User user WHERE user.userCode='"
					+ userID + "'";
			TypedQuery<User> query = em().createQuery(jpql, User.class);
			int roleCode = query.getResultList().get(0).getRole().getRoleCode();// 1
			int deptCode = query.getResultList().get(0).getDept().getDeptCode();// 2
			int Code = 0;
			List LXList = null;
			System.out.println("1");
			if (roleCode < 4) {
				System.out.println("1");
				if (deptCode == 2) {// 安监局
					System.out.println("1");
					String jpql1 = "SELECT dep.deptCode,dep.deptName from Department dep WHERE dep.deptLevel=2 ";
					TypedQuery<notDepList> query1 = em().createQuery(jpql1,
							notDepList.class);
					query1.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
					System.out.println("安监局");
					LXList = query1.getResultList();
					System.out.println("1");

				} else if (deptCode == 3) {// 安监部
					String jpql1 = "SELECT ent.entCode,ent.entName from Enterprise ent where ent.entLevel=1 AND depCode='"
							+ deptCode + "";
					TypedQuery<notEntList> query1 = em().createQuery(jpql1,
							notEntList.class);
					query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
					System.out.println("安监部");
					LXList = query1.getResultList();

				} else {
					System.out.println("失败");
				}
			} else if (roleCode >= 4) {
				System.out.println("该用户类型是企业类型");
			}
			System.out.println("AAA");
			return LXList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;

		}

	}

	/**
	 * 接收人信息列表
	 */
	public List<noticeRecIsRead> RecUserAndIsRead(int noticeCode, int page) {
		try {
			String jpql = "select nte.isRead ,us.name from NoticeRead nte "
					+ "INNER JOIN User us ON us.userCode=nte.userCode "
					+ "where nte.noticeCode='" + noticeCode + "'";

			TypedQuery<noticeRecIsRead> query = em().createQuery(jpql,
					noticeRecIsRead.class);
			int head = (page - 1) * 15;
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			query.setFirstResult(head);
			query.setMaxResults(15);
			List<noticeRecIsRead> noticeIfReadList = query.getResultList();
			System.out.println(" 查看的sql语句成功执行");
			return noticeIfReadList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}

	}

	/*
	 * 接收文件的接收列表查询
	 */
	public int NoticeRecListSumPage(int noticeCode) {

		try {
			String jpql = "select nte.isRead ,us.name from NoticeRead nte "
					+ "INNER JOIN User us ON us.userCode=nte.userCode "
					+ "where nte.noticeCode='" + noticeCode + "'";

			TypedQuery<noticeRecIsRead> query = em().createQuery(jpql,
					noticeRecIsRead.class);

			int sum = (query.getResultList().size() - 1) / PublicVariable.rows
					+ 1;
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("计算最后一页异常" + e.getMessage());
			return 1;
		}

	}

	public int getPDNowUuser(int userID) {
		String jpql = "SELECT user FROM User user WHERE user.userCode='"
				+ userID + "'";
		TypedQuery<User> query = em().createQuery(jpql, User.class);//
		int deptCode = query.getResultList().get(0).getDept().getDeptCode();// 2
		return deptCode;
	}

	public int addNotice(Notice notice) {
		em().persist(notice);
		String jpql = "SELECT n FROM Notice n WHERE 1=1 order by n.noticeCode DESC";
		
		TypedQuery<Notice> query = em().createQuery(jpql, Notice.class);
		
		return query.getResultList().get(0).getNoticeCode();
	}

	public void addNoticeSRInfo(NoticeSRInfo noticeSRInfo) {
		em().persist(noticeSRInfo);
	}

}