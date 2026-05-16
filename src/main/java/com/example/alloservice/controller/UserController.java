package com.example.alloservice.controller;



import com.example.alloservice.model.User;
import com.example.alloservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){

        return userService.getAll();
    }

    // Get User By ID
    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getById(id);
    }

    // Delete User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @PutMapping("/update")
    public User update(@RequestBody User user){
        return userService.updateUser(user);
    }
}
