package com.pingdu.dao.safePromiseDao;

import static com.pingdu.manager.ThreadLocalManager.em;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.pingdu.entity.safePromise.SafePromise;
import com.pingdu.entity.user.User;
import com.pingdu.utility.PublicVariable;

@Repository
public class SafePromiseDao {
	
	public Integer sum(int rows) {
		String jpql = "select sf from SafePromise sf where 1=1";
		TypedQuery<SafePromise> query = em().createQuery(jpql, SafePromise.class);
		int SUM = ((query.getResultList().size()) - 1) / rows + 1;
		return SUM;
	}
	

	public List<SafePromise> getSafePromiseList(int page, int rows) {
		int head = (page - 1) * rows;
		String jpql = "select sf from SafePromise sf where 1=1 ";
		TypedQuery<SafePromise> query = em().createQuery(jpql, SafePromise.class);
		query.setFirstResult(head);
		query.setMaxResults(rows);
		List<SafePromise> list = query.getResultList();
		for(SafePromise sf : list)
		{
			Integer entCode = sf.getEntCode();
//通过entCode获得企业名称，管辖部门名称			
			
			
			
			sf.setDeptName("测试");
			sf.setEntName("测试");
		}
		return list;
	}
	
	public SafePromise getSafePromiseInfo(int entCode, int safePromiseCode)
	{
		return em().find(SafePromise.class, safePromiseCode);
	}
	
	public boolean delete(int entCode, int safePromiseCode)
	{
		SafePromise sf = getSafePromiseInfo( entCode,safePromiseCode);
		if(sf!= null)
		{
			em().remove(sf);
			String path = sf.getImagePath();
			if(!(path==null)){
				File file = new File(path);
				if(file.isDirectory())
				{
					File[] subFiles = file.listFiles();
					for(int i=0;i<subFiles.length;i++)
					{
						subFiles[i].delete();
					}
				}
				file.delete();
			}
			return true;
		}
		return false;
	}
	
//////查询条件待完善 
	public List<SafePromise> searchSafePromise(String searchType, String keyWord, int page, int rows) {
		String jpql = new String();
		int head = (page - 1) * rows;
		if (searchType.equals("日期")) {
			jpql = "select sf from SafePromise sf where sf.expireDate like :keyWord order by sf.safePromiseCode";
		}
		if (searchType.equals("企业名称")) {
			jpql = "select sf from SafePromise sf where sf.entCode like :keyWord order by sf.safePromiseCode";
		}
		if (searchType.equals("上传时间")) {
			jpql = "select sf from SafePromise sf where sf.uploadDate like :keyWord order by sf.safePromiseCode";
		}
		
		TypedQuery<SafePromise> query = em().createQuery(jpql, SafePromise.class);
		query.setParameter("keyWord", "%"+keyWord+"%");
		query.setFirstResult(head);
		query.setMaxResults(rows);
		List<SafePromise> list = query.getResultList();
		for(SafePromise sf : list)
		{
			Integer entCode = sf.getEntCode();
//通过entCode获得企业名称，管辖部门名称			
			
			
			
			sf.setDeptName("测试");
			sf.setEntName("测试");
		}

		return list;
	}
	public int sumOfSearch(String searchType, String keyWord)
	{
		String jpql = new String();
		if (searchType.equals("日期")) {
			jpql = "select sf from SafePromise sf where sf.expireDate like :keyWord order by sf.safePromiseCode";
		}
		if (searchType.equals("企业名称")) {
			jpql = "select sf from SafePromise sf where sf.entCode like :keyWord order by sf.safePromiseCode";
		}
		if (searchType.equals("上传时间")) {
			jpql = "select sf from SafePromise sf where sf.uploadDate like :keyWord order by sf.safePromiseCode";
		}
		
		TypedQuery<SafePromise> query = em().createQuery(jpql, SafePromise.class);
		query.setParameter("keyWord", "%"+keyWord+"%");
		List<SafePromise> list = query.getResultList();
		return list.size();
	}


	public int userCodeChangeEntCode(String userCode) {
		String jpql = "select u from User u where u.userCode =:userCode";
		TypedQuery<User> query = em().createQuery(jpql,User.class);
		
		query.setParameter("userCode", userCode);
		
		return query.getResultList().get(0).getEnterprise().getEntCode();
	}


	public boolean modifySafetyPromise(SafePromise safe) {
		try {
			em().merge(safe);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public List<SafePromise> getCurrentSafePromiseList() {
		String jpql="select s from SafePromise s where s.issueDate=:issueDate";
		TypedQuery<SafePromise> query = em().createQuery(jpql,SafePromise.class);
		query.setParameter("issueDate", PublicVariable.sdfDate.format(new Date()));
		
		return query.getResultList();
	}
}
