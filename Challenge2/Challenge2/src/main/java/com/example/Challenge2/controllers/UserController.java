package com.example.Challenge2.controllers;

import com.example.Challenge2.entities.User;
import com.example.Challenge2.responses.MessageResponse;
import com.example.Challenge2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers(){
        List<User> Users =  userService.getAllUsers();
        return Users;
    }
    @GetMapping("/{id}")
    public User getUserByID(@PathVariable("id") Integer id){
        User user = userService.getUserByID(id);
        return user;
    }
    @PostMapping("/addUser")
    public User addNewUser(@RequestBody User user){
        User user1 = userService.addNewUser(user);
        return user1;
    }
    @PutMapping("/editUser/{id}")
    public User updateUserByID(@PathVariable("id") Integer id, @RequestBody User user){
        User user1 = userService.updateUserByID(id, user);
        return user1;
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<MessageResponse> deleteUserById(@PathVariable("id") Integer id){
        ResponseEntity<MessageResponse> response = userService.deleteUserById(id);
        return response;
    }





}
