package com.example.amazonserverclone.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotNull(message = "merchant stock id should not be empty")
    private Integer id;

    @NotNull(message = "product id should not be empty")
    private Integer productId;

    @NotNull(message = "merchant id should not be empty")
    private Integer merchantId;

    @NotNull(message = "stock should not be empty")
    @Min(value = 11, message = "stock must be greater than 10") //in start 11
    private Integer stock;
}
