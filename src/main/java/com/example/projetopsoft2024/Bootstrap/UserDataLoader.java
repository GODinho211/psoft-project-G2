package com.example.projetopsoft2024.Bootstrap;


import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.Gender;
import com.example.projetopsoft2024.models.RoleUser;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Component
@Order(4)
public class UserDataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GenderRepository genderRepository;

    @Autowired
    public UserDataLoader(UserRepository userRepository, GenderRepository genderRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.genderRepository = genderRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<Gender> fictionGenderOpt = genderRepository.findById(1L);
        Optional<Gender> nonFictionGenderOpt = genderRepository.findById(2L);
        Optional<Gender> romanceGenderOpt = genderRepository.findById(3L);

        Gender fictionGender = fictionGenderOpt.get();
        Gender nonFictionGender = nonFictionGenderOpt.get();
        Gender romanceGender = romanceGenderOpt.get();

        List<User> users = Arrays.asList(
                new User("Alice", "alice@example.com", new Date(100, 0, 15), 123456789L, 123456L, "sim", passwordEncoder.encode("pass"), RoleUser.LIBRARIAN, "A leitura é a porta para o conhecimento", Arrays.asList(fictionGender)),
                new User("Carlos", "carlos@example.com", new Date(99, 0, 15), 987654321L, 654321L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "O conhecimento é a base do progresso", Arrays.asList(nonFictionGender)),
                new User("Pedro", "pedro@example.com", new Date(98, 0, 15), 555555555L, 333333L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "A educação é a chave para o futuro", Arrays.asList(romanceGender)),
                new User("Diana", "diana@example.com", new Date(97, 0, 15), 111111111L, 999999L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "A leitura nos transporta para outros mundos", Arrays.asList(fictionGender)),
                new User("Mariana", "mariana@example.com", new Date(96, 0, 15), 222222222L, 222222L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Livros são portais para outros mundos", Arrays.asList(nonFictionGender)),
                new User("Joaquim", "joaquim@example.com", new Date(95, 0, 15), 333333333L, 444444L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Aprender é sempre um tesouro", Arrays.asList(romanceGender)),
                new User("Beatriz", "beatriz@example.com", new Date(94, 0, 15), 444444444L, 555555L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Ler é viajar sem sair do lugar", Arrays.asList(fictionGender)),
                new User("Gabriel", "gabriel@example.com", new Date(93, 0, 15), 555555555L, 666666L, "sim", passwordEncoder.encode("pass"), RoleUser.LIBRARIAN, "Uma biblioteca é uma farmácia para a alma", Arrays.asList(nonFictionGender)),
                new User("Inês", "ines@example.com", new Date(92, 0, 15), 666666666L, 777777L, "sim", passwordEncoder.encode("pass"), RoleUser.LIBRARIAN, "O conhecimento é poder", Arrays.asList(romanceGender)),
                new User("Bernardo", "bernardo@example.com", new Date(91, 0, 15), 777777777L, 888888L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Ler enriquece a mente e a alma", Arrays.asList(fictionGender)),
                new User("Isabela", "isabela@example.com", new Date(90, 0, 15), 888888888L, 999999L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Um livro é um amigo que nunca te abandona", Arrays.asList(nonFictionGender)),
                new User("João", "joao@example.com", new Date(89, 0, 15), 999999999L, 111111L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "A educação é a chave para o futuro", Arrays.asList(romanceGender)),
                new User("Sofia", "sofia@example.com", new Date(100, 0, 15), 111111111L, 222222L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "A leitura nos transporta para outros mundos", Arrays.asList(fictionGender)),
                new User("Laura", "laura@example.com", new Date(99, 0, 15), 222222222L, 333333L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Os livros são meus melhores amigos", Arrays.asList(nonFictionGender)),
                new User("Miguel", "miguel@example.com", new Date(98, 0, 15), 333333333L, 444444L, "sim", passwordEncoder.encode("pass"), RoleUser.LIBRARIAN, "Ler é como viajar sem sair do lugar", Arrays.asList(romanceGender)),
                new User("Ana", "ana@example.com", new Date(97, 0, 15), 444444444L, 555555L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "A leitura é a porta para o conhecimento", Arrays.asList(fictionGender)),
                new User("Tiago", "tiago@example.com", new Date(96, 0, 15), 555555555L, 666666L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "O conhecimento é a base do progresso", Arrays.asList(nonFictionGender)),
                new User("Clara", "clara@example.com", new Date(95, 0, 15), 666666666L, 777777L, "sim", passwordEncoder.encode("pass"), RoleUser.READER, "Um livro por dia afasta a ignorância para sempre", Arrays.asList(romanceGender))
        );


            userRepository.saveAll(users);

    }
}
