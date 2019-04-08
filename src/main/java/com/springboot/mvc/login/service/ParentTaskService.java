package com.springboot.mvc.login.service;

import java.util.List;

import com.springboot.mvc.login.model.ParentTask;

public interface ParentTaskService {
	
	public void saveParentTask(ParentTask parenttask);
	public void deleteParentTask(long parentid);

	public ParentTask findByParentId(long parentid);
	public ParentTask findByParenttask(String parenttask);
	public List<ParentTask> findAllParentTasks();

}
