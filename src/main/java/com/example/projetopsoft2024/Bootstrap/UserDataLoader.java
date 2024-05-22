package com.example.projetopsoft2024.Bootstrap;

;
import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.Gender;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
                new User("Alice", "alice@example.com", new Date(), 123456789L, "sim"),
                new User("Bob", "bob@example.com", new Date(), 987654321L, "sim"),
                new User("Charlie", "charlie@example.com", new Date(), 555555555L, "sim"),
                new User("Diana", "diana@example.com", new Date(), 111111111L, "sim")

        );




        userRepository.saveAll(users);
    }
}