package com.springboot.mvc.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.springboot.mvc.login.model.Project;
import com.springboot.mvc.login.repository.ProjectRepository;

public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepository projectrepository;
	
	@Override
	public void saveProject(Project project) {
		// TODO Auto-generated method stub
		projectrepository.save(project);
	}

	@Override
	public List<Project> findAllProjects() {
		// TODO Auto-generated method stub
		return projectrepository.findAll();
	}

	@Override
	public void deleteProject(long projectid) {
		// TODO Auto-generated method stub
		projectrepository.deleteById(projectid);
	}

	@Override
	public Project findByProjectId(long projectid) {
		// TODO Auto-generated method stub
		Optional<Project> project;
		project = projectrepository.findById(projectid);
		if (project.isPresent()) {
			return project.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Project findByProjectName(String projectname) {
		// TODO Auto-generated method stub
		return projectrepository.findByProjectname(projectname);
	}

	@Override
	public List<Project> sortByProjectStartdateAsc() {
		// TODO Auto-generated method stub
		return projectrepository.findAll(orderByProjectStartdateAsc());
	}
	
	private Sort orderByProjectStartdateAsc() {
		return new Sort(Sort.Direction.ASC, "projectstartdate");
		
	}

	@Override
	public List<Project> sortByProjectEnddateAsc() {
		// TODO Auto-generated method stub
		return projectrepository.findAll(orderByProjectEnddateAsc());
	}
	
	private Sort orderByProjectEnddateAsc() {
		return new Sort(Sort.Direction.ASC, "projectenddate");
		
	}

	@Override
	public List<Project> sortByProjectPriorityAsc() {
		// TODO Auto-generated method stub
		return projectrepository.findAll(orderByProjectPriorityAsc());
	}
	
	private Sort orderByProjectPriorityAsc() {
		return new Sort(Sort.Direction.ASC, "projectpriority");
	}

}
