package com.s21.sda.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.s21.sda.models.User;

public interface UserRepository extends MongoRepository<User, String>{
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
