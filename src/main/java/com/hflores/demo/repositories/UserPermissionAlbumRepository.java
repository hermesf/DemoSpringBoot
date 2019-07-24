
package com.hflores.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hflores.demo.models.UserPermissionAlbum;

@Repository
public interface UserPermissionAlbumRepository  extends JpaRepository<UserPermissionAlbum, Integer>{  

	public List<UserPermissionAlbum> findByRef(String ref);
	
	public List<UserPermissionAlbum> findByRefAlbumPermission(String ref);	
	
}
