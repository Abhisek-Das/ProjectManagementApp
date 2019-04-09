package com.springboot.mvc.login.service;

import java.util.List;
import com.springboot.mvc.login.model.Project;

public interface ProjectService {
	
	public void saveProject(Project project);
	public void deleteProject(long projectid);

	public Project findByProjectId(long projectid);
	public Project findByProjectName(String projectname);
	public List<Project> findAllProjects();
	
	public List<Project> sortByProjectStartdateAsc();
	public List<Project> sortByProjectEnddateAsc();
	public List<Project> sortByProjectPriorityAsc();
	
	


}
