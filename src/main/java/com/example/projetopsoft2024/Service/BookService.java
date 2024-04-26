package com.example.projetopsoft2024.Service;

import com.example.projetopsoft2024.Repositories.BookRepository;
import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenderRepository GenderRepository;

    @Transactional
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

    @Transactional
    public List<Book> getBooksByGender(Long genderId) {
        Gender gender = GenderRepository.findById(genderId)
                .orElseThrow(() -> new RuntimeException("Gender not found with id " + genderId));
        return bookRepository.findByGender(gender);
    }
}

