/**
 * 
 */
package com.hflores.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hflores.demo.models.Album;
import com.hflores.demo.models.Permission;
import com.hflores.demo.models.User;
import com.hflores.demo.models.UserPermissionAlbum;
import com.hflores.demo.repositories.UserPermissionAlbumRepository;
import com.hflores.demo.services.UserPermissionAlbumService;

/**
 * The Class UserPermissionAlbumServiceImpl.
 *
 * @author Hermes Flores
 */
@Service
public class UserPermissionAlbumServiceImpl implements UserPermissionAlbumService {

	/** The repo rel. */
	@Autowired
	UserPermissionAlbumRepository repoRel;

	/** The repo user. */
	@Autowired
	UserServiceImp repoUser;

	/** The repo album. */
	@Autowired
	AlbumServiceImpl repoAlbum;

	/** The repo permission. */
	@Autowired
	PermissionServiceImpl repoPermission;

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(UserPermissionAlbumServiceImpl.class);

	/**
	 * Save relation.
	 *
	 * @param userId        the user id
	 * @param albumId       the album id
	 * @param permissionIds the permission ids
	 * @return the int
	 */
	@Override
	public int saveRelation(long userId, long albumId, List<Integer> permissionIds) {

		logger.info("saveRelation");

		Optional<User> userOpt = repoUser.findById(userId);
		Optional<Album> albumOpt = repoAlbum.findById(albumId);

		List<Permission> permits = permissionIds.stream().map(repoPermission::findById).filter(Optional::isPresent)
				.map(Optional::get).collect(Collectors.toList());

		List<UserPermissionAlbum> listrel = new ArrayList<>();

		if (userOpt.isPresent() && albumOpt.isPresent() && !permits.isEmpty()) {

			listrel = permits.parallelStream()
					.map(permission -> new UserPermissionAlbum(userOpt.get(), albumOpt.get(), permission))
					.collect(Collectors.toList());

			repoRel.saveAll(listrel);
		}

		logger.info("creadas " + listrel.size());
		return listrel.size();
	}

	/**
	 * Update relation.
	 *
	 * @param userId        the user id
	 * @param albumId       the album id
	 * @param permissionIds the permission ids
	 * @return the int
	 */
	@Override
	public int updateRelation(long userId, long albumId, List<Integer> permissionIds) {

		logger.info("updateRelation");

		Optional<User> userOpt = repoUser.findById(userId);
		Optional<Album> albumOpt = repoAlbum.findById(albumId);
		List<UserPermissionAlbum> listrel = new ArrayList<>();
		int n = 0;

		if (userOpt.isPresent() && albumOpt.isPresent()) {

			String ref = userId + "-" + albumId;

			// clear relations
			listrel = repoRel.findByRef(ref);

			logger.info("borrando " + listrel.size());

			repoRel.deleteAll(listrel);

			n = this.saveRelation(userId, albumId, permissionIds);

		}

		return n;
	}

	/**
	 * Save relation.
	 *
	 * @param rel the rel
	 * @return the user permission album
	 */
	@Override
	public UserPermissionAlbum saveRelation(UserPermissionAlbum rel) {
		return repoRel.save(rel);
	}

	/**
	 * Gets the count permission.
	 *
	 * @param userId  the user id
	 * @param albumId the album id
	 * @return the count permission
	 */
	@Override
	public int getCountPermission(long userId, long albumId) {

		return repoRel.findByRef(userId + "-" + albumId).size();
	}

	/**
	 * Gets the permits for ref album permission.
	 *
	 * @param ref the ref
	 * @return the permits for ref album permission
	 */
	@Override
	public List<UserPermissionAlbum> getPermitsForRefAlbumPermission(String ref) {

		logger.info("Buscando ref " + ref);

		return repoRel.findByRefAlbumPermission(ref);
	}

}
