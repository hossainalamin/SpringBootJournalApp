package com.hossainalamin.SpringBootProject.repository;

import com.hossainalamin.SpringBootProject.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {
}
