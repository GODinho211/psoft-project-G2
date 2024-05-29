package com.example.projetopsoft2024.Bootstrap;

import com.example.projetopsoft2024.Repositories.AuthorRepository;
import com.example.projetopsoft2024.Repositories.BookRepository;
import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.models.Author;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BookDataLoader implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final GenderRepository genderRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookDataLoader(BookRepository bookRepository, GenderRepository genderRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genderRepository = genderRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize books
        List<Book> books = Arrays.asList(
                createBook("0-061-96436-0", "Title 1", 1L, "Description 1", 1L),
                createBook("0-545-01022-5", "Title 2", 1L, "Description 2", 1L),
                createBook("1-56619-909-3", "Title 3", 1L, "Description 3", 1L)
                // Add more books as needed
        );

        // Save the books to the database
        bookRepository.saveAll(books);
    }

    private Book createBook(String isbn, String title, Long authorId, String description, Long genderId) {
        Book book = new Book(title, description); // Create the book with title and description
        book.setIsbn(isbn); // Set the isbn separately
        Gender gender = genderRepository.findById(genderId).orElse(null);
        book.setGender(Arrays.asList(gender));
        Author author = authorRepository.findById(authorId).orElse(null);
        book.setAuthor(author);
        return book;
    }
}