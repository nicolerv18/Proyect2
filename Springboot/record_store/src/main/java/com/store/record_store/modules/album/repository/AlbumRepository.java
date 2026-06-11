package com.store.record_store.modules.Album.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.record_store.modules.Album.model.Album;
import org.springframework.data.jpa.repository.Query;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query("""
        SELECT a
        FROM Album a
        WHERE a.title LIKE %?1%
        """)
    List<Album> findByTitleContaining(String title);
}
