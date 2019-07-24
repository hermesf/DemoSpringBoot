package com.hflores.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hflores.demo.models.UserPermissionAlbum;

/**
 * @author Hermes Flores
 *
 */

@Service
public interface UserPermissionAlbumService {

	
	public UserPermissionAlbum saveRelation(UserPermissionAlbum rel);
	
	public int saveRelation(long userId, long albumId, List<Integer> permissionIds);
	
	public int updateRelation(long userId, long albumId, List<Integer> permissionIds);
	
	public int getCountPermission(long userId, long albumId);
	

	public List<UserPermissionAlbum> getPermitsForRefAlbumPermission(String ref);
	
	

	

}
