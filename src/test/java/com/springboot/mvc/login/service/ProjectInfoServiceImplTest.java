package com.springboot.mvc.login.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.springboot.mvc.login.ProjectManagementAppApplicationTests;
import com.springboot.mvc.login.model.ParentTask;
import com.springboot.mvc.login.model.Project;
import com.springboot.mvc.login.model.ProjectInfo;
import com.springboot.mvc.login.model.Task;
import com.springboot.mvc.login.model.User;
import com.springboot.mvc.login.repository.ProjectRepository;
import com.springboot.mvc.login.repository.TaskRepository;
import com.springboot.mvc.login.repository.UserRepository;


public class ProjectInfoServiceImplTest extends ProjectManagementAppApplicationTests {

	@InjectMocks
	private ProjectInfoServiceImpl projinfoservice;

	@Mock
	private ProjectRepository projrepository;
	private Project project;
	Optional<Project> projectopt;
	private List<Project> projectlist;
	
	@Before
	public void datasetup() {
		
		MockitoAnnotations.initMocks(this);
		
		project = new Project(1, "Project1", new Date(2019,04,04), new Date(2019,04,06), 10);
		
		projectlist = new ArrayList<Project>();
		Mockito.when(projrepository.findAll()).thenReturn(projectlist);
		
	}
	
	@Test
	public void testfindAll() {
		
		List<ProjectInfo> projectInfo = projinfoservice.findAll();
		assertTrue(projectInfo != null);
		
	}
	
	@Test
	public void sortProjectByStartDate() {
		
		List<ProjectInfo> projectInfo = projinfoservice.sortProjectByStartDate();
		assertTrue(projectInfo != null);
		
	}
	
	@Test
	public void sortProjectByEndDate() {
		
		List<ProjectInfo> projectInfo = projinfoservice.sortProjectByEndDate();
		assertTrue(projectInfo != null);
		
	}
	
	@Test
	public void sortProjectByPriority() {
		
		List<ProjectInfo> projectInfo = projinfoservice.sortProjectByPriority();
		assertTrue(projectInfo != null);
		
	}
	
	@Test
	public void sortProjectByCompleted() {
		
		List<ProjectInfo> projectInfo = projinfoservice.sortProjectByPriority();
		assertTrue(projectInfo != null);
		
	}
	
	
}
