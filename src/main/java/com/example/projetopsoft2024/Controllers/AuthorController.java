package com.example.projetopsoft2024.Controllers;

import com.example.projetopsoft2024.Service.AuthorService;

import com.example.projetopsoft2024.models.Author;

import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.DTO.AuthorDTO;
import com.example.projetopsoft2024.models.Requests.AuthorRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Tag(name = "Author", description = "Endpoints for managing authors.")
@RestController
@RequestMapping("api/author")
public class AuthorController {

    //Boas

    @Autowired
    private AuthorService authorService;
    @Operation(summary = "Get a list of all authors")
    @GetMapping()
    public List<AuthorDTO> getAuthors() {
        return authorService.getAuthors();
    }
    @Operation(summary = "Create a new author with an optional photo")
    @PostMapping()
    public ResponseEntity<String> createAuthor(@RequestParam(value = "photo", required = false) MultipartFile photo, @ModelAttribute AuthorRequest authorRequest ){
        byte[] photoBytes = null;
        try {
            if (photo != null && !photo.isEmpty()) {
                // Check photo format
                String contentType = photo.getContentType();
                if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
                    return new ResponseEntity<>("Unsupported photo format", HttpStatus.BAD_REQUEST);
                }
                // Check photo size
                if (photo.getSize() > 20 * 1024) { // 20 KB limit
                    return new ResponseEntity<>("Photo size exceeds 20 KB", HttpStatus.BAD_REQUEST);
                }
                photoBytes = photo.getBytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload photo", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        AuthorDTO createdAuthor = authorService.createAuthor(authorRequest, photoBytes);
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
    @Operation(summary = "Find authors by name")
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getAuthorByName(@PathVariable String name) {
        List<Author> authors = authorService.findByName(name);
        if (!authors.isEmpty()) {
            return ResponseEntity.ok(authors);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No authors found with name " + name);
        }
    }
    @Operation(summary = "Get an author by ID")
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.getAuthorById(id);
        if (author.isPresent()) {
            return ResponseEntity.ok(author.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author with id " + id + " not found");
        }
    }
    @Operation(summary = "Update an author by ID with an optional photo")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable Long id,
                                               @RequestParam("name") String name,
                                               @RequestParam("bio") String bio,
                                               @RequestParam(value = "photo", required = false) MultipartFile photo) {
        AuthorRequest updatedAuthorRequest = new AuthorRequest();
        updatedAuthorRequest.setName(name);
        updatedAuthorRequest.setBio(bio);
        updatedAuthorRequest.setPhoto(photo);
        AuthorDTO updatedAuthor = authorService.updateAuthor(id, updatedAuthorRequest);
        if (updatedAuthor != null) {
            return ResponseEntity.ok("Author updated!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
    }
    @Operation(summary = "Get the photo of an author by ID")
    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> getAuthorPhoto(@PathVariable Long id) {
        Optional<Author> optionalAuthor = authorService.getAuthorById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            byte[] photo = author.getPhoto();
            if (photo != null && photo.length > 0) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);
                headers.setContentLength(photo.length);
                return new ResponseEntity<>(photo, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Operation(summary = "Get books by author name")
    @GetMapping("/{name}/books")
    public ResponseEntity<?> getBooksByAuthor(@PathVariable String name) {
        List<Book> books = authorService.findBooksByAuthor(name);
        if (!books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No books found for author with name " + name);
        }
    }



}