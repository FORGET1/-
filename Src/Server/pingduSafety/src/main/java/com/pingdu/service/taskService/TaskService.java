package com.pingdu.service.taskService;

import java.util.List;


import com.pingdu.entity.task.Task;
import com.pingdu.entity.task_item.Task_item;
import com.pingdu.view.TaskView;
import com.pingdu.view.WorkOrderItem;



public interface TaskService {

	public List<TaskView> getTaskListByEntAndStatus(int entCode,int page,String status);
	
	public List<TaskView> getTaskListByDeptAndStatus(Integer deptCode, Integer page, String status);
	
	public int getPageByEnt(int entCode,String status);
	
	public int getPageByDept(int deptId,String status);
	
	public int getSearchPage(String type,String key);
	
	public void addTask(Task task);
	
	public  Task findBycode(int taskCode);
	
	public  Task getTaskInfo(int taskCode);
	
	public String deleteTaskByTaskCode(int taskCode);
	
	public TaskView getUnFinishedTaskInfo(int taskCode);
	
	public  TaskView getFinishedTaskView(int taskCode);
	
	public  List<WorkOrderItem>  getFinishedItems(int taskCode);
	
	public boolean GenerateImage(String imgStr, String Path, String imageName);
	
	public void modifyTask(Task task);

	public List<TaskView> getUnFinishedTaskList(int entCode, String type);

	public List<Task_item> getAllTaskItemsByTaskCode(int taskCode);
	
	public void setException(int taskCode, int itemCode, short exception);
		
	public List<TaskView> searchTask( String type, String key,int page);

	public Task getLastTaskByPoint(Integer pointCode);

	public List<TaskView> getTaskListByStatus(Integer page, String status);

	public int sumOfGetTaskListByStatus(String status);

	public Integer sumOfSearchTaskListByKey(String searchType, String keyWord, String status);

	public List<TaskView> searchTaskListByKey(String searchType, String keyWord, int page, String status);

	public Integer sumOfSearchTaskListByKeyAndDept(String searchType, String keyWord, String manageCode, String status);

	public List<TaskView> searchTaskListByKeyAndDept(String searchType, String keyWord, String manageCode,int page,String status);
	
	


			 

}
