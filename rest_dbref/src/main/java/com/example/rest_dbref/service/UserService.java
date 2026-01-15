package com.example.rest_dbref.service;

import com.example.rest_dbref.entity.Book;
import com.example.rest_dbref.entity.User;
import com.example.rest_dbref.repository.BookRepository;
import com.example.rest_dbref.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUserById(String userId,User user){
        User oldUser = userRepository.findById(userId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        oldUser.setName(user.getName());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        return userRepository.save(oldUser);
    }

    public User addBookToUser(String userId, String bookId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        user.getBooks().add(book);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public void deleteUserById(@PathVariable String userId) {
        userRepository.deleteById(userId);
    }
}
