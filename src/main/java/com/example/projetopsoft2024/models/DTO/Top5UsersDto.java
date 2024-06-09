package com.example.projetopsoft2024.models.DTO;

import com.example.projetopsoft2024.models.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Top5UsersDto {
    private Long readernumber;
    private String name;
    private String email;
    private String funnyQuote;
    private List<Gender> genres;

}