package com.example.projetopsoft2024.Service;


import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
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
    public  String createUser(User user) {
        userRepository.save(user);
        return "User created";
    }
    public void assignedReadnumber(Long id, User userUpdates) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (userUpdates.getReadernumber() != null) {
                user.setReadernumber(userUpdates.getReadernumber());
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










