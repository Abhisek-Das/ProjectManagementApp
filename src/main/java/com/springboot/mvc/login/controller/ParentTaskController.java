package com.springboot.mvc.login.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parenttask")
public class ParentTaskController {

	@RequestMapping("/")
	public String runApp() {
		return "ParentTask";
	}
	
}
