package com.example.projetopsoft2024.Controllers;


import com.example.projetopsoft2024.Service.UserService;
import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/Users")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping()
    public List<Users> getUsers(){
        return userService.getAllUsers();
    }
}
