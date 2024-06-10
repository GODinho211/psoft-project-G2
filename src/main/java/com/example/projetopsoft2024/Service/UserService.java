package com.example.projetopsoft2024.Service;


import com.example.projetopsoft2024.Repositories.GenderRepository;
import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.DTO.UserDto;
import com.example.projetopsoft2024.models.Gender;
import com.example.projetopsoft2024.models.RoleUser;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(User::toUserDTO)
                .collect(Collectors.toList());
    }
    public Optional<User> getUserByReadernumber(Long readernumber) {
        return userRepository.findByReaderNumber(readernumber);
    }

    public List<User> getUsersByPhonenumber(Long phonenumber) {
        return userRepository.findByPhonenumber(phonenumber);
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

        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("O email deve estar em um formato válido");
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

    private boolean isValidEmail(String email) {
        // Utilizar a classe Pattern para verificar o padrão do e-mail
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
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

    public User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        return userRepository.findByUserEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public List<Book> getBooksByUserGenres(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getGenres().stream()
                .flatMap(genre -> genre.getBooks().stream())
                .distinct()
                .toList();
    }

    public User createUserPhoto(String name, String email, String password, String role, LocalDate dateOfBirth,
                                Long phoneNumber, String gdprConsent, String funnyQuote, List<Long> genderIds,
                                MultipartFile photoFile) {
        if (photoFile.getSize() > 20000) {
            throw new IllegalArgumentException("Photo size must not exceed 20KBytes");
        }

        List<Gender> genders = genderRepository.findAllById(genderIds);
        if (genders.isEmpty()) {
            throw new IllegalArgumentException("One or more genders not found");
        }




        byte[] photo;
        try {
            photo = photoFile.getBytes();
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to process photo file");
        }

        if (gdprConsent == null || !gdprConsent.equalsIgnoreCase("sim")) {
            throw new IllegalArgumentException("O usuário deve aceitar a política de privacidade de dados para se registrar no sistema.");
        }


        if (containsProhibitedWord(name)) {
            throw new IllegalArgumentException("O nome do usuário contém palavras proibidas.");
        }

        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("O email deve estar em um formato válido");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(RoleUser.valueOf(role.toUpperCase()));
        user.setDateofbirth(Date.from(dateOfBirth.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setPhonenumber(phoneNumber);
        user.setGdprconsent(gdprConsent);
        user.setFunnyQuote(funnyQuote);
        user.setGenres(genders);
        user.setPhoto_user(photo);


        return userRepository.save(user);
    }
    //comentario



    public Optional<UserDto> getUserDtoByEmail(String email) {
        return userRepository.findByUserEmail(email)
                .map(User::toUserDTO);
    }



}











