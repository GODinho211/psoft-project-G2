package com.example.projetopsoft2024.Controllers;

import com.example.projetopsoft2024.Service.AuthorService;

import com.example.projetopsoft2024.models.Author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/author")
public class AuthorController {

    //Boas

    @Autowired
    private AuthorService authorService;

    @GetMapping()
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }
    @PostMapping()
    public ResponseEntity<String> createAuthor(@RequestBody Author author ){
        Author createdAuthor = authorService.createAuthor(author);
        if (createdAuthor != null) {
            return new ResponseEntity<>("Author created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create Author", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{authorId}")
    public String deleteAuthor(@PathVariable long authorId){
        return authorService.deleteAuthor(authorId);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getAuthorByName(@PathVariable String name) {
        List<Author> authors = authorService.findByName(name);
        if (!authors.isEmpty()) {
            return ResponseEntity.ok(authors);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No authors found with name " + name);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.getAuthorById(id);
        if (author.isPresent()) {
            return ResponseEntity.ok(author.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author with id " + id + " not found");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable Long id, @RequestBody Author updatedAuthor) {
        Optional<Author> optionalAuthor = authorService.getAuthorById(id);
        if (optionalAuthor.isPresent()) {
            Author existingAuthor = optionalAuthor.get();
            existingAuthor.updateName(updatedAuthor.getName());
            existingAuthor.setBio(updatedAuthor.getBio());
            authorService.updateAuthor(id, existingAuthor);
            return ResponseEntity.ok("Author updated!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
    }



}