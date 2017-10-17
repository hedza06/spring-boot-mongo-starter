package com.springbootmongo.starter.service;

import com.springbootmongo.starter.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    List<User> findAll();

    User createUser(User user);

}
