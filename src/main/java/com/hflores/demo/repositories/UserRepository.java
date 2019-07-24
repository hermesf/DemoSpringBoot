package com.hflores.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hflores.demo.models.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{ 
	
    
	
    	
}


	
