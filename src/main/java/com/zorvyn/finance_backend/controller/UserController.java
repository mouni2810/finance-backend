package com.zorvyn.finance_backend.controller;
import com.zorvyn.finance_backend.model.Role;
import com.zorvyn.finance_backend.model.User;
import com.zorvyn.finance_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user, @RequestParam Role role) {
        if (role != Role.ADMIN) {
            throw new RuntimeException("Only ADMIN can create users");
        }
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
}