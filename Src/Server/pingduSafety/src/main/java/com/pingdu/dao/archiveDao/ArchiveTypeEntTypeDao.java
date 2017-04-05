package com.pingdu.dao.archiveDao;

import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import static com.pingdu.manager.ThreadLocalManager.em;

@Repository
public class ArchiveTypeEntTypeDao {

	
	public List<Integer> getArchTypeCodeByEntTypeCode(int entTypeCode){
		
		String jpql = "select a.archTypeCode from ArchTypeEntType a where a.entTypeCode=:entTypeCode ";
		TypedQuery<Integer> query = em().createQuery(jpql,Integer.class);
		query.setParameter("entTypeCode", entTypeCode);
		return query.getResultList();
		
	}
}
