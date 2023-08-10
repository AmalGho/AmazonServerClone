package com.example.amazonserverclone.Controller;

import com.example.amazonserverclone.ApiResponse.ApiResponse;
import com.example.amazonserverclone.Model.MerchantStock;
import com.example.amazonserverclone.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ArrayList<MerchantStock> getAllMerchantStock() {
        return merchantStockService.getAllMerchantStocks();
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Merchant Stock Added Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id) {
        boolean isDeleted = merchantStockService.deleteMerchantStock(id);

        if (isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Merchant Stock deleted Successfully"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Merchant Stock not exist!!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id, @RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()){
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        boolean isUpdated = merchantStockService.updateMerchantStock(id, merchantStock);
        if (isUpdated)
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Merchant Stock updated Successfully"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Merchant Stock not exist!!"));
    }
}
