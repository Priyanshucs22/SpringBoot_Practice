package com.example.rest_dbref.service;

import com.example.rest_dbref.entity.Book;
import com.example.rest_dbref.entity.User;
import com.example.rest_dbref.repository.BookRepository;
import com.example.rest_dbref.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BCryptPasswordEncoder encoder;
    public UserService(UserRepository userRepository,
                       BookRepository bookRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.encoder = encoder;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User updatedUser, Principal principal) {

        String email = principal.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.setName(updatedUser.getName());
        user.setPassword(encoder.encode(updatedUser.getPassword()));

        return userRepository.save(user);
    }

    public User addBookToUser(String bookId, Principal principal) {

        String email = principal.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        user.getBooks().add(book);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Principal principal) {
        String email = principal.getName();
        userRepository.deleteByEmail(email);
    }
}