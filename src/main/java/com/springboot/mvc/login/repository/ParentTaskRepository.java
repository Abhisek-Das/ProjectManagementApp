package com.springboot.mvc.login.repository;

import java.util.List;


import com.springboot.mvc.login.model.ParentTask;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ParentTaskRepository extends JpaRepository<ParentTask, Long> {

	List<ParentTask> findByParenttask(String parenttask);
}
