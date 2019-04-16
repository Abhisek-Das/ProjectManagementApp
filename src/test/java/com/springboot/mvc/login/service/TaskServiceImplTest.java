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
import com.springboot.mvc.login.model.Task;
import com.springboot.mvc.login.model.User;
import com.springboot.mvc.login.repository.ProjectRepository;
import com.springboot.mvc.login.repository.TaskRepository;
import com.springboot.mvc.login.repository.UserRepository;


public class TaskServiceImplTest extends ProjectManagementAppApplicationTests {

	
	@InjectMocks
	private TaskServiceImpl taskservice;
	@Mock
	private TaskRepository taskrepository;
	@Mock
	private ProjectRepository projrepository;
	private Task task;
	private Project project;
	Optional<Task> taskopt;
	Optional<Project> projectopt;
	private List<Task> tasklist;
	private long taskid = 1;
	private int taskupd = 1;
	private long projectid = 1;
	
	@Before
	public void datasetup() {
		
		MockitoAnnotations.initMocks(this);
		
		task = new Task(1, "Task1", new Date(2019,04,04), new Date(2019,04,06), 10, 1, new Project(), new ParentTask());
		
		taskopt = Optional.of(task);
		tasklist = new ArrayList<Task>();
		tasklist.add(task);
		
		Mockito.when(taskrepository.save(any())).thenReturn(task);
		Mockito.when(taskrepository.findById(any())).thenReturn(taskopt);
		Mockito.when(taskrepository.findByTaskname(any())).thenReturn(tasklist);
		Mockito.when(taskrepository.updateTaskByProject(any())).thenReturn(taskupd);		
		taskservice.saveTask(task);
		
		project = new Project(1, "Project1", new Date(2019,04,04), new Date(2019,04,06), 10);
		projectopt = Optional.of(project);
		Mockito.when(projrepository.findById(any())).thenReturn(projectopt);
		
	}
	
	@Test
	public void testsaveTask() {
		task = new Task(2, "Task2", new Date(2019,04,04), new Date(2019,04,06), 10, 1, new Project(), new ParentTask());
		taskservice.saveTask(task);
		
		Optional<Task> taskopt = taskrepository.findById((long) 2);
		assertTrue(taskopt != null);
	}
	
	@Test
	public void testdeleteTask() {
		taskservice.deleteTask(taskid);
		
		Optional<Task> tasklist = taskrepository.findById((long) 1);
		assertTrue(tasklist != null);
	}
	
	@Test
	public void testfindAllTasks() {
		List<Task> tasklist = taskservice.findAllTasks();
		assertTrue(tasklist != null);
	}
	
	@Test 
	public void testupdateTaskByProject() {
		taskupd = taskrepository.updateTaskByProject(new Project());
		assertTrue(taskopt != null);
	}
	
	@Test
	public void testsortTaskByStartDate() {
		final List<Task> tasklist = taskservice.sortTaskByStartDate(projectid);
		assertTrue(tasklist != null);
	}
	
	@Test
	public void testsortTaskByEndDate() {
		final List<Task> tasklist = taskservice.sortTaskByEndDate(projectid);
		assertTrue(tasklist != null);
	}
	
	@Test
	public void testsortTaskByTaskPriority() {
		final List<Task> tasklist = taskservice.sortTaskByTaskPriority(projectid);
		assertTrue(tasklist != null);
	}
	
	@Test
	public void testsortTaskByTaskStatus() {
		final List<Task> tasklist = taskservice.sortTaskByTaskStatus(projectid);
		assertTrue(tasklist != null);
	}
	
	
}
