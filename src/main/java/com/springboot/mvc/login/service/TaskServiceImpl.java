package com.springboot.mvc.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mvc.login.model.Project;
import com.springboot.mvc.login.model.Task;
import com.springboot.mvc.login.repository.ProjectRepository;
import com.springboot.mvc.login.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskrepository;
	@Autowired
	ProjectRepository projectrepository;
	
	@Override
	public void saveTask(Task task) {
		// TODO Auto-generated method stub
		taskrepository.save(task);
	}

	@Override
	public void deleteTask(long taskid) {
		// TODO Auto-generated method stub
		taskrepository.deleteById(taskid);
	}

//	@Override
//	public void updateTask(long taskid) {
//		// TODO Auto-generated method stub
////		Task task = taskrepository.findById(taskid);
//	}

	@Override
	public List<Task> findAllTasks() {
		// TODO Auto-generated method stub
		return taskrepository.findAll();
	}

	@Override
	public int updateTaskByProject(long projectid) {
		// TODO Auto-generated method stub
		return taskrepository.updateTaskByProject(projectrepository.findById(projectid).get());
	}

	@Override
	public List<Task> sortTaskByStartDate(long projectid) {
		// TODO Auto-generated method stub
		return taskrepository.findByProjectOrderByTaskstartdate(projectrepository.findById(projectid).get());
	}

	@Override
	public List<Task> sortTaskByEndDate(long projectid) {
		// TODO Auto-generated method stub
		return taskrepository.findByProjectOrderByTaskenddate(projectrepository.findById(projectid).get());
	}

	@Override
	public List<Task> sortTaskByTaskPriority(long projectid) {
		// TODO Auto-generated method stub
		return taskrepository.findByProjectOrderByTaskpriority(projectrepository.findById(projectid).get());
	}

	@Override
	public List<Task> sortTaskByTaskStatus(long projectid) {
		// TODO Auto-generated method stub
		return taskrepository.findByProjectOrderByTaskstatus(projectrepository.findById(projectid).get());
	}

	@Override
	public Task findByTaskId(long taskid) {
		// TODO Auto-generated method stub
		Optional<Task> task;
		task = taskrepository.findById(taskid);
		if (task.isPresent()) {
			return task.get();
		}
		else {
			return null;
		}
	}

	@Override
	public int countByProject(Project project) {
		// TODO Auto-generated method stub
		return taskrepository.countByProject(project);
	}

}
