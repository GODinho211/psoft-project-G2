package com.example.projetopsoft2024.Service;

import com.example.projetopsoft2024.Repositories.AuthorRepository;
import com.example.projetopsoft2024.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    public AuthorRepository authorRepository;

    public List<Author> getAuthors(){
        List<Author> authors = new ArrayList<>();
        authorRepository.findAll().forEach(n -> authors.add(n));
        return  authors;
    }

    public String createAuthor(Author author) {
        authorRepository.save(author);
        return "Author created!";
    }

    public String deleteAuthor(long authorId) {
        authorRepository.deleteById(authorId);
        return "User deleted";

    }
}
