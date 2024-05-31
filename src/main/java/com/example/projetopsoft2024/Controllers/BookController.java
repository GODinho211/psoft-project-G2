package com.example.projetopsoft2024.Controllers;

import com.example.projetopsoft2024.Repositories.BookRepository;
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

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;



@RestController
@RequestMapping("api/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @Autowired
  private AuthorService authorService;

  @Autowired
  private GenderRepository genderRepository;

  @Autowired
  private BookRepository bookRepository;


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

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<String> createBooks(@RequestParam("title") String title,
                                            @RequestParam("description") String description,
                                            @RequestParam("authorId") long authorId,
                                            @RequestParam("genderId") String genderId,
                                            @RequestParam("isbn") String isbn,
                                            @RequestParam("picture") MultipartFile pictureFile) {
    if (pictureFile.getSize() > 20000) { // Check if file size is greater than 20MB
      return new ResponseEntity<>("Picture size must not exceed 20KBytes", HttpStatus.BAD_REQUEST);
    }
    Author author = authorService.getAuthorById(authorId).orElse(null);
    if (author == null) {
      return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
    }

    List<Gender> genders = genderRepository.findByGenderId(genderId);
    if (genders.isEmpty()) {
      return new ResponseEntity<>("Gender not found", HttpStatus.NOT_FOUND);
    }

    byte[] picture = null;
    try {
      picture = pictureFile.getBytes();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Book book = new Book(title, description, picture);
    book.setAuthor(author);
    book.setGender(genders);
    book.setIsbn(isbn);

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

  @GetMapping("/title/{title}")
  public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title) {
    List<Book> books = bookService.getBooksByTitle(title);
    if (books.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(books, HttpStatus.OK);
  }

  @GetMapping("/top5Genders")
  public ResponseEntity<Map<String, Long>> getTop5Genders() {
    Map<String, Long> top5Genders = bookService.getTop5Genders();

    if (top5Genders.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(top5Genders, HttpStatus.OK);
  }

  @GetMapping("/author/{authorName}")
  public ResponseEntity<?> getBooksByAuthor(@PathVariable String authorName) {
    List<Book> books = bookRepository.findByAuthorName(authorName);
    if (!books.isEmpty()) {
      return ResponseEntity.ok(books);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No books found by author " + authorName);
    }



    }
}