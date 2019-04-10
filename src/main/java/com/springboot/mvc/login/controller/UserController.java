package com.springboot.mvc.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mvc.login.model.ErrorBean;
import com.springboot.mvc.login.model.User;
import com.springboot.mvc.login.service.UserService;

@RestController
@RequestMapping(value="/api/user")
public class UserController {
	
	@Autowired
	UserService userservice;
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody User user){
//		System.out.println("User:"+user);
		userservice.saveUser(user);
		
		return new ResponseEntity<User> (user, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/deleteUser/{id}", method=RequestMethod.POST)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id){
		User user = userservice.findByUserId(id);
		
		if (user==null) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("User not found"), HttpStatus.NO_CONTENT);
		}
		else {
			userservice.deleteUser(id);
			
			return new ResponseEntity<User> (user, HttpStatus.OK);
			
		}
		
	}
	
	@RequestMapping(value="/updateUser/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user){
		User userold = userservice.findByUserId(id);
		
		if (userold==null) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("User not found"), HttpStatus.NO_CONTENT);
		}
		else {
			user.setUserid(id);
			userservice.saveUser(user);
			
			return new ResponseEntity<User> (user, HttpStatus.OK);
			
		}
		
	}
	
	@RequestMapping(value="/viewUser", method=RequestMethod.GET)
	public ResponseEntity<?> viewUser(){
		List<User> user = userservice.findAllUsers();
		
		if (user.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("User not found"), HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<List<User>> (user, HttpStatus.OK);
			
		}
		
	}
	
	@RequestMapping(value="/viewUserById/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> viewUserById(@PathVariable("id") long id){
		User user = userservice.findByUserId(id);
		
		if (user==null) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("User not found"), HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<User> (user, HttpStatus.OK);
			
		}
		
	}
	
	@RequestMapping(value="/sortUserByFirstName", method=RequestMethod.GET)
	public ResponseEntity<?> sortByUserFirstName(){
		List<User> userlistsorted = userservice.sortByUserFirstNameAsc();
		if (userlistsorted.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("User not found"), HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<List<User>> (userlistsorted, HttpStatus.OK);
			
		}
		
	}
	
	@RequestMapping(value="/sortUserByLastName", method=RequestMethod.GET)
	public ResponseEntity<?> sortByUserLastName(){
		List<User> userlistsorted = userservice.sortByUserLastNameAsc();
		if (userlistsorted.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("User not found"), HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<List<User>> (userlistsorted, HttpStatus.OK);
			
		}
		
	}
	
	@RequestMapping(value="/sortUserByEmployeeID", method=RequestMethod.GET)
	public ResponseEntity<?> sortByEmployeeID(){
		List<User> userlistsorted = userservice.sortByUserEmployeeIDAsc();
		if (userlistsorted.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("User not found"), HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<List<User>> (userlistsorted, HttpStatus.OK);
			
		}
		
	}

}
