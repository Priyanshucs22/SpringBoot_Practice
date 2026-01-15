package com.example.rest_dbref.controller;

import com.example.rest_dbref.entity.Book;
import com.example.rest_dbref.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable String id){
        return bookService.getBookById(id);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {

        Book savedBook = bookService.createBook(book);

        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book) {
        Book bookToUpdate = bookService.updateBookById(id,book);
        return new ResponseEntity<>(bookToUpdate, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
