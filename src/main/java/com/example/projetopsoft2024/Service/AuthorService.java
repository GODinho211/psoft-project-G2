package com.example.projetopsoft2024.Service;

import com.example.projetopsoft2024.Repositories.AuthorRepository;
import com.example.projetopsoft2024.models.Author;
import com.example.projetopsoft2024.models.DTO.AuthorDTO;
import com.example.projetopsoft2024.models.Requests.AuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.server.NotFoundException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    public AuthorRepository authorRepository;

    public List<AuthorDTO> getAuthors() {
        List<AuthorDTO> authors = new ArrayList<>();
        authorRepository.findAll().forEach(n -> authors.add(convertToDTO(n)));
        return authors;
    }

    public AuthorDTO createAuthor(AuthorRequest authorRequest, byte[] photo) {
        Author author = new Author(authorRequest.getName(), authorRequest.getBio(), photo);
        Author createdAuthor = authorRepository.save(author);
        return convertToDTO(createdAuthor);
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


    public AuthorDTO updateAuthor(Long id, AuthorRequest updatedAuthorRequest) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author existingAuthor = optionalAuthor.get();
            existingAuthor.updateName(updatedAuthorRequest.getName());
            existingAuthor.setBio(updatedAuthorRequest.getBio());
            authorRepository.save(existingAuthor);
            return convertToDTO(existingAuthor);
        } else {
            return null;
            //throw new NotFoundException("Author not found");
        }
    }
    private AuthorDTO convertToDTO(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setIdAuthor(author.getIdAuthor());
        dto.setName(author.getName());
        dto.setBio(author.getBio());
        return dto;
    }
}
