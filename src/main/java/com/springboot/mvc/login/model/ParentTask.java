package com.springboot.mvc.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name="PARENT_SEQ", sequenceName="parent_sequence",allocationSize=1)
@Table(name="parenttasks")
public class ParentTask {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PARENT_SEQ")
	@Column(name="parent_id")
	long parentid;
	@Column(name="parent_task")
	String parenttask;
	
	public ParentTask() {
	}
	
	public ParentTask(long parentid, String parenttask) {
		super();
		this.parentid = parentid;
		this.parenttask = parenttask;
	}

	public long getParentid() {
		return parentid;
	}

	public void setParentid(long parentid) {
		this.parentid = parentid;
	}

	public String getParenttask() {
		return parenttask;
	}

	public void setParenttask(String parenttask) {
		this.parenttask = parenttask;
	}
	
	

}
