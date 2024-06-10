package com.example.projetopsoft2024.models.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LendingsPerMonthDTO {


    private String genreDescription;
    private int count;

}
