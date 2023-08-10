package com.example.amazonserverclone.Controller;

import com.example.amazonserverclone.ApiResponse.ApiResponse;
import com.example.amazonserverclone.Model.Category;
import com.example.amazonserverclone.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ArrayList<Category> getAllCategory() {
        return categoryService.getAllCategories();
    }


    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Category Added Successfully"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Category deleted Successfully"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Category not exist!!"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        boolean isUpdated = categoryService.updateCategory(id, category);
        if (isUpdated)
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Category updated Successfully"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Category not exist!!"));
    }

}
