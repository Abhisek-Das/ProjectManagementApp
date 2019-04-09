package com.springboot.mvc.login.service;

import java.util.List;
import com.springboot.mvc.login.model.Task;


public interface TaskService {
	
	public void saveTask(Task task);
	public void deleteTask(long taskid);
	public void updateTaskByProject(long projectid);
	
	public List<Task> findAllTasks();
	public List<Task> sortTaskByStartDate(long projectid);
	public List<Task> sortTaskByEndDate(long projectid);
	public List<Task> sortTaskByTaskPriority(long projectid);
	public List<Task> sortTaskByTaskStatus(long projectid);

}
