package com.store.record_store.modules.artist.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.store.record_store.modules.artist.model.Artist;

public interface ArtistRepository extends JpaRepository<Artist, UUID> {
    List<Artist> findByNameContaining(String name);
}