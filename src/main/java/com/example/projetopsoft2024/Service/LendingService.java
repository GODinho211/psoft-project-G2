package com.example.projetopsoft2024.Service;


import com.example.projetopsoft2024.Repositories.BookRepository;
import com.example.projetopsoft2024.Repositories.LendingRepository;
import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Entitys.Lending;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Lending findLendingByLendId(long lendingId){
        return lendingRepository.findById(lendingId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<Book> findBooksByIds(List<Long> bookIds) {
        return bookRepository.findAllById(bookIds);
    }

    public void saveLending(Lending lending) {
        lendingRepository.save(lending);
    }
    public List<Lending> getAll() {
        return lendingRepository.findAll();
    }

    public List<Lending> findLendingByBookId(Long bookId) {
        return lendingRepository.findLendingByBookId(bookId);
    }

    public List<Lending> findLendingByUserId(Long userId) {
        return lendingRepository.findLendingByUserId(userId);
    }


    public List<Lending> findOverdueLendings() {
        return lendingRepository.findAll().stream()
                .filter(lending -> lending.getReturnDate() == null)
                .sorted(Comparator.comparingLong(Lending::getDaysOverdue).reversed())
                .collect(Collectors.toList());
    }
}
