package com.springboot.mvc.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.mvc.login.model.Project;
import com.springboot.mvc.login.model.User;
import com.springboot.mvc.login.repository.ProjectRepository;
import com.springboot.mvc.login.repository.TaskRepository;
import com.springboot.mvc.login.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userrepository;
	@Autowired
	TaskRepository taskrepository;
	@Autowired
	ProjectRepository projectrepository;
	
	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userrepository.save(user);
	}

	@Override
	public void deleteUser(long userid) {
		// TODO Auto-generated method stub
		userrepository.deleteById(userid);
	}

	@Override
	public void updateUser(long userid) {
		// TODO Auto-generated method stub
		userrepository.save(userrepository.findById(userid).get());
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return userrepository.findAll();
	}

	@Override
	public User findByUserId(long userid) {
		// TODO Auto-generated method stub
		Optional<User> user;
		user = userrepository.findById(userid);
		if (user.isPresent()) {
			return user.get();
		}
		else {
			return null;
		}
		
	}

	@Override
	public List<User> sortByUserFirstNameAsc() {
		// TODO Auto-generated method stub
		return userrepository.findAll(orderByUserFirstNameAsc());
	}

	private Sort orderByUserFirstNameAsc() {
		// TODO Auto-generated method stub
		return new Sort(Sort.Direction.ASC, "userfirstname");
	}

	@Override
	public List<User> sortByUserLastNameAsc() {
		// TODO Auto-generated method stub
		return userrepository.findAll(orderByUserLastNameAsc());
	}

	private Sort orderByUserLastNameAsc() {
		// TODO Auto-generated method stub
		return new Sort(Sort.Direction.ASC, "userlastname");
	}

	@Override
	public List<User> sortByUserEmployeeIDAsc() {
		// TODO Auto-generated method stub
		return userrepository.findAll(orderByUserEmployeeIDAsc());
	}

	private Sort orderByUserEmployeeIDAsc() {
		// TODO Auto-generated method stub
		return new Sort(Sort.Direction.ASC, "useremployeeid");
	}

	@Override
	public User findByProject(Project project) {
		// TODO Auto-generated method stub
		return userrepository.findByProject(project);
	}
	
	@Override
	public User findByProjectId(long projectID) {
		// TODO Auto-generated method stub
		return userrepository.findByProject(projectrepository.findById(projectID).get());
	}

	@Override
	public List<User> findByUseremployeeid(int employeeid) {
		// TODO Auto-generated method stub
		return userrepository.findByUseremployeeid(employeeid);
		
	}

	@Override
	public int updateUserByProjectID(long projectid) {
		// TODO Auto-generated method stub
		return userrepository.updateUserByProjectid(projectid);
	
	}

	@Override
	public User findByTask(long taskid) {
		// TODO Auto-generated method stub
		return userrepository.findByTask(taskrepository.findById(taskid).get());
	}

}
