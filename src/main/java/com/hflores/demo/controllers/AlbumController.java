package com.hflores.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hflores.demo.components.ExternalApiConfig;
import com.hflores.demo.models.Album;
import com.hflores.demo.service.impl.AlbumServiceImpl;

/**
 * The Class AlbumController.
 *
 * @author Hermes Flores
 */
@RestController
@RequestMapping(value = "/albums")
public class AlbumController {

	/** The rest template. */
	@Autowired
	private RestTemplate restTemplate;

	/** The external api. */
	@Autowired
	private ExternalApiConfig externalApi;

	/** The album service. */
	@Autowired
	private AlbumServiceImpl albumService;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

	/**
	 * Obtiene el listado de albunes desde el Web Service, opcionalmente filtrado
	 * por userId
	 *
	 * @param userId the user id
	 * @return the albums
	 */
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Album>> getAlbums(@RequestParam(value = "userId", required = false) Long userId) {

		ResponseEntity<List<Album>> response = restTemplate.exchange(externalApi.getAlbumsPath(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Album>>() {
				});
		List<Album> albums = response.getBody();

		// Guarda los Albunes en DB
		albums.parallelStream().forEach(albumService::saveAlbum);

		// Filtra por userId
		if (userId != null && userId.intValue() > 0) {

			logger.info("Filtrando por " + userId);
			albums = albums.stream().filter(a -> a.getUserId() == userId.intValue()).collect(Collectors.toList());
		}

		return new ResponseEntity<List<Album>>(albums, response.getStatusCode());
	}

	/**
	 * Obtiene un album para un id determinado
	 *
	 * @param id the id
	 * @return the photo
	 */
	@GetMapping(value = "/{ID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getPhoto(@PathVariable(value = "ID") int id) {

		Object obj = null;
		HttpStatus httpStatus = HttpStatus.OK;
		try {

			obj = restTemplate.getForObject(externalApi.getAlbumsPath() + id, Album.class);

		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			obj = e.getMessage();
			httpStatus = HttpStatus.NOT_FOUND;

		}

		return new ResponseEntity<>(obj, httpStatus);
	}

}