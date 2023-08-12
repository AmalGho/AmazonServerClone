package com.example.amazonserverclone.Controller;

import com.example.amazonserverclone.ApiResponse.ApiResponse;
import com.example.amazonserverclone.Model.User;
import com.example.amazonserverclone.Service.UserService;
import com.sun.source.tree.BreakTree;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ArrayList<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        userService.addUsers(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User Added successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        boolean isDeleted = userService.deleteUser(id);

        if (isDeleted)
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User Deleted Successfully"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("User not exist!!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()){
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
        }

        boolean isUpdated = userService.updateUser(id, user);
        if (isUpdated)
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User Updated Successfully"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("User not exist!!"));
    }


    @GetMapping("/buy/{userId}/{productId}/{merchantId}")
    public  ResponseEntity buyProduct(@PathVariable Integer userId, @PathVariable Integer productId, @PathVariable Integer merchantId) {
        String msg = userService.buyProduct(userId, productId, merchantId);
         return ResponseEntity.status(HttpStatus.OK).body(msg);

    }



}
