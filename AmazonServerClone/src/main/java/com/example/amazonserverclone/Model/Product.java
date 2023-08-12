package com.example.amazonserverclone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class Product {

    @NotNull(message = "product id should not be empty")
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Length(min = 4, message = "name length should be more than 3 characters")
    private String name;

    @NotNull(message = "price should not be empty")
    @Positive(message = "price should be positive amount")
    private Double price;

    @NotNull(message = "category id should not be empty")
    private Integer categoryID;

}
