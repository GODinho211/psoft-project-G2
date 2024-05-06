package com.example.projetopsoft2024.Service;

import com.example.projetopsoft2024.Repositories.AuthorRepository;
import com.example.projetopsoft2024.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    public AuthorRepository authorRepository;

    public List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        authorRepository.findAll().forEach(n -> authors.add(n));
        return authors;
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
        //return "Author created!";
    }

    public String deleteAuthor(long authorId) {
        authorRepository.deleteById(authorId);
        return "Author deleted";

    }
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }
    public List<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }


    public String updateAuthor(Long id, Author updatedAuthor) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author existingAuthor = optionalAuthor.get();
            existingAuthor.updateName(updatedAuthor.getName());
            existingAuthor.setBio(updatedAuthor.getBio());
            authorRepository.save(existingAuthor);
            return "Author updated!";
        } else {
            return "Author not found";
        }
    }
}
