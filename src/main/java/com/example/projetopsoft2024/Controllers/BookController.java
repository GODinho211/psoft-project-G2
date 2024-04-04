package com.example.projetopsoft2024.Controllers;

import com.example.projetopsoft2024.Service.BookService;
import com.example.projetopsoft2024.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {
  //asdasd
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




}
