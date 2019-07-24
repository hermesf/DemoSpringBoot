package com.hflores.demo.controllers;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hflores.demo.components.ExternalApiConfig;
import com.hflores.demo.models.User;
import com.hflores.demo.service.impl.UserServiceImp;

/**
 * The Class UserController.
 */
@RestController
@RequestMapping(value = "users")
public class UserController {

	/** The rest template. */
	@Autowired
	private RestTemplate restTemplate;

	/** The external api. */
	@Autowired
	ExternalApiConfig externalApi;

	/** The user service. */
	@Autowired
	UserServiceImp userService;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Gets the ussers.
	 *
	 * @return the ussers
	 */
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getUssers() {

		ResponseEntity<List<User>> response = restTemplate.exchange(externalApi.getUsersPath(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});
		List<User> users = response.getBody();

		users.parallelStream().forEach(userService::saveUser);

		if (!users.isEmpty()) {

			Optional<User> userRecovery = userService.findById(users.get(0).getId());

			logger.info("Recuperado " + userRecovery.isPresent());
			if (userRecovery.isPresent()) {

				ObjectMapper mapper = new ObjectMapper();

				try {
					logger.info(mapper.writeValueAsString(userRecovery.get()));
				} catch (JsonProcessingException e) {

					e.printStackTrace();
				}
			}
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	/**
	 * Gets the usser.
	 *
	 * @param id the id
	 * @return the usser
	 */
	@GetMapping(value = "/{ID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUsser(@PathVariable(value = "ID") int id) {

		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		Object obj = null;

		try {

			obj = restTemplate.getForObject(externalApi.getUserPath() + id, User.class);
		} catch (Exception e) {

			logger.error(e.getMessage(), e);
			obj = e.getMessage();
			httpStatus = HttpStatus.NOT_FOUND;

		}

		return new ResponseEntity<>(obj, httpStatus);
	}

}
