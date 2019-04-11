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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mvc.login.model.ErrorBean;
import com.springboot.mvc.login.model.Project;
import com.springboot.mvc.login.model.ProjectInfo;
import com.springboot.mvc.login.service.ProjectInfoService;
import com.springboot.mvc.login.service.ProjectService;

@RestController
@RequestMapping("api/project")
@CrossOrigin
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	@Autowired
	ProjectInfoService projectInfoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@RequestMapping(value="/addProject", method=RequestMethod.POST)
	public ResponseEntity<?> addProject(@RequestBody Project project){
		
		projectService.saveProject(project);
		
		return new ResponseEntity<Project> (project, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/deleteProject/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteProject(@PathVariable("id") long id){
		
		Project project = projectService.findByProjectId(id);
		if (project==null) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Project not found"), HttpStatus.NO_CONTENT);
		}
		else {
			
			projectService.deleteProject(id);
			return new ResponseEntity<Project> (project, HttpStatus.OK);
			
		}
		
	}
	
	@RequestMapping(value="/viewProjectById/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> viewProjectById(@PathVariable("id") long id){
		
		Project project = projectService.findByProjectId(id);
		if (project==null) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Project not found"), HttpStatus.NO_CONTENT);
		}
		else {
			
			projectService.findByProjectId(id);
			return new ResponseEntity<Project> (project, HttpStatus.OK);
			
		}
		
	}
	
	@RequestMapping(value="/viewProjectByName/{projectname}", method=RequestMethod.GET)
	public ResponseEntity<?> viewProjectByName(@PathVariable("projectname") String projectname){
		
		Project project = projectService.findByProjectName(projectname);
		if (project==null) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Project not found"), HttpStatus.NO_CONTENT);
		}
		else {
			
			projectService.findByProjectName(projectname);
			return new ResponseEntity<Project> (project, HttpStatus.OK);
			
		}
		
	}
	
	@RequestMapping(value="/viewProject", method=RequestMethod.GET)
	public ResponseEntity<?> viewProject(){
		
		List<Project> projectList = projectService.findAllProjects();
		
		if (projectList.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Project List is empty"), HttpStatus.NO_CONTENT); 
		}
		else {
			return new ResponseEntity<List<Project>> (projectList, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/updateProject/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateProject(@PathVariable("id") long id, @RequestBody Project project){
		
		Project projecttemp = projectService.findByProjectId(id);
		if (projecttemp==null) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Project not found"), HttpStatus.NO_CONTENT);
		}
		else {
			project.setProjectid(id);
			
			projectService.saveProject(project);
			return new ResponseEntity<Project> (project, HttpStatus.OK);
			
		}
		
	}
	
	@RequestMapping(value="/sortProjectByStartDate", method=RequestMethod.GET)
	public ResponseEntity<?> sortProjectByStartDate(){
		
		List<ProjectInfo> projectInfoList = projectInfoService.sortProjectByStartDate();
		
		if (projectInfoList.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Project List is empty"), HttpStatus.NO_CONTENT); 
		}
		else {
			return new ResponseEntity<List<ProjectInfo>> (projectInfoList, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/sortProjectByEndDate", method=RequestMethod.GET)
	public ResponseEntity<?> sortProjectByEndDate(){
		
		List<ProjectInfo> projectInfoList = projectInfoService.sortProjectByEndDate();
		
		if (projectInfoList.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Project List is empty"), HttpStatus.NO_CONTENT); 
		}
		else {
			return new ResponseEntity<List<ProjectInfo>> (projectInfoList, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/sortProjectByPriority", method=RequestMethod.GET)
	public ResponseEntity<?> sortProjectByPriority(){
		
		List<ProjectInfo> projectInfoList = projectInfoService.sortProjectByPriority();
		
		if (projectInfoList.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Project List is empty"), HttpStatus.NO_CONTENT); 
		}
		else {
			return new ResponseEntity<List<ProjectInfo>> (projectInfoList, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/sortProjectByCompleted", method=RequestMethod.GET)
	public ResponseEntity<?> sortProjectByCompleted(){
		
		List<ProjectInfo> projectInfoList = projectInfoService.sortProjectByCompleted();
		
		if (projectInfoList.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Project List is empty"), HttpStatus.NO_CONTENT); 
		}
		else {
			return new ResponseEntity<List<ProjectInfo>> (projectInfoList, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/viewProjectInfo", method=RequestMethod.GET)
	public ResponseEntity<?> viewProjectInfo(){
		
		List<ProjectInfo> projectInfoList = projectInfoService.findAll();
		
		if (projectInfoList.isEmpty()) {
			return new ResponseEntity<ErrorBean> (new ErrorBean("Project Info List is empty"), HttpStatus.NO_CONTENT); 
		}
		else {
			return new ResponseEntity<List<ProjectInfo>> (projectInfoList, HttpStatus.OK);
		}
		
	}

}
