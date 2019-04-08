package com.springboot.mvc.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.mvc.login.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	Project findByProjectname(String projectname);

}
