package com.store.record_store.modules.album.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.store.record_store.modules.album.model.Album;

public interface AlbumRepository extends JpaRepository<Album, UUID> {
    @Query("""
        SELECT a
        FROM Album a
        WHERE a.title LIKE %?1%
        """)
    List<Album> findByTitleContaining(String title);
}
