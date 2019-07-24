package com.hflores.demo.services;

import java.util.Optional;

import com.hflores.demo.models.User;

/**
 * @author Hermes Flores
 *
 */
public interface UserService {

	public User saveUser(User User);

	public Optional<User> findById(long id);



}
