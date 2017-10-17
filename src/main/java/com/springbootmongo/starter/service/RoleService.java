package com.springbootmongo.starter.service;

import com.springbootmongo.starter.domain.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleService {

    List<Role> findAll();

}
