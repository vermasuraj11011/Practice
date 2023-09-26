package com.specification.controller;

import com.specification.entities.User;
import com.specification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(
            @PathVariable Integer id
    ) throws Exception {
        return ResponseEntity.ok(this.userService.getUser(id));
    }

    @PostMapping("/")
    public ResponseEntity<User> addUser(
            @RequestBody User user
    ) throws Exception {
        return ResponseEntity.ok(this.userService.create(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Integer id,
            @RequestBody User user
    ) throws Exception {
        return ResponseEntity.ok(this.userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Integer id
    ) throws Exception {
        User user = this.userService.getUser(id);
        this.userService.delete(user);
        return ResponseEntity.ok("User deleted");
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<List<User>> getUserByName(@PathVariable String name) {
        return ResponseEntity.ok(this.userService.getUsersWithNameGeneric(name));
    }

}
