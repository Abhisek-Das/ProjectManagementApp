package com.springboot.mvc.login.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
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
import com.springboot.mvc.login.repository.ParentTaskRepository;
import com.springboot.mvc.login.repository.ProjectRepository;
import com.springboot.mvc.login.repository.UserRepository;

public class UserServiceImplTest extends ProjectManagementAppApplicationTests {

	@InjectMocks
	private UserServiceImpl userservice;
	
	@Mock
	private UserRepository userrepository;
	@Mock
	private ProjectRepository projectRepository;
	private User usr;
	long userid = 1;
	private List<User> userlisttst;
	Optional<User> userOpt;

	@Before
	public void datasetup() {
		
		MockitoAnnotations.initMocks(this);
		
		usr = new User(1, "Ram", "Bose", 007, new Project(),new Task());
		Optional<User> UserOpt = Optional.of(usr);
		userlisttst = new ArrayList<User>();
		userlisttst.add(usr);
//		List<User> UserempOpt = Optional.of(userlist);
		
		Mockito.when(userrepository.save(any())).thenReturn(usr);
		Mockito.when(userrepository.findById(any())).thenReturn(UserOpt);
//		List<User> UserempOpt = null;
		Mockito.when(userrepository.findByUseremployeeid(anyInt())).thenReturn(userlisttst);
		User userlisttst1 = null;
		Mockito.when(userrepository.findByProject(any())).thenReturn(userlisttst1);
		
		
	}
	
	@Test
	public void testsaveUser() {
		usr = new User(2, "Ram", "Dey", 80, new Project(),new Task());
		userservice.saveUser(usr);
		Optional<User> userlist = userrepository.findById((long) 2);
		assertTrue(userlist != null);
	}
	
	@Test
	public void testdeleteUser() {
		userservice.deleteUser(userid);
		
		List<User> userlist = userservice.findByUseremployeeid(007);
		assertTrue(userlist != null);
	}
	
	@Test
	public void testupdateUser() {
		usr = new User(2, "Ram-Upd", "Dey", 80, new Project(),new Task());
		userservice.updateUser(userid);
		Optional<User> userlist = userrepository.findById((long) 1);
		assertTrue(userlist != null);
		
	}
	
	@Test
	public void testfindAllUsers() {
		List<User> userlist = userservice.findAllUsers();
		assertTrue(userlist != null);
		
	}
	
	@Test
	public void testfindByUserId() {
		final User us = userservice.findByUserId(userid);
		assertEquals(1, us.getUserid());
	}
	
	@Test
	public void testsortByUserFirstNameAsc() {
		List<User> uslist = userservice.sortByUserFirstNameAsc();
		assertTrue(uslist != null);
	}
	
	@Test
	public void testsortByUserLastNameAsc() {
		List<User> uslist = userservice.sortByUserLastNameAsc();
		assertTrue(uslist != null);
	}
	
	@Test
	public void testsortByUserEmplIDAsc() {
		List<User> uslist = userservice.sortByUserEmployeeIDAsc();
		assertTrue(uslist != null);
	}
	
//	@Test
//	public void testfindByProjectId() {
//		Project prj = new Project();
////		prj.setProjectid(1);
//		User us = userservice.findByProjectId(prj.getProjectid());
//		assertTrue(us.getProject() != null);
////		assertEquals(1, us.getProject().ge);
//	}
	
//	@Test
//	public void testfindByEmployeeId() {
//		List<User> us = userservice.findByUseremployeeid(007);
//		assertEquals(null, us.get(0).getUserid());
//	}
	
	@Test
	public void testupdateUserByProjectID() {
		usr = new User(2, "Ram-Upd", "Dey", 80, new Project(),new Task());
		int count = userservice.updateUserByProjectID((long) 1);
		Optional<User> userlist = userrepository.findById((long) 1);
		assertTrue(userlist != null);
		
	}
	
	
//	@Test
//	public void testfindByTask() {
//		Task task = new Task();
//		User usr = userrepository.findByTask(task);
//		assertTrue(usr != null);
//	}
	
}
