package com.springbootmongo.starter.repository;

import com.springbootmongo.starter.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoleRepository extends MongoRepository<Role, Long> {

    // CRUD method definition.

}
