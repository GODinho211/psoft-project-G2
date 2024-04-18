package com.example.projetopsoft2024.Service;


import com.example.projetopsoft2024.Repositories.UserRepository;
import com.example.projetopsoft2024.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<Users>();
        userRepository.findAll().forEach(n -> users.add(n));
        return users;
    }
}
