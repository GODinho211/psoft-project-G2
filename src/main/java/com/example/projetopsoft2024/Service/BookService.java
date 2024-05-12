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
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenderRepository GenderRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    //@Transactional
    public List<Book> getAllBooks() {
        //List<Book> books = new ArrayList<Book>();
        //bookRepository.findAll().forEach(n -> {
        //  n.getGender();//.size(); // force initialization of the gender collection
        //  books.add(n);
        //});
        //return books;
        return bookRepository.findAllWithGender();
    }


    @Transactional
    public Optional<Book> getBookById(long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            book.get().getGender();//.size();
        }
        return book;
    }

    //@Transactional
    //public Book updateBook(long bookId, Book updatedBook) {
    //Optional<Book> bookOptional = bookRepository.findById(bookId);
    //if (!bookOptional.isPresent()) {
    //throw new RuntimeException("Book not found with id " + bookId);
    //}

    //Book existingBook = bookOptional.get();
    //existingBook.setTitle(updatedBook.getTitle());
    //existingBook.setDescription(updatedBook.getDescription());
    //existingBook.setGender(updatedBook.getGender());
    //existingBook.setAuthor(updatedBook.getAuthor());
    //bookRepository.save(existingBook);
    //return existingBook;
    //}

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

    //@Transactional
    public List<Book> getBooksByGender(Long genderId) {
        Gender gender = GenderRepository.findById(genderId)
                .orElseThrow(() -> new RuntimeException("Gender not found with id " + genderId));
        return bookRepository.findByGender(gender);
    }

    public List<Book> findByGenderDescription(String description) {
        return bookRepository.findByGenderDescription(description);
    }

}