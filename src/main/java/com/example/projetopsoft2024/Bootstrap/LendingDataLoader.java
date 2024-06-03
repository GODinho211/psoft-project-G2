package com.example.projetopsoft2024.Bootstrap;

import com.example.projetopsoft2024.Repositories.BookRepository;
import com.example.projetopsoft2024.Repositories.LendingRepository;
import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Entitys.Lending;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@Component
@DependsOn({"userDataLoader", "bookDataLoader"})
public class LendingDataLoader implements CommandLineRunner {
    private final LendingRepository lendingRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LendingDataLoader(LendingRepository lendingRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.lendingRepository = lendingRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Recuperar usuários existentes
        User user1 = userRepository.findByEmail("alice@example.com");
        User user2= userRepository.findByEmail("carlos@example.com");
        User user3= userRepository.findByEmail("pedro@example.com");

        // Recuperar livros existentes
        List<Book> books1 = bookRepository.findAllById(Arrays.asList(1L));
        List<Book> books2 = bookRepository.findAllById(Arrays.asList(2L, 3L));
        List<Book> books3 = bookRepository.findAllById(Arrays.asList(1L, 3L));
        List<Book> books4 = bookRepository.findAllById(Arrays.asList(2L, 3L));

        // Criar instâncias de Lending
        Lending lending1 = new Lending(user1, books1,LocalDate.of(2024, 2, 1),LocalDate.of(2024, 3, 27));
        Lending lending2 = new Lending(user2,books2,LocalDate.of(2024, 3, 1),LocalDate.of(2024, 4, 27));
        Lending lending3 = new Lending(user1, books2,LocalDate.of(2024, 4, 1),LocalDate.of(2024, 5, 27));
        Lending lending4 = new Lending(user1,books3,LocalDate.of(2024, 6, 1),LocalDate.of(2024, 6, 27));
        Lending lending5 = new Lending(user2, books3,LocalDate.of(2024, 1, 14),LocalDate.of(2024, 6, 27));
        Lending lending6 = new Lending(user3,books4,LocalDate.of(2024, 8, 14),LocalDate.of(2024, 9, 27));


        lending1.fineCalc(lending1.getStartDate(), lending1.getReturnDate());
        lending2.fineCalc(lending2.getStartDate(), lending2.getReturnDate());
        lending3.fineCalc(lending3.getStartDate(), lending3.getReturnDate());
        lending4.fineCalc(lending4.getStartDate(), lending4.getReturnDate());
        lending5.fineCalc(lending5.getStartDate(), lending5.getReturnDate());
        lending6.fineCalc(lending6.getStartDate(), lending6.getReturnDate());
        ;


        // Salvar as instâncias de Lending no banco de dados
        lendingRepository.saveAll(Arrays.asList(lending1,lending2,lending3,lending4,lending5,lending6));
    }
}