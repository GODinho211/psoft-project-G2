package com.example.projetopsoft2024.Bootstrap;


import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.RoleUser;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class UserDataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        List<User> users = Arrays.asList(
                new User("Alice", "alice@example.com", new Date(), 123456789L, 123456L, "sim",passwordEncoder.encode("pass"), RoleUser.LIBRARIAN, "Alice funny quote"),
                new User("Carlos", "carlos@example.com", new Date(), 987654321L, 654321L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Carlos funny quote"),
                new User("Pedro", "pedro@example.com", new Date(), 555555555L, 333333L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Pedro funny quote"),
                new User("Diana", "diana@example.com", new Date(), 111111111L, 999999L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Diana funny quote"),
                new User("Mariana", "mariana@example.com", new Date(), 222222222L, 222222L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Livros são portais para outros mundos"),
                new User("Joaquim", "joaquim@example.com", new Date(), 333333333L, 444444L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Aprender é sempre um tesouro"),
                new User("Beatriz", "beatriz@example.com", new Date(), 444444444L, 555555L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Ler é viajar sem sair do lugar"),
                new User("Gabriel", "gabriel@example.com", new Date(), 555555555L, 666666L, "sim", passwordEncoder.encode("pass"), RoleUser.LIBRARIAN, "Uma biblioteca é uma farmácia para a alma"),
                new User("Inês", "ines@example.com", new Date(), 666666666L, 777777L, "sim", passwordEncoder.encode("pass"), RoleUser.LIBRARIAN, "O conhecimento é poder"),
                new User("Bernardo", "bernardo@example.com", new Date(), 777777777L, 888888L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Ler enriquece a mente e a alma"),
                new User("Isabela", "isabela@example.com", new Date(), 888888888L, 999999L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Um livro é um amigo que nunca te abandona"),
                new User("João", "joao@example.com", new Date(), 999999999L, 111111L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "A educação é a chave para o futuro"),
                new User("Sofia", "sofia@example.com", new Date(), 111111111L, 222222L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "A leitura nos transporta para outros mundos"),
                new User("Laura", "laura@example.com", new Date(), 222222222L, 333333L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Os livros são meus melhores amigos"),
                new User("Miguel", "miguel@example.com", new Date(), 333333333L, 444444L, "sim", passwordEncoder.encode("pass"), RoleUser.LIBRARIAN, "Ler é como viajar sem sair do lugar"),
                new User("Ana", "ana@example.com", new Date(), 444444444L, 555555L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "A leitura é a porta para o conhecimento"),
                new User("Tiago", "tiago@example.com", new Date(), 555555555L, 666666L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "O conhecimento é a base do progresso"),
                new User("Clara", "clara@example.com", new Date(), 666666666L, 777777L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Um livro por dia afasta a ignorância para sempre")

        );

        userRepository.saveAll(users);
    }
}