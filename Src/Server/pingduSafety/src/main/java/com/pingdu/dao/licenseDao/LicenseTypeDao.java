package com.pingdu.dao.licenseDao;

import static com.pingdu.manager.ThreadLocalManager.em;

import java.util.List;

import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
import org.springframework.stereotype.Repository;

import com.pingdu.entity.entType.EntType;
import com.pingdu.entity.license.LicType_entType;
import com.pingdu.entity.license.LicenseType;
import com.pingdu.entity.license.LicenseTypeReturn;
import com.pingdu.entity.notice.Notice;
import com.pingdu.utility.PublicVariable;

@Repository
public class LicenseTypeDao {
	public LicenseType findById(int licTypeCode) {
		LicenseType lt = em().find(LicenseType.class, licTypeCode);
		return lt;
	}

	public LicenseType findByName(String licName) {
		String jpql = "select t from LicenseType t where t.licName =:licName ";
		TypedQuery<LicenseType> query = em().createQuery(jpql,
				LicenseType.class);
//		query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
		query.setParameter("licName", licName);
		List<LicenseType> list = query.getResultList();
		return list.get(0);
	}

	public String LicenseTypeSQL(int page) {
		// 查看档案列表
		String jpql = "SELECT lictype.licName,lictype.validTerm,lic.entCode,ent.entTypeName  FROM LicenseType lictype "
				+ " INNER JOIN License lic ON lic.licTypeCode=lictype.licTypeCode "
				+ " NNER JOIN LicType_entType licent ON licent.licTypeCode=licent.licTypeCode "
				+ " INNER JOIN EntType ent ON ent.entTypeCode=licent.entTypeCode";
		return jpql;

	}

	public String LicenseTypeSpecSQL(String searchType, String keyword) {
		// 根据特定条件查询
		String jpql = "SELECT lictype.licName,lictype.validTerm,lic.entCode,ent.entTypeName  FROM LicenseType lictype "
				+ " INNER JOIN License lic ON lic.licTypeCode=lictype.licTypeCode "
				+ " INNER JOIN LicType_entType licent ON licent.licTypeCode=licent.licTypeCode "
				+ " INNER JOIN EntType ent ON ent.entTypeCode=licent.entTypeCode";
		if ("1".equals(searchType)) {
			// 根据企业编号查询
			jpql = jpql + " where lic.entCode like '" + "%"+ keyword + "%"
					+ "'";

		} else if ("2".equals(searchType)) {
			//证照类型编号
			jpql = jpql + " where lic.licTypeCode like '" + "%" + keyword + "%"
					+ "'";
		}
		return jpql;
	}

	public List<LicenseTypeReturn> getLicenseTypeList(int page) {
		try {
			String jpql = LicenseTypeSQL(page);
			TypedQuery<LicenseTypeReturn> query = em().createQuery(jpql,
					LicenseTypeReturn.class);
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			int head = (page - 1) * 15;
			query.setFirstResult(head);
			query.setMaxResults(15);
			List<LicenseTypeReturn> LicenseTypeList = query.getResultList();
			System.out.println(" 证照类型管理sql语句成功执行");
			return LicenseTypeList;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("证照类型管理sql语句异常" + e.getMessage());
			return null;
		}
	}

	public List<LicenseTypeReturn> LicenseTypeSpecList(String searchType,
			String keyword) {
		try {
			// 根据条件查询文件列表
			String jpql = LicenseTypeSpecSQL(searchType, keyword);
			TypedQuery<LicenseTypeReturn> query = em().createQuery(jpql,
					LicenseTypeReturn.class);
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			List<LicenseTypeReturn> LicenseTypeSpecList = query.getResultList();
			System.out.println(" 证照类型管理sql语句成功执行");
			return LicenseTypeSpecList;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" 证照类型管理sql语句异常" + e.getMessage());
			return null;
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
	public int calPage(int page) {
		try {
			String jpql = LicenseTypeSQL(page);
			TypedQuery query = em().createQuery(jpql, LicenseTypeReturn.class);
			query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
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
	 * 获得最后一页
	 * 
	 * @param searchType
	 * @param keyword
	 * @return
	 */
	public int calPageSearch(String searchType, String keyword) {
		try {
			String jpql = LicenseTypeSpecSQL(searchType, keyword);
			TypedQuery<LicenseTypeReturn> query = em().createQuery(jpql, LicenseTypeReturn.class);
			//query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
			int sum = (query.getResultList().size() - 1) / PublicVariable.rows
					+ 1;
			return sum;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("计算最后一页异常" + e.getMessage());
			return 1;
		}

	}

//	/**
//	 * 添加证照类型
//	 * 
//	 * @param licName
//	 * @param validTerm
//	 * @param entTypeName
//	 */
//	public boolean insertaddLicense(String licName, int validTerm,
//			String entTypeName) {
//		try {
//
////			LicenseType licenseType = new LicenseType();
////			/****************************************/
////			licenseType.setLicName(licName);
////			licenseType.setValidTerm(validTerm);
////			em().persist(licenseType);
////			String jpql = "SELECT t FROM LicenseType t WHERE 1=1 order by t.licTypeCode DESC";
////			TypedQuery<LicenseType> query = em().createQuery(jpql,
////					LicenseType.class);
////			int licTypeCode = query.getResultList().get(0).getLicTypeCode();// 获得证照类型编号
////			System.out.println(licTypeCode);
////			System.out.println(licTypeCode);
////			System.out.println(licTypeCode);
////			System.out.println(licTypeCode);
////			System.out.println(licTypeCode);
////			
////			/****************************************/
////			EntType enterprisetype = new EntType();
////			enterprisetype.setEntTypeName(entTypeName);
////			enterprisetype.setNeedSafety((short) 0);
////			em().persist(enterprisetype);
////
////			String jpql1 = "SELECT t FROM EntType t WHERE 1=1 order by t.entTypeCode DESC";
////			TypedQuery<EntType> query1 = em().createQuery(jpql1, EntType.class);
////			int entTypeCode = query1.getResultList().get(0).getEntTypeCode();// 获得企业类型编号
////			System.out.println();
////			System.out.println(entTypeCode);
////			System.out.println(entTypeCode);
////			System.out.println(entTypeCode);
////			System.out.println(entTypeCode);
////			System.out.println(entTypeCode);
////			System.out.println(entTypeCode);
//			/******************************************/
//			LicType_entType entType = new LicType_entType();
//			entType.setEntTypeCode(12);// LicType_entType
//			entType.setLicTypeCode(12);
//			em().persist(enterprisetype);
//			
//			return true;
//		} catch (Exception e) {
//			System.out.println("AAS"+e.getMessage());
//			System.out.println("AAS"+e.getMessage());
//			System.out.println("AAS"+e.getMessage());
//			return false;
//			
//		}
//	}

//	/**
//	 * 删除证照类型
//	 * 
//	 * @param entTypeName
//	 * @param licTyoeCode
//	 */
//	public boolean deleteLicenseType(String entTypeName, int licTyoeCode) {
//		try {
//			EntType enterprisetype = new EntType();// 删除删除企业证照类型关系表时使用
//			String jpql = "select dev from EntType dev where  dev.entTypeName=:entTypeName ";
//			TypedQuery<EntType> query = em().createQuery(jpql, EntType.class);
//			query.setParameter("entTypeName", entTypeName);
//			List<EntType> list = query.getResultList();
//			int entTypeCode = enterprisetype.getEntTypeCode();// 删除企业证照类型管理表获取判读条件
//			for (int i = 0; i < list.size(); i++) {
//				em().remove(list.get(i));// 删除企业类型管理表
//			}
//
//			String jpql1 = "select dev from LicenseType dev where dev.licTyoeCode=:licTyoeCode ";
//			TypedQuery<LicenseType> query1 = em().createQuery(jpql,
//					LicenseType.class);
//			query.setParameter("licTyoeCode", licTyoeCode);
//			List<LicenseType> list1 = query1.getResultList();
//			for (int i = 0; i < list1.size(); i++) {
//				em().remove(list1.get(i));// 删除证照类型管理表
//			}
//
//			String jpql2 = "select dev from LicType_entType dev where dev.licTyoeCode=:licTyoeCode "
//					+ " AND dev.entTypeCode=:entTypeCode";
//			TypedQuery<LicType_entType> query2 = em().createQuery(jpql,
//					LicType_entType.class);
//
//			query.setParameter("licTyoeCode", licTyoeCode);
//			query.setParameter("entTypeCode", entTypeCode);
//			List<LicenseType> list2 = query1.getResultList();
//			for (int i = 0; i < list1.size(); i++) {
//				em().remove(list1.get(i));// 删除企业证照类型管理表
//			}
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}

}
