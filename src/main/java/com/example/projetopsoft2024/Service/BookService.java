package com.example.projetopsoft2024.Service;

import com.example.projetopsoft2024.Repositories.BookRepository;
import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.projetopsoft2024.models.Entitys.Lending;


import java.util.LinkedHashMap;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.projetopsoft2024.Repositories.LendingRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenderRepository GenderRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    @Transactional
    public Optional<Book> getBookById(long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            book.get().getGender();//.size();
        }
        return book;
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }



    @Transactional
    public Book updateBook(long bookId, Book updatedBook) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (!bookOptional.isPresent()) {
            throw new RuntimeException("Book not found with id " + bookId);
        }

        Book existingBook = bookOptional.get();
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setDescription(updatedBook.getDescription());
        existingBook.setGender(updatedBook.getGender());
        existingBook.setAuthor(updatedBook.getAuthor());
        bookRepository.save(existingBook);
        return existingBook;
    }

    public Book createBook(Book book) {
        Optional<Book> existingBook = bookRepository.findByIsbn(book.getIsbn());
        if (existingBook.isPresent()) {
            throw new IllegalArgumentException("A book with this ISBN already exists");
        }
        return bookRepository.save(book);
    }


    public String deleteBook(long bookId) {
        bookRepository.deleteById(bookId);
        return "Book deleted";
    }


    public List<Book> getBooksByGender(Long genderId) {
        Gender gender = GenderRepository.findById(genderId)
                .orElseThrow(() -> new RuntimeException("Gender not found with id " + genderId));
        return bookRepository.findByGender(gender);
    }

    public List<Book> findByGenderDescription(String description) {
        return bookRepository.findByGenderDescription(description);
    }


    public Map<String, Long> getTop5Genders() {
        List<Book> allBooks = getAllBooks();

        return allBooks.stream()
                .flatMap(book -> book.getGender().stream())
                .collect(Collectors.groupingBy(Gender::getDescription, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(
                        (entry) -> entry.getKey(),
                        (entry) -> entry.getValue(),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    @Autowired
    private LendingRepository lendingRepository;

    public List<Book> getTop5LentBooks() {
        LocalDate oneYearAgo = LocalDate.now().minus(1, ChronoUnit.YEARS);

        return lendingRepository.findAll().stream()
                .filter(lending -> lending.getLendDate().isAfter(oneYearAgo))
                .collect(Collectors.groupingBy(Lending::getBook, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Book, Long>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public Book updateBookPicture(long bookId, byte[] picture) {//
        Optional<Book> bookOptional = bookRepository.findById(bookId);//
        if (!bookOptional.isPresent()) {//
            throw new RuntimeException("Book not found with id " + bookId);//
        }//
//comentario
        Book existingBook = bookOptional.get();//
        existingBook.setPicture(picture);//
        bookRepository.save(existingBook);//
        return existingBook;//
    }//




}