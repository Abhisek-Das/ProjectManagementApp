package com.springboot.mvc.login.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mvc.login.model.ErrorBean;
import com.springboot.mvc.login.model.ParentTask;
import com.springboot.mvc.login.service.ParentTaskService;


@RestController
@RequestMapping("/api/parenttask")
@CrossOrigin
public class ParentTaskController {

	@Autowired
	ParentTaskService parentTaskService;
	
	@RequestMapping("/")
	public String runApp() {
		return "ParentTask";
	}
	
	@RequestMapping(value="/viewParentTask", method=RequestMethod.GET)
	public ResponseEntity<?> viewParentTask(){
		List<ParentTask> parentTaskList = parentTaskService.findAllParentTasks();
		
		if (parentTaskList.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Parent Task Not Found"), HttpStatus.NO_CONTENT);
		}
		else {
			return  new ResponseEntity<List<ParentTask>> (parentTaskList, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/viewParentTask/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> viewParentTaskById(@PathVariable("id") long id){
		ParentTask parentTask = parentTaskService.findByParentId(id);
		
		if (parentTask == null) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Parent Task Not Found"), HttpStatus.NO_CONTENT);
			
		}
		else {
			return  new ResponseEntity<ParentTask> (parentTask, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/addParentTask", method=RequestMethod.POST)
	public ResponseEntity<?> addParentTask(@RequestBody ParentTask parenttask){
		
		parentTaskService.saveParentTask(parenttask);
//		System.out.println("after save parenttask");
		return new ResponseEntity<ParentTask> (parenttask, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/deleteParentTask/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteParentTask(@PathVariable("id") long id){
		
		ParentTask parenttask = parentTaskService.findByParentId(id);
		if (parenttask == null) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Parent Task Not Found"), HttpStatus.NO_CONTENT);
		}
		else {
			
			parentTaskService.deleteParentTask(id);
			return new ResponseEntity<ParentTask> (parenttask, HttpStatus.OK);
		}
		
	}
	
}
