package com.example.restapi.controller;

//Without database
import com.example.restapi.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getAllUser() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        users.add(user);
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        for (User user1 : users) {
            if (user1.getId() == id) {
                user1.setName(user.getName());
                user1.setContent(user.getContent());
                return user1;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}