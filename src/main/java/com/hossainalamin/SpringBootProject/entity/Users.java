package com.hossainalamin.SpringBootProject.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class Users {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    public String userName;
    @NonNull
    public String password;
    @DBRef
    List<JournalEntry> journalEntries = new ArrayList<>();
}
