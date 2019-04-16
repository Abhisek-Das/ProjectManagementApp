package com.springboot.mvc.login.repository;

import static org.assertj.core.api.Assertions.assertThat;
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
import com.springboot.mvc.login.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
						 TransactionalTestExecutionListener.class,
						 DirtiesContextTestExecutionListener.class,
						 DbUnitTestExecutionListener.class})
@DatabaseSetup("/userData.xml")

public class UserRepositoryTest extends ProjectManagementAppApplicationTests{

	@Autowired
	UserRepository userrepository;
	
	@Test
	public void testfindByUseremployeeid() {
		List<User> usr = userrepository.findByUseremployeeid(007);
		
		assertThat(usr.size(), is(1));
	}
	
}
