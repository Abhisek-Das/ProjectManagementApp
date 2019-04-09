package com.springboot.mvc.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.mvc.login.model.ParentTask;
import com.springboot.mvc.login.repository.ParentTaskRepository;

public class ParentTaskServiceImpl implements ParentTaskService {

	@Autowired
	ParentTaskRepository parenttaskrepository;
	
	@Override
	public void saveParentTask(ParentTask parenttask) {
		// TODO Auto-generated method stub
		parenttaskrepository.save(parenttask);
	}

	@Override
	public List<ParentTask> findAllParentTasks() {
		// TODO Auto-generated method stub
		return parenttaskrepository.findAll();
	}

	@Override
	public void deleteParentTask(long parentid) {
		// TODO Auto-generated method stub
		parenttaskrepository.deleteById(parentid);
	}

	@Override
	public ParentTask findByParentId(long parentid) {
		// TODO Auto-generated method stub
		Optional<ParentTask> parenttask;
		parenttask = parenttaskrepository.findById(parentid);
		if (parenttask.isPresent()){
			return parenttask.get();
		}
		else {
			return null;
		}
	}

	@Override
	public ParentTask findByParenttask(String parenttask) {
		// TODO Auto-generated method stub
		return (ParentTask) parenttaskrepository.findByParenttask(parenttask);
	}

}
