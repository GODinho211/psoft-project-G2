package com.example.projetopsoft2024.Controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.example.projetopsoft2024.models.User;
import com.example.projetopsoft2024.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/users")
public class UserController {


    @Autowired
    private UserService userservice;


   @GetMapping(value = "/all")
    public List<User> getUsers() {
        return userservice.getAllUsers();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = userservice.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + id + " not found");
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getUsersByName(@PathVariable String name) {
        List<User> users = userservice.findByName(name);
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found with name " + name);
        }
    }

    @PostMapping()
    public ResponseEntity<?> createUsers(@RequestBody User user) {
        try {
            userservice.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> assignedReadnumber(@PathVariable Long id, @RequestBody User assignedReadnumber) {
        try {
            userservice.assignedReadnumber(id, assignedReadnumber);
            return ResponseEntity.ok("User Reader Number updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceUser(@PathVariable Long id, @RequestBody User user) {
        try {
            userservice.replaceUser(id, user);
            return ResponseEntity.ok("User replaced successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error replacing user: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userservice.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user: " + e.getMessage());
        }
    }


}

