package com.springbootmongo.starter.service;

import com.springbootmongo.starter.domain.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    User createUser(User user);

}
