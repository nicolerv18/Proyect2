package com.store.record_store.modules.user.repository;

import com.store.record_store.modules.user.model.User;
import com.store.record_store.shared.repository.ABaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends ABaseRepository<User, UUID> {
    
    @Query("""
        SELECT u 
        FROM User u 
        WHERE u.firstName LIKE %:name% 
        OR u.lastName LIKE %:name%
        """)
    List<User> findByName(String name);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByStatus(Boolean status);

    Optional<User> findByAddress(String address);
}