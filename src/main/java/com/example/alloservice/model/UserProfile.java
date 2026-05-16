package com.example.alloservice.model;


import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserProfile {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullname;
    private String phone;
    private String address;

    @OneToOne(mappedBy = "userProfile")
    private User user;
}
