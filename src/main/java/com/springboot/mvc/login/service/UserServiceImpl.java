package com.springboot.mvc.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.springboot.mvc.login.model.User;
import com.springboot.mvc.login.repository.UserRepository;

public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userrepository;
	
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

}
