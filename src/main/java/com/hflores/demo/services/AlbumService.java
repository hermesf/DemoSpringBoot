/**
 * 
 */
package com.hflores.demo.services;

import java.util.Optional;

import com.hflores.demo.models.Album;

/**
 * @author Hermes Flores
 *
 */

public interface AlbumService {
	
	public Album saveAlbum(Album album);
	
	public Optional<Album> findById(long id);
}
