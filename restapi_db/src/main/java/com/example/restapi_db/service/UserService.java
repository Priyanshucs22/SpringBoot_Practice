package com.example.restapi_db.service;

import com.example.restapi_db.model.User;
import com.example.restapi_db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;

    //Constructor injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Create or save a user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Get user by ID
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // Delete user by ID
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    // Update user
    public User updateUser(String id, User updatedUser) {

//        1 way to do this
//        return userRepository.findById(id)
//                .map(user -> {
//                    user.setName(updatedUser.getName());
//                    user.setContent(updatedUser.getContent());
//                    return userRepository.save(user);
//                })
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        Optional<User> optionalUser = userRepository.findById(id);
        // Check if user exists
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Update fields
            existingUser.setName(updatedUser.getName());
            existingUser.setContent(updatedUser.getContent());

            // Save updated user
            return userRepository.save(existingUser);
        } else {
            // User not found
            throw new RuntimeException("User not found");
        }
    }
}
