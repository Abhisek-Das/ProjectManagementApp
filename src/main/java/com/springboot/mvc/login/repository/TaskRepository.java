package com.springboot.mvc.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.mvc.login.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	List<Task> findByTaskname(String taskname);
	

}
