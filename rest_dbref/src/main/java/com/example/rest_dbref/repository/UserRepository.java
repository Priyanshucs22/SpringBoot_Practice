package com.example.rest_dbref.repository;

import com.example.rest_dbref.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByBooks_Id(String bookId);

}
