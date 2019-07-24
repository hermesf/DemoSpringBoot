/**
 * 
 */
package com.hflores.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hflores.demo.models.User;
import com.hflores.demo.repositories.UserRepository;
import com.hflores.demo.services.UserService;

/**
 * The Class UserServiceImp.
 *
 * @author Hermes Flores
 */
@Service
public class UserServiceImp implements UserService {

	/** The repo. */
	@Autowired
	UserRepository repo;

	/**
	 * Save user.
	 *
	 * @param user the user
	 * @return the user
	 */
	@Override
	public User saveUser(User user) {
		return repo.save(user);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@Override
	public Optional<User> findById(long id) {
		return repo.findById(id);
	}

}
