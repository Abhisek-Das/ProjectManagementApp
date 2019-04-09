package com.springboot.mvc.login.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.springboot.mvc.login.model.Project;
import com.springboot.mvc.login.model.Task;



public interface TaskRepository extends JpaRepository<Task, Long> {
	
	@Modifying
	@Query("update Task t set t.taskid=?1")
	public void updateTaskByProject(Project project);
	
	List<Task> findByTaskname(String taskname);
	List<Task> findByProject(Project project);
	List<Task> findByProjectOrderByTaskstartdate(Project project);
	List<Task> findByProjectOrderByTaskenddate(Project project);
	List<Task> findByProjectOrderByTaskpriority(Project project);
	List<Task> findByProjectOrderByTaskstatus(Project project);
	
	

}
