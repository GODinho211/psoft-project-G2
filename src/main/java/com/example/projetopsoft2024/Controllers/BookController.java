package com.example.projetopsoft2024.Controllers;

import com.example.projetopsoft2024.Service.BookService;
import com.example.projetopsoft2024.models.Book;
import jakarta.transaction.Transactional;
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

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }



  @GetMapping("/getAll")
  public List<Book> getBooks(){
    return bookService.getAllBooks();
  }

  //@PostMapping()
  //public Book createBooks(@RequestBody Book book){
  //  return bookService.createBook(book);
  //}

  @PostMapping()
  public ResponseEntity<String> createBooks(@RequestBody Book book){
    Book createdBooks = bookService.createBook(book);
    if (createdBooks != null) {
      return new ResponseEntity<>("Book created successfully", HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>("Failed to create book", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{bookId}")
  public String deleteBook(@PathVariable long bookId){
    return bookService.deleteBook(bookId);
  }




  @Transactional
  @GetMapping("/{bookId}")
  public ResponseEntity<?> getBookById(@PathVariable long bookId){
    Optional<Book> book = bookService.getBookById(bookId);
    if(book.isPresent()){
      return ResponseEntity.ok(book.get());
    }else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with id " + bookId + " not found");
    }
  }
  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book book) {
    try {
      Book updatedBook = bookService.updateBook(id, book);
      return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/gender/{genderId}")
  public List<Book> getBooksByGender(@PathVariable Long genderId) {
    return bookService.getBooksByGender(genderId);
  }

  @GetMapping("/search")
  public ResponseEntity<List<Book>> findByGenderDescription(@RequestParam String description) {
    List<Book> books = bookService.findByGenderDescription(description);
    return ResponseEntity.ok(books);
  }

}