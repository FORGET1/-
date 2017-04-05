package com.pingdu.dao.licenseDao;

import static com.pingdu.manager.ThreadLocalManager.em;

import java.util.List;

import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.config.ResultType;
import org.springframework.stereotype.Repository;

import com.pingdu.entity.license.LicType_entType;
import com.pingdu.entity.license.LicenseType;

@Repository
public class LicType_entTypeDao {

	public Integer sum(int rows) {
		String jpql = "select lic_ent from LicType_entType lic_ent where 1=1";
		TypedQuery<LicType_entType> query = em().createQuery(jpql, LicType_entType.class);
				//query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);;
		int SUM = ((query.getResultList().size()) - 1) / rows + 1;
		return SUM;
	}
	
	
	public List<LicType_entType> getLicType_entTypeList(int page, int rows) {
		int head = (page - 1) * rows;
		String jpql = "select l from LicType_entType l where 1=1 ";
		TypedQuery<LicType_entType> query = em().createQuery(jpql, LicType_entType.class);
		//query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
		query.setFirstResult(head);
		query.setMaxResults(rows);
		List<LicType_entType> list = query.getResultList();
		return list;
	}
	
	public List<Integer> getLicenceTypeListByEntTypeCode(int entTypeCode){
		String jpql = "select l.licTypeCode from LicType_entType l where l.entTypeCode=:entTypeCode";
		TypedQuery<Integer> query = em().createQuery(jpql, Integer.class);
		//query.setHint(QueryHints.RESULT_TYPE, ResultType.Map);
		query.setParameter("entTypeCode", entTypeCode);
		return query.getResultList();

	}
}
