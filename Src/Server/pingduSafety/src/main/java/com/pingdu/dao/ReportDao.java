package com.pingdu.dao;
import com.pingdu.entity.Depts;

import com.pingdu.entity.Students;
import com.pingdu.view.Report;
import static com.pingdu.manager.ThreadLocalManager.em;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class ReportDao {
	
	public static SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static List<Report> getReport() {
        String jpql= String.format
        ("select new %3$s(a.deptId, a.deptName,b.stuNo,b.stuName,b.resTime) from %1$s a,%2$s b where a.deptId = b.depts.deptId",
                Depts.class.getName(),
                Students.class.getName(),
                Report.class.getName());
         
        //建立有类型的查询  
        TypedQuery<Report> reportTypedQuery= em().createQuery(jpql, Report.class);
        //另外有详细查询条件的在jpql中留出参数位置来(?1 ?2 ?3....)，然后在这设置
        //reportTypedQuery.setParameter(1, params);  
        List<Report> reports= reportTypedQuery.getResultList();
		
//		String sql = "select * from Report r where 1=1 ";
//		Query query= em().createNativeQuery(sql);
//		SQLQuery nativeQuery= query.unwrap(SQLQuery.class);
//		
//		nativeQuery.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
//		
//		List<Map<String, Object>> retVal = nativeQuery.list();
//		List<Report> reports = new ArrayList<Report>();
//		for(Map<String, Object>  item :retVal){
//			Report report = new Report();
//			report.setDeptId((Integer)item.get("deptId"));
//			report.setDeptName((String)item.get("deptId"));
//		}
//		
        return reports; 
    }  
	
}
