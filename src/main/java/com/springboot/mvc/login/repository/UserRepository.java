package com.springboot.mvc.login.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.mvc.login.model.Project;
import com.springboot.mvc.login.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Modifying
    @Transactional
	@Query(value = "update users set project_id = null where project_id = ?1", nativeQuery = true)
	int updateUserByProjectid(long projectid);
	
	List<User> findByUseremployeeid(int useremployeeid);
	User findByProject(Project project);

}
