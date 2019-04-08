package com.springboot.mvc.login.service;

import java.util.List;

import com.springboot.mvc.login.model.Task;


public interface TaskService {
	
	public void saveTask(Task task);
	public void deleteTask(long taskid);
	public void updateTask(long taskid);
	
	public List<Task> findAllTasks();


}
