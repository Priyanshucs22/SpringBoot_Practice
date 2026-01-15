package com.example.rest_dbref.controller;

import com.example.rest_dbref.entity.User;
import com.example.rest_dbref.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User savedUser = userService.createUser(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
        User updateUser = userService.updateUserById(userId,user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @PutMapping("/{userId}/books/{bookId}")
    public ResponseEntity<User> addBookToUser(
            @PathVariable String userId,
            @PathVariable String bookId) {

        User updatedUser = userService.addBookToUser(userId, bookId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
