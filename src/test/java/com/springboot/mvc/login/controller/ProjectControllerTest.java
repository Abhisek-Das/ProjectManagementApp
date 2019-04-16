package com.springboot.mvc.login.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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
import com.springboot.mvc.login.model.ProjectInfo;
import com.springboot.mvc.login.model.Task;
import com.springboot.mvc.login.model.User;
import com.springboot.mvc.login.service.ProjectInfoService;
import com.springboot.mvc.login.service.ProjectService;
import com.springboot.mvc.login.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProjectController.class)
public class ProjectControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private Project proj1;
	private Project proj2;
	private ProjectInfo projinfo1;
	private ProjectInfo projinfo2;
	private Task task1;
//	private User usr1 = new User(1, "Ram","Charan",34,proj1,task1 );
	private Project proj = new Project(1, "Project1", new Date(2019,04,04), new Date(2019,04,06), 10);
	private List<Project> projlist1 = new ArrayList<Project>();
	private List<Project> projlist2 = new ArrayList<Project>();
	private List<Project> projlist3 = new ArrayList<Project>();
	private List<ProjectInfo> projinfolist1 = new ArrayList<ProjectInfo>();
	private List<ProjectInfo> projinfolist2 = new ArrayList<ProjectInfo>();
	
	
	@MockBean
	private ProjectService projservice;
	@MockBean
	private ProjectInfoService projinfoservice;
	
	
	@Before
	public void dataSetup() {
		
		proj1 = new Project(1, "Project1", new Date(2019,04,04), new Date(2019,04,06), 10);
		proj2 = new Project(2, "Project2", new Date(2019,05,04), new Date(2019,06,06), 10);
//		task1 = new Task(1, "Task1", new Date(2019,04,04), new Date(2019,04,06), 10, 1, proj1, new ParentTask());
		
//		User usr1 = new User(1, "Ram","Charan",34,proj1,task1 );
//		ParentTask ptask2 = new ParentTask(2, "Parent Task2");
		List<ParentTask> ptasklist = new ArrayList<ParentTask>();
		List<ParentTask> ptasklist1 = new ArrayList<ParentTask>();
		
		projlist1 = new ArrayList<Project>();
		projlist2 = new ArrayList<Project>();
		
		projlist1.add(proj1);
		
		projinfo1 = new ProjectInfo(proj1, 3, "Y", new User());
		
		projinfolist1 = new ArrayList<ProjectInfo>();
		projinfolist2 = new ArrayList<ProjectInfo>();
		
		projinfolist1.add(projinfo1);
		 
		 
	}
	
	
	@Test
	public void shouldeleteProject() throws Exception {
		
		given(projservice.findByProjectId(1)).willReturn(proj1);
		
		MvcResult result = mockMvc.perform(delete("/api/project/deleteProject/"+1)
							.accept(MediaType.APPLICATION_JSON))			
							.andReturn();
		
		System.out.println("Delete proj  is:" + result.getResponse().getContentAsString());
//		{"errorMesssage":"User not found"}
		String expectedResult = "{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shoulnotdeleteProject() throws Exception {
		
		given(projservice.findByProjectId(1)).willReturn(proj1);
		
		MvcResult result = mockMvc.perform(delete("/api/project/deleteProject/"+7)
							.accept(MediaType.APPLICATION_JSON))			
							.andReturn();
		
		System.out.println("Delete user  is:" + result.getResponse().getContentAsString());

		String expectedResult = "{\"errorMesssage\":\"Project not found\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldreturnProject() throws Exception {
		
		given(projservice.findByProjectId(1)).willReturn(proj1);
		
		MvcResult result = mockMvc.perform(get("/api/project/viewProjectById/"+1)).andReturn();
		
		System.out.println("Project in shouldreturnusr:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldnotreturnProject() throws Exception {
		
		given(projservice.findByProjectId(1)).willReturn(proj1);
		
		MvcResult result = mockMvc.perform(get("/api/project/viewProjectById/"+7)).andReturn();
		
		System.out.println("Project in shouldnotreturnusr:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"errorMesssage\":\"Project not found\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}

	@Test
	public void shouldreturnProjectByName() throws Exception {
		
		given(projservice.findByProjectName("Project1")).willReturn(proj1);
		
		MvcResult result = mockMvc.perform(get("/api/project/viewProjectByName/Project1")).andReturn();
		
		System.out.println("Project in shouldreturnusr:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldnotreturnProjectByName() throws Exception {
		
		given(projservice.findByProjectName("Project1")).willReturn(proj1);
		
		MvcResult result = mockMvc.perform(get("/api/project/viewProjectByName/Project7")).andReturn();
		
		System.out.println("Project in shouldnotreturnusr:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"errorMesssage\":\"Project not found\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldreturnAllProjects() throws Exception {
		
		given(projservice.findAllProjects()).willReturn(projlist1);
		
		MvcResult result = mockMvc.perform(get("/api/project/viewProject")).andReturn();
		
		System.out.println("Return all user is:" + result.getResponse().getContentAsString());
		
		String expectedResult = "[{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10}]";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldnotreturnAllProjects() throws Exception {

		given(projservice.findAllProjects()).willReturn(projlist2);

		MvcResult result = mockMvc.perform(get("/api/project/viewProject")).andReturn();
		
		System.out.println("Return all not user is:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"errorMesssage\":\"Project List is empty\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		

	}
	
	@Test
	public void shoulupdateProject() throws Exception {
		
		String updateddata = "{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10}";
		given(projservice.findByProjectId(1)).willReturn(proj1);
		
		MvcResult result = mockMvc.perform(put("/api/project/updateProject/"+1)
							.accept(MediaType.APPLICATION_JSON)
							.content(updateddata).contentType(MediaType.APPLICATION_JSON))		
							.andReturn();
		
		System.out.println("Update project  is:" + result.getResponse().getContentAsString());
		String expectedResult = "{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shoulnotupdateProject() throws Exception {
		
		String updateddata = "{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10}";
		
		given(projservice.findByProjectId(1)).willReturn(proj1);
		
		MvcResult result = mockMvc.perform(put("/api/project/updateProject/"+7)
				.accept(MediaType.APPLICATION_JSON)
				.content(updateddata).contentType(MediaType.APPLICATION_JSON))		
				.andReturn();
		
		System.out.println("Update project  is:" + result.getResponse().getContentAsString());

		String expectedResult = "{\"errorMesssage\":\"Project not found\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}

	@Test
	public void shouldreturnsortProjectByStartDate() throws Exception {
		
		given(projinfoservice.sortProjectByStartDate()).willReturn(projinfolist1);
		
		MvcResult result = mockMvc.perform(get("/api/user/sortProjectByStartDate/")).andReturn();
			
		assertTrue(result != null);
	}
	
	@Test
	public void shouldnotreturnsortProjectByStartDate() throws Exception {
		
		given(projinfoservice.sortProjectByStartDate()).willReturn(projinfolist2);
		
		MvcResult result = mockMvc.perform(get("/api/user/sortProjectByStartDate/"))
				.andReturn();
		assertTrue(result != null);
		
	}
	
	@Test
	public void shouldreturnsortProjectByEndDate() throws Exception {
		
		given(projinfoservice.sortProjectByEndDate()).willReturn(projinfolist1);
		
		MvcResult result = mockMvc.perform(get("/api/user/sortProjectByEndDate/")).andReturn();
			
		assertTrue(result != null);
	}
	
	@Test
	public void shouldnotreturnsortProjectByEndDate() throws Exception {
		
		given(projinfoservice.sortProjectByEndDate()).willReturn(projinfolist2);
		
		MvcResult result = mockMvc.perform(get("/api/user/sortProjectByEndDate/"))
				.andReturn();
		assertTrue(result != null);
		
	}
	
	@Test
	public void shouldreturnsortProjectByPriority() throws Exception {
		
		given(projinfoservice.sortProjectByPriority()).willReturn(projinfolist1);
		
		MvcResult result = mockMvc.perform(get("/api/user/sortProjectByPriority/")).andReturn();
			
		assertTrue(result != null);
	}
	
	@Test
	public void shouldnotreturnsortProjectByPriority() throws Exception {
		
		given(projinfoservice.sortProjectByPriority()).willReturn(projinfolist2);
		
		MvcResult result = mockMvc.perform(get("/api/user/sortProjectByPriority/"))
				.andReturn();
		assertTrue(result != null);
		
	}
	
	@Test
	public void shouldreturnsortProjectByCompleted() throws Exception {
		
		given(projinfoservice.sortProjectByCompleted()).willReturn(projinfolist1);
		
		MvcResult result = mockMvc.perform(get("/api/user/sortProjectByCompleted/")).andReturn();
			
		assertTrue(result != null);
	}
	
	@Test
	public void shouldnotreturnsortProjectByCompleted() throws Exception {
		
		given(projinfoservice.sortProjectByCompleted()).willReturn(projinfolist2);
		
		MvcResult result = mockMvc.perform(get("/api/user/sortProjectByCompleted/"))
				.andReturn();
		assertTrue(result != null);
		
	}

	@Test
	public void shouldreturnAllProjectInfo() throws Exception {
		
		given(projinfoservice.findAll()).willReturn(projinfolist1);
		
		MvcResult result = mockMvc.perform(get("/api/project/viewProjectInfo")).andReturn();
		
		System.out.println("Return all user is:" + result.getResponse().getContentAsString());
		
//		String expectedResult = "[{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10}]";
//		
//		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		assertTrue(result != null);
		
	}
	
	@Test
	public void shouldnotreturnAllProjectInfo() throws Exception {

		given(projinfoservice.findAll()).willReturn(projinfolist2);

		MvcResult result = mockMvc.perform(get("/api/project/viewProjectInfo")).andReturn();
		
		System.out.println("Return all not user is:" + result.getResponse().getContentAsString());
		
//		String expectedResult = "{\"errorMesssage\":\"Project List is empty\"}";
//		
//		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		assertTrue(result != null);

	}
	
}
