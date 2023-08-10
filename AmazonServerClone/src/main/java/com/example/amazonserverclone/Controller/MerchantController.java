package com.example.amazonserverclone.Controller;

import com.example.amazonserverclone.ApiResponse.ApiResponse;
import com.example.amazonserverclone.Model.Merchant;
import com.example.amazonserverclone.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ArrayList<Merchant> getAllMerchant() {
        return merchantService.getAllMerchants();
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        merchantService.addMerchant(merchant);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Merchant Added Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id) {
        boolean isDeleted = merchantService.deleteMerchant(id);
        if (isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Merchant deleted Successfully"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Merchant not exist!!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable Integer id, @RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        boolean isUpdated = merchantService.updateMerchant(id, merchant);
        if (isUpdated)
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Merchant updated Successfully"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Merchant not exist!!"));
    }

}
