package com.springboot.mvc.login.service;

import java.util.List;

import com.springboot.mvc.login.model.ProjectInfo;

public interface ProjectInfoService {
	
	public List<ProjectInfo> findAll();
	public List<ProjectInfo> sortProjectByStartDate();
	public List<ProjectInfo> sortProjectByEndDate();
	public List<ProjectInfo> sortProjectByPriority();
	public List<ProjectInfo> sortProjectByCompleted();
	

}
