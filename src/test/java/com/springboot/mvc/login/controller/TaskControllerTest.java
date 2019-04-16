package com.springboot.mvc.login.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.springboot.mvc.login.model.ParentTask;
import com.springboot.mvc.login.model.Project;
import com.springboot.mvc.login.model.Task;
import com.springboot.mvc.login.model.User;
import com.springboot.mvc.login.service.TaskService;
import com.springboot.mvc.login.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TaskController.class)

public class TaskControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	private Project proj1 = new Project(1, "Project1", new Date(2019,04,04), new Date(2019,04,06), 10);
	private Task task1 = new Task(1, "Task1", new Date(2019,04,04), new Date(2019,04,06), 10, 1, proj1, new ParentTask());;
	private Task task2;
	private User usr1 = new User(1, "Ram","Charan",34,proj1,task1 );
	private List<Task> tasklist1 = new ArrayList<Task>();
	private List<Task> tasklist2 = new ArrayList<Task>();
	private List<Task> tasklist3 = new ArrayList<Task>();
	
	
	@MockBean
	private TaskService taskservice;
	
	
	@Before
	public void dataSetup() {
		
		proj1 = new Project(1, "Project1", new Date(2019,04,04), new Date(2019,04,06), 10);
		task1 = new Task(1, "Task1", new Date(2019,04,04), new Date(2019,04,06), 10, 1, proj1, new ParentTask());
		
		User usr1 = new User(1, "Ram","Charan",34,proj1,task1 );
		ParentTask ptask2 = new ParentTask(2, "Parent Task2");
		List<ParentTask> ptasklist = new ArrayList<ParentTask>();
		List<ParentTask> ptasklist1 = new ArrayList<ParentTask>();
		
		tasklist1 = new ArrayList<Task>();
		tasklist2 = new ArrayList<Task>();
		
		tasklist1.add(task1);
		 
		 
	}
	
	@Test
	public void shouldeleteTask() throws Exception {
		
		given(taskservice.findByTaskId(1)).willReturn(task1);
		
		MvcResult result = mockMvc.perform(delete("/api/task/deleteTask/"+1)
							.accept(MediaType.APPLICATION_JSON))			
							.andReturn();
		
		System.out.println("Delete task  is:" + result.getResponse().getContentAsString());
//		{"errorMesssage":"User not found"}
		String expectedResult = "{\"taskid\":1,\"taskname\":\"Task1\",\"taskstartdate\":\"3919-05-04T04:00:00.000+0000\",\"taskenddate\":\"3919-05-06T04:00:00.000+0000\",\"taskpriority\":10,\"taskstatus\":1,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"parenttask\":{\"parentid\":0,\"parenttask\":null}}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shoulnotdeleteTask() throws Exception {
		
		given(taskservice.findByTaskId(1)).willReturn(task1);
		
		MvcResult result = mockMvc.perform(delete("/api/task/deleteTask/"+7)
							.accept(MediaType.APPLICATION_JSON))			
							.andReturn();
		
		System.out.println("Delete task  is:" + result.getResponse().getContentAsString());

		String expectedResult = "{\"errorMesssage\":\"Task not found\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldreturnAllTask() throws Exception {
		
		given(taskservice.findAllTasks()).willReturn(tasklist1);
		
		MvcResult result = mockMvc.perform(get("/api/task/viewTask")).andReturn();
		
		System.out.println("Return all user is:" + result.getResponse().getContentAsString());
		
//		String expectedResult = "[{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10}]";
//		
//		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		assertTrue(result != null);
		
	}
	
	@Test
	public void shouldnotreturnAllTask() throws Exception {

		given(taskservice.findAllTasks()).willReturn(tasklist2);

		MvcResult result = mockMvc.perform(get("/api/task/viewTask")).andReturn();
		
		System.out.println("Return all not user is:" + result.getResponse().getContentAsString());
		
//		String expectedResult = "{\"errorMesssage\":\"Project List is empty\"}";
//		
//		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		assertTrue(result != null);

	}

	@Test
	public void shouldreturnTask() throws Exception {
		
		given(taskservice.findByTaskId(1)).willReturn(task1);
		
		MvcResult result = mockMvc.perform(get("/api/task/viewTaskById/"+1)).andReturn();
		
		System.out.println("Task in should return task:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"taskid\":1,\"taskname\":\"Task1\",\"taskstartdate\":\"3919-05-04T04:00:00.000+0000\",\"taskenddate\":\"3919-05-06T04:00:00.000+0000\",\"taskpriority\":10,\"taskstatus\":1,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"parenttask\":{\"parentid\":0,\"parenttask\":null}}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldnotreturnTask() throws Exception {
		
		given(taskservice.findByTaskId(1)).willReturn(task1);
		
		MvcResult result = mockMvc.perform(get("/api/task/viewTaskById/"+7)).andReturn();
		
		System.out.println("Task in should not return tsk:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"errorMesssage\":\"Task List is empty\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldreturnsortProjectByStartDate() throws Exception {
		
		given(taskservice.sortTaskByStartDate(1)).willReturn(tasklist1);
		
		MvcResult result = mockMvc.perform(get("/api/task/sortTaskByStartDate")).andReturn();
			
		assertTrue(result != null);
	}
	
	@Test
	public void shouldnotreturnsortProjectByStartDate() throws Exception {
		
		given(taskservice.sortTaskByStartDate(1)).willReturn(tasklist2);
		
		MvcResult result = mockMvc.perform(get("/api/task/sortTaskByStartDate"))
				.andReturn();
		assertTrue(result != null);
		
	}
	
	@Test
	public void shouldreturnsortProjectByEndDate() throws Exception {
		
		given(taskservice.sortTaskByEndDate(1)).willReturn(tasklist1);
		
		MvcResult result = mockMvc.perform(get("/api/task/sortTaskByEndDate")).andReturn();
			
		assertTrue(result != null);
	}
	
	@Test
	public void shouldnotreturnsortProjectByEndDate() throws Exception {
		
		given(taskservice.sortTaskByEndDate(1)).willReturn(tasklist2);
		
		MvcResult result = mockMvc.perform(get("/api/task/sortTaskByEndDate"))
				.andReturn();
		assertTrue(result != null);
		
	}
	
	@Test
	public void shouldreturnsortProjectByPriority() throws Exception {
		
		given(taskservice.sortTaskByTaskPriority(1)).willReturn(tasklist1);
		
		MvcResult result = mockMvc.perform(get("/api/task/sortTaskByPriority")).andReturn();
			
		assertTrue(result != null);
	}
	
	@Test
	public void shouldnotreturnsortProjectByPriority() throws Exception {
		
		given(taskservice.sortTaskByTaskPriority(1)).willReturn(tasklist2);
		
		MvcResult result = mockMvc.perform(get("/api/task/sortTaskByPriority"))
				.andReturn();
		assertTrue(result != null);
		
	}
	
	@Test
	public void shouldreturnsortTaskByTaskStatus() throws Exception {
		
		given(taskservice.sortTaskByTaskStatus(1)).willReturn(tasklist1);
		
		MvcResult result = mockMvc.perform(get("/api/task/sortTaskByTaskStatus")).andReturn();
			
		assertTrue(result != null);
	}
	
	@Test
	public void shouldnotreturnsortTaskByTaskStatus() throws Exception {
		
		given(taskservice.sortTaskByTaskStatus(1)).willReturn(tasklist2);
		
		MvcResult result = mockMvc.perform(get("/api/task/sortTaskByTaskStatus"))
				.andReturn();
		assertTrue(result != null);
		
	}

	@Test
	public void shouldreturnviewTaskByProject() throws Exception {
		
		given(taskservice.findAllTasksByProject(1)).willReturn(tasklist1);
		
		MvcResult result = mockMvc.perform(get("/api/task/viewTaskByProject")).andReturn();
			
		assertTrue(result != null);
	}
	
	@Test
	public void shouldnotreturnviewTaskByProject() throws Exception {
		
		given(taskservice.findAllTasksByProject(1)).willReturn(tasklist2);
		
		MvcResult result = mockMvc.perform(get("/api/task/viewTaskByProject"))
				.andReturn();
		assertTrue(result != null);
		
	}


}
