package com.example.projetopsoft2024.Service;

import com.example.projetopsoft2024.Repositories.BookRepository;
import com.example.projetopsoft2024.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
       return bookRepository.findAll();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public String deleteBook(long bookId) {
        bookRepository.deleteById(bookId);
        return "Book deleted";
    }
}

