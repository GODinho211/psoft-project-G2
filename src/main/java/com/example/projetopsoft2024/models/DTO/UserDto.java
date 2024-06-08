package com.example.projetopsoft2024.models.DTO;

import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Gender;
import com.example.projetopsoft2024.models.RoleUser;
import com.example.projetopsoft2024.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long readernumber;
    private String name;
    private String email;
    private String password;
    private Date dateofbirth;
    private Long phonenumber;
    private String funnyQuote;
    private List<Gender> genres;

}