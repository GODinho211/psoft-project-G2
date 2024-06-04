package com.example.projetopsoft2024.Bootstrap;

import com.example.projetopsoft2024.Repositories.AuthorRepository;
import com.example.projetopsoft2024.Repositories.BookRepository;
import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.models.Author;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.DependsOn;

import java.util.Arrays;
import java.util.List;

@Component
@DependsOn({"genderDataLoader", "authorDataLoader"})
public class BookDataLoader implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final GenderRepository genderRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookDataLoader(BookRepository bookRepository, GenderRepository genderRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genderRepository = genderRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Initialize books
        List<Book> books = Arrays.asList(
                createBook("978-0061120084", "To Kill a Mockingbird", 1L, "Description1", 1L,null),
                createBook("978-0451524935", "1984", 5L,"Description2",3L,null),
                createBook("978-0062316097","A Brief History of Humankind", 3L, "Description3",1L,null),
                createBook("978-0399590504","Educated",2L,"Description4",4L,null),
                createBook("978-0441013593","Dune",7L,"Description5",6L,null),
                createBook("978-0441569595","Neuromancer",3L,"Description6",2L,null),
                createBook("978-0547928227","The Hobbit",4L,"Description7",5L,null),
                createBook("978-0590353427","Harry Potter",6L,"Description8",5L,null),
                createBook("978-0307454546","The Girl with the Dragon Tattoo",3L,"Description9",3L,null),
                createBook("978-0307588371","Gone Girl",9L,"Description10",3L,null),
                createBook("978-1451648539","Steve Jobs",8L,"Description11",1L,null),
                createBook("978-1524763138","Becoming",10L,"Description12",4L,null),
                createBook("978-0812981605","The Power of Habit",3L,"Description13",1L,null),
                createBook("978-0735211292","Atomic Habits",7L,"Description14",2L,null),
                createBook("978-0375842207","The Book Thief",2L,"Description15",3L,null)





                // Add more books as needed
        );

        // Save the books to the database
        bookRepository.saveAll(books);
    }

    private Book createBook(String isbn, String title, Long authorId, String description, Long genderId, byte[] picture) {
        // If no picture is provided, use a default picture
        byte[] bookPicture = (picture != null) ? picture : new byte[0]; // replace new byte[0] with your default picture
        Book book = new Book(title, description, bookPicture); // Create the book with title, description, and picture
        book.setIsbn(isbn); // Set the isbn separately

        Gender gender = genderRepository.findById(genderId).orElse(null);
        if (gender != null) {
            book.setGender(Arrays.asList(gender));
            gender.getBooks().add(book); // Add the book to the gender's list of books
            bookRepository.save(book); // Save the book after associating it with the gender
        }

        Author author = authorRepository.findById(authorId).orElse(null);
        if (author != null) {
            book.setAuthor(author);
            // Instead of calling author.getBooks().add(book), we fetch the books from the database and add the new book
            List<Book> authorBooks = bookRepository.findByAuthor(author);
            authorBooks.add(book);
        }

        return book;
    }
}