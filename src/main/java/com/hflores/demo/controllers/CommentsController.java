/**
 * 
 */
package com.hflores.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hflores.demo.components.ExternalApiConfig;
import com.hflores.demo.models.Comment;

/**
 * @author Hermes Flores
 *
 */
@RestController
@RequestMapping(value = "/comments")
public class CommentsController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ExternalApiConfig externalApi;

	private static final Logger logger = LoggerFactory.getLogger(CommentsController.class);

	/**
	 * Obtiene el listado de Comentarios el Web Service, opcionalmente filtrado por
	 * el nombre del comentario o el usuario (email) por userId *
	 * 
	 * @param name  the name
	 * @param email the email
	 * @return the comments
	 */

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Comment>> getComments(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "email", required = false) String email) {

		ResponseEntity<List<Comment>> response = restTemplate.exchange(externalApi.getCommentsPath(), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Comment>>() {
				});

		Stream<Comment> streamComments = response.getBody().stream();

		// Filtra por name
		if (name != null) {
			logger.info("Filtrando por name:" + name);
			streamComments = streamComments.filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()));
		}
		// Filtra por email
		if (email != null) {
			logger.info("Filtrando por email:" + email);
			streamComments = streamComments.filter(c -> c.getEmail().toLowerCase().contains(email.toLowerCase()));
		}

		return new ResponseEntity<List<Comment>>(streamComments.collect(Collectors.toList()), response.getStatusCode());
	}

}
