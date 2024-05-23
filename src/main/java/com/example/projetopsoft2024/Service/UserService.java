package com.example.projetopsoft2024.Service;


import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Gender;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenderRepository genderRepository;

    public  List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(n -> users.add(n));
        return users;
    }
    public Optional<User> getUserByReadernumber(Long readernumber) {
        return userRepository.findByReaderNumber(readernumber);
    }
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }


    private static List<String> getProhibitedWords() {
        // Aqui você pode adicionar as palavras proibidas
        return Arrays.asList("palavraproibida","palavraproibida1", "palavraproibida2", "palavraproibida3");
    }


    public String createUser(User user) throws Exception {

        if (user.getGdprconsent() == null || !user.getGdprconsent().equalsIgnoreCase("sim")) {
            throw new Exception("O usuário deve aceitar a política de privacidade de dados para se registrar no sistema.");
        }

        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(user.getDateofbirth());
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH) ||
                (today.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        if (age < 12) {
            throw new Exception("O usuário deve ter pelo menos 12 anos para se registrar no sistema.");
        }
        if (containsProhibitedWord(user.getName())) {
            throw new Exception("O nome do usuário contém palavras proibidas.");
        }

        List<Gender> managedGenders = user.getGenres().stream()
                .map(gender -> genderRepository.findById(gender.getGenderId())
                        .orElseThrow(() -> new RuntimeException("Gender not found: " + gender.getGenderId())))
                .collect(Collectors.toList());

        // Set the managed genders to the user
        user.setGenres(managedGenders);

        userRepository.save(user);
        return "User created";
        }
        private boolean containsProhibitedWord(String username) {
            List<String> prohibitedWords = getProhibitedWords();
            for (String word : prohibitedWords) {
                if (username.toLowerCase().contains(word.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }

    public void replaceUser(Long readernumber , User user) throws Exception {

        if (containsProhibitedWord(user.getName())) {
            throw new Exception("O nome do usuário contém palavras proibidas.");
        }
        user.setReadernumber(readernumber);
        userRepository.save(user);
    }

    public void deleteUser(Long readernumber) {
        userRepository.deleteById(readernumber);
    }

    public Optional<User> getUserByReaderNumber(Long readerNumber) {
        return userRepository.findByReaderNumber(readerNumber);
    }

    public List<Book> getBooksByUserGenres(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getGenres().stream()
                .flatMap(genre -> genre.getBooks().stream())
                .distinct()
                .toList();
    }

}










