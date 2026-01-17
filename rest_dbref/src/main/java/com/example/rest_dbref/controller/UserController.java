package com.example.rest_dbref.controller;

import com.example.rest_dbref.entity.User;
import com.example.rest_dbref.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    public UserController(UserService userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/get")
    public ResponseEntity<List<User>> getUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    @PostMapping("/user/update")
    public ResponseEntity<User> updateUser(@RequestBody User user, Principal principal) {
        User newUser =  userService.updateUser(user, principal);
        return new ResponseEntity<>(newUser,HttpStatus.OK);
    }

    @PutMapping("/user/add-book/{bookId}")
    public  ResponseEntity<User> addBook(@PathVariable String bookId, Principal principal) {
        User user = userService.addBookToUser(bookId, principal);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<Void> deleteUser(Principal principal) {
        userService.deleteUser(principal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
