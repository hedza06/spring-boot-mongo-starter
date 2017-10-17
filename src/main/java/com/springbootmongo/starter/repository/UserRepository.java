package com.springbootmongo.starter.repository;

import com.springbootmongo.starter.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    Optional<User> findFirstByUsername(String username);

}
