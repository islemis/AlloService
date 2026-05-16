package com.example.alloservice.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Valid Email required")
    private String email;

    @Size(min = 8  , message = "Password must be between 8 and 20")
    private String password;

    private String role;

    @JsonIgnoreProperties({"user"})
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="profile_id")
    private UserProfile userProfile;

}
