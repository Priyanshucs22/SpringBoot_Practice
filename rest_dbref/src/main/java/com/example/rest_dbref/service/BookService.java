package com.example.rest_dbref.service;

import com.example.rest_dbref.entity.Book;
import com.example.rest_dbref.repository.BookRepository;
import com.example.rest_dbref.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(String id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book updateBookById(String id,Book book){
        Book bookToUpdate = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        return bookRepository.save(bookToUpdate);
    }

    @Transactional
    public void deleteBookById(String bookId) {
        userRepository.findByBooks_Id(bookId).ifPresent(user -> {
            user.getBooks().removeIf(book -> book.getId().equals(bookId));
            userRepository.save(user);
        });

        bookRepository.deleteById(bookId);
    }
}
