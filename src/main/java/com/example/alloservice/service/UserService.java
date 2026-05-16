package com.example.alloservice.service;

import com.example.alloservice.dto.UserDto;
import com.example.alloservice.exception.ResourceNotFoundException;
import com.example.alloservice.model.User;
import com.example.alloservice.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // --- Mapper ---
    private UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getFullname(),
                user.getPhone(),
                user.getAddress()
        );
    }

    // Get All Users (ADMIN)
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Get by ID (ADMIN)
    public UserDto getById(Long id) {
        return userRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
    }



    // Delete (ADMIN)
    public ResponseEntity<String> deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    // Update — only the owner can update their own profile
    public UserDto updateUser(UserDto dto, String currentUsername) {
        User existing = userRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Ownership check — prevent a user from editing someone else's profile
        if (!existing.getEmail().equals(currentUsername)) {
            throw new AccessDeniedException("You are not allowed to update this profile");
        }

        if (dto.getUsername() != null) existing.setUsername(dto.getUsername());
        if (dto.getEmail()    != null) existing.setEmail(dto.getEmail());
        if (dto.getRole()     != null) existing.setRole(dto.getRole());
        if (dto.getFullname() != null) existing.setFullname(dto.getFullname());
        if (dto.getPhone()    != null) existing.setPhone(dto.getPhone());
        if (dto.getAddress()  != null) existing.setAddress(dto.getAddress());

        return toDto(userRepository.save(existing));
    }
}