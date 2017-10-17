package com.springbootmongo.starter.implementation;

import com.springbootmongo.starter.domain.Role;
import com.springbootmongo.starter.repository.RoleRepository;
import com.springbootmongo.starter.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }
}
