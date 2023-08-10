package com.example.amazonserverclone.Controller;

import com.example.amazonserverclone.ApiResponse.ApiResponse;
import com.example.amazonserverclone.Model.Product;
import com.example.amazonserverclone.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;

    @GetMapping("/get")
    public ArrayList<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()){
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Product Added Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Product deleted Successfully"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not exist!!");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()){
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        boolean isUpdate = productService.updateProduct(id,product);
        if (isUpdate) return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Product updated Successfully"));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Product not exist!!"));
    }
}
