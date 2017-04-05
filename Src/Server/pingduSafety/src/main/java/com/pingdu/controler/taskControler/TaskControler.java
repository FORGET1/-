package com.pingdu.controler.taskControler;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingdu.view.TaskMap;
import com.pingdu.view.TaskView;
import com.pingdu.view.WorkOrderItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pingdu.baseModel.BaseController;
import com.pingdu.baseModel.Root;
import com.pingdu.entity.task.Task;
import com.pingdu.entity.task_item.Task_item;
import com.pingdu.entity.user.User;
import com.pingdu.service.taskService.TaskService;
import com.pingdu.service.userService.UserService;
import com.pingdu.utility.PublicVariable;

@Controller
@RequestMapping(value = "task")
public class TaskControler extends BaseController{

	@Autowired
	TaskService taskService;
	@Autowired
	Root root;
	@Autowired
	UserService userService;
	/**
	 * 获取企业任务列表
	 * @param entCode
	 * @param page
	 * @param status
	 * @return
	 */
	
	@RequestMapping(value = "getTaskListByEntAndStatus", method = RequestMethod.GET)
	@ResponseBody
	public Root getTaskListByEntAndStatus(@RequestParam("entCode") Integer entCode, @RequestParam("page") Integer page,@RequestParam("status") String status)
	{
		Integer sumpage = taskService.getPageByEnt(entCode,status);
		if (page > sumpage || page == 0) {
			page = sumpage;
		}
		List<TaskView> tasks = taskService.getTaskListByEntAndStatus(entCode, page,status);
		if (tasks.size() == 0 && sumpage == 1) {
			page = 0;
			sumpage = 0;
		}
		//TaskMap seachTask=new TaskMap();
		root.setCurrentPage(page);
		root.setSumPage(sumpage);
		root.setMessage(0, status);
		root.setObject(tasks);

		return root;
	}
	/**
	 * 获取部门任务列表
	 * @param deptId
	 * @param page
	 * @param status
	 * @return
	 */
	
	@RequestMapping(value = "getTaskListByDeptAndStatus", method = RequestMethod.GET)
	@ResponseBody
	public Root getTaskListByDeptAndStatus(@RequestParam("manageCode") String manageCode,
			@RequestParam("page") Integer page,
			@RequestParam("status") String status){
		Integer sumpage;
		List<TaskView> tasks;
		switch (manageCode) {
		case "":
			
			sumpage = taskService.sumOfGetTaskListByStatus(status);
			tasks = taskService.getTaskListByStatus(page,status);
			break;
		default:
			sumpage = taskService.getPageByDept(Integer.parseInt(manageCode),status);
			tasks = taskService.getTaskListByDeptAndStatus(Integer.parseInt(manageCode), page,status);
			break;
		}

		root.setCurrentPage(page);
		root.setSumPage(sumpage);
		root.setMessage(0, status);
		root.setObject(tasks);
		return root;
	}
	
	@RequestMapping(value = "searchTask", method = RequestMethod.GET)
	@ResponseBody
	public Root searchTask(@RequestParam("manageCode") String manageCode,
						   @RequestParam("searchType") String searchType, 
						   @RequestParam("keyWord") String keyWord,
						   @RequestParam("page") int page,@RequestParam("status") String status) {

		Integer sumpage;
		List<TaskView> tasks;
		switch (manageCode) {
		case "":
			
			sumpage = taskService.sumOfSearchTaskListByKey(searchType,keyWord,status);
			tasks = taskService.searchTaskListByKey(searchType,keyWord,page,status);
			break;
		default:
			sumpage = taskService.sumOfSearchTaskListByKeyAndDept(searchType,keyWord,manageCode,status);
			tasks = taskService.searchTaskListByKeyAndDept(searchType,keyWord,manageCode,page,status);
			break;
		}
	
		root.setCurrentPage(page);
		root.setSumPage(sumpage);	
		root.setObject(tasks);
		return root;
	}
	
	
	
	
	/**
	 * 删除任务
	 * @param taskCode
	 * @return
	 */
	@RequestMapping(value = "deleteTaskByTaskCode", method = RequestMethod.POST)
	@ResponseBody
	public Root deleteTaskByTaskCode(@RequestParam("taskCode") Integer taskCode)
	{
		String flag;
		flag = taskService.deleteTaskByTaskCode(taskCode);
		
		root.setMessage(0, flag);
		return  root;
	}
	/**
	 * 批量删除
	 * @param taskCodes
	 * @return
	 */
	@RequestMapping(value = "deleteTaskByTaskCodes", method = RequestMethod.POST)
	@ResponseBody
	public Root deleteTaskByTaskCode(@RequestParam("taskCode[]")  List<Integer> taskCodes)
	{
		for(int i=0;i<taskCodes.size();i++)
		{
			String flag=taskService.deleteTaskByTaskCode(taskCodes.get(i));
			if(flag.equals("FAILEd"))
			{
				root.setMessage(1, "FAILEd");
				return root;
			}
		}
		root.setMessage(0, "SUCCESS");
		return  root;
	}
	/**
	 * 获取路径下的图片
	 * @param taskCode
	 * @return
	 */
	@RequestMapping(value = "getImage", method = RequestMethod.GET)
	@ResponseBody
	public Root  getImage(@RequestParam("taskCode") Integer taskCode) {

		Task task = taskService.getTaskInfo(taskCode);
		String path = task.getImagePath();

		List<String> imagePaths = com.pingdu.utility.TakeFilePathAndName.getFile(path);
		root.setObject(imagePaths);
		//root.setObject(imagePaths);
		return root;
	}
	
	/**
	 * 加载图片
	 * 
	 * @param path
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getRealImage", method = RequestMethod.GET)
	@ResponseBody
	public void getRealImage(@RequestParam("path") String path, HttpServletRequest request,
			HttpServletResponse response) {
		if (!(path == null)) {
			String filename = path.substring(path.lastIndexOf(File.separator) + 1, path.length());
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
			try {
				InputStream inputStream = new FileInputStream(new File(path));
				OutputStream os = response.getOutputStream();
				byte[] b = new byte[2048];
				int length;
				while ((length = inputStream.read(b)) > 0) {
					os.write(b, 0, length);
				}
				os.close();
				inputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 获取任务信息
	 * @param taskCode
	 * @return
	 */
	@RequestMapping(value = "getTaskInfo", method = RequestMethod.GET)
	@ResponseBody
	public Root getTaskInfo(@RequestParam("taskCode") Integer taskCode)
	{
		
		Task task=taskService.getTaskInfo(taskCode);
		TaskMap taskMap=new TaskMap();
		//ModelAndView mav = new ModelAndView();
		if(task.getIsComplete()==0)
		{
			TaskView unFinish=taskService.getUnFinishedTaskInfo(taskCode);
			root.setObject(unFinish);
			return root;
			
		}
		else{
			TaskView finish=taskService.getFinishedTaskView(taskCode);
			List<WorkOrderItem> items=taskService.getFinishedItems(taskCode);
			taskMap.setTaskView(finish);
			taskMap.setItems(items);
			root.setObject(taskMap);
			return root;
		}
		
		
	}
	/**
	 * 审核通过
	 * @param taskCode
	 * @return
	 */
	
	@RequestMapping(value = "checkPassTask", method = RequestMethod.GET)
	@ResponseBody
	public Root checkPassTask(@RequestParam("taskCode") int taskCode,@RequestParam("status") short status
			,@RequestParam("advDesc") String advDesc) {
		
		Task task = taskService.getTaskInfo(taskCode);
		if(task!=null)
		{
			task.setIsVerify(status);
			task.setAdvDesc(advDesc);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
			task.setVerifyTime(df.format(new Date()).toString());
		
			taskService.modifyTask(task);
			root.setMessage(0, "SUCCESS");
			return root;
		}
		
		else
			{
			root.setMessage(1, "FAILED");
			return  root;
			}
		
		
	}
	
	/**************************************************APP端*****************************************************/
	/**
	 * 上传图片
	 * 
	 * @param taskCode
	 * @param image
	 * @return
	 */
	@RequestMapping(value = "appSendImage", method = RequestMethod.POST)
	@ResponseBody
	public Root sendImage(@RequestParam("taskCode") String taskCode, @RequestParam("image") String  image, @RequestParam("imageName") String imageName) {

		taskService.GenerateImage(image, taskCode, imageName);
		Task task = taskService.getTaskInfo(Integer.parseInt(taskCode));
		task.setImagePath(
				this.getClass().getClassLoader().getResource("").getPath() + "tasks" + File.separator + taskCode);
		taskService.modifyTask(task);
		root.setMessage(0, "success");
		return root;
	}	
	
	
	
	@RequestMapping(value = "appHandleTask", method = RequestMethod.POST)
	@ResponseBody
	public Root appHandleTask(@RequestParam("result") String taskResult,
			@RequestParam("taskCode") Integer taskCode, @RequestParam("probDesc") String probDesc,
			@RequestParam("completeTime") String completeTime,@RequestParam("userCode") String userCode)
	{
		Task task = taskService.getTaskInfo(taskCode);
		User u=userService.getUserByCode(userCode);
		List<WorkOrderItem> taskResultList = new Gson().fromJson(taskResult, new TypeToken<List<WorkOrderItem>>() {
		}.getType());
		if (taskResultList.size() != 0) {
			int count = 0;
		
			for (WorkOrderItem wod : taskResultList) {
				if (wod.isException==(short)1) {
					task.setException((short)1);
					System.err.println("******************");
					taskService.setException(taskCode, wod.itemCode, (short)1);
					//System.out.println(wod.yesIsChecked+"\n\n\n\n\n");
				} else {
					count++;
					System.err.println(wod.isException);
					System.err.println(wod.isException);
					taskService.setException(taskCode,wod.itemCode,(short)0);
				}
			}
			if(count == taskResultList.size()){
				task.setException((short) 0);
			}
		}
		else{
			task.setException((short) 0);
		}
		
		if(task!=null)
		{
			task.setUserInfo(u);
			try {
				probDesc = new String(probDesc.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			task.setProbDesc(probDesc);
			System.err.println();
			System.err.println(probDesc);
			System.err.println();
			task.setCompleteTime(completeTime);
			
			task.setIsComplete((short)1);
			
			task.setUploadTime(PublicVariable.sdf.format(new Date()));
			
			taskService.modifyTask(task);
			root.setMessage(0, "success");
			return root;
		}
		else {
			
			System.err.println("--------------------------------");
			System.err.println("--------task is null-----------");
			System.err.println("--------------------------------");
			root.setMessage(1, "fail");
			return  root;
		}
	}
	
	

	/**
	 * 本次任务结束信号
	 * 
	 * @param taskResult
	 * @return
	 */
	@RequestMapping(value = "appCompleteSignal", method = RequestMethod.POST)
	@ResponseBody
	public Root appCompleteSignal(@RequestParam("taskCode") String staskCode){
		Integer taskCode = Integer.parseInt(staskCode);
		Task task = taskService.getTaskInfo(taskCode);
		short s=1;
		task.setIsComplete(s);
		taskService.modifyTask(task);
		
		root.setMessage(0, "SUCCESS");
		return root;
	}
	
	/**
	 * 用户获取未完成任务列表
	 * 
	 * @param userCode
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "appGetUnFinishedTaskList", method = RequestMethod.GET)
	@ResponseBody
	public Root appGetUnFinishedTaskList(@RequestParam("entCode") Integer entCode,@RequestParam("type") String type) 
	{
		List<TaskView> tasks = taskService.getUnFinishedTaskList(entCode,type);
		root.setObject(tasks);
		return root;
	}
	/*
	 * 获取task_item
	 */
	@RequestMapping(value = "appGetTaskItemsByTaskCode", method = RequestMethod.POST)
	@ResponseBody
	public Root getAllTaskItems(@RequestParam("taskCode[]") List<Integer> taskCodes) {
		List<Task_item> taskItems=new ArrayList<>();
		for (Integer taskCode:taskCodes)
		{
			taskItems.addAll(taskService.getAllTaskItemsByTaskCode(taskCode));
		}
		int size=taskItems.size();
		root.setObject(taskItems);
		root.setMessage(size==0?1:0,size==0?"fail":"success");
		return root;
	}

}
