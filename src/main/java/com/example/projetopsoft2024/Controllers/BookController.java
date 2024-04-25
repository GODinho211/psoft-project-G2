package com.example.projetopsoft2024.Controllers;

import com.example.projetopsoft2024.Service.BookService;
import com.example.projetopsoft2024.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping()
  public List<Book> getBooks(){
    return bookService.getAllBooks();
  }
  @PostMapping()
  public String createBooks(Book book){
    return bookService.createBook(book);
  }

  @DeleteMapping("/{bookId}")
  public String deleteBook(@PathVariable long bookId){
    return bookService.deleteBook(bookId);
  }

  @GetMapping("/{bookId}")
  public ResponseEntity<?> getBookById(@PathVariable long bookId){
    Optional<Book> book = bookService.getBookById(bookId);
    if(book.isPresent()){
      return ResponseEntity.ok(book.get());
    }else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with id " + bookId + " not found");
    }
  }


}