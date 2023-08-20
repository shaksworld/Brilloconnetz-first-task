package com.shaks.UserRegistrationApp;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4, message = "Username must have at least 4 characters")
    private String username;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Pattern.List({
            @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter"),
            @Pattern(regexp = ".*[!@#$%^&*].*", message = "Password must contain at least one special character"),
            @Pattern(regexp = ".*[0-9].*", message = "Password must contain at least one digit"),
            @Pattern(regexp = ".{8,}", message = "Password must have at least 8 characters")
    })
    private String password;

    @NotNull(message = "Date of Birth cannot be empty")
    @Past(message = "Date of Birth must be in the past")
    private LocalDate dob;
}
