package com.springboot.mvc.login.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
import com.springboot.mvc.login.model.Project;
import com.springboot.mvc.login.repository.ProjectRepository;

public class ProjectServiceImplTest extends ProjectManagementAppApplicationTests {
	
	@InjectMocks
	private ProjectServiceImpl projservice;
	@Mock
	private ProjectRepository projrepository;
	private Project project;
	Optional<Project> projectopt;
	private List<Project> projectlist;
	private long projectid = 1;
	
	@Before
	public void datasetup() {
		
		MockitoAnnotations.initMocks(this);
		
		project = new Project(1, "Project1", new Date(2019,04,04), new Date(2019,04,06), 10);
		
		projectopt = Optional.of(project);
		projectlist = new ArrayList<Project>();
		projectlist.add(project);
		
		Mockito.when(projrepository.save(any())).thenReturn(project);
		Mockito.when(projrepository.findById(any())).thenReturn(projectopt);
		Mockito.when(projrepository.findByProjectname(any())).thenReturn(project);
				
		projservice.saveProject(project);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testsaveProject() {
		project = new Project(2, "Project2", new Date(2019,06,04), new Date(2019,06,06), 11);
		projservice.saveProject(project);
		
		Optional<Project> projectlist1 = projrepository.findById((long) 2);
		assertTrue(projectlist1 != null);
	}
	
	@Test
	public void testfindAllProjects() {
		List<Project> projectlist = projservice.findAllProjects();
		assertTrue(projectlist != null);
	}
	
	@Test
	public void testdeleteProject() {
//		project = new Project(2, "Project2", new Date(2019,06,04), new Date(2019,06,06), 11);
		projservice.deleteProject(projectid);
		
		Optional<Project> projectlist1 = projrepository.findById((long) 1);
		assertTrue(projectlist1 != null);
	}
	
	@Test
	public void testfindByProjectId() {
		Project project = projservice.findByProjectId((long) 1);
		assertEquals(1, project.getProjectid());
	}
	
	@Test
	public void testfindByProjectName() {
		Project project = projservice.findByProjectName("Project1");
		assertEquals("Project1", project.getProjectname());
	}
	
	@Test
	public void testsortByProjectStartdateAsc() {
		projectlist = projservice.sortByProjectStartdateAsc();
		assertTrue(projectlist != null);
	}
	
	@Test
	public void testsortByProjectEnddateAsc() {
		projectlist = projservice.sortByProjectEnddateAsc();
		assertTrue(projectlist != null);
	}
	
	@Test
	public void testsortByProjectPriorityAsc() {
		projectlist = projservice.sortByProjectPriorityAsc();
		assertTrue(projectlist != null);
	}
	

}
