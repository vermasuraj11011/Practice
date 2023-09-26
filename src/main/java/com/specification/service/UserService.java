package com.specification.service;

import com.specification.entities.User;
import com.specification.repo.GenericSpecification;
import com.specification.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User getUser(int id) throws Exception {
        return userRepo.findById(id).orElseThrow(() -> new Exception("user not found"));
    }

    public List<User> getUsers() {
        return this.userRepo.findAll();
    }

    public User create(User user) {
        return this.userRepo.save(user);
    }

    public User updateUser(int id, User user) throws Exception {
        User user1 = getUser(id);
        if (user.getAge() != 0) user1.setAge(user.getAge());
        if (user.getEmail() != null) user1.setEmail(user.getEmail());
        if (user.getName() != null) user1.setName(user.getName());
        if (user.getRole() != null) user1.setRole(user.getRole());
        return this.userRepo.save(user1);
    }

    public void delete(User user) throws Exception {
        User user1 = this.getUser(user.getId());
        this.userRepo.delete(user1);
    }

    public List<User> getUsersWithNameGeneric(String name) {
        GenericSpecification<User> userNameSpecification = new GenericSpecification<>("name", name);
        return this.userRepo.findAll(where(userNameSpecification));
    }
}
