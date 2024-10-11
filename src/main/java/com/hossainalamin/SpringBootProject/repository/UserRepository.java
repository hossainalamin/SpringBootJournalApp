package com.hossainalamin.SpringBootProject.repository;

import com.hossainalamin.SpringBootProject.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, ObjectId> {
}
