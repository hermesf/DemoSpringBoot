package com.hflores.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hflores.demo.models.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {


	
}
