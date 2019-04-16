package com.springboot.mvc.login.service;



import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.springboot.mvc.login.ProjectManagementAppApplicationTests;
import com.springboot.mvc.login.model.ParentTask;
import com.springboot.mvc.login.model.Project;
import com.springboot.mvc.login.repository.ParentTaskRepository;


public class ParentTaskServiceImplTest extends ProjectManagementAppApplicationTests{

	@InjectMocks
	private ParentTaskServiceImpl parenttaskservice;
	
	@Mock
	private ParentTaskRepository parenttaskrepository;
	private ParentTask parenttask;
	long parentid = 1;
	private List<ParentTask> ptlist;
	Optional<ParentTask> ptaskopt;

	@Before
	public void datasetup() {
		
		MockitoAnnotations.initMocks(this);
		
		parenttask = new ParentTask(1, "Parent Task1");
		ptaskopt = Optional.of(parenttask);
		
		ptlist = new ArrayList<ParentTask>();
		ptlist.add(parenttask);
		
		Mockito.when(parenttaskrepository.save(any())).thenReturn(parenttask);
		Mockito.when(parenttaskrepository.findByParenttask(any())).thenReturn(ptlist);
		Mockito.when(parenttaskrepository.findById(any())).thenReturn(ptaskopt);
		
		parenttaskservice.saveParentTask(parenttask);
		
	}
	
	@Test
	public void testsaveParentTask() {
		parenttask = new ParentTask(2, "Parent Task2");
		
	    parenttaskservice.saveParentTask(parenttask);
	    Optional<ParentTask> parenttask1 = parenttaskrepository.findById((long) 2);
	    		
		assertTrue(parenttask1 != null);
	
	}
	
	@Test
	public void testfindAllParentTasks() {
		List<ParentTask> pttask = parenttaskservice.findAllParentTasks();
		assertTrue(pttask != null);
		
	}
	
	@Test
	public void testdeleteParentTask() {
		parenttaskservice.deleteParentTask(parentid);
		
		List<ParentTask> pttask = parenttaskrepository.findByParenttask("Parent Task1");
 		
		assertTrue(pttask != null);
	
	}
	
	@Test
	public void testfindByParentId() {
		Optional<ParentTask> ptaskopt = parenttaskrepository.findById(parentid);
		
		assertTrue(ptaskopt != null);
	}
	
	@Test 
	public void testfindByParenttask() {
//		ParentTask ptlist = parenttaskservice.findByParenttask("Parent Task1");
		
		List<ParentTask> pttask = parenttaskrepository.findByParenttask("Parent Task2");
 		
		assertTrue(pttask != null);
	
	}
	
}
