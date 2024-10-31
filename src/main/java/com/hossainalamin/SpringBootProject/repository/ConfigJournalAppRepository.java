package com.hossainalamin.SpringBootProject.repository;

import com.hossainalamin.SpringBootProject.entity.ConfigJournalApp;
import com.hossainalamin.SpringBootProject.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalApp, ObjectId> {
    ConfigJournalApp findByKey(String key);

    void deleteUserByKey(String key);
}
