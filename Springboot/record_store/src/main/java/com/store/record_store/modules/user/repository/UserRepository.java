package com.store.record_store.modules.user.repository;
import com.store.record_store.modules.user.model.User;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {


    @Query("""
        SELECT u
        FROM User u
        WHERE
        u.name like %?1%
            """)
    List<User> findByName(String name);

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    List<User> findByStatus(String status);
    Optional<User> findByAddress(String address);
    
    
}
