package com.example.Challenge2.services;

import com.example.Challenge2.entities.User;
import com.example.Challenge2.entities.UserDetails;
import com.example.Challenge2.repositories.UserDetailsRepository;
import com.example.Challenge2.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserDetailsRepository userDetailsRepository;


    @Override
    public List<UserDetails> getAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    @Override
    public UserDetails getUserDetailsById(Integer id) {
        return userDetailsRepository.findById(id).get();
    }

    @Override
    public UserDetails addUserDetails(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    @Override
    public UserDetails editUserDetails(Integer id, UserDetails userDetails) {
        UserDetails editedDetails = userDetailsRepository.findById(id).get();
        editedDetails.setGithubProfileLink(userDetails.getGithubProfileLink());
        editedDetails.setLinkedInProfileLink(userDetails.getLinkedInProfileLink());
        editedDetails.setCreatedAt(userDetails.getCreatedAt());
        editedDetails.setUpdatedAt(userDetails.getUpdatedAt());

        return userDetailsRepository.save(editedDetails);
    }

    @Override
    public ResponseEntity<MessageResponse> deleteUserDetailsById(int id) {
        verifyExistenceById(id);
        userDetailsRepository.deleteById(id);
        MessageResponse messageResponse = new MessageResponse("User Details Deleted");
        return ResponseEntity.ok(messageResponse);
    }

    @Override
    public void verifyExistenceById(Integer id){
        userDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no employee was found for id " + id));
    }
}
