package com.pingdu.dao.pointDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.pingdu.manager.ThreadLocalManager.em;

import com.pingdu.dao.deptDao.DeptDao;
import com.pingdu.dao.entDao.EntDao;
import com.pingdu.dao.itemDao.ItemDao;
import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.entity.item.Item;
import com.pingdu.entity.point.Point;
import com.pingdu.utility.PublicVariable;
import com.pingdu.view.PointView;

@Repository
public class PointDao {

	@Autowired
	private DeptDao deptDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private EntDao entDao;
	/*
	 * 获取 所有 项点view列表
	 */
	public List<PointView> getAllPointView(Integer page){

		String jpql = String.format("select new %1$s(p,e.entCode,e.entName)from Point p ,Enterprise e "
								+ "where  p.enterprise.entCode = e.entCode  order by e.entCode ",
								PointView.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);

		int head = (page - 1) * PublicVariable.getRows();
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.getRows());
		
		List<PointView> points = query.getResultList();
		return points;
	}
	/*
	 * 获取 所有 项点view列表页数
	 */
	public Integer getAllPointViewPage(){

		String jpql = String.format("select new %1$s(p,e.entCode,e.entName)from Point p ,Enterprise e "
								+ "where  p.enterprise.entCode = e.entCode  order by e.entCode ",
								PointView.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);

		
		return  (query.getResultList().size() - 1 )/ PublicVariable.getRows()+1;
	}
	/*
	 * 根据企业编码查找页数
	 * @param page
	 * @param entCode
	 * @return 
	 */
	public int getPageByEntCode(Integer entCode){
		
		String jpql = String.format("select new %1$s(p,e.entCode,e.entName) "
				+ "from %2$s p,%3$s e where e.entCode = :entCode and p.enterprise.entCode = e.entCode ",
									PointView.class.getName(),
									Point.class.getName(),
									Enterprise.class.getName());
		
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		
		query.setParameter("entCode",entCode);
		
		int sum = ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
		return  sum;
		
	}
	
	/*
	 * 查看项点信息
	 * @param pointCode
	 * @return PointView
	 */
	public PointView getPointInfo (Integer pointCode){
		String jpql = 
				String.format("select new %1$s(p.pointCode,p.pointName,e.entCode,e.entName,p.pointAddr,p.pointPerson,p.pointPersonTel,p.lantitude,p.longtitude)"
						+ " from %2$s p, %3$s e "
						+ "where p.enterprise.entCode = e.entCode and p.pointCode = :pointCode", 
						PointView.class.getName(),
						Point.class.getName(),
						Enterprise.class.getName());
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter("pointCode", pointCode);
		List<PointView> points = query.getResultList();
		
		if(points == null){
			return null;
		}else {
			return points.get(0);
		}
	}


	/*
	 * 是否存在项点
	 */
	public boolean isExist(Integer pointCode){
		
		if(find(pointCode) == null){
			return false;
		}
		else{
			return true;
		}
	}

	/*
	 * @description 通过企业编号获取项点view列表
	 * @param entCode
	 * @return List<Point>
	 */
	public List<PointView> getByEntCode(Integer entCode,Integer page){
		String jpql = "select new %1$s(p,e.entCode,e.entName) "
				+ "from  Point p, Enterprise e where p.enterprise.entCode = :entCode and p.enterprise.entCode = e.entCode ";
		jpql = String.format(jpql, PointView.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter("entCode", entCode);
		
		int head = (page - 1) * PublicVariable.getRows();
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.getRows());
		
		List<PointView> points = query.getResultList();
		
		return points;
	}
	/*
	 * @description 通过企业名称获取项点view列表
	 * @param entName
	 * @return List<Point>
	 */
	public List<PointView> getByEntName(String entName,Integer page){
		String jpql = "select new %1$s(p,e.entCode,e.entName) "
				+ "from  Point p, Enterprise e where p.enterprise.entName like :entName and p.enterprise.entCode = e.entCode order by p.pointCode ";
		jpql = String.format(jpql, PointView.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter("entName", "%"+entName+"%");
		
		int head = (page - 1) * PublicVariable.getRows();
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.getRows());
		
		List<PointView> points = query.getResultList();
		
		return points;
	}

	/*
	 * @description 通过企业名称获取项点页数
	 * @param entCode
	 * @return Integer
	 */
	public Integer getPageByEntName(String entName){
		String jpql = "select new %1$s(p,e.entCode,e.entName) "
				+ "from  Point p, Enterprise e where p.enterprise.entName like :entName and p.enterprise.entCode = e.entCode";
		jpql = String.format(jpql, PointView.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter("entName","%"+entName+"%");
		
		return (query.getResultList().size() - 1 )/ PublicVariable.getRows()+1;
	}

	/*
	 * 通过项点名称获取项点view列表
	 * @param pointName
	 * @return List<pointView>
	 */
	public List<PointView> getByPointName(String pointName,Integer page){

		String jpql =String.format("select new %1$s(p,e.entCode,e.entName)from Point p ,Enterprise e  "
				+ "where p.pointName like :key and p.enterprise.entCode = e.entCode order by e.entCode",
				PointView.class.getName());
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter("key","%"+pointName+"%" );
		
		int head = (page - 1) * PublicVariable.getRows();
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.getRows());
		
		List<PointView> points = query.getResultList();
		return points;
	}
	
	/*
	 * 通过项点名称获取项点view列表页数
	 * @param pointName
	 * @return int
	 */
	public Integer getPageByPointName(String pointName){

		String jpql =String.format("select new %1$s(p,e.entCode,e.entName)from Point p ,Enterprise e "
				+ "where p.pointName like :key and p.enterprise.entCode = e.entCode",
				PointView.class.getName());
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter("key","%"+pointName+"%" );
		
		return (query.getResultList().size() - 1 )/ PublicVariable.getRows()+1;
	}
	/*
	 * 通过项点地址获取项点view列表
	 * @param pointAddr
	 * @param page
	 * @return List<pointView>
	 */
	public List<PointView> getByPointAddr(String pointAddr,Integer page){
		String jpql = String.format("select new %1$s(p,e.entCode,e.entName)"
				+ "from  Point p ,Enterprise e where p.pointAddr like :pointAddr and p.enterprise.entCode = e.entCode",
				PointView.class.getName());
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter("key","%"+pointAddr+"%" );
		int head = (page - 1) * PublicVariable.getRows();
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.getRows());
		List<PointView> points = query.getResultList();
		if(points.size() == 0){
			return null;
		}else {
			return points;
		}
	}
	/*
	 * 通过项点地址获取项点view页数
	 * @param pointAddr
	 * @return Integer
	 */
	public Integer getPageByPointAddr(String pointAddr){
		String jpql = String.format("select new %1$s(p) from  Point p ,Enterprise e "
				+ "where p.pointAddr like :pointAddr and p.enterprise.entCode = e.entCode",
				PointView.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter("key","%"+pointAddr+"%" );

		int sum = ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
		return  sum;
	}
	/*
	 * 通过NFCCode获取项点view列表
	 * @param NFCCode
	 * @param page
	 * @return List<pointView>
	 */
	public List<PointView> getByNFCCode(String NFCCode,Integer page){
		
		String jpql = String.format("select new %1$s(p,e.entCode,e.entName)from Point p ,Enterprise e "
				+ "where p.NFCCode like :key and p.enterprise.entCode = e.entCode",
				PointView.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter("key","%"+NFCCode+"%" );
		
		int head = (page - 1) * PublicVariable.getRows();
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.getRows());
		
		List<PointView> points = query.getResultList();
		if(points.size() == 0){
			return null;
		}else {
			return points;
		}
	}
	/*
	 * 通过NFCCode获取项点view页数
	 * @param NFCCode
	 * @return page
	 * 
	 */
	public Integer getPageByNFCCode(String NFCCode){
		String jpql = String.format("select new %1$s(p)from Point p ,Enterprise e "
				+ "where p.NFCCode like :key and p.enterprise.entCode = e.entCode",
				PointView.class.getName());
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter("key","%"+NFCCode+"%" );
		int sum = ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
		return  sum;
	}
	/*
	 * 通过pointInfo获取项点view列表
	 * @param pointInfo
	 * @param page
	 * @return List<pointView>
	 */
	public List<PointView> getByPointInfo(String pointInfo,Integer page){
		
		String jpql = String.format("select new %1$s(p,e.entCode,e.entName)from Point p ,Enterprise e "
				+ "where  p.pointInfo like :key and p.enterprise.entCode = e.entCode",
				PointView.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter("key","%"+pointInfo+"%" );
		
		int head = (page - 1) * PublicVariable.getRows();
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.getRows());
		List<PointView> points = query.getResultList();
		if(points.size() == 0){
			return null;
		}else {
			return points;
		}
	}
	
	public Integer getPageByPointInfo(String pointInfo){
		String jpql = String.format("select new %1$s(p)from Point p ,Enterprise e "
				+ "where  p.pointInfo like :key and p.enterprise.entCode = e.entCode",
				PointView.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter("key","%"+pointInfo+"%" );
		
		int sum = ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
		return  sum;
	}
	
	public List<PointView> getByPointCircle(Integer pointCircle,Integer page){
		String jpql = String.format("select new %1$s(p,e.entCode,e.entName)from Point p ,Enterprise e "
				+ "where  p.pointCircle = :pointCircle and p.enterprise.entCode = e.entCode",
				PointView.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter( "pointCircle",pointCircle);
		
		int head = (page - 1) * PublicVariable.getRows();
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.getRows());
		List<PointView> points = query.getResultList();
		if(points.size() == 0){
			return null;
		}else {
			return points;
		}
	}
	
	public Integer getPageByPointCircle(Integer pointCircle){
		String jpql = String.format("select new %1$s(p)from Point p ,Enterprise e "
				+ "where  p.pointCircle = :pointCircle and p.enterprise.entCode = e.entCode",
				PointView.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter( "pointCircle",pointCircle);
		int sum = ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
		return  sum;
	}
	/*
	 * 通过项点编码查询pointview
	 */
	public PointView getByPointCode(Integer pointCode){
		String jpql = String.format("select new $1%s(p,e.entCode,e.entName)"
				+ "from %2$s p ,%3$s e  "
				+ "where  p.pointCode = :pointCode and p.enterprise.entCode = e.entCode",
				PointView.class.getName(),
				Point.class.getName(),
				Enterprise.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter( "pointCode",pointCode);
		
		
		List<PointView> points = query.getResultList();
		if(points.size() == 0){
			return null;
		}else {
			return points.get(0);
		}
	}
	
	public List<PointView> getByPointPerson(String pointPerson,Integer page){
		String jpql = String.format("select new %1$s(p,e.entCode,e.entName)from Point p ,Enterprise e "
				+ "where  p.pointPerson  like :pointPerson and p.enterprise.entCode = e.entCode order by e.entCode",
				PointView.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter( "pointPerson","%"+pointPerson+"%");
		
		int head = (page - 1) * PublicVariable.getRows();
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.getRows());
		List<PointView> points = query.getResultList();
		
		return points;
		
	}
	public List<PointView> getByPointPersonTel(String pointPersonTel,Integer page){
		String jpql = String.format("select new %1$s(p,e.entCode,e.entName)from Point p ,Enterprise e "
				+ "where  p.pointPersonTel  like :pointPersonTel and p.enterprise.entCode = e.entCode order by e.entCode",
				PointView.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter( "pointPersonTel","%"+pointPersonTel+"%");
		
		int head = (page - 1) * PublicVariable.getRows();
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.getRows());
		List<PointView> points = query.getResultList();
		
		return points;
		
	}
	
	public Integer getPageByPointPerson(String pointPerson){
		String jpql = String.format("select new %1$s(p,e.entCode,e.entName)from Point p ,Enterprise e "
				+ "where  p.pointPerson like :pointPerson and p.enterprise.entCode = e.entCode",
				PointView.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter( "pointPerson","%"+pointPerson+"%");
		
		return  ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
		
	}
	public Integer getPageByPointPersonTel(String pointPersonTel){
		String jpql = String.format("select new %1$s(p,e.entCode,e.entName)from Point p ,Enterprise e "
				+ "where  p.pointPersonTel like :pointPersonTel and p.enterprise.entCode = e.entCode",
				PointView.class.getName());
		
		TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
		query.setParameter( "pointPersonTel","%"+pointPersonTel+"%");
		
		return  ((query.getResultList().size()) - 1) / PublicVariable.rows + 1;
		
	}
	/*
	 * 通过key查询pointView
	 */
	public List<PointView> search(String type, String key,Integer page){
		
		List<PointView> points = new ArrayList<PointView>();
	
		if (type.equals("项点名称")) {
			points = getByPointName(key,page);
		} 
		else if (type.equals("所属企业") || type.equals("企业名称")) {
			points = getByEntName(key,page);
		} 
		else if (type.equals("企业编号")) {
			points = getByEntCode(Integer.parseInt(key),page);
		} 
		else if (type.equals("项点信息")) {
			points = getByPointInfo(key,page);
		} 
		else if (type.equals("项点地址")) {
			points = getByPointAddr(key,page);
		} 
		else if (type.equals("NFCCode")) {
			points = getByNFCCode(key,page);
		} 
		else if (type.equals("巡检周期")) {
			points = getByPointCircle(Integer.parseInt(key),page);
		} 
		else if (type.equals("负责人")) {
			points = getByPointPerson(key, page);
		} 
		else if (type.equals("负责人电话")) {
			points = getByPointPersonTel(key,page);
		} 
		else if (type.equals("项点编号")) {
			PointView p = getByPointCode(Integer.parseInt(key));
			if( p == null){
				return null;
			}
			points.add(p);	
		}
		
		return points;
	}
	
	/*
	 * 
	 */
public Integer getSearchPage(String type, String key){
	int page = 1;
	if (type.equals("项点名称")) {
		page = getPageByPointName(key);
	}
	else if (type.equals("负责人")) {
		page = getPageByPointPerson(key);
	}
	else if (type.equals("负责人电话")) {
		page = getPageByPointPersonTel(key);
	}
	else if (type.equals("所属企业") || type.equals("企业名称")) {
		page = getPageByEntName(key);
	}
	else if (type.equals("项点信息")) {
		page = getPageByPointInfo(key);
	}
	else if (type.equals("项点地址")) {
		page = getPageByPointAddr(key);
	}
	else if (type.equals("NFCCode")) {
		page = getPageByNFCCode(key);
	}
	else if (type.equals("企业编号")) {
		page = getPageByEntCode(Integer.parseInt(key));
	}
	else if (type.equals("巡检周期")) {
		page = getPageByPointCircle(Integer.parseInt(key));
	}
	
	else if (type.equals("项点编号")) {
		page = 1;
	}
	
	return page;
}
	/*
	 * 通过企业编号获得项点
	 */
	public List<Point> getPointByEntCode(Integer entCode,Integer page){
		
		String jpql = String.format("select new %1$s(p.pointCode,p.pointName)"
				+ "from Point p where p.enterprise.entCode = :entCode",
				Point.class.getName());
		
		TypedQuery<Point> query = em().createQuery(jpql,Point.class);
		query.setParameter("entCode", entCode);
		int head = (page - 1) * PublicVariable.getRows();
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.getRows());
		List<Point> points = query.getResultList();
		return points;
	}
	/*
	 * @description 删除项点
	 * @param pointCode
	 * @return String
	 */

	public String delete(Integer pointCode){
		
		Point point = find(pointCode);
		//没有此项点
		if( point == null){
			return "FAILED";
		}
		try{
			em().remove(point);
			return "SUCCESS";
		}catch (Exception e) {
			return "FAILED";
		}
	}
	
	/*
	 * @description 添加项点
	 * @return String
	 */
	public String add(Point point,Integer entCode){
		Point p = find(point.getPointCode());
		//要添加的项点已经存在  ，返回错误
		if(p != null){
			return "FAILED";
		}
		//外键entCode,对应的企业编号是否存在
		EntDao entDao = new EntDao();
		Enterprise ent = entDao.findByCode(entCode);
		if(ent == null){
			return "FAILED";
		}
		point.setEnterprise(ent);
		
		
		try{
			em().persist(point);
			return "SUCCESS";
		}catch (Exception e) {
			e.printStackTrace();
			return "FAILED";
		}
	
	}
	/*
	 * @description 根据pointcode获得Point
	 * @param pointCode
	 * @return Point
	 */
	public Point find(Integer pointCode){
		
		try{
			Point p = em().find(Point.class, pointCode);
			return p;
		}catch(Exception e){
			return null;
		}
		
	}
	
	
	/*
	 * @description 修改项点信息
	 * @return String
	 */
	public String update( Point point){
		
		Point p = find(point.getPointCode());
		//要修改的point不存在
		if(p == null){
			return "FAILED";
		}
		
		p.setPointName(point.getPointName());
		p.setPointAddr(point.getPointAddr());
		p.setPointPerson(point.getPointPerson());
		p.setPointPersonTel(point.getPointPersonTel());
		p.setLantitude(point.getLantitude());
		p.setLongtitude(point.getLongtitude());
		p.setSafetyTips(point.getSafetyTips());
		
		
		try{
			em().merge(p);
			return "SUCCEED";
		}catch (Exception e) {
			return "FAILED";
		}
	}
	public String update(Point point,Integer entCode){
		Point p = find(point.getPointCode());
		//要修改的point不存在
		if(p == null){
			return "FAILED";
		}
		p.setPointName(point.getPointName());
		p.setPointAddr(point.getPointAddr());
		p.setPointPerson(point.getPointPerson());
		p.setPointPersonTel(point.getPointPersonTel());
		p.setLantitude(point.getLantitude());
		p.setLongtitude(point.getLongtitude());
		p.setSafetyTips(point.getSafetyTips());
		
		
		Enterprise ent = entDao.findByCode(entCode);
		
		p.setEnterprise(ent);
		try{
			em().merge(p);
			return "SUCCEED";
		}catch (Exception e) {
			return "FAILED";
		}
	}
	/*
	 * 通过企业编号获得 所有 项点
	 */
	public List<Point> getAllByEntCode(Integer entCode){
		
		String jpql = "select new %1$s(p.pointCode,p.pointName)from Point p where p.enterprise.entCode = :entCode";
		jpql = String.format(jpql, Point.class.getName());
		TypedQuery<Point> query = em().createQuery(jpql,Point.class);
		query.setParameter("entCode", entCode);
		
		List<Point> points = query.getResultList();
		return points;
	}
	
	/*
	 * 通过部门编号查询项点列表
	 */
	public List<PointView> getByDeptCode(Integer deptCode,Integer page){
		
		List<PointView> points = null;
		if("安监局".equals(deptDao.departmentInfo(deptCode).getDeptName())){
			points = getAllPointView(page);
		}
		else {
			String jpql = String.format("select new %1$s(p) from Point p ,Department d "
					+ "where p.enterprise.department.deptCode = d.deptCode and d.deptCode = :deptCode order by p.enterprise.entCode",
					PointView.class.getName());
			TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
			query.setParameter("deptCode", deptCode);
			
			int head = (page - 1) * PublicVariable.getRows();
			query.setFirstResult(head);
			query.setMaxResults(PublicVariable.getRows());
			points = query.getResultList();
		}
		
		return points;
	}
	
	/*
	 * 通过部门编号查询项点列表
	 */
	public Integer getPageByDeptCode(Integer deptCode){
		
		Integer page = 1;
		
		if("安监局".equals(deptDao.departmentInfo(deptCode).getDeptName())){
			page = getAllPointViewPage();
		}
		else {
			String jpql = String.format("select new %1$s(p) from Point p ,Department d "
					+ "where p.enterprise.department.deptCode = d.deptCode and d.deptCode = :deptCode order by p.enterprise.entCode",
					PointView.class.getName());
			TypedQuery<PointView> query = em().createQuery(jpql,PointView.class);
			query.setParameter("deptCode", deptCode);
			
		
			page = (query.getResultList().size()-1)/ PublicVariable.getRows()+1;
		}
		
		return page;
	}
	/*
	 * 获得所有项点
	 */
	public List<Point> getAllPoint(){
		String jpql = "select p from Point p where 1=1";
		TypedQuery<Point> query = em().createQuery(jpql,Point.class);
		
		return query.getResultList();
	}
	/*
	 * 获得该项点的所有条目
	 * @param pointCode
	 * @return 
	 */
	public List<Item> getItemsByPointCode(Integer pointCode){
		
		Point point = find(pointCode);
		if(point != null){
			//set 转 list<Item>
			List<Item> items = new ArrayList<Item>(point.getItems());
			return items;
		}
		else{
			return null;
		}
		
	}
	/*
	 *从一个项点中删除一个条目
	 *@param pointCode
	 *@param itemCode
	 *@return
	 */
	public String deletePointItem(Integer pointCode, Integer itemCode){
		Point point = find(pointCode);
		if(point == null){
			return "FAILED";
		}
		
		Set<Item> items = point.getItems();
		//删除为itemCode的item
		for(Item item:items){
			if(item.getItemCode() == itemCode){
				items.remove(item);	
			}
		}
		
		point.setItems(items);
		return update(point);
		
	}
	
	/*
	 * 向一个项点中添加多个条目
	 * @param pointCode
	 * @param itemCodes
	 */
	public String addItemsToPoint(Integer pointCode, List<Integer> itemCodes){
		
		Point point = find(pointCode);
		if(point == null){
			return "FAILED";
		}
		Set<Item> items = point.getItems();
		
		//将items添加进point
		for(int code:itemCodes){
		
			items.add(itemDao.getItem(code));
		}
		
		point.setItems(items);
		return update(point);
		
	}
	
}
