package com.springboot.mvc.login.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.mvc.login.model.Project;
import com.springboot.mvc.login.model.ProjectInfo;
import com.springboot.mvc.login.model.User;
import com.springboot.mvc.login.repository.ProjectRepository;
import com.springboot.mvc.login.repository.TaskRepository;
import com.springboot.mvc.login.repository.UserRepository;

@Service
public class ProjectInfoServiceImpl implements ProjectInfoService {

	@Autowired
	ProjectService projectservice;
	@Autowired
	UserService userservice;
	@Autowired
	TaskRepository taskrepository;
	@Autowired
	ProjectRepository projectrepository;
	@Autowired
	UserRepository userrepository;
	
	
	@Override
	public List<ProjectInfo> findAll() {
		// TODO Auto-generated method stub
		List<ProjectInfo> projectInfo = new ArrayList<ProjectInfo>();
		List<Project> projectlist = projectrepository.findAll();
		
//		System.out.println("ProjList: "+projectlist);
		
		Iterator<Project> projcount = projectlist.iterator();
		
		while (projcount.hasNext()) {
			Project project = projcount.next();
//			System.out.println("Project: "+project);
			
			User user = userrepository.findByProject(project);
			int taskcount = taskrepository.countByProject(project);
			int notcompleted = taskrepository.countByProjectAndTaskstatus(project, 1);
			String completed = "N";
			if (notcompleted==0) {
				completed = "Y";
			}
			projectInfo.add(new ProjectInfo(project, taskcount, completed, user));
//			System.out.println("ProjectInfo: "+projectInfo);
		}
		
		return projectInfo;
	}

	@Override
	public List<ProjectInfo> sortProjectByStartDate() {
		// TODO Auto-generated method stub
		List<ProjectInfo> projectInfo = new ArrayList<ProjectInfo>();
		List<Project> projectlist = projectrepository.findAll(orderByProjectStartdateAsc());
		
		Iterator<Project> projcount = projectlist.iterator();
		
		while (projcount.hasNext()) {
			Project project = projcount.next();
			
			User user = userrepository.findByProject(project);
			int taskcount = taskrepository.countByProject(project);
			int notcompleted = taskrepository.countByProjectAndTaskstatus(project, 1);
			String completed = "N";
			if (notcompleted==0) {
				completed = "Y";
			}
			projectInfo.add(new ProjectInfo(project, taskcount, completed, user));
		}
		
		return projectInfo;
	}

	private Sort orderByProjectStartdateAsc() {
		// TODO Auto-generated method stub
		return new Sort(Sort.Direction.ASC, "projectstartdate");
	}

	@Override
	public List<ProjectInfo> sortProjectByEndDate() {
		// TODO Auto-generated method stub
		List<ProjectInfo> projectInfo = new ArrayList<ProjectInfo>();
		List<Project> projectlist = projectrepository.findAll(orderByProjectEnddateAsc());
		
		Iterator<Project> projcount = projectlist.iterator();
		
		while (projcount.hasNext()) {
			Project project = projcount.next();
			
			User user = userrepository.findByProject(project);
			int taskcount = taskrepository.countByProject(project);
			int notcompleted = taskrepository.countByProjectAndTaskstatus(project, 1);
			String completed = "N";
			if (notcompleted==0) {
				completed = "Y";
			}
			projectInfo.add(new ProjectInfo(project, taskcount, completed, user));
		}
		
		return projectInfo;
	}

	private Sort orderByProjectEnddateAsc() {
		// TODO Auto-generated method stub
		return new Sort(Sort.Direction.ASC, "projectenddate");
	}

	@Override
	public List<ProjectInfo> sortProjectByPriority() {
		// TODO Auto-generated method stub
		List<ProjectInfo> projectInfo = new ArrayList<ProjectInfo>();
		List<Project> projectlist = projectrepository.findAll(orderByProjectPriorityAsc());
		
		Iterator<Project> projcount = projectlist.iterator();
		
		while (projcount.hasNext()) {
			Project project = projcount.next();
			
			User user = userrepository.findByProject(project);
			int taskcount = taskrepository.countByProject(project);
			int notcompleted = taskrepository.countByProjectAndTaskstatus(project, 1);
			String completed = "N";
			if (notcompleted==0) {
				completed = "Y";
			}
			projectInfo.add(new ProjectInfo(project, taskcount, completed, user));
		}
		
		return projectInfo;
	}

	private Sort orderByProjectPriorityAsc() {
		// TODO Auto-generated method stub
		return new Sort(Sort.Direction.ASC, "projectpriority");
	}

	@Override
	public List<ProjectInfo> sortProjectByCompleted() {
		// TODO Auto-generated method stub
		List<ProjectInfo> projectinfo = findAll();
		List<ProjectInfo> projectinfoSorted = projectinfo.stream().sorted(Comparator.comparing(ProjectInfo::getCompleted)).collect(Collectors.toList());
		
		
		return projectinfoSorted;
	}

}
