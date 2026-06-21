package com.store.record_store.modules.genre.repository;

import java.util.List;
import java.util.UUID;

import com.store.record_store.modules.genre.model.genre;
import org.springframework.data.jpa.repository.Query;
import com.store.record_store.shared.repository.ABaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GenreRepository extends ABaseRepository <genre, UUID> {


    @Query("""
        SELECT g
        FROM genre g
        WHERE
        g.name like %?1%
            """)
    
    List<genre> findByName(String name);
    
}

