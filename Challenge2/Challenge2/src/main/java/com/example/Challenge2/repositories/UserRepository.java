package com.example.Challenge2.repositories;

import com.example.Challenge2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query("SELECT COUNT (*) AS Number FROM User ")
    Integer numberOfUsers();
}
