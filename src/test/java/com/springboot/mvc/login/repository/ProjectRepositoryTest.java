package com.springboot.mvc.login.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

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
import com.springboot.mvc.login.model.Project;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
						 TransactionalTestExecutionListener.class,
						 DirtiesContextTestExecutionListener.class,
						 DbUnitTestExecutionListener.class})
@DatabaseSetup("/projectData.xml")
public class ProjectRepositoryTest extends ProjectManagementAppApplicationTests{
	
	@Autowired
	ProjectRepository projectrepository;
	
	@Test
	public void testfindByProjectname() {
		
		Project proj = projectrepository.findByProjectname("Final Project1");
		assertEquals(proj.getProjectname(), "Final Project1");
	}

}
