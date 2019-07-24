package com.hflores.demo.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * The Class ExternalApiConfig.
 */
@Component
public class ExternalApiConfig {

	/** The path separator. */
	private final String PATH_SEPARATOR = "/";

	/** The base path. */
	@Value("${basePath}")
	private String basePath;

	/** The users path. */
	@Value("${usersPath}")
	private String usersPath;

	/** The photoss path. */
	@Value("${photosPath}")
	private String photossPath;

	/** The albums path. */
	@Value("${albumsPath}")
	private String albumsPath;

	/** The comments path. */
	@Value("${commentsPath}")
	private String commentsPath;

	/** The posts path. */
	@Value("${postsPath}")
	private String postsPath;

	/**
	 * Gets the base path.
	 *
	 * @return the basePath
	 */
	public String getBasePath() {
		return basePath;
	}

	/**
	 * Sets the base path.
	 *
	 * @param basePath the basePath to set
	 */
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	/**
	 * Gets the users path.
	 *
	 * @return the complete users Path
	 */
	public String getUsersPath() {
		return basePath + usersPath;
	}

	/**
	 * Gets the user path.
	 *
	 * @return the user Path
	 */
	public String getUserPath() {
		return basePath + usersPath + PATH_SEPARATOR;
	}

	/**
	 * Sets the users path.
	 *
	 * @param usersPath the usersPath to set
	 */
	public void setUsersPath(String usersPath) {
		this.usersPath = usersPath;
	}

	/**
	 * Gets the photoss path.
	 *
	 * @return the photossPath
	 */
	public String getPhotossPath() {
		return basePath + photossPath + PATH_SEPARATOR;
	}

	/**
	 * Sets the photoss path.
	 *
	 * @param photossPath the photossPath to set
	 */
	public void setPhotossPath(String photossPath) {
		this.photossPath = photossPath;
	}

	/**
	 * Gets the albums path.
	 *
	 * @return the albumsPath
	 */
	public String getAlbumsPath() {

		return basePath + albumsPath + PATH_SEPARATOR;
	}

	/**
	 * Sets the albums path.
	 *
	 * @param albumsPath the albumsPath to set
	 */
	public void setAlbumsPath(String albumsPath) {
		this.albumsPath = albumsPath;
	}

	/**
	 * Gets the comments path.
	 *
	 * @return the comments path
	 */
	public String getCommentsPath() {
		return basePath + commentsPath + PATH_SEPARATOR;
	}

	/**
	 * Gets the posts path.
	 *
	 * @return the posts path
	 */
	public String getPostsPath() {
		return basePath + postsPath + PATH_SEPARATOR;
	}

}
