package com.example.projetopsoft2024.models.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LendingsPerMonthDTO {
    private int month;
    private int numberOfLendings;

    // Getters and setters
}
