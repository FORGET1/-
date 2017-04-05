package com.pingdu.dao.taskDao;


import static com.pingdu.manager.ThreadLocalManager.em;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import com.pingdu.entity.department.Department;
import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.entity.item.Item;
import com.pingdu.entity.point.Point;
import com.pingdu.entity.task.Task;
import com.pingdu.view.WorkOrderItem;
import com.pingdu.entity.task_item.Task_item;
import com.pingdu.entity.user.User;
import com.pingdu.utility.PublicVariable;
import com.pingdu.view.TaskView;


@Repository
public class TaskDao {

	private String getTaskListByEntAndStatusJpql(Integer entCode,String status){
		String jpql =  new String();
		String temp = " select new %5$s(a.taskCode,b.entName,c.deptName,d.pointName,a.completeTime,a.exception,a.probDesc) "
				+ " from %1$s a,%2$s b,%3$s c,%4$s d where a.enterprise.entCode = b.entCode AND a.enterprise.entCode=:entCode "
				+ " AND a.point.pointCode=d.pointCode AND b.department.deptCode=c.deptCode ";
			
				
		if ("ALL".equals(status)) {
			jpql= temp+ "order by a.issueTime DESC";
		}
		if ("00".equals(status)) {
			jpql= temp+ "AND a.isVerify=0 AND a.isComplete=0 order by a.issueTime DESC ";
		}
		if ("10".equals(status)) {
			jpql= temp+ "AND a.isVerify=0 AND a.isComplete=1 order by a.issueTime DESC ";
		}
		if ("11".equals(status)) {
			jpql= temp+ "AND a.isVerify=1 AND a.isComplete=1 order by a.issueTime DESC ";
		}
		jpql= String.format(jpql,Task.class.getName(),Enterprise.class.getName(),Department.class.getName(),Point.class.getName(),TaskView.class.getName());
		
		return jpql;
	}
	
	private String getTaskListByDeptAndStatusJpql(String status){
		
		String jpql =  new String();
		String temp = "select new %5$s(a.taskCode,b.entName,c.deptName,d.pointName,a.completeTime,a.exception,a.probDesc) "
				+ " from %1$s a,%2$s b,%3$s c,%4$s d where a.enterprise.entCode = b.entCode  "
				+ " AND a.point.pointCode=d.pointCode AND b.department.deptCode=c.deptCode "
				+ " AND c.deptCode =:deptCode ";
		if ("ALL".equals(status)) {
			jpql= temp+ "order by a.issueTime DESC";
		}
		if ("00".equals(status)) {
			jpql= temp+ "AND a.isVerify=0 AND a.isComplete=0 order by a.issueTime DESC ";
		}
		if ("10".equals(status)) {
			jpql= temp+ "AND a.isVerify=0 AND a.isComplete=1 order by a.issueTime DESC ";
		}
		if ("11".equals(status)) {
			jpql= temp+ "AND a.isVerify=1 AND a.isComplete=1 order by a.issueTime DESC ";
		}
		jpql= String.format(jpql,Task.class.getName(),Enterprise.class.getName(),Department.class.getName(),Point.class.getName(),TaskView.class.getName());
		
		return jpql;
	}
	
	private String getTaskListByStatusJpql(String status){
		
		String jpql =  new String();
		String temp = "select new %5$s(a.taskCode,b.entName,c.deptName,d.pointName,a.completeTime,a.exception,a.probDesc) "
				+ " from %1$s a,%2$s b,%3$s c,%4$s d where a.enterprise.entCode = b.entCode  "
				+ " AND a.point.pointCode=d.pointCode AND b.department.deptCode=c.deptCode ";
		if ("ALL".equals(status)) {
			jpql= temp+ "order by a.issueTime DESC";
		}
		if ("00".equals(status)) {
			jpql= temp+ "AND a.isVerify=0 AND a.isComplete=0 order by a.issueTime DESC ";
		}
		if ("10".equals(status)) {
			jpql= temp+ "AND a.isVerify=0 AND a.isComplete=1 order by a.issueTime DESC ";
		}
		if ("11".equals(status)) {
			jpql= temp+ "AND a.isVerify=1 AND a.isComplete=1 order by a.issueTime DESC ";
		}
		jpql= String.format(jpql,Task.class.getName(),Enterprise.class.getName(),Department.class.getName(),Point.class.getName(),TaskView.class.getName());
		
		return jpql;
	}
	
	private String searchTaskListByDeptAndStatusJpql(String status,String searchType){
		
		String jpql =  new String();
		String temp = "select new %5$s(a.taskCode,b.entName,c.deptName,d.pointName,a.completeTime,a.exception,a.probDesc) "
				+ " from %1$s a,%2$s b,%3$s c,%4$s d where a.enterprise.entCode = b.entCode  "
				+ " AND a.point.pointCode=d.pointCode AND b.department.deptCode=c.deptCode "
				+ " AND c.deptCode =:deptCode ";
		
		switch (searchType) {
		case "项点名称":
			temp+="AND d.pointName like :keyword ";
			break;
		case "企业名称":
			temp+="AND b.entName like :keyword ";
			break;
		case "管辖部门":
			temp+="AND c.deptName like :keyword ";
			break;
		}
		if ("ALL".equals(status)) {
			jpql= temp+ "order by a.issueTime DESC";
		}
		if ("00".equals(status)) {
			jpql= temp+ "AND a.isVerify=0 AND a.isComplete=0 order by a.issueTime DESC ";
		}
		if ("10".equals(status)) {
			jpql= temp+ "AND a.isVerify=0 AND a.isComplete=1 order by a.issueTime DESC ";
		}
		if ("11".equals(status)) {
			jpql= temp+ "AND a.isVerify=1 AND a.isComplete=1 order by a.issueTime DESC ";
		}
		jpql= String.format(jpql,Task.class.getName(),Enterprise.class.getName(),Department.class.getName(),Point.class.getName(),TaskView.class.getName());
		
		return jpql;
	}
	
	private String searchTaskListByStatusJpql(String status,String searchType){
		
		String jpql =  new String();
		String temp = "select new %5$s(a.taskCode,b.entName,c.deptName,d.pointName,a.completeTime,a.exception,a.probDesc) "
				+ " from %1$s a,%2$s b,%3$s c,%4$s d where a.enterprise.entCode = b.entCode  "
				+ " AND a.point.pointCode=d.pointCode AND b.department.deptCode=c.deptCode ";
		switch (searchType) {
		case "项点名称":
			temp+="AND d.pointName like :keyword ";
			break;
		case "企业名称":
			temp+="AND b.entName like :keyword ";
			break;
		case "管辖部门":
			temp+="AND c.deptName like :keyword ";
			break;

		}
		if ("ALL".equals(status)) {
			jpql= temp+ "order by a.issueTime DESC";
		}
		if ("00".equals(status)) {
			jpql= temp+ "AND a.isVerify=0 AND a.isComplete=0 order by a.issueTime DESC ";
		}
		if ("10".equals(status)) {
			jpql= temp+ "AND a.isVerify=0 AND a.isComplete=1 order by a.issueTime DESC ";
		}
		if ("11".equals(status)) {
			jpql= temp+ "AND a.isVerify=1 AND a.isComplete=1 order by a.issueTime DESC ";
		}
		jpql= String.format(jpql,Task.class.getName(),Enterprise.class.getName(),Department.class.getName(),Point.class.getName(),TaskView.class.getName());
		
		return jpql;
	}
	
	/**
	 * 通过企业获得任务
	 * @param entCoded
	 * @param page
	 * @param status
	 * @return
	 */
	public List<TaskView> getTaskListByEntAndStatus(Integer entCode,int page,String status)
	{
		
		String jpql = getTaskListByEntAndStatusJpql(entCode,status);
		
		TypedQuery<TaskView> query = em().createQuery(jpql, TaskView.class);
		query.setParameter("entCode", entCode);
		int head = (page - 1) * 15;
		query.setFirstResult(head);
		query.setMaxResults(15);
		
		List<TaskView> list = query.getResultList();
		return  list;
	}
	/**
	 * 通过部门获取任务
	 * @param deptId
	 * @param page
	 * @param status
	 * @return
	 */
	
	public List<TaskView> getTaskListByDeptAndStatus(int deptCode,int page,String status)
	{
		String jpql = getTaskListByDeptAndStatusJpql(status);

		TypedQuery<TaskView> query = em().createQuery(jpql.toString(), TaskView.class);
		query.setParameter("deptCode", deptCode);
		
		int head = (page - 1) * 15;
		query.setFirstResult(head);
		query.setMaxResults(15);
		
		List<TaskView> list = query.getResultList();
		return  list;
	}
	
	/**
	 * 计算总页数
	 * @param entCode
	 * @param page
	 * @param status
	 * @return
	 */
	public int calPageByEnt(Integer entCode,String status)
	{
		
		String jpql = getTaskListByEntAndStatusJpql(entCode,status);
		TypedQuery<TaskView> query = em().createQuery(jpql, TaskView.class);
		query.setParameter("entCode", entCode);
		
		int sum =  (query.getResultList().size() - 1) / PublicVariable.rows + 1;
		return  sum;
	}
	
	/**
	 * 计算总页数
	 * @param entCode
	 * @param status
	 * @return
	 */
//	public int calPageByDept(Integer deptId, String status) {
//		
//		List<Integer> enterpriseCodes= Deptdao.getEnterpriseCodes(deptId);
//		
//		String Temp = getTaskListByDeptAndStatusJpql(status);
//		 StringBuffer jpql= new StringBuffer(Temp);
//		 jpql.append(" AND a.enterprise.entCode in ( " );
//		 for(int i=0;i<enterpriseCodes.size();i++){
//			 jpql.append(":entCode"+i+",");
//		 }
//		 jpql.deleteCharAt(jpql.length()-1);
//		 
//		TypedQuery<TaskView> query = em().createQuery(jpql.toString(), TaskView.class);
//		 for(int k=0;k<enterpriseCodes.size(); k++){
//		 query.setParameter("entCode"+k, enterpriseCodes.get(k));
//		 }
//		 
//		 int sum =  (query.getResultList().size() - 1) / PublicVariable.rows + 1;
//		 return sum;
//	}
	/**
	 * 查询
	 * @param type
	 * @param key
	 * @param page
	 * @return
	 */
	public  List<TaskView> searchTasks(String type,String key,int page)
	{
		String jpql = new String();
		if (type.equals("企业名称")) {
			jpql = "select new %5$s(a.taskCode,b.entName,c.deptName,d.pointName,a.completeTime,a.exception,a.probDesc) "
				+ " from %1$s a,%2$s b,%3$s c,%4$s d where a.enterprise.entCode = b.entCode "
				+ " AND a.point.pointCode=d.pointName AND b.department.deptCode=c.deptCode  AND b.entName like :key ";
		}
		if (type.equals("管辖部门")) {
			jpql = "select new %5$s(a.taskCode,b.entName,c.deptName,d.pointName,a.completeTime,a.exception,a.probDesc) "
				+ " from %1$s a,%2$s b,%3$s c,%4$s d where a.enterprise.entCode = b.entCode "
				+ " AND a.point.pointCode=d.pointName AND b.department.deptCode=c.deptCode AND c.deptName like :key ";
		}
		if (type.equals("项点名称")) {
			jpql = "select new %5$s(a.taskCode,b.entName,c.deptName,d.pointName,a.completeTime,a.exception,a.probDesc) "
				+ " from %1$s a,%2$s b,%3$s c,%4$s d where a.enterprise.entCode = b.entCode "
				+ " AND a.point.pointCode=d.pointName AND b.department.deptCode=c.deptCode  AND d.pointName like :key ";
		}
		jpql= String.format(jpql,Task.class.getName(),Enterprise.class.getName(),Department.class.getName(),Point.class.getName(),TaskView.class.getName());
		TypedQuery<TaskView> query = em().createQuery(jpql, TaskView.class);
		
		query.setParameter("key", "%" + key + "%");
	
		int head = (page - 1) * 15;
		query.setFirstResult(head);
		query.setMaxResults(15);
		
		List<TaskView> list = query.getResultList();
		if(list.size()==0){
			TaskView t=new TaskView();
			list.add(t);
			return list;
		}
		
		return list;
		
	}
	/**
	 * 计算查询页数
	 * @param type
	 * @param key
	 * @return
	 */
	public int calSearchPage(String type,String key)
	{
		String jpql = new String();
		if (type.equals("企业名称")) {
			jpql = "select new %5$s(a.taskCode,b.entName,c.deptName,d.pointName,a.completeTime,a.exception,a.probDesc) "
				+ " from %1$s a,%2$s b,%3$s c,%4$s d where a.enterprise.entCode = b.entCode "
				+ "AND a.point.pointCode=d.pointName AND b.department.deptCode=c.deptCode  AND b.entName like :key ";
		}
		if (type.equals("管辖部门")) {
			jpql = "select new %5$s(a.taskCode,b.entName,c.deptName,d.pointName,a.completeTime,a.exception,a.probDesc) "
				+ " from %1$s a,%2$s b,%3$s c,%4$s d where a.enterprise.entCode = b.entCode "
				+ " AND a.point.pointCode=d.pointName AND b.department.deptCode=c.deptCode AND c.deptName like :key ";
		}
		if (type.equals("项点名称")) {
			jpql = "select new %5$s(a.taskCode,b.entName,c.deptName,d.pointName,a.completeTime,a.exception,a.probDesc) "
				+ " from %1$s a,%2$s b,%3$s c,%4$s d where a.enterprise.entCode = b.entCode "
				+ " AND a.point.pointCode=d.pointName AND b.department.deptCode=c.deptCode  AND d.pointName like :key ";
		}
		jpql= String.format(jpql,Task.class.getName(),Enterprise.class.getName(),Department.class.getName(),Point.class.getName(),TaskView.class.getName());
		TypedQuery<TaskView> query = em().createQuery(jpql, TaskView.class);
		
		query.setParameter("key", "%" + key + "%");
		
		int sum =  (query.getResultList().size() - 1) / PublicVariable.rows + 1;
		return  sum;
	}
	
	public void addTask(Task task)
	{
		em().persist(task);
	}
	
	
	/**
	 * 删除
	 * @param taskCode
	 * @return
	 */
	
	public  String delete(Integer taskCode) {
		Task t = findBycode(taskCode);
		if (t != null) {
			em().remove(t);
			return "SUCCESS";
		}
		return "FAILED";
	}
	/**
	 * 查找一个任务
	 * @param taskCode
	 * @return
	 */
	public  Task findBycode(Integer taskCode) {
		return em().find(Task.class, taskCode);
	}
	
	/**
	 * 获取未完成任务信息
	 * @param taskCode
	 * @return
	 */
	public  TaskView getUnFinishedTaskInfo(Integer taskCode)
	{
		String jpql =  new String();
		
		 jpql = "select new %4$s(a.taskCode,a.issueTime,a.uploadTime,b.entName,c.pointName,c.pointAddr)"
				+ "from %1$s a,%2$s b ,%3$s c where a.enterprise.entCode = b.entCode AND a.point.pointCode=c.pointCode "
				+ "AND　a.taskCode=:taskCode" ;
		jpql= String.format(jpql,Task.class.getName(),Enterprise.class.getName(),Point.class.getName(),TaskView.class.getName());
		TypedQuery<TaskView> query = em().createQuery(jpql, TaskView.class);
		query.setParameter("taskCode", taskCode);
		List<TaskView> list = query.getResultList();
		int size = list.size();
		if(size == 0){
			return new TaskView();
		}
		else		
		return list.get(0);
	}
	/**+
	 * 获取已完成任务的部分信息
	 * @param taskCode
	 * @return
	 */
	public  TaskView getFinishedTaskView(Integer taskCode)
	{
		String jpql =  new String();
	
		jpql = "select new %5$s(a.taskCode,a.issueTime,a.uploadTime,a.completeTime, "
				+ "b.entName,c.pointName,c.pointAddr,d.name) "
				+ " from %1$s a,%2$s b ,%3$s c ,%4$s d "
				+ "where a.enterprise.entCode = b.entCode AND a.point.pointCode=c.pointCode "
				+ " AND a.user.userCode = d.userCode "
				+ " AND　a.taskCode=:taskCode " ;
		jpql= String.format(jpql,Task.class.getName(),Enterprise.class.getName(),Point.class.getName(),User.class.getName()
				,TaskView.class.getName());
		TypedQuery<TaskView> query = em().createQuery(jpql, TaskView.class);
		query.setParameter("taskCode", taskCode);
		List<TaskView> list = query.getResultList();
		int size = list.size();
		if(size == 0){
			return new TaskView();
		}
		else		
		return list.get(0);
	}
	/**
	 * 获取已完成任务的部分信息
	 * @param taskCode
	 * @return
	 */
	public List<WorkOrderItem>  getFinishedItems(Integer taskCode)
	{
		
		String jpql =  new String();
		jpql="select new %3$s(a.itemCode,a.itemInfo,b.isException) from %1$s a,%2$s b where a.itemCode = b.itemCode AND b.taskCode=:taskCode";
		jpql=String.format(jpql, Item.class.getName(),Task_item.class.getName(),WorkOrderItem.class.getName());
		TypedQuery<WorkOrderItem> query = em().createQuery(jpql, WorkOrderItem.class);
		query.setParameter("taskCode", taskCode);
		List<WorkOrderItem> list = query.getResultList();
		
		return list;
	}	
	/**
	 * 获取任务信息
	 * @param taskCode
	 * @return
	 */
	public  Task getTaskInfo(Integer taskCode) {
		if (taskCode != 0) {
			String jpql = "select t from Task t where t.taskCode=:taskCode";
			TypedQuery<Task> query = em().createQuery(jpql, Task.class);
			query.setParameter("taskCode", taskCode);
			List<Task> tasks = query.getResultList();
			if (tasks.size() != 0) {
				return tasks.get(0);
			}
			return new Task();
		}
		return new Task();
	}
	
	/**
	 * 插入任务
	 */
	public void insert() {
		em().persist(this);
	}
	/**
	 * 修改任务
	 * @param task
	 */
	public void modifyTask(Task task) {
		try {
			em().merge(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取完成任务列表
	 * @param entCode
	 * @param type
	 * @return
	 */
	public List<TaskView> getUnFinishedTaskList(Integer entCode, String type) {
		
			String jpql =  new String();
			String temp = "select new %3$s(a.taskCode,a.issueTime,b.pointName,a.enterprise.entCode,a.expireDate,b.pointCode,a.probDesc,"
					+ " b.pointAddr,b.patrolCircle ) "
					+ " from %1$s a,%2$s b where a.point.pointCode = b.pointCode AND a.enterprise.entCode=:entCode ";
			if ("1".equals(type)) {
				jpql= temp+ " AND  b.patrolCircle = 1 ";
			}
			if ("2".equals(type)) {
				jpql= temp+ " AND  b.patrolCircle = 7 ";
			}
			if ("3".equals(type)) {
				jpql= temp+ " AND  b.patrolCircle = 30 ";
			}
			jpql+=" and a.isComplete=0 ";
			jpql= String.format(jpql,Task.class.getName(),Point.class.getName(),TaskView.class.getName());
			
			TypedQuery<TaskView> query = em().createQuery(jpql, TaskView.class);
			query.setParameter("entCode", entCode);
			List<TaskView> list = query.getResultList();
			return  list;
		
	}

	public 	List<Task_item> getAllTaskItemsByTaskCode(Integer taskCode) {
		
		String jpql =  "select t from Task_item t where t.taskCode=:taskCode";
		TypedQuery<Task_item> query = em().createQuery(jpql, Task_item.class);
		query.setParameter("taskCode", taskCode);
		List<Task_item>  tasks= query.getResultList();
		if(tasks==null){
			tasks.add(new Task_item());
			return  tasks;
		}
		else return tasks;

	}

	public void updateTaskItem(Integer taskCode, Integer itemCode, short exception) {
//		int exception;
//		exception=exceptions;
		String jpql = "update Task_item ti  set ti.isException =:isException where ti.taskCode=:taskCode AND ti.itemCode=:itemCode ";
		TypedQuery<Task_item> query = em().createQuery(jpql, Task_item.class);
		query.setParameter("taskCode", taskCode);
		query.setParameter("itemCode", itemCode);
//		query.setParameter("userCode", userCode);
		query.setParameter("isException", exception);
		query.executeUpdate();
		
	}

	public Task getLastTaskByPointCode(Integer pointCode) {
		String jpql =  "select t from Task t where t.point.pointCode=:pointCode order by t.taskCode DESC";
		
		TypedQuery<Task> query = em().createQuery(jpql, Task.class);
		query.setParameter("pointCode", pointCode);
		
		List<Task> tasks = query.getResultList();
		
		return tasks.size()>0?tasks.get(0):null;
		
	}

	public List<TaskView> getTaskListByStatus(Integer page, String status) {
		
		String jpql =getTaskListByStatusJpql(status);
		TypedQuery<TaskView> query = em().createQuery(jpql.toString(), TaskView.class);
		
		int head = (page - 1) * 15;
		query.setFirstResult(head);
		query.setMaxResults(15);
		
		List<TaskView> list = query.getResultList();
		return  list;
	}

	public int sumOfGetTaskListByStatus(String status) {
		String jpql =getTaskListByStatusJpql(status);
		TypedQuery<TaskView> query = em().createQuery(jpql.toString(), TaskView.class);

		
		return (query.getResultList().size() - 1) / PublicVariable.rows + 1;
	}

	public Integer sumOfSearchTaskListByKey(String searchType, String keyWord, String status) {
		
		String jpql = searchTaskListByStatusJpql(status, searchType);
		
		TypedQuery<TaskView> query = em().createQuery(jpql,TaskView.class);
		
		query.setParameter("keyword", "%"+keyWord+"%");
		return (query.getResultList().size() - 1) / PublicVariable.rows + 1;
	}

	public List<TaskView> searchTaskListByKey(String searchType, String keyWord, int page, String status) {

		String jpql = searchTaskListByStatusJpql(status, searchType);
		int head = (page-1)*PublicVariable.rows;
		TypedQuery<TaskView> query = em().createQuery(jpql,TaskView.class);
		
		query.setParameter("keyword", "%"+keyWord+"%");
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.rows);
		
		
		return query.getResultList();
	}

	public Integer sumOfSearchTaskListByKeyAndDept(String searchType, String keyWord, String manageCode, String status) {

		String jpql = searchTaskListByDeptAndStatusJpql(status, searchType);
		
		TypedQuery<TaskView> query = em().createQuery(jpql,TaskView.class);
		query.setParameter("keyword", "%"+keyWord+"%");
		query.setParameter("deptCode", Integer.parseInt(manageCode));
		
		
		return (query.getResultList().size() - 1) / PublicVariable.rows + 1;
	}

	public List<TaskView> searchTaskListByKeyAndDept(String searchType, String keyWord, String manageCode, int page, String status) {

		String jpql = searchTaskListByDeptAndStatusJpql(status, searchType);
		int head = (page-1)*PublicVariable.rows;
		TypedQuery<TaskView> query = em().createQuery(jpql,TaskView.class);
		query.setParameter("keyword", "%"+keyWord+"%");
		query.setParameter("deptCode", Integer.parseInt(manageCode));
		query.setFirstResult(head);
		query.setMaxResults(PublicVariable.rows);
		
		return query.getResultList();
	}

	public List<TaskView> getArroundMonthTaskList() {
		
		
		
		
		
		
		return null;
	}
}
