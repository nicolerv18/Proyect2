package com.store.record_store.modules.artist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.store.record_store.modules.artist.model.Artist;


@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findByNameContaining(String name);
}