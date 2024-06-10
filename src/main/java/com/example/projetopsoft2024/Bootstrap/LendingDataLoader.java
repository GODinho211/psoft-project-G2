package com.example.projetopsoft2024.Bootstrap;

import com.example.projetopsoft2024.Repositories.BookRepository;
import com.example.projetopsoft2024.Repositories.LendingRepository;
import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Entitys.Lending;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

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
        User user5 = userRepository.findByEmail("isabela@example.com");
        User user6 = userRepository.findByEmail("joao@example.com");
        User user7 = userRepository.findByEmail("ana@example.com");
        User user8 = userRepository.findByEmail("sofia@example.com");
        User user9 = userRepository.findByEmail("tiago@example.com");
        User user10 = userRepository.findByEmail("clara@example.com");


        // Recuperar livros existentes
        Optional<Book> bookOpt = bookRepository.findById(9780061120084L);
        Optional<Book> bookOpt1 = bookRepository.findById(9780451524935L);
        Optional<Book> bookOpt2 = bookRepository.findById(9780062316097L);
        Optional<Book> bookOpt3 = bookRepository.findById(9780399590504L);
        Optional<Book> bookOpt4 = bookRepository.findById(9780590353427L);
        Optional<Book> bookOpt5 = bookRepository.findById(9780307454546L);
        Optional<Book> bookOpt6 = bookRepository.findById(9780307588371L);
        Optional<Book> bookOpt7 = bookRepository.findById(9781451648539L);
        Optional<Book> bookOpt8 = bookRepository.findById(9780812981605L);
        Optional<Book> bookOpt9 = bookRepository.findById(9780735211292L);
        Optional<Book> bookOpt10 = bookRepository.findById(9780375842207L);



        if (!bookOpt.isPresent()) {
            System.out.println("Livro não encontrado com ISBN: 1L");
            return;
        }
        Book book = bookOpt.get();
        Book book1 = bookOpt1.get();
        Book book2 = bookOpt2.get();
        Book book3 = bookOpt3.get();
        Book book4 = bookOpt4.get();
        Book book5 = bookOpt5.get();
        Book book6 = bookOpt6.get();
        Book book7 = bookOpt7.get();
        Book book8 = bookOpt8.get();
        Book book9 = bookOpt9.get();
        Book book10 = bookOpt10.get();



        System.out.println("Livro encontrado: " + book1.getTitle());

        // Criar instâncias de Lending
        Lending lending1 = new Lending(user1, Arrays.asList(book), LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 30));
        Lending lending2 = new Lending(user2, Arrays.asList(book1), LocalDate.of(2024, 2, 1), LocalDate.of(2024, 3, 24));
        Lending lending3 = new Lending(user3, Arrays.asList(book2), LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 12));
        Lending lending4 = new Lending(user1, Arrays.asList(book3), LocalDate.of(2024, 4, 1), LocalDate.of(2024, 6, 29));
        Lending lending5 = new Lending(user2, Arrays.asList(book4), LocalDate.of(2024, 5, 1), LocalDate.of(2024, 6, 14));
        Lending lending6 = new Lending(user4, Arrays.asList(book5), LocalDate.of(2024, 6, 1), LocalDate.of(2024, 7, 10));
        Lending lending7 = new Lending(user4, Arrays.asList(book6), LocalDate.of(2024, 6, 1), LocalDate.of(2024, 10, 5));
        Lending lending8 = new Lending(user3, Arrays.asList(book7), LocalDate.of(2024, 11, 1), LocalDate.of(2024, 12, 24));
        Lending lending9 = new Lending(user4, Arrays.asList(book8), LocalDate.of(2024, 11, 1), LocalDate.of(2024, 12, 21));
        Lending lending10 = new Lending(user5, Arrays.asList(book9), LocalDate.of(2024, 10, 1), LocalDate.of(2024, 11, 30));
        Lending lending11 = new Lending(user5, Arrays.asList(book10), LocalDate.of(2024, 10, 1), LocalDate.of(2024, 10, 24));
        Lending lending12 = new Lending(user6, Arrays.asList(book1), LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 25));
        Lending lending13 = new Lending(user6, Arrays.asList(book2), LocalDate.of(2024, 6, 1), LocalDate.of(2024, 12, 29));
        Lending lending14 = new Lending(user6, Arrays.asList(book3), LocalDate.of(2024, 6, 1), LocalDate.of(2024, 3, 17));
        Lending lending15 = new Lending(user7, Arrays.asList(book4), LocalDate.of(2024, 6, 1), LocalDate.of(2024, 3, 14));
        Lending lending16 = new Lending(user8, Arrays.asList(book7), LocalDate.of(2024, 4, 1), LocalDate.of(2024, 4, 18));
        Lending lending17 = new Lending(user8, Arrays.asList(book7), LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 12));
        Lending lending18 = new Lending(user9, Arrays.asList(book7), LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 10));
        Lending lending19 = new Lending(user7, Arrays.asList(book8), LocalDate.of(2024, 7, 1), LocalDate.of(2024, 10, 27));
        Lending lending20 = new Lending(user10, Arrays.asList(book10), LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 26));
        Lending lending21 = new Lending(user10, Arrays.asList(book9), LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 22));


        // Calcular multa, se houver
        lending1.fineCalc(lending1.getStartDate(), lending1.getReturnDate());
        lending2.fineCalc(lending2.getStartDate(), lending2.getReturnDate());
        lending3.fineCalc(lending3.getStartDate(), lending3.getReturnDate());
        lending4.fineCalc(lending4.getStartDate(), lending4.getReturnDate());
        lending5.fineCalc(lending5.getStartDate(), lending5.getReturnDate());
        lending6.fineCalc(lending6.getStartDate(), lending6.getReturnDate());
        lending7.fineCalc(lending7.getStartDate(), lending7.getReturnDate());
        lending8.fineCalc(lending8.getStartDate(), lending8.getReturnDate());
        lending9.fineCalc(lending9.getStartDate(), lending9.getReturnDate());
        lending10.fineCalc(lending10.getStartDate(), lending10.getReturnDate());
        lending11.fineCalc(lending11.getStartDate(), lending11.getReturnDate());
        lending12.fineCalc(lending12.getStartDate(), lending12.getReturnDate());
        lending13.fineCalc(lending13.getStartDate(), lending13.getReturnDate());
        lending14.fineCalc(lending14.getStartDate(), lending14.getReturnDate());
        lending15.fineCalc(lending15.getStartDate(), lending15.getReturnDate());
        lending16.fineCalc(lending16.getStartDate(), lending16.getReturnDate());
        lending17.fineCalc(lending17.getStartDate(), lending17.getReturnDate());
        lending18.fineCalc(lending18.getStartDate(), lending18.getReturnDate());
        lending19.fineCalc(lending19.getStartDate(), lending19.getReturnDate());
        lending20.fineCalc(lending20.getStartDate(), lending20.getReturnDate());
        lending21.fineCalc(lending21.getStartDate(), lending21.getReturnDate());


        System.out.println("Multa calculada para o empréstimo: " + lending1.getFine());


        lendingRepository.saveAll(Arrays.asList(lending1,lending2,lending3,lending4,lending5,lending6,lending7,lending8,lending9,lending10,lending11,lending12,lending14,lending15,lending16,lending17,lending18,lending19,lending20,lending21));
        System.out.println("Empréstimo salvo: " + lending21.getLendingId());
    }
}