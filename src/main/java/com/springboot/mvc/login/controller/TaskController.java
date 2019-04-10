package com.springboot.mvc.login.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mvc.login.model.ErrorBean;
import com.springboot.mvc.login.model.Task;
import com.springboot.mvc.login.service.TaskService;

@RestController
@RequestMapping(value="/api/task")
public class TaskController {
	
	@Autowired
	TaskService taskservice;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@RequestMapping(value="/addTask", method=RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody Task task){
		
		taskservice.saveTask(task);
		
		return new ResponseEntity<Task> (task, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/deleteTask/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteTask(@PathVariable("id") long id){
		
		Task task = taskservice.findByTaskId(id);
		
		if (task==null) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Task not found"), HttpStatus.NO_CONTENT);
		}
		else {
			taskservice.deleteTask(id);
			return new ResponseEntity<Task> (task, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/viewTask", method=RequestMethod.GET)
	public ResponseEntity<?> viewTask(){
		
		List<Task> tasklist = taskservice.findAllTasks();
		
		if (tasklist.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Task List is empty"), HttpStatus.NO_CONTENT); 
		}
		else {
			return new ResponseEntity<List<Task>> (tasklist, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/viewTaskById/{taskid}", method=RequestMethod.GET)
	public ResponseEntity<?> viewTaskById(@PathVariable("taskid") long taskid){
		
		Task task = taskservice.findByTaskId(taskid);
		
		if (task==null) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Task List is empty"), HttpStatus.NO_CONTENT); 
		}
		else {
			return new ResponseEntity<Task> (task, HttpStatus.OK);
		}	
	}
	
	@RequestMapping(value="/updateTaskByProject/{projectid}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateTaskByProject(@PathVariable long projectid){
		
		int updatedcount = taskservice.updateTaskByProject(projectid);
		
		if (updatedcount==0) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Task not found"), HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<String> (Integer.toString(updatedcount), HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/sortTaskByStartDate/{projectid}", method=RequestMethod.GET)
	public ResponseEntity<?> sortTaskByStartDate(@PathVariable("projectid") long projectid){
		
		List<Task> taskList = taskservice.sortTaskByStartDate(projectid);
		
		if (taskList.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Task List is empty"), HttpStatus.NO_CONTENT); 
		}
		else {
			return new ResponseEntity<List<Task>> (taskList, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/sortTaskByEndDate/{projectid}", method=RequestMethod.GET)
	public ResponseEntity<?> sortTaskByEndDate(@PathVariable("projectid") long projectid){
		
		List<Task> taskList = taskservice.sortTaskByEndDate(projectid);
		
		if (taskList.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Task List is empty"), HttpStatus.NO_CONTENT); 
		}
		else {
			return new ResponseEntity<List<Task>> (taskList, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/sortTaskByPriority/{projectid}", method=RequestMethod.GET)
	public ResponseEntity<?> sortTaskByPriority(@PathVariable("projectid") long projectid){
		
		List<Task> taskList = taskservice.sortTaskByTaskPriority(projectid);
		
		if (taskList.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Task List is empty"), HttpStatus.NO_CONTENT); 
		}
		else {
			return new ResponseEntity<List<Task>> (taskList, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/sortTaskByTaskStatus/{projectid}", method=RequestMethod.GET)
	public ResponseEntity<?> sortTaskByTaskStatus(@PathVariable("projectid") long projectid){
		
		List<Task> taskList = taskservice.sortTaskByTaskStatus(projectid);
		
		if (taskList.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Task List is empty"), HttpStatus.NO_CONTENT); 
		}
		else {
			return new ResponseEntity<List<Task>> (taskList, HttpStatus.OK);
		}
		
	}

}
