package com.example.rest_dbref.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String password;

    @Indexed(unique = true)
    @NonNull
    private String email;

    @Builder.Default
    @DBRef
    private List<Book> books = new ArrayList<>();
}
