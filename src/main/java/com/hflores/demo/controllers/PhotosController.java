/**
 * 
 */
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hflores.demo.components.ExternalApiConfig;
import com.hflores.demo.models.Photo;
import com.hflores.demo.models.Post;

/**
 * @author Hermes Flores
 *
 */
@RestController
public class PhotosController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	ExternalApiConfig externalApi;

	private static final Logger logger = LoggerFactory.getLogger(PhotosController.class);

	/**
	 * Gets the list of photos.
	 *
	 * @param userId the user id
	 * @return the photos
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/photos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Photo>> getPhotos(@RequestParam(value = "userId", required = false) Long userId) {

		ResponseEntity<List<Photo>> response = restTemplate.exchange(externalApi.getPhotossPath(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Photo>>() {
				});
		List<Photo> photosByUser = response.getBody();

		// obtener los post , filtrar por id usuario y obtener los id de los post
		if (userId != null) {
			logger.info("Filtrando por userId " + userId);

			ResponseEntity<List<Post>> responsePost = restTemplate.exchange(externalApi.getPostsPath(), HttpMethod.GET,
					null, new ParameterizedTypeReference<List<Post>>() {
					});

			List<Integer> idsPostByUser = responsePost.getBody().parallelStream().filter(p -> p.getUserId() == userId)
					.map(p -> p.getId()).collect(Collectors.toList());

			// consulta las fotos y filtra por los id albumId
			photosByUser = photosByUser.parallelStream().filter(p -> idsPostByUser.contains(p.getAlbumId()))
					.collect(Collectors.toList());
		}

		return new ResponseEntity<>(photosByUser, HttpStatus.OK);

	}

	/**
	 * Gets the photo.
	 *
	 * @param id the id
	 * @return the photo
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/photos/{ID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Photo getPhoto(@PathVariable(value = "ID") int id) {

		Photo photo = restTemplate.getForObject(externalApi.getPhotossPath() + id, Photo.class);

		return photo;
	}
}
