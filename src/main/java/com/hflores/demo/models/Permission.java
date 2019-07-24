/**
 * 
 */
package com.hflores.demo.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Class Permission.
 *
 * @author Hermes Flores
 */
@Entity
public class Permission {

	/** The id. */
	@Id
	private int id;

	/** The liked courses. */
	@JsonIgnore
	@ManyToMany
	private Set<Album> albunes;

	@OneToMany(mappedBy = "permission", cascade = { CascadeType.ALL, CascadeType.MERGE })
	private Set<UserPermissionAlbum> userPermissionAlbumLinks;

	/** The name. */
	private String name;

	public Permission() {
		super();
	}

	public Permission(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the albunes.
	 *
	 * @return the albunes
	 */
	public Set<Album> getAlbunes() {
		return albunes;
	}

	/**
	 * Sets the albunes.
	 *
	 * @param albunes the new albunes
	 */
	public void setAlbunes(Set<Album> albunes) {
		this.albunes = albunes;
	}

	/**
	 * Adds the albun.
	 *
	 * @param album the album
	 */
	public void addAlbun(Album album) {
		this.albunes.add(album);
	}

}
