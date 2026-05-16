package com.example.alloservice.service;


import com.example.alloservice.exception.ResourceNotFoundException;
import com.example.alloservice.model.User;
import com.example.alloservice.repository.UserProfileRepository;
import com.example.alloservice.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public UserService(UserRepository userRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    // Get All Users
    public List<User> getAll(){
        return userRepository.findAll();
    }

    // Get User By ID
    public User getById(Long id){
        if (!userRepository.existsById(id)){
                throw new ResourceNotFoundException("User with given ID : " + id + " not found");
        }
        return userRepository.findById(id).get();
    }

    // Delete User
    public ResponseEntity<String> deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User with given ID : " + id + " not found");
        }
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("User deleted successfully ");
    }

    // Update User
    public User updateUser(User user) {
        if (!userRepository.existsById(user.getId())){
            throw  new ResourceNotFoundException("User not found");
        }
        return userRepository.save(user);
    }
}
