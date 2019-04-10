package com.springboot.mvc.login.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.mvc.login.model.Project;
import com.springboot.mvc.login.model.Task;



public interface TaskRepository extends JpaRepository<Task, Long> {
	
	@Modifying
	@Transactional
//	@Query("update Task t where t.project=?1")
	@Query("delete Task t where t.project=?1")
	public int updateTaskByProject(Project project);
	
	List<Task> findByTaskname(String taskname);
	List<Task> findByProject(Project project);
	List<Task> findByProjectOrderByTaskstartdate(Project project);
	List<Task> findByProjectOrderByTaskenddate(Project project);
	List<Task> findByProjectOrderByTaskpriority(Project project);
	List<Task> findByProjectOrderByTaskstatus(Project project);
	
	int countByProject(Project project);
	int countByProjectAndTaskstatus(Project project, int tasktatus);

}
