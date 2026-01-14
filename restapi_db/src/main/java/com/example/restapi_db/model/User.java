package com.example.restapi_db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users") // MongoDB collection
public class User {
    @Id
    private String id;
    private String name;
    private String content;

    // constructors
    public User() {}
    public User(String name, String content) {
        this.name = name;
        this.content = content;
    }

    // getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
