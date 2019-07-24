package com.hflores.demo.services;

import java.util.List;
import java.util.Optional;

import com.hflores.demo.models.Permission;

/**
 * @author Hermes Flores
 *
 */
public interface PermissionService {

	public Permission savePermission(Permission permission);
	
	
	public Optional<Permission> findById(int id);
	
	public List<Permission> findAll();
	
	//public Optional<Permission> findByNameAnd(int id);
}
