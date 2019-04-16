package com.springboot.mvc.login.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.springboot.mvc.login.ProjectManagementAppApplicationTests;
import com.springboot.mvc.login.model.ParentTask;
import com.springboot.mvc.login.repository.ParentTaskRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
						 TransactionalTestExecutionListener.class,
						 DirtiesContextTestExecutionListener.class,
						 DbUnitTestExecutionListener.class})
@DatabaseSetup("/parentTaskData.xml")
public class ParentTaskRepositoryTest extends ProjectManagementAppApplicationTests{
	
	@Autowired
	ParentTaskRepository parenttaskrepository;
	
	@Test
	public void testfindByParenttask(){
		
		List<ParentTask> parenttask = parenttaskrepository.findByParenttask("First Parnt Task");
		assertThat(parenttask.size(), is(1));
		
	}

}
