package com.store.record_store.modules.genre.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.record_store.modules.genre.model.genre;
import org.springframework.data.jpa.repository.Query;


public interface genreRepository extends JpaRepository<genre, Long> {


    @Query("""
        SELECT g
        FROM genre g
        WHERE
        g.name like %?1%
            """)
    
    List<genre> findByName(String name);
    
}
