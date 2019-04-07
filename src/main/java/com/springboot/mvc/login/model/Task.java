package com.springboot.mvc.login.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@SequenceGenerator(name="TASK_SEQ", sequenceName="task_sequence",allocationSize=1)
@Table(name="tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TASK_SEQ")
	@Column(name="task_id")
	long taskid;
	@Column(name="task_name")
	String taskname;
	@Column(name="task_start_date")
	@Temporal(TemporalType.DATE)
	Date taskstartdate;
	@Column(name="task_end_date")
	@Temporal(TemporalType.DATE)
	Date taskenddate;
	@Column(name="task_priority")
	int taskpriority;
	@Column(name="task_status")
	int taskstatus;
	@ManyToOne(cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
	@JoinColumn(name= "project_id", referencedColumnName="project_id")
	Project project;
	@ManyToOne(cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
	@JoinColumn(name= "parent_id", referencedColumnName="parent_id")
	ParentTask parenttask;
	
	
	public Task() {
		
	}


	public Task(long taskid, String taskname, Date taskstartdate, Date taskenddate, int taskpriority, int taskstatus,
			Project project, ParentTask parenttask) {
		super();
		this.taskid = taskid;
		this.taskname = taskname;
		this.taskstartdate = taskstartdate;
		this.taskenddate = taskenddate;
		this.taskpriority = taskpriority;
		this.taskstatus = taskstatus;
		this.project = project;
		this.parenttask = parenttask;
	}


	public long getTaskid() {
		return taskid;
	}


	public void setTaskid(long taskid) {
		this.taskid = taskid;
	}


	public String getTaskname() {
		return taskname;
	}


	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}


	public Date getTaskstartdate() {
		return taskstartdate;
	}


	public void setTaskstartdate(Date taskstartdate) {
		this.taskstartdate = taskstartdate;
	}


	public Date getTaskenddate() {
		return taskenddate;
	}


	public void setTaskenddate(Date taskenddate) {
		this.taskenddate = taskenddate;
	}


	public int getTaskpriority() {
		return taskpriority;
	}


	public void setTaskpriority(int taskpriority) {
		this.taskpriority = taskpriority;
	}


	public int getTaskstatus() {
		return taskstatus;
	}


	public void setTaskstatus(int taskstatus) {
		this.taskstatus = taskstatus;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	public ParentTask getParenttask() {
		return parenttask;
	}


	public void setParenttask(ParentTask parenttask) {
		this.parenttask = parenttask;
	}
	
		

}
