package com.pingdu.dao.archiveDao;

import static com.pingdu.manager.ThreadLocalManager.em;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
import org.springframework.stereotype.Repository;

import com.pingdu.entity.archive.ArchType;
import com.pingdu.entity.archive.Archive;
import com.pingdu.entity.user.User;
import com.pingdu.utility.PublicVariable;
import com.pingdu.view.ArchiveImgListView;
import com.pingdu.view.ArchiveItemList;
import com.pingdu.view.archiveApp;

@Repository
public class ArchiveDao {

	public String getArchiveSQL() {
		// 查看档案列表
		String jpql = "SELECT archive.archTypeCode,archive.archName,"
				+ " archive.validTerm,arc.archiveCode,enter.entCode,"
				+ " enter.entName,arc.expireDate,arc.uploadDate,arc.isExpire "
				+ " FROM ArchType archive  "
				+ "  INNER JOIN Archive arc ON arc.archTypeCode = archive.archTypeCode "
				+ " INNER JOIN Enterprise enter ON enter.entCode = arc.entCode ";

		return jpql;

	}

	public String getArchiveSpecSQL(String searchType, int keyword) {
		// 根据特定条件查询
		String jpql = "SELECT archive.archTypeCode,archive.archName,"
				+ " archive.validTerm,arc.archiveCode,enter.entCode,"
				+ " enter.entName,arc.expireDate,arc.uploadDate,arc.isExpire "
				+ " FROM ArchType archive  "
				+ " INNER JOIN ArchTypeEntType acv ON acv.archTypeCode=archive.archTypeCode "
				+ "  INNER JOIN Archive arc ON arc.archTypeCode = archive.archTypeCode "
				+ " INNER JOIN Enterprise enter ON enter.entCode = arc.entCode ";
		if ("1".equals(searchType)) {
			// 根据企业编号查询
			jpql = jpql + " where arc.entCode like '" + "%" + keyword + "%"
					+ "'";

		} else if ("2".equals(searchType)) {
			jpql = jpql + " where enter.entCode like '" + "%" + keyword + "%"
					+ "'";
		}
		return jpql;
	}

	public String getArchivesImgSQL(int archiveCode, int companyCode) {
		// 查看档案图片
		String jpql = " select arc.imagePath from Archive arc "
				+ " INNER JOIN Archivetype arch ON arch.archTypeCode=arc.archTypeCode "
				+ " INNER JOIN Enterprise enter ON enter.entCode=arc.entCode "
				+ " where arc.archiveCode='" + archiveCode
				+ "' AND enter.entCode='" + companyCode + "'";
		return jpql;
	}

	/**
	 * 查看檔案列表根据特定的条件
	 * 
	 * @param page
	 * @return
	 */
	public List<ArchiveItemList> getArchiveSpecList(String searchType,
			int keyword, int page) {
		try {
			String jpql = getArchiveSpecSQL(searchType, keyword);
			TypedQuery<ArchiveItemList> query = em().createQuery(jpql,
					ArchiveItemList.class);
			// .unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			int head = (page - 1) * 15;
			query.setFirstResult(head);
			query.setMaxResults(15);
			List<ArchiveItemList> archiveList = query.getResultList();
			System.out.println(" 查看特定条件档案列表sql语句成功执行");
			return archiveList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" 查看特定条件档案列表sql语句异常" + e.getMessage());
			return null;
		}
	}

	/**
	 * 查看档案列表
	 * 
	 * @param page
	 * @return
	 */
	public List<ArchiveItemList> getArchiveList(int page) {
		try {
			String jpql = getArchiveSQL();
			TypedQuery<ArchiveItemList> query = em().createQuery(jpql,
					ArchiveItemList.class);
			// .unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			int head = (page - 1) * 15;
			query.setFirstResult(head);
			query.setMaxResults(15);
			List<ArchiveItemList> archiveList = query.getResultList();
			System.out.println(" 查看档案列表sql语句成功执行");
			return archiveList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" 查看档案列表sql语句异常" + e.getMessage());
			return null;
		}
	}

	/**
	 * 查看档案图片，多图片
	 * 
	 * @param archiveCode
	 * @param companyCode
	 * @return
	 */
	public List<ArchiveImgListView> getArchivesImgList(int archiveCode,
			int companyCode) {
		try {
			String jpql = getArchivesImgSQL(archiveCode, companyCode);
			TypedQuery<ArchiveImgListView> query = em().createQuery(jpql,
					ArchiveImgListView.class);
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			List<ArchiveImgListView> archiveImgList = query.getResultList();
			System.out.println(" 查看档案图片sql语句成功执行");
			return archiveImgList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" 查看档案图片sql语句成功异常" + e.getMessage());
			return null;
		}
	}

	public void delete(int archiveCode, int companyCode) {
		String jpql = "select dev from Archive dev where dev.archiveCode=:archiveCode AND dev.entCode=:companyCode ";
		TypedQuery<Archive> query = em().createQuery(jpql, Archive.class);
		query.setParameter("archiveCode", archiveCode);
		query.setParameter("companyCode", companyCode);
		List<Archive> list = query.getResultList();
		for (int i = 0; i < list.size(); i++) {
			em().remove(list.get(i));
		}
	}

	/**
	 * 计算总页数
	 * 
	 * @param entCode
	 * @param page
	 * @param status
	 * @return
	 */
	public int calPage() {
		try {
			String jpql = getArchiveSQL();
			TypedQuery query = em().createQuery(jpql, ArchiveItemList.class);
			// query.setParameter("entCode", entCode);
			// Query query=em().createQuery(jpql);

			int sum = (query.getResultList().size() - 1) / PublicVariable.rows
					+ 1;
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("计算总页数异常" + e.getMessage());
			return 1;
		}

	}

	/**
	 * 根据跟定查询条件时使用
	 * 
	 * @return
	 */
	public int calPageSearch(String searchType, int keyword) {
		try {
			String jpql = getArchiveSpecSQL(searchType, keyword);
			TypedQuery query = em().createQuery(jpql, ArchiveItemList.class);
			// query.setParameter("entCode", entCode);
			// Query query=em().createQuery(jpql);

			int sum = (query.getResultList().size() - 1) / PublicVariable.rows
					+ 1;
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("计算最后一页异常" + e.getMessage());
			return 1;
		}

	}

	/**************************** APP接口定义 **********************************************************/
	/**
	 * 获得某个时间之后days天的时间
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getDateAfter(Date date, int days) {
		return getDateAgo(date, -days);
	}

	/**
	 * 获取某个时间之前days的时间
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getDateAgo(Date date, int days) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.DATE, -days);
		return c.getTime();
	}

	/**
	 * 查看d1是否在d2之后
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean afterDate(Date d1, Date d2) {
		d1 = org.apache.commons.lang.time.DateUtils.truncate(d1, Calendar.DATE);
		d2 = org.apache.commons.lang.time.DateUtils.truncate(d2, Calendar.DATE);
		return d1.after(d2);
	}

	/**
	 * 截断日期
	 * 
	 * @param d1
	 * @param i
	 *            java.util.Calendar.DATE
	 * @return
	 */
	public static Date truncate(Date d1, int i) {
		return org.apache.commons.lang.time.DateUtils.truncate(d1, i);
	}

	/**
	 * 获得两个时间的间隔天数
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getIntervalDays(Date begin, Date end) {

		if (begin == null || end == null)
			return 0;
		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
		return (int) between / (24 * 3600);
	}

	/**
	 * 获得当前时间
	 * 
	 * @return
	 */
	public static Date getNowTime() {
		try {
			Date NowDate = new Date();// 当前日期
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			String date = df.format(new Date());// new Date()为获取当前系统时间
			NowDate = df.parse(date);
			System.out.println(NowDate);
			System.out.println(NowDate);

			return NowDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**************************************************************************************************/
	/**
	 * 获取过期档案
	 * 
	 * @param UserCode
	 * @param page
	 * @return
	 * @throws ParseException
	 */
	public List GetAlreadyExpiredArchiveSQL(int UserCode, int page)
			throws ParseException {
		System.out.println(1);
		String jpql = "SELECT user FROM User user WHERE user.userCode='"
				+ UserCode + "'";
		TypedQuery<User> query = em().createQuery(jpql, User.class);
		int entCode = query.getResultList().get(0).getEnterprise().getEntCode();
		// String jpql1 = "SELECT arc FROM Archive arc WHERE arc.entCode='"
		// + entCode + "'";
		// TypedQuery<Archive> query1 = em().createQuery(jpql1, Archive.class);
		//
		// String expireDate = query1.getResultList().get(0).getExpireDate();
		// /*-------------------------Stirng-->Date-----------------------------*/
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// Date exprieTime = new Date();// 有效期
		// exprieTime = sdf.parse(expireDate);// 日期格式的有效日期
		//
		// /*-----------------------------------------------------------------*/
		String jpql2 = "SELECT arc.archiveCode,arct.archName FROM Archive arc "
				+ " INNER JOIN ArchType arct ON arct.archTypeCode=arc.archTypeCode"
				+ " WHERE arc.entCode='" + entCode + "' AND arc.isExpire=1 ";
		TypedQuery<archiveApp> query2 = em().createQuery(jpql2,
				archiveApp.class);
		int head = (page - 1) * 15;
		query2.setFirstResult(head);
		query2.setMaxResults(15);
		query2.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
		List<archiveApp> arcAlreadyList = query2.getResultList();

		return arcAlreadyList;

	}

	/**
	 * 
	 * 获取过期档案天数
	 * 
	 * @param UserCode
	 * @param NowDate
	 * @return
	 */
	public Map<String, Integer> BetweenDays(int UserCode, Date NowDate) {
		try {
			String jpql = "SELECT user FROM User user WHERE user.userCode='"
					+ UserCode + "'";
			TypedQuery<User> query = em().createQuery(jpql, User.class);

			int entCode = query.getResultList().get(0).getEnterprise().getEntCode();
			String jpql1 = "SELECT arc FROM Archive arc WHERE arc.entCode='"
					+ entCode + "' AND arc.isExpire=1 ";
			TypedQuery<Archive> query1 = em().createQuery(jpql1, Archive.class);
			List a = query1.getResultList();
			int dayC = 0;
			Map<String, Integer> map = new HashMap<String, Integer>();
			for (int i = 0; i < a.size(); i++) {
				String expireDate = query1.getResultList().get(i)
						.getExpireDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date exprieTime = new Date();// 有效期
				try {
					exprieTime = sdf.parse(expireDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// 日期格式的有效日期
				/*-----------------------------------------------------------------*/
				dayC = getIntervalDays(getNowTime(), exprieTime);// 日期间隔
				map.put("'" + i + "'", dayC);
			}
			return map;
			/*-------------------------Stirng-->Date-----------------------------*/

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("YAB" + e.getMessage());
			return null;
		}

	}

	/**
	 * 获取过期档案最后一页
	 * 
	 * @param UserCode
	 * @return
	 */
	public int calPageGetAlreadyExp(int UserCode) {
		try {
			String jpql1 = "SELECT user FROM User user WHERE user.userCode='"
					+ UserCode + "'";
			TypedQuery<User> query1 = em().createQuery(jpql1, User.class);
			int entCode = query1.getResultList().get(0).getEnterprise().getEntCode();

			String jpql2 = "SELECT arc.archiveCode,arct.archName FROM Archive arc "
					+ " INNER JOIN ArchType arct ON arct.archTypeCode=arc.archTypeCode"
					+ " WHERE arc.entCode='" + entCode + "'";
			TypedQuery<archiveApp> query2 = em().createQuery(jpql2,
					archiveApp.class);
			int sum = (query2.getResultList().size() - 1) / PublicVariable.rows
					+ 1;
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("计算最后一页异常" + e.getMessage());
			return 1;
		}

	}

	/**
	 * 获取全部档案
	 * 
	 * @param UserCode
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List GetAllExpiredArchiveSQL(int UserCode, int page)
			throws Exception {
		String jpql = "SELECT user FROM User user WHERE user.userCode='"
				+ UserCode + "'";
		TypedQuery<User> query = em().createQuery(jpql, User.class);
		int entCode = query.getResultList().get(0).getEnterprise().getEntCode();
		String jpql2 = "SELECT arc.archiveCode,arct.archName,arc.expireDate FROM Archive arc "
				+ " INNER JOIN ArchTtype arct ON arct.archTypeCode=arc.archTypeCode"
				+ " WHERE arc.entCode='" + entCode + "'";
		TypedQuery<archiveApp> query2 = em().createQuery(jpql2,
				archiveApp.class);
		int head = (page - 1) * 15;
		query2.setFirstResult(head);
		query2.setMaxResults(15);
		query2.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
		List arcAllList = query2.getResultList();
		return arcAllList;

	}

	/**
	 * 获取全部档案--最后一页
	 * 
	 * @param UserCode
	 * @return
	 */
	public int calPageGetAllExp(int UserCode) {
		try {
			String jpql1 = "SELECT user FROM User user WHERE user.userCode='"
					+ UserCode + "'";
			TypedQuery<User> query1 = em().createQuery(jpql1, User.class);
			int entCode = query1.getResultList().get(0).getEnterprise().getEntCode();

			String jpql2 = "SELECT arc.archiveCode,arct.archName,arc.expireDate FROM Archive arc "
					+ " INNER JOIN ArchType arct ON arct.archTypeCode=arc.archTypeCode"
					+ " WHERE arc.entCode='" + entCode + "'";
			TypedQuery<archiveApp> query2 = em().createQuery(jpql2,
					archiveApp.class);
			int sum = (query2.getResultList().size() - 1) / PublicVariable.rows
					+ 1;
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("计算最后一页异常" + e.getMessage());
			return 1;
		}

	}

	/*--------------------------------------------------------------------------------------------*/
	/**
	 * 获取将要过期档案
	 * 
	 * @param UserCode
	 * @param page
	 * @return
	 * @throws ParseException
	 */
	public List GetWillExpiredArchiveSQL(int UserCode, int page)
			throws ParseException {
		String jpql = "SELECT user FROM User user WHERE user.userCode='"
				+ UserCode + "'";
		TypedQuery<User> query = em().createQuery(jpql, User.class);
		int entCode = query.getResultList().get(0).getEnterprise().getEntCode();
		String jpql1 = "SELECT arc FROM Archive arc WHERE arc.entCode='"
				+ entCode + "'";
		TypedQuery<Archive> query1 = em().createQuery(jpql1, Archive.class);

		List a = query1.getResultList();// 循环遍历出所有
		List<archiveApp> arcWillList = null;// 声明将要过期的变量

		String expireDate = query1.getResultList().get(0).getExpireDate();
		/*-------------------------Stirng-->Date-----------------------------*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date exprieTime = new Date();// 有效期
		exprieTime = sdf.parse(expireDate);// 日期格式的有效日期
		SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd");
		String getNowTitmeStr = s1.format(getNowTime());// 当前String类型的时间
		String getafter60Time = s1.format(getDateAfter(getNowTime(), 60));// 获得String类型的60天以后的时间
		/*-----------------------------------------------------------------*/
		System.out.println("档案还有60天过期");
		String jpql2 = "SELECT arc.archiveCode,arct.archName FROM Archive arc "
				+ " INNER JOIN ArchType arct ON arct.archTypeCode=arc.archTypeCode"
				+ " WHERE arc.entCode='" + entCode
				+ "' AND arc.expireDate BETWEEN '" + getNowTitmeStr + "' AND '"
				+ getafter60Time + "'";
		TypedQuery<archiveApp> query2 = em().createQuery(jpql2,
				archiveApp.class);
		int head = (page - 1) * 15;
		query2.setFirstResult(head);
		query2.setMaxResults(15);
		query2.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
		System.out.println(jpql2);
		arcWillList = query2.getResultList();

		return arcWillList;

	}

	/**
	 * 
	 * 获取将要过期档案天数
	 * 
	 * @param UserCode
	 * @param NowDate
	 * @return
	 */
	public Map<String, Integer> WillBetweenDays(int UserCode, Date NowDate) {
		try {
			String jpql = "SELECT user FROM User user WHERE user.userCode='"
					+ UserCode + "'";
			TypedQuery<User> query = em().createQuery(jpql, User.class);
			int entCode = query.getResultList().get(0).getEnterprise().getEntCode();
			String jpql1 = "SELECT arc FROM Archive arc WHERE arc.entCode='"
					+ entCode + "'";
			TypedQuery<Archive> query1 = em().createQuery(jpql1, Archive.class);
			List a = query1.getResultList();
			Map<String, Integer> map = new HashMap<String, Integer>();
			for (int i = 0; i < a.size(); i++) {
				String expireDate = query1.getResultList().get(i)
						.getExpireDate();
				/*-------------------------Stirng-->Date-----------------------------*/
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date exprieTime = new Date();// 有效期
				try {
					exprieTime = sdf.parse(expireDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// 日期格式的有效日期

				/*-----------------------------------------------------------------*/
				int dayC = getIntervalDays(getNowTime(), exprieTime);// 日期间隔
				map.put("'" + i + "'", dayC);
			}

			return map;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("YAB" + e.getMessage());
			return null;
		}

	}

	/**
	 * 获取将要过期档案最后一页
	 * 
	 * @param UserCode
	 * @return
	 */
	public int calPageGetWillExp(int UserCode) {
		try {
			String jpql1 = "SELECT user FROM User user WHERE user.userCode='"
					+ UserCode + "'";
			TypedQuery<User> query1 = em().createQuery(jpql1, User.class);
			int entCode = query1.getResultList().get(0).getEnterprise().getEntCode();

			String jpql2 = "SELECT arc.archiveCode,arct.archName FROM Archive arc "
					+ " INNER JOIN ArchType arct ON arct.archTypeCode=arc.archTypeCode"
					+ " WHERE arc.entCode='" + entCode + "'";
			TypedQuery<archiveApp> query2 = em().createQuery(jpql2,
					archiveApp.class);
			int sum = (query2.getResultList().size() - 1) / PublicVariable.rows
					+ 1;
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("计算最后一页异常" + e.getMessage());
			return 1;
		}

	}
	
	public Archive getArchiveInfo(int parseInt) {
		// TODO Auto-generated method stub
		Archive archive=em().find(Archive.class, parseInt);
		return archive;
	}
	
	public void modifyArchive(Archive archive) {
		try {
			em().merge(archive);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("aaa"+e.getMessage());
		}
		
		
	}

	public void addArchive(Archive archive) {
		try {
			em().persist(archive);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("aaa"+e.getMessage());
		}
		
	}

	public List<Archive> getAllArchive() {
		
		String jpql="select a from Archive a where 1=1";
		TypedQuery<Archive> query = em().createQuery(jpql,Archive.class);
		
		return query.getResultList();
	}

	public ArchType getArchiveTypeById(int archTypeCode) {
		
		return em().find(ArchType.class, archTypeCode);
	}

	

}
