package com.example.alloservice.controller;

import com.example.alloservice.dto.UserDto;
import com.example.alloservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    // ADMIN only — get any user by ID
    @GetMapping("/get/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> update(
            @RequestBody UserDto dto,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.updateUser(dto, userDetails.getUsername()));
    }
}