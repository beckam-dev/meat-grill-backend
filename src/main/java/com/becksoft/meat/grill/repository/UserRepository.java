package com.becksoft.meat.grill.repository;

import com.becksoft.meat.grill.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    // Clave para la autenticación
    Optional<User> findByUsername(String username);

}
