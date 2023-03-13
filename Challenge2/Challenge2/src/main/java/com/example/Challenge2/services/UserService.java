package com.example.Challenge2.services;

import com.example.Challenge2.entities.User;
import com.example.Challenge2.responses.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User getUserByID(Integer id);

    User addNewUser(User user);

    User updateUserByID(int id, User user);

    ResponseEntity<MessageResponse> deleteUserById(int id);

    User findByEmail(String email);

    Boolean existsByEmail(String email);

    Integer numberOfUsers();

}
