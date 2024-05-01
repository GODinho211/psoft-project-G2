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

  @GetMapping("/getAll")
  public List<Book> getBooks(){
    return bookService.getAllBooks();
  }

  @PostMapping()
  public Book createBooks(@RequestBody Book book){
    return bookService.createBook(book);
  }


}