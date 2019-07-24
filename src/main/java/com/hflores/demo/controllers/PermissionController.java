/**
 * 
 */
package com.hflores.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hflores.demo.models.Permission;
import com.hflores.demo.models.User;
import com.hflores.demo.models.UserPermissionAlbum;
import com.hflores.demo.service.impl.PermissionServiceImpl;
import com.hflores.demo.service.impl.UserPermissionAlbumServiceImpl;

/**
 * The Class PermissionController.
 *
 * @author Hermes Flores
 */
@RestController
@RequestMapping(value = "/permits")
public class PermissionController {

	/** The permission service. */
	@Autowired
	private PermissionServiceImpl permissionService;

	/** The repo rel. */
	@Autowired
	private UserPermissionAlbumServiceImpl repoRel;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

	/**
	 * Gets the permits.
	 *
	 * @return the permits
	 */
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Permission>> getPermits() {

		List<Permission> permits = permissionService.findAll();

		permits.stream().forEach(p -> logger.info("id " + p.getId() + "    name   " + p.getName()));

		return new ResponseEntity<List<Permission>>(permits, HttpStatus.OK);

	}

	/**
	 * Post permission.
	 *
	 * @param userId the user id
	 * @param albumId the album id
	 * @param permissionIds the permission ids
	 * @return the response entity
	 */
	@PostMapping(value = "/user")
	public ResponseEntity<Integer> postPermission(@RequestParam(required = true) long userId,
			@RequestParam(required = true) long albumId, @RequestParam(required = true) List<Integer> permissionIds) {

		int nCreated = 0;
		int nExist = 0;
		HttpStatus httpStatus = HttpStatus.CONFLICT;

		nExist = repoRel.getCountPermission(userId, albumId);

		if (nExist == 0) {
			nCreated = repoRel.saveRelation(userId, albumId, permissionIds);
			httpStatus = HttpStatus.CREATED;
		}

		return new ResponseEntity<Integer>(nCreated, httpStatus);

	}

	/**
	 * Put permission.
	 *
	 * @param userId the user id
	 * @param albumId the album id
	 * @param permissionIds the permission ids
	 * @return the response entity
	 */
	@PutMapping(value = "/user")
	public ResponseEntity<Integer> putPermission(@RequestParam(required = true) long userId,
			@RequestParam(required = true) long albumId, @RequestParam(required = true) List<Integer> permissionIds) {

		int nUpdated = 0;
		int nExist = 0;
		HttpStatus httpStatus = HttpStatus.CONFLICT;

		nExist = repoRel.getCountPermission(userId, albumId);

		if (nExist > 0) {

			httpStatus = HttpStatus.OK;
			nUpdated = repoRel.updateRelation(userId, albumId, permissionIds);

		}

		return new ResponseEntity<Integer>(nUpdated, httpStatus);

	}

	/**
	 * Gets the usser.
	 *
	 * @param albumId the album id
	 * @param permissionId the permission id
	 * @return the usser
	 */
	@GetMapping(value = "/user/{ALBUM_ID}/{PERMISSION_ID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsser(@PathVariable(value = "ALBUM_ID") long albumId,
			@PathVariable(value = "PERMISSION_ID") long permissionId) {

		List<UserPermissionAlbum> permits = repoRel.getPermitsForRefAlbumPermission(albumId + "-" + permissionId);

		return permits.stream().map(p -> p.getUser()).collect(Collectors.toList());

	}

}
