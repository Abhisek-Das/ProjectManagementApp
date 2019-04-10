package com.springboot.mvc.login.model;

public class ProjectInfo {
	
	private Project project;
	private int taskcount;
	private String completed;
	private User user;
	
	public ProjectInfo() {
		super();
	}

	public ProjectInfo(Project project, int taskcount, String completed, User user) {
		super();
		this.project = project;
		this.taskcount = taskcount;
		this.completed = completed;
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getTaskcount() {
		return taskcount;
	}

	public void setTaskcount(int taskcount) {
		this.taskcount = taskcount;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
