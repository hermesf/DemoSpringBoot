/**
 * 
 */
package com.hflores.demo.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Hermes Flores
 *
 */
@Entity
public class UserPermissionAlbum {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "album_id")
	private Album album;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "permission_id")
	private Permission permission;

	/*
	 * Clave auxiliar para la busqueda por userId-albumId
	 */
	private String ref;
	/*
	 * Clave auxiliar para la busqueda por albumId-permissionId
	 */
	private String refAlbumPermission;

	public UserPermissionAlbum() {
		super();
	}

	public UserPermissionAlbum(User user, Album album, Permission permission) {
		super();
		this.user = user;
		this.album = album;
		this.permission = permission;
		this.ref =user.getId()+"-"+album.getId();
		this.refAlbumPermission =album.getId()+"-"+permission.getId();
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the album
	 */
	public Album getAlbum() {
		return album;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(Album album) {
		this.album = album;
	}

	/**
	 * @return the permission
	 */
	public Permission getPermission() {
		return permission;
	}

	/**
	 * @param permission the permission to set
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**
	 * @return the refAlbumPermission
	 */
	public String getRefAlbumPermission() {
		return refAlbumPermission;
	}

	/**
	 * @param refAlbumPermission the refAlbumPermission to set
	 */
	public void setRefAlbumPermission(String refAlbumPermission) {
		this.refAlbumPermission = refAlbumPermission;
	}

	
	
}
