package com.pingdu.dao.entDao;
import static com.pingdu.manager.ThreadLocalManager.em;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.utility.PublicVariable;
import com.pingdu.view.EnterpriseView;

@Repository
public class EntDao {
	
	
	public String getEntListHJpql(int parentCode){
		
		String jpql = "select new %1$s(e) from Enterprise e where e.entLevel=:level ";
		switch (parentCode) {
		case 0:
			
			break;
			
		default:
			jpql +="and e.parentCode=:parentCode";
			break;
		}
		return jpql;
		
	}
	
	public List<EnterpriseView> getEnterpriseListByParent(int page,int parentCode){
		
		String jpql = getEntListHJpql(parentCode);
		jpql = String.format(jpql, EnterpriseView.class.getName());
		TypedQuery<EnterpriseView> query = em().createQuery(jpql,EnterpriseView.class);
		switch (parentCode) {
		case 0:
			query.setParameter("level", 1);
			break;

		default:
			query.setParameter("level", 2);
			query.setParameter("parentCode", parentCode);
			break;
		}
		
		int head = (page-1)*PublicVariable.rows;
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.rows);
		List<EnterpriseView> enterprises = query.getResultList();
		
		return enterprises;
	}
 
	public List<EnterpriseView> getEnterpriseByDept(int page,int deptCode){
		String jpql = "select new %1$s(e) from Enterprise e where e.department.deptCode=:deptCode and e.entLevel =:level ";
		jpql = String.format(jpql, EnterpriseView.class.getName());
		TypedQuery<EnterpriseView> jQuery = em().createQuery(jpql,EnterpriseView.class);
		jQuery.setParameter("level", 1);
		jQuery.setParameter("deptCode", deptCode);
		
		int head = (page-1)*PublicVariable.rows;
		jQuery.setFirstResult(head);
		jQuery.setMaxResults(PublicVariable.rows);
		List<EnterpriseView> enterprises = jQuery.getResultList();
		
		return enterprises;
		
	}

	public int getSumOfPage(int parentCode) {
		
		String jpql = getEntListHJpql(parentCode);
		jpql = String.format(jpql, EnterpriseView.class.getName());
		TypedQuery<EnterpriseView> query = em().createQuery(jpql,EnterpriseView.class);
		switch (parentCode) {
		case 0:
			query.setParameter("level", 1);
			break;

		default:
			query.setParameter("level", 2);
			query.setParameter("parentCode", parentCode);
			break;
		}
		
		
		return  (query.getResultList().size() - 1 )/ PublicVariable.getRows()+1;
	}

	public int getSumOfPageByDept(int deptCode) {
		String jpql = "select e from Enterprise e where e.department.deptCode=:deptCode and e.entLevel =:level ";
		
		TypedQuery<Enterprise> jQuery = em().createQuery(jpql,Enterprise.class);
		jQuery.setParameter("level", 1);
		jQuery.setParameter("deptCode", deptCode);
		
		return (jQuery.getResultList().size() - 1 )/ PublicVariable.getRows()+1;
	}
	
	public Enterprise findByCode(int entCode) {
		return em().find(Enterprise.class, entCode);
	}
	
	
	public boolean deleteEnterprisement (int entCode) {
		Enterprise e = findByCode(entCode);
		if (e != null) {
			em().getTransaction().begin();
			em().remove(e);
			em().getTransaction().commit();
			em().close();
			return true;
		}
		return false;
	}
	
	public int addEnterprisement(Enterprise enterprise) {
		
		try {
			em().persist(enterprise);
			String jpql="select MAX(e.entCode) from Enterprise e where 1=1";
			jpql = String.format(jpql, EnterpriseView.class);
			TypedQuery<Integer> query = em().createQuery(jpql,Integer.class);
			List<Integer> entCodes = query.getResultList();
			
			return entCodes.size()>0?(entCodes.get(0)):0;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public EnterpriseView getEnterpriseByCode(int entCode){
		
		String jpql="select new %1$s(e) from Enterprise e where e.entCode=:entCode";
		jpql = String.format(jpql, EnterpriseView.class);
		
		
		TypedQuery<EnterpriseView> jQuery = em().createQuery(jpql,EnterpriseView.class);
		jQuery.setParameter("entCode", entCode);
		List<EnterpriseView> enterprises = jQuery.getResultList();
		
		return enterprises.size()>0?enterprises.get(0):new EnterpriseView();
		
	}
	
	
	public List<EnterpriseView>  ajbSearchEnterpriseByKey(String searchType,String keyword,int entLevel,int deptCode,int parentCode){
		
		
		String jpql="select new %1$s(e) from Enterprise e where e.entLevel=:entLevel ";
		
		switch (searchType) {
		case "企业名称":
			jpql +="and e.entName like :keyword ";
			break;
		case "联系人":
			jpql +="and e.principle like :keyword ";
			break;
		default:
			break;
		}
		jpql+="and e.department.deptCode=:deptCode ";
		if(entLevel == 2){
			jpql+="and e.parentCode=:parentCode ";
		}
		jpql = String.format(jpql, EnterpriseView.class);
		
		
		TypedQuery<EnterpriseView> jQuery = em().createQuery(jpql,EnterpriseView.class);
		jQuery.setParameter("deptCode", deptCode);
		jQuery.setParameter("entLevel", entLevel);
		jQuery.setParameter("keyword", keyword);
		if(entLevel == 2){
			jQuery.setParameter("parentCode", parentCode);
		}
		List<EnterpriseView> enterprises = jQuery.getResultList();
		
		return  enterprises;
		
		
	}
	
	public List<EnterpriseView>  ajjSearchEnterpriseByKey(String searchType,String keyword,int entLevel,int parentCode){
		
		
		String jpql="select new %1$s(e) from Enterprise e where e.entLevel=:entLevel ";
		
		switch (searchType) {
		case "企业名称":
			jpql +="and e.entName like :keyword ";
			break;
		case "联系人":
			jpql +="and e.principle like :keyword ";
			break;
		default:
			break;
		}
		if(entLevel == 2){
			jpql+="and e.parentCode=:parentCode ";
		}
		jpql = String.format(jpql, EnterpriseView.class);
		
		
		TypedQuery<EnterpriseView> jQuery = em().createQuery(jpql,EnterpriseView.class);
		jQuery.setParameter("entLevel", entLevel);
		jQuery.setParameter("keyword", keyword);
		if(entLevel == 2){
			jQuery.setParameter("parentCode", parentCode);
		}
		List<EnterpriseView> enterprises = jQuery.getResultList();
		
		return  enterprises;
		
		
	}

	public boolean modifyEnterprise(Enterprise enterprise) {
		
		try {
			em().merge(enterprise);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Integer> getEntCodesByDeptNoPage(Integer deptId) {
		String jpql = "select e.entCode from Enterprise e where e.department.deptCode=:deptCode order by e.entCode";
		
		TypedQuery<Integer> jQuery = em().createQuery(jpql,Integer.class);
		
		jQuery.setParameter("deptCode", deptId);

		List<Integer> entCodes = jQuery.getResultList();
		
		return entCodes;
		
	}

	public List<EnterpriseView> getEnterpriseByDepNoPage(int deptCode) {
		String jpql = "select new %1$s(e) from Enterprise e where e.department.deptCode=:deptCode order by e.entCode";
		jpql = String.format(jpql, EnterpriseView.class.getName());
		TypedQuery<EnterpriseView> jQuery = em().createQuery(jpql,EnterpriseView.class);
		
		jQuery.setParameter("deptCode", deptCode);
		List<EnterpriseView> ents = jQuery.getResultList();
		
		return ents;
	}

	
}
