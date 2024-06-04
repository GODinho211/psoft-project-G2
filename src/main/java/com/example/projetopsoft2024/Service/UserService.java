package com.example.projetopsoft2024.Service;


import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Gender;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public  List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(n -> users.add(n));
        return users;
    }
    public Optional<User> getUserByReadernumber(Long readernumber) {
        return userRepository.findByReaderNumber(readernumber);
    }

    public Optional<User> getUserByPhonenumber(Long phonenumber) {
        return userRepository.findByPhoneNumber(phonenumber);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByUserEmail(email);
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

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        List<Gender> managedGenders = user.getGenres().stream()
                .map(gender -> genderRepository.findById(gender.getGenderId())
                        .orElseThrow(() -> new RuntimeException("Gender not found: " + gender.getGenderId())))
                .collect(Collectors.toList());

        // Set the managed genders to the user
        user.setGenres(managedGenders);

        String funnyQuote = generateFunnyQuote(age);
        user.setFunnyQuote(funnyQuote);

        userRepository.save(user);
        return "User created";
        }

    private String generateFunnyQuote(int age) {List<String> quotes;
        if (age >= 12 && age <= 17) {
            quotes = Arrays.asList(
                    "Nunca é tarde demais para desejar uma estrela!",
                    "Por que o adolescente trouxe uma escada para a escola? Porque ouviu dizer que a escola estava 'motivadora'!",
                    "À medida que envelhece, três coisas acontecem: primeiro, sua memória se vai, e eu não consigo me lembrar das outras duas."
            );
        } else if (age >= 18 && age <= 25) {
            quotes = Arrays.asList(
                    "A juventude é um presente da natureza, mas a idade é uma obra de arte!",
                    "Por que os cientistas não confiam em átomos? Porque eles compõem tudo!",
                    "O problema de ser pontual é que ninguém está lá para apreciar."
            );
        } else if (age >= 26 && age <= 35) {
            quotes = Arrays.asList(
                    "Lembre-se, uma vez que você passa do auge, começa a ganhar velocidade.",
                    "A meia-idade é quando você está sentado em casa em uma noite de sábado e o telefone toca e você espera que não seja para você.",
                    "Estou em uma dieta de uísque. Já perdi três dias."
            );
        } else if (age >= 36 && age <= 50) {
            quotes = Arrays.asList(
                    "Você não está envelhecendo, apenas se tornando um clássico!",
                    "A vida começa aos 40 - mas também começam os pés chatos, o reumatismo, a visão defeituosa e a tendência de contar uma história para a mesma pessoa, três ou quatro vezes.",
                    "Não tenho 40 anos, tenho 18 com 22 anos de experiência."
            );
        } else {
            quotes = Arrays.asList(
                    "Envelhecer é obrigatório, mas crescer é opcional.",
                    "A idade é apenas o número de anos que o mundo tem desfrutado de você. Saúde!",
                    "Você não para de rir quando fica velho, fica velho quando para de rir."
            );
        }
        Random rand = new Random();
        return quotes.get(rand.nextInt(quotes.size()));
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

        user.setPassword(passwordEncoder.encode(user.getPassword()));

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










