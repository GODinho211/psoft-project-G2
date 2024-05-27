package com.example.projetopsoft2024.Bootstrap;

;
import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.Gender;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDataLoader implements CommandLineRunner {
    private final UserRepository userRepository;

    @Autowired
    public UserDataLoader(UserRepository userRepository, GenderRepository genderRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public void run(String... args) throws Exception {

        List<User> users = Arrays.asList(
                new User("Alice", "alice@example.com", new Date(110, 1, 15), 123456789L, "sim", "Alice funny quote"),
                new User("Carlos", "Carlos@example.com", new Date(102, 3,22), 987654321L, "sim", "Carlos funny quote"),
                new User("Pedro", "Pedro@example.com", new Date(98,5,12), 555555555L, "sim", "Pedro funny quote"),
                new User("Diana", "diana@example.com", new Date(84,9,9), 111111111L, "sim", "Diana funny quote")

        );




        userRepository.saveAll(users);
    }
}