package com.example.alloservice.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserDto {
	private Long id;
	private String username;
	private String email;
	private String role;
	private String fullname;
	private String phone;
	private String address;
}