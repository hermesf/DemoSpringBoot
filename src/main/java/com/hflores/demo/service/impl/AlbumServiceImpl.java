/**
 * 
 */
package com.hflores.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hflores.demo.models.Album;
import com.hflores.demo.repositories.AlbumRepository;
import com.hflores.demo.services.AlbumService;

/**
 * The Class AlbumServiceImpl.
 *
 * @author Hermes Flores
 */
@Service
public class AlbumServiceImpl implements AlbumService {

	/** The repo. */
	@Autowired
	AlbumRepository repo;

	/**
	 * Save album.
	 *
	 * @param album the album
	 * @return the album
	 */
	@Override
	public Album saveAlbum(Album album) {

		return repo.save(album);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	@Override
	public Optional<Album> findById(long id) {

		return repo.findById(id);

	}

}
