package com.example.Challenge2.services;

import com.example.Challenge2.entities.User;
import com.example.Challenge2.responses.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserByID(Integer id);

    User addNewUser(User user);

    User updateUserByID(int id, User user);

    ResponseEntity<MessageResponse> deleteUserById(int id);
}
