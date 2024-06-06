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
import java.util.Optional;


@Component

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
        User user2 = userRepository.findByEmail("carlos@example.com");
        User user3 = userRepository.findByEmail("pedro@example.com");
        User user4 = userRepository.findByEmail("diana@example.com");

        if (user1 == null || user2 == null|| user3 == null) {
            System.out.println("Usuário não encontrado: alice@example.com");
            return;
        }

        // Recuperar livros existentes
        Optional<Book> bookOpt = bookRepository.findById(9780061120084L);
        Optional<Book> bookOpt1 = bookRepository.findById(9780451524935L);
        Optional<Book> bookOpt2 = bookRepository.findById(9780062316097L);
        Optional<Book> bookOpt3 = bookRepository.findById(9780399590504L);
        Optional<Book> bookOpt4 = bookRepository.findById(9780590353427L);

        if (!bookOpt.isPresent()) {
            System.out.println("Livro não encontrado com ISBN: 1L");
            return;
        }
        Book book1 = bookOpt.get();
        Book book2 = bookOpt1.get();
        Book book3 = bookOpt2.get();
        Book book4 = bookOpt3.get();
        Book book5 = bookOpt4.get();

        System.out.println("Livro encontrado: " + book1.getTitle());

        // Criar instâncias de Lending
        Lending lending1 = new Lending(user1, Arrays.asList(book1), LocalDate.of(2024, 1, 1), LocalDate.of(2024, 3, 27));
        Lending lending2 = new Lending(user2, Arrays.asList(book2), LocalDate.of(2024, 2, 1), LocalDate.of(2024, 3, 27));
        Lending lending3 = new Lending(user3, Arrays.asList(book4), LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 27));
        Lending lending4 = new Lending(user1, Arrays.asList(book3), LocalDate.of(2024, 4, 1), LocalDate.of(2024, 3, 27));
        Lending lending5 = new Lending(user2, Arrays.asList(book1), LocalDate.of(2024, 5, 1), LocalDate.of(2024, 3, 27));
        Lending lending6 = new Lending(user4, Arrays.asList(book5), LocalDate.of(2024, 6, 1), LocalDate.of(2024, 3, 27));
        Lending lending7 = new Lending(user4, Arrays.asList(book1), LocalDate.of(2024, 6, 1), LocalDate.of(2024, 3, 27));
        Lending lending8 = new Lending(user3, Arrays.asList(book4), LocalDate.of(2024, 11, 1), LocalDate.of(2024, 3, 27));

        // Calcular multa, se houver
        lending1.fineCalc(lending1.getStartDate(), lending1.getReturnDate());
        lending2.fineCalc(lending2.getStartDate(), lending2.getReturnDate());
        lending3.fineCalc(lending3.getStartDate(), lending3.getReturnDate());
        lending4.fineCalc(lending4.getStartDate(), lending4.getReturnDate());
        lending5.fineCalc(lending5.getStartDate(), lending5.getReturnDate());
        lending6.fineCalc(lending6.getStartDate(), lending6.getReturnDate());
        lending7.fineCalc(lending7.getStartDate(), lending7.getReturnDate());
        lending8.fineCalc(lending8.getStartDate(), lending8.getReturnDate());

        System.out.println("Multa calculada para o empréstimo: " + lending1.getFine());

        // Salvar as instâncias de Lending no banco de dados
        lendingRepository.saveAll(Arrays.asList(lending1,lending2,lending3,lending4,lending5,lending6,lending7,lending8));
        System.out.println("Empréstimo salvo: " + lending1.getLendingId());
    }
}