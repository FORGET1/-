package com.pingdu.serviceImpl.taskServiceImpl;

import java.io.File;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;

import com.pingdu.dao.taskDao.TaskDao;
import com.pingdu.entity.task.Task;
import com.pingdu.entity.task_item.Task_item;
import com.pingdu.service.taskService.TaskService;
import com.pingdu.view.TaskView;
import com.pingdu.view.WorkOrderItem;

@Service
public  class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskDao taskDao;
	
	@Override
	public List<TaskView> getTaskListByEntAndStatus(int entCode,int page,String status) {
		
		List<TaskView> taskList=taskDao.getTaskListByEntAndStatus(entCode, page,status);
		
		return taskList;
	}
	@Override
	public List<TaskView> getTaskListByDeptAndStatus(Integer deptCode, Integer page, String status) {
		
		List<TaskView> taskList = taskDao.getTaskListByDeptAndStatus(deptCode, page, status);
		return taskList;
	}
	
	@Override
	public List<TaskView> getTaskListByStatus(Integer page, String status) {
		List<TaskView> taskList = taskDao.getTaskListByStatus(page, status);
		return taskList;
	}
	
	@Override
	public int sumOfGetTaskListByStatus(String status) {
		return taskDao.sumOfGetTaskListByStatus(status);
	}
	@Override
	public int getPageByEnt(int entCode,String status) {
		
		return taskDao.calPageByEnt(entCode,status);
	}

	@Override
	public int getPageByDept(int deptId, String status) {
		
		return 0;
		
	}
	
	@Override
	public int getSearchPage(String type, String key) {
	
		return taskDao.calSearchPage(type, key);
	}

	@Override
	public String  deleteTaskByTaskCode(int taskCode) {
		String flag;
		flag=taskDao.delete(taskCode);
		return flag;
		
	}

	@Override
	public TaskView getUnFinishedTaskInfo(int taskCode) {
		
		return taskDao.getUnFinishedTaskInfo(taskCode);
	}

	@Override
	public TaskView getFinishedTaskView(int taskCode) {
		
		return taskDao.getFinishedTaskView(taskCode);
	}

	@Override
	public List<WorkOrderItem> getFinishedItems(int taskCode) {
		
		return taskDao.getFinishedItems(taskCode);
	}

	@Override
	public Task findBycode(int taskCode) {
		
		return taskDao.findBycode(taskCode);
	}

	@Override
	public boolean GenerateImage(String imgStr, String Path, String imageName) {
		String pic = imgStr.substring(imgStr.indexOf(",") + 1);
		if (pic == null) // 图像数据为空
			return false;
		try {
			// Base64解码
			byte[] bytes = Base64.decodeBase64(pic.getBytes());
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}

			String taskImagePath = this.getClass().getClassLoader().getResource("").getPath() + "tasks"
					+ File.separator;
			File file = new File(taskImagePath + Path);
			// 如果文件夹不存在则创建
			if (!file.exists() && !file.isDirectory()) {
				System.out.println("//不存在");
				file.mkdirs();
			} else {
				System.out.println("//目录存在");
			}
			// 生成png图片
			OutputStream out = new FileOutputStream(taskImagePath + Path + File.separator + imageName + ".jpg");
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void modifyTask(Task task) {
		taskDao.modifyTask(task);
	}

	@Override
	public List<TaskView> getUnFinishedTaskList(int entCode, String type) {
		return taskDao.getUnFinishedTaskList(entCode,type);
	}

	@Override
	public List<Task_item> getAllTaskItemsByTaskCode(int taskCode) {
		List<Task_item> tasks=new ArrayList<>();
		tasks=taskDao.getAllTaskItemsByTaskCode(taskCode);
		return tasks;
	}

	@Override
	public Task getTaskInfo(int taskCode) {
		
		return taskDao.getTaskInfo(taskCode);
	}

	@Override
	public void setException(int taskCode, int itemCode, short exception) {
		taskDao.updateTaskItem(taskCode, itemCode, exception);
	}

	@Override
	public List<TaskView> searchTask(String type, String key,int page) {
		List<TaskView> tasks=new ArrayList<>();
		tasks=taskDao.searchTasks(type, key, page);
		return tasks;
	}

	@Override
	public void addTask(Task task) {
		taskDao.addTask(task);
	}

	@Override
	public Task getLastTaskByPoint(Integer pointCode) {
		return taskDao.getLastTaskByPointCode(pointCode);
	}
	@Override
	public Integer sumOfSearchTaskListByKey(String searchType, String keyWord,String status) {
		
		return taskDao.sumOfSearchTaskListByKey(searchType,keyWord,status);
	}
	@Override
	public List<TaskView> searchTaskListByKey(String searchType, String keyWord, int page,String status) {
		
		return taskDao.searchTaskListByKey(searchType,keyWord,page,status);
	}
	@Override
	public Integer sumOfSearchTaskListByKeyAndDept(String searchType, String keyWord,String manageCode,String status) {
		return taskDao.sumOfSearchTaskListByKeyAndDept(searchType,keyWord,manageCode,status);
	}
	@Override
	public List<TaskView> searchTaskListByKeyAndDept(String searchType, String keyWord, String manageCode,int page,String status) {
		
		return taskDao.searchTaskListByKeyAndDept(searchType,keyWord,manageCode,page,status);
	}





	

}
