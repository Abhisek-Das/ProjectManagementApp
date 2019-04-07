package com.springboot.mvc.login.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name="USER_SEQ", sequenceName="user_sequence",allocationSize=1)
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USER_SEQ")
	@Column(name="user_id")
	long userid;
	@Column(name="user_first_name")
	String userfirstname;
	@Column(name="user_last_name")
	String userlastname;
	@Column(name="user_employee_id")
	int useremployeeid;
	@OneToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(name="project_id", referencedColumnName="project_id")
	Project project;
	@OneToOne(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(name="task_id", referencedColumnName="task_id")
	Task task;

	public User() {
		
	}

	public User(long userid, String userfirstname, String userlastname, int useremployeeid, Project project,
			Task task) {
		super();
		this.userid = userid;
		this.userfirstname = userfirstname;
		this.userlastname = userlastname;
		this.useremployeeid = useremployeeid;
		this.project = project;
		this.task = task;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getUserfirstname() {
		return userfirstname;
	}

	public void setUserfirstname(String userfirstname) {
		this.userfirstname = userfirstname;
	}

	public String getUserlastname() {
		return userlastname;
	}

	public void setUserlastname(String userlastname) {
		this.userlastname = userlastname;
	}

	public int getUseremployeeid() {
		return useremployeeid;
	}

	public void setUseremployeeid(int useremployeeid) {
		this.useremployeeid = useremployeeid;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
	}
