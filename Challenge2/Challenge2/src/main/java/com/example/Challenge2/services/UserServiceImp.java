package com.example.Challenge2.services;

import com.example.Challenge2.entities.User;
import com.example.Challenge2.repositories.UserRepository;
import com.example.Challenge2.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByID(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUserByID(int id, User user) {

        User getUser = userRepository.findById(id).get();

        getUser.setFirstName(user.getFirstName());
        getUser.setLastName(user.getLastName());
        getUser.setEmail(user.getEmail());
        getUser.setAge(user.getAge());
        getUser.setPassword(user.getPassword());


        return userRepository.save(getUser);
    }

    @Override
    public ResponseEntity<MessageResponse> deleteUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            MessageResponse messageResponse = new MessageResponse("User deleted successfully");
            return ResponseEntity.ok(messageResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public Boolean existsByEmail(String email) {
        return  userRepository.existsByEmail(email);
    }

    @Override
    public Integer numberOfUsers() {
        return userRepository.numberOfUsers();
    }


}
