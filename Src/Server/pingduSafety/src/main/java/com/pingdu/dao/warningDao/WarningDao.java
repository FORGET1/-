package com.pingdu.dao.warningDao;

import static com.pingdu.manager.ThreadLocalManager.em;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import com.pingdu.utility.PublicVariable;

import com.pingdu.view.WarningView;
import com.pingdu.entity.warning.Warning;

@Repository
public class WarningDao {

	
	/*--------------------------------业务逻辑------------------------------------*/

	/**
	 * 获取报警信息列表
	 */
	public  List<WarningView> getAll(int page) {
		int index = (page - 1) * PublicVariable.rows;
		String jpql = String.format("select %1$s(w) from Warning w where 1=1 order by w.warningCode DESC", WarningView.class.getName());
		
		TypedQuery<WarningView> query = em().createQuery(jpql, WarningView.class);
		query.setFirstResult(index);
		query.setMaxResults(PublicVariable.rows);
		List<WarningView> list = query.getResultList();
		return list;
	}

	/**
	 * 寻找报警
	 */
	public WarningView findByWarningCode(Integer warningCode) {
		
		Warning warning =em().find(Warning.class, warningCode);
		
		if(warning != null){
			return new WarningView(warning);
		}
		return new WarningView();
		
	}

	public  boolean deleteByWarningCode(Integer warningCode) {
		Warning warning  = em().find(Warning.class, warningCode);
		if(warning != null){
			em().remove(warning);
		}
		return true;
	}
	
	private String getSearchJpql(String searchType){
		String jpql = "select %1$s(w) from Warning w where ";
		if (searchType.equals("报警人")) {
			jpql +="w.userName like :keyWord ";
		}
		if (searchType.equals("标题")) {
			jpql = "w.warningTitle like :keyWord ";
		}
		if (searchType.equals("内容")) {
			jpql = "w.warningInfo like :keyWord ";
		}
		jpql +="AND w.warningDate>=:begintime AND w.warningDate<=:endtime order by w.warningCode";
		
		jpql = String.format(jpql, WarningView.class.getName());
		
		return jpql;
	}

	/**
	 * 查询
	 */
	public  List<WarningView> searchByKey(String keyWord, String searchType, String beginTime, String endTime,
			int page) {

		int index = (page - 1) * PublicVariable.rows;
		
		String jpql = getSearchJpql(searchType);
		
		TypedQuery<WarningView> query = em().createQuery(jpql, WarningView.class);
		query.setParameter("keyWord", "%" + keyWord + "%");

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		ParsePosition pos = new ParsePosition(0);
		String begin = new Date(sdf.parse(beginTime,pos).getTime()).toString();
		String end = new Date(sdf.parse(endTime,pos).getTime()).toString();

		query.setParameter("begintime", begin);
		query.setParameter("endtime", end);
		query.setFirstResult(index);
		query.setMaxResults(PublicVariable.rows);

		List<WarningView> list = query.getResultList();

		return list;
	}

	/**
	 * 获取总页数
	 */
	public  Integer sumOfPage() {
		String jpql = "select w from WarningDemoDomain w where 1=1";
		TypedQuery<Warning> query = em().createQuery(jpql, Warning.class);
		
		int SUM = ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
		return SUM;
	}

	/*
	 * 获取查询总页数
	 */
	public int searchSum(String keyWord, String searchType, String beginTime, String endTime) {
		String jpql = getSearchJpql(searchType);
		TypedQuery<WarningView> query = em().createQuery(jpql.toString(), WarningView.class);

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		ParsePosition pos = new ParsePosition(0);
		String begin = new Date(sdf.parse(beginTime,pos).getTime()).toString();
		String end = new Date(sdf.parse(endTime,pos).getTime()).toString();
		
		query.setParameter("keyWord", "%" + keyWord + "%");
		query.setParameter("begintime", begin);
		query.setParameter("endtime", end);
		int sum = ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
		return sum;
	}

	/**
	 * 获取最新报警标题
	 * 
	 * @return
	 */
	public  Map<String, Object> getLastWarningTitle() {
		String jpql = "select w from Warning w where 1=1 order by w.warningDate";
		TypedQuery<Warning> query = em().createQuery(jpql.toString(), Warning.class);
		List<Warning> warns = query.getResultList();
		Map<String, Object> map = new HashMap<String, Object>();
		if (warns.size() != 0) {
			map.put("warningTitle", warns.get(warns.size() - 1).getWarningTitle());
			map.put("warningInfo", warns.get(warns.size() - 1).getWarningInfo());
			return map;
		}
		map.put("warningTitle","暂无");
		map.put("warningInfo","暂无");
		return map;
	}


	public boolean addWaining(Warning warning ) {
		try {
			em().persist(warning);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
