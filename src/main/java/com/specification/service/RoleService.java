package com.specification.service;

import com.specification.entities.Role;
import com.specification.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role getRole(int id) throws Exception {
        return this.roleRepo.findById(id).orElseThrow(() -> new Exception("Role not found with id : " + id));
    }


}
