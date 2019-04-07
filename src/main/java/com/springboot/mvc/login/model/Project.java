package com.springboot.mvc.login.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="PROJECT_SEQ", sequenceName="project_sequence",allocationSize=1)
@Table(name="projects")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PROJECT_SEQ")
	@Column(name="project_id")
	long projectid;
	@Column(name="project_name")
	String projectname;
	@Column(name="project_start_date")
	@Temporal(TemporalType.DATE)
	Date projectstartdate;
	@Column(name="project_end_date")
	@Temporal(TemporalType.DATE)
	Date projectenddate;
	@Column(name="project_priority")
	int projectpriority;

	public Project() {
		
	}

	public Project(long projectid, String projectname, Date projectstartdate, Date projectenddate,
			int projectpriority) {
		super();
		this.projectid = projectid;
		this.projectname = projectname;
		this.projectstartdate = projectstartdate;
		this.projectenddate = projectenddate;
		this.projectpriority = projectpriority;
	}

	public long getProjectid() {
		return projectid;
	}

	public void setProjectid(long projectid) {
		this.projectid = projectid;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public Date getProjectstartdate() {
		return projectstartdate;
	}

	public void setProjectstartdate(Date projectstartdate) {
		this.projectstartdate = projectstartdate;
	}

	public Date getProjectenddate() {
		return projectenddate;
	}

	public void setProjectenddate(Date projectenddate) {
		this.projectenddate = projectenddate;
	}

	public int getProjectpriority() {
		return projectpriority;
	}

	public void setProjectpriority(int projectpriority) {
		this.projectpriority = projectpriority;
	}

		
	
}
