package com.hflores.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hflores.demo.models.Album;

@Repository
public interface AlbumRepository   extends JpaRepository<Album, Long>{
	
}
