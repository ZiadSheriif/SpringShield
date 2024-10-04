package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}

/*
 * List<User> findAll(): Retrieves all users.
 * Optional<User> findById(String id): Retrieves a user by its ID.
 * User save(User user): Saves a given user.
 * void deleteById(String id): Deletes a user by its ID.
 * void delete(User user): Deletes a given user.
 * boolean existsById(String id): Checks if a user exists by its ID.
 * long count(): Returns the number of users.
 
*/