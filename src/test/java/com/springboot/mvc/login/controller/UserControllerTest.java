package com.springboot.mvc.login.controller;

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
import com.springboot.mvc.login.model.Task;
import com.springboot.mvc.login.model.User;
import com.springboot.mvc.login.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private Project proj1;
	private Task task1;
	private User usr1 = new User(1, "Ram","Charan",34,proj1,task1 );
	private List<User> userlist1 = new ArrayList<User>();
	private List<User> userlist2 = new ArrayList<User>();
	private List<User> userlist3 = new ArrayList<User>();
	
	
	@MockBean
	private UserService userservice;
	
	
	@Before
	public void dataSetup() {
		
		proj1 = new Project(1, "Project1", new Date(2019,04,04), new Date(2019,04,06), 10);
		task1 = new Task(1, "Task1", new Date(2019,04,04), new Date(2019,04,06), 10, 1, proj1, new ParentTask());
		
		User usr1 = new User(1, "Ram","Charan",34,proj1,task1 );
		ParentTask ptask2 = new ParentTask(2, "Parent Task2");
		List<ParentTask> ptasklist = new ArrayList<ParentTask>();
		List<ParentTask> ptasklist1 = new ArrayList<ParentTask>();
		
		userlist1 = new ArrayList<User>();
		userlist2 = new ArrayList<User>();
		
		userlist1.add(usr1);
		 
		 
	}
	
	@Test
	public void shouldeleteUser() throws Exception {
		
		given(userservice.findByUserId(1)).willReturn(usr1);
		
		MvcResult result = mockMvc.perform(delete("/api/user/deleteUser/"+1)
							.accept(MediaType.APPLICATION_JSON))			
							.andReturn();
		
		System.out.println("Delete user  is:" + result.getResponse().getContentAsString());
//		{"errorMesssage":"User not found"}
		String expectedResult = "{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":null,\"task\":null}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shoulnotdeleteUser() throws Exception {
		
		given(userservice.findByUserId(1)).willReturn(usr1);
		
		MvcResult result = mockMvc.perform(delete("/api/user/deleteUser/"+7)
							.accept(MediaType.APPLICATION_JSON))			
							.andReturn();
		
		System.out.println("Delete user  is:" + result.getResponse().getContentAsString());

		String expectedResult = "{\"errorMesssage\":\"User not found\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shoulupdateUser() throws Exception {
		
		String updateddata = "{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":null,\"task\":null}";
		given(userservice.findByUserId(1)).willReturn(usr1);
		
		MvcResult result = mockMvc.perform(put("/api/user/updateUser/"+1)
							.accept(MediaType.APPLICATION_JSON)
							.content(updateddata).contentType(MediaType.APPLICATION_JSON))		
							.andReturn();
		
		System.out.println("Update user  is:" + result.getResponse().getContentAsString());
		String expectedResult = "{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":null,\"task\":null}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shoulnotupdateUser() throws Exception {
		
		String updateddata = "{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":null,\"task\":null}";
		
		given(userservice.findByUserId(1)).willReturn(usr1);
		
		MvcResult result = mockMvc.perform(put("/api/user/updateUser/"+7)
				.accept(MediaType.APPLICATION_JSON)
				.content(updateddata).contentType(MediaType.APPLICATION_JSON))		
				.andReturn();
		
		System.out.println("Update user  is:" + result.getResponse().getContentAsString());

		String expectedResult = "{\"errorMesssage\":\"User not found\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	

	@Test
	public void shouldreturnAllUsers() throws Exception {
		
		given(userservice.findAllUsers()).willReturn(userlist1);
		
		MvcResult result = mockMvc.perform(get("/api/user/viewUser")).andReturn();
		
		System.out.println("Return all user is:" + result.getResponse().getContentAsString());
		
		String expectedResult = "[{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"task\":{\"taskid\":1,\"taskname\":\"Task1\",\"taskstartdate\":\"3919-05-04T04:00:00.000+0000\",\"taskenddate\":\"3919-05-06T04:00:00.000+0000\",\"taskpriority\":10,\"taskstatus\":1,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"parenttask\":{\"parentid\":0,\"parenttask\":null}}}]";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldnotreturnAllUsers() throws Exception {

		given(userservice.findAllUsers()).willReturn(userlist2);

		MvcResult result = mockMvc.perform(get("/api/user/viewUser")).andReturn();
		
		System.out.println("Return all not user is:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"errorMesssage\":\"User not found\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		

	}
	
	@Test
	public void shouldreturnUser() throws Exception {
		
		given(userservice.findByUserId(1)).willReturn(usr1);
		
		MvcResult result = mockMvc.perform(get("/api/user/viewUserById/"+1)).andReturn();
		
		System.out.println("user in shouldreturnusr:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":null,\"task\":null}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldnotreturnUser() throws Exception {
		
		given(userservice.findByUserId(1)).willReturn(usr1);
		
		MvcResult result = mockMvc.perform(get("/api/user/viewUserById/"+7)).andReturn();
		
		System.out.println("usr in shouldnotreturnusr:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"errorMesssage\":\"User not found\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldreturnUserByEmplID() throws Exception {
		
		given(userservice.findByUseremployeeid(34)).willReturn(userlist1);
		
		MvcResult result = mockMvc.perform(get("/api/user/viewUserByEmployeeId/"+34)).andReturn();
		
		System.out.println("user in shouldreturnusrEmplID:" + result.getResponse().getContentAsString());
		
		String expectedResult = "[{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"task\":{\"taskid\":1,\"taskname\":\"Task1\",\"taskstartdate\":\"3919-05-04T04:00:00.000+0000\",\"taskenddate\":\"3919-05-06T04:00:00.000+0000\",\"taskpriority\":10,\"taskstatus\":1,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"parenttask\":{\"parentid\":0,\"parenttask\":null}}}]";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldnotreturnUserByEmplID() throws Exception {
		String updateddata = "[{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":null,\"task\":null}]";
		
		given(userservice.findByUseremployeeid(34)).willReturn(userlist1);
		
		MvcResult result = mockMvc.perform(get("/api/user/viewUserByEmployeeId/"+700)
				.accept(MediaType.APPLICATION_JSON)
				.content(updateddata).contentType(MediaType.APPLICATION_JSON))		
				.andReturn();
		
		System.out.println("user in shouldnot return usrEmplID:" + result.getResponse().getContentAsString());
		
		String expectedResult = "[]";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldreturnUsersortByFirstName() throws Exception {
		
		given(userservice.sortByUserFirstNameAsc()).willReturn(userlist1);
		
		MvcResult result = mockMvc.perform(get("/api/user/sortUserByFirstName/")).andReturn();
		
		System.out.println("user in shouldreturnusrEmplID:" + result.getResponse().getContentAsString());
		
		String expectedResult = "[{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"task\":{\"taskid\":1,\"taskname\":\"Task1\",\"taskstartdate\":\"3919-05-04T04:00:00.000+0000\",\"taskenddate\":\"3919-05-06T04:00:00.000+0000\",\"taskpriority\":10,\"taskstatus\":1,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"parenttask\":{\"parentid\":0,\"parenttask\":null}}}]";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldnotreturnUsersortByFirstName() throws Exception {
		String updateddata = "[{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":null,\"task\":null}]";
		
		given(userservice.sortByUserFirstNameAsc()).willReturn(userlist1);
		
		MvcResult result = mockMvc.perform(get("/api/user/sortUserByFirstName/")
				.accept(MediaType.APPLICATION_JSON)
				.content(updateddata).contentType(MediaType.APPLICATION_JSON))		
				.andReturn();
		
		System.out.println("user in shouldnot return SORT:" + result.getResponse().getContentAsString());
		
		String expectedResult = "[{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"task\":{\"taskid\":1,\"taskname\":\"Task1\",\"taskstartdate\":\"3919-05-04T04:00:00.000+0000\",\"taskenddate\":\"3919-05-06T04:00:00.000+0000\",\"taskpriority\":10,\"taskstatus\":1,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"parenttask\":{\"parentid\":0,\"parenttask\":null}}}]";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldreturnUsersortByLastName() throws Exception {
		
		given(userservice.sortByUserLastNameAsc()).willReturn(userlist1);
		
		MvcResult result = mockMvc.perform(get("/api/user/sortUserByLastName/")).andReturn();
		
		System.out.println("user in shouldreturnusrEmplID:" + result.getResponse().getContentAsString());
		
		String expectedResult = "[{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"task\":{\"taskid\":1,\"taskname\":\"Task1\",\"taskstartdate\":\"3919-05-04T04:00:00.000+0000\",\"taskenddate\":\"3919-05-06T04:00:00.000+0000\",\"taskpriority\":10,\"taskstatus\":1,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"parenttask\":{\"parentid\":0,\"parenttask\":null}}}]";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldnotreturnUsersortByLastName() throws Exception {
		String updateddata = "[{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":null,\"task\":null}]";
		
		given(userservice.sortByUserLastNameAsc()).willReturn(userlist1);
		
		MvcResult result = mockMvc.perform(get("/api/user/sortUserByLastName/")
				.accept(MediaType.APPLICATION_JSON)
				.content(updateddata).contentType(MediaType.APPLICATION_JSON))		
				.andReturn();
		
		System.out.println("user in shouldnot return SORT:" + result.getResponse().getContentAsString());
		
		String expectedResult = "[{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"task\":{\"taskid\":1,\"taskname\":\"Task1\",\"taskstartdate\":\"3919-05-04T04:00:00.000+0000\",\"taskenddate\":\"3919-05-06T04:00:00.000+0000\",\"taskpriority\":10,\"taskstatus\":1,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"parenttask\":{\"parentid\":0,\"parenttask\":null}}}]";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldreturnUsersortByEmplID() throws Exception {
		
		given(userservice.sortByUserEmployeeIDAsc()).willReturn(userlist1);
		
		MvcResult result = mockMvc.perform(get("/api/user/sortUserByEmployeeID/")).andReturn();
		
		System.out.println("user in shouldreturnusrEmplID:" + result.getResponse().getContentAsString());
		
		String expectedResult = "[{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"task\":{\"taskid\":1,\"taskname\":\"Task1\",\"taskstartdate\":\"3919-05-04T04:00:00.000+0000\",\"taskenddate\":\"3919-05-06T04:00:00.000+0000\",\"taskpriority\":10,\"taskstatus\":1,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"parenttask\":{\"parentid\":0,\"parenttask\":null}}}]";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldnotreturnUsersortByEmplID() throws Exception {
		String updateddata = "[{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":null,\"task\":null}]";
		
		given(userservice.sortByUserEmployeeIDAsc()).willReturn(userlist1);
		
		MvcResult result = mockMvc.perform(get("/api/user/sortUserByEmployeeID/")
				.accept(MediaType.APPLICATION_JSON)
				.content(updateddata).contentType(MediaType.APPLICATION_JSON))		
				.andReturn();
		
		System.out.println("user in shouldnot return SORT:" + result.getResponse().getContentAsString());
		
		String expectedResult = "[{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"task\":{\"taskid\":1,\"taskname\":\"Task1\",\"taskstartdate\":\"3919-05-04T04:00:00.000+0000\",\"taskenddate\":\"3919-05-06T04:00:00.000+0000\",\"taskpriority\":10,\"taskstatus\":1,\"project\":{\"projectid\":1,\"projectname\":\"Project1\",\"projectstartdate\":\"3919-05-04T04:00:00.000+0000\",\"projectenddate\":\"3919-05-06T04:00:00.000+0000\",\"projectpriority\":10},\"parenttask\":{\"parentid\":0,\"parenttask\":null}}}]";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldreturnUserByTaskId() throws Exception {
		
		given(userservice.findByTask(1)).willReturn(usr1);
		
		MvcResult result = mockMvc.perform(get("/api/user/viewUserByTaskId/"+1)).andReturn();
		
		System.out.println("user in shouldreturn TASKid:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":null,\"task\":null}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldnotreturnUserByTaskId() throws Exception {
		
		given(userservice.findByTask(1)).willReturn(usr1);
		
		MvcResult result = mockMvc.perform(get("/api/user/viewUserByTaskId/"+7)).andReturn();
		
		System.out.println("usr in shouldnotreturnusr TASKid:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"errorMesssage\":\"User not found\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldreturnUserByProjectId() throws Exception {
		
		given(userservice.findByProjectId(1)).willReturn(usr1);
		
		MvcResult result = mockMvc.perform(get("/api/user/viewUserByProjectId/"+1)).andReturn();
		
		System.out.println("user in shouldreturn TASKid:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"userid\":1,\"userfirstname\":\"Ram\",\"userlastname\":\"Charan\",\"useremployeeid\":34,\"project\":null,\"task\":null}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void shouldnotreturnUserByProjectId() throws Exception {
		
		given(userservice.findByProjectId(1)).willReturn(usr1);
		
		MvcResult result = mockMvc.perform(get("/api/user/viewUserByProjectId/"+7)).andReturn();
		
		System.out.println("usr in shouldnotreturnusr TASKid:" + result.getResponse().getContentAsString());
		
		String expectedResult = "{\"errorMesssage\":\"User not found\"}";
		
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);
		
	}
	
	
}
