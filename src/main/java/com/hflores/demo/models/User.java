package com.hflores.demo.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class User {
	
	@Id
	private long id;
	
	@JsonIgnore
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL, CascadeType.MERGE})
    private Set<UserPermissionAlbum> userPermissionAlbumLinks;
    
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
    private Address address;
	
    @OneToOne(cascade = CascadeType.ALL)
   	@JoinColumn(name = "company_id")
    private Company company;
	
	
	    
	private String name;
	private String username;
	private String email;	
	private String phone;
	private String website;
	
	
	public User() {
		super();
	}
	
	
	public User(long userId) {
		super();
		this.id = userId;
	}







	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the addres
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param addres the addres to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	public Set<UserPermissionAlbum> getUserPermissionAlbumLinks() {
		return userPermissionAlbumLinks;
	}

	public void setUserPermissionAlbumLinks(Set<UserPermissionAlbum> userPermissionAlbumLinks) {
		this.userPermissionAlbumLinks = userPermissionAlbumLinks;
	}

	
	
}
	

