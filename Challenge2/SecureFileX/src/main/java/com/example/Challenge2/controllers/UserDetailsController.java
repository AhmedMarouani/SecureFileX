package com.example.Challenge2.controllers;

import com.example.Challenge2.entities.Role;
import com.example.Challenge2.entities.UserDetails;
import com.example.Challenge2.responses.MessageResponse;
import com.example.Challenge2.services.RoleService;
import com.example.Challenge2.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/UserDetails")

public class UserDetailsController {
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/")
    public List<UserDetails> getAllUserDetails(){
        List<UserDetails> AllUserDetails =  userDetailsService.getAllUserDetails();
        return AllUserDetails;
    }
    @GetMapping("/{id}")
    public UserDetails getUserDetailsByID(@PathVariable("id") Integer id){
        UserDetails userDetails = userDetailsService.getUserDetailsById(id);
        return userDetails;
    }
    @PostMapping("/addUserDetails")
    public UserDetails addNewUserDetails(@RequestBody UserDetails userDetails){
        UserDetails newUserDetails = userDetailsService.addUserDetails(userDetails);
        return newUserDetails;
    }
    @PutMapping("/editUserDetails/{id}")
    public UserDetails updateUserDetailsByID(@PathVariable("id") Integer id, @RequestBody UserDetails userDetails){
        UserDetails editedUserDetails = userDetailsService.editUserDetails(id, userDetails);
        return editedUserDetails;
    }
    @DeleteMapping("/deleteUserDetails/{id}")
    public ResponseEntity<MessageResponse> deleteUserDetailsById(@PathVariable("id") Integer id){
        ResponseEntity<MessageResponse> response = userDetailsService.deleteUserDetailsById(id);
        return response;
    }
}
