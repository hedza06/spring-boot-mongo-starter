package com.springbootmongo.starter.implementation;

import com.springbootmongo.starter.domain.User;
import com.springbootmongo.starter.repository.UserRepository;
import com.springbootmongo.starter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate; // helper class for CRUD operations.

    @Autowired
    private UserRepository userRepository;


    /**
     * Method for creating new user.
     *
     * @param user - user object to be created
     * @return inserted user object
     */
    @Override
    public User createUser(User user) {
        return null;
    }
}
