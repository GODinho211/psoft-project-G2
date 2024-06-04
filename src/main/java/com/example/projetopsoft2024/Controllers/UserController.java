package com.example.projetopsoft2024.Controllers;



import com.example.projetopsoft2024.models.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.example.projetopsoft2024.models.User;
import com.example.projetopsoft2024.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "User", description = "Endpoints for managing users.")
@RestController
@RequestMapping("api/users")
public class UserController {


    @Autowired
    private UserService userservice;

    @Operation(summary = "Get all users")
   @GetMapping(value = "/all")
    public List<User> getUsers() {
        return userservice.getAllUsers();
    }

    @Operation(summary = "Get user by readernumber ")
    @GetMapping("/readernumber/{readernumber}")
    public ResponseEntity<?> getUserByReadernumber(@PathVariable Long readernumber) {
        Optional<User> user = userservice.getUserByReadernumber(readernumber);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + readernumber + " not found");
        }
    }

    @Operation(summary = "Get users by phone number")
    @GetMapping("/phonenumber/{phonenumber}")
    public ResponseEntity<?> getUsersByPhonenumber(@PathVariable Long phonenumber) {
        List<User> users = userservice.getUsersByPhonenumber(phonenumber);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found with phone number " + phonenumber);
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @Operation(summary = "Get user by email ")
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userservice.getUserByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + email + " not found");
        }
    }

    @Operation(summary = "Get user by name")
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getUsersByName(@PathVariable String name) {
        List<User> users = userservice.findByName(name);
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found with name " + name);
        }
    }

    @Operation(summary = "Create a user")
    @PostMapping()
    public ResponseEntity<?> createUsers(@RequestBody User user) {
        try {
            userservice.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }
    }
    @Operation(summary = "Replace user info")
    @PutMapping("/{readernumber}")
    public ResponseEntity<?> replaceUser(@PathVariable Long readernumber, @RequestBody User user) {

        User currentUser = userservice.getCurrentAuthenticatedUser();
        if (!currentUser.getReadernumber().equals(readernumber)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }

        try {
            userservice.replaceUser(readernumber, user);
            return ResponseEntity.ok("User replaced successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error replacing user: " + e.getMessage());
        }
    }


    @Operation(summary = "Delete a user")
    @DeleteMapping("/{readernumber}")
    public ResponseEntity<?> deleteUser(@PathVariable Long readernumber) {
        try {
            userservice.deleteUser(readernumber);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user: " + e.getMessage());
        }
    }

    @GetMapping("/books/{readernumber}")
    public ResponseEntity<?> getBooksByUserGenres(@PathVariable Long readernumber) {
        User currentUser = userservice.getCurrentAuthenticatedUser();
        if (!currentUser.getReadernumber().equals(readernumber)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }

        List<Book> books = userservice.getBooksByUserGenres(readernumber);
        return ResponseEntity.ok(books);
    }


}

