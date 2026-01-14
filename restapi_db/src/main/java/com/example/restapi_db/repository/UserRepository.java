package com.example.restapi_db.repository;

import com.example.restapi_db.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}