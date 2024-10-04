package com.example.demo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, ObjectId> {

    List<Message> findByGroupId(ObjectId groupId);
}

/*
 * List<Message> findAll(): Retrieves all messages.
 * Optional<Message> findById(String id): Retrieves a message by its ID.
 * Message save(Message message): Saves a given message.
 * void deleteById(String id): Deletes a message by its ID.
 * void delete(Message message): Deletes a given message.
 * boolean existsById(String id): Checks if a message exists by its ID.
 * long count(): Returns the number of message
 */