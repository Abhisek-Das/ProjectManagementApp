package com.springboot.mvc.login.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.springboot.mvc.login.model.ParentTask;
import com.springboot.mvc.login.service.ParentTaskService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ParentTaskController.class)
public class ParentTaskControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ParentTaskService parentTaskService;
	
	private ParentTask ptask1 = new ParentTask(1, "Parent Task1");
	private ParentTask ptask2 = new ParentTask(2, "Parent Task2");
	private List<ParentTask> ptasklist = new ArrayList<ParentTask>();
	private List<ParentTask> ptasklist1 = new ArrayList<ParentTask>();
	
	
	@Before
	public void datasetUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		
		ptask1 = new ParentTask(1, "Parent Task1");
		ptask2 = new ParentTask(2, "Parent Task2");
		ptasklist = new ArrayList<ParentTask>();
		ptasklist.add(ptask1);
		ptasklist.add(ptask2);
		
		System.out.println("ptaskl: "+ptasklist);
		
	}
	
	@Test
	public void shouldreturnAllParentTask() throws Exception {
		
		given(parentTaskService.findAllParentTasks()).willReturn(ptasklist);
		System.out.println("ptaskl inside finall: "+ptasklist);
		
		MvcResult result = mockMvc.perform(get("/api/parenttask/viewParentTask")).andReturn();
		
		System.out.println("TaskList is:" + result.getResponse().getContentAsString());
		
		String expectedResult = "[{\"parentid\":1,\"parenttask\":\"Parent Task1\"},{\"parentid\":2,\"parenttask\":\"Parent Task2\"}]";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldnotreturnAllParentTask() throws Exception {
		
		given(parentTaskService.findAllParentTasks()).willReturn(ptasklist1);
		
		MvcResult result = mockMvc.perform(get("/api/parenttask/viewParentTask")).andReturn();
		
		System.out.println("TaskList is:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"errorMesssage\":\"Parent Task Not Found\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldreturnParentTask() throws Exception {
		
		given(parentTaskService.findByParentId(1)).willReturn(ptask1);
		
		MvcResult result = mockMvc.perform(get("/api/parenttask/viewParentTask/"+1)).andReturn();
		
		System.out.println("TaskList in shouldreturnParentTask:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"parentid\":1,\"parenttask\":\"Parent Task1\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldnotreturnParentTask() throws Exception {
		
		given(parentTaskService.findByParentId(1)).willReturn(ptask1);
		
		MvcResult result = mockMvc.perform(get("/api/parenttask/viewParentTask/"+7)).andReturn();
		
		System.out.println("TaskList in shouldreturnParentTask:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"errorMesssage\":\"Parent Task Not Found\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldeleteParentTask() throws Exception {
		
		given(parentTaskService.findByParentId(1)).willReturn(ptask1);
		
		MvcResult result = mockMvc.perform(delete("/api/parenttask/deleteParentTask/"+1)
							.accept(MediaType.APPLICATION_JSON))			
							.andReturn();
		
		System.out.println("Delete parent taskList is:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"parentid\":1,\"parenttask\":\"Parent Task1\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldnoteleteParentTask() throws Exception {
		
//		given(parentTaskService.findByParentId(1)).willReturn(ptask1);
		
		MvcResult result = mockMvc.perform(delete("/api/parenttask/deleteParentTask/"+7)
							.accept(MediaType.APPLICATION_JSON))			
							.andReturn();
		
		System.out.println("Not Delete parent taskList is:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"errorMesssage\":\"Parent Task Not Found\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	
}
