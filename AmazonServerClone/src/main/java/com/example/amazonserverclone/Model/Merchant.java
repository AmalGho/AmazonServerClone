package com.example.amazonserverclone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class Merchant {
    @NotNull(message = "merchant id should not be empty")
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Length(min = 4, message = "name length should be more than 3 characters")
    private String name;
}
