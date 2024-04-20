package com.example.projetopsoft2024.Service;


import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public  List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(n -> users.add(n));
        return users;
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
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

        userRepository.save(user);
        return "User created";
    }
    public void assignedReadnumber(Long id, User userUpdates) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (userUpdates.getReadernumber() != null) {
                String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
               // user.setReadernumber(userUpdates.getReadernumber());
                user.setReadernumber(Long.valueOf(year  + userUpdates.getReadernumber()));
            }
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User with id " + id + " not found");
        }
    }

    public void replaceUser(Long id, User user) {
        user.setUserId(id);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}










