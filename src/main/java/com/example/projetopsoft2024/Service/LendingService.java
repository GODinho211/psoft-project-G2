package com.example.projetopsoft2024.Service;


import com.example.projetopsoft2024.Repositories.BookRepository;
import com.example.projetopsoft2024.Repositories.LendingRepository;
import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Lending;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

        if (user == null || book == null) {
            return false;
        }

        // Verifica se o usuário possui empréstimos com multas pendentes
        List<Lending> userLendings = lendingRepository.findLendingByUserIdAndFineIsTrue(userId);
        if (!userLendings.isEmpty()) {
            throw new RuntimeException("Usuário possui multas pendentes e não pode fazer novos empréstimos!");
        }
        //verifica se user tem mais de 3 emprestimos.
        List<Lending> userLendingsMax = lendingRepository.findLendingByUserId(userId);
        if (userLendingsMax.size() >= 3) {
            throw new RuntimeException("Usuário possui o máximo de empréstimos possíveis (3)!");
        }

        List<Lending> bookLendings = lendingRepository.findLendingByBookIdAndReturnDateIsNull(bookId);
        if (!bookLendings.isEmpty()) {
            throw new RuntimeException("O livro não está disponível no momento!");
        }

        Lending lending = new Lending();
        lending.setUser(user);

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

    public List<Lending> findLendingByBookId(Long bookId) {
        return lendingRepository.findLendingByBookId(bookId);
    }

    public List<Lending> findLendingByUserId(Long userId) {
        return lendingRepository.findLendingByUserId(userId);
    }


    public void returnBook(Long lendingId) {
        // Encontrar o empréstimo pelo ID
        Lending lending = lendingRepository.findById(lendingId)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado!"));

        // Verificar se a data atual é superior à data de retorno
        if (LocalDate.now().isAfter(lending.getReturnDate())) {
            lending.setFine(true); // Define 'fine' como true se a data atual for superior à data de retorno
        } else {
            lending.setReturnDate(null); // Define a data de retorno como null se a data atual não for superior à data de retorno
        }

        // Atualizar o empréstimo no banco de dados
        lendingRepository.save(lending);
    }

}
