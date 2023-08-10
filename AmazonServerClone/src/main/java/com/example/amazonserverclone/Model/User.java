package com.example.amazonserverclone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class User {

    @NotNull(message = "user id should not be empty")
    private Integer id;

    @NotEmpty(message = "username should not be empty")
    @Length(min = 5, message = "username length should be more than 5 characters")
    private String username;

    @NotEmpty(message = "password should not be empty")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,}$")
    private String password;

    @NotEmpty(message = "email should not be empty")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @NotEmpty(message = "role should not be empty")
    @Pattern(regexp = "\\W*((?i)Admin|Customer(?-i))\\W*", message = "role should only be Admin or Customer")
    private String role;

    @NotNull(message = "balance should not be empty")
    @Positive(message = "balance should be positive amount")
    private Double balance;
}
