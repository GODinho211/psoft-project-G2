package com.example.projetopsoft2024.Controllers;

import com.example.projetopsoft2024.Service.BookService;
import com.example.projetopsoft2024.models.Book;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.List;
import java.util.Optional;

import java.util.Map;
import com.example.projetopsoft2024.models.Author;
import com.example.projetopsoft2024.Service.AuthorService;
import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.models.Gender;



@RestController
@RequestMapping("api/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @Autowired
  private AuthorService authorService;

  @Autowired
  private GenderRepository genderRepository;


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
  public ResponseEntity<String> createBooks(@RequestBody Map<String, Object> payload){
    long isbn = Long.parseLong(payload.get("isbn").toString());
    String title = payload.get("title").toString();
    String description = payload.get("description").toString();
    long authorId = Long.parseLong(payload.get("authorId").toString());
    String genderId = payload.get("genderId").toString();

    Author author = authorService.getAuthorById(authorId).orElse(null);
    if (author == null) {
      return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
    }

    List<Gender> genders = genderRepository.findByGenderId(genderId);
    if (genders.isEmpty()) {
      return new ResponseEntity<>("Gender not found", HttpStatus.NOT_FOUND);
    }

    Book book = new Book(isbn, title, description);
    book.setAuthor(author);
    book.setGender(genders);

    Book createdBook = bookService.createBook(book);
    if (createdBook != null) {
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

  @PutMapping("/{bookId}")
  public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book bookDetails) {
    try {
      Book existingBook = bookService.getBookById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
      existingBook.setTitle(bookDetails.getTitle());
      existingBook.setDescription(bookDetails.getDescription());
      // Merge existing and new genders
      List<Gender> mergedGenders = Stream.concat(existingBook.getGender().stream(), bookDetails.getGender().stream())
              .distinct()
              .collect(Collectors.toList());
      existingBook.setGender(mergedGenders);
      Book updatedBook = bookService.saveBook(existingBook);
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