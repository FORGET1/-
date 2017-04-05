package com.pingdu.dao.rescueDao;

import org.springframework.stereotype.Repository;


import com.pingdu.entity.rescue.Rescue;

import static com.pingdu.manager.ThreadLocalManager.em;

import java.util.List;

import javax.persistence.TypedQuery;


@Repository
public class RescueDao {
	
	public boolean modify(Rescue rescue) {
		Rescue res = em().find(Rescue.class, rescue.getRescueCode());
		if(res != null)
		{
			res.setAddress(rescue.getAddress());
			res.setEmail(rescue.getEmail());
			res.setPhone(rescue.getPhone());
			res.setRescueName(rescue.getRescueName());
			try {
				em().merge(res);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}
	
	public boolean add (Rescue rescue){
		try {
			em().persist(rescue);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean delete(int code){
		Rescue res = em().find(Rescue.class, code);
		if(res != null)
		{
			try {
				em().remove(res);
				return true;
			} catch (Exception e) {
				return false;
			}		
		}
		return false;
	}
	
	public Integer sum(int rows) {
		String jpql = "select res from Rescue res where 1=1";
		TypedQuery<Rescue> query = em().createQuery(jpql, Rescue.class);
		int SUM = ((query.getResultList().size()) - 1) / rows + 1;
		return SUM;
	}
	
	public List<Rescue> getRescuesList(int page, int rows) {
		int head = (page - 1) * rows;
		String jpql = "select res from Rescue res where 1=1";
		TypedQuery<Rescue> query = em().createQuery(jpql, Rescue.class);
		query.setFirstResult(head);
		query.setMaxResults(rows);
		List<Rescue> list = query.getResultList();
		return list;
	}

    //////查询条件待完善 
	public List<Rescue> search(String searchType, String keyWord, int page, int rows) {
		String jpql = new String();
		int head = (page - 1) * rows;
		if (searchType.equals("名称")) {
			jpql = "select res from Rescue res where res.rescueName like :keyWord order by res.rescueCode";
		}
		if (searchType.equals("联系电话")) {
			jpql = "select res from Rescue res where res.phone like :keyWord order by res.rescueCode";
		}
		if (searchType.equals("邮箱")) {
			jpql = "select res from Rescue res where res.email like :keyWord order by res.rescueCode";
		}
		
		TypedQuery<Rescue> query = em().createQuery(jpql, Rescue.class);
		query.setParameter("keyWord", "%"+keyWord+"%");
		query.setFirstResult(head);
		query.setMaxResults(rows);
		List<Rescue> list = query.getResultList();
		return list;
	}
	
	public int sumOfSearch(String searchType, String keyWord)
	{
		String jpql = new String();
		if (searchType.equals("名称")) {
			jpql = "select res from Rescue res where res.rescueName like :keyWord order by res.rescueCode";
		}
		if (searchType.equals("联系电话")) {
			jpql = "select res from Rescue res where res.phone like :keyWord order by res.rescueCode";
		}
		if (searchType.equals("邮箱")) {
			jpql = "select res from Rescue res where res.email like :keyWord order by res.rescueCode";
		}
		
		TypedQuery<Rescue> query = em().createQuery(jpql, Rescue.class);
		query.setParameter("keyWord", "%"+keyWord+"%");
		List<Rescue> list = query.getResultList();
		return list.size();
	}

	public List<Rescue> getAllRescuesList() {
		String jpql = "select res from Rescue res where 1=1";
		TypedQuery<Rescue> query = em().createQuery(jpql, Rescue.class);
		
		
		return query.getResultList();
	}
	
}
