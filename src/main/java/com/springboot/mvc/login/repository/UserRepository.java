package com.springboot.mvc.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.mvc.login.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByUseremployeeid(int useremployeeid);

}
