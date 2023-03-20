package com.example.Challenge2.services;

import com.example.Challenge2.entities.Post;
import com.example.Challenge2.entities.UserDetails;
import com.example.Challenge2.responses.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserDetailsService {
    List<UserDetails> getAllUserDetails();

    UserDetails getUserDetailsById(Integer id);

    UserDetails addUserDetails(UserDetails userDetails);

    UserDetails editUserDetails(Integer id, UserDetails userDetails);

    ResponseEntity<MessageResponse> deleteUserDetailsById(int id);

    void verifyExistenceById(Integer id);

}
