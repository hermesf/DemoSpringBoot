/**
 * 
 */
package com.hflores.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hflores.demo.models.Permission;
import com.hflores.demo.repositories.PermissionRepository;
import com.hflores.demo.services.PermissionService;

/**
 * The Class PermissionServiceImpl.
 *
 * @author Hermes Flores
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	/** The repo. */
	@Autowired
	PermissionRepository repo;

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@Override
	public Optional<Permission> findById(int id) {

		return repo.findById(id);
	}

	/**
	 * Save permission.
	 *
	 * @param permission the permission
	 * @return the permission
	 */
	@Override
	public Permission savePermission(Permission permission) {
		return repo.save(permission);

	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	public List<Permission> findAll() {
		return repo.findAll();
	}

}
