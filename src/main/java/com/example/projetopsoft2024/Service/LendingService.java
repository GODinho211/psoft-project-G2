package com.example.projetopsoft2024.Service;


import com.example.projetopsoft2024.Repositories.BookRepository;
import com.example.projetopsoft2024.Repositories.LendingRepository;
import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Lending;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LendingService {

    private final LendingRepository lendingRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LendingService(LendingRepository lendingRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.lendingRepository = lendingRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public boolean lendBooks(Long userId, Long bookId) {
        // Verifica se o usuário e o livro existem
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

        if (user == null || book == null) {
            return false;
        }


        Lending lending = new Lending();
        lending.setUser(user);
        lending.setStartDate(new Date());

        if (lending.getBooks() == null) {
            lending.setBooks(new ArrayList<>());
        }


        lending.getBooks().add(book);


        lendingRepository.save(lending);

        return true;
    }

    public List<Lending> getAll() {
        return lendingRepository.findAll();
    }
}
