package com.example.rest_dbref.repository;

import com.example.rest_dbref.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
