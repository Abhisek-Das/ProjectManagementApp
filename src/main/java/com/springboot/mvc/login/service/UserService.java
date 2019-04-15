package com.springboot.mvc.login.service;

import java.util.List;

import com.springboot.mvc.login.model.Project;
import com.springboot.mvc.login.model.User;



public interface UserService {
	
	public void saveUser(User user);
	public void deleteUser(long userid);
	public void updateUser(long userid);
	public int updateUserByProjectID(long projectid);
	
	public List<User> findAllUsers();
	public User findByUserId(long userid);
	public User findByProject(Project project);
	public User findByTask(long taskid);
	public List<User> findByUseremployeeid(int employeeid);
	
	public List<User> sortByUserFirstNameAsc();
	public List<User> sortByUserLastNameAsc();
	public List<User> sortByUserEmployeeIDAsc();
	User findByProjectId(long projectID);

}
