package com.example.projetopsoft2024.Controllers;

import com.example.projetopsoft2024.Service.AuthorService;

import com.example.projetopsoft2024.models.Author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String createAuthor(@RequestBody Author author ){
        return authorService.createAuthor(author);
    }

    @DeleteMapping("/{authorId}")
    public String deleteAuthor(@PathVariable long authorId){
        return authorService.deleteAuthor(authorId);
    }


}