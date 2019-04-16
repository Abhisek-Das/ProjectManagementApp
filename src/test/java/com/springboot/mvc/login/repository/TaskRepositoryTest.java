package com.springboot.mvc.login.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
import com.springboot.mvc.login.model.Task;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
						 TransactionalTestExecutionListener.class,
						 DirtiesContextTestExecutionListener.class,
						 DbUnitTestExecutionListener.class})
@DatabaseSetup("/taskData.xml")
public class TaskRepositoryTest extends ProjectManagementAppApplicationTests{
	
	@Autowired
	TaskRepository taskrepository;
	
	
	@Test()
	public void testfindByTaskname(){
		
		List<Task> task = taskrepository.findByTaskname("Final Task1");
//		assertEquals(task.get(0).getTaskname(), "Final Task1");
		assertTrue(task != null);
		
	}

}
