package com.pingdu.autoBatch;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pingdu.entity.item.Item;
import com.pingdu.entity.point.Point;
import com.pingdu.entity.task.Task;
import com.pingdu.service.pointService.PointService;
import com.pingdu.service.taskService.TaskService;
import com.pingdu.utility.CaculateDate;
import com.pingdu.utility.PublicVariable;

@Component("autoTimerSchedule")
public class AutoTimerSchedule {

	@Autowired
	TaskService taskService;
	@Autowired
	PointService pointService;
	
	public void addTaskScheduler(){
		String currentDate = PublicVariable.sdfDate.format(new Date());
		List<Point> points = pointService.getAllPoint();
		int flag = points.size() > 0 ?0:1;
		System.out.println(flag);
		switch(flag){
		
		case 0:
			for(Point point : points){
				
				Task oldTask = taskService.getLastTaskByPoint(point.getPointCode());
				if(oldTask != null){
					String expDate = oldTask.getExpireDate();
					
					int result = expDate.compareTo(currentDate);
					if(result == 0){
						addOneTask(point, currentDate);
						System.err.println("---------------------------------------");
						System.err.println("add one task of old Task is not null");
						System.err.println("---------------------------------------");
					}
				}
				else{
					addOneTask(point, currentDate);
					System.err.println("---------------------------------------");
					System.err.println("add one task of old task is null");
					System.err.println("---------------------------------------");
				}
				
			}
			break;
		case 1:
			break;
			
		}
	}
	
	public void addOneTask(Point point,String currentDate){
		Set<Item> items = point.getItems();
		int circle = point.getPatrolCircle();
		String newExp = CaculateDate.getNextDate(currentDate, circle, Calendar.DATE, "yyyy-MM-dd");
		
		Task newTask =
				new Task(point.getEnterprise(),items,point,currentDate,(short)0,"/","/","/",(short)0,newExp,(short)0,"/","/","/","/");
		
		taskService.addTask(newTask);
	}
	
	
	
}
